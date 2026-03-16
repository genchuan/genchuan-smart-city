package cn.iocoder.yudao.module.envirhealth.service.tasktype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.tasktype.vo.TaskTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.tasktype.vo.TaskTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tasktype.TaskTypeDO;
import jakarta.validation.Valid;

/**
 * 任务类型字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface TaskTypeService {

    /**
     * 创建任务类型字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskType(@Valid TaskTypeSaveReqVO createReqVO);

    /**
     * 更新任务类型字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskType(@Valid TaskTypeSaveReqVO updateReqVO);

    /**
     * 删除任务类型字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteTaskType(Long id);

    /**
     * 获得任务类型字典表【通用复用】
     *
     * @param id 编号
     * @return 任务类型字典表【通用复用】
     */
    TaskTypeDO getTaskType(Long id);

    /**
     * 获得任务类型字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 任务类型字典表【通用复用】分页
     */
    PageResult<TaskTypeDO> getTaskTypePage(TaskTypePageReqVO pageReqVO);

}