package cn.iocoder.yudao.module.evaluate.dal.mysql.taskstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.taskstatus.TaskStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务状态字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TaskStatusMapper extends BaseMapperX<TaskStatusDO> {

    default PageResult<TaskStatusDO> selectPage(TaskStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskStatusDO>()
                .eqIfPresent(TaskStatusDO::getStatusId, reqVO.getStatusId())
                .likeIfPresent(TaskStatusDO::getName, reqVO.getName())
                .eqIfPresent(TaskStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(TaskStatusDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(TaskStatusDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(TaskStatusDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(TaskStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TaskStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TaskStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskStatusDO::getId));
    }

}