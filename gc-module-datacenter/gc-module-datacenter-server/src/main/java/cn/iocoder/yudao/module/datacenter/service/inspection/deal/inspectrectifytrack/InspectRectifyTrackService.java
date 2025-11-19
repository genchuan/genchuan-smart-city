package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytrack.InspectRectifyTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检整改进度跟踪视图 Service 接口
 *
 * @author zcq
 */
public interface InspectRectifyTrackService {

    /**
     * 创建巡查巡检整改进度跟踪视图
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRectifyTrack(@Valid InspectRectifyTrackSaveReqVO createReqVO);

    /**
     * 更新巡查巡检整改进度跟踪视图
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRectifyTrack(@Valid InspectRectifyTrackSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检整改进度跟踪视图
     *
     * @param id 编号
     */
    void deleteInspectRectifyTrack(Long id);

    /**
     * 获得巡查巡检整改进度跟踪视图
     *
     * @param id 编号
     * @return 巡查巡检整改进度跟踪视图
     */
    InspectRectifyTrackDO getInspectRectifyTrack(Long id);

    /**
     * 获得巡查巡检整改进度跟踪视图分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检整改进度跟踪视图分页
     */
    PageResult<InspectRectifyTrackDO> getInspectRectifyTrackPage(InspectRectifyTrackPageReqVO pageReqVO);

}