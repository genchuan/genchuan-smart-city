package cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectemerplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectemerplan.InspectEmerPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应急巡查计划信息 Service 接口
 *
 * @author zcq
 */
public interface InspectEmerPlanService {

    /**
     * 创建应急巡查计划信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectEmerPlan(@Valid InspectEmerPlanSaveReqVO createReqVO);

    /**
     * 更新应急巡查计划信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectEmerPlan(@Valid InspectEmerPlanSaveReqVO updateReqVO);

    /**
     * 删除应急巡查计划信息
     *
     * @param id 编号
     */
    void deleteInspectEmerPlan(Long id);

    /**
     * 获得应急巡查计划信息
     *
     * @param id 编号
     * @return 应急巡查计划信息
     */
    InspectEmerPlanDO getInspectEmerPlan(Long id);

    /**
     * 获得应急巡查计划信息分页
     *
     * @param pageReqVO 分页查询
     * @return 应急巡查计划信息分页
     */
    PageResult<InspectEmerPlanDO> getInspectEmerPlanPage(InspectEmerPlanPageReqVO pageReqVO);

}