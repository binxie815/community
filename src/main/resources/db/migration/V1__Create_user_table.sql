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
