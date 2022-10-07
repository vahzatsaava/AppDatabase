CREATE TABLE IF NOT EXISTS post_labels(
    post_id  INT NOT NULL,
    label_id INT NOT NULL,

    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (label_id) REFERENCES labels(id),
    UNIQUE(post_id,label_id)
    )