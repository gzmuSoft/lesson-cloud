create table if not exists course
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                           null comment '名称',
    spell       varchar(255)                           null comment '名称的全拼',
    `period`      smallint(6)  default 16                not null comment '基础学时',
    credit      float        default 1                 not null comment '基础学分',
    type        varchar(255) default '专业必修'            not null comment '课程性质',
    sort        smallint(6)                            null comment '排序',
    create_user varchar(255)                           null comment '创建用户名称',
    create_time datetime     default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                           null comment '末次更新用户名称',
    modify_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                           null comment '备注',
    is_enable   binary(1)    default 0x31              not null comment '是否可用，1：可用，0：不可用'
)
    comment '课程' charset = utf8;

create table if not exists exam
(
    id bigint auto_increment comment '编号'
        primary key,
    name varchar(255) null comment '名称',
    spell varchar(255) null comment '名称的全拼',
    start_time datetime null comment '开始时间',
    end_time datetime null comment '结束时间',
    publication_time datetime null comment '成绩公布时间',
    total_use_time int null comment '考试总用时限制（正数，如：60分钟）',
    total_score float null comment '满分分值',
    course_id bigint(255) null comment '课程编号',
    logic_class_ids varchar(512) null comment '参与考试的逻辑班级id列表，以逗号作为分隔符',
    method int(1) default 0 not null comment '出题类型：1指定题目、2顺序练习、3指定范围、4随机抽题',
    allow_times int null comment '可以考试的次数限制（正数，0代表可以无限次考试）',
    is_publish binary(1) default 0x31 not null comment '是否发布',
    sort smallint(6) null comment '排序',
    create_user varchar(255) null comment '创建用户名称',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255) null comment '末次更新用户名称',
    modify_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark varchar(255) null comment '备注',
    is_enable binary(1) default 0x31 not null comment '是否可用，1：可用，0：不可用'
)
    comment '考试' charset=utf8;


create table if not exists exam_rule
(
    id                   bigint auto_increment comment '编号'
        primary key,
    name                 varchar(255)                        null comment '名称',
    spell                varchar(255)                        null comment '名称的全拼',
    exam_id              bigint                              null comment '考试编号',
    question_type        varchar(255)                        null comment '题型（单项选择题、多项选择题、判断题、填空题、编程题）',
    question_count       int(255)                            null comment '题量',
    start_difficult_rate float                               null comment '起始难度系数',
    end_difficult_rate   float                               null comment '终止难度系数',
    each_value           float                               not null comment '每题分值',
    sort                 smallint(6)                         null comment '排序',
    create_user          varchar(255)                        null comment '创建用户名称',
    create_time          datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user          varchar(255)                        null comment '末次更新用户名称',
    modify_time          datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark               varchar(255)                        null comment '备注',
    is_enable            binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint exam_rule_fk
        foreign key (exam_id) references exam (id)
)
    comment '试卷组卷规则' charset = utf8;

create table if not exists logic_class
(
    id           bigint auto_increment comment '编号'
        primary key,
    name         varchar(255)                        null comment '名称',
    spell        varchar(255)                        null comment '名称的全拼',
    type         binary(1) default 0x30              not null comment '0：物理班级(classes_id值为实体班级id)，1：学生个体(student_id为学生实体id)',
    school_id    bigint                              not null comment '学校编号',
    college_id   bigint                              null comment '学院编号',
    dep_id       bigint                              null comment '系部编号',
    specialty_id bigint                              null comment '专业编号',
    classes_id   bigint                              null comment '实体班级id，type为0值，本字段值才有效',
    student_id   bigint                              null comment '学生实体id，type为1值，本字段值才有效',
    teacher_id   bigint                              null comment '教师编号',
    semester_id  bigint                              null comment '学期编号',
    course_id    bigint                              null comment '课程编号',
    `period`       smallint(6)                         null comment '学时',
    credit       float                               null comment '学分',
    course_type  varchar(255)                        null comment '课程性质',
    sort         smallint(6)                         null comment '排序',
    create_user  varchar(255)                        null comment '创建用户名称',
    create_time  datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user  varchar(255)                        null comment '末次更新用户名称',
    modify_time  datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark       varchar(255)                        null comment '备注',
    is_enable    binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用'
)
    comment '逻辑班级' charset = utf8;

create table if not exists course_timetable_location
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                        null comment '名称',
    spell          varchar(255)                        null comment '名称的全拼',
    logic_class_id bigint                              not null comment '逻辑班级编号',
    weeks          varchar(255)                        null comment '以逗号作为分隔符的各个周序号，例如：1,3,5,7代表第1周、第3周、第5周和第7周上课',
    location       varchar(255)                        null comment '上课地点',
    weekday        smallint(6)                         null comment '星期几，1：星期一，2：星期二，3：星期三，4：星期四，5：星期五，6：星期六，7：星期天',
    class_section  varchar(255)                        null comment '上课是第几节，例如：第1节和第2节上课记录为1~2，第1节至第3节上课记录为1~2~3。如果同一天有多个分开的时间段，则以逗号作为分隔符。例如：1~2;7~8',
    sort           smallint(6)                         null comment '排序',
    create_user    varchar(255)                        null comment '创建用户名称',
    create_time    datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                        null comment '末次更新用户名称',
    modify_time    datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                        null comment '备注',
    is_enable      binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint course_timetable_location_ibfk_1
        foreign key (logic_class_id) references logic_class (id)
            on update cascade on delete cascade
)
    comment '逻辑班级（课程）上课时间表及地点信息' charset = utf8;

create index FK_school
    on course_timetable_location (logic_class_id);

create index FK_school
    on logic_class (school_id);

create table if not exists paper
(
    id                    bigint auto_increment comment '编号'
        primary key,
    name                  varchar(255)                        null comment '名称',
    spell                 varchar(255)                        null comment '名称的全拼',
    exam_id               bigint                              null comment '考试编号',
    student_id            bigint                              null comment '学生编号',
    start_time            datetime                            null comment '考试开始时间',
    submit_time           datetime                            null comment '考试交卷时间',
    score                 float                               null comment '考试成绩',
    single_sel_ids        varchar(512)                        null comment '单项选择题id列表',
    single_sel_option_ids varchar(1024)                       null comment '单项选择题选项乱序之后的顺序列表（以逗号作为分隔符）',
    multi_sel_ids         varchar(512)                        null comment '多项选择题id列表',
    multi_sel_option_ids  varchar(1024)                       null comment '多项选择题选项乱序之后的顺序列表（以逗号作为分隔符）',
    judgement_ids         varchar(512)                        null comment '判断题id列表',
    judgement_option_ids  varchar(1024)                       null comment '判断题选项乱序之后的顺序列表（以逗号作为分隔符）',
    essay_ids             varchar(512)                        null comment '问答题id列表',
    program_ids           varchar(512)                        null comment '编程题id列表',
    sort                  smallint(6)                         null comment '排序',
    create_user           varchar(255)                        null comment '创建用户名称',
    create_time           datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user           varchar(255)                        null comment '末次更新用户名称',
    modify_time           datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark                varchar(255)                        null comment '备注',
    is_enable             binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint paper_exam_fk
        foreign key (exam_id) references exam (id)
)
    comment '试卷' charset = utf8;

create table if not exists exam_history
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                        null comment '名称',
    spell       varchar(255)                        null comment '名称的全拼',
    exam_id     bigint                              null comment '考试编号',
    student_id  bigint                              null comment '学生编号',
    max_score   float                               null comment '最高得分',
    paper_id    bigint                              null comment '得分最高的试卷编号',
    exam_time   datetime                            null comment '得分最高的考试的开始时间',
    times       int       default 0                 null comment '已考次数（生成paper时就计数+1）',
    sort        smallint(6)                         null comment '排序',
    create_user varchar(255)                        null comment '创建用户名称',
    create_time datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                        null comment '末次更新用户名称',
    modify_time datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                        null comment '备注',
    is_enable   binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint exam_history_exam_fk
        foreign key (exam_id) references exam (id),
    constraint exam_history_paper_fk
        foreign key (paper_id) references paper (id)
)
    comment '考试试卷历史记录' charset = utf8;

create index exam_history_student_fk
    on exam_history (student_id);

create index paper_student_fk
    on paper (student_id);

create table if not exists paper_detail
(
    id            bigint auto_increment comment '编号'
        primary key,
    name          varchar(255)                        null comment '名称',
    spell         varchar(255)                        null comment '名称的全拼',
    paper_id      bigint                              null comment '试卷编号',
    question_type smallint(6)                         null comment '0：单项选择题，1：多项选择题，2：判断题，3：填空题，4：简答题，5：编程题',
    question_id   bigint                              null comment '问题编号',
    ref_answer    varchar(255)                        null comment '参考答案（仅记录单项、多项、判断题这些客观题的参考答案，其他题的答案直接从题库中读取）',
    user_answer   varchar(2048)                       null comment '用户答案',
    org_value     float                               null comment '原始分值',
    obtain_value  float                               null comment '用户得分',
    sort          smallint(6)                         null comment '排序',
    create_user   varchar(255)                        null comment '创建用户名称',
    create_time   datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user   varchar(255)                        null comment '末次更新用户名称',
    modify_time   datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark        varchar(255)                        null comment '备注',
    is_enable     binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint paper_detail_paper_fk
        foreign key (paper_id) references paper (id)
            on update cascade on delete cascade
)
    comment '试卷详情及阅卷信息' charset = utf8;

create index paper_essay_essay_fk
    on paper_detail (question_id);

create table if not exists section
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                        null comment '名称',
    spell       varchar(255)                        null comment '名称的全拼',
    intro       varchar(1024)                       null comment '内容简介',
    course_id   bigint    default 0                 not null comment '课程编号',
    parent_id   bigint    default 0                 not null comment '0，代表无上级，即：课程的第一个章节',
    type        binary(1) default 0x31              not null comment '类型，0：章，1：节',
    sort        smallint(6)                         null comment '排序',
    create_user varchar(255)                        null comment '创建用户名称',
    create_time datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                        null comment '末次更新用户名称',
    modify_time datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                        null comment '备注',
    is_enable   binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint section_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade
)
    comment '章节' charset = utf8;

create table if not exists knowledge
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                        null comment '名称',
    spell       varchar(255)                        null comment '名称的全拼',
    intro       varchar(1024)                       null comment '内容简介',
    parent_id   bigint    default 0                 not null comment '0，代表无上级，即：课程的顶层知识点',
    course_id   bigint    default 0                 not null comment '课程编号',
    section_id  bigint    default 0                 not null comment '章节编号',
    sort        smallint(6)                         null comment '排序',
    create_user varchar(255)                        null comment '创建用户名称',
    create_time datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                        null comment '末次更新用户名称',
    modify_time datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                        null comment '备注',
    is_enable   binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint knowledge_course_ibfk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint knowledge_section_ibfk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    comment '知识点' charset = utf8;

create table if not exists essay
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                            null comment '题目',
    spell          varchar(255)                            null comment '题目的全拼',
    difficult_rate float         default 0.1               not null comment '难度系数，介于0~1之间',
    answer         varchar(2048) default '1'               not null comment '参考答案（可能包含图片）',
    explanation    varchar(2048)                           null comment '答案解析',
    course_id      bigint        default 0                 not null comment '课程编号',
    section_id     bigint        default 0                 not null comment '章节编号',
    knowledge_id   bigint        default 0                 not null comment '知识点编号',
    sort           smallint(6)                             null comment '排序',
    create_user    varchar(255)                            null comment '创建用户名称',
    create_time    datetime      default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                            null comment '末次更新用户名称',
    modify_time    datetime      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                            null comment '备注',
    is_enable      binary(1)     default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint essay_ibfk_1
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint essay_ibfk_2
        foreign key (knowledge_id) references knowledge (id)
            on update cascade on delete cascade,
    constraint essay_ibfk_3
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    comment '问答题' charset = utf8;

create index FK_essay_course
    on essay (course_id);

create index FK_essay_knowledge
    on essay (knowledge_id);

create index FK_essay_section
    on essay (section_id);

create table if not exists judgement
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                        null comment '题目',
    spell          varchar(255)                        null comment '题目的全拼',
    difficult_rate float     default 0.1               not null comment '难度系数，介于0~1之间',
    answer         binary(1) default 0x31              not null comment '参考答案（1：正确，0：错误）',
    explanation    varchar(2048)                       null comment '答案解析',
    course_id      bigint    default 0                 not null comment '课程编号',
    section_id     bigint    default 0                 not null comment '章节编号',
    knowledge_id   bigint    default 0                 not null comment '知识点编号',
    sort           smallint(6)                         null comment '排序',
    create_user    varchar(255)                        null comment '创建用户名称',
    create_time    datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                        null comment '末次更新用户名称',
    modify_time    datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                        null comment '备注',
    is_enable      binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint judgement_course_fk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint judgement_knowledge_fk
        foreign key (knowledge_id) references knowledge (id)
            on update cascade on delete cascade,
    constraint judgement_section_fk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    comment '判断题' charset = utf8;

create index FK_single_course
    on judgement (course_id);

create index FK_single_knowledge
    on judgement (knowledge_id);

create index FK_single_section
    on judgement (section_id);

create index FK_knowledge_course
    on knowledge (course_id);

create index FK_knowledge_section
    on knowledge (section_id);

create table if not exists multi_sel
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                        null comment '题目',
    spell          varchar(255)                        null comment '题目的全拼',
    difficult_rate float     default 0.1               not null comment '难度系数，介于0~1之间',
    answer         varchar(255)                        null comment '参考答案对应的选项在此题选项列表中的顺序，多个选项的以英文逗号分隔',
    explanation    varchar(2048)                       null comment '答案解析',
    course_id      bigint    default 0                 not null comment '课程编号',
    section_id     bigint    default 0                 not null comment '章节编号',
    knowledge_id   bigint    default 0                 not null comment '知识点编号',
    sort           smallint(6)                         null comment '排序',
    create_user    varchar(255)                        null comment '创建用户名称',
    create_time    datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                        null comment '末次更新用户名称',
    modify_time    datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                        null comment '备注',
    is_enable      binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
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
    comment '多项选择题' charset = utf8;

create index FK_multi_course
    on multi_sel (course_id);

create index FK_multi_knowledge
    on multi_sel (knowledge_id);

create index FK_multi_section
    on multi_sel (section_id);

create table if not exists program
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                            null comment '题目',
    spell          varchar(255)                            null comment '题目的全拼',
    difficult_rate float         default 0.1               not null comment '难度系数，介于0~1之间',
    answer         varchar(2048) default '1'               not null comment '参考答案',
    explanation    varchar(2048)                           null comment '答案解析',
    course_id      bigint        default 0                 not null comment '课程编号',
    section_id     bigint        default 0                 not null comment '章节编号',
    knowledge_id   bigint        default 0                 not null comment '知识点编号',
    sort           smallint(6)                             null comment '排序',
    create_user    varchar(255)                            null comment '创建用户名称',
    create_time    datetime      default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                            null comment '末次更新用户名称',
    modify_time    datetime      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                            null comment '备注',
    is_enable      binary(1)     default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint program_course_fk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint program_knowledge_fk
        foreign key (knowledge_id) references knowledge (id)
            on update cascade on delete cascade,
    constraint program_section_fk
        foreign key (section_id) references section (id)
            on update cascade on delete cascade
)
    comment '编程题' charset = utf8;

create index FK_single_course
    on program (course_id);

create index FK_single_knowledge
    on program (knowledge_id);

create index FK_single_section
    on program (section_id);

create index FK_section_course
    on section (course_id);

create table if not exists sel_options
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                          null comment '选项值',
    spell       varchar(255)                          null comment '选项值的全拼',
    question_id bigint      default 0                 not null comment '题目编号',
    type        smallint(6) default 0                 not null comment '题目类型，0：单项选择题，1：多项选择题，2：填空题',
    sort        smallint(6)                           null comment '排序',
    create_user varchar(255)                          null comment '创建用户名称',
    create_time datetime    default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                          null comment '末次更新用户名称',
    modify_time datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                          null comment '备注',
    is_enable   binary(1)   default 0x31              not null comment '是否可用，1：可用，0：不可用'
)
    comment '单项选择题、多项选择题、填空题的选项信息表，一个题目的选项可以有多个，不局限于4个选项' charset = utf8;

create table if not exists single_sel
(
    id             bigint auto_increment comment '编号'
        primary key,
    name           varchar(255)                          null comment '题目',
    spell          varchar(255)                          null comment '题目的全拼',
    difficult_rate float       default 0.1               not null comment '难度系数，介于0~1之间',
    answer         smallint(6) default 1                 not null comment '参考答案对应的选项在此题选项列表中的顺序',
    explanation    varchar(2048)                         null comment '答案解析',
    course_id      bigint      default 0                 not null comment '课程编号',
    section_id     bigint      default 0                 not null comment '章节编号',
    knowledge_id   bigint      default 0                 not null comment '知识点编号',
    sort           smallint(6)                           null comment '排序',
    create_user    varchar(255)                          null comment '创建用户名称',
    create_time    datetime    default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user    varchar(255)                          null comment '末次更新用户名称',
    modify_time    datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark         varchar(255)                          null comment '备注',
    is_enable      binary(1)   default 0x31              not null comment '是否可用，1：可用，0：不可用',
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
    comment '单项选择题' charset = utf8;

create index FK_single_course
    on single_sel (course_id);

create index FK_single_knowledge
    on single_sel (knowledge_id);

create index FK_single_section
    on single_sel (section_id);

create table if not exists sys_log
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(55)                            null comment '名称',
    spell       varchar(255) default ''                null comment '名称的全拼',
    browser     varchar(255)                           null comment '浏览器',
    operation   varchar(20)                            null comment '操作方式：GET/POST',
    from_url    varchar(1000)                          null comment '访问的实际url地址',
    ip          varchar(200)                           null comment '来源ip地址',
    url         varchar(255)                           null comment '访问url相对地址',
    args        varchar(255)                           null comment '请求参数',
    result      varchar(10240)                         null comment '返回结果',
    status      varchar(2)   default '1'               null comment '1-记录',
    sort        int          default 1                 null,
    create_user varchar(255)                           null comment '创建用户名称',
    create_time datetime     default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                           null comment '末次更新用户名称',
    modify_time datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                           null comment '备注',
    is_enable   binary(1)    default 0x31              not null comment '是否可用，1：可用，0：不可用'
)
    comment '系统日志表' charset = utf8;

create table if not exists sys_res
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                        null comment '名称',
    spell       varchar(255)                        null comment '名称的全拼',
    parent_id   bigint                              null comment '父权限资源编号',
    des         varchar(1024)                       null comment '描述',
    match_url   varchar(512)                        not null comment 'url 匹配',
    router      varchar(255)                        null comment '路由路径',
    component   varchar(255)                        null comment '组件名称',
    icon_cls    varchar(255)                        null comment '图标',
    level       int                                 null comment '层级',
    method      varchar(50)                         null comment '允许使用的方法：GET、POST、PUT、PATCH、DELETE、ALL',
    type        varchar(255)                        null comment '类型：1 功能 2 权限',
    sort        smallint(6)                         null comment '排序',
    create_user varchar(255)                        null comment '创建用户名称',
    create_time datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                        null comment '末次更新用户名称',
    modify_time datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                        null comment '备注',
    is_enable   binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用'
)
    comment '权限资源' charset = utf8;

create table if not exists sys_role_res
(
    id          bigint auto_increment comment '编号'
        primary key,
    name        varchar(255)                        null comment '名称',
    spell       varchar(255)                        null comment '名称的全拼',
    role_id     bigint                              null comment '角色编号',
    res_id      bigint                              null comment '权限资源编号',
    sort        smallint(6)                         null comment '排序',
    create_user varchar(255)                        null comment '创建用户名称',
    create_time datetime  default CURRENT_TIMESTAMP null comment '创建日期',
    modify_user varchar(255)                        null comment '末次更新用户名称',
    modify_time datetime  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '末次更新时间',
    remark      varchar(255)                        null comment '备注',
    is_enable   binary(1) default 0x31              not null comment '是否可用，1：可用，0：不可用',
    constraint sys_role_res_ibfk_1
        foreign key (res_id) references sys_res (id)
)
    comment '角色权限关联' charset = utf8;

create index FK_roleRes_res
    on sys_role_res (res_id);

create index FK_userAuth_ur
    on sys_role_res (role_id);

