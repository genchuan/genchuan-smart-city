package cn.iocoder.yudao.module.datacenter.service.commanddispatch.taskoverallmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.taskoverallmng.TaskOverallMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 任务统筹管理 Service 接口
 *
 * @author 亘川智城
 */
public interface TaskOverallMngService {

    /**
     * 创建任务统筹管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskOverallMng(@Valid TaskOverallMngSaveReqVO createReqVO);

    /**
     * 更新任务统筹管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskOverallMng(@Valid TaskOverallMngSaveReqVO updateReqVO);

    /**
     * 删除任务统筹管理
     *
     * @param id 编号
     */
    void deleteTaskOverallMng(Long id);

    /**
     * 获得任务统筹管理
     *
     * @param id 编号
     * @return 任务统筹管理
     */
    TaskOverallMngDO getTaskOverallMng(Long id);

    /**
     * 获得任务统筹管理分页
     *
     * @param pageReqVO 分页查询
     * @return 任务统筹管理分页
     */
    PageResult<TaskOverallMngDO> getTaskOverallMngPage(TaskOverallMngPageReqVO pageReqVO);

}