/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/21 20:15:27                          */
/*==============================================================*/


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
   title                varchar(20) comment '����',
   profile              varchar(100) comment '���',
   url                  varchar(100) comment 'URL��ַ',
   publish              datetime comment '����ʱ��',
   seq                  tinyint unsigned comment '˳��',
   primary key (id)
);

alter table blog_ad comment 'һЩ���';

/*==============================================================*/
/* Table: blog_article                                          */
/*==============================================================*/
create table blog_article
(
   id                   int unsigned not null auto_increment comment 'ID',
   title                varchar(20) not null unique key  comment '���±���',
   profile              varchar(100) not null comment '���¼��',
   content              text not null comment '��������',
   publish              datetime comment '����ʱ��',
   author_id            int unsigned comment '����ID',
   author_name          varchar(20) comment '�����ǳ�',
   category             int unsigned comment '�������',
   state                tinyint unsigned comment '����״̬��0�ݸ壬1δ��ˣ�2����ˣ�3���δ��',
   favorite_count       smallint unsigned comment '�ղ�����',
   recommend            tinyint unsigned comment '�Ƿ��Ƽ���0 ����1�Ƽ�',
   primary key (id)
);

alter table blog_article comment '���±�';

/*==============================================================*/
/* Table: blog_article_tag                                      */
/*==============================================================*/
create table blog_article_tag
(
   article_id           int unsigned not null comment '����id',
   tag_id               int unsigned not null comment '��ǩid',
   primary key (article_id, tag_id)
);

alter table blog_article_tag comment '���ºͱ�ǩ������';

/*==============================================================*/
/* Table: blog_category                                         */
/*==============================================================*/
create table blog_category
(
   id                   int unsigned not null auto_increment comment '����ID',
   name                 varchar(20) not null unique key comment '������',
   seq                  tinyint unsigned not null comment '����',
   primary key (id)
);

alter table blog_category comment '���·����';

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table blog_comment
(
   id                   int unsigned not null auto_increment comment '����ID',
   user_id              int unsigned comment '�������۵��û���id',
   user_name            varchar(20) comment '�������۵��û����ǳ�',
   article              int unsigned comment '�ĸ������µ�����',
   content              varchar(100) comment '��������',
   publish              datetime comment '��������ʱ��',
   author_id            int unsigned comment '���µ����ߵ�Id',
   author_name          varchar(20) comment '���µ����ߵ��ǳ�',
   seq                  tinyint unsigned comment '���۵�˳��1��ʼ������',
   reply_user_id        int unsigned comment '�ĸ��û��µĻظ���0����ʾ�ޣ�',
   reply_seq            tinyint unsigned comment '�ظ������ĸ�¥�㣺0��ʾ����¥��',
   primary key (id)
);

/*==============================================================*/
/* Table: blog_feedback                                         */
/*==============================================================*/
create table blog_feedback
(
   id                   int unsigned not null auto_increment comment '����ID',
   user_id              int unsigned comment '�û�id',
   problem              varchar(255) comment '����ĵط�',
   advice               varchar(255) comment 'һЩ�õĽ���',
   is_read                tinyint unsigned comment '0δ��,1�Ѷ�',
   email                varchar(20) comment '���email(��ѡ)',
   create_time          datetime comment '����ʱ�估����ʱ��',
   primary key (id)
);

alter table blog_feedback comment '�û����������';

/*==============================================================*/
/* Table: blog_tag                                              */
/*==============================================================*/
create table blog_tag
(
   id                   int unsigned not null auto_increment comment '��ǩID',
   name                 varchar(20) not null unique key comment '��ǩ��',
   primary key (id)
);

alter table blog_tag comment '���±�ǩ��';

/*==============================================================*/
/* Table: blog_user                                             */
/*==============================================================*/
create table blog_user
(
   id                   int unsigned not null auto_increment comment 'ID',
   username             varchar(20) not null unique key comment '�û���',
   phone                char(11) unique key comment '�ֻ���',
   email                varchar(20) unique key comment '����',
   password             char(70) not null comment '����',
   head                 text comment 'ͷ��text��(��ѡ)',
   oneword              varchar(50) comment '����ǩ��',
   url                  varchar(100) comment '���˲��͵�ַ',
   state                tinyint unsigned comment '�˺�״̬��0δ���1������2ע��',
   create_time          datetime comment '�˺Ŵ���ʱ�估����ʱ��',
   primary key (id)
);

alter table blog_user comment '�û���';

create view blog_vuser as select *from blog_user where state<2;

/*==============================================================*/
/* Table: blog_user_care                                        */
/*==============================================================*/
create table blog_user_care
(
   user_id              int unsigned not null comment '�û�id',
   idol_id              int unsigned not null comment '��ע���û�id',
   primary key (user_id, idol_id)
);

alter table blog_user_care comment '�û����ı�';

/*==============================================================*/
/* Table: blog_user_favorite                                    */
/*==============================================================*/
create table blog_user_favorite
(
   user_id              int unsigned not null comment '�û�id',
   favorite_id          int unsigned not null comment '�ղص�����id',
   primary key (user_id, favorite_id)
);

alter table blog_user_favorite comment '�û��ղص�����';

/*==============================================================*/
/* Table: sys_acl                                               */
/*==============================================================*/
create table sys_acl
(
   id                   int unsigned not null auto_increment comment 'id',
   code                 varchar(20) unique key comment 'Ȩ����',
   name                 varchar(20) unique key comment 'Ȩ����',
   url                  varchar(100) comment 'Ȩ��url',
   module_id            int unsigned comment 'Ȩ��ģ��',
   type                 tinyint unsigned comment 'Ȩ�����',
   status               tinyint unsigned comment 'Ȩ��״̬',
   remark               varchar(100) comment '��ע',
   create_time          datetime comment '����ʱ�估����ʱ��',
   primary key (id)
);

alter table sys_acl comment '��̨Ȩ�ޱ�';

/*==============================================================*/
/* Table: sys_aclmodule                                         */
/*==============================================================*/
create table sys_aclmodule
(
   id                   int unsigned not null auto_increment comment 'id',
   name                 varchar(20) unique key comment 'Ȩ��ģ����',
   module_id            int unsigned comment '��ģ��',
   level                varchar(50) comment 'ģ��ȼ�',
   seq                  tinyint unsigned comment '˳��',
   remark               varchar(100) comment '��ע',
   create_time          datetime comment '����ʱ�估����ʱ��',
   primary key (id)
);

alter table sys_aclmodule comment '��̨Ȩ��ģ��';

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   int unsigned not null auto_increment comment 'id',
   type                 tinyint unsigned comment ' ��������ʲô��,��int��ʾ',
   target_id            int unsigned comment '���������ĸ���¼��ID',
   old_value            varchar(100) comment '��ֵ',
   new_value            varchar(100) comment '��ֵ',
   operator             int unsigned comment '������',
   operator_time        datetime comment '����ʱ��',
   primary key (id)
);

alter table sys_log comment '��̨������¼��';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int unsigned not null auto_increment comment 'id',
   name                 varchar(20) unique key comment '�˵���',
   module_id            int unsigned comment '���˵�',
   level                varchar(50) comment '�˵��ȼ�',
   seq                  tinyint unsigned comment '˳��',
   remark               varchar(100) comment '��ע',
   create_time          datetime comment '����ʱ�估����ʱ��',
   primary key (id)
);

alter table sys_menu comment '��̨�����ҳ��Ĳ˵�';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int unsigned not null auto_increment comment 'ID',
   name                 varchar(20) unique key comment '��ɫ��',
   type                 tinyint unsigned comment '��ɫ���ͣ�ĿǰΪĬ��0',
   status               tinyint unsigned comment '��ɫ״̬',
   remark               varchar(100) comment '��ע',
   create_time          datetime comment '����ʱ�估�޸�ʱ��',
   primary key (id)
);

alter table sys_role comment '��̨�û���ɫ��';

/*==============================================================*/
/* Table: sys_role_acl                                          */
/*==============================================================*/
create table sys_role_acl
(
   role_id              int unsigned not null,
   acl_id               int unsigned not null,
   primary key (acl_id, role_id)
);

alter table sys_role_acl comment '��ɫ��Ȩ�޹�����';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int unsigned not null auto_increment comment 'ID',
   username             varchar(20) unique key comment '�˺�',
   password             varchar(50) comment '����',
   email                varchar(20) comment '����',
   remark               varchar(100) comment '��ע',
   create_time          datetime comment '����ʱ�估����ʱ��',
   primary key (id)
);

alter table sys_user comment '��̨�����˺�';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   user_id              int unsigned not null,
   role_id              int unsigned not null,
   primary key (user_id, role_id)
);

alter table sys_user_role comment '�˺źͽ�ɫ������';

