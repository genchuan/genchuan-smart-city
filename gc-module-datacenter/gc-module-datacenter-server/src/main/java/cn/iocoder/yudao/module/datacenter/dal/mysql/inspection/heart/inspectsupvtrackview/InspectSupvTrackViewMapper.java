package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvtrackview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检督办进度跟踪视图 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSupvTrackViewMapper extends BaseMapperX<InspectSupvTrackViewDO> {

    default PageResult<InspectSupvTrackViewDO> selectPage(InspectSupvTrackViewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSupvTrackViewDO>()
                .eqIfPresent(InspectSupvTrackViewDO::getSupvTaskId, reqVO.getSupvTaskId())
                .eqIfPresent(InspectSupvTrackViewDO::getSupvTaskCode, reqVO.getSupvTaskCode())
                .likeIfPresent(InspectSupvTrackViewDO::getProblemName, reqVO.getProblemName())
                .likeIfPresent(InspectSupvTrackViewDO::getSupvUserName, reqVO.getSupvUserName())
                .likeIfPresent(InspectSupvTrackViewDO::getLeaderUserName, reqVO.getLeaderUserName())
                .eqIfPresent(InspectSupvTrackViewDO::getSupvStatus, reqVO.getSupvStatus())
                .betweenIfPresent(InspectSupvTrackViewDO::getSupvDeadlineTime, reqVO.getSupvDeadlineTime())
                .eqIfPresent(InspectSupvTrackViewDO::getIsOverdue, reqVO.getIsOverdue())
                .betweenIfPresent(InspectSupvTrackViewDO::getLatestSupvTime, reqVO.getLatestSupvTime())
                .eqIfPresent(InspectSupvTrackViewDO::getLatestSupvType, reqVO.getLatestSupvType())
                .eqIfPresent(InspectSupvTrackViewDO::getLatestSupvContent, reqVO.getLatestSupvContent())
                .eqIfPresent(InspectSupvTrackViewDO::getSupvOperCount, reqVO.getSupvOperCount())
                .eqIfPresent(InspectSupvTrackViewDO::getRectifyStatus, reqVO.getRectifyStatus())
                .betweenIfPresent(InspectSupvTrackViewDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSupvTrackViewDO::getId));
    }

}