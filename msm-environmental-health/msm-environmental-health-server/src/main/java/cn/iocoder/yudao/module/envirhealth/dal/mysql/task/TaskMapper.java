package cn.iocoder.yudao.module.envirhealth.dal.mysql.task;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithGarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicToiletDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 任务 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskMapper extends BaseMapperX<TaskDO> {

    default PageResult<TaskDO> selectPage(TaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .eqIfPresent(TaskDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TaskDO::getTaskTypeId, reqVO.getTaskTypeId())
                .eqIfPresent(TaskDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(TaskDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TaskDO::getInstitutionId, reqVO.getInstitutionId())
                .eqIfPresent(TaskDO::getStreetId, reqVO.getStreetId())
                .eqIfPresent(TaskDO::getParkId, reqVO.getParkId())
                .eqIfPresent(TaskDO::getVillageId, reqVO.getVillageId())
                .eqIfPresent(TaskDO::getMarketId, reqVO.getMarketId())
                .eqIfPresent(TaskDO::getRiverId, reqVO.getRiverId())
                .eqIfPresent(TaskDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(TaskDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(TaskDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(TaskDO::getProofUrl, reqVO.getProofUrl())
                .betweenIfPresent(TaskDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(TaskDO::getHandleDuration, reqVO.getHandleDuration())
                .eqIfPresent(TaskDO::getStatPeriod, reqVO.getStatPeriod())
                .eqIfPresent(TaskDO::getCleaningQualifiedRate, reqVO.getCleaningQualifiedRate())
                .eqIfPresent(TaskDO::getProblemCompleteRate, reqVO.getProblemCompleteRate())
                .eqIfPresent(TaskDO::getInspectionPassRate, reqVO.getInspectionPassRate())
                .eqIfPresent(TaskDO::getTotalEntryVolume, reqVO.getTotalEntryVolume())
                .eqIfPresent(TaskDO::getEquipmentIntactRate, reqVO.getEquipmentIntactRate())
                .eqIfPresent(TaskDO::getEnvironmentQualifiedRate, reqVO.getEnvironmentQualifiedRate())
                .eqIfPresent(TaskDO::getSatisfaction, reqVO.getSatisfaction())
                .eqIfPresent(TaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TaskDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskDO::getId));
    }

    List<TaskDetailWithPublicToiletDO> selectDetailPageWithPublicToilet(@Param("reqVO") TaskPageReqVO pageReqVO);

    Long selectCountWithPublicToilet(@Param("reqVO") TaskPageReqVO pageReqVO);

    List<TaskDetailWithGarbageTransferDO> selectDetailPageWithGarbageTransfer(@Param("reqVO") TaskPageReqVO pageReqVO);

    Long selectCountWithGarbageTransfer(@Param("reqVO") TaskPageReqVO pageReqVO);

    List<TaskDetailWithPublicInstitutionDO> selectDetailPageWithPublicInstitution(@Param("reqVO") TaskPageReqVO pageReqVO);

    Long selectCountWithPublicInstitution(@Param("reqVO") TaskPageReqVO pageReqVO);
}