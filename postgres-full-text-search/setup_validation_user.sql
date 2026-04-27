
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_roles
        WHERE rolname = 'lecture_user'
    ) THEN
        CREATE ROLE lecture_user LOGIN PASSWORD 'lecture_pass';
    END IF;
END
$$;

SELECT 'CREATE DATABASE lecture_db OWNER lecture_user'
WHERE NOT EXISTS (
    SELECT 1
    FROM pg_database
    WHERE datname = 'lecture_db'
)\gexec

ALTER DATABASE lecture_db OWNER TO lecture_user;

\connect lecture_db

GRANT USAGE, CREATE ON SCHEMA public TO lecture_user;
ALTER SCHEMA public OWNER TO lecture_user;

CREATE SCHEMA IF NOT EXISTS search_example AUTHORIZATION lecture_user;
GRANT USAGE, CREATE ON SCHEMA search_example TO lecture_user;



/*
or use docker compose


  */
