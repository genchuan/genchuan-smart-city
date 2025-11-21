package cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltask;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltask.EvalTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 任务管理 Service 接口
 *
 * @author zcq
 */
public interface EvalTaskService {

    /**
     * 创建任务管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalTask(@Valid EvalTaskSaveReqVO createReqVO);

    /**
     * 更新任务管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalTask(@Valid EvalTaskSaveReqVO updateReqVO);

    /**
     * 删除任务管理
     *
     * @param id 编号
     */
    void deleteEvalTask(Long id);

    /**
     * 获得任务管理
     *
     * @param id 编号
     * @return 任务管理
     */
    EvalTaskDO getEvalTask(Long id);

    /**
     * 获得任务管理分页
     *
     * @param pageReqVO 分页查询
     * @return 任务管理分页
     */
    PageResult<EvalTaskDO> getEvalTaskPage(EvalTaskPageReqVO pageReqVO);

}