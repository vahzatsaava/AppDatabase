CREATE TABLE IF NOT EXISTS posts
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    content VARCHAR(50) NOT NULL ,
    created DATE NOT NULL ,
    updated DATE NOT NULL,
    writer_id INT ,
    FOREIGN KEY (writer_id) REFERENCES writers(id)
 )