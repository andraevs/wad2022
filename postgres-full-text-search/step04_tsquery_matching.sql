-- Prerequisite: run step01_schema_and_seed.sql first.

SET search_path TO search_example;

--  inspect explicit tsquery expressions.
SELECT to_tsquery('english', 'machine & learning') AS both_terms_query;

SELECT to_tsquery('english', 'search | retrieval') AS either_term_query;

--  @@ compares a document vector with a query value.
SELECT
    to_tsvector('english', 'Machine learning improves article search')
    @@ to_tsquery('english', 'learn & search') AS document_matches_query;

--  compute the article document in the query for clarity.
SELECT article_id, title
FROM articles
WHERE to_tsvector('english', coalesce(title, '') || ' ' || coalesce(abstract, ''))
      @@ to_tsquery('english', 'search')
ORDER BY article_id;

-- Both terms are required by the explicit query expression.
SELECT article_id, title
FROM articles
WHERE to_tsvector('english', coalesce(title, '') || ' ' || coalesce(abstract, ''))
      @@ to_tsquery('english', 'search & rank')
ORDER BY article_id;
