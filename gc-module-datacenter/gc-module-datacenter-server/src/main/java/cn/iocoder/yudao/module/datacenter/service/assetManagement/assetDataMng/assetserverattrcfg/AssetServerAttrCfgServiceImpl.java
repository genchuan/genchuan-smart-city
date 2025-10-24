package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetserverattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产服务端属性配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetServerAttrCfgServiceImpl implements AssetServerAttrCfgService {

    @Resource
    private AssetServerAttrCfgMapper assetServerAttrCfgMapper;

    @Override
    public Long createAssetServerAttrCfg(AssetServerAttrCfgSaveReqVO createReqVO) {
        // 插入
        AssetServerAttrCfgDO assetServerAttrCfg = BeanUtils.toBean(createReqVO, AssetServerAttrCfgDO.class);
        assetServerAttrCfgMapper.insert(assetServerAttrCfg);
        // 返回
        return assetServerAttrCfg.getId();
    }

    @Override
    public void updateAssetServerAttrCfg(AssetServerAttrCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetServerAttrCfgExists(updateReqVO.getId());
        // 更新
        AssetServerAttrCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetServerAttrCfgDO.class);
        assetServerAttrCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetServerAttrCfg(Long id) {
        // 校验存在
        validateAssetServerAttrCfgExists(id);
        // 删除
        assetServerAttrCfgMapper.deleteById(id);
    }

    private void validateAssetServerAttrCfgExists(Long id) {
        if (assetServerAttrCfgMapper.selectById(id) == null) {
            throw exception(ASSET_SERVER_ATTR_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetServerAttrCfgDO getAssetServerAttrCfg(Long id) {
        return assetServerAttrCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetServerAttrCfgDO> getAssetServerAttrCfgPage(AssetServerAttrCfgPageReqVO pageReqVO) {
        return assetServerAttrCfgMapper.selectPage(pageReqVO);
    }

}