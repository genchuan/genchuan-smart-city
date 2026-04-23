package cn.iocoder.yudao.module.inspectop.service.scheduleview;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 排班查看 Service 接口
 *
 * @author zhucongquan
 */
public interface ScheduleViewService {

    /**
     * 创建排班查看
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScheduleView(@Valid ScheduleViewSaveReqVO createReqVO);

    /**
     * 更新排班查看
     *
     * @param updateReqVO 更新信息
     */
    void updateScheduleView(@Valid ScheduleViewSaveReqVO updateReqVO);

    /**
     * 删除排班查看
     *
     * @param id 编号
     */
    void deleteScheduleView(Long id);

    /**
    * 批量删除排班查看
    *
    * @param ids 编号
    */
    void deleteScheduleViewListByIds(List<Long> ids);

    /**
     * 获得排班查看
     *
     * @param id 编号
     * @return 排班查看
     */
    ScheduleViewDO getScheduleView(Long id);

    /**
     * 获得排班查看分页
     *
     * @param pageReqVO 分页查询
     * @return 排班查看分页
     */
    PageResult<ScheduleViewRespVO> getScheduleViewPage(ScheduleViewPageReqVO pageReqVO);

    /**
     * 申请换班
     *
     * @param reqVO 申请信息
     * @return 申请结果
     */
    Boolean applyShift(@Valid ShiftApplyReqVO reqVO);

    /**
     * 获取排班统计图表数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    ScheduleViewChartRespVO getScheduleViewChart(ScheduleViewChartReqVO reqVO);
}