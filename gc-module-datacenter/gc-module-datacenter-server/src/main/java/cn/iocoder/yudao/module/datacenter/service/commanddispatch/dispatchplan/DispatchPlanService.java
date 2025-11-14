package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchplan.DispatchPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 调度方案 Service 接口
 *
 * @author 亘川智城
 */
public interface DispatchPlanService {

    /**
     * 创建调度方案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDispatchPlan(@Valid DispatchPlanSaveReqVO createReqVO);

    /**
     * 更新调度方案
     *
     * @param updateReqVO 更新信息
     */
    void updateDispatchPlan(@Valid DispatchPlanSaveReqVO updateReqVO);

    /**
     * 删除调度方案
     *
     * @param id 编号
     */
    void deleteDispatchPlan(Long id);

    /**
     * 获得调度方案
     *
     * @param id 编号
     * @return 调度方案
     */
    DispatchPlanDO getDispatchPlan(Long id);

    /**
     * 获得调度方案分页
     *
     * @param pageReqVO 分页查询
     * @return 调度方案分页
     */
    PageResult<DispatchPlanDO> getDispatchPlanPage(DispatchPlanPageReqVO pageReqVO);

}