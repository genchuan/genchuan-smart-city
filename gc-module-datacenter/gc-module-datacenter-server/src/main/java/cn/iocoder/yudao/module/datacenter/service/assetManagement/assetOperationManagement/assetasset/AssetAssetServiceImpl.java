package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetasset.AssetAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetasset.AssetAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产关联资产 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetAssetServiceImpl implements AssetAssetService {

    @Resource
    private AssetAssetMapper assetAssetMapper;

    @Override
    public Long createAssetAsset(AssetAssetSaveReqVO createReqVO) {
        // 插入
        AssetAssetDO assetAsset = BeanUtils.toBean(createReqVO, AssetAssetDO.class);
        assetAssetMapper.insert(assetAsset);
        // 返回
        return assetAsset.getId();
    }

    @Override
    public void updateAssetAsset(AssetAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetAssetExists(updateReqVO.getId());
        // 更新
        AssetAssetDO updateObj = BeanUtils.toBean(updateReqVO, AssetAssetDO.class);
        assetAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetAsset(Long id) {
        // 校验存在
        validateAssetAssetExists(id);
        // 删除
        assetAssetMapper.deleteById(id);
    }

    private void validateAssetAssetExists(Long id) {
        if (assetAssetMapper.selectById(id) == null) {
            throw exception(ASSET_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public AssetAssetDO getAssetAsset(Long id) {
        return assetAssetMapper.selectById(id);
    }

    @Override
    public PageResult<AssetAssetDO> getAssetAssetPage(AssetAssetPageReqVO pageReqVO) {
        return assetAssetMapper.selectPage(pageReqVO);
    }

}