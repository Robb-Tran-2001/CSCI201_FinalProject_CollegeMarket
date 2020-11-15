USE marketplace;

INSERT INTO Users (name, email, password)
	VALUES ('John Doe', 'jdoe@example.com', 'password');
	
INSERT INTO Items (seller_id, name, description, price, images_json)
	VALUES (1, 'table', 'used table in description', 10.99, '{json_here}');