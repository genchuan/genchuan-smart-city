package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产数据质量检查 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetDataQualCkService {

    /**
     * 创建资产数据质量检查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetDataQualCk(@Valid AssetDataQualCkSaveReqVO createReqVO);

    /**
     * 更新资产数据质量检查
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetDataQualCk(@Valid AssetDataQualCkSaveReqVO updateReqVO);

    /**
     * 删除资产数据质量检查
     *
     * @param id 编号
     */
    void deleteAssetDataQualCk(Long id);

    /**
     * 获得资产数据质量检查
     *
     * @param id 编号
     * @return 资产数据质量检查
     */
    AssetDataQualCkDO getAssetDataQualCk(Long id);

    /**
     * 获得资产数据质量检查分页
     *
     * @param pageReqVO 分页查询
     * @return 资产数据质量检查分页
     */
    PageResult<AssetDataQualCkDO> getAssetDataQualCkPage(AssetDataQualCkPageReqVO pageReqVO);

}