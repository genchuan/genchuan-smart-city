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
    PageResult<AssetStockDO> getAssetStockPage(AssetStockPageReqVO pageReqVO);

}