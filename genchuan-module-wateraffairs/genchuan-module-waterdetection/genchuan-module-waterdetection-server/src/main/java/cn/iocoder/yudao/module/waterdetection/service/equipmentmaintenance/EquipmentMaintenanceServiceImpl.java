package cn.iocoder.yudao.module.waterdetection.service.equipmentmaintenance;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentmaintenance.EquipmentMaintenanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.equipmentmaintenance.EquipmentMaintenanceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 设备保养计划管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EquipmentMaintenanceServiceImpl implements EquipmentMaintenanceService {

    @Resource
    private EquipmentMaintenanceMapper equipmentMaintenanceMapper;

    @Override
    public Long createEquipmentMaintenance(EquipmentMaintenanceSaveReqVO createReqVO) {
        // 插入
        EquipmentMaintenanceDO equipmentMaintenance = BeanUtils.toBean(createReqVO, EquipmentMaintenanceDO.class);
        equipmentMaintenanceMapper.insert(equipmentMaintenance);
        // 返回
        return equipmentMaintenance.getId();
    }

    @Override
    public void updateEquipmentMaintenance(EquipmentMaintenanceSaveReqVO updateReqVO) {
        // 校验存在
        validateEquipmentMaintenanceExists(updateReqVO.getId());
        // 更新
        EquipmentMaintenanceDO updateObj = BeanUtils.toBean(updateReqVO, EquipmentMaintenanceDO.class);
        equipmentMaintenanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEquipmentMaintenance(Long id) {
        // 校验存在
        validateEquipmentMaintenanceExists(id);
        // 删除
        equipmentMaintenanceMapper.deleteById(id);
    }

    private void validateEquipmentMaintenanceExists(Long id) {
        if (equipmentMaintenanceMapper.selectById(id) == null) {
            throw exception(EQUIPMENT_MAINTENANCE_NOT_EXISTS);
        }
    }

    @Override
    public EquipmentMaintenanceDO getEquipmentMaintenance(Long id) {
        return equipmentMaintenanceMapper.selectById(id);
    }

    @Override
    public PageResult<EquipmentMaintenanceDO> getEquipmentMaintenancePage(EquipmentMaintenancePageReqVO pageReqVO) {
        return equipmentMaintenanceMapper.selectPage(pageReqVO);
    }

}