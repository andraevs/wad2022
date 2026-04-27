-- Prerequisite: run step01_schema_and_seed.sql first.

SET search_path TO search_example;

-- Inspect the document representation for one piece of text.
SELECT to_tsvector(
    'english',
    'Learning systems search articles by estimated relevance.'
) AS document_vector;

-- compare configurations for the same phrase.
SELECT
    to_tsvector('english', 'The articles are ranking learning systems') AS english_vector,
    to_tsvector('simple', 'The articles are ranking learning systems') AS simple_vector;

-- Build a combined article document from title and abstract.
SELECT
    article_id,
    title,
    to_tsvector(
        'english',
        coalesce(title, '') || ' ' || coalesce(abstract, '')
    ) AS article_document
FROM articles
ORDER BY article_id;

-- Include keywords as curated vocabulary for the same article document.
SELECT
    a.article_id,
    a.title,
    to_tsvector(
        'english',
        coalesce(a.title, '') || ' ' ||
        coalesce(a.abstract, '') || ' ' ||
        coalesce(string_agg(k.keyword, ' ' ORDER BY k.keyword), '')
    ) AS article_document
FROM articles AS a
LEFT JOIN article_keywords AS ak ON ak.article_id = a.article_id
LEFT JOIN keywords AS k ON k.keyword_id = ak.keyword_id
GROUP BY a.article_id, a.title, a.abstract
ORDER BY a.article_id;
