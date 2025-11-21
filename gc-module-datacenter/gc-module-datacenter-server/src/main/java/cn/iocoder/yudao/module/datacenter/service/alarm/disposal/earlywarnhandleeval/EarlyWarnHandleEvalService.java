package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandleeval;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警处置评估 Service 接口
 *
 * @author zhucongquan
 */
public interface EarlyWarnHandleEvalService {

    /**
     * 创建预警告警处置评估
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnHandleEval(@Valid EarlyWarnHandleEvalSaveReqVO createReqVO);

    /**
     * 更新预警告警处置评估
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnHandleEval(@Valid EarlyWarnHandleEvalSaveReqVO updateReqVO);

    /**
     * 删除预警告警处置评估
     *
     * @param id 编号
     */
    void deleteEarlyWarnHandleEval(Long id);

    /**
     * 获得预警告警处置评估
     *
     * @param id 编号
     * @return 预警告警处置评估
     */
    EarlyWarnHandleEvalDO getEarlyWarnHandleEval(Long id);

    /**
     * 获得预警告警处置评估分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警处置评估分页
     */
    PageResult<EarlyWarnHandleEvalDO> getEarlyWarnHandleEvalPage(EarlyWarnHandleEvalPageReqVO pageReqVO);

}