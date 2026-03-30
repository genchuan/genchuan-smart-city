package cn.iocoder.yudao.module.waterdetection.service.taskdispatch;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.taskdispatch.TaskDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 任务派发 Service 接口
 *
 * @author zcq
 */
public interface TaskDispatchService {

    /**
     * 创建任务派发
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskDispatch(@Valid TaskDispatchSaveReqVO createReqVO);

    /**
     * 更新任务派发
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskDispatch(@Valid TaskDispatchSaveReqVO updateReqVO);

    /**
     * 删除任务派发
     *
     * @param id 编号
     */
    void deleteTaskDispatch(Long id);

    /**
     * 获得任务派发
     *
     * @param id 编号
     * @return 任务派发
     */
    TaskDispatchDO getTaskDispatch(Long id);

    /**
     * 获得任务派发分页
     *
     * @param pageReqVO 分页查询
     * @return 任务派发分页
     */
    PageResult<TaskDispatchDO> getTaskDispatchPage(TaskDispatchPageReqVO pageReqVO);

}