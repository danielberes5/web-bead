
drop table if exists replies;
drop table if exists blogs;

CREATE TABLE IF NOT EXISTS blogs (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NULL,
    content VARCHAR(250) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS replies (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       blog_id BIGINT NOT NULL,
                                       content VARCHAR(250) NULL,
                                       PRIMARY KEY (id),
                                       FOREIGN KEY (blog_id) REFERENCES blogs(id)
);

