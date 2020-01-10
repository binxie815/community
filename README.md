## 资源
[github授权登API](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
## 工具
##脚本
```sql
CREATE TABLE USER
(
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(100),
    TOKEN varchar(36),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint
)
```
