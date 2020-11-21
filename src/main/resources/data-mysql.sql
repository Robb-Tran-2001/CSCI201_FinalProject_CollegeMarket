USE marketplace;

INSERT INTO Users (name, password) VALUES ('admin', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2');
INSERT INTO Users (name, password) VALUES ('John Doe', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Users (name, password) VALUES ('alicebob', '30c952fab122c3f9759f02a6d95c3758b246b4fee239957b2d4fee46e26170c4');

INSERT INTO Items (name, seller_id, price, description) VALUES ("aaa", 1, 12.0, "a desc");
INSERT INTO Items (name, seller_id, price, description) VALUES ("bbb", 2, 13.0, "b desc");
INSERT INTO Items (name, seller_id, buyer_id, price, description) VALUES ("ccc", 1, 2, 14.0, "c desc");