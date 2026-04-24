
UPDATE `bed_mgmt` SET `status` = 'unallocated' WHERE `status`  = '未分配';

UPDATE `bed_mgmt` SET `status` = 'allocated' WHERE `status`  = '已分配';
