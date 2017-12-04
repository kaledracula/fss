
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for `code_sequence`
-- ----------------------------
create table code_sequence (
seq_name        VARCHAR(50) NOT NULL, -- 序列名称
current_val     INT         NOT NULL, -- 当前值
increment_val   INT         NOT NULL    DEFAULT 1, -- 步长(跨度)
PRIMARY KEY (seq_name)   );
-- ----------------------------
-- Function structure for `currval`
-- ----------------------------
create function currval(v_seq_name VARCHAR(50))
returns integer
begin
    declare value integer;
    set value = 0;
    select current_val into value  from tb_sequence where seq_name = v_seq_name;
   return value;
end;
-- ----------------------------
-- Function structure for `nextval`
-- ----------------------------
create function nextval(v_seq_name VARCHAR(50))
    returns integer
begin
    update tb_sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;
    return currval(v_seq_name);
end;
-- ----------------------------
-- Trigger  structure for `collector`
-- ----------------------------
CREATE TRIGGER `Tri_collector` BEFORE INSERT ON `collector` FOR EACH ROW BEGIN
set code = nextval('seq_collector');
-- ----------------------------
-- Trigger  structure for `driver`
-- ----------------------------
CREATE TRIGGER `Tri_driver` BEFORE INSERT ON `driver` FOR EACH ROW BEGIN
set code = nextval('seq_driver');
-- ----------------------------
-- Trigger  structure for `salesman`
-- ----------------------------
CREATE TRIGGER `Tri_salesman` BEFORE INSERT ON `salesman` FOR EACH ROW BEGIN
set code = nextval('seq_salesman');

SET FOREIGN_KEY_CHECKS=1;
