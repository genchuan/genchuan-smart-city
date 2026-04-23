package cn.iocoder.yudao.module.studentmgmt.service.moralactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity.MoralActivityDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 德育活动 Service 接口
 *
 * @author 芋道源码
 */
public interface MoralActivityService {

    /**
     * 创建德育活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMoralActivity(@Valid MoralActivitySaveReqVO createReqVO);

    /**
     * 更新德育活动
     *
     * @param updateReqVO 更新信息
     */
    void updateMoralActivity(@Valid MoralActivitySaveReqVO updateReqVO);

    /**
     * 删除德育活动
     *
     * @param id 编号
     */
    void deleteMoralActivity(Long id);

    /**
    * 批量删除德育活动
    *
    * @param ids 编号
    */
    void deleteMoralActivityListByIds(List<Long> ids);

    /**
     * 获得德育活动
     *
     * @param id 编号
     * @return 德育活动
     */
    MoralActivityDO getMoralActivity(Long id);

    /**
     * 获得德育活动分页
     *
     * @param pageReqVO 分页查询
     * @return 德育活动分页
     */
    PageResult<MoralActivityDO> getMoralActivityPage(MoralActivityPageReqVO pageReqVO);

    boolean publish(@Valid MoralActivityPublishReqVO reqVO);

    boolean join(@Valid MoralActivityJoinReqVO reqVO);

    boolean record(@Valid MoralActivityRecordReqVO reqVO);

    MoralActivityChartRespVO chart(@Valid MoralActivityChartReqVO reqVO);

    ChartActivityCountRespVO activityCount(@Valid MoralActivityChartReqVO reqVO);
}