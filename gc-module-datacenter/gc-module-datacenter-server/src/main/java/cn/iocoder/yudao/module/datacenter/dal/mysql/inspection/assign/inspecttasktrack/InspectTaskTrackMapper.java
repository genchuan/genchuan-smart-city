package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.assign.inspecttasktrack;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttasktrack.InspectTaskTrackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检任务跟踪 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectTaskTrackMapper extends BaseMapperX<InspectTaskTrackDO> {

    default PageResult<InspectTaskTrackDO> selectPage(InspectTaskTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTaskTrackDO>()
                .eqIfPresent(InspectTaskTrackDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectTaskTrackDO::getTaskCode, reqVO.getTaskCode())
                .likeIfPresent(InspectTaskTrackDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(InspectTaskTrackDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(InspectTaskTrackDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectTaskTrackDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectTaskTrackDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectTaskTrackDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectTaskTrackDO::getTaskStatus, reqVO.getTaskStatus())
                .betweenIfPresent(InspectTaskTrackDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(InspectTaskTrackDO::getCompleteTime, reqVO.getCompleteTime())
                .betweenIfPresent(InspectTaskTrackDO::getDeadlineTime, reqVO.getDeadlineTime())
                .eqIfPresent(InspectTaskTrackDO::getTotalCkItem, reqVO.getTotalCkItem())
                .eqIfPresent(InspectTaskTrackDO::getCompletedCkItem, reqVO.getCompletedCkItem())
                .eqIfPresent(InspectTaskTrackDO::getUploadPhotoCount, reqVO.getUploadPhotoCount())
                .betweenIfPresent(InspectTaskTrackDO::getIsOvertime, reqVO.getIsOvertime())
                .eqIfPresent(InspectTaskTrackDO::getRemindCount, reqVO.getRemindCount())
                .betweenIfPresent(InspectTaskTrackDO::getLastRemindTime, reqVO.getLastRemindTime())
                .betweenIfPresent(InspectTaskTrackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectTaskTrackDO::getId));
    }

}