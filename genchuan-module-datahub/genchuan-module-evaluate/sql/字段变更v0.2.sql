ALTER TABLE eval_subject MODIFY COLUMN change_log TEXT COMMENT '变更日志';
ALTER TABLE eval_object MODIFY COLUMN change_log TEXT COMMENT '变更日志';
-- 为eval_rule_category添加字段
ALTER TABLE eval_rule_category 
ADD COLUMN veto_item_id CHAR(36) COMMENT '否决项ID(关联eval_veto_item.veto_item_id)' AFTER system_id;
-- 为eval_index_system添加字段
ALTER TABLE eval_index_system
ADD COLUMN index_type_id CHAR(36) COMMENT '指标类型ID(关联sys_index_type.type_id)' AFTER object_type_id;