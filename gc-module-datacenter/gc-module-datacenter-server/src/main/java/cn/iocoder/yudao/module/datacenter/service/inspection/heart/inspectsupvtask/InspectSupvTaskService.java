package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtask.InspectSupvTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检重点督办任务 Service 接口
 *
 * @author zcq
 */
public interface InspectSupvTaskService {

    /**
     * 创建巡查巡检重点督办任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSupvTask(@Valid InspectSupvTaskSaveReqVO createReqVO);

    /**
     * 更新巡查巡检重点督办任务
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSupvTask(@Valid InspectSupvTaskSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检重点督办任务
     *
     * @param id 编号
     */
    void deleteInspectSupvTask(Long id);

    /**
     * 获得巡查巡检重点督办任务
     *
     * @param id 编号
     * @return 巡查巡检重点督办任务
     */
    InspectSupvTaskDO getInspectSupvTask(Long id);

    /**
     * 获得巡查巡检重点督办任务分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检重点督办任务分页
     */
    PageResult<InspectSupvTaskDO> getInspectSupvTaskPage(InspectSupvTaskPageReqVO pageReqVO);

}