/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/19 13:06:36                          */
/*==============================================================*/
CREATE DATABASE weblog DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use weblog;

drop table if exists blog_ad;

drop table if exists blog_article;

drop table if exists blog_article_tag;

drop table if exists blog_category;

drop table if exists blog_comment;

drop table if exists blog_feedback;

drop table if exists blog_tag;

drop table if exists blog_user;

drop table if exists blog_user_care;

drop table if exists blog_user_favorite;

drop table if exists sys_acl;

drop table if exists sys_aclmodule;

drop table if exists sys_log;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_acl;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: blog_ad                                               */
/*==============================================================*/
create table blog_ad
(
   id                   int unsigned not null auto_increment comment 'ID',
   title                varchar(50) comment '标题',
   profile              varchar(255) comment '简介',
   url                  varchar(100) comment 'URL地址',
   publish              datetime comment '发布时间',
   seq                  tinyint unsigned comment '顺序',
   primary key (id)
);

alter table blog_ad comment '一些活动等';

/*==============================================================*/
/* Table: blog_article                                          */
/*==============================================================*/
create table blog_article
(
   id                   int unsigned not null auto_increment comment 'ID',
   title                varchar(50) not null comment '文章标题',
   profile              varchar(255) not null comment '文章简介',
   content              text not null comment '文章内容',
   publish              datetime comment '发布时间',
   author_id            int unsigned comment '作者ID',
   author_name          varchar(20) comment '作者昵称',
   category             int unsigned comment '文章类别',
   state                tinyint unsigned comment '文章状态：0草稿，1未审核，2已审核，3审核未过',
   favorite_count       smallint unsigned comment '收藏数量',
   recommend            tinyint unsigned comment '是否推荐：0 不，1推荐',
   primary key (id)
);

alter table blog_article comment '文章表';

/*==============================================================*/
/* Table: blog_article_tag                                      */
/*==============================================================*/
create table blog_article_tag
(
   article_id           int unsigned not null comment '文章id',
   tag_id               int unsigned not null comment '标签id',
   primary key (article_id, tag_id)
);

alter table blog_article_tag comment '文章和标签关联表';

/*==============================================================*/
/* Table: blog_category                                         */
/*==============================================================*/
create table blog_category
(
   id                   int unsigned not null auto_increment comment '分类ID',
   name                 varchar(20) not null comment '分类名',
   seq                  tinyint unsigned not null comment '排序',
   primary key (id)
);

alter table blog_category comment '文章分类表';

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table blog_comment
(
   id                   int unsigned not null auto_increment comment '分类ID',
   user_id              int unsigned comment '发表评论的用户的id',
   user_name            varchar(20) comment '发表评论的用户的昵称',
   article              int unsigned comment '哪个文章下的评论',
   content              varchar(255) comment '评论内容',
   publish              datetime comment '发表评论时间',
   author_id            int unsigned comment '文章的作者的Id',
   author_name          varchar(20) comment '文章的作者的昵称',
   seq                  tinyint unsigned comment '评论的顺序：1开始，自增',
   reply_user_id        int unsigned comment '哪个用户下的回复：0，表示无，',
   reply_seq            tinyint unsigned comment '回复的是哪个楼层：0表示跟评楼主',
   primary key (id)
);

/*==============================================================*/
/* Table: blog_feedback                                         */
/*==============================================================*/
create table blog_feedback
(
   id                   int unsigned not null auto_increment comment '分类ID',
   user_id              int unsigned comment '用户id',
   problem              varchar(255) comment '不足的地方',
   advice               varchar(255) comment '一些好的建议',
   email                varchar(50) comment '你的email(可选)',
   primary key (id)
);

alter table blog_feedback comment '用户反馈意见表';

/*==============================================================*/
/* Table: blog_tag                                              */
/*==============================================================*/
create table blog_tag
(
   id                   int unsigned not null auto_increment comment '标签ID',
   name                 varchar(20) not null comment '标签名',
   primary key (id)
);

alter table blog_tag comment '文章标签表';

/*==============================================================*/
/* Table: blog_user                                             */
/*==============================================================*/
create table blog_user
(
   id                   int unsigned not null auto_increment comment 'ID',
   nickname             varchar(20) comment '昵称，可选',
   username             char(11) not null comment '账号(手机号)',
   password             char(50) not null comment '密码',
   email                varchar(20),
   head                 text comment '头像text，可选',
   profile              varchar(255) comment '个人简介',
   url                  varchar(100) comment '个人博客地址',
   state                tinyint unsigned comment '账号状态：0未激活，1正常，2注销',
   create_time          datetime comment '账号创建时间及更改时间',
   primary key (id)
);

alter table blog_user comment '用户表';

/*==============================================================*/
/* Table: blog_user_care                                        */
/*==============================================================*/
create table blog_user_care
(
   user_id              int unsigned not null comment '用户id',
   idol_id              int unsigned not null comment '关注的用户id',
   primary key (user_id, idol_id)
);

alter table blog_user_care comment '用户关心表';

/*==============================================================*/
/* Table: blog_user_favorite                                    */
/*==============================================================*/
create table blog_user_favorite
(
   user_id              int unsigned not null comment '用户id',
   favorite_id          int unsigned not null comment '收藏的文章id',
   primary key (user_id, favorite_id)
);

alter table blog_user_favorite comment '用户收藏的文章';

/*==============================================================*/
/* Table: sys_acl                                               */
/*==============================================================*/
create table sys_acl
(
   id                   int unsigned not null auto_increment comment 'id',
   code                 char(20) comment '权限码',
   name                 varchar(50) comment '权限名',
   url                  varchar(100) comment '权限url',
   module_id            int unsigned comment '权限模块',
   type                 tinyint unsigned comment '权限类别',
   status               tinyint unsigned comment '权限状态',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '创建时间及更改时间',
   primary key (id)
);

alter table sys_acl comment '后台权限表';

/*==============================================================*/
/* Table: sys_aclmodule                                         */
/*==============================================================*/
create table sys_aclmodule
(
   id                   int unsigned not null auto_increment comment 'id',
   name                 varchar(50) comment '权限模块名',
   module_id            int unsigned comment '父模块',
   level                varchar(50) comment '模块等级',
   seq                  tinyint unsigned comment '顺序',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '创建时间及更改时间',
   primary key (id)
);

alter table sys_aclmodule comment '后台权限模块';

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   int unsigned not null auto_increment comment 'id',
   type                 tinyint unsigned comment ' 操作的是什么表,用int表示',
   target_id            int unsigned comment '操作的是哪个记录的ID',
   old_value            varchar(255) comment '旧值',
   new_value            varchar(255) comment '新值',
   operator             int unsigned comment '操作者',
   operator_time        datetime comment '操作时间',
   primary key (id)
);

alter table sys_log comment '后台操作记录表';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int unsigned not null auto_increment comment 'id',
   name                 varchar(50) comment '菜单名',
   module_id            int unsigned comment '父菜单',
   level                varchar(50) comment '菜单等级',
   seq                  tinyint unsigned comment '顺序',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '创建时间及更改时间',
   primary key (id)
);

alter table sys_menu comment '后台管理的页面的菜单';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int unsigned not null auto_increment comment 'ID',
   name                 varchar(50) comment '角色名',
   type                 tinyint unsigned comment '角色类型，目前为默认0',
   status               tinyint unsigned comment '角色状态',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '创建时间及修改时间',
   primary key (id)
);

alter table sys_role comment '后台用户角色表';

/*==============================================================*/
/* Table: sys_role_acl                                          */
/*==============================================================*/
create table sys_role_acl
(
   role_id              int unsigned not null,
   acl_id               int unsigned not null,
   primary key (acl_id, role_id)
);

alter table sys_role_acl comment '角色和权限关联表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int unsigned not null auto_increment comment 'ID',
   username             varchar(20) comment '账号',
   password             varchar(50) comment '密码',
   email                varchar(50) comment '邮箱',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '创建时间及更改时间',
   primary key (id)
);

alter table sys_user comment '后台管理账号';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   user_id              int unsigned not null,
   role_id              int unsigned not null,
   primary key (user_id, role_id)
);

alter table sys_user_role comment '账号和角色关联表';

