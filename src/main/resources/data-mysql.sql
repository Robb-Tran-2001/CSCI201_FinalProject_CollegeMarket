USE marketplace;

INSERT INTO Users (name, password) 
	SELECT 'John Doe', 'password' 
	FROM DUAL WHERE NOT EXISTS (SELECT * FROM users       
									WHERE name='John Doe' 
										AND password='password' LIMIT 1);

