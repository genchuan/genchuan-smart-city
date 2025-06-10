package cn.iocoder.yudao.module.smartcity.service.maintenanceplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.MaintenancePlanPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.MaintenancePlanSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplan.MaintenancePlanDO;
import jakarta.validation.Valid;

/**
 * 养护计划 Service 接口
 *
 * @author zcq
 */
public interface MaintenancePlanService {

    /**
     * 创建养护计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintenancePlan(@Valid MaintenancePlanSaveReqVO createReqVO);

    /**
     * 更新养护计划
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintenancePlan(@Valid MaintenancePlanSaveReqVO updateReqVO);

    /**
     * 删除养护计划
     *
     * @param id 编号
     */
    void deleteMaintenancePlan(Long id);

    /**
     * 获得养护计划
     *
     * @param id 编号
     * @return 养护计划
     */
    MaintenancePlanDO getMaintenancePlan(Long id);

    /**
     * 获得养护计划分页
     *
     * @param pageReqVO 分页查询
     * @return 养护计划分页
     */
    PageResult<MaintenancePlanDO> getMaintenancePlanPage(MaintenancePlanPageReqVO pageReqVO);

}