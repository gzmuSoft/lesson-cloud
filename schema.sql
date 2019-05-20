/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : lesson-cloud

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 13/05/2019 21:33:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `period`      smallint(6)                                             NOT NULL DEFAULT 16 COMMENT '基础学时',
    `credit`      float                                                   NOT NULL DEFAULT 1 COMMENT '基础学分',
    `type`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '专业必修' COMMENT '课程性质',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '课程'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for essay
-- ----------------------------
DROP TABLE IF EXISTS `essay`;
CREATE TABLE `essay`
(
    `id`             bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目的全拼',
    `difficult_rate` float                                                    NOT NULL DEFAULT 0.1 COMMENT '难度系数，介于0~1之间',
    `answer`         varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '参考答案（可能包含图片）',
    `explanation`    varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '答案解析',
    `course_id`      bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`     bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `knowledge_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '知识点编号',
    `sort`           smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_essay_course` (`course_id`) USING BTREE,
    INDEX `FK_essay_knowledge` (`knowledge_id`) USING BTREE,
    INDEX `FK_essay_section` (`section_id`) USING BTREE,
    CONSTRAINT `essay_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `essay_ibfk_2` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `essay_ibfk_3` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '问答题'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`
(
    `id`             bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `start_time`     datetime(0)                                             NULL     DEFAULT NULL COMMENT '开始时间',
    `end_time`       datetime(0)                                             NULL     DEFAULT NULL COMMENT '结束时间',
    `total_use_time` int(11)                                                 NULL     DEFAULT NULL COMMENT '考试总用时限制（正数，如：60分钟）',
    `total_score`    float                                                   NULL     DEFAULT NULL COMMENT '满分分值',
    `course_id`      bigint(255)                                             NULL     DEFAULT NULL COMMENT '课程编号',
    `class_ids`      varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '参与考试的班级id列表，以分号作为分隔符',
    `allow_times`    int(11)                                                 NULL     DEFAULT NULL COMMENT '可以考试的次数限制（正数，0代表可以无限次考试）',
    `sort`           smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '考试'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_history
-- ----------------------------
DROP TABLE IF EXISTS `exam_history`;
CREATE TABLE `exam_history`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `exam_id`     bigint(20)                                              NULL     DEFAULT NULL COMMENT '考试编号',
    `student_id`  bigint(20)                                              NULL     DEFAULT NULL COMMENT '学生编号',
    `max_score`   float                                                   NULL     DEFAULT NULL COMMENT '最高得分',
    `paper_id`    bigint(20)                                              NULL     DEFAULT NULL COMMENT '得分最高的试卷编号',
    `exam_time`   datetime(0)                                             NULL     DEFAULT NULL COMMENT '得分最高的考试的开始时间',
    `times`       int(11)                                                 NULL     DEFAULT 0 COMMENT '已考次数（生成paper时就计数+1）',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `exam_history_exam_fk` (`exam_id`) USING BTREE,
    INDEX `exam_history_student_fk` (`student_id`) USING BTREE,
    INDEX `exam_history_paper_fk` (`paper_id`) USING BTREE,
    CONSTRAINT `exam_history_exam_fk` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `exam_history_paper_fk` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `exam_history_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '考试试卷历史记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_rule
-- ----------------------------
DROP TABLE IF EXISTS `exam_rule`;
CREATE TABLE `exam_rule`
(
    `id`                   bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `exam_id`              bigint(20)                                              NULL     DEFAULT NULL COMMENT '考试编号',
    `question_type`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '题型（单项选择题、多项选择题、判断题、填空题、编程题）',
    `question_count`       int(255)                                                NULL     DEFAULT NULL COMMENT '题量',
    `start_difficult_rate` float                                                   NULL     DEFAULT NULL COMMENT '起始难度系数',
    `end_difficult_rate`   float                                                   NULL     DEFAULT NULL COMMENT '终止难度系数',
    `each_value`           float                                                   NOT NULL COMMENT '每题分值',
    `sort`                 smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`          datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`          datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`            binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `exam_rule_fk` (`exam_id`) USING BTREE,
    CONSTRAINT `exam_rule_fk` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '试卷组卷规则'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for judgement
-- ----------------------------
DROP TABLE IF EXISTS `judgement`;
CREATE TABLE `judgement`
(
    `id`             bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目的全拼',
    `difficult_rate` float                                                    NOT NULL DEFAULT 0.1 COMMENT '难度系数，介于0~1之间',
    `answer`         binary(1)                                                NOT NULL DEFAULT 1 COMMENT '参考答案（1：正确，0：错误）',
    `explanation`    varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '答案解析',
    `course_id`      bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`     bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `knowledge_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '知识点编号',
    `sort`           smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_single_course` (`course_id`) USING BTREE,
    INDEX `FK_single_knowledge` (`knowledge_id`) USING BTREE,
    INDEX `FK_single_section` (`section_id`) USING BTREE,
    CONSTRAINT `judgement_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `judgement_knowledge_fk` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `judgement_section_fk` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '判断题'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge`
(
    `id`          bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `intro`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '内容简介',
    `parent_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '0，代表无上级，即：课程的顶层知识点',
    `course_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`  bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `sort`        smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_knowledge_course` (`course_id`) USING BTREE,
    INDEX `FK_knowledge_section` (`section_id`) USING BTREE,
    CONSTRAINT `knowledge_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `knowledge_section_ibfk` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '知识点'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for multi_sel
-- ----------------------------
DROP TABLE IF EXISTS `multi_sel`;
CREATE TABLE `multi_sel`
(
    `id`             bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目的全拼',
    `difficult_rate` float                                                    NOT NULL DEFAULT 0.1 COMMENT '难度系数，介于0~1之间',
    `answer`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '参考答案对应的选项在此题选项列表中的顺序，多个选项的以英文逗号分隔',
    `explanation`    varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '答案解析',
    `course_id`      bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`     bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `knowledge_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '知识点编号',
    `sort`           smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_multi_course` (`course_id`) USING BTREE,
    INDEX `FK_multi_knowledge` (`knowledge_id`) USING BTREE,
    INDEX `FK_multi_section` (`section_id`) USING BTREE,
    CONSTRAINT `multi_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `multi_knowledge_ibfk` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `multi_section_ibfk` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '多项选择题'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`
(
    `id`              bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `exam_id`         bigint(20)                                               NULL     DEFAULT NULL COMMENT '考试编号',
    `student_id`      bigint(20)                                               NULL     DEFAULT NULL COMMENT '学生编号',
    `start_time`      datetime(0)                                              NULL     DEFAULT NULL COMMENT '考试开始时间',
    `submit_time`     datetime(0)                                              NULL     DEFAULT NULL COMMENT '考试交卷时间',
    `score`           float                                                    NULL     DEFAULT NULL COMMENT '考试成绩',
    `single_sel_ids`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '单项选择题id列表',
    `single_sel_opts` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '单项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）',
    `multi_sel_ids`   varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '多项选择题id列表',
    `multi_sel_opts`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '多项选择题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）',
    `judgement_ids`   varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '判断题id列表',
    `judgement_opts`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '判断题选项乱序之后的顺序列表（以分号作为分隔符，一个题的多个选项以逗号作为分隔符）',
    `essay_ids`       varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '问答题id列表',
    `program_ids`     varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '编程题id列表',
    `sort`            smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`     datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`     datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`       binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `paper_exam_fk` (`exam_id`) USING BTREE,
    INDEX `paper_student_fk` (`student_id`) USING BTREE,
    CONSTRAINT `paper_exam_fk` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `paper_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '试卷'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper_detail
-- ----------------------------
DROP TABLE IF EXISTS `paper_detail`;
CREATE TABLE `paper_detail`
(
    `id`            bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `paper_id`      bigint(20)                                               NULL     DEFAULT NULL COMMENT '试卷编号',
    `question_type` smallint(6)                                              NULL     DEFAULT NULL COMMENT '0：单项选择题，1：多项选择题，2：判断题，3：填空题，4：简答题，5：编程题',
    `question_id`   bigint(20)                                               NULL     DEFAULT NULL COMMENT '问题编号',
    `ref_answer`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '参考答案（仅记录单项、多项、判断题这些客观题的参考答案，其他题的答案直接从题库中读取）',
    `user_answer`   varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '用户答案',
    `org_value`     float                                                    NULL     DEFAULT NULL COMMENT '原始分值',
    `obtain_value`  float                                                    NULL     DEFAULT NULL COMMENT '用户得分',
    `sort`          smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`   datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`   datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`     binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `paper_essay_essay_fk` (`question_id`) USING BTREE,
    INDEX `paper_detail_paper_fk` (`paper_id`) USING BTREE,
    CONSTRAINT `paper_detail_paper_fk` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '试卷详情及阅卷信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program`
(
    `id`             bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目的全拼',
    `difficult_rate` float                                                    NOT NULL DEFAULT 0.1 COMMENT '难度系数，介于0~1之间',
    `answer`         varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '参考答案',
    `explanation`    varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '答案解析',
    `course_id`      bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`     bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `knowledge_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '知识点编号',
    `sort`           smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_single_course` (`course_id`) USING BTREE,
    INDEX `FK_single_knowledge` (`knowledge_id`) USING BTREE,
    INDEX `FK_single_section` (`section_id`) USING BTREE,
    CONSTRAINT `program_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `program_knowledge_fk` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `program_section_fk` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '编程题'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`
(
    `id`          bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `intro`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '内容简介',
    `course_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `parent_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '0，代表无上级，即：课程的第一个章节',
    `type`        binary(1)                                                NOT NULL DEFAULT 1 COMMENT '类型，0：章，1：节',
    `sort`        smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_section_course` (`course_id`) USING BTREE,
    CONSTRAINT `section_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '章节'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sel_options
-- ----------------------------
DROP TABLE IF EXISTS `sel_options`;
CREATE TABLE `sel_options`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '选项值',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '选项值的全拼',
    `question_id` bigint(20)                                              NOT NULL DEFAULT 0 COMMENT '题目编号',
    `type`        smallint(6)                                             NOT NULL DEFAULT 0 COMMENT '题目类型，0：单项选择题，1：多项选择题，2：填空题',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '单项选择题、多项选择题、填空题的选项信息表，一个题目的选项可以有多个，不局限于4个选项'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for single_sel
-- ----------------------------
DROP TABLE IF EXISTS `single_sel`;
CREATE TABLE `single_sel`
(
    `id`             bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '题目的全拼',
    `difficult_rate` float                                                    NOT NULL DEFAULT 0.1 COMMENT '难度系数，介于0~1之间',
    `answer`         smallint(6)                                              NOT NULL DEFAULT 1 COMMENT '参考答案对应的选项在此题选项列表中的顺序',
    `explanation`    varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '答案解析',
    `course_id`      bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '课程编号',
    `section_id`     bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '章节编号',
    `knowledge_id`   bigint(20)                                               NOT NULL DEFAULT 0 COMMENT '知识点编号',
    `sort`           smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_single_course` (`course_id`) USING BTREE,
    INDEX `FK_single_knowledge` (`knowledge_id`) USING BTREE,
    INDEX `FK_single_section` (`section_id`) USING BTREE,
    CONSTRAINT `single_course_ibfk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `single_knowledge_ibfk` FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `single_section_ibfk` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '单项选择题'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_data`;
CREATE TABLE `sys_data`
(
    `id`          bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `parent_id`   bigint(20)                                               NULL     DEFAULT 0 COMMENT '0，代表无上级，即：学校',
    `brief`       varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '简介',
    `type`        tinyint(4)                                               NULL     DEFAULT 0 COMMENT '0：学校，1：学院，2：系部，3：专业，4：班级，5：性别，6：学历，7：学位，8：教师毕业专业，9：民族，10：研究方向，11：职称',
    `sort`        smallint(6)                                              NULL     DEFAULT 1 COMMENT '同一type数据（如：学校）的排序顺序，值大于等于1',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统基本数据表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`          bigint(20)                                                NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT '' COMMENT '名称的全拼',
    `browser`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '浏览器',
    `operation`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL     DEFAULT NULL COMMENT '操作方式：GET/POST',
    `from_url`    varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '访问的实际url地址',
    `ip`          varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '来源ip地址',
    `url`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '访问url相对地址',
    `args`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   null     DEFAULT NULL comment '请求参数',
    `result`      varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci null     DEFAULT NULL comment '返回结果',
    `status`      varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci     NULL     DEFAULT '1' COMMENT '1-记录',
    `sort`        int(11)                                                   NULL     DEFAULT 1,
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                               NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                               NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                                 NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统日志表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log`
VALUES (1, '123', '123', '123', '123', '123', '1232', '123', '1', 1, NULL, '2019-04-13 15:03:34', NULL,
        '2019-04-13 15:18:52', NULL, 0x31);
INSERT INTO `sys_log`
VALUES (2, '123', '123', '123', '123', '123', '1232', '123', '1', 1, NULL, '2019-04-13 15:03:34', NULL,
        '2019-04-13 15:03:42', NULL, 0x31);

-- ----------------------------
-- Table structure for sys_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res`
(
    `id`          bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `parent_id`   bigint(20)                                               NULL     DEFAULT NULL COMMENT '父权限资源编号',
    `des`         varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `match_url`   varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT 'url 匹配',
    `router`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '路由路径',
    `component`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '组件名称',
    `icon_cls`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '图标',
    `level`       int(11)                                                  NULL     DEFAULT NULL COMMENT '层级',
    `method`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '允许使用的方法：GET、POST、PUT、PATCH、DELETE、ALL',
    `type`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '类型：1 功能 2 权限',
    `sort`        smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '权限资源'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_res
-- ----------------------------
INSERT INTO `sys_res`
VALUES (1, '手机登录', NULL, NULL, 'oauth 手机登录', '/oauth/sms', '-', '-', '-', 0, 'GET', NULL, NULL, NULL,
        '2019-04-20 17:11:01', NULL, '2019-04-20 17:20:33', NULL, 0x31);
INSERT INTO `sys_res`
VALUES (2, '日志管理', NULL, NULL, '获取日志信息', '/sysLogs/**', '/sysLogs', 'sysLogs', NULL, 0, 'GET', NULL, NULL, NULL,
        '2019-04-20 17:11:55', NULL, '2019-04-20 17:22:54', NULL, 0x31);
INSERT INTO `sys_res`
VALUES (3, '日志管理-新增', NULL, NULL, '新增日志信息', '/sysLogs/**', NULL, NULL, NULL, 2, 'POST', NULL, NULL, NULL,
        '2019-04-20 17:21:09', NULL, '2019-04-20 17:22:54', NULL, 0x31);
INSERT INTO `sys_res`
VALUES (4, '日志管理-删除', NULL, NULL, '删除日志信息', '/sysLogs/**', NULL, NULL, NULL, 2, 'DELETE', NULL, NULL, NULL,
        '2019-04-20 17:22:54', NULL, '2019-04-20 17:22:54', NULL, 0x31);
INSERT INTO `sys_res`
VALUES (5, '日志管理-编辑', NULL, NULL, '编辑日志信息', '/sysLogs/**', NULL, NULL, NULL, 2, 'PUT', NULL, NULL, NULL,
        '2019-04-20 17:22:54', NULL, '2019-04-20 17:22:54', NULL, 0x31);
INSERT INTO `sys_res`
VALUES (6, '用户管理', NULL, NULL, '获取用户信息', '/sysLogs/**', NULL, NULL, NULL, 0, 'GET', NULL, NULL, NULL,
        '2019-04-20 17:22:54', NULL, '2019-04-20 17:22:54', NULL, 0x31);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `des`         varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `icon_cls`    varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT 'status_online' COMMENT '图标',
    `parent_id`   bigint(20)                                              NOT NULL DEFAULT 0 COMMENT '父角色编号',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, 'ROLE_ADMIN', '管理员', '开放所有资源访问权限', 'status_online', 0, 1, 'admin', '2019-04-19 22:48:15', 'admin',
        '2019-04-20 17:08:39', NULL, 0x31);
INSERT INTO `sys_role`
VALUES (2, 'ROLE_ANONYMOUS', '匿名资源', '无需任何认证即可访问', 'status_online', 0, 3, 'admin', '2019-04-20 16:58:45', 'admin',
        '2019-04-20 17:09:39', NULL, 0x31);
INSERT INTO `sys_role`
VALUES (3, 'ROLE_TEACHER', '教师', '开放部分权限', 'status_online', 0, 2, 'admin', '2019-04-19 22:48:25', 'admin',
        '2019-04-20 17:09:38', NULL, 0x31);
INSERT INTO `sys_role`
VALUES (4, 'ROLE_STUDENT', '学生', '开放部分资源', 'status_online', 0, 4, 'admin', '2019-04-20 17:10:10', 'admin',
        '2019-04-20 17:10:10', NULL, 0x31);

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `role_id`     bigint(20)                                              NULL     DEFAULT NULL COMMENT '角色编号',
    `res_id`      bigint(20)                                              NULL     DEFAULT NULL COMMENT '权限资源编号',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_roleRes_res` (`res_id`) USING BTREE,
    INDEX `FK_userAuth_ur` (`role_id`) USING BTREE,
    CONSTRAINT `sys_role_res_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `sys_res` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `sys_role_res_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色权限关联'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`            bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`          varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT '' COMMENT '名称的全拼',
    `entity_id`     bigint(20)                                              NULL     DEFAULT NULL COMMENT '用户主体编号',
    `entity_type`   int(4)                                                  NULL     DEFAULT NULL COMMENT '0：系统管理员、1：教务管理员、2：课程管理员、3：教师、4：学生',
    `pwd`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '密码',
    `status`        int(2)                                                  NOT NULL DEFAULT 1 COMMENT '1：正常、2：锁定一小时、3：禁用',
    `icon`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT '图标：images/guest.jpg' COMMENT '图标',
    `email`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '电子邮箱',
    `phone`         varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '联系电话',
    `online_status` bit(1)                                                  NULL     DEFAULT b'0' COMMENT '在线状态  1-在线 0-离线',
    `sort`          smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`   datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`   datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`     tinyint(1)                                              NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `sys_user_email_uindex` (`email`) USING BTREE,
    UNIQUE INDEX `sys_user_name_uindex` (`name`) USING BTREE,
    UNIQUE INDEX `sys_user_phone_uindex` (`phone`) USING BTREE,
    INDEX `entity_id` (`entity_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', '', NULL, 0, '$2a$10$TtwVLF9OMb5/LhZYgC1mAepnQMPoG.rdNWKIrUvn6NlzHvD.jRITW', 1,
        '图标：images/guest.jpg', 'lizhongyue248@163.com', '13765308262', b'0', 1, 'admin', '2019-04-20 17:07:50', 'admin',
        '2019-04-20 17:12:45', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `user_id`     bigint(20)                                              NOT NULL COMMENT '用户编号',
    `role_id`     bigint(20)                                              NOT NULL COMMENT '角色编号',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_ur_role` (`role_id`) USING BTREE,
    INDEX `FK_ur_user` (`user_id`) USING BTREE,
    CONSTRAINT `FK_ur_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_ur_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户角色关联'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, '管理员权限赋予管理员', NULL, 1, 1, NULL, NULL, '2019-04-20 17:23:12', NULL, '2019-04-20 17:23:12', NULL, 0x31);

/*
调整说明：
1、增加用户申诉表appeal、逻辑班级表logic_class
2、修改semester表中原先误写的3个字段名
3、student表增加学号no和身份证号id_number字段
4、teacher表删除多余的phone和e-mail字段
5、userconnection表更名为user_connection，以便统一命名方式
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appeal
-- ----------------------------
DROP TABLE IF EXISTS `appeal`;
CREATE TABLE `appeal`
(
    `id`                bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `school_id`         bigint(20)                                              NOT NULL COMMENT '学校编号',
    `student_no`        varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '学号',
    `student_name`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '学生姓名',
    `id_number`         varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '学生身份证号',
    `email`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '申诉邮箱，用于接收申诉处理结果',
    `id_path`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '上传的身份证照片保存路径',
    `student_card_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '上传的学生证照片保存路径',
    `sort`              smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`       datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`       datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`         binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_school` (`school_id`) USING BTREE,
    CONSTRAINT `appeal_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `sys_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户申诉'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for logic_class
-- ----------------------------
DROP TABLE IF EXISTS `logic_class`;
CREATE TABLE `logic_class`
(
    `id`           bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `type`         binary(1)                                               NULL     DEFAULT NULL COMMENT '0：物理班级(class_id值为实体班级id)，1：学生个体(student_id为学生实体id)',
    `school_id`    bigint(20)                                              NOT NULL COMMENT '学校编号',
    `college_id`   bigint(20)                                              NULL     DEFAULT NULL COMMENT '学院编号',
    `dep_id`       bigint(20)                                              NULL     DEFAULT NULL COMMENT '系部编号',
    `specialty_id` bigint(20)                                              NULL     DEFAULT NULL COMMENT '专业编号',
    `class_id`     bigint(20)                                              NULL     DEFAULT NULL COMMENT '实体班级id，type为0值，本字段值才有效',
    `student_id`   bigint(20)                                              NULL     DEFAULT NULL COMMENT '学生实体id，type为1值，本字段值才有效',
    `teacher_id`   bigint(20)                                              NULL     DEFAULT NULL COMMENT '教师编号',
    `semester_id`  bigint(20)                                              NULL     DEFAULT NULL COMMENT '学期编号',
    `course_id`    bigint(20)                                              NULL     DEFAULT NULL COMMENT '课程编号',
    `period`       smallint(6)                                             NULL     DEFAULT NULL COMMENT '学时',
    `credit`       float                                                   NULL     DEFAULT NULL COMMENT '学分',
    `course_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '课程性质',
    `sort`         smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`  datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`  datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`    binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_school` (`school_id`) USING BTREE,
    CONSTRAINT `logic_class_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `sys_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '逻辑班级'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`
(
    `id`          bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `school_id`   bigint(20)                                              NOT NULL COMMENT '学校编号',
    `start_date`  date                                                    NULL     DEFAULT NULL COMMENT '起始日期',
    `end_date`    date                                                    NULL     DEFAULT NULL COMMENT '结束日期',
    `sort`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time` datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`   binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_school` (`school_id`) USING BTREE,
    CONSTRAINT `semester_school_ibfk` FOREIGN KEY (`school_id`) REFERENCES `sys_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '学期'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`                   bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `user_id`              bigint(20)                                               NULL     DEFAULT NULL COMMENT '用户编号',
    `school_id`            bigint(20)                                               NULL     DEFAULT NULL COMMENT '学校编号',
    `college_id`           bigint(20)                                               NULL     DEFAULT NULL COMMENT '学院编号',
    `dep_id`               bigint(20)                                               NULL     DEFAULT NULL COMMENT '系部编号',
    `specialty_id`         bigint(20)                                               NULL     DEFAULT NULL COMMENT '专业编号',
    `class_id`             bigint(20)                                               NULL     DEFAULT NULL COMMENT '班级编号',
    `no`                   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '学号',
    `gender`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '性别',
    `id_number`            varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '身份证号码',
    `birthday`             date                                                     NULL     DEFAULT NULL COMMENT '出生日期',
    `enter_date`           date                                                     NULL     DEFAULT NULL COMMENT '入校时间',
    `academic`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历',
    `graduation_date`      date                                                     NULL     DEFAULT NULL COMMENT '最后学历毕业时间',
    `graduate_institution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历毕业院校',
    `original_major`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历所学专业（若最后学历是高中，则不需要填写\r\n若最后学历是大专，则需要填写）',
    `resume`               varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '个人简历',
    `sort`                 smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`          datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`          datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`            binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '学生信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`                   bigint(20)                                               NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称',
    `spell`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '名称的全拼',
    `user_id`              bigint(20)                                               NULL     DEFAULT NULL COMMENT '用户编号',
    `school_id`            bigint(20)                                               NULL     DEFAULT NULL COMMENT '学校编号',
    `college_id`           bigint(20)                                               NULL     DEFAULT NULL COMMENT '学院编号',
    `dep_id`               bigint(20)                                               NULL     DEFAULT NULL COMMENT '系部编号',
    `gender`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '性别',
    `birthday`             date                                                     NULL     DEFAULT NULL COMMENT '出生日期',
    `nation`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '民族',
    `degree`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '学位',
    `academic`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历',
    `graduation_date`      date                                                     NULL     DEFAULT NULL COMMENT '最后学历毕业时间',
    `major`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历所学专业',
    `graduate_institution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '最后学历毕业院校',
    `major_research`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '主要研究方向',
    `resume`               varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '个人简历',
    `work_date`            date                                                     NULL     DEFAULT NULL COMMENT '参加工作时间',
    `prof_title`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '职称',
    `prof_title_ass_date`  date                                                     NULL     DEFAULT NULL COMMENT '职称评定时间',
    `is_academic_leader`   binary(1)                                                NULL     DEFAULT NULL COMMENT '是否学术学科带头人',
    `subject_category`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '所属学科门类',
    `id_number`            varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '身份证号码',
    `sort`                 smallint(6)                                              NULL     DEFAULT NULL COMMENT '排序',
    `create_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`          datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`          datetime(0)                                              NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`            binary(1)                                                NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '教师信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_connection
-- ----------------------------
DROP TABLE IF EXISTS `user_connection`;
CREATE TABLE `user_connection`
(
    `userId`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `providerId`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `providerUserId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `rank`           int(11)                                                       NOT NULL,
    `displayName`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `profileUrl`     varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `imageUrl`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `accessToken`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `secret`         varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `refreshToken`   varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `expireTime`     bigint(20)                                                    NULL DEFAULT NULL,
    PRIMARY KEY (`userId`, `providerId`, `providerUserId`) USING BTREE,
    UNIQUE INDEX `UserConnectionRank` (`userId`, `providerId`, `rank`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for course_timetable_location
-- ----------------------------
DROP TABLE IF EXISTS `course_timetable_location`;
CREATE TABLE `course_timetable_location`
(
    `id`             bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称',
    `spell`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '名称的全拼',
    `logic_class_id` bigint(20)                                              NOT NULL COMMENT '逻辑班级编号',
    `weeks`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '以逗号作为分隔符的各个周序号，例如：1,3,5,7代表第1周、第3周、第5周和第7周上课',
    `location`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '上课地点',
    `weekday`        smallint(6)                                             NULL     DEFAULT NULL COMMENT '星期几，1：星期一，2：星期二，3：星期三，4：星期四，5：星期五，6：星期六，7：星期天',
    `class_section`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '上课是第几节，例如：第1节和第2节上课记录为1~2，第1节至第3节上课记录为1~2~3。如果同一天有多个分开的时间段，则以分号作为分隔符。例如：1~2;7~8',
    `sort`           smallint(6)                                             NULL     DEFAULT NULL COMMENT '排序',
    `create_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '创建用户名称',
    `create_time`    datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
    `modify_user`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '末次更新用户名称',
    `modify_time`    datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '末次更新时间',
    `remark`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `is_enable`      binary(1)                                               NOT NULL DEFAULT 1 COMMENT '是否可用，1：可用，0：不可用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_school` (`logic_class_id`) USING BTREE,
    CONSTRAINT `course_timetable_location_ibfk_1` FOREIGN KEY (`logic_class_id`) REFERENCES `logic_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '逻辑班级（课程）上课时间表及地点信息'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
