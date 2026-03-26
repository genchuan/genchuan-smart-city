package cn.iocoder.yudao.module.waterdetection.service.equipmentmaintenance;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentmaintenance.EquipmentMaintenanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 设备保养计划管理 Service 接口
 *
 * @author zcq
 */
public interface EquipmentMaintenanceService {

    /**
     * 创建设备保养计划管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEquipmentMaintenance(@Valid EquipmentMaintenanceSaveReqVO createReqVO);

    /**
     * 更新设备保养计划管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEquipmentMaintenance(@Valid EquipmentMaintenanceSaveReqVO updateReqVO);

    /**
     * 删除设备保养计划管理
     *
     * @param id 编号
     */
    void deleteEquipmentMaintenance(Long id);

    /**
     * 获得设备保养计划管理
     *
     * @param id 编号
     * @return 设备保养计划管理
     */
    EquipmentMaintenanceDO getEquipmentMaintenance(Long id);

    /**
     * 获得设备保养计划管理分页
     *
     * @param pageReqVO 分页查询
     * @return 设备保养计划管理分页
     */
    PageResult<EquipmentMaintenanceDO> getEquipmentMaintenancePage(EquipmentMaintenancePageReqVO pageReqVO);

}