package cn.iocoder.yudao.module.datacenter.service.decisionexectrack;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionexectrack.DecisionExecTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 决策执行跟踪 Service 接口
 *
 * @author 亘川智城
 */
public interface DecisionExecTrackService {

    /**
     * 创建决策执行跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDecisionExecTrack(@Valid DecisionExecTrackSaveReqVO createReqVO);

    /**
     * 更新决策执行跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateDecisionExecTrack(@Valid DecisionExecTrackSaveReqVO updateReqVO);

    /**
     * 删除决策执行跟踪
     *
     * @param id 编号
     */
    void deleteDecisionExecTrack(Long id);

    /**
     * 获得决策执行跟踪
     *
     * @param id 编号
     * @return 决策执行跟踪
     */
    DecisionExecTrackDO getDecisionExecTrack(Long id);

    /**
     * 获得决策执行跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 决策执行跟踪分页
     */
    PageResult<DecisionExecTrackDO> getDecisionExecTrackPage(DecisionExecTrackPageReqVO pageReqVO);

}