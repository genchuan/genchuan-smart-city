package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetclientattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产客户端属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetClientAttrCfgServiceImpl implements AssetClientAttrCfgService {

    @Resource
    private AssetClientAttrCfgMapper assetClientAttrCfgMapper;

    @Override
    public Long createAssetClientAttrCfg(AssetClientAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetClientAttrCfgDO assetClientAttrCfg = BeanUtils.toBean(createReqVO, AssetClientAttrCfgDO.class);
        assetClientAttrCfgMapper.insert(assetClientAttrCfg);
        // 返回
        return assetClientAttrCfg.getId();
    }

    @Override
    public void updateAssetClientAttrCfg(AssetClientAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetClientAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetClientAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetClientAttrCfgDO.class);
        assetClientAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetClientAttrCfg(Long id) {
        // 校验存在
        validateAssetClientAttrCfgExists(id);
        // 删除
        assetClientAttrCfgMapper.deleteById(id);
    }

    private void validateAssetClientAttrCfgExists(Long id) {
        if (assetClientAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_CLIENT_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetClientAttrCfgDO getAssetClientAttrCfg(Long id) {
        return assetClientAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetClientAttrCfgDO> getAssetClientAttrCfgPage(AssetClientAttrCfgPageReqVO pageReqVO) {
        return assetClientAttrCfgMapper.selectPage(pageReqVO);
    }

}