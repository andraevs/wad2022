for local development make sure to have a mariadb container running:

docker run -d --name some-mariadb  -e MARIADB_ROOT_PASSWORD=root -e MARIADB_DATABASE=ecommerce -v my-vol:/var/lib/mysql -p 3306:3306 mariadb


docker build -t spring-boot-docker .
docker run --name springboot spring-boot-docker

docker network create spring1
docker run -d --name some-mariadb --network spring1  -e MARIADB_ROOT_PASSWORD=root -e MARIADB_DATABASE=ecommerce -v my-vol:/var/lib/mysql -p 3306:3306 mariadb



docker compose down -v