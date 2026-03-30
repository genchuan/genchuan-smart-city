package cn.iocoder.yudao.module.waterdetection.service.watersampletestsummary;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampletestsummary.WaterSampleTestSummaryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 外检统计水质检测结果汇总 Service 接口
 *
 * @author zhucongquan
 */
public interface WaterSampleTestSummaryService {

    /**
     * 创建外检统计水质检测结果汇总
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterSampleTestSummary(@Valid WaterSampleTestSummarySaveReqVO createReqVO);

    /**
     * 更新外检统计水质检测结果汇总
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterSampleTestSummary(@Valid WaterSampleTestSummarySaveReqVO updateReqVO);

    /**
     * 删除外检统计水质检测结果汇总
     *
     * @param id 编号
     */
    void deleteWaterSampleTestSummary(Long id);

    /**
     * 获得外检统计水质检测结果汇总
     *
     * @param id 编号
     * @return 外检统计水质检测结果汇总
     */
    WaterSampleTestSummaryDO getWaterSampleTestSummary(Long id);

    /**
     * 获得外检统计水质检测结果汇总分页
     *
     * @param pageReqVO 分页查询
     * @return 外检统计水质检测结果汇总分页
     */
    PageResult<WaterSampleTestSummaryDO> getWaterSampleTestSummaryPage(WaterSampleTestSummaryPageReqVO pageReqVO);

    /**
     * 批量导入用户
     *
     * @param importWaterSamples 导入水样结果
     * @param isUpdateSupport    是否支持更新
     * @return 导入结果
     */
    WaterSampleTestImportRespVO importWaterSampleList(List<WaterSampleTestImportExcelVO> importWaterSamples, boolean isUpdateSupport);

    /**
     * 接收设备数据
     *
     * @param deviceData 设备数据
     * @return 接收结果
     */
//    WaterDeviceDataRespVO receiveDeviceData(WaterDeviceDataReqVO deviceData);
    String receiveDeviceData(WaterDeviceDataReqVO deviceData);
}