package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.taskoverallmng;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.taskoverallmng.TaskOverallMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务统筹管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TaskOverallMngMapper extends BaseMapperX<TaskOverallMngDO> {

    default PageResult<TaskOverallMngDO> selectPage(TaskOverallMngPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskOverallMngDO>()
                .eqIfPresent(TaskOverallMngDO::getOverallId, reqVO.getOverallId())
                .eqIfPresent(TaskOverallMngDO::getTaskNo, reqVO.getTaskNo())
                .likeIfPresent(TaskOverallMngDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(TaskOverallMngDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskOverallMngDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(TaskOverallMngDO::getCommandId, reqVO.getCommandId())
                .eqIfPresent(TaskOverallMngDO::getCrossId, reqVO.getCrossId())
                .eqIfPresent(TaskOverallMngDO::getTaskStatus, reqVO.getTaskStatus())
                .eqIfPresent(TaskOverallMngDO::getPriority, reqVO.getPriority())
                .eqIfPresent(TaskOverallMngDO::getChargerId, reqVO.getChargerId())
                .likeIfPresent(TaskOverallMngDO::getChargerName, reqVO.getChargerName())
                .betweenIfPresent(TaskOverallMngDO::getPlanCompleteTime, reqVO.getPlanCompleteTime())
                .betweenIfPresent(TaskOverallMngDO::getActualCompleteTime, reqVO.getActualCompleteTime())
                .eqIfPresent(TaskOverallMngDO::getDelayEndure, reqVO.getDelayEndure())
                .betweenIfPresent(TaskOverallMngDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(TaskOverallMngDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(TaskOverallMngDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(TaskOverallMngDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskOverallMngDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskOverallMngDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(TaskOverallMngDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(TaskOverallMngDO::getId));
    }

}