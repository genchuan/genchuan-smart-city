package cn.iocoder.yudao.module.inspectop.service.assetcheck;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetcheck.AssetCheckDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 资产盘点 Service 接口
 *
 * @author zhucongquan
 */
public interface AssetCheckService {

    /**
     * 创建资产盘点
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCheck(@Valid AssetCheckSaveReqVO createReqVO);

    /**
     * 更新资产盘点
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCheck(@Valid AssetCheckSaveReqVO updateReqVO);

    /**
     * 删除资产盘点
     *
     * @param id 编号
     */
    void deleteAssetCheck(Long id);

    /**
    * 批量删除资产盘点
    *
    * @param ids 编号
    */
    void deleteAssetCheckListByIds(List<Long> ids);

    /**
     * 获得资产盘点
     *
     * @param id 编号
     * @return 资产盘点
     */
    AssetCheckDO getAssetCheck(Long id);

    /**
     * 获得资产盘点分页
     *
     * @param pageReqVO 分页查询
     * @return 资产盘点分页
     */
    PageResult<AssetCheckDO> getAssetCheckPage(AssetCheckPageReqVO pageReqVO);

    /**
     * 创建资产盘点
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCheck(@Valid AssetCheckCreateReqVO createReqVO);

    /**
     * 执行资产盘点
     *
     * @param executeReqVO 执行信息
     */
    void executeAssetCheck(@Valid AssetCheckExecuteReqVO executeReqVO);

    /**
     * 更新资产盘点进度
     *
     * @param updateProgressReqVO 更新进度信息
     */
    void updateAssetCheckProgress(@Valid AssetCheckUpdateProgressReqVO updateProgressReqVO);

    /**
     * 确认资产盘点
     *
     * @param confirmReqVO 确认信息
     */
    void confirmAssetCheck(@Valid AssetCheckConfirmReqVO confirmReqVO);

    /**
     * 获取资产盘点图表统计数据
     *
     * @param chartReqVO 图表统计查询条件
     * @return 图表统计数据
     */
    AssetCheckChartRespVO getAssetCheckChart(@Valid AssetCheckChartReqVO chartReqVO);
}