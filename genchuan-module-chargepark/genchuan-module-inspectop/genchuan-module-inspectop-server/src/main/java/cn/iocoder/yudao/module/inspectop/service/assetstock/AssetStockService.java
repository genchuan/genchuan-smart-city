package cn.iocoder.yudao.module.inspectop.service.assetstock;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 库存管理 Service 接口
 *
 * @author zhucongquan
 */
public interface AssetStockService {

    /**
     * 创建库存管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetStock(@Valid AssetStockSaveReqVO createReqVO);

    /**
     * 更新库存管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetStock(@Valid AssetStockSaveReqVO updateReqVO);

    /**
     * 删除库存管理
     *
     * @param id 编号
     */
    void deleteAssetStock(Long id);

    /**
    * 批量删除库存管理
    *
    * @param ids 编号
    */
    void deleteAssetStockListByIds(List<Long> ids);

    /**
     * 获得库存管理
     *
     * @param id 编号
     * @return 库存管理
     */
    AssetStockDO getAssetStock(Long id);

    /**
     * 获得库存管理分页
     *
     * @param pageReqVO 分页查询
     * @return 库存管理分页
     */
    PageResult<AssetStockRespVO> getAssetStockPage(AssetStockPageReqVO pageReqVO);

    /**
     * 调配库存
     *
     * @param allocateReqVO 调配信息
     */
    void allocateAssetStock(@Valid AssetStockAllocateReqVO allocateReqVO);

    /**
     * 更新库存告警状态
     *
     * @param alarmReqVO 告警信息
     */
    void alarmAssetStock(@Valid AssetStockAlarmReqVO alarmReqVO);

    /**
     * 获得库存统计图表
     *
     * @param reqVO 查询条件
     * @return 图表统计结果
     */
    AssetStockChartRespVO getAssetStockChart(AssetStockChartReqVO reqVO);
}