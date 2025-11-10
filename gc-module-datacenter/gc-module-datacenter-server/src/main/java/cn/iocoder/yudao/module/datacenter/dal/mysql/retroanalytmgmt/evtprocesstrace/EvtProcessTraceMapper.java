package cn.iocoder.yudao.module.datacenter.dal.mysql.retroanalytmgmt.evtprocesstrace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.evtprocesstrace.EvtProcessTraceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件过程回溯 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtProcessTraceMapper extends BaseMapperX<EvtProcessTraceDO> {

    default PageResult<EvtProcessTraceDO> selectPage(EvtProcessTracePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtProcessTraceDO>()
                .eqIfPresent(EvtProcessTraceDO::getTraceId, reqVO.getTraceId())
                .eqIfPresent(EvtProcessTraceDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(EvtProcessTraceDO::getEvtName, reqVO.getEvtName())
                .likeIfPresent(EvtProcessTraceDO::getEvtTypeName, reqVO.getEvtTypeName())
                .eqIfPresent(EvtProcessTraceDO::getRptLink, reqVO.getRptLink())
                .eqIfPresent(EvtProcessTraceDO::getAnalysisLink, reqVO.getAnalysisLink())
                .eqIfPresent(EvtProcessTraceDO::getAllocateLink, reqVO.getAllocateLink())
                .eqIfPresent(EvtProcessTraceDO::getWoLink, reqVO.getWoLink())
                .eqIfPresent(EvtProcessTraceDO::getDispatchLink, reqVO.getDispatchLink())
                .eqIfPresent(EvtProcessTraceDO::getTraceUserId, reqVO.getTraceUserId())
                .likeIfPresent(EvtProcessTraceDO::getTraceUserName, reqVO.getTraceUserName())
                .betweenIfPresent(EvtProcessTraceDO::getTraceTime, reqVO.getTraceTime())
                .eqIfPresent(EvtProcessTraceDO::getRptPath, reqVO.getRptPath())
                .eqIfPresent(EvtProcessTraceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvtProcessTraceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvtProcessTraceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvtProcessTraceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EvtProcessTraceDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(EvtProcessTraceDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(EvtProcessTraceDO::getId));
    }

}