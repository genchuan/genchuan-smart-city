package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.riskpropagatechain;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.riskpropagatechain.RiskPropagateChainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 风险传播链 Service 接口
 *
 * @author zcq
 */
public interface RiskPropagateChainService {

    /**
     * 创建风险传播链
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRiskPropagateChain(@Valid RiskPropagateChainSaveReqVO createReqVO);

    /**
     * 更新风险传播链
     *
     * @param updateReqVO 更新信息
     */
    void updateRiskPropagateChain(@Valid RiskPropagateChainSaveReqVO updateReqVO);

    /**
     * 删除风险传播链
     *
     * @param id 编号
     */
    void deleteRiskPropagateChain(Long id);

    /**
     * 获得风险传播链
     *
     * @param id 编号
     * @return 风险传播链
     */
    RiskPropagateChainDO getRiskPropagateChain(Long id);

    /**
     * 获得风险传播链分页
     *
     * @param pageReqVO 分页查询
     * @return 风险传播链分页
     */
    PageResult<RiskPropagateChainDO> getRiskPropagateChainPage(RiskPropagateChainPageReqVO pageReqVO);

}