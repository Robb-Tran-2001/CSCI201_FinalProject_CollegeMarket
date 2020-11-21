USE marketplace;

INSERT INTO Users (name, password) VALUES ('John Doe', 'password'); 
INSERT INTO Users (name, password) VALUES ('alicebob', 'pw');

INSERT INTO Items (name, seller_id, price, description) VALUES ("aaa", 1, 12.0, "a desc");
INSERT INTO Items (name, seller_id, price, description) VALUES ("bbb", 2, 13.0, "b desc");
INSERT INTO Items (name, seller_id, buyer_id, price, description) VALUES ("ccc", 1, 2, 14.0, "c desc");