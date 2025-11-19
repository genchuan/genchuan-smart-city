package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtrackview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检督办进度跟踪视图 Service 接口
 *
 * @author zcq
 */
public interface InspectSupvTrackViewService {

    /**
     * 创建巡查巡检督办进度跟踪视图
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSupvTrackView(@Valid InspectSupvTrackViewSaveReqVO createReqVO);

    /**
     * 更新巡查巡检督办进度跟踪视图
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSupvTrackView(@Valid InspectSupvTrackViewSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检督办进度跟踪视图
     *
     * @param id 编号
     */
    void deleteInspectSupvTrackView(Long id);

    /**
     * 获得巡查巡检督办进度跟踪视图
     *
     * @param id 编号
     * @return 巡查巡检督办进度跟踪视图
     */
    InspectSupvTrackViewDO getInspectSupvTrackView(Long id);

    /**
     * 获得巡查巡检督办进度跟踪视图分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检督办进度跟踪视图分页
     */
    PageResult<InspectSupvTrackViewDO> getInspectSupvTrackViewPage(InspectSupvTrackViewPageReqVO pageReqVO);

}