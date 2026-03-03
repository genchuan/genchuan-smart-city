package cn.iocoder.yudao.module.evaluate.service.plan;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.plan.PlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考察计划 Service 接口
 *
 * @author 芋道源码
 */
public interface PlanService {

    /**
     * 创建考察计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlan(@Valid PlanSaveReqVO createReqVO);

    /**
     * 更新考察计划
     *
     * @param updateReqVO 更新信息
     */
    void updatePlan(@Valid PlanSaveReqVO updateReqVO);

    /**
     * 删除考察计划
     *
     * @param id 编号
     */
    void deletePlan(Long id);

    /**
    * 批量删除考察计划
    *
    * @param ids 编号
    */
    void deletePlanListByIds(List<Long> ids);

    /**
     * 获得考察计划
     *
     * @param id 编号
     * @return 考察计划
     */
    PlanDO getPlan(Long id);

    /**
     * 获得考察计划分页
     *
     * @param pageReqVO 分页查询
     * @return 考察计划分页
     */
    PageResult<PlanDO> getPlanPage(PlanPageReqVO pageReqVO);

}