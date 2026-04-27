-- Prerequisite: run step05_indexing.sql first.

SET search_path TO search_example;

--  matching is still boolean; ranking adds an ordering signal.
WITH search_query AS (
    SELECT plainto_tsquery('english', 'search') AS query
)
SELECT
    a.article_id,
    a.title,
    a.published_year,
    ts_rank(a.search_document, q.query) AS relevance_score
FROM articles AS a
JOIN search_query AS q ON true
WHERE a.search_document @@ q.query
ORDER BY relevance_score DESC, a.article_id;
