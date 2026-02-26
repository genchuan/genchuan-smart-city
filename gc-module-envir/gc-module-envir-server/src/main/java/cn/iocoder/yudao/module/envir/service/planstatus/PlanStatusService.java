package cn.iocoder.yudao.module.envir.service.planstatus;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.planstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.planstatus.PlanStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 计划状态字典 Service 接口
 *
 * @author 芋道源码
 */
public interface PlanStatusService {

    /**
     * 创建计划状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlanStatus(@Valid PlanStatusSaveReqVO createReqVO);

    /**
     * 更新计划状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updatePlanStatus(@Valid PlanStatusSaveReqVO updateReqVO);

    /**
     * 删除计划状态字典
     *
     * @param id 编号
     */
    void deletePlanStatus(Long id);

    /**
     * 获得计划状态字典
     *
     * @param id 编号
     * @return 计划状态字典
     */
    PlanStatusDO getPlanStatus(Long id);

    /**
     * 获得计划状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 计划状态字典分页
     */
    PageResult<PlanStatusDO> getPlanStatusPage(PlanStatusPageReqVO pageReqVO);

}