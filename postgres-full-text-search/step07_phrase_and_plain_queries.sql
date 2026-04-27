-- Step goal: compare explicit, plain-text, and phrase query construction.
-- Prerequisite: run step05_indexing.sql first.

SET search_path TO search_example;

-- Slide 037: all three functions produce tsquery values.
SELECT
    to_tsquery('english', 'search & ranking') AS explicit_query,
    plainto_tsquery('english', 'search ranking') AS plain_query,
    phraseto_tsquery('english', 'digital library') AS phrase_query;

-- Slide 035: plain search-box text should use plainto_tsquery.
SELECT
    a.article_id,
    a.title,
    ts_rank(a.search_document, q.query) AS relevance_score
FROM articles AS a
JOIN (
    SELECT plainto_tsquery('english', 'search ranking') AS query
) AS q ON true
WHERE a.search_document @@ q.query
ORDER BY relevance_score DESC, a.article_id;

-- Slide 036: phrase intent should use phraseto_tsquery.
SELECT
    article_id,
    title
FROM articles
WHERE search_document @@ phraseto_tsquery('english', 'digital library')
ORDER BY article_id;
