package cn.iocoder.yudao.module.waterdetection.dal.mysql.taskdispatch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.taskdispatch.TaskDispatchDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo.*;

/**
 * 任务派发 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TaskDispatchMapper extends BaseMapperX<TaskDispatchDO> {

    default PageResult<TaskDispatchDO> selectPage(TaskDispatchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDispatchDO>()
                .eqIfPresent(TaskDispatchDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(TaskDispatchDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(TaskDispatchDO::getTestPoints, reqVO.getTestPoints())
                .eqIfPresent(TaskDispatchDO::getIndicators, reqVO.getIndicators())
                .eqIfPresent(TaskDispatchDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(TaskDispatchDO::getDispatchDept, reqVO.getDispatchDept())
                .betweenIfPresent(TaskDispatchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskDispatchDO::getId));
    }

}