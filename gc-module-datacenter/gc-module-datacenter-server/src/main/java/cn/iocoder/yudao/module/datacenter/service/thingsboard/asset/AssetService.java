package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetDetailRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSimpleRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.asset.AssetDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

/**
 * 资产 Service 接口
 *
 * @author 芋道源码
 */
public interface AssetService {

    /**
     * 创建资产信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAsset(@Valid AssetSaveReqVO createReqVO);

    /**
     * 更新资产信息
     *
     * @param updateReqVO 更新信息
     */
    void updateAsset(@Valid AssetSaveReqVO updateReqVO);

    /**
     * 删除资产信息
     *
     * @param id 编号
     */
    void deleteAsset(Long id);

    /**
     * 获得资产信息
     *
     * @param id 编号
     * @return 资产信息
     */
    AssetDO getAsset(Long id);

    /**
     * 获得资产信息分页
     *
     * @param pageReqVO 分页查询
     * @return 资产信息分页
     */
    PageResult<AssetDO> getAssetPage(AssetPageReqVO pageReqVO);


    PageResult<AssetDetailRespVO> getAssetPage1(Integer pageSize, Integer page);

    List<AssetSimpleRespVO> getAssetList();

    /**
     * 同步ThingsBoard资产到本地数据库
     *
     * @return 同步结果信息
     */
    Map<String, Object> syncAssetsFromThingsBoard();


    /**
     * 获取资产配置分页列表
     *
     * @param pageSize 每页大小
     * @param page 页码
     * @param sortProperty 排序字段
     * @param sortOrder 排序方向
     * @return 资产配置分页数据
     */
    PageData<AssetProfile> getAssetProfiles(Integer pageSize, Integer page, String sortProperty, String sortOrder);

    /**
     * 添加资产属性
     * @param assetId 资产ID
     * @param attributes 属性键值对
     */
    void addAssetAttributes(String assetId, Map<String, Object> attributes);

    /**
     * 删除资产属性
     * @param assetId 资产ID
     * @param scope 属性作用域
     * @param keys 要删除的属性键列表
     */
    void deleteAssetAttributes(String assetId, String scope, List<String> keys);
}