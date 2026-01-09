package cn.iocoder.yudao.module.smartcity.service.maintenancetasks;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancetasks.MaintenanceTasksDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 养护任务 Service 接口
 *
 * @author zcq
 */
public interface MaintenanceTasksService {

    /**
     * 创建养护任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintenanceTasks(@Valid MaintenanceTasksSaveReqVO createReqVO);

    /**
     * 更新养护任务
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintenanceTasks(@Valid MaintenanceTasksSaveReqVO updateReqVO);

    /**
     * 删除养护任务
     *
     * @param id 编号
     */
    void deleteMaintenanceTasks(Long id);

    /**
     * 获得养护任务
     *
     * @param id 编号
     * @return 养护任务
     */
    MaintenanceTasksDO getMaintenanceTasks(Long id);

    /**
     * 获得养护任务分页
     *
     * @param pageReqVO 分页查询
     * @return 养护任务分页
     */
    PageResult<MaintenanceTasksDO> getMaintenanceTasksPage(MaintenanceTasksPageReqVO pageReqVO);

}