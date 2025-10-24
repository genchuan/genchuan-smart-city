package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetshareattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产共享属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetShareAttrCfgServiceImpl implements AssetShareAttrCfgService {

    @Resource
    private AssetShareAttrCfgMapper assetShareAttrCfgMapper;

    @Override
    public Long createAssetShareAttrCfg(AssetShareAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetShareAttrCfgDO assetShareAttrCfg = BeanUtils.toBean(createReqVO, AssetShareAttrCfgDO.class);
        assetShareAttrCfgMapper.insert(assetShareAttrCfg);
        // 返回
        return assetShareAttrCfg.getId();
    }

    @Override
    public void updateAssetShareAttrCfg(AssetShareAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetShareAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetShareAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetShareAttrCfgDO.class);
        assetShareAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetShareAttrCfg(Long id) {
        // 校验存在
        validateAssetShareAttrCfgExists(id);
        // 删除
        assetShareAttrCfgMapper.deleteById(id);
    }

    private void validateAssetShareAttrCfgExists(Long id) {
        if (assetShareAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_SHARE_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetShareAttrCfgDO getAssetShareAttrCfg(Long id) {
        return assetShareAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetShareAttrCfgDO> getAssetShareAttrCfgPage(AssetShareAttrCfgPageReqVO pageReqVO) {
        return assetShareAttrCfgMapper.selectPage(pageReqVO);
    }

}