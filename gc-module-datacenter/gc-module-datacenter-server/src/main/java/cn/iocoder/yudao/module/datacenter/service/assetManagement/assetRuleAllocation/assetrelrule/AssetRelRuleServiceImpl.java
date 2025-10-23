package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetrelrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo.AssetRelRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo.AssetRelRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrule.AssetRelRuleDO;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetrelrule.AssetRelRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产关联规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetRelRuleServiceImpl implements AssetRelRuleService {

    @Resource
    private AssetRelRuleMapper assetRelRuleMapper;

    @Override
    public Long createAssetRelRule(AssetRelRuleSaveReqVO createReqVO) {
        // 校验父级编号的有效性
        validateParentAssetRelRule(null, createReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetRelRuleNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        AssetRelRuleDO assetRelRule = BeanUtils.toBean(createReqVO, AssetRelRuleDO.class);
        assetRelRuleMapper.insert(assetRelRule);
        // 返回
        return assetRelRule.getId();
    }

    @Override
    public void updateAssetRelRule(AssetRelRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetRelRuleExists(updateReqVO.getId());
        // 校验父级编号的有效性
        validateParentAssetRelRule(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetRelRuleNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        AssetRelRuleDO updateObj = BeanUtils.toBean(updateReqVO, AssetRelRuleDO.class);
        assetRelRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetRelRule(Long id) {
        // 校验存在
        validateAssetRelRuleExists(id);
        // 校验是否有子资产关联规则配置
        if (assetRelRuleMapper.selectCountByParentId(id) > 0) {
            throw exception(ASSET_REL_RULE_EXITS_CHILDREN);
        }
        // 删除
        assetRelRuleMapper.deleteById(id);
    }

    private void validateAssetRelRuleExists(Long id) {
        if (assetRelRuleMapper.selectById(id) == null) {
            throw exception(ASSET_REL_RULE_NOT_EXISTS);
        }
    }

    private void validateParentAssetRelRule(Long id, Long parentId) {
        if (parentId == null || AssetRelRuleDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父资产关联规则配置
        if (Objects.equals(id, parentId)) {
            throw exception(ASSET_REL_RULE_PARENT_ERROR);
        }
        // 2. 父资产关联规则配置不存在
        AssetRelRuleDO parentAssetRelRule = assetRelRuleMapper.selectById(parentId);
        if (parentAssetRelRule == null) {
            throw exception(ASSET_REL_RULE_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父资产关联规则配置，如果父资产关联规则配置是自己的子资产关联规则配置，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentAssetRelRule.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(ASSET_REL_RULE_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父资产关联规则配置
            if (parentId == null || AssetRelRuleDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentAssetRelRule = assetRelRuleMapper.selectById(parentId);
            if (parentAssetRelRule == null) {
                break;
            }
        }
    }

    private void validateAssetRelRuleNameUnique(Long id, Long parentId, String name) {
        AssetRelRuleDO assetRelRule = assetRelRuleMapper.selectByParentIdAndName(parentId, name);
        if (assetRelRule == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资产关联规则配置
        if (id == null) {
            throw exception(ASSET_REL_RULE_NAME_DUPLICATE);
        }
        if (!Objects.equals(assetRelRule.getId(), id)) {
            throw exception(ASSET_REL_RULE_NAME_DUPLICATE);
        }
    }

    @Override
    public AssetRelRuleDO getAssetRelRule(Long id) {
        return assetRelRuleMapper.selectById(id);
    }

    @Override
    public List<AssetRelRuleDO> getAssetRelRuleList(AssetRelRuleListReqVO listReqVO) {
        return assetRelRuleMapper.selectList(listReqVO);
    }

}