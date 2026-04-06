Inspect bootstrap.DataLoader to find default users and passwords.

docker run -d --name mariadb_container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=spring_data -e MYSQL_USER=myuser -e MYSQL_PASSWORD=pass -p 3306:3306 -v mariadb_data:/var/lib/mysql mariadb:latest
