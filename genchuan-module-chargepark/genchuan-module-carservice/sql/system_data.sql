-- =====================================================================
-- 车务服务 carservice 字典 + 菜单数据初始化脚本
-- 目标数据库：ruoyi-vue-pro
-- 模块：genchuan-module-chargepark/genchuan-module-carservice
--
-- 包含：
--   1. system_dict_type 字典类型（13 个）
--   2. system_dict_data 字典数据（35 条）
--   3. system_menu      菜单 + 按钮权限（1 顶级 + 7 子目录 + 11 页面 + 78 按钮）
--
-- ID 段位（避免与现有数据冲突）：
--   dict_type:  2101 - 2113
--   dict_data:  3201 - 3299
--   menu:       9001 - 9999
-- =====================================================================

USE `ruoyi-vue-pro`;

-- =====================================================================
-- 一、字典类型 system_dict_type
-- =====================================================================
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`) VALUES
(2101, '救援类型',         'rescue_info_rescue_type',         0, '车务服务-救援信息-救援类型',           'carservice'),
(2102, '救援状态',         'rescue_info_status',              0, '车务服务-救援信息-救援状态',           'carservice'),
(2103, '救援归档状态',     'rescue_info_archive_status',      0, '车务服务-救援信息-归档状态',           'carservice'),
(2104, '空位推送状态',     'space_push_status',               0, '车务服务-空位推送-推送状态',           'carservice'),
(2105, '空位推送结果',     'space_push_push_result',          0, '车务服务-空位推送-推送结果',           'carservice'),
(2106, '预约类型',         'reserve_list_reserve_type',       0, '车务服务-预约列表-预约类型',           'carservice'),
(2107, '预约状态',         'reserve_list_status',             0, '车务服务-预约列表-预约状态',           'carservice'),
(2108, '车位定位结果',     'space_location_location_result',  0, '车务服务-车位定位-定位结果',           'carservice'),
(2109, '意见建议状态',     'suggestion_status',               0, '车务服务-意见建议-处理状态',           'carservice'),
(2110, '用户申诉状态',     'user_appeal_status',              0, '车务服务-用户申诉-申诉状态',           'carservice'),
(2111, '纠纷调解状态',     'dispute_mediate_status',          0, '车务服务-纠纷调解-调解状态',           'carservice'),
(2112, '客服话术类型',     'wording_mgmt_type',               0, '车务服务-话术管理-话术类型',           'carservice'),
(2113, '客服话术状态',     'wording_mgmt_status',             0, '车务服务-话术管理-话术状态',           'carservice');

-- =====================================================================
-- 二、字典数据 system_dict_data
-- =====================================================================
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `creator`) VALUES
-- 救援类型 rescue_info_rescue_type
(3201, 1, '道路救援',     '道路救援',     'rescue_info_rescue_type',        0, 'primary', 'carservice'),
(3202, 2, '充电故障救援', '充电故障救援', 'rescue_info_rescue_type',        0, 'warning', 'carservice'),
(3203, 3, '停车故障救援', '停车故障救援', 'rescue_info_rescue_type',        0, 'info',    'carservice'),
-- 救援状态 rescue_info_status
(3204, 1, '待派发', '待派发', 'rescue_info_status',                        0, 'warning', 'carservice'),
(3205, 2, '待认领', '待认领', 'rescue_info_status',                        0, 'info',    'carservice'),
(3206, 3, '处理中', '处理中', 'rescue_info_status',                        0, 'primary', 'carservice'),
(3207, 4, '已完成', '已完成', 'rescue_info_status',                        0, 'success', 'carservice'),
-- 救援归档状态 rescue_info_archive_status
(3208, 1, '未归档', '未归档', 'rescue_info_archive_status',                0, 'warning', 'carservice'),
(3209, 2, '已归档', '已归档', 'rescue_info_archive_status',                0, 'success', 'carservice'),
-- 空位推送状态 space_push_status
(3210, 1, '待推送', '待推送', 'space_push_status',                         0, 'warning', 'carservice'),
(3211, 2, '已推送', '已推送', 'space_push_status',                         0, 'success', 'carservice'),
-- 空位推送结果 space_push_push_result
(3212, 1, '成功', '成功',     'space_push_push_result',                    0, 'success', 'carservice'),
(3213, 2, '失败', '失败',     'space_push_push_result',                    0, 'danger',  'carservice'),
-- 预约类型 reserve_list_reserve_type
(3214, 1, '停车预约', '停车预约', 'reserve_list_reserve_type',             0, 'primary', 'carservice'),
(3215, 2, '充电预约', '充电预约', 'reserve_list_reserve_type',             0, 'warning', 'carservice'),
-- 预约状态 reserve_list_status
(3216, 1, '待审核', '待审核', 'reserve_list_status',                       0, 'warning', 'carservice'),
(3217, 2, '已生效', '已生效', 'reserve_list_status',                       0, 'success', 'carservice'),
(3218, 3, '已完成', '已完成', 'reserve_list_status',                       0, 'info',    'carservice'),
(3219, 4, '已取消', '已取消', 'reserve_list_status',                       0, 'danger',  'carservice'),
-- 车位定位结果 space_location_location_result
(3220, 1, '成功', '成功', 'space_location_location_result',                0, 'success', 'carservice'),
(3221, 2, '失败', '失败', 'space_location_location_result',                0, 'danger',  'carservice'),
-- 意见建议处理状态 suggestion_status
(3222, 1, '待处理', '待处理', 'suggestion_status',                         0, 'warning', 'carservice'),
(3223, 2, '处理中', '处理中', 'suggestion_status',                         0, 'primary', 'carservice'),
(3224, 3, '已完成', '已完成', 'suggestion_status',                         0, 'success', 'carservice'),
-- 用户申诉状态 user_appeal_status
(3225, 1, '待审核', '待审核', 'user_appeal_status',                        0, 'warning', 'carservice'),
(3226, 2, '待处置', '待处置', 'user_appeal_status',                        0, 'primary', 'carservice'),
(3227, 3, '已完成', '已完成', 'user_appeal_status',                        0, 'success', 'carservice'),
-- 纠纷调解状态 dispute_mediate_status
(3228, 1, '待调解', '待调解', 'dispute_mediate_status',                    0, 'warning', 'carservice'),
(3229, 2, '调解中', '调解中', 'dispute_mediate_status',                    0, 'primary', 'carservice'),
(3230, 3, '已完成', '已完成', 'dispute_mediate_status',                    0, 'success', 'carservice'),
-- 客服话术类型 wording_mgmt_type
(3231, 1, '快捷回复', '快捷回复', 'wording_mgmt_type',                     0, 'primary', 'carservice'),
(3232, 2, '自动回复', '自动回复', 'wording_mgmt_type',                     0, 'success', 'carservice'),
(3233, 3, '投诉回复', '投诉回复', 'wording_mgmt_type',                     0, 'warning', 'carservice'),
-- 客服话术状态 wording_mgmt_status
(3234, 1, '未生效', '未生效', 'wording_mgmt_status',                       0, 'info',    'carservice'),
(3235, 2, '已生效', '已生效', 'wording_mgmt_status',                       0, 'success', 'carservice');

-- =====================================================================
-- 三、菜单 system_menu
-- 菜单类型：1=目录, 2=菜单（页面）, 3=按钮（权限标识）
-- 权限格式严格按客户文档 05 接口文档：carservice:{表名连字符}:{操作驼峰}
-- 例:carservice:rescue-info:batchDispatch
-- 共 48 个权限码,与文档 05 接口文档定义 1:1 对齐
-- =====================================================================

-- ---------- 顶级目录：车务服务 ----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9001, '车务服务', '', 1, 19, 0, '/carservice', 'ep:service', NULL, NULL, 0, 'carservice');

-- ---------- 一级子目录 ----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9100, '救援服务', '', 1, 1, 9001, 'rescue',         'ep:warning',          NULL, NULL, 0, 'carservice'),
(9200, '车辆引导', '', 1, 2, 9001, 'car-guide',      'ep:guide',            NULL, NULL, 0, 'carservice'),
(9300, '预约服务', '', 1, 3, 9001, 'reserve',        'ep:calendar',         NULL, NULL, 0, 'carservice'),
(9400, '反向寻车', '', 1, 4, 9001, 'find-car',       'ep:position',         NULL, NULL, 0, 'carservice'),
(9500, '投诉调解', '', 1, 5, 9001, 'complaint',      'ep:chat-line-square', NULL, NULL, 0, 'carservice'),
(9600, '客服配置', '', 1, 6, 9001, 'service-config', 'ep:setting',          NULL, NULL, 0, 'carservice'),
(9700, '决策分析', '', 1, 7, 9001, 'decision',       'ep:data-analysis',    NULL, NULL, 0, 'carservice');

-- ---------- 救援服务 → 救援信息(8 个权限码)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9101, '救援信息', '', 2, 1, 9100, 'rescue-info', 'ep:document',
 'genchuan/chargePark/carService/rescueService/rescueInfo/index', 'RescueInfo', 0, 'carservice'),
(9102, '救援信息查询',  'carservice:rescue-info:query',          3, 1, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9103, '派发救援',      'carservice:rescue-info:dispatch',       3, 2, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9104, '批量派发救援',  'carservice:rescue-info:batchDispatch',  3, 3, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9105, '认领救援',      'carservice:rescue-info:claim',          3, 4, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9106, '更新救援进度',  'carservice:rescue-info:updateProgress', 3, 5, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9107, '转派救援',      'carservice:rescue-info:transfer',       3, 6, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9108, '评价救援',      'carservice:rescue-info:evaluate',       3, 7, 9101, '', '', NULL, NULL, 0, 'carservice'),
(9109, '归档救援',      'carservice:rescue-info:archive',        3, 8, 9101, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 车辆引导 → 充停地图(3 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9210, '充停地图', '', 2, 1, 9200, 'charge-park-map', 'ep:map-location',
 'genchuan/chargePark/carService/carGuide/chargeParkMap/index', 'ChargeParkMap', 0, 'carservice'),
(9211, '充停地图查询', 'carservice:charge-park-map:query',    3, 1, 9210, '', '', NULL, NULL, 0, 'carservice'),
(9212, '充停地图导航', 'carservice:charge-park-map:navigate', 3, 2, 9210, '', '', NULL, NULL, 0, 'carservice'),
(9213, '充停地图预订', 'carservice:charge-park-map:reserve',  3, 3, 9210, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 车辆引导 → 周边场站(3 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9220, '周边场站', '', 2, 2, 9200, 'near-station', 'ep:office-building',
 'genchuan/chargePark/carService/carGuide/nearStation/index', 'NearStation', 0, 'carservice'),
(9221, '周边场站查询', 'carservice:near-station:query',    3, 1, 9220, '', '', NULL, NULL, 0, 'carservice'),
(9222, '周边场站导航', 'carservice:near-station:navigate', 3, 2, 9220, '', '', NULL, NULL, 0, 'carservice'),
(9223, '周边场站预订', 'carservice:near-station:reserve',  3, 3, 9220, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 车辆引导 → 空位推送(3 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9230, '空位推送', '', 2, 3, 9200, 'space-push', 'ep:bell',
 'genchuan/chargePark/carService/carGuide/spacePush/index', 'SpacePush', 0, 'carservice'),
(9231, '空位推送查询', 'carservice:space-push:query',     3, 1, 9230, '', '', NULL, NULL, 0, 'carservice'),
(9232, '空位推送',     'carservice:space-push:push',      3, 2, 9230, '', '', NULL, NULL, 0, 'carservice'),
(9233, '空位批量推送', 'carservice:space-push:batchPush', 3, 3, 9230, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 预约服务 → 预约列表(6 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9310, '预约列表', '', 2, 1, 9300, 'reserve-list', 'ep:list',
 'genchuan/chargePark/carService/reserveService/reserveList/index', 'ReserveList', 0, 'carservice'),
(9311, '预约列表查询', 'carservice:reserve-list:query',      3, 1, 9310, '', '', NULL, NULL, 0, 'carservice'),
(9312, '预约通过',     'carservice:reserve-list:approve',    3, 2, 9310, '', '', NULL, NULL, 0, 'carservice'),
(9313, '预约驳回',     'carservice:reserve-list:reject',     3, 3, 9310, '', '', NULL, NULL, 0, 'carservice'),
(9314, '预约批量审核', 'carservice:reserve-list:batchAudit', 3, 4, 9310, '', '', NULL, NULL, 0, 'carservice'),
(9315, '预约取消',     'carservice:reserve-list:cancel',     3, 5, 9310, '', '', NULL, NULL, 0, 'carservice'),
(9316, '预约评价',     'carservice:reserve-list:evaluate',   3, 6, 9310, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 反向寻车 → 车位定位(2 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9410, '车位定位', '', 2, 1, 9400, 'space-location', 'ep:aim',
 'genchuan/chargePark/carService/findCar/spaceLocation/index', 'SpaceLocation', 0, 'carservice'),
(9411, '车位定位查询', 'carservice:space-location:query',    3, 1, 9410, '', '', NULL, NULL, 0, 'carservice'),
(9412, '车位定位导航', 'carservice:space-location:navigate', 3, 2, 9410, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 反向寻车 → 路径规划(2 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9420, '路径规划', '', 2, 2, 9400, 'path-plan', 'ep:share',
 'genchuan/chargePark/carService/findCar/pathPlan/index', 'PathPlan', 0, 'carservice'),
(9421, '路径规划查询', 'carservice:path-plan:query',    3, 1, 9420, '', '', NULL, NULL, 0, 'carservice'),
(9422, '路径规划导航', 'carservice:path-plan:navigate', 3, 2, 9420, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 投诉调解 → 意见建议(4 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9510, '意见建议', '', 2, 1, 9500, 'suggestion', 'ep:edit-pen',
 'genchuan/chargePark/carService/complaintMediate/suggestion/index', 'Suggestion', 0, 'carservice'),
(9511, '意见建议查询', 'carservice:suggestion:query',          3, 1, 9510, '', '', NULL, NULL, 0, 'carservice'),
(9512, '意见处理',     'carservice:suggestion:handle',         3, 2, 9510, '', '', NULL, NULL, 0, 'carservice'),
(9513, '意见更新进度', 'carservice:suggestion:updateProgress', 3, 3, 9510, '', '', NULL, NULL, 0, 'carservice'),
(9514, '意见反馈',     'carservice:suggestion:feedback',       3, 4, 9510, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 投诉调解 → 用户申诉(6 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9520, '用户申诉', '', 2, 2, 9500, 'user-appeal', 'ep:warning-filled',
 'genchuan/chargePark/carService/complaintMediate/userAppeal/index', 'UserAppeal', 0, 'carservice'),
(9521, '用户申诉查询', 'carservice:user-appeal:query',      3, 1, 9520, '', '', NULL, NULL, 0, 'carservice'),
(9522, '申诉通过',     'carservice:user-appeal:approve',    3, 2, 9520, '', '', NULL, NULL, 0, 'carservice'),
(9523, '申诉驳回',     'carservice:user-appeal:reject',     3, 3, 9520, '', '', NULL, NULL, 0, 'carservice'),
(9524, '申诉批量审核', 'carservice:user-appeal:batchAudit', 3, 4, 9520, '', '', NULL, NULL, 0, 'carservice'),
(9525, '申诉执行',     'carservice:user-appeal:execute',    3, 5, 9520, '', '', NULL, NULL, 0, 'carservice'),
(9526, '申诉反馈',     'carservice:user-appeal:feedback',   3, 6, 9520, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 投诉调解 → 纠纷调解(4 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9530, '纠纷调解', '', 2, 3, 9500, 'dispute-mediate', 'ep:scale-to-original',
 'genchuan/chargePark/carService/complaintMediate/disputeMediate/index', 'DisputeMediate', 0, 'carservice'),
(9531, '纠纷调解查询', 'carservice:dispute-mediate:query',          3, 1, 9530, '', '', NULL, NULL, 0, 'carservice'),
(9532, '纠纷调解操作', 'carservice:dispute-mediate:mediate',        3, 2, 9530, '', '', NULL, NULL, 0, 'carservice'),
(9533, '纠纷更新进度', 'carservice:dispute-mediate:updateProgress', 3, 3, 9530, '', '', NULL, NULL, 0, 'carservice'),
(9534, '纠纷确认',     'carservice:dispute-mediate:confirm',        3, 4, 9530, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 客服配置 → 话术管理(6 个)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9610, '话术管理', '', 2, 1, 9600, 'wording-mgmt', 'ep:chat-dot-square',
 'genchuan/chargePark/carService/serviceConfig/wordingMgmt/index', 'WordingMgmt', 0, 'carservice'),
(9611, '话术管理查询', 'carservice:wording-mgmt:query',   3, 1, 9610, '', '', NULL, NULL, 0, 'carservice'),
(9612, '话术新增',     'carservice:wording-mgmt:create',  3, 2, 9610, '', '', NULL, NULL, 0, 'carservice'),
(9613, '话术编辑',     'carservice:wording-mgmt:update',  3, 3, 9610, '', '', NULL, NULL, 0, 'carservice'),
(9614, '话术保存',     'carservice:wording-mgmt:save',    3, 4, 9610, '', '', NULL, NULL, 0, 'carservice'),
(9615, '话术启用',     'carservice:wording-mgmt:enable',  3, 5, 9610, '', '', NULL, NULL, 0, 'carservice'),
(9616, '话术禁用',     'carservice:wording-mgmt:disable', 3, 6, 9610, '', '', NULL, NULL, 0, 'carservice');

-- ---------- 决策分析 → 服务运营报表(1 个,文档只定义 query)----------
INSERT INTO `system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`, `status`, `creator`) VALUES
(9710, '服务运营报表', '', 2, 1, 9700, 'service-op-report', 'ep:data-line',
 'genchuan/chargePark/carService/decision/serviceOpReport/index', 'ServiceOpReport', 0, 'carservice'),
(9711, '服务报表查询', 'carservice:service-op-report:query', 3, 1, 9710, '', '', NULL, NULL, 0, 'carservice');

-- =====================================================================
-- 四、角色-菜单关联 system_role_menu
-- 把车务服务的所有菜单（9001-9999）赋给超级管理员角色（role_id=1）
-- 多租户 tenant_id=1（默认租户「芋道源码」）
-- =====================================================================
INSERT INTO `system_role_menu` (`role_id`, `menu_id`, `tenant_id`, `creator`)
SELECT 1, id, 1, 'carservice'
FROM `system_menu`
WHERE id BETWEEN 9001 AND 9999
  AND NOT EXISTS (
      SELECT 1 FROM `system_role_menu` rm
      WHERE rm.role_id = 1 AND rm.menu_id = `system_menu`.id
  );

-- =====================================================================
-- 五、站内信模板 system_notify_template
-- =====================================================================
INSERT INTO `system_notify_template` (`name`, `code`, `nickname`, `content`, `type`, `params`, `status`, `creator`) VALUES
('车务-空位推送',     'carservice_space_push',     '车务系统', '您好，场站 {stationId} 有新的空位：{spaceInfo}',                  1, '["stationId","spaceInfo"]',         0, 'carservice'),
('车务-救援派发',     'carservice_rescue_dispatch','车务系统', '有一条新的救援任务派发给您，请尽快处理。位置：{location}',         1, '["location"]',                       0, 'carservice'),
('车务-救援转派',     'carservice_rescue_transfer','车务系统', '一条救援任务已转派给您，请尽快接手。位置：{location}',             1, '["location"]',                       0, 'carservice'),
('车务-预约审核通过', 'carservice_reserve_pass',   '车务系统', '您的预约（{reserveType}）已审核通过，预约时间：{reserveTime}',     1, '["reserveType","reserveTime"]',     0, 'carservice'),
('车务-预约审核驳回', 'carservice_reserve_reject', '车务系统', '您的预约（{reserveType}）被驳回，原因：{rejectReason}',            1, '["reserveType","rejectReason"]',    0, 'carservice'),
('车务-申诉审核通过', 'carservice_appeal_pass',    '车务系统', '您的申诉已通过审核，将进入处置流程',                                1, '[]',                                 0, 'carservice'),
('车务-申诉处置完成', 'carservice_appeal_done',    '车务系统', '您的申诉已处置完成。反馈：{feedbackContent}',                       1, '["feedbackContent"]',               0, 'carservice');
