create schema kono collate utf8mb4_unicode_ci;

create table user_info
(
    user_id varchar(32) not null
        primary key,
    username varchar(16) not null comment '用户名',
    pass_word varchar(128) not null comment '密码',
    enabled tinyint default 1 null comment '是否可用  0 否 1 是',
    add_time datetime null,
    update_time datetime null,
    constraint user_info_username_uindex
        unique (username)
)
    comment '用户表';


