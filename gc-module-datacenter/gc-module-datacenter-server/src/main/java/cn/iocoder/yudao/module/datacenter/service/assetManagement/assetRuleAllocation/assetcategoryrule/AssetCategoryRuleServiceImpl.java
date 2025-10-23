package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetcategoryrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcategoryrule.vo.AssetCategoryRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcategoryrule.vo.AssetCategoryRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcategoryrule.AssetCategoryRuleDO;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetcategoryrule.AssetCategoryRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分类规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetCategoryRuleServiceImpl implements AssetCategoryRuleService {

    @Resource
    private AssetCategoryRuleMapper assetCategoryRuleMapper;

    @Override
    public Long createAssetCategoryRule(AssetCategoryRuleSaveReqVO createReqVO) {
        // 校验父级编号的有效性
        validateParentAssetCategoryRule(null, createReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetCategoryRuleNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        AssetCategoryRuleDO assetCategoryRule = BeanUtils.toBean(createReqVO, AssetCategoryRuleDO.class);
        assetCategoryRuleMapper.insert(assetCategoryRule);
        // 返回
        return assetCategoryRule.getId();
    }

    @Override
    public void updateAssetCategoryRule(AssetCategoryRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetCategoryRuleExists(updateReqVO.getId());
        // 校验父级编号的有效性
        validateParentAssetCategoryRule(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetCategoryRuleNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        AssetCategoryRuleDO updateObj = BeanUtils.toBean(updateReqVO, AssetCategoryRuleDO.class);
        assetCategoryRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCategoryRule(Long id) {
        // 校验存在
        validateAssetCategoryRuleExists(id);
        // 校验是否有子资产分类规则配置
        if (assetCategoryRuleMapper.selectCountByParentId(id) > 0) {
            throw exception(ASSET_CATEGORY_RULE_EXITS_CHILDREN);
        }
        // 删除
        assetCategoryRuleMapper.deleteById(id);
    }

    private void validateAssetCategoryRuleExists(Long id) {
        if (assetCategoryRuleMapper.selectById(id) == null) {
            throw exception(ASSET_CATEGORY_RULE_NOT_EXISTS);
        }
    }

    private void validateParentAssetCategoryRule(Long id, Long parentId) {
        if (parentId == null || AssetCategoryRuleDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父资产分类规则配置
        if (Objects.equals(id, parentId)) {
            throw exception(ASSET_CATEGORY_RULE_PARENT_ERROR);
        }
        // 2. 父资产分类规则配置不存在
        AssetCategoryRuleDO parentAssetCategoryRule = assetCategoryRuleMapper.selectById(parentId);
        if (parentAssetCategoryRule == null) {
            throw exception(ASSET_CATEGORY_RULE_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父资产分类规则配置，如果父资产分类规则配置是自己的子资产分类规则配置，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentAssetCategoryRule.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(ASSET_CATEGORY_RULE_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父资产分类规则配置
            if (parentId == null || AssetCategoryRuleDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentAssetCategoryRule = assetCategoryRuleMapper.selectById(parentId);
            if (parentAssetCategoryRule == null) {
                break;
            }
        }
    }

    private void validateAssetCategoryRuleNameUnique(Long id, Long parentId, String name) {
        AssetCategoryRuleDO assetCategoryRule = assetCategoryRuleMapper.selectByParentIdAndName(parentId, name);
        if (assetCategoryRule == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资产分类规则配置
        if (id == null) {
            throw exception(ASSET_CATEGORY_RULE_NAME_DUPLICATE);
        }
        if (!Objects.equals(assetCategoryRule.getId(), id)) {
            throw exception(ASSET_CATEGORY_RULE_NAME_DUPLICATE);
        }
    }

    @Override
    public AssetCategoryRuleDO getAssetCategoryRule(Long id) {
        return assetCategoryRuleMapper.selectById(id);
    }

    @Override
    public List<AssetCategoryRuleDO> getAssetCategoryRuleList(AssetCategoryRuleListReqVO listReqVO) {
        return assetCategoryRuleMapper.selectList(listReqVO);
    }

}