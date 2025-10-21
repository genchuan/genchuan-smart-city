package cn.iocoder.yudao.module.datacenter.service.assetattrrule;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetattrrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetattrrule.AssetAttrRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetattrrule.AssetAttrRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产属性规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetAttrRuleServiceImpl implements AssetAttrRuleService {

    @Resource
    private AssetAttrRuleMapper assetAttrRuleMapper;

    @Override
    public Long createAssetAttrRule(AssetAttrRuleSaveReqVO createReqVO) {
        // 校验父级编号的有效性
        validateParentAssetAttrRule(null, createReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetAttrRuleNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        AssetAttrRuleDO assetAttrRule = BeanUtils.toBean(createReqVO, AssetAttrRuleDO.class);
        assetAttrRuleMapper.insert(assetAttrRule);
        // 返回
        return assetAttrRule.getId();
    }

    @Override
    public void updateAssetAttrRule(AssetAttrRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetAttrRuleExists(updateReqVO.getId());
        // 校验父级编号的有效性
        validateParentAssetAttrRule(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetAttrRuleNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        AssetAttrRuleDO updateObj = BeanUtils.toBean(updateReqVO, AssetAttrRuleDO.class);
        assetAttrRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetAttrRule(Long id) {
        // 校验存在
        validateAssetAttrRuleExists(id);
        // 校验是否有子资产属性规则配置
        if (assetAttrRuleMapper.selectCountByParentId(id) > 0) {
            throw exception(ASSET_ATTR_RULE_EXITS_CHILDREN);
        }
        // 删除
        assetAttrRuleMapper.deleteById(id);
    }

    private void validateAssetAttrRuleExists(Long id) {
        if (assetAttrRuleMapper.selectById(id) == null) {
            throw exception(ASSET_ATTR_RULE_NOT_EXISTS);
        }
    }

    private void validateParentAssetAttrRule(Long id, Long parentId) {
        if (parentId == null || AssetAttrRuleDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父资产属性规则配置
        if (Objects.equals(id, parentId)) {
            throw exception(ASSET_ATTR_RULE_PARENT_ERROR);
        }
        // 2. 父资产属性规则配置不存在
        AssetAttrRuleDO parentAssetAttrRule = assetAttrRuleMapper.selectById(parentId);
        if (parentAssetAttrRule == null) {
            throw exception(ASSET_ATTR_RULE_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父资产属性规则配置，如果父资产属性规则配置是自己的子资产属性规则配置，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentAssetAttrRule.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(ASSET_ATTR_RULE_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父资产属性规则配置
            if (parentId == null || AssetAttrRuleDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentAssetAttrRule = assetAttrRuleMapper.selectById(parentId);
            if (parentAssetAttrRule == null) {
                break;
            }
        }
    }

    private void validateAssetAttrRuleNameUnique(Long id, Long parentId, String name) {
        AssetAttrRuleDO assetAttrRule = assetAttrRuleMapper.selectByParentIdAndName(parentId, name);
        if (assetAttrRule == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资产属性规则配置
        if (id == null) {
            throw exception(ASSET_ATTR_RULE_NAME_DUPLICATE);
        }
        if (!Objects.equals(assetAttrRule.getId(), id)) {
            throw exception(ASSET_ATTR_RULE_NAME_DUPLICATE);
        }
    }

    @Override
    public AssetAttrRuleDO getAssetAttrRule(Long id) {
        return assetAttrRuleMapper.selectById(id);
    }

    @Override
    public List<AssetAttrRuleDO> getAssetAttrRuleList(AssetAttrRuleListReqVO listReqVO) {
        return assetAttrRuleMapper.selectList(listReqVO);
    }

}