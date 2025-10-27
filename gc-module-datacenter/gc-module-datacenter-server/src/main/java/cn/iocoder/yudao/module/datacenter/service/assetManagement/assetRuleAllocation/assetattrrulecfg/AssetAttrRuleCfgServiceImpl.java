package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetattrrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产属性规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetAttrRuleCfgServiceImpl implements AssetAttrRuleCfgService {

    @Resource
    private AssetAttrRuleCfgMapper assetAttrRuleCfgMapper;

    @Override
    public Long createAssetAttrRuleCfg(AssetAttrRuleCfgSaveReqVO createReqVO) {
        // 插入
        AssetAttrRuleCfgDO assetAttrRuleCfg = BeanUtils.toBean(createReqVO, AssetAttrRuleCfgDO.class);
        assetAttrRuleCfgMapper.insert(assetAttrRuleCfg);
        // 返回
        return assetAttrRuleCfg.getId();
    }

    @Override
    public void updateAssetAttrRuleCfg(AssetAttrRuleCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetAttrRuleCfgExists(updateReqVO.getId());
        // 更新
        AssetAttrRuleCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetAttrRuleCfgDO.class);
        assetAttrRuleCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetAttrRuleCfg(Long id) {
        // 校验存在
        validateAssetAttrRuleCfgExists(id);
        // 删除
        assetAttrRuleCfgMapper.deleteById(id);
    }

    private void validateAssetAttrRuleCfgExists(Long id) {
        if (assetAttrRuleCfgMapper.selectById(id) == null) {
            throw exception(ASSET_ATTR_RULE_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetAttrRuleCfgDO getAssetAttrRuleCfg(Long id) {
        return assetAttrRuleCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetAttrRuleCfgDO> getAssetAttrRuleCfgPage(AssetAttrRuleCfgPageReqVO pageReqVO) {
        return assetAttrRuleCfgMapper.selectPage(pageReqVO);
    }

}