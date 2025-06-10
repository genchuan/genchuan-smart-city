package cn.iocoder.yudao.module.smartcity.dal.mysql.maintenancetasks;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancetasks.MaintenanceTasksDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks.vo.*;

/**
 * 养护任务 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MaintenanceTasksMapper extends BaseMapperX<MaintenanceTasksDO> {

    default PageResult<MaintenanceTasksDO> selectPage(MaintenanceTasksPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintenanceTasksDO>()
                .eqIfPresent(MaintenanceTasksDO::getTaskNumber, reqVO.getTaskNumber())
                .eqIfPresent(MaintenanceTasksDO::getTask, reqVO.getTask())
                .eqIfPresent(MaintenanceTasksDO::getMaintainTheLandParcel, reqVO.getMaintainTheLandParcel())
                .eqIfPresent(MaintenanceTasksDO::getMaintenancePersonnel, reqVO.getMaintenancePersonnel())
                .betweenIfPresent(MaintenanceTasksDO::getTaskStartTime, reqVO.getTaskStartTime())
                .betweenIfPresent(MaintenanceTasksDO::getTaskEndTime, reqVO.getTaskEndTime())
                .eqIfPresent(MaintenanceTasksDO::getTaskContent, reqVO.getTaskContent())
                .eqIfPresent(MaintenanceTasksDO::getRequiredTools, reqVO.getRequiredTools())
                .eqIfPresent(MaintenanceTasksDO::getRequiredMaterials, reqVO.getRequiredMaterials())
                .eqIfPresent(MaintenanceTasksDO::getTaskPriority, reqVO.getTaskPriority())
                .eqIfPresent(MaintenanceTasksDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MaintenanceTasksDO::getCompletionStatusDescription, reqVO.getCompletionStatusDescription())
                .betweenIfPresent(MaintenanceTasksDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintenanceTasksDO::getId));
    }

}