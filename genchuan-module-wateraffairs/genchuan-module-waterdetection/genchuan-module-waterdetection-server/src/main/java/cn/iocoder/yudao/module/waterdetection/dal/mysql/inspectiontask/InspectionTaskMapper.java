package cn.iocoder.yudao.module.waterdetection.dal.mysql.inspectiontask;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectiontask.InspectionTaskDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo.*;

/**
 * 巡检任务派发与执行 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectionTaskMapper extends BaseMapperX<InspectionTaskDO> {

    default PageResult<InspectionTaskDO> selectPage(InspectionTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionTaskDO>()
                .eqIfPresent(InspectionTaskDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectionTaskDO::getInspectorId, reqVO.getInspectorId())
                .eqIfPresent(InspectionTaskDO::getTaskContent, reqVO.getTaskContent())
                .betweenIfPresent(InspectionTaskDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(InspectionTaskDO::getReceiveTime, reqVO.getReceiveTime())
                .betweenIfPresent(InspectionTaskDO::getCheckinTime, reqVO.getCheckinTime())
                .eqIfPresent(InspectionTaskDO::getInspectionResult, reqVO.getInspectionResult())
                .eqIfPresent(InspectionTaskDO::getPhotoUrl, reqVO.getPhotoUrl())
                .eqIfPresent(InspectionTaskDO::getLocationInfo, reqVO.getLocationInfo())
                .betweenIfPresent(InspectionTaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionTaskDO::getId));
    }

}