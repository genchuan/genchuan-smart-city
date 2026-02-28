package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionImportReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionImportRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.all.GarbageCollectionCardAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.executing.GarbageCollectionCardExecutingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.pending.GarbageCollectionCardPendingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all.AreaCompletionRateColumnAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.pending.TimePeriodPendingColumnVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.statistics.GarbageCollectionStatisticsRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.executing.GarbageCollectionDailyTrendVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 收运计划 Service 接口
 *
 * @author 芋道源码
 */
public interface GarbageCollectionService {

    /**
     * 创建收运计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageCollection(@Valid GarbageCollectionSaveReqVO createReqVO);

    /**
     * 更新收运计划
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageCollection(@Valid GarbageCollectionSaveReqVO updateReqVO);

    /**
     * 删除收运计划
     *
     * @param id 编号
     */
    void deleteGarbageCollection(Long id);

    /**
     * 批量删除收运计划
     *
     * @param ids 编号列表
     */
    void deleteGarbageCollectionBatch(List<Long> ids);

    /**
     * 获得收运计划
     *
     * @param id 编号
     * @return 收运计划
     */
    GarbageCollectionDO getGarbageCollection(Long id);

    /**
     * 获得收运计划分页
     *
     * @param pageReqVO 分页查询
     * @return 收运计划分页
     */
    PageResult<GarbageCollectionDO> getGarbageCollectionPage(GarbageCollectionPageReqVO pageReqVO);

    /**
     * 获得收运计划详情分页
     *
     * @param pageReqVO 分页查询
     * @return 收运计划详情分页
     */
    PageResult<GarbageCollectionDetailDO> getGarbageCollectionDetailPage(GarbageCollectionPageReqVO pageReqVO);

    /**
     * 获取收运计划统计数据（图表用）
     * @return 统计结果
     */
    GarbageCollectionCardAllVO getGarbageCollectionCardAll();

    /**
     * 获取收运品类占比（环状图）
     */
    List<GarbageCollectionCircleAllVO> getGarbageTypeCircleAll();

    /**
     * 获取计划状态占比（环状图）
     */
    List<GarbageCollectionCircleAllVO> getPlanStatusCircleAll();

    /**
     * 获取区域分布占比（环状图）
     */
    List<GarbageCollectionCircleAllVO> getAreaDistributionCircleAll();

    /**
     * 获取各区域收运完成率（柱状图）
     */
    List<AreaCompletionRateColumnAllVO> getAreaCompletionRateColumnAll();

    /**
     * 获取待执行收运计划统计卡片数据
     * @return 待执行统计卡片VO
     */
    GarbageCollectionCardPendingVO getGarbageCollectionCardPending();

    /**
     * 获取待执行计划-按区域统计
     */
    List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByArea();

    /**
     * 获取待执行计划-按品类统计
     */
    List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByGarbageType();

    /**
     * 获取不同时段待执行计划数量（柱状图）
     */
    List<TimePeriodPendingColumnVO> getTimePeriodPendingColumn();

    /**
     * 批量导入收运计划
     *
     * @param importList 导入数据列表
     * @return 导入结果
     */
    GarbageCollectionImportRespVO importGarbageCollection(List<GarbageCollectionImportReqVO> importList);

    /**
     * 获取收运计划统计数据（按状态分组）
     * @return 统计数据
     */
    GarbageCollectionStatisticsRespVO getGarbageCollectionStatistics();

    /**
     * 获取卡片统计数据（当前作业任务数、正常运行数、异常标记数）
     * @return 卡片统计数据
     */
    GarbageCollectionCardExecutingVO getGarbageCollectionCardExecuting();

    /**
     * 获取当日收运量实时增长趋势（基础折线图）
     * @return 按小时维度的收运量趋势数据
     */
    List<GarbageCollectionDailyTrendVO> getDailyCollectionVolumeTrend();
}