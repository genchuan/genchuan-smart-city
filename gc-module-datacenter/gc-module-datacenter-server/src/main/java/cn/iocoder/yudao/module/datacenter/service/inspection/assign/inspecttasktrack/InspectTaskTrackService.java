package cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttasktrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttasktrack.InspectTaskTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检任务跟踪 Service 接口
 *
 * @author zcq
 */
public interface InspectTaskTrackService {

    /**
     * 创建巡查巡检任务跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTaskTrack(@Valid InspectTaskTrackSaveReqVO createReqVO);

    /**
     * 更新巡查巡检任务跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTaskTrack(@Valid InspectTaskTrackSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检任务跟踪
     *
     * @param id 编号
     */
    void deleteInspectTaskTrack(Long id);

    /**
     * 获得巡查巡检任务跟踪
     *
     * @param id 编号
     * @return 巡查巡检任务跟踪
     */
    InspectTaskTrackDO getInspectTaskTrack(Long id);

    /**
     * 获得巡查巡检任务跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检任务跟踪分页
     */
    PageResult<InspectTaskTrackDO> getInspectTaskTrackPage(InspectTaskTrackPageReqVO pageReqVO);

}