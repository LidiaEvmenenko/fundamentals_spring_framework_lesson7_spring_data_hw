drop table if exists products;

create table products
(
  id bigserial primary key,
  title varchar(255),
  price int
);

insert into products (title, price)
values ('Bread', 25),
('Milk', 80),
('ketchup', 65),
('Pasta', 75),
('Tomato', 99),
('Cucumber', 78),
('Sour cream', 80),
('Water', 40),
('Chocolate', 59),
('Candies', 280),
('Cottage cheese', 150),
('Sausage', 80),
('Meat', 380),
('Mushrooms', 98),
('Ice cream', 54),
('Crisps', 40),
('Coca Cola', 80),
('Baton', 83),
('Apple', 79),
('Cheese', 450);