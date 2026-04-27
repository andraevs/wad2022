-- Prerequisite: run step01_schema_and_seed.sql first.

SET search_path TO search_example;

SELECT article_id, title
FROM articles
WHERE title ILIKE '%learning%'
ORDER BY article_id;

SELECT article_id, title
FROM articles
WHERE title ILIKE '%search%'
   OR abstract ILIKE '%search%'
ORDER BY article_id;
