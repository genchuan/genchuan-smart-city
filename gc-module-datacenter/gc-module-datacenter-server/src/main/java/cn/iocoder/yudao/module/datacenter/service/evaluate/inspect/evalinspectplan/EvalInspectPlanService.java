package cn.iocoder.yudao.module.datacenter.service.evaluate.inspect.evalinspectplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo.EvalInspectPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo.EvalInspectPlanSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectplan.EvalInspectPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考察计划管理 Service 接口
 *
 * @author zcq
 */
public interface EvalInspectPlanService {

    /**
     * 创建考察计划管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalInspectPlan(@Valid EvalInspectPlanSaveReqVO createReqVO);

    /**
     * 更新考察计划管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalInspectPlan(@Valid EvalInspectPlanSaveReqVO updateReqVO);

    /**
     * 删除考察计划管理
     *
     * @param id 编号
     */
    void deleteEvalInspectPlan(Long id);

    /**
     * 获得考察计划管理
     *
     * @param id 编号
     * @return 考察计划管理
     */
    EvalInspectPlanDO getEvalInspectPlan(Long id);

    /**
     * 获得考察计划管理分页
     *
     * @param pageReqVO 分页查询
     * @return 考察计划管理分页
     */
    PageResult<EvalInspectPlanDO> getEvalInspectPlanPage(EvalInspectPlanPageReqVO pageReqVO);

}