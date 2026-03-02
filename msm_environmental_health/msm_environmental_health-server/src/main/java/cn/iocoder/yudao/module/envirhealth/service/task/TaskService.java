package cn.iocoder.yudao.module.envirhealth.service.task;

import java.util.*;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithGarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail.TaskDetailWithPublicToiletDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 任务 Service 接口
 *
 * @author 芋道源码
 */
public interface TaskService {

    /**
     * 创建任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTask(@Valid TaskSaveReqVO createReqVO);

    /**
     * 更新任务
     *
     * @param updateReqVO 更新信息
     */
    void updateTask(@Valid TaskSaveReqVO updateReqVO);

    /**
     * 删除任务
     *
     * @param id 编号
     */
    void deleteTask(Long id);

    /**
     * 获得任务
     *
     * @param id 编号
     * @return 任务
     */
    TaskDO getTask(Long id);

    /**
     * 获得任务分页
     *
     * @param pageReqVO 分页查询
     * @return 任务分页
     */
    PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO);

    PageResult<TaskDetailWithPublicToiletDO> getTaskDetailPageWithPublicToilet(TaskPageReqVO pageReqVO);

    PageResult<TaskDetailWithGarbageTransferDO> getTaskDetailPageWithGarbageTransfer(TaskPageReqVO pageReqVO);

    PageResult<TaskDetailWithPublicInstitutionDO> getTaskDetailPageWithPublicInstitution(TaskPageReqVO pageReqVO);
}