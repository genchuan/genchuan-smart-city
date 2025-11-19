package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresulteval.InspectResultEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检结果评估 Service 接口
 *
 * @author zcq
 */
public interface InspectResultEvalService {

    /**
     * 创建巡查巡检结果评估
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectResultEval(@Valid InspectResultEvalSaveReqVO createReqVO);

    /**
     * 更新巡查巡检结果评估
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectResultEval(@Valid InspectResultEvalSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检结果评估
     *
     * @param id 编号
     */
    void deleteInspectResultEval(Long id);

    /**
     * 获得巡查巡检结果评估
     *
     * @param id 编号
     * @return 巡查巡检结果评估
     */
    InspectResultEvalDO getInspectResultEval(Long id);

    /**
     * 获得巡查巡检结果评估分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检结果评估分页
     */
    PageResult<InspectResultEvalDO> getInspectResultEvalPage(InspectResultEvalPageReqVO pageReqVO);

}