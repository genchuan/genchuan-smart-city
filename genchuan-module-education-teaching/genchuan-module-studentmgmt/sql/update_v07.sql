-- ----------------------------
-- 德育资源状态 - 字典类型
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES (2000, '德育资源状态', 'moral_resource_status', 0, '德育资源的上架状态', 'admin', SYSDATE, '', NULL, '0', NULL);

-- ----------------------------
-- 德育资源状态 - 字典数据
-- ----------------------------
INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (2000, 1, '未上架', 'disable', 'moral_resource_status', 0, 'danger', '', '德育资源未上架状态', 'admin', SYSDATE, '', NULL, '0');

INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (2001, 2, '已上架', 'enable', 'moral_resource_status', 0, 'success', '', '德育资源已上架状态', 'admin', SYSDATE, '', NULL, '0');
