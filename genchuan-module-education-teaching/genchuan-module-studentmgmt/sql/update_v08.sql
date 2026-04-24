
delete FROM system_dict_data WHERE dict_type = 'target_mgmt_score_type';


INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
    (1, '定量', 'quantitative', 'target_mgmt_score_type', 0, 'primary', '', '定量评分', 'admin', NOW(), 'admin', NOW(), b'0'),
    (2, '定性', 'qualitative', 'target_mgmt_score_type', 0, 'success', '', '定性评分', 'admin', NOW(), 'admin', NOW(), b'0');


-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('调班状态', 'duty_mgmt_transfer_status', 0, '值班管理调班申请状态', 'admin', NOW(), 'admin', NOW(), 0, 1);


-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '无', 'none', 'duty_mgmt_transfer_status', 0, 'info', '', '未申请调班', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '待审批', 'pending', 'duty_mgmt_transfer_status', 0, 'warning', '', '调班申请待审批', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (3, '已通过', 'approved', 'duty_mgmt_transfer_status', 0, 'success', '', '调班申请已通过', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (4, '已驳回', 'rejected', 'duty_mgmt_transfer_status', 0, 'danger', '', '调班申请已驳回', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('出车状态', 'duty_mgmt_car_status', 0, '值班管理出车申请状态', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '无   ', 'none', 'duty_mgmt_car_status', 0, 'info', '', '未申请出车', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '待审批', 'pending', 'duty_mgmt_car_status', 0, 'warning', '', '出车申请待审批', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (3, '已通过', 'approved', 'duty_mgmt_car_status', 0, 'success', '', '出车申请已通过', 'admin', NOW(), 'admin', NOW(), 0, 1);



-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('调班状态', 'duty_mgmt_transfer_status', 0, '值班管理调班申请状态', 'admin', NOW(), 'admin', NOW(), 0, 1);


-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '无', 'none', 'duty_mgmt_transfer_status', 0, 'info', '', '未申请调班', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '待审批', 'pending', 'duty_mgmt_transfer_status', 0, 'warning', '', '调班申请待审批', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (3, '已通过', 'approved', 'duty_mgmt_transfer_status', 0, 'success', '', '调班申请已通过', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (4, '已驳回', 'rejected', 'duty_mgmt_transfer_status', 0, 'danger', '', '调班申请已驳回', 'admin', NOW(), 'admin', NOW(), 0, 1);




-- 插入字典类型
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES ('评比状态', 'compare_mgmt_status', 0, '评比管理状态', 'admin', NOW(), 'admin', NOW(), 0, 1);

-- 插入字典数据
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, '打分中', 'scoring', 'compare_mgmt_status', 0, 'warning', '', '评比正在打分中', 'admin', NOW(), 'admin', NOW(), 0, 1),
    (2, '已汇总', 'summarized', 'compare_mgmt_status', 0, 'success', '', '评比分数已汇总', 'admin', NOW(), 'admin', NOW(), 0, 1);

