USE marketplace;

CREATE TABLE IF NOT EXISTS Users(
	user_id INT NOT NULL UNIQUE AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(60) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Items(
	item_id INT NOT NULL UNIQUE AUTO_INCREMENT,
	seller_id INT NOT NULL,
	buyer_id INT,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	price FLOAT NOT NULL,
	images_json VARCHAR(500),
	PRIMARY KEY (item_id),
	FOREIGN KEY (seller_id) REFERENCES Users(user_id),
	FOREIGN KEY (buyer_id) REFERENCES Users(user_id)
);
