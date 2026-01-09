package cn.iocoder.yudao.module.smartcity.service.patroltaskmanagement;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patroltaskmanagement.PatrolTaskManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡査任务管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PatrolTaskManagementService {

    /**
     * 创建巡査任务管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatrolTaskManagement(@Valid PatrolTaskManagementSaveReqVO createReqVO);

    /**
     * 更新巡査任务管理
     *
     * @param updateReqVO 更新信息
     */
    void updatePatrolTaskManagement(@Valid PatrolTaskManagementSaveReqVO updateReqVO);

    /**
     * 删除巡査任务管理
     *
     * @param id 编号
     */
    void deletePatrolTaskManagement(Long id);

    /**
     * 获得巡査任务管理
     *
     * @param id 编号
     * @return 巡査任务管理
     */
    PatrolTaskManagementDO getPatrolTaskManagement(Long id);

    /**
     * 获得巡査任务管理分页
     *
     * @param pageReqVO 分页查询
     * @return 巡査任务管理分页
     */
    PageResult<PatrolTaskManagementDO> getPatrolTaskManagementPage(PatrolTaskManagementPageReqVO pageReqVO);

}