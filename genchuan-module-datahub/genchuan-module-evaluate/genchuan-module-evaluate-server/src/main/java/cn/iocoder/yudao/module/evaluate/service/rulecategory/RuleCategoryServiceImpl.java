package cn.iocoder.yudao.module.evaluate.service.rulecategory;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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

    @Resource
    private cn.iocoder.yudao.module.evaluate.dal.mysql.ruleitem.RuleItemMapper ruleItemMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public Long createRuleCategory(RuleCategorySaveReqVO createReqVO) {
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
        // 更新
        RuleCategoryDO updateObj = BeanUtils.toBean(updateReqVO, RuleCategoryDO.class);
        ruleCategoryMapper.updateById(updateObj);
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

        // ========== 根据 creator 和 updater 查 system_users 获取创建人和更新人姓名 ==========
        // 1. 提取当前页所有的 creator 和 updater（去重，避免重复查询）
        Set<Long> allUserIds = new HashSet<>();
        pageResult.getList().forEach(vo -> {
            // 处理 creator
            String creatorStr = String.valueOf(vo.getCreator());
            if (StrUtil.isNotBlank(creatorStr) && NumberUtil.isNumber(creatorStr)) {
                allUserIds.add(Long.valueOf(creatorStr));
            }
            // 处理 updater
            String updaterStr = String.valueOf(vo.getUpdater());
            if (StrUtil.isNotBlank(updaterStr) && NumberUtil.isNumber(updaterStr)) {
                allUserIds.add(Long.valueOf(updaterStr));
            }
        });

        // 2. 批量查询用户信息
        Map<Long, AdminUserRespDTO> userMap = CollUtil.isNotEmpty(allUserIds)
                ? adminUserApi.getUserMap(allUserIds)
                : new HashMap<>();

        // ========== 统计每个规则分类下的规则项数量 ==========
        // 提取当前页所有规则分类的 ID
        List<String> categoryIds = pageResult.getList().stream()
                .map(vo -> String.valueOf(vo.getId()))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 批量查询规则项数量
        Map<String, Long> itemCountMap = new HashMap<>();
        if (CollUtil.isNotEmpty(categoryIds)) {
            // 使用 LambdaQueryWrapperX 进行批量统计查询
            cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> countWrapper
                    = new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO>()
                    .in(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId, categoryIds)
                    .eq(cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getDeleted, false);

            // 查询所有匹配的规则项
            List<cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO> ruleItems =
                    ruleItemMapper.selectList(countWrapper);

            // 按 ruleCategoryId 统计数量
            itemCountMap = ruleItems.stream()
                    .collect(java.util.stream.Collectors.groupingBy(
                            cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO::getRuleCategoryId,
                            java.util.stream.Collectors.counting()));
        }

        // 3. 回填创建人和更新人姓名，以及规则项数量
        Map<String, Long> finalItemCountMap = itemCountMap;
        pageResult.getList().forEach(vo -> {
            // 回填创建人姓名
            String creatorStr = String.valueOf(vo.getCreator());
            if (StrUtil.isNotBlank(creatorStr) && NumberUtil.isNumber(creatorStr)) {
                AdminUserRespDTO creatorUser = userMap.get(Long.valueOf(creatorStr));
                if (creatorUser != null) {
                    vo.setCreateUserName(creatorUser.getNickname());
                }
            }
            // 回填更新人姓名
            String updaterStr = String.valueOf(vo.getUpdater());
            if (StrUtil.isNotBlank(updaterStr) && NumberUtil.isNumber(updaterStr)) {
                AdminUserRespDTO updaterUser = userMap.get(Long.valueOf(updaterStr));
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

}