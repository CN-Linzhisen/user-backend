create table user_center.user
(
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                       not null comment '账号',
    userPassword varchar(512)                       not null comment '密码',
    userName     varchar(256)                       null comment '用户昵称',
    gender       tinyint                            null comment '性别',
    userAvatar   varchar(1024)                      null comment '用户头像',
    phone        varchar(256)                       null comment '电话',
    email        varchar(256)                       null comment '邮箱',
    userStatus   int                                null comment '用户状态',
    userRole     tinyint  default 0                 not null comment '用户角色：user/admin/ban',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_unicode_ci;

create index idx_unionId
    on user_center.user (userAccount);

