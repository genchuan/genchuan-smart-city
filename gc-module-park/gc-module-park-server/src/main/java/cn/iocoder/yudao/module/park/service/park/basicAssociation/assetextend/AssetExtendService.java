package cn.iocoder.yudao.module.park.service.park.basicAssociation.assetextend;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.assetextend.AssetExtendDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产扩展 Service 接口
 *
 * @author zhucongquan
 */
public interface AssetExtendService {

    /**
     * 创建资产扩展
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetExtend(@Valid AssetExtendSaveReqVO createReqVO);

    /**
     * 更新资产扩展
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetExtend(@Valid AssetExtendSaveReqVO updateReqVO);

    /**
     * 删除资产扩展
     *
     * @param id 编号
     */
    void deleteAssetExtend(Long id);

    /**
     * 获得资产扩展
     *
     * @param id 编号
     * @return 资产扩展
     */
    AssetExtendDO getAssetExtend(Long id);

    /**
     * 获得资产扩展分页
     *
     * @param pageReqVO 分页查询
     * @return 资产扩展分页
     */
    PageResult<AssetExtendDO> getAssetExtendPage(AssetExtendPageReqVO pageReqVO);

}