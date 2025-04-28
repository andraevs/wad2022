for local development make sure to have a mariadb container running:

docker run -d --name some-mariadb  -e MARIADB_ROOT_PASSWORD=root -e MARIADB_DATABASE=ecommerce -v my-vol:/var/lib/mysql -p 3306:3306 mariadb

for docker use docker compose up --build to start the app, make sure to package the app first