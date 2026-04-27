
CREATE SCHEMA IF NOT EXISTS search_example;
SET search_path TO search_example;

DROP TABLE IF EXISTS article_keywords;
DROP TABLE IF EXISTS article_authors;
DROP TABLE IF EXISTS keywords;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS articles;

CREATE TABLE articles (
    article_id integer PRIMARY KEY,
    title text NOT NULL,
    abstract text NOT NULL,
    venue text NOT NULL,
    published_year integer NOT NULL
);

CREATE TABLE authors (
    author_id integer PRIMARY KEY,
    author_name text NOT NULL
);

CREATE TABLE article_authors (
    article_id integer NOT NULL REFERENCES articles(article_id),
    author_id integer NOT NULL REFERENCES authors(author_id),
    PRIMARY KEY (article_id, author_id)
);

CREATE TABLE keywords (
    keyword_id integer PRIMARY KEY,
    keyword text NOT NULL
);

CREATE TABLE article_keywords (
    article_id integer NOT NULL REFERENCES articles(article_id),
    keyword_id integer NOT NULL REFERENCES keywords(keyword_id),
    PRIMARY KEY (article_id, keyword_id)
);

INSERT INTO articles (article_id, title, abstract, venue, published_year) VALUES
    (1, 'Learning to Rank Academic Articles',
     'Ranking models help search systems order articles by estimated relevance.',
     'Journal of Information Retrieval', 2024),
    (2, 'Neural Methods for Citation Search',
     'The study compares neural retrieval methods for finding related research papers.',
     'Data Science Review', 2023),
    (3, 'Teaching Database Indexes',
     'This article explains B-tree and inverted indexes for database courses.',
     'Computer Science Education', 2022),
    (4, 'Phrase Search in Digital Libraries',
     'Digital library users often search for exact phrases in article abstracts.',
     'Library Systems Quarterly', 2024);

INSERT INTO authors (author_id, author_name) VALUES
    (1, 'Ada Novak'),
    (2, 'Mihai Ionescu'),
    (3, 'Leila Chen');

INSERT INTO article_authors (article_id, author_id) VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 3);

INSERT INTO keywords (keyword_id, keyword) VALUES
    (1, 'ranking'),
    (2, 'search'),
    (3, 'indexing'),
    (4, 'digital libraries');

INSERT INTO article_keywords (article_id, keyword_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 2),
    (4, 4);
