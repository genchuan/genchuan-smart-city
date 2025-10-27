package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetcatrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分类规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetCatRuleCfgServiceImpl implements AssetCatRuleCfgService {

    @Resource
    private AssetCatRuleCfgMapper assetCatRuleCfgMapper;

    @Override
    public Long createAssetCatRuleCfg(AssetCatRuleCfgSaveReqVO createReqVO) {
        // 插入
        AssetCatRuleCfgDO assetCatRuleCfg = BeanUtils.toBean(createReqVO, AssetCatRuleCfgDO.class);
        assetCatRuleCfgMapper.insert(assetCatRuleCfg);
        // 返回
        return assetCatRuleCfg.getId();
    }

    @Override
    public void updateAssetCatRuleCfg(AssetCatRuleCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetCatRuleCfgExists(updateReqVO.getId());
        // 更新
        AssetCatRuleCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetCatRuleCfgDO.class);
        assetCatRuleCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCatRuleCfg(Long id) {
        // 校验存在
        validateAssetCatRuleCfgExists(id);
        // 删除
        assetCatRuleCfgMapper.deleteById(id);
    }

    private void validateAssetCatRuleCfgExists(Long id) {
        if (assetCatRuleCfgMapper.selectById(id) == null) {
            throw exception(ASSET_CAT_RULE_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetCatRuleCfgDO getAssetCatRuleCfg(Long id) {
        return assetCatRuleCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetCatRuleCfgDO> getAssetCatRuleCfgPage(AssetCatRuleCfgPageReqVO pageReqVO) {
        return assetCatRuleCfgMapper.selectPage(pageReqVO);
    }

}