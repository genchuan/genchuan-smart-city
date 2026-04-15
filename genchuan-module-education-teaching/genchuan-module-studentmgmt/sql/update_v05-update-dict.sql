
-- 添加荣誉状态审核不通过的状态
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(4, '不通过', '3', 'honor_mgmt_status', 0, 'danger', '', '审核不通过', 'admin', NOW(), 'admin', NOW(), b'0');
