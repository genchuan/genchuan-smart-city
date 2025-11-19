package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifytrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytrack.InspectRectifyTrackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检整改进度跟踪视图 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRectifyTrackMapper extends BaseMapperX<InspectRectifyTrackDO> {

    default PageResult<InspectRectifyTrackDO> selectPage(InspectRectifyTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRectifyTrackDO>()
                .eqIfPresent(InspectRectifyTrackDO::getRectifyTaskId, reqVO.getRectifyTaskId())
                .eqIfPresent(InspectRectifyTrackDO::getRectifyTaskCode, reqVO.getRectifyTaskCode())
                .likeIfPresent(InspectRectifyTrackDO::getProblemName, reqVO.getProblemName())
                .likeIfPresent(InspectRectifyTrackDO::getDeptName, reqVO.getDeptName())
                .likeIfPresent(InspectRectifyTrackDO::getLeaderUserName, reqVO.getLeaderUserName())
                .eqIfPresent(InspectRectifyTrackDO::getRectifyStatus, reqVO.getRectifyStatus())
                .eqIfPresent(InspectRectifyTrackDO::getRectifyDeadlineHour, reqVO.getRectifyDeadlineHour())
                .betweenIfPresent(InspectRectifyTrackDO::getRectifyDeadlineTime, reqVO.getRectifyDeadlineTime())
                .eqIfPresent(InspectRectifyTrackDO::getRemainTimeMin, reqVO.getRemainTimeMin())
                .eqIfPresent(InspectRectifyTrackDO::getIsOverdue, reqVO.getIsOverdue())
                .betweenIfPresent(InspectRectifyTrackDO::getLatestProgressTime, reqVO.getLatestProgressTime())
                .eqIfPresent(InspectRectifyTrackDO::getLatestProgressDesc, reqVO.getLatestProgressDesc())
                .eqIfPresent(InspectRectifyTrackDO::getLatestProgressPhoto, reqVO.getLatestProgressPhoto())
                .eqIfPresent(InspectRectifyTrackDO::getRemindCount, reqVO.getRemindCount())
                .betweenIfPresent(InspectRectifyTrackDO::getLastRemindTime, reqVO.getLastRemindTime())
                .betweenIfPresent(InspectRectifyTrackDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(InspectRectifyTrackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRectifyTrackDO::getId));
    }

}