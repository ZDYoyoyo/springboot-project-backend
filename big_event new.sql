-- 創建資料庫
create database big_event;

-- 使用資料庫
use big_event;

-- 用戶表
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20)  character set utf8mb3 collate utf8mb3_general_ci not null unique comment '用戶名',
                      password varchar(32) character set utf8mb3 collate utf8mb3_general_ci comment '密碼',
                      nickname varchar(10)  character set utf8mb3 collate utf8mb3_general_ci default '' comment '暱稱',
                      email varchar(128)  character set utf8mb3 collate utf8mb3_general_ci default '' comment  '郵箱',
                      user_pic varchar(128) character set utf8mb3 collate utf8mb3_general_ci  default '' comment '頭像',
                      create_time datetime not null comment '創建時間',
                      update_time datetime not null comment '修改時間'
) comment '用戶表';

-- 分類表
create table category(
                        id int unsigned primary key auto_increment comment 'ID',
                        category_name varchar(32) character set utf8mb3 collate utf8mb3_general_ci not null comment '分類名稱',
                        category_alias varchar(32) character set utf8mb3 collate utf8mb3_general_ci not null comment '分類別名',
                        create_user int unsigned not null comment '創建人ID',
                        create_time datetime not null comment '創建時間',
                        update_time datetime not null comment '修改時間',
                        constraint fk_category_user foreign key (create_user) references user(id) -- 外鍵約束
);

-- 文章表
create table article(
                        id int unsigned primary key auto_increment comment 'ID',
                        title varchar(30) character set utf8mb3 collate utf8mb3_general_ci not null comment '文章標題',
                        content varchar(10000) character set utf8mb3 collate utf8mb3_general_ci not null comment '文章內容',
                        cover_img varchar(128) character set utf8mb3 collate utf8mb3_general_ci not null comment '文章封面',
                        state varchar(3) character set utf8mb3 collate utf8mb3_general_ci  not null comment '文章狀態: 只能是[已發布] 或者 [草稿]',
                        category_id int unsigned comment '文章分類ID',
                        create_user int unsigned not null comment '創建人ID',
                        create_time datetime not null comment '創建時間',
                        update_time datetime not null comment '修改時間',
                        constraint fk_article_category foreign key (category_id) references category(id),-- 外鍵約束
                        constraint fk_article_user foreign key (create_user) references user(id) -- 外鍵約束
);

