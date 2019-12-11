drop table if exists categories cascade;
create table categories (id bigserial, title varchar(255), primary key(id));
insert into categories
(title) values
('FOOD'), ('DEVICES');

drop table if exists items cascade;
create table items (id bigserial, title varchar(255), category_id bigint, description varchar(5000), price int, primary key(id), constraint fk_cat_id foreign key (category_id) references categories (id));
insert into items
(title, category_id, description, price) values
('banana', 1, 'big yellow', 75),
('apple', 1, 'fresh red', 90),
('grapefruit', 1, 'good tasty', 105),
('lemon', 1, 'sour', 115),
('NoteBook ASUS X1000', 2, 'Model: ASUS X1000, CPU: Xeon N700, RAM: 128 Gb, SSD: 1Tb', 25000),
('Samsung S10', 2, 'CPU: Quallcom, RAM: 6GB', 80000),
('Iphone 11PRO', 2, 'CPU: A13, RAM: 3GB', 85000),
('Mi 10', 2, 'CPU: Quallcom, RAM: 8GB', 35000),
('HTC ONE', 2, 'CPU: Mediatek, RAM: 6GB', 45000);
