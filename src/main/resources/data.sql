insert into product_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 'ACTIVE', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into product_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 'INACTIVE', current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into product(id, name, sku, status_id, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 'USB Flash Drive 8GB', 'USB-DRIVE-8GB', 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into product(id, name, sku, status_id, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 'Black Hydro Flask', 'HYDRO-FLASK-BLK', 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into product(id, name, sku, status_id, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 'Pink Hair Clip', 'HAIR-CLIP-PINK', 1, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into contact_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 'ACTIVE', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into contact_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 'INACTIVE', current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into supplier(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 1, 'Callan', 'Oneill', 'Orbit Push', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into supplier(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 1, 'Aiza', 'John', 'VidaSupply', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into supplier(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 1, 'Lyra', 'Wilson', 'Titan Contractors', current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 1, 'Nicole', 'Stark', 'Brick Masters', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 1, 'Eddie', 'Doyle', 'Cityscape Architects', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 1, 'Victor', 'Miller', 'Steel City Construction', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (4, 1, 'Trang', 'My', 'Reliable Renovations', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (5, 1, 'Robert', 'Angiolo', 'Elite Construction Group', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into customer(id, status_id, first_name, last_name, company, created_date, last_modified_date, created_by, last_modified_by)
	values (6, 1, 'Sota', 'Hiroto', 'Craftsman Carpentry', current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 'PENDING', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 'NEW', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 'PROCESSING', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (4, 'AWAITING_PAYMENT', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (5, 'PARTIALLY SHIPPED', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (6, 'FULLY_SHIPPED', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (7, 'CANCELLED', current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into order_status(id, status, created_date, last_modified_date, created_by, last_modified_by)
	values (8, 'COMPLETED', current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into purchase_order(id, status_id, address1, shipping_description, shipping_cost, total, supplier_id, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 1, '3446 Cardinal Lane', 'Standard', 5.5, 0, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 1, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 10, 0, 0, 2.1, 1, 1, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 2, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 2, 0, 0, 4.6, 1, 2, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into purchase_order(id, status_id, address1, shipping_description, shipping_cost, total, supplier_id, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 1, '3767 Science Center Drive', '2-day', 11.2, 0, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 1, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 25, 0, 0, 2.1, 2, 1, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into purchase_order(id, status_id, address1, shipping_description, shipping_cost, total, supplier_id, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 1, '4595 Mesa Drive', 'Overnight', 25.84, 0, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (4, 1, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 15, 0, 0, 1.7, 3, 3, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (5, 2, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 8, 0, 0, 4.6, 3, 2, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into purchase_order_line(id, line_number, sku, product_name, qty, qty_received, base_cost, cost, purchase_order_id, product_id, eta, created_date, last_modified_date, created_by, last_modified_by)
	values (6, 3, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 30, 0, 0, 2.1, 3, 1, current_timestamp(), current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (1, 1, '1046 Fowler Avenue', '3-day', 7, 0, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(1, 1, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 4, 0, 2.1, 12, 0, 1, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (2, 1, '2523 Duncan Avenue', '2-day', 10, 0, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(2, 1, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 2, 0, 4.6, 55, 0, 2, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(3, 2, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 10, 0, 1.7, 6.2, 0, 2, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (3, 1, '516 Anthony Avenue', 'Standard', 5, 0, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(4, 1, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 15, 0, 1.7, 6.2, 0, 3, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(5, 2, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 6, 0, 2.1, 12, 0, 3, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (4, 1, '1442 Meadow Drive', 'Free', 0, 0, 4, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(6, 1, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 5, 0, 2.1, 12, 0, 4, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(7, 2, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 4, 0, 4.6, 55, 0, 4, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(8, 3, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 10, 0, 1.7, 6.2, 0, 4, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (5, 1, '216 Lunetta Street', 'Overnight', 18, 0, 5, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(9, 1, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 35, 0, 1.7, 6.2, 0, 5, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (6, 1, '3047 Pineview Drive', 'Free', 0, 0, 6, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(10, 1, 'USB-DRIVE-8GB', 'USB Flash Drive 8GB', 9, 0, 2.1, 12, 0, 6, 1, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(11, 2, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 3, 0, 4.6, 55, 0, 6, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(12, 3, 'HAIR-CLIP-PINK', 'Pink Hair Clip', 15, 0, 1.7, 6.2, 0, 6, 3, current_timestamp(), current_timestamp(), 'admin', 'admin');

insert into sales_order(id, status_id, address1, shipping_description, shipping_cost, total, customer_id, created_date, last_modified_date, created_by, last_modified_by)
	values (7, 1, '3047 Pineview Drive', '3-day', 7, 0, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');
insert into sales_order_line(id, line_number, sku, product_name, qty, qty_allocated, cost, price, line_total, sales_order_id, product_id, created_date, last_modified_date, created_by, last_modified_by)
	values(13, 1, 'HYDRO-FLASK-BLK', 'Black Hydro Flask', 7, 0, 4.6, 55, 0, 7, 2, current_timestamp(), current_timestamp(), 'admin', 'admin');

