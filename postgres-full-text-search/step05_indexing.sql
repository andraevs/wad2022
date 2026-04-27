-- Prerequisite: run step01_schema_and_seed.sql first.

SET search_path TO search_example;

--  make keyword evidence available to a row-local generated column.
ALTER TABLE articles
ADD COLUMN IF NOT EXISTS keyword_text text NOT NULL DEFAULT '';

UPDATE articles AS a
SET keyword_text = coalesce((
    SELECT string_agg(kw.keyword, ' ' ORDER BY kw.keyword)
    FROM article_keywords AS ak
    JOIN keywords AS kw ON kw.keyword_id = ak.keyword_id
    WHERE ak.article_id = a.article_id
), '');

--  give the chosen article document expression a stable name.
ALTER TABLE articles
ADD COLUMN IF NOT EXISTS search_document tsvector
GENERATED ALWAYS AS (
    to_tsvector(
        'english',
        coalesce(title, '') || ' ' ||
        coalesce(abstract, '') || ' ' ||
        coalesce(keyword_text, '')
    )
) STORED;

--  create the GIN index on the same vector used by queries.
CREATE INDEX IF NOT EXISTS articles_search_document_gin
ON articles
USING gin (search_document);

-- The predicate uses the indexed representation.
SELECT article_id, title
FROM articles
WHERE search_document @@ to_tsquery('english', 'search')
ORDER BY article_id;
