CREATE TABLE IF NOT EXISTS writer
(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    firstName VARCHAR (50) NOT NULL,
    lastName VARCHAR (50) NOT NULL,
    posts  VARCHAR (200) NOT NULL
)