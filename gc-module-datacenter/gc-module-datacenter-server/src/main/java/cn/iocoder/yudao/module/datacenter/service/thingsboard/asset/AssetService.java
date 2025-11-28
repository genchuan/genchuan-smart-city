package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSimpleRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;

/**
 * 资产 Service 接口
 *
 * @author 芋道源码
 */
public interface AssetService {

    /**
     * 创建资产
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createAsset(@Valid AssetSaveReqVO createReqVO);

    /**
     * 更新资产
     *
     * @param updateReqVO 更新信息
     */
    void updateAsset(@Valid AssetSaveReqVO updateReqVO);

    /**
     * 删除资产
     *
     * @param id 编号
     */
    void deleteAsset(String id);

    /**
     * 批量删除资产
     *
     * @param ids 编号
     */
    void deleteAssetListByIds(List<String> ids);

    /**
     * 获得资产
     *
     * @param id 编号
     * @return 资产
     */
    AssetInfo getAsset(String id);

    /**
     * 获得资产分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分页
     */
    PageResult<Asset> getAssetPage(AssetPageReqVO pageReqVO);

    /**
     *
     * @return 资产简单数据
     */
    List<AssetSimpleRespVO> getAssetList();
}