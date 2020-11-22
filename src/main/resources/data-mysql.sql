USE marketplace;

-- admin : root
INSERT INTO Users (name, password) VALUES ('admin', 'b867e4d87037b340c1490f78b05d9d1d102adf164223a571d5cd8027afaff1ce');
-- John Doe : pw
INSERT INTO Users (name, password) VALUES ('John Doe', 'a803fe15f35b64f6e923edaa1fb3b61a3e167522df044f9853dbaef2ea2e331a');
-- tommytrojan : fighton
INSERT INTO Users (name, password) VALUES ('tommytrojan', 'ba4a79abafb9b0e0a73b4456aa4b60c11683bbfb27cb65271f4332625cc9ae59');

INSERT INTO Items (name, seller_id, price, description) VALUES ("aaa", 1, 12.0, "a desc");
INSERT INTO Items (name, seller_id, price, description) VALUES ("bbb", 2, 13.0, "b desc");
INSERT INTO Items (name, seller_id, buyer_id, price, description) VALUES ("ccc", 1, 2, 14.0, "c desc");
INSERT INTO Items (name, seller_id, price, description) VALUES ("tumbleweed", 1, 1.0, "hey how'd this roll in here?");
INSERT INTO Items (name, seller_id, price, description) VALUES ("clay helton's hat", 3, 233, "now that's a lot of sweat");
<<<<<<< HEAD
INSERT INTO Items (name, seller_id, buyer_id, price, description) VALUES ("evk table", 1, 2, 24.0, "better than ikea");
INSERT INTO Items (name, seller_id, price, description) VALUES ("keys to SAL", 3, 999999.0, "treasure");
INSERT INTO Items (name, seller_id, price, description) VALUES ("tide pod", 2, 13.0, "inflation, eh?");
INSERT INTO Items (name, seller_id, buyer_id, price, description) VALUES ("cheese", 1, 2, 14.0, "of the cheddar variety");
=======
INSERT INTO Items (name, seller_id, price, description) VALUES ("evk table", 1, 24.0, "better than ikea");
INSERT INTO Items (name, seller_id, price, description) VALUES ("keys to SAL", 3, 999999.0, "treasure");
INSERT INTO Items (name, seller_id, price, description) VALUES ("tide pod", 2, 13.0, "inflation, eh?");
INSERT INTO Items (name, seller_id, price, description) VALUES ("cheese", 1, 14.0, "of the cheddar variety");
INSERT INTO Items (name, seller_id, price, description) VALUES ("more cheese", 1, 14.0, "of the swiss variety");
>>>>>>> main
