SELECT    p1_0.id, 
          p1_0.address1, 
          p1_0.created_by, 
          p1_0.created_date, 
          p1_0.last_modified_by, 
          p1_0.last_modified_date, 
          p1_0.shipping_cost, 
          p1_0.shipping_description, 
          p1_0.status_id, 
          s1_0.id, 
          s1_0.company, 
          s1_0.created_by, 
          s1_0.created_date, 
          s1_0.first_name, 
          s1_0.last_modified_by, 
          s1_0.last_modified_date, 
          s1_0.last_name, 
          s1_0.status_id, 
          p1_0.total 
FROM      purchase_order p1_0 
left join supplier s1_0 
ON        s1_0.id=p1_0.supplier_id 
ORDER BY  p1_0.id ASC offset ? ROWS 
FETCH first ? ROWS ONLY;

SELECT p1_0.purchase_order_id, 
       p1_0.id, 
       p1_0.base_cost, 
       p1_0.cost, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.eta, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.line_number, 
       p2_0.id, 
       p2_0.created_by, 
       p2_0.created_date, 
       p2_0.last_modified_by, 
       p2_0.last_modified_date, 
       p2_0.name, 
       p2_0.sku, 
       p2_0.status_id, 
       p1_0.product_name, 
       p1_0.qty, 
       p1_0.qty_received, 
       p1_0.sku 
FROM   purchase_order_line p1_0 
       left join product p2_0 
              ON p2_0.id = p1_0.product_id 
WHERE  p1_0.purchase_order_id =? ; 


SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 


SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 

SELECT p1_0.purchase_order_id, 
       p1_0.id, 
       p1_0.base_cost, 
       p1_0.cost, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.eta, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.line_number, 
       p2_0.id, 
       p2_0.created_by, 
       p2_0.created_date, 
       p2_0.last_modified_by, 
       p2_0.last_modified_date, 
       p2_0.name, 
       p2_0.sku, 
       p2_0.status_id, 
       p1_0.product_name, 
       p1_0.qty, 
       p1_0.qty_received, 
       p1_0.sku 
FROM   purchase_order_line p1_0 
       left join product p2_0 
              ON p2_0.id = p1_0.product_id 
WHERE  p1_0.purchase_order_id =? ; 


SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 

SELECT p1_0.purchase_order_id, 
       p1_0.id, 
       p1_0.base_cost, 
       p1_0.cost, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.eta, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.line_number, 
       p2_0.id, 
       p2_0.created_by, 
       p2_0.created_date, 
       p2_0.last_modified_by, 
       p2_0.last_modified_date, 
       p2_0.name, 
       p2_0.sku, 
       p2_0.status_id, 
       p1_0.product_name, 
       p1_0.qty, 
       p1_0.qty_received, 
       p1_0.sku 
FROM   purchase_order_line p1_0 
       left join product p2_0 
              ON p2_0.id = p1_0.product_id 
WHERE  p1_0.purchase_order_id =? ; 


SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 

SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 

SELECT b1_0.purchase_order_line_id, 
       b1_0.id, 
       b1_0.created_by, 
       b1_0.created_date, 
       b1_0.last_modified_by, 
       b1_0.last_modified_date, 
       b1_0.location, 
       p1_0.id, 
       p1_0.created_by, 
       p1_0.created_date, 
       p1_0.last_modified_by, 
       p1_0.last_modified_date, 
       p1_0.name, 
       p1_0.sku, 
       p1_0.status_id, 
       b1_0.qty, 
       b1_0.qty_allocated 
FROM   batch b1_0 
       left join product p1_0 
              ON p1_0.id = b1_0.product_id 
WHERE  b1_0.purchase_order_line_id =? ; 