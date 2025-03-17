For the database we need a spring_data database in MariaDB with user myuser and password pass

You can create a docker container with this command if you have Docker Desktop installed

docker run -d --name mariadb_container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spring_data -e MYSQL_USER=myuser -e MYSQL_PASSWORD=pass -p 3306:3306 -v mariadb_data:/var/lib/mysql mariadb:latest