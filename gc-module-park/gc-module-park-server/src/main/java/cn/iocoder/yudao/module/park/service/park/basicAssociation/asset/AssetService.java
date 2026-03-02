package cn.iocoder.yudao.module.park.service.park.basicAssociation.asset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.asset.AssetDO;
import jakarta.validation.Valid;

/**
 * 资产-thingsboard Service 接口
 *
 * @author zhucongquan
 */
public interface AssetService {

    /**
     * 创建资产-thingsboard
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAsset(@Valid AssetSaveReqVO createReqVO);

    /**
     * 更新资产-thingsboard
     *
     * @param updateReqVO 更新信息
     */
    void updateAsset(@Valid AssetSaveReqVO updateReqVO);

    /**
     * 删除资产-thingsboard
     *
     * @param id 编号
     */
    void deleteAsset(Long id);

    /**
     * 获得资产-thingsboard
     *
     * @param id 编号
     * @return 资产-thingsboard
     */
    AssetDO getAsset(Long id);

    /**
     * 获得资产-thingsboard分页
     *
     * @param pageReqVO 分页查询
     * @return 资产-thingsboard分页
     */
    PageResult<AssetDO> getAssetPage(AssetPageReqVO pageReqVO);

}
