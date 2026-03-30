package cn.iocoder.yudao.module.evaluate.dal.mysql.task;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype.CollectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.scope.ScopeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.taskstatus.TaskStatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate.TaskTemplateDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价任务 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskMapper extends BaseMapperX<TaskDO> {

    default PageResult<TaskDO> selectPage(TaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskDO>()
                .eqIfPresent(TaskDO::getTaskId, reqVO.getTaskId())
                .likeIfPresent(TaskDO::getName, reqVO.getName())
                .eqIfPresent(TaskDO::getCode, reqVO.getCode())
                .eqIfPresent(TaskDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(TaskDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(TaskDO::getScopeId, reqVO.getScopeId())
                .betweenIfPresent(TaskDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(TaskDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(TaskDO::getCollectTypeId, reqVO.getCollectTypeId())
                .eqIfPresent(TaskDO::getTotalCount, reqVO.getTotalCount())
                .eqIfPresent(TaskDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(TaskDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(TaskDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(TaskDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(TaskDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(TaskDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(TaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TaskDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskDO::getId));
    }
    /**
     * 分页联表查询评价任务
     */
    default PageResult<TaskRespVO> selectTaskPage(TaskPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<TaskRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 构建MPJ联表查询条件
        MPJLambdaWrapper<TaskDO> wrapper = new MPJLambdaWrapper<TaskDO>()
                // 主表字段（eval_task）
                .selectAll(TaskDO.class)
                // 关联模板表：eval_task_template（t2）
                .leftJoin(TaskTemplateDO.class, TaskTemplateDO::getId, TaskDO::getTemplateId)
                .selectAs(TaskTemplateDO::getName, TaskRespVO::getTemplateName)
                // 关联范围字典表：sys_scope（t3，根据实际表名调整）
                .leftJoin(ScopeDO.class, ScopeDO::getScopeId, TaskDO::getScopeId) // ScopeDO是范围表实体
                .selectAs(ScopeDO::getName, TaskRespVO::getScopeName)
                // 关联采集方式字典表：sys_collect_type（t4）
                .leftJoin(CollectTypeDO.class, CollectTypeDO::getTypeId, TaskDO::getCollectTypeId) // CollectTypeDO是采集方式表实体
                .selectAs(CollectTypeDO::getName, TaskRespVO::getCollectTypeName)
                // 关联任务状态字典表：sys_task_status（t5）
                .leftJoin(TaskStatusDO.class, TaskStatusDO::getStatusId, TaskDO::getStatusId) // TaskStatusDO是状态表实体
                .selectAs(TaskStatusDO::getName, TaskRespVO::getStatusName)
                // 关联创建人表：sys_user（t6）
                .leftJoin(UserDO.class, UserDO::getUserId, TaskDO::getCreateBy)
                .selectAs(UserDO::getUserName, TaskRespVO::getCreateUserName)
                // 关联评价对象表：eval_object（t7）
               // .leftJoin(ObjectDO.class, ObjectDO::getObjectId, TaskDO::getObjectId)
               // .selectAs(ObjectDO::getName, TaskRespVO::getObjectName)
                // ========== 筛选条件 ==========
                // 任务ID
                .eq(reqVO.getTaskId() != null, TaskDO::getTaskId, reqVO.getTaskId())
                // 任务名称（模糊查询）
                .like(reqVO.getName() != null, TaskDO::getName, reqVO.getName())
                // 任务编码（模糊查询）
                .like(reqVO.getCode() != null, TaskDO::getCode, reqVO.getCode())
                // 关联模板ID
                .eq(reqVO.getTemplateId() != null, TaskDO::getTemplateId, reqVO.getTemplateId())
                //关联评价对象ID
                .eq(reqVO.getObjectId() != null, TaskDO::getObjectId, reqVO.getObjectId())
                // 评价对象范围ID
                .eq(reqVO.getScopeId() != null, TaskDO::getScopeId, reqVO.getScopeId())
                // 任务开始时间（区间）
                .ge(reqVO.getStartTime() != null, TaskDO::getStartTime, reqVO.getStartTime() != null ? reqVO.getStartTime()[0] : null)
                .le(reqVO.getStartTime() != null, TaskDO::getStartTime, reqVO.getStartTime() != null ? reqVO.getStartTime()[1] : null)

                // 任务结束时间（区间）
                .ge(reqVO.getEndTime() != null, TaskDO::getEndTime, reqVO.getEndTime() != null ? reqVO.getEndTime()[0] : null)
                .le(reqVO.getEndTime() != null, TaskDO::getEndTime, reqVO.getEndTime() != null ? reqVO.getEndTime()[1] : null)
                // 数据采集方式ID
                .eq(reqVO.getCollectTypeId() != null, TaskDO::getCollectTypeId, reqVO.getCollectTypeId())
                // 总对象数
                .eq(reqVO.getTotalCount() != null, TaskDO::getTotalCount, reqVO.getTotalCount())
                // 已完成对象数
                .eq(reqVO.getCompletedCount() != null, TaskDO::getCompletedCount, reqVO.getCompletedCount())
                // 完成率
                .eq(reqVO.getCompletionRate() != null, TaskDO::getCompletionRate, reqVO.getCompletionRate())
                // 任务状态ID
                .eq(reqVO.getStatusId() != null, TaskDO::getStatusId, reqVO.getStatusId())
                // 创建人ID
                .eq(reqVO.getCreateBy() != null, TaskDO::getCreateBy, reqVO.getCreateBy())
                // 创建时间（业务字段）区间
                .ge(reqVO.getBizCreateTime() != null, TaskDO::getBizCreateTime, reqVO.getBizCreateTime() != null ? reqVO.getBizCreateTime()[0] : null)
                .le(reqVO.getBizCreateTime() != null, TaskDO::getBizCreateTime, reqVO.getBizCreateTime() != null ? reqVO.getBizCreateTime()[1] : null)
                // 更新时间（业务字段）区间
                //.ge(reqVO.getBizUpdateTime() != null && reqVO.getBizUpdateTime()[0] != null, TaskDO::getBizUpdateTime, reqVO.getBizUpdateTime()[0])
                //.le(reqVO.getBizUpdateTime() != null && reqVO.getBizUpdateTime()[1] != null, TaskDO::getBizUpdateTime, reqVO.getBizUpdateTime()[1])
                // 创建时间区间
               // .ge(reqVO.getCreateTime() != null && reqVO.getCreateTime()[0] != null, TaskDO::getCreateTime, reqVO.getCreateTime()[0])
               // .le(reqVO.getCreateTime() != null && reqVO.getCreateTime()[1] != null, TaskDO::getCreateTime, reqVO.getCreateTime()[1])

                // 通用扩展字段
                .eq(reqVO.getExtCommon1() != null, TaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eq(reqVO.getExtCommon2() != null, TaskDO::getExtCommon2, reqVO.getExtCommon2())
                .eq(reqVO.getExtCommon3() != null, TaskDO::getExtCommon3, reqVO.getExtCommon3())
                .eq(reqVO.getExtCommon4() != null, TaskDO::getExtCommon4, reqVO.getExtCommon4())

                // 逻辑删除（假设主表有deleted字段）
                .eq(TaskDO::getDeleted, 0)
                // 排序：按创建时间倒序
                .orderByDesc(TaskDO::getCreateTime);

        // 3. 执行分页查询
        Page<TaskRespVO> resultPage = selectJoinPage(page, TaskRespVO.class, wrapper);


        // 5. 封装PageResult返回
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }


}