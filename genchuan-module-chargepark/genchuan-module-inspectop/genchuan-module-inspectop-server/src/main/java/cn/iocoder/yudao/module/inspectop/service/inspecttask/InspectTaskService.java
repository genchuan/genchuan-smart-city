package cn.iocoder.yudao.module.inspectop.service.inspecttask;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡检任务 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectTaskService {

    /**
     * 创建巡检任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTask(@Valid InspectTaskSaveReqVO createReqVO);

    /**
     * 更新巡检任务
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTask(@Valid InspectTaskSaveReqVO updateReqVO);

    /**
     * 删除巡检任务
     *
     * @param id 编号
     */
    void deleteInspectTask(Long id);

    /**
    * 批量删除巡检任务
    *
    * @param ids 编号
    */
    void deleteInspectTaskListByIds(List<Long> ids);

    /**
     * 获得巡检任务
     *
     * @param id 编号
     * @return 巡检任务
     */
    InspectTaskDO getInspectTask(Long id);

    /**
     * 获得巡检任务分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检任务分页
     */
    PageResult<InspectTaskDO> getInspectTaskPage(InspectTaskPageReqVO pageReqVO);

}