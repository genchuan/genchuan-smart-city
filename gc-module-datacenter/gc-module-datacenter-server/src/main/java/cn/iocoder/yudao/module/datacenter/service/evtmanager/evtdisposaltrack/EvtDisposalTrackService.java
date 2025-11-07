package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtdisposaltrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtdisposaltrack.EvtDisposalTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件处置跟踪 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtDisposalTrackService {

    /**
     * 创建事件处置跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtDisposalTrack(@Valid EvtDisposalTrackSaveReqVO createReqVO);

    /**
     * 更新事件处置跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtDisposalTrack(@Valid EvtDisposalTrackSaveReqVO updateReqVO);

    /**
     * 删除事件处置跟踪
     *
     * @param id 编号
     */
    void deleteEvtDisposalTrack(Long id);

    /**
     * 获得事件处置跟踪
     *
     * @param id 编号
     * @return 事件处置跟踪
     */
    EvtDisposalTrackDO getEvtDisposalTrack(Long id);

    /**
     * 获得事件处置跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 事件处置跟踪分页
     */
    PageResult<EvtDisposalTrackDO> getEvtDisposalTrackPage(EvtDisposalTrackPageReqVO pageReqVO);

}