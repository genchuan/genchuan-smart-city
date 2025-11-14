package cn.iocoder.yudao.module.datacenter.service.decisionexectrack;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionexectrack.DecisionExecTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.decisionexectrack.DecisionExecTrackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 决策执行跟踪 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DecisionExecTrackServiceImpl implements DecisionExecTrackService {

    @Resource
    private DecisionExecTrackMapper decisionExecTrackMapper;

    @Override
    public Long createDecisionExecTrack(DecisionExecTrackSaveReqVO createReqVO) {
        // 插入
        DecisionExecTrackDO decisionExecTrack = BeanUtils.toBean(createReqVO, DecisionExecTrackDO.class);
        decisionExecTrackMapper.insert(decisionExecTrack);
        // 返回
        return decisionExecTrack.getId();
    }

    @Override
    public void updateDecisionExecTrack(DecisionExecTrackSaveReqVO updateReqVO) {
        // 校验存在
        validateDecisionExecTrackExists(updateReqVO.getId());
        // 更新
        DecisionExecTrackDO updateObj = BeanUtils.toBean(updateReqVO, DecisionExecTrackDO.class);
        decisionExecTrackMapper.updateById(updateObj);
    }

    @Override
    public void deleteDecisionExecTrack(Long id) {
        // 校验存在
        validateDecisionExecTrackExists(id);
        // 删除
        decisionExecTrackMapper.deleteById(id);
    }

    private void validateDecisionExecTrackExists(Long id) {
        if (decisionExecTrackMapper.selectById(id) == null) {
            throw exception(DECISION_EXEC_TRACK_NOT_EXISTS);
        }
    }

    @Override
    public DecisionExecTrackDO getDecisionExecTrack(Long id) {
        return decisionExecTrackMapper.selectById(id);
    }

    @Override
    public PageResult<DecisionExecTrackDO> getDecisionExecTrackPage(DecisionExecTrackPageReqVO pageReqVO) {
        return decisionExecTrackMapper.selectPage(pageReqVO);
    }

}