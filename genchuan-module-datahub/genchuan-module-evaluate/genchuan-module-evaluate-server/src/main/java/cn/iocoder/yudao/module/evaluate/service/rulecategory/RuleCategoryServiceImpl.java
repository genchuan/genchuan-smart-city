package cn.iocoder.yudao.module.evaluate.service.rulecategory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype.ObjectTypeMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail.RuleDetailMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.ruletype.RuleTypeMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_CATEGORY_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_CATEGORY_NOT_EXISTS;

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
    private CommentRuleMapper commentRuleMapper;

    @Lazy
    @Resource
    private RuleDetailMapper ruleDetailMapper;

    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.ruleitem.RuleItemMapper ruleItemMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Lazy
    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper indexSystemMapper;

    @Resource
    private ObjectTypeMapper objectTypeMapper;

    @Resource
    private RuleTypeMapper ruleTypeMapper;

    @Resource
    private StatusMapper statusMapper;

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
            List<IndexSystemDO> systems =
                    ruleCategoryMapper.selectSystemByIds(new ArrayList<>(systemIdPks));
            if (systems != null) {
                systemNameMap = systems.stream()
                        .collect(Collectors.toMap(
                                IndexSystemDO::getId,
                                IndexSystemDO::getName,
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
            LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> countWrapper
                    = new LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO>()
                    .in(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId, categoryIds)
                    .eq(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getDeleted, false);

            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> ruleItems =
                    ruleItemMapper.selectList(countWrapper);

            itemCountMap = ruleItems.stream()
                    .collect(Collectors.groupingBy(
                            cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId,
                            Collectors.counting()));
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
                new LambdaQueryWrapperX<CommentRuleDO>()
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
                                new LambdaQueryWrapperX<RuleDetailDO>()
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
    public Long updateRuleCategoryWithRules(RuleCategorySaveReqVO updateReqVO) {
        Long categoryId;

        if (updateReqVO.getId() == null) {
            // ========== 新增场景 ==========
            // 校验名称在同一体系下唯一
            validateNameUnique(updateReqVO.getName(), updateReqVO.getSystemId(), null);
            // 插入规则分类
            RuleCategoryDO newCategory = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
            ruleCategoryMapper.insert(newCategory);
            categoryId = newCategory.getId();
        } else {
            // ========== 修改场景 ==========
            // 校验存在
            validateRuleCategoryExists(updateReqVO.getId());
            // 校验名称在同一体系下唯一
            validateNameUnique(updateReqVO.getName(), updateReqVO.getSystemId(), updateReqVO.getId());
            // 更新规则分类基本信息
            RuleCategoryDO updateObj = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
            ruleCategoryMapper.updateById(updateObj);
            categoryId = updateReqVO.getId();
        }

        // 3. 处理评分规则列表
        List<CommentRuleSaveReqVO> commentRules = updateReqVO.getCommentRules();
        if (CollUtil.isEmpty(commentRules)) {
            // 如果没有传规则，则删除原有的所有规则和明细
            List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                    new LambdaQueryWrapperX<CommentRuleDO>()
                            .eq(CommentRuleDO::getRuleCategoryId, categoryId)
                            .eq(CommentRuleDO::getDeleted, false)
            );
            if (CollUtil.isNotEmpty(existingRules)) {
                // 删除所有明细
                List<Long> ruleIds = existingRules.stream().map(CommentRuleDO::getId).collect(Collectors.toList());
                ruleDetailMapper.delete(
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .in(RuleDetailDO::getRuleId, ruleIds)
                );
                // 删除所有规则
                commentRuleMapper.deleteByIds(ruleIds);
            }
            return categoryId;
        }

        // 4. 获取已有的规则ID列表
        List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                new LambdaQueryWrapperX<CommentRuleDO>()
                        .eq(CommentRuleDO::getRuleCategoryId, categoryId)
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
                newRule.setRuleCategoryId(categoryId);
                // 统一处理 systemId：无论 newRule.systemId 是否为 null，都优先使用 updateReqVO.systemId
                if (StrUtil.isBlank(String.valueOf(newRule.getSystemId())) && updateReqVO.getSystemId() != null) {
                    try {
                        newRule.setSystemId(Long.parseLong(updateReqVO.getSystemId()));
                    } catch (NumberFormatException e) {
                        // 忽略转换错误
                    }
                }
                Db.saveBatch(Collections.singletonList(newRule));
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
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, ruleId)
                );
                continue;
            }

            // 获取已有的明细ID列表
            List<RuleDetailDO> existingDetails = ruleDetailMapper.selectList(
                    new LambdaQueryWrapperX<RuleDetailDO>()
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
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, existingRuleId)
                );
                // 删除规则
                commentRuleMapper.deleteById(existingRuleId);
            }
        }

        return categoryId;
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
                    new LambdaQueryWrapperX<CommentRuleDO>()
                            .eq(CommentRuleDO::getRuleCategoryId, categoryId)
                            .eq(CommentRuleDO::getDeleted, false)
            );
            if (CollUtil.isNotEmpty(existingRules)) {
                List<Long> ruleIds = existingRules.stream().map(CommentRuleDO::getId).collect(Collectors.toList());
                ruleDetailMapper.delete(
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .in(RuleDetailDO::getRuleId, ruleIds)
                );
                commentRuleMapper.deleteByIds(ruleIds);
            }
            return categoryId;
        }

        // 3. 获取已有的规则ID列表
        List<CommentRuleDO> existingRules = commentRuleMapper.selectList(
                new LambdaQueryWrapperX<CommentRuleDO>()
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
                // 统一处理 systemId：无论 newRule.systemId 是否为 null，都优先使用 saveFullReqVO.systemId
                if (StrUtil.isBlank(String.valueOf(newRule.getSystemId())) && saveFullReqVO.getSystemId() != null) {
                    try {
                        newRule.setSystemId(Long.parseLong(saveFullReqVO.getSystemId()));
                    } catch (NumberFormatException e) {
                        // 忽略转换错误
                    }
                }
                Db.saveBatch(Collections.singletonList(newRule));
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
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, ruleId)
                );
                continue;
            }

            // 获取已有的明细ID列表
            List<RuleDetailDO> existingDetails = ruleDetailMapper.selectList(
                    new LambdaQueryWrapperX<RuleDetailDO>()
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
                        new LambdaQueryWrapperX<RuleDetailDO>()
                                .eq(RuleDetailDO::getRuleId, existingRuleId)
                );
                commentRuleMapper.deleteById(existingRuleId);
            }
        }

        return categoryId;
    }

    @Override
    public RuleCategoryStatisticsVO getRuleCategoryStatistics() {
        RuleCategoryStatisticsVO vo = new RuleCategoryStatisticsVO();

        // ========== 1. 卡片数据 ==========
        RuleCategoryStatisticsVO.CardData cardData = new RuleCategoryStatisticsVO.CardData();

        Map<String, Object> countMap = ruleCategoryMapper.selectStatisticsCount();
        Long categoryCount = countMap.get("categoryCount") != null
                ? ((Number) countMap.get("categoryCount")).longValue() : 0L;
        Long ruleCount = countMap.get("ruleCount") != null
                ? ((Number) countMap.get("ruleCount")).longValue() : 0L;
        Map<String, Object> indexItemCountMap = ruleCategoryMapper.selectIndexItemCount();
        Long indexItemCount = indexItemCountMap.get("indexItemCount") != null
                ? ((Number) indexItemCountMap.get("indexItemCount")).longValue() : 0L;

        Map<String, Object> enabledRuleCountMap = ruleCategoryMapper.selectEnabledRuleCount();
        Long enabledRuleCount = enabledRuleCountMap.get("enabledRuleCount") != null
                ? ((Number) enabledRuleCountMap.get("enabledRuleCount")).longValue() : 0L;

        cardData.setTotalCategoryCount(categoryCount);
        cardData.setTotalRuleCount(ruleCount);
        cardData.setEnabledRuleCount(enabledRuleCount);
        vo.setCardData(cardData);

        // ========== 2. 查询所有评分规则（用于各类图表统计） ==========
        List<CommentRuleDO> allRules = commentRuleMapper.selectList(
                new LambdaQueryWrapperX<CommentRuleDO>()
                        .eq(CommentRuleDO::getDeleted, false)
        );

        // ========== 3. 规则类型圆环图（ruleType -> id 关联查询字典名称） ==========
        List<Map<String, Object>> ruleTypeGroupList = ruleCategoryMapper.selectRuleTypeGroupCount();
        if (CollUtil.isNotEmpty(ruleTypeGroupList)) {
            Set<Long> ruleTypeIds = new HashSet<>();
            for (Map<String, Object> item : ruleTypeGroupList) {
                Object ruleTypeObj = item.get("rule_type");
                if (ruleTypeObj != null) {
                    ruleTypeIds.add(((Number) ruleTypeObj).longValue());
                }
            }
            Map<Long, String> ruleTypeNameMap = new HashMap<>();
            if (CollUtil.isNotEmpty(ruleTypeIds)) {
                List<cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO> ruleTypeList =
                        ruleTypeMapper.selectByIds(new ArrayList<>(ruleTypeIds));
                if (ruleTypeList != null) {
                    ruleTypeNameMap = ruleTypeList.stream()
                            .collect(Collectors.toMap(
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO::getId,
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO::getName,
                                    (a, b) -> a));
                }
            }
            Map<Long, String> finalRuleTypeNameMap = ruleTypeNameMap;
            List<RuleCategoryStatisticsVO.PieChartItem> ruleTypePieChart = ruleTypeGroupList.stream()
                    .map(item -> {
                        RuleCategoryStatisticsVO.PieChartItem pieItem = new RuleCategoryStatisticsVO.PieChartItem();
                        Object ruleTypeObj = item.get("rule_type");
                        Long ruleTypeId = ruleTypeObj != null ? ((Number) ruleTypeObj).longValue() : null;
                        pieItem.setName(finalRuleTypeNameMap.getOrDefault(ruleTypeId, ruleTypeId != null ? String.valueOf(ruleTypeId) : "未知"));
                        pieItem.setValue(((Number) item.get("ruleCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setRuleTypePieChart(ruleTypePieChart);
        } else {
            vo.setRuleTypePieChart(new ArrayList<>());
        }

        // ========== 4. 适用对象类型圆环图（applyObjectType -> typeId 关联查询字典名称） ==========
        List<Map<String, Object>> applyObjectTypeGroupList = ruleCategoryMapper.selectApplyObjectTypeGroupCount();
        if (CollUtil.isNotEmpty(applyObjectTypeGroupList)) {
            Set<String> typeIds = new HashSet<>();
            for (Map<String, Object> item : applyObjectTypeGroupList) {
                Object typeIdObj = item.get("apply_object_type");
                if (typeIdObj != null) {
                    typeIds.add(String.valueOf(typeIdObj));
                }
            }
            Map<String, String> objectTypeNameMap = new HashMap<>();
            if (CollUtil.isNotEmpty(typeIds)) {
                List<cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO> objectTypeList =
                        objectTypeMapper.selectByTypeIds(new ArrayList<>(typeIds));
                if (objectTypeList != null) {
                    objectTypeNameMap = objectTypeList.stream()
                            .collect(Collectors.toMap(
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO::getTypeId,
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO::getName,
                                    (a, b) -> a));
                }
            }
            Map<String, String> finalObjectTypeNameMap = objectTypeNameMap;
            List<RuleCategoryStatisticsVO.PieChartItem> applyObjectTypePieChart = applyObjectTypeGroupList.stream()
                    .map(item -> {
                        RuleCategoryStatisticsVO.PieChartItem pieItem = new RuleCategoryStatisticsVO.PieChartItem();
                        String typeId = String.valueOf(item.get("apply_object_type"));
                        pieItem.setName(finalObjectTypeNameMap.getOrDefault(typeId, typeId));
                        pieItem.setValue(((Number) item.get("ruleCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setApplyObjectTypePieChart(applyObjectTypePieChart);
        } else {
            vo.setApplyObjectTypePieChart(new ArrayList<>());
        }

        // ========== 5. 状态圆环图（status -> id 关联查询字典名称） ==========
        List<Map<String, Object>> statusGroupList = ruleCategoryMapper.selectStatusGroupCount();
        if (CollUtil.isNotEmpty(statusGroupList)) {
            Set<Long> statusIds = new HashSet<>();
            for (Map<String, Object> item : statusGroupList) {
                Object statusObj = item.get("status");
                if (statusObj != null) {
                    statusIds.add(((Number) statusObj).longValue());
                }
            }
            Map<Long, String> statusNameMap = new HashMap<>();
            if (CollUtil.isNotEmpty(statusIds)) {
                List<cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO> statusList =
                        statusMapper.selectByIds(new ArrayList<>(statusIds));
                if (statusList != null) {
                    statusNameMap = statusList.stream()
                            .collect(Collectors.toMap(
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO::getId,
                                    cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO::getName,
                                    (a, b) -> a));
                }
            }
            Map<Long, String> finalStatusNameMap = statusNameMap;
            List<RuleCategoryStatisticsVO.PieChartItem> statusPieChart = statusGroupList.stream()
                    .map(item -> {
                        RuleCategoryStatisticsVO.PieChartItem pieItem = new RuleCategoryStatisticsVO.PieChartItem();
                        Object statusObj = item.get("status");
                        Long statusId = statusObj != null ? ((Number) statusObj).longValue() : null;
                        pieItem.setName(finalStatusNameMap.getOrDefault(statusId, statusId != null ? String.valueOf(statusId) : "未知"));
                        pieItem.setValue(((Number) item.get("ruleCount")).longValue());
                        return pieItem;
                    })
                    .collect(Collectors.toList());
            vo.setStatusPieChart(statusPieChart);
        } else {
            vo.setStatusPieChart(new ArrayList<>());
        }

        // ========== 6. 柱状图：按分类统计规则数量 ==========
        List<Map<String, Object>> categoryRuleCountList = ruleCategoryMapper.selectCategoryRuleCount();
        List<RuleCategoryDO> categories = ruleCategoryMapper.selectList(
                new LambdaQueryWrapperX<RuleCategoryDO>()
                        .eq(RuleCategoryDO::getDeleted, false)
                        .orderByDesc(RuleCategoryDO::getId)
        );
        Map<Long, Long> categoryRuleCountMap = new HashMap<>();
        for (Map<String, Object> item : categoryRuleCountList) {
            Object categoryIdObj = item.get("rule_category_id");
            Object countObj = item.get("ruleCount");
            if (categoryIdObj != null && countObj != null) {
                categoryRuleCountMap.put(((Number) categoryIdObj).longValue(), ((Number) countObj).longValue());
            }
        }
        Map<Long, String> categoryNameMap = categories.stream()
                .collect(Collectors.toMap(RuleCategoryDO::getId, RuleCategoryDO::getName, (a, b) -> a));

        List<RuleCategoryStatisticsVO.BarChartItem> categoryBarChart = categoryRuleCountList.stream()
                .map(item -> {
                    RuleCategoryStatisticsVO.BarChartItem barItem = new RuleCategoryStatisticsVO.BarChartItem();
                    Object categoryIdObj = item.get("rule_category_id");
                    Long categoryId = categoryIdObj != null ? ((Number) categoryIdObj).longValue() : null;
                    barItem.setCategoryName(categoryNameMap.getOrDefault(categoryId, categoryId != null ? String.valueOf(categoryId) : "未知"));
                    barItem.setRuleCount(((Number) item.get("ruleCount")).longValue());
                    return barItem;
                })
                .collect(Collectors.toList());
        vo.setCategoryBarChart(categoryBarChart);

        // ========== 7. 明细列表（复用原有逻辑） ==========
        if (CollUtil.isEmpty(categories)) {
            vo.setCategoryList(new ArrayList<>());
            return vo;
        }

        // ========== 8. 按分类ID分组规则 ==========
        Map<Long, List<CommentRuleDO>> rulesByCategory = allRules.stream()
                .collect(Collectors.groupingBy(CommentRuleDO::getRuleCategoryId));

        // ========== 9. 统计每个规则的指标项数量 ==========
        List<Map<String, Object>> ruleIndexCountList = ruleCategoryMapper.selectRuleIndexItemCount();
        Map<Long, Long> ruleIndexCountMap = new HashMap<>();
        for (Map<String, Object> item : ruleIndexCountList) {
            Object ruleIdObj = item.get("comment_rule_id");
            Object countObj = item.get("indexItemCount");
            if (ruleIdObj != null && countObj != null) {
                ruleIndexCountMap.put(((Number) ruleIdObj).longValue(), ((Number) countObj).longValue());
            }
        }

        // ========== 10. 批量查询体系名称 ==========
        Set<Long> systemIds = categories.stream()
                .map(c -> {
                    try {
                        return Long.parseLong(c.getSystemId());
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, String> systemNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(systemIds)) {
            List<IndexSystemDO> systems = indexSystemMapper.selectBatchIds(systemIds);
            if (systems != null) {
                systemNameMap = systems.stream()
                        .collect(Collectors.toMap(IndexSystemDO::getId, IndexSystemDO::getName, (a, b) -> a));
            }
        }

        // ========== 11. 组装明细数据 ==========
        final Map<Long, String> finalSystemNameMap = systemNameMap;
        List<RuleCategoryStatisticsVO.CategoryStatistics> categoryStatisticsList = categories.stream()
                .map(category -> {
                    RuleCategoryStatisticsVO.CategoryStatistics catStat = new RuleCategoryStatisticsVO.CategoryStatistics();
                    catStat.setCategoryId(category.getId());
                    catStat.setCategoryName(category.getName());

                    if (StrUtil.isNotBlank(category.getSystemId())) {
                        try {
                            Long sysId = Long.parseLong(category.getSystemId());
                            catStat.setSystemName(finalSystemNameMap.get(sysId));
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    List<CommentRuleDO> categoryRules = rulesByCategory.getOrDefault(category.getId(), Collections.emptyList());
                    catStat.setRuleCount((long) categoryRules.size());

                    long totalIndexCount = categoryRules.stream()
                            .mapToLong(rule -> ruleIndexCountMap.getOrDefault(rule.getId(), 0L))
                            .sum();
                    catStat.setIndexItemCount(totalIndexCount);

                    List<RuleCategoryStatisticsVO.RuleStatistics> ruleStatisticsList = categoryRules.stream()
                            .map(rule -> {
                                RuleCategoryStatisticsVO.RuleStatistics ruleStat = new RuleCategoryStatisticsVO.RuleStatistics();
                                ruleStat.setRuleId(rule.getId());
                                ruleStat.setRuleName(rule.getRuleName());
                                ruleStat.setIndexItemCount(ruleIndexCountMap.getOrDefault(rule.getId(), 0L));
                                return ruleStat;
                            })
                            .collect(Collectors.toList());

                    catStat.setRules(ruleStatisticsList);
                    return catStat;
                })
                .collect(Collectors.toList());

        vo.setCategoryList(categoryStatisticsList);
        return vo;
    }

}