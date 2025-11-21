package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectdailyplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectdailyplan.InspectDailyPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 日常巡查计划信息 Service 接口
 *
 * @author zcq
 */
public interface InspectDailyPlanService {

    /**
     * 创建日常巡查计划信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectDailyPlan(@Valid InspectDailyPlanSaveReqVO createReqVO);

    /**
     * 更新日常巡查计划信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectDailyPlan(@Valid InspectDailyPlanSaveReqVO updateReqVO);

    /**
     * 删除日常巡查计划信息
     *
     * @param id 编号
     */
    void deleteInspectDailyPlan(Long id);

    /**
     * 获得日常巡查计划信息
     *
     * @param id 编号
     * @return 日常巡查计划信息
     */
    InspectDailyPlanDO getInspectDailyPlan(Long id);

    /**
     * 获得日常巡查计划信息分页
     *
     * @param pageReqVO 分页查询
     * @return 日常巡查计划信息分页
     */
    PageResult<InspectDailyPlanDO> getInspectDailyPlanPage(InspectDailyPlanPageReqVO pageReqVO);

}