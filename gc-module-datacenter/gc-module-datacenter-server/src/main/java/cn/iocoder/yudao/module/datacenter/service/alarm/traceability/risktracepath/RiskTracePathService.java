package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risktracepath;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risktracepath.RiskTracePathDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 风险溯源路径 Service 接口
 *
 * @author zcq
 */
public interface RiskTracePathService {

    /**
     * 创建风险溯源路径
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRiskTracePath(@Valid RiskTracePathSaveReqVO createReqVO);

    /**
     * 更新风险溯源路径
     *
     * @param updateReqVO 更新信息
     */
    void updateRiskTracePath(@Valid RiskTracePathSaveReqVO updateReqVO);

    /**
     * 删除风险溯源路径
     *
     * @param id 编号
     */
    void deleteRiskTracePath(Long id);

    /**
     * 获得风险溯源路径
     *
     * @param id 编号
     * @return 风险溯源路径
     */
    RiskTracePathDO getRiskTracePath(Long id);

    /**
     * 获得风险溯源路径分页
     *
     * @param pageReqVO 分页查询
     * @return 风险溯源路径分页
     */
    PageResult<RiskTracePathDO> getRiskTracePathPage(RiskTracePathPageReqVO pageReqVO);

}