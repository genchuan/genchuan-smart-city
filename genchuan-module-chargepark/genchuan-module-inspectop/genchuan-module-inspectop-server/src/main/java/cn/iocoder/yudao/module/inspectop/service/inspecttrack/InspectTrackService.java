package cn.iocoder.yudao.module.inspectop.service.inspecttrack;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 巡检轨迹 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectTrackService {

    /**
     * 创建巡检轨迹
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTrack(@Valid InspectTrackSaveReqVO createReqVO);

    /**
     * 更新巡检轨迹
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTrack(@Valid InspectTrackSaveReqVO updateReqVO);

    /**
     * 删除巡检轨迹
     *
     * @param id 编号
     */
    void deleteInspectTrack(Long id);

    /**
    * 批量删除巡检轨迹
    *
    * @param ids 编号
    */
    void deleteInspectTrackListByIds(List<Long> ids);

    /**
     * 获得巡检轨迹
     *
     * @param id 编号
     * @return 巡检轨迹
     */
    InspectTrackDO getInspectTrack(Long id);

    /**
     * 获得巡检轨迹分页
     *
     * @param pageReqVO 分页查询
     * @return 巡检轨迹分页
     */
    PageResult<InspectTrackRespVO> getInspectTrackPage(InspectTrackPageReqVO pageReqVO);

    /**
     * 获得巡检轨迹回放信息
     *
     * @param id 轨迹ID
     * @return 巡检轨迹回放信息
     */
    InspectTrackReplayRespVO getInspectTrackReplay(Long id);

    /**
     * 核查巡检轨迹
     *
     * @param checkReqVO 核查信息
     * @return 是否成功
     */
    Boolean checkInspectTrack(@Valid InspectTrackCheckReqVO checkReqVO);

    /**
     * 获得巡检轨迹图表统计数据
     *
     * @param reqVO 查询参数
     * @return 图表统计数据
     */
    InspectTrackChartRespVO getInspectTrackChart(InspectTrackChartReqVO reqVO);

}