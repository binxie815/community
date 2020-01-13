CREATE TABLE USER
(
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(100),
    TOKEN varchar(36),
    BIO varchar(256),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint,
)
CREATE TABLE question
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator INT,
    comment_count INT DEFAULT 0,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    tag VARCHAR(256)
);
COMMENT ON COLUMN question.creator IS '创建者';
COMMENT ON COLUMN question.comment_count IS '评论数';
COMMENT ON COLUMN question.view_count IS '浏览数';
COMMENT ON COLUMN question.like_count IS '点赞数';
COMMENT ON COLUMN USER.avatar_url IS '头像';
