
-- 修改 dorm_compare 表的 rank 字段名称为 rank_no 字段名称
ALTER TABLE `dorm_compare` CHANGE COLUMN `rank` `rank_no` INT DEFAULT NULL COMMENT '排名';;

-- 修改 assess_mgmt 表的 rank 字段名称为 rank_no 字段名称
ALTER TABLE `assess_mgmt` CHANGE COLUMN `rank` `rank_no` INT DEFAULT NULL COMMENT '排名';;

-- 修改 compare_mgmt 表的 rank 字段名称为 rank_no 字段名称
ALTER TABLE `compare_mgmt` CHANGE COLUMN `rank` `rank_no` INT DEFAULT NULL COMMENT '排名';;

