package cn.iocoder.yudao.module.waterdetection.service.inspectiontask;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectiontask.InspectionTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡检任务派发与执行 Service 接口
 *
 * @author zcq
 */
public interface InspectionTaskService {

    /**
     * 创建巡检任务派发与执行
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionTask(@Valid InspectionTaskSaveReqVO createReqVO);

    /**
     * 更新巡检任务派发与执行
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionTask(@Valid InspectionTaskSaveReqVO updateReqVO);

    /**
     * 删除巡检任务派发与执行
     *
     * @param id 编号
     */
    void deleteInspectionTask(Long id);

    /**
     * 获得巡检任务派发与执行
     *
     * @param id 编号
     * @return 巡检任务派发与执行
     */
    InspectionTaskDO getInspectionTask(Long id);

    /**
     * 获得巡检任务派发与执行分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检任务派发与执行分页
     */
    PageResult<InspectionTaskDO> getInspectionTaskPage(InspectionTaskPageReqVO pageReqVO);

}