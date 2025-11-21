package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risksourcearch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risksourcearch.RiskSourceArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 风险源档案 Service 接口
 *
 * @author zcq
 */
public interface RiskSourceArchService {

    /**
     * 创建风险源档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRiskSourceArch(@Valid RiskSourceArchSaveReqVO createReqVO);

    /**
     * 更新风险源档案
     *
     * @param updateReqVO 更新信息
     */
    void updateRiskSourceArch(@Valid RiskSourceArchSaveReqVO updateReqVO);

    /**
     * 删除风险源档案
     *
     * @param id 编号
     */
    void deleteRiskSourceArch(Long id);

    /**
     * 获得风险源档案
     *
     * @param id 编号
     * @return 风险源档案
     */
    RiskSourceArchDO getRiskSourceArch(Long id);

    /**
     * 获得风险源档案分页
     *
     * @param pageReqVO 分页查询
     * @return 风险源档案分页
     */
    PageResult<RiskSourceArchDO> getRiskSourceArchPage(RiskSourceArchPageReqVO pageReqVO);

}