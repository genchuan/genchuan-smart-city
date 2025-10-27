package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetarea;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 资产关联行政区划 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetAreaService {

    /**
     * 创建资产关联行政区划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetArea(@Valid AssetAreaSaveReqVO createReqVO);

    /**
     * 更新资产关联行政区划
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetArea(@Valid AssetAreaSaveReqVO updateReqVO);

    /**
     * 删除资产关联行政区划
     *
     * @param id 编号
     */
    void deleteAssetArea(Long id);

    /**
     * 获得资产关联行政区划
     *
     * @param id 编号
     * @return 资产关联行政区划
     */
    AssetAreaDO getAssetArea(Long id);

    /**
     * 获得资产关联行政区划分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联行政区划分页
     */
    PageResult<AssetAreaDO> getAssetAreaPage(AssetAreaPageReqVO pageReqVO);

}