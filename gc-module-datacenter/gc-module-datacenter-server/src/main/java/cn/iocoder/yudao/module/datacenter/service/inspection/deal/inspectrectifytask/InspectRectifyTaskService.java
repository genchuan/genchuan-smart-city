package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytask.InspectRectifyTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检整改任务派发 Service 接口
 *
 * @author zcq
 */
public interface InspectRectifyTaskService {

    /**
     * 创建巡查巡检整改任务派发
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRectifyTask(@Valid InspectRectifyTaskSaveReqVO createReqVO);

    /**
     * 更新巡查巡检整改任务派发
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRectifyTask(@Valid InspectRectifyTaskSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检整改任务派发
     *
     * @param id 编号
     */
    void deleteInspectRectifyTask(Long id);

    /**
     * 获得巡查巡检整改任务派发
     *
     * @param id 编号
     * @return 巡查巡检整改任务派发
     */
    InspectRectifyTaskDO getInspectRectifyTask(Long id);

    /**
     * 获得巡查巡检整改任务派发分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检整改任务派发分页
     */
    PageResult<InspectRectifyTaskDO> getInspectRectifyTaskPage(InspectRectifyTaskPageReqVO pageReqVO);

}