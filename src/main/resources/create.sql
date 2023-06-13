DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
   id INT AUTO_INCREMENT,
   name VARCHAR(50),
   description VARCHAR(150),
   price VARCHAR(10)
);

INSERT INTO products (id, name, description, price) VALUES (1, 'Plastic Bags', 'Plastic bags to shopping', '40,00');
INSERT INTO products (id, name, description, price) VALUES (2, 'Wallet Plan', 'Money Wallet', '20,00');
