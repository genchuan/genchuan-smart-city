package cn.iocoder.yudao.module.park.service.park.basicAssociation.asset;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.asset.AssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.asset.AssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 资产-thingsboard Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Override
    public Long createAsset(AssetSaveReqVO createReqVO) {
        // 插入
        AssetDO asset = BeanUtils.toBean(createReqVO, AssetDO.class);
        assetMapper.insert(asset);
        // 返回
        return asset.getId();
    }

    @Override
    public void updateAsset(AssetSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetExists(updateReqVO.getId());
        // 更新
        AssetDO updateObj = BeanUtils.toBean(updateReqVO, AssetDO.class);
        assetMapper.updateById(updateObj);
    }

    @Override
    public void deleteAsset(Long id) {
        // 校验存在
        validateAssetExists(id);
        // 删除
        assetMapper.deleteById(id);
    }

    private void validateAssetExists(Long id) {
        if (assetMapper.selectById(id) == null) {
            throw exception(ASSET_NOT_EXISTS);
        }
    }

    @Override
    public AssetDO getAsset(Long id) {
        return assetMapper.selectById(id);
    }

    @Override
    public PageResult<AssetDO> getAssetPage(AssetPageReqVO pageReqVO) {
        return assetMapper.selectPage(pageReqVO);
    }

}