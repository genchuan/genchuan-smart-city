package cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risktracepath;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risktracepath.RiskTracePathDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.risktracepath.RiskTracePathMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 风险溯源路径 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class RiskTracePathServiceImpl implements RiskTracePathService {

    @Resource
    private RiskTracePathMapper riskTracePathMapper;

    @Override
    public Long createRiskTracePath(RiskTracePathSaveReqVO createReqVO) {
        // 插入
        RiskTracePathDO riskTracePath = BeanUtils.toBean(createReqVO, RiskTracePathDO.class);
        riskTracePathMapper.insert(riskTracePath);
        // 返回
        return riskTracePath.getId();
    }

    @Override
    public void updateRiskTracePath(RiskTracePathSaveReqVO updateReqVO) {
        // 校验存在
        validateRiskTracePathExists(updateReqVO.getId());
        // 更新
        RiskTracePathDO updateObj = BeanUtils.toBean(updateReqVO, RiskTracePathDO.class);
        riskTracePathMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiskTracePath(Long id) {
        // 校验存在
        validateRiskTracePathExists(id);
        // 删除
        riskTracePathMapper.deleteById(id);
    }

    private void validateRiskTracePathExists(Long id) {
        if (riskTracePathMapper.selectById(id) == null) {
            throw exception(RISK_TRACE_PATH_NOT_EXISTS);
        }
    }

    @Override
    public RiskTracePathDO getRiskTracePath(Long id) {
        return riskTracePathMapper.selectById(id);
    }

    @Override
    public PageResult<RiskTracePathDO> getRiskTracePathPage(RiskTracePathPageReqVO pageReqVO) {
        return riskTracePathMapper.selectPage(pageReqVO);
    }

}