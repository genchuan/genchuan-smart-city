package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnriskpred;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnriskpred.EarlyWarnRiskPredDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警风险趋势预测 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnRiskPredService {

    /**
     * 创建预警告警风险趋势预测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnRiskPred(@Valid EarlyWarnRiskPredSaveReqVO createReqVO);

    /**
     * 更新预警告警风险趋势预测
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnRiskPred(@Valid EarlyWarnRiskPredSaveReqVO updateReqVO);

    /**
     * 删除预警告警风险趋势预测
     *
     * @param id 编号
     */
    void deleteEarlyWarnRiskPred(Long id);

    /**
     * 获得预警告警风险趋势预测
     *
     * @param id 编号
     * @return 预警告警风险趋势预测
     */
    EarlyWarnRiskPredDO getEarlyWarnRiskPred(Long id);

    /**
     * 获得预警告警风险趋势预测分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警风险趋势预测分页
     */
    PageResult<EarlyWarnRiskPredDO> getEarlyWarnRiskPredPage(EarlyWarnRiskPredPageReqVO pageReqVO);

}