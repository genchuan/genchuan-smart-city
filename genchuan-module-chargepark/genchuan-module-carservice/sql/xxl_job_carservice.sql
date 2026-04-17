-- =====================================================================
-- 车务服务 XXL-Job 任务注册脚本
--
-- 适用：xxl-job-admin 自带的 xxl-job 数据库（独立 MySQL 实例）
-- 执行前请确认：
--   1. 已存在 xxl_job_group 中的车务执行器 (app_name='carservice-server')，
--      若不存在请先 INSERT job_group。
--   2. 7 个 handler 都在 carservice-server 应用启动后可见。
--
-- 包含 7 个调度任务：
--   carserviceDailyReportJob       0 5 0 * * ?     每日 00:05 日报
--   carserviceWeeklyReportJob      0 10 0 ? * 2    每周一 00:10 周报
--   carserviceMonthlyReportJob     0 15 0 1 * ?    每月 1 日 00:15 月报
--   carserviceQuarterlyReportJob   0 20 0 1 1,4,7,10 ?  季度报
--   carserviceSemiAnnualReportJob  0 25 0 1 1,7 ?  半年报
--   carserviceAnnualReportJob      0 30 0 1 1 ?    年报
--   carserviceCleanupJob           0 0 2 * * ?     每日 02:00 清理 3 年前数据
-- =====================================================================

-- 1. 注册执行器（如果还没有）
INSERT INTO `xxl_job_group` (`app_name`, `title`, `address_type`, `update_time`)
SELECT 'carservice-server', '车务服务', 0, NOW()
WHERE NOT EXISTS (
    SELECT 1 FROM `xxl_job_group` WHERE `app_name` = 'carservice-server'
);

-- 2. 注册 7 个调度任务（用 handler 名做去重，避免重复执行本脚本时插入重复任务）
SET @gid = (SELECT id FROM `xxl_job_group` WHERE `app_name` = 'carservice-server' LIMIT 1);

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-日报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 5 0 * * ?', 'DO_NOTHING',
       'FIRST', 'carserviceDailyReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动日报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceDailyReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-周报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 10 0 ? * 2', 'DO_NOTHING',
       'FIRST', 'carserviceWeeklyReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动周报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceWeeklyReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-月报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 15 0 1 * ?', 'DO_NOTHING',
       'FIRST', 'carserviceMonthlyReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动月报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceMonthlyReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-季报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 20 0 1 1,4,7,10 ?', 'DO_NOTHING',
       'FIRST', 'carserviceQuarterlyReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动季度报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceQuarterlyReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-半年报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 25 0 1 1,7 ?', 'DO_NOTHING',
       'FIRST', 'carserviceSemiAnnualReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动半年报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceSemiAnnualReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-年报', NOW(), NOW(), 'carservice', '',
       'CRON', '0 30 0 1 1 ?', 'DO_NOTHING',
       'FIRST', 'carserviceAnnualReportJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '车务自动年报', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceAnnualReportJob');

INSERT INTO `xxl_job_info`
(`job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`,
 `schedule_type`, `schedule_conf`, `misfire_strategy`,
 `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`,
 `executor_timeout`, `executor_fail_retry_count`,
 `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`)
SELECT @gid, '车务-数据保留清理(3年)', NOW(), NOW(), 'carservice', '',
       'CRON', '0 0 2 * * ?', 'DO_NOTHING',
       'FIRST', 'carserviceCleanupJob', '', 'SERIAL_EXECUTION',
       0, 0,
       'BEAN', '', '物理删除 11 张业务表 createTime < now()-3y 的记录', NOW(), '', 1
WHERE NOT EXISTS (SELECT 1 FROM `xxl_job_info` WHERE `executor_handler` = 'carserviceCleanupJob');
