package cn.iocoder.yudao.module.envirhealth.service.vehicle.maintenancetype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.maintenancetype.MaintenanceTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.MaintenanceTypeDO;
import jakarta.validation.Valid;

/**
 * 维护类型字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface MaintenanceTypeService {

    /**
     * 创建维护类型字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintenanceType(@Valid MaintenanceTypeSaveReqVO createReqVO);

    /**
     * 更新维护类型字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintenanceType(@Valid MaintenanceTypeSaveReqVO updateReqVO);

    /**
     * 删除维护类型字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteMaintenanceType(Long id);

    /**
     * 获得维护类型字典表【通用复用】
     *
     * @param id 编号
     * @return 维护类型字典表【通用复用】
     */
    MaintenanceTypeDO getMaintenanceType(Long id);

    /**
     * 获得维护类型字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 维护类型字典表【通用复用】分页
     */
    PageResult<MaintenanceTypeDO> getMaintenanceTypePage(MaintenanceTypePageReqVO pageReqVO);

}