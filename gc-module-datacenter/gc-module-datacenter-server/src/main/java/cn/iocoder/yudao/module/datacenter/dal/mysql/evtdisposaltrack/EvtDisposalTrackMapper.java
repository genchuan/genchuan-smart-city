package cn.iocoder.yudao.module.datacenter.dal.mysql.evtdisposaltrack;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtdisposaltrack.EvtDisposalTrackDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtdisposaltrack.vo.*;

/**
 * 事件处置跟踪 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtDisposalTrackMapper extends BaseMapperX<EvtDisposalTrackDO> {

    default PageResult<EvtDisposalTrackDO> selectPage(EvtDisposalTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtDisposalTrackDO>()
                .eqIfPresent(EvtDisposalTrackDO::getTrackId, reqVO.getTrackId())
                .eqIfPresent(EvtDisposalTrackDO::getWoId, reqVO.getWoId())
                .eqIfPresent(EvtDisposalTrackDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(EvtDisposalTrackDO::getCurrentNode, reqVO.getCurrentNode())
                .likeIfPresent(EvtDisposalTrackDO::getHandlerName, reqVO.getHandlerName())
                .betweenIfPresent(EvtDisposalTrackDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(EvtDisposalTrackDO::getEstCompleteTime, reqVO.getEstCompleteTime())
                .orderByDesc(EvtDisposalTrackDO::getId));
    }

}