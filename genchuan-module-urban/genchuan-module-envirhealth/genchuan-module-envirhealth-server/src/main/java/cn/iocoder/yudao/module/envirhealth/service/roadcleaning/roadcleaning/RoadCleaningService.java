package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 道路清扫计划 Service 接口
 *
 * @author 芋道源码
 */
public interface RoadCleaningService {

    /**
     * 创建道路清扫计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoadCleaning(@Valid RoadCleaningSaveReqVO createReqVO);

    /**
     * 更新道路清扫计划
     *
     * @param updateReqVO 更新信息
     */
    void updateRoadCleaning(@Valid RoadCleaningSaveReqVO updateReqVO);

    /**
     * 删除道路清扫计划
     *
     * @param id 编号
     */
    void deleteRoadCleaning(Long id);

    /**
     * 批量删除道路清扫计划
     *
     * @param ids 编号列表
     */
    void deleteRoadCleaningBatch(List<Long> ids);

    /**
     * 获得道路清扫计划
     *
     * @param id 编号
     * @return 道路清扫计划
     */
    RoadCleaningDO getRoadCleaning(Long id);

    /**
     * 获得道路清扫计划分页
     *
     * @param pageReqVO 分页查询
     * @return 道路清扫计划分页
     */
    PageResult<RoadCleaningDO> getRoadCleaningPage(RoadCleaningPageReqVO pageReqVO);

    /**
     * 获得道路清扫计划分页(详情)
     *
     * @param pageReqVO 分页查询
     * @return 道路清扫计划分页
     */
    PageResult<RoadCleaningDetailDO> getRoadCleaningDetailPage(RoadCleaningPageReqVO pageReqVO);

    /**
     * 获得道路名称下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getRoadCleaningNameOptions();

    /**
     * 批量调整道路清扫计划
     *
     * @param adjustReqVO 批量调整信息
     */
    void batchAdjustRoadCleaning(RoadCleaningBatchAdjustReqVO adjustReqVO);

    /**
     * 卡片/圆环图/柱状图(全部)
     */
    RoadCleaningAllRespVO getAll();

    /**
     * 卡片/圆环图/柱状图(待执行)
     */
    RoadCleaningPendingRespVO getPendingData();

    /**
     * 卡片/圆环图/柱状图(执行中)
     */
    RoadCleaningExecutingRespVO getExecutingData();

    /**
     * 获得核查统计卡片/圆环图/柱状图数据
     *
     * @return 核查统计信息
     */
    RoadCleaningCheckRespVO getCheckData();

    /**
     * 获得已完成统计卡片/柱状图/折线图/圆环图数据
     *
     * @param timeRange 时间范围：day/week/month
     * @return 已完成统计信息
     */
    RoadCleaningCompletedRespVO getCompletedData(String timeRange);

}