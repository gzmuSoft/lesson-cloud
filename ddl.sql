create schema if not exists `lesson-cloud` collate utf8mb4_unicode_ci;

create table if not exists course
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                             null comment '名称',
    spell       varchar(255)                             null comment '名称的全拼',
    period      smallint(6)  default 16                  not null comment '基础学时',
    credit      float        default 1                   not null comment '基础学分',
    type        varchar(255) default '专业必修'              not null comment '课程性质',
    sort        smallint(6)                              null comment '排序',
    create_user varchar(255)                             null comment '创建用户名称',
    create_time datetime     default current_timestamp() null comment '创建日期',
    modify_time varchar(255)                             null comment '末次更新用户名称',
    modify_user datetime     default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                             null comment '备注',
    is_enable   binary(1)    default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create table if not exists section
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                          null comment '名称',
    spell       varchar(255)                          null comment '名称的全拼',
    intro       varchar(1024)                         null comment '内容简介',
    course_id   bigint    default 0                   not null comment '课程编号',
    parent_id   bigint    default 0                   not null comment '0，代表无上级，即：课程的第一个章节',
    type        binary(1) default '1'                 not null comment '类型，0：章，1：节',
    sort        smallint(6)                           null comment '排序',
    create_user varchar(255)                          null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint section_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade
)
    charset = utf8;

create table if not exists knowledge
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                          null comment '名称',
    spell       varchar(255)                          null comment '名称的全拼',
    intro       varchar(1024)                         null comment '内容简介',
    parent_id   bigint    default 0                   not null comment '0，代表无上级，即：课程的顶层知识点',
    course_id   bigint    default 0                   not null comment '课程编号',
    section_id  bigint    default 0                   not null comment '章节编号',
    sort        smallint(6)                           null comment '排序',
    create_user varchar(255)                          null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint knowledge_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint knowledge_section_ibfk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    charset = utf8;

create index FK_knowledge_course
    on knowledge (course_id);

create index FK_knowledge_section
    on knowledge (section_id);

create table if not exists multi_sel
(
    id             bigint auto_increment comment '编号' primary key,
    name           varchar(255)                          null comment '题目',
    spell          varchar(255)                          null comment '题目的全拼',
    difficult_rate float     default 0.1                 not null comment '难度系数，介于0~1之间',
    answer         varchar(255)                          null comment '参考答案对应的选项在此题选项列表中的顺序，多个选项的以英文逗号分隔',
    explanation    varchar(1024)                         null comment '答案解析',
    course_id      bigint    default 0                   not null comment '课程编号',
    section_id     bigint    default 0                   not null comment '章节编号',
    knowledge_id   bigint    default 0                   not null comment '知识点编号',
    sort           smallint(6)                           null comment '排序',
    create_user    varchar(255)                          null comment '创建用户名称',
    create_time    datetime  default current_timestamp() null comment '创建日期',
    modify_user    varchar(255)                          null comment '末次更新用户名称',
    modify_time    datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark         varchar(255)                          null comment '备注',
    is_enable      binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint multi_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint multi_knowledge_ibfk
        foreign key (knowledge_id) references knowledge (id)
            on update cascade on delete cascade,
    constraint multi_section_ibfk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    charset = utf8;

create index FK_multi_course
    on multi_sel (course_id);

create index FK_multi_knowledge
    on multi_sel (knowledge_id);

create index FK_multi_section
    on multi_sel (section_id);

create index FK_section_course
    on section (course_id);

create table if not exists sel_options
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                            null comment '选项值',
    spell       varchar(255)                            null comment '选项值的全拼',
    question_id bigint      default 0                   not null comment '题目编号',
    type        smallint(6) default 0                   not null comment '题目类型，0：单项选择题，1：多项选择题，2：填空题',
    sort        smallint(6)                             null comment '排序',
    create_user varchar(255)                            null comment '创建用户名称',
    create_time datetime    default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                            null comment '末次更新用户名称',
    modify_time datetime    default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                            null comment '备注',
    is_enable   binary(1)   default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create table if not exists single_sel
(
    id             bigint auto_increment comment '编号' primary key,
    name           varchar(255)                            null comment '题目',
    spell          varchar(255)                            null comment '题目的全拼',
    difficult_rate float       default 0.1                 not null comment '难度系数，介于0~1之间',
    answer         smallint(6) default 1                   not null comment '参考答案对应的选项在此题选项列表中的顺序',
    explanation    varchar(1024)                           null comment '答案解析',
    course_id      bigint      default 0                   not null comment '课程编号',
    section_id     bigint      default 0                   not null comment '章节编号',
    knowledge_id   bigint      default 0                   not null comment '知识点编号',
    sort           smallint(6)                             null comment '排序',
    create_user    varchar(255)                            null comment '创建用户名称',
    create_time    datetime    default current_timestamp() null comment '创建日期',
    modify_user    varchar(255)                            null comment '末次更新用户名称',
    modify_time    datetime    default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark         varchar(255)                            null comment '备注',
    is_enable      binary(1)   default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint single_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint single_knowledge_ibfk
        foreign key (knowledge_id) references knowledge (id)
            on update cascade on delete cascade,
    constraint single_section_ibfk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    charset = utf8;

create index FK_single_course
    on single_sel (course_id);

create index FK_single_knowledge
    on single_sel (knowledge_id);

create index FK_single_section
    on single_sel (section_id);

create table if not exists sys_data
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(50)                             not null comment '名称',
    spell       varchar(255)                            null comment '名称的全拼',
    parent_id   bigint      default 0                   null comment '0，代表无上级，即：学校',
    brief       varchar(2048)                           null comment '简介',
    type        tinyint     default 0                   null comment '0：学校，1：学院，2：系部，3：专业，4：班级',
    sort        smallint(6) default 1                   null comment '同一type数据（如：学校）的排序顺序，值大于等于1',
    create_user bigint                                  null comment '创建用户名称',
    create_time datetime    default current_timestamp() null comment '创建日期',
    modify_user bigint                                  null comment '末次更新用户名称',
    modify_time datetime    default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                            null comment '备注',
    is_enable   binary(1)   default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create table if not exists semester
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                          null comment '名称',
    spell       varchar(255)                          null comment '名称的全拼',
    school_id   bigint                                not null comment '学校编号',
    startDate   date                                  null comment '起始日期',
    endDate     date                                  null comment '结束日期',
    sort        smallint(6)                           null comment '排序',
    create_name varchar(255)                          null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint semester_school_ibfk
        foreign key (school_id) references sys_data (id)
            on update cascade on delete cascade
)
    charset = utf8;

create index FK_school
    on semester (school_id);

create table if not exists sys_log
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(55)                              null comment '名称',
    spell       varchar(255) default ''                  null comment '名称的全拼',
    browser     varchar(255)                             null comment '浏览器',
    operation   varchar(20)                              null comment '操作方式：GET/POST',
    from_url    varchar(1000)                            null comment '访问的实际url地址',
    ip          varchar(200)                             null comment '来源ip地址',
    url         varchar(255)                             null comment '访问url相对地址',
    status      varchar(2)   default '1'                 null comment '1-记录',
    sort        int          default 1                   null,
    create_user varchar(255)                             null comment '创建用户名称',
    create_time datetime     default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                             null comment '末次更新用户名称',
    modify_time datetime     default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                             null comment '备注',
    is_enable   binary(1)    default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create table if not exists sys_res
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                          null comment '名称',
    spell       varchar(255)                          null comment '名称的全拼',
    parent_id   bigint                                null comment '父权限资源编号',
    des         varchar(1024)                         null comment '描述',
    url         varchar(512)                          null comment 'url地址',
    level       int                                   null comment '层级',
    icon_cls    varchar(255)                          null comment '图标',
    type        varchar(255)                          null comment '类型：1 功能 2 权限',
    sort        smallint(6)                           null comment '排序',
    create_user bigint                                null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user bigint                                null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    comment '权限资源' charset = utf8;

create table if not exists sys_role
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                            null comment '名称',
    spell       varchar(255)                            null comment '名称的全拼',
    des         varchar(128)                            null comment '描述',
    icon_cls    varchar(55) default 'status_online'     null comment '图标',
    parent_id   bigint      default 0                   null comment '父角色编号',
    sort        smallint(6)                             null comment '排序',
    create_user varchar(255)                            null comment '创建用户名称',
    create_time datetime    default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                            null comment '末次更新用户名称',
    modify_time datetime    default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                            null comment '备注',
    is_enable   binary(1)   default '1'                 not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create table if not exists sys_role_res
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(255)                          null comment '名称',
    spell       varchar(255)                          null comment '名称的全拼',
    role_id     bigint                                null comment '角色编号',
    res_id      bigint                                null comment '权限资源编号',
    sort        smallint(6)                           null comment '排序',
    create_user varchar(255)                          null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint sys_role_res_ibfk_1
        foreign key (res_id) references sys_res (id),
    constraint sys_role_res_ibfk_2
        foreign key (role_id) references sys_role (id)
)
    comment '角色权限关联' charset = utf8;

create index FK_roleRes_res
    on sys_role_res (res_id);

create index FK_userAuth_ur
    on sys_role_res (role_id);

create table if not exists sys_user
(
    id            bigint auto_increment comment '编号' primary key,
    name          varchar(55)                                null comment '名称',
    spell         varchar(255) default ''                    null comment '名称的全拼',
    entity_id     bigint                                     null comment '用户主体编号',
    entity_type   int(4)                                     null comment '0：系统管理员、1：教务管理员、2：课程管理员、3：教师、4：学生',
    salt          varchar(55)                                null comment '用于用户密码的加盐处理',
    pwd           varchar(255)                               null comment '密码',
    status        int(2)       default 1                     not null comment '1：正常、2：锁定一小时、3：禁用',
    icon          varchar(255) default '图标：images/guest.jpg' null,
    email         varchar(255)                               null comment '电子邮箱',
    phone         varchar(20)                                null comment '联系电话',
    online_status bit          default b'0'                  null comment '在线状态  1-在线 0-离线',
    sort          smallint(6)                                null comment '排序',
    create_user   varchar(255)                               null comment '创建用户名称',
    create_time   datetime     default current_timestamp()   null comment '创建日期',
    modify_user   varchar(255)                               null comment '末次更新用户名称',
    modify_time   datetime     default current_timestamp()   null on update current_timestamp() comment '末次更新时间',
    remark        varchar(255)                               null comment '备注',
    is_enable     tinyint(1)   default 1                     not null comment '是否可用，1：可用，0：不可用'
)
    charset = utf8;

create index entity_id
    on sys_user (entity_id);

create table if not exists sys_user_role
(
    id          bigint auto_increment comment '编号' primary key,
    name        varchar(254)                          null comment '名称',
    spell       varchar(254)                          null comment '名称的全拼',
    user_id     bigint                                null comment '用户编号',
    role_id     bigint                                null comment '角色编号',
    sort        smallint(6)                           null comment '排序',
    create_user varchar(255)                          null comment '创建用户名称',
    create_time datetime  default current_timestamp() null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime  default current_timestamp() null on update current_timestamp() comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1) default '1'                 not null comment '是否可用，1：可用，0：不可用',
    constraint FK_ur_role
        foreign key (role_id) references sys_role (id),
    constraint FK_ur_user
        foreign key (user_id) references sys_user (id)
)
    comment '用户角色关联' charset = utf8;

