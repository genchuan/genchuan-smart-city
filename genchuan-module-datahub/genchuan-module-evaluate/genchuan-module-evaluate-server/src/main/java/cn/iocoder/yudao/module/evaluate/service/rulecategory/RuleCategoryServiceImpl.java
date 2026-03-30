package cn.iocoder.yudao.module.evaluate.service.rulecategory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveFullReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail.RuleDetailMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_CATEGORY_NOT_EXISTS;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_CATEGORY_NAME_DUPLICATE;

/**
 * 规则分类管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RuleCategoryServiceImpl implements RuleCategoryService {

    @Resource
    private RuleCategoryMapper ruleCategoryMapper;

    @Lazy
    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper commentRuleMapper;

    @Lazy
    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail.RuleDetailMapper ruleDetailMapper;

    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.ruleitem.RuleItemMapper ruleItemMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public Long createRuleCategory(RuleCategorySaveReqVO createReqVO) {
        // 校验名称在同一体系下唯一
        validateNameUnique(createReqVO.getName(), createReqVO.getSystemId(), null);

        // 插入
        RuleCategoryDO ruleCategory = BeanUtils.toBean(createReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.insert(ruleCategory);
        // 返回
        return ruleCategory.getId();
    }

    @Override
    public void updateRuleCategory(RuleCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateRuleCategoryExists(updateReqVO.getId());
        // 校验名称在同一体系下唯一
        validateNameUnique(updateReqVO.getName(), updateReqVO.getSystemId(), updateReqVO.getId());
        // 更新
        RuleCategoryDO updateObj = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.updateById(updateObj);
    }

    private void validateNameUnique(String name, String systemId, Long excludeId) {
        if (name == null || systemId == null) {
            return;
        }
        RuleCategoryDO existing = ruleCategoryMapper.selectByNameAndSystemId(name, systemId);
        if (existing != null && !existing.getId().equals(excludeId)) {
            throw exception(RULE_CATEGORY_NAME_DUPLICATE);
        }
    }

    @Override
    public void deleteRuleCategory(Long id) {
        // 校验存在
        validateRuleCategoryExists(id);
        // 删除
        ruleCategoryMapper.deleteById(id);
    }

    private void validateRuleCategoryExists(Long id) {
        if (ruleCategoryMapper.selectById(id) == null) {
            throw exception(RULE_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public RuleCategoryDO getRuleCategory(Long id) {
        return ruleCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<RuleCategoryDO> getRuleCategoryPage(RuleCategoryPageReqVO pageReqVO) {
        return ruleCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RuleCategoryRespVO> getRuleCategoryJoinPage(RuleCategoryPageReqVO pageReqVO) {
        // 1. 查询分页数据
        PageResult<RuleCategoryRespVO> pageResult = ruleCategoryMapper.selectJoinPage(pageReqVO);

        // 如果没有数据，直接返回
        if (pageResult == null || pageResult.getList() == null || pageResult.getList().isEmpty()) {
            return pageResult;
        }

        // ========== 提取所有关联ID，用于批量查询 ==========
        // 使用主键ID进行查询
        Set<Long> systemIdPks = new HashSet<>();
        Set<Long> statusIdPks = new HashSet<>();
        Set<Long> itemIdPks = new HashSet<>();
        Set<Long> ruleIdPks = new HashSet<>();
        Set<Long> objectTypeIdPks = new HashSet<>();
        Set<Long> userIds = new HashSet<>();

        pageResult.getList().forEach(vo -> {
            // 体系主键ID
            if (vo.getSystemIdPk() != null) {
                systemIdPks.add(vo.getSystemIdPk());
            }
            // 状态主键ID
            if (vo.getStatusIdPk() != null) {
                statusIdPks.add(vo.getStatusIdPk());
            }
            // 指标项主键ID
            if (vo.getItemIdPk() != null) {
                itemIdPks.add(vo.getItemIdPk());
            }
            // 规则类型主键ID
            if (vo.getRuleIdPk() != null) {
                ruleIdPks.add(vo.getRuleIdPk());
            }
            // 对象类型主键ID
            if (vo.getObjectTypeIdPk() != null) {
                objectTypeIdPks.add(vo.getObjectTypeIdPk());
            }
            // 创建人
            String creatorStr = String.valueOf(vo.getCreator());
            if (StrUtil.isNotBlank(creatorStr) && NumberUtil.isNumber(creatorStr)) {
                userIds.add(Long.valueOf(creatorStr));
            }
            // 更新人
            String updaterStr = String.valueOf(vo.getUpdater());
            if (StrUtil.isNotBlank(updaterStr) && NumberUtil.isNumber(updaterStr)) {
                userIds.add(Long.valueOf(updaterStr));
            }
        });

        // ========== 批量查询关联名称 ==========
        // 1. 批量查询体系名称
        Map<Long, String> systemNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(systemIdPks)) {
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO> systems =
                    ruleCategoryMapper.selectSystemByIds(new ArrayList<>(systemIdPks));
            if (systems != null) {
                systemNameMap = systems.stream()
                        .collect(Collectors.toMap(
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO::getName,
                                (a, b) -> a));
            }
        }

        // 2. 批量查询状态名称
        Map<Long, String> statusNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(statusIdPks)) {
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO> statuses =
                    ruleCategoryMapper.selectStatusByIds(new ArrayList<>(statusIdPks));
            if (statuses != null) {
                statusNameMap = statuses.stream()
                        .collect(Collectors.toMap(
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO::getName,
                                (a, b) -> a));
            }
        }

        // 3. 批量查询指标项名称
        Map<Long, String> itemNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(itemIdPks)) {
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO> items =
                    ruleCategoryMapper.selectItemByIds(new ArrayList<>(itemIdPks));
            if (items != null) {
                itemNameMap = items.stream()
                        .collect(Collectors.toMap(
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO::getName,
                                (a, b) -> a));
            }
        }

        // 4. 批量查询规则类型名称
        Map<Long, String> ruleNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(ruleIdPks)) {
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO> ruleTypes =
                    ruleCategoryMapper.selectRuleTypeByIds(new ArrayList<>(ruleIdPks));
            if (ruleTypes != null) {
                ruleNameMap = ruleTypes.stream()
                        .collect(Collectors.toMap(
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO::getName,
                                (a, b) -> a));
            }
        }

        // 5. 批量查询对象类型名称
        Map<Long, String> objectTypeNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(objectTypeIdPks)) {
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO> objectTypes =
                    ruleCategoryMapper.selectObjectTypeByIds(new ArrayList<>(objectTypeIdPks));
            if (objectTypes != null) {
                objectTypeNameMap = objectTypes.stream()
                        .collect(Collectors.toMap(
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO::getName,
                                (a, b) -> a));
            }
        }

        // 6. 批量查询用户信息
        Map<Long, cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO> userMap =
                CollUtil.isNotEmpty(userIds) ? adminUserApi.getUserMap(userIds) : new HashMap<>();

        // ========== 统计每个规则分类下的规则项数量 ==========
        List<String> categoryIds = pageResult.getList().stream()
                .map(vo -> String.valueOf(vo.getId()))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<String, Long> itemCountMap = new HashMap<>();
        if (CollUtil.isNotEmpty(categoryIds)) {
            cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> countWrapper
                    = new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO>()
                    .in(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId, categoryIds)
                    .eq(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getDeleted, false);

            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> ruleItems =
                    ruleItemMapper.selectList(countWrapper);

            itemCountMap = ruleItems.stream()
                    .collect(java.util.stream.Collectors.groupingBy(
                            cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId,
                            java.util.stream.Collectors.counting()));
        }

        // ========== 回填所有关联数据 ==========
        Map<Long, String> finalSystemNameMap = systemNameMap;
        Map<Long, String> finalStatusNameMap = statusNameMap;
        Map<Long, String> finalItemNameMap = itemNameMap;
        Map<Long, String> finalRuleNameMap = ruleNameMap;
        Map<Long, String> finalObjectTypeNameMap = objectTypeNameMap;
        Map<String, Long> finalItemCountMap = itemCountMap;
        pageResult.getList().forEach(vo -> {
            // 回填体系名称
            if (StrUtil.isBlank(vo.getSystemName()) && vo.getSystemIdPk() != null) {
                vo.setSystemName(finalSystemNameMap.get(vo.getSystemIdPk()));
            }
            // 回填状态名称
            if (StrUtil.isBlank(vo.getStatusName()) && vo.getStatusIdPk() != null) {
                vo.setStatusName(finalStatusNameMap.get(vo.getStatusIdPk()));
            }
            // 回填指标项名称
            if (StrUtil.isBlank(vo.getItemName()) && vo.getItemIdPk() != null) {
                vo.setItemName(finalItemNameMap.get(vo.getItemIdPk()));
            }
            // 回填规则类型名称
            if (StrUtil.isBlank(vo.getRuleName()) && vo.getRuleIdPk() != null) {
                vo.setRuleName(finalRuleNameMap.get(vo.getRuleIdPk()));
            }
            // 回填对象类型名称
            if (StrUtil.isBlank(vo.getObjectTypeName()) && vo.getObjectTypeIdPk() != null) {
                vo.setObjectTypeName(finalObjectTypeNameMap.get(vo.getObjectTypeIdPk()));
            }
            // 回填创建人姓名
            String creatorStr = String.valueOf(vo.getCreator());
            if (StrUtil.isNotBlank(creatorStr) && NumberUtil.isNumber(creatorStr)) {
                cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO creatorUser = userMap.get(Long.valueOf(creatorStr));
                if (creatorUser != null) {
                    vo.setCreateUserName(creatorUser.getNickname());
                }
            }
            // 回填更新人姓名
            String updaterStr = String.valueOf(vo.getUpdater());
            if (StrUtil.isNotBlank(updaterStr) && NumberUtil.isNumber(updaterStr)) {
                cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO updaterUser = userMap.get(Long.valueOf(updaterStr));
                if (updaterUser != null) {
                    vo.setUpdateUserName(updaterUser.getNickname());
                }
            }
            // 回填规则项数量
            Long count = finalItemCountMap.get(String.valueOf(vo.getId()));
            vo.setItemCount(count != null ? count.intValue() : 0);
        });

        return pageResult;
    }

    @Override
    public RuleCategoryRespVO getRuleCategoryTree(Long id) {
        // 1. 查询规则分类基本信息
        RuleCategoryDO ruleCategory = ruleCategoryMapper.selectById(id);
        if (ruleCategory == null) {
            throw exception(RULE_CATEGORY_NOT_EXISTS);
        }

        // 2. 转换为VO
        RuleCategoryRespVO respVO = BeanUtils.toBean(ruleCategory, RuleCategoryRespVO.class);

        // 3. 查询该分类下的所有评分规则
        List<CommentRuleDO> commentRules = commentRuleMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<CommentRuleDO>()
                        .eq(CommentRuleDO::getRuleCategoryId, id)
                        .eq(CommentRuleDO::getDeleted, false)
                        .orderByDesc(CommentRuleDO::getId)
        );

        if (CollUtil.isNotEmpty(commentRules)) {
            // 4. 转换评分规则VO
            List<CommentRuleRespVO> commentRuleVOList = commentRules.stream()
                    .map(rule -> {
                        CommentRuleRespVO ruleVO = BeanUtils.toBean(rule, CommentRuleRespVO.class);
                        // 5. 查询每个规则下的明细
                        List<RuleDetailDO> details = ruleDetailMapper.selectList(
                                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                        .eq(RuleDetailDO::getRuleId, rule.getId())
                                        .eq(RuleDetailDO::getDeleted, false)
                                        .orderByAsc(RuleDetailDO::getSortOrder)
                        );
                        if (CollUtil.isNotEmpty(details)) {
                            List<RuleDetailRespVO> detailVOList = details.stream()
                                    .map(detail -> BeanUtils.toBean(detail, RuleDetailRespVO.class))
                                    .collect(Collectors.toList());
                            ruleVO.setDetails(detailVOList);
                        }
                        return ruleVO;
                    })
                    .collect(Collectors.toList());

            respVO.setCommentRules(commentRuleVOList);
            respVO.setItemCount(commentRuleVOList.size());
        } else {
            respVO.setCommentRules(new ArrayList<>());
            respVO.setItemCount(0);
        }

        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRuleCategoryWithRules(RuleCategorySaveReqVO updateReqVO) {
        // 1. 校验存在
        validateRuleCategoryExists(updateReqVO.getId());
        // 校验名称在同一体系下唯一
        validateNameUnique(updateReqVO.getName(), updateReqVO.getSystemId(), updateReqVO.getId());

        // 2. 更新规则分类基本信息
        RuleCategoryDO updateObj = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.updateById(updateObj);

        // 3. 处理评分规则列表
        List<CommentRuleSaveReqVO> commentRules = updateReqVO.getCommentRules();
        if (CollUtil.isEmpty(commentRules)) {
            // 如果没有传规则，则删除原有的所有规则和明细
            List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                    new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<CommentRuleDO>()
                            .eq(CommentRuleDO::getRuleCategoryId, updateReqVO.getId())
                            .eq(CommentRuleDO::getDeleted, false)
            );
            if (CollUtil.isNotEmpty(existingRules)) {
                // 删除所有明细
                List<Long> ruleIds = existingRules.stream().map(CommentRuleDO::getId).collect(Collectors.toList());
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .in(RuleDetailDO::getRuleId, ruleIds)
                );
                // 删除所有规则
                commentRuleMapper.deleteByIds(ruleIds);
            }
            return;
        }

        // 4. 获取已有的规则ID列表
        List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<CommentRuleDO>()
                        .eq(CommentRuleDO::getRuleCategoryId, updateReqVO.getId())
                        .eq(CommentRuleDO::getDeleted, false)
        );
        Set<Long> existingRuleIds = existingRules.stream()
                .map(CommentRuleDO::getId)
                .collect(Collectors.toSet());

        // 5. 处理提交的规则
        Set<Long> submittedRuleIds = new HashSet<>();
        for (CommentRuleSaveReqVO ruleVO : commentRules) {
            Long ruleId;
            if (ruleVO.getId() == null) {
                // 新增规则
                CommentRuleDO newRule = BeanUtils.toBean(ruleVO, CommentRuleDO.class);
                newRule.setRuleCategoryId(updateReqVO.getId());
                commentRuleMapper.insert(newRule);
                ruleId = newRule.getId();
            } else {
                // 更新规则
                ruleId = ruleVO.getId();
                submittedRuleIds.add(ruleId);
                CommentRuleDO updateRule = BeanUtils.toBean(ruleVO, CommentRuleDO.class);
                commentRuleMapper.updateById(updateRule);
            }

            // 处理明细列表
            List<RuleDetailSaveReqVO> details = ruleVO.getDetails();
            if (CollUtil.isEmpty(details)) {
                // 删除原有的明细
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, ruleId)
                );
                continue;
            }

            // 获取已有的明细ID列表
            List<RuleDetailDO> existingDetails = ruleDetailMapper.selectList(
                    new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                            .eq(RuleDetailDO::getRuleId, ruleId)
                            .eq(RuleDetailDO::getDeleted, false)
            );
            Set<Long> existingDetailIds = existingDetails.stream()
                    .map(RuleDetailDO::getId)
                    .collect(Collectors.toSet());

            Set<Long> submittedDetailIds = new HashSet<>();
            for (RuleDetailSaveReqVO detailVO : details) {
                if (detailVO.getId() == null) {
                    // 新增明细
                    RuleDetailDO newDetail = BeanUtils.toBean(detailVO, RuleDetailDO.class);
                    newDetail.setRuleId(ruleId);
                    ruleDetailMapper.insert(newDetail);
                } else {
                    // 更新明细
                    Long detailId = detailVO.getId();
                    submittedDetailIds.add(detailId);
                    RuleDetailDO updateDetail = BeanUtils.toBean(detailVO, RuleDetailDO.class);
                    ruleDetailMapper.updateById(updateDetail);
                }
            }

            // 删除已移除的明细
            for (Long existingDetailId : existingDetailIds) {
                if (!submittedDetailIds.contains(existingDetailId)) {
                    ruleDetailMapper.deleteById(existingDetailId);
                }
            }
        }

        // 6. 删除已移除的规则
        for (Long existingRuleId : existingRuleIds) {
            if (!submittedRuleIds.contains(existingRuleId)) {
                // 删除该规则下的所有明细
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, existingRuleId)
                );
                // 删除规则
                commentRuleMapper.deleteById(existingRuleId);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveFull(RuleCategorySaveFullReqVO saveFullReqVO) {
        // 1. 处理规则分类基本信息
        Long categoryId;
        if (saveFullReqVO.getId() != null) {
            // 修改规则分类
            categoryId = saveFullReqVO.getId();
            // 校验存在
            RuleCategoryDO existing = ruleCategoryMapper.selectById(categoryId);
            if (existing == null) {
                throw exception(RULE_CATEGORY_NOT_EXISTS);
            }
            // 更新基本信息
            RuleCategoryDO updateObj = BeanUtils.toBean(saveFullReqVO, RuleCategoryDO.class);
            ruleCategoryMapper.updateById(updateObj);
        } else {
            // 新增规则分类
            RuleCategoryDO newCategory = BeanUtils.toBean(saveFullReqVO, RuleCategoryDO.class);
            ruleCategoryMapper.insert(newCategory);
            categoryId = newCategory.getId();
        }

        // 2. 处理评分规则列表
        List<CommentRuleSaveReqVO> commentRules = saveFullReqVO.getCommentRules();
        if (CollUtil.isEmpty(commentRules)) {
            // 如果没有传规则，则删除原有的所有规则和明细
            List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                    new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<CommentRuleDO>()
                            .eq(CommentRuleDO::getRuleCategoryId, categoryId)
                            .eq(CommentRuleDO::getDeleted, false)
            );
            if (CollUtil.isNotEmpty(existingRules)) {
                List<Long> ruleIds = existingRules.stream().map(CommentRuleDO::getId).collect(Collectors.toList());
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .in(RuleDetailDO::getRuleId, ruleIds)
                );
                commentRuleMapper.deleteByIds(ruleIds);
            }
            return categoryId;
        }

        // 3. 获取已有的规则ID列表
        List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<CommentRuleDO>()
                        .eq(CommentRuleDO::getRuleCategoryId, categoryId)
                        .eq(CommentRuleDO::getDeleted, false)
        );
        Set<Long> existingRuleIds = existingRules.stream()
                .map(CommentRuleDO::getId)
                .collect(Collectors.toSet());

        // 4. 处理提交的规则
        Set<Long> submittedRuleIds = new HashSet<>();
        for (CommentRuleSaveReqVO ruleVO : commentRules) {
            Long ruleId;
            if (ruleVO.getId() == null) {
                // 新增规则
                CommentRuleDO newRule = BeanUtils.toBean(ruleVO, CommentRuleDO.class);
                newRule.setRuleCategoryId(categoryId);
                // 如果没有传体系ID，则使用规则分类的体系ID
                if (newRule.getSystemId() == null && saveFullReqVO.getSystemId() != null) {
                    try {
                        newRule.setSystemId(Long.parseLong(saveFullReqVO.getSystemId()));
                    } catch (NumberFormatException e) {
                        // 忽略转换错误
                    }
                }
                commentRuleMapper.insert(newRule);
                ruleId = newRule.getId();
            } else {
                // 更新规则
                ruleId = ruleVO.getId();
                submittedRuleIds.add(ruleId);
                CommentRuleDO updateRule = BeanUtils.toBean(ruleVO, CommentRuleDO.class);
                commentRuleMapper.updateById(updateRule);
            }

            // 处理明细列表
            List<RuleDetailSaveReqVO> details = ruleVO.getDetails();
            if (CollUtil.isEmpty(details)) {
                // 删除原有的明细
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, ruleId)
                );
                continue;
            }

            // 获取已有的明细ID列表
            List<RuleDetailDO> existingDetails = ruleDetailMapper.selectList(
                    new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                            .eq(RuleDetailDO::getRuleId, ruleId)
                            .eq(RuleDetailDO::getDeleted, false)
            );
            Set<Long> existingDetailIds = existingDetails.stream()
                    .map(RuleDetailDO::getId)
                    .collect(Collectors.toSet());

            Set<Long> submittedDetailIds = new HashSet<>();
            for (RuleDetailSaveReqVO detailVO : details) {
                if (detailVO.getId() == null) {
                    // 新增明细
                    RuleDetailDO newDetail = BeanUtils.toBean(detailVO, RuleDetailDO.class);
                    newDetail.setRuleId(ruleId);
                    ruleDetailMapper.insert(newDetail);
                } else {
                    // 更新明细
                    Long detailId = detailVO.getId();
                    submittedDetailIds.add(detailId);
                    RuleDetailDO updateDetail = BeanUtils.toBean(detailVO, RuleDetailDO.class);
                    ruleDetailMapper.updateById(updateDetail);
                }
            }

            // 删除已移除的明细
            for (Long existingDetailId : existingDetailIds) {
                if (!submittedDetailIds.contains(existingDetailId)) {
                    ruleDetailMapper.deleteById(existingDetailId);
                }
            }
        }

        // 5. 删除已移除的规则
        for (Long existingRuleId : existingRuleIds) {
            if (!submittedRuleIds.contains(existingRuleId)) {
                ruleDetailMapper.delete(
                        new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, existingRuleId)
                );
                commentRuleMapper.deleteById(existingRuleId);
            }
        }

        return categoryId;
    }

}