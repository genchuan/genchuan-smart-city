package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatchcommand;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchcommand.DispatchCommandDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度指令 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DispatchCommandMapper extends BaseMapperX<DispatchCommandDO> {

    default PageResult<DispatchCommandDO> selectPage(DispatchCommandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DispatchCommandDO>()
                .eqIfPresent(DispatchCommandDO::getCommandId, reqVO.getCommandId())
                .eqIfPresent(DispatchCommandDO::getCommandNo, reqVO.getCommandNo())
                .eqIfPresent(DispatchCommandDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(DispatchCommandDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(DispatchCommandDO::getExecUnitId, reqVO.getExecUnitId())
                .likeIfPresent(DispatchCommandDO::getExecUnitName, reqVO.getExecUnitName())
                .eqIfPresent(DispatchCommandDO::getTaskContent, reqVO.getTaskContent())
                .eqIfPresent(DispatchCommandDO::getExecRequirement, reqVO.getExecRequirement())
                .eqIfPresent(DispatchCommandDO::getCommandStatus, reqVO.getCommandStatus())
                .eqIfPresent(DispatchCommandDO::getIssueUserId, reqVO.getIssueUserId())
                .likeIfPresent(DispatchCommandDO::getIssueUserName, reqVO.getIssueUserName())
                .betweenIfPresent(DispatchCommandDO::getIssueTime, reqVO.getIssueTime())
                .eqIfPresent(DispatchCommandDO::getReceiveUserId, reqVO.getReceiveUserId())
                .likeIfPresent(DispatchCommandDO::getReceiveUserName, reqVO.getReceiveUserName())
                .betweenIfPresent(DispatchCommandDO::getReceiveTime, reqVO.getReceiveTime())
                .eqIfPresent(DispatchCommandDO::getExecFeedback, reqVO.getExecFeedback())
                .betweenIfPresent(DispatchCommandDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(DispatchCommandDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DispatchCommandDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DispatchCommandDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DispatchCommandDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(DispatchCommandDO::getId));
    }

}