package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectspecplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectspecplan.vo.InspectSpecPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectspecplan.vo.InspectSpecPlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectspecplan.InspectSpecPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 专项巡查计划信息 Service 接口
 *
 * @author zcq
 */
public interface InspectSpecPlanService {

    /**
     * 创建专项巡查计划信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSpecPlan(@Valid InspectSpecPlanSaveReqVO createReqVO);

    /**
     * 更新专项巡查计划信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSpecPlan(@Valid InspectSpecPlanSaveReqVO updateReqVO);

    /**
     * 删除专项巡查计划信息
     *
     * @param id 编号
     */
    void deleteInspectSpecPlan(Long id);

    /**
     * 获得专项巡查计划信息
     *
     * @param id 编号
     * @return 专项巡查计划信息
     */
    InspectSpecPlanDO getInspectSpecPlan(Long id);

    /**
     * 获得专项巡查计划信息分页
     *
     * @param pageReqVO 分页查询
     * @return 专项巡查计划信息分页
     */
    PageResult<InspectSpecPlanDO> getInspectSpecPlanPage(InspectSpecPlanPageReqVO pageReqVO);

}