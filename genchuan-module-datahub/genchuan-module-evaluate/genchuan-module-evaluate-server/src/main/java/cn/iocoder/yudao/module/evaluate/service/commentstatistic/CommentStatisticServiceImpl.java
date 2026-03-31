package cn.iocoder.yudao.module.evaluate.service.commentstatistic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentstatistic.CommentStatisticMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.object.ObjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.patrolinspection.PatrolInspectionMapper;
import cn.iocoder.yudao.module.evaluate.service.commentrule.CommentRuleService;
import cn.iocoder.yudao.module.evaluate.service.objectscore.ObjectScoreService;
import cn.iocoder.yudao.module.evaluate.service.ruledetail.RuleDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.COMMENT_STATISTIC_NOT_EXISTS;

/**
 * 巡查巡检统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CommentStatisticServiceImpl implements CommentStatisticService {

    @Resource
    private CommentStatisticMapper commentStatisticMapper;

    @Resource
    private PatrolInspectionMapper patrolInspectionMapper;

    @Resource
    private CommentRuleService commentRuleService;

    @Resource
    private RuleDetailService ruleDetailService;

    @Resource
    private IndexItemMapper indexItemMapper;

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private CommentRuleMapper commentRuleMapper;

    @Resource
    private ObjectScoreService objectScoreService;

    @Override
    public Long createCommentStatistic(CommentStatisticSaveReqVO createReqVO) {
        // 插入
        CommentStatisticDO commentStatistic = BeanUtils.toBean(createReqVO, CommentStatisticDO.class);
        commentStatisticMapper.insert(commentStatistic);

        // 同步更新公司得分表
        if (commentStatistic.getSystemId() != null && commentStatistic.getObjectId() != null) {
            objectScoreService.recalculateScore(commentStatistic.getSystemId(), commentStatistic.getObjectId());
        }

        // 返回
        return commentStatistic.getId();
    }

    @Override
    public void updateCommentStatistic(CommentStatisticSaveReqVO updateReqVO) {
        // 校验存在，获取旧值用于同步
        CommentStatisticDO exist = commentStatisticMapper.selectById(updateReqVO.getId());
        if (exist == null) {
            throw exception(COMMENT_STATISTIC_NOT_EXISTS);
        }
        Long oldSystemId = exist.getSystemId();
        Long oldObjectId = exist.getObjectId();

        // 更新
        CommentStatisticDO updateObj = BeanUtils.toBean(updateReqVO, CommentStatisticDO.class);
        commentStatisticMapper.updateById(updateObj);

        // 同步更新公司得分表
        if (oldSystemId != null && oldObjectId != null) {
            objectScoreService.recalculateScore(oldSystemId, oldObjectId);
        }
    }

    @Override
    public void deleteCommentStatistic(Long id) {
        // 校验存在，获取删除前的数据用于统计
        CommentStatisticDO exist = commentStatisticMapper.selectById(id);
        if (exist == null) {
            throw exception(COMMENT_STATISTIC_NOT_EXISTS);
        }

        // 删除
        commentStatisticMapper.deleteById(id);

        // 删除后，同步更新公司得分表
        if (exist.getSystemId() != null && exist.getObjectId() != null) {
            objectScoreService.recalculateScore(exist.getSystemId(), exist.getObjectId());
        }
    }

    private void validateCommentStatisticExists(Long id) {
        if (commentStatisticMapper.selectById(id) == null) {
            throw exception(COMMENT_STATISTIC_NOT_EXISTS);
        }
    }

    @Override
    public CommentStatisticDO getCommentStatistic(Long id) {
        return commentStatisticMapper.selectById(id);
    }

    @Override
    public PageResult<CommentStatisticRespVO> getCommentStatisticPage(CommentStatisticPageReqVO pageReqVO) {
        // 1. 检查并初始化统计表（首次访问或统计表为空时，从巡查表全量初始化）
        ensureDataInitialized(pageReqVO.getItemId(), pageReqVO.getObjectId(), pageReqVO.getAddressCoding());

        // 2. 使用 MyBatis-Plus 内置分页直接查询统计表（支持 systemId 等全部过滤条件）
        PageResult<CommentStatisticDO> pageResult = commentStatisticMapper.selectPage(pageReqVO);

        // 3. 转换为 RespVO 并填充名称
        return convertToRespVOPage(pageResult);
    }

    /**
     * 检查统计表是否为空，若为空则从巡查表全量初始化
     * 兼容系统冷启动或统计表被清空后首次访问的场景
     */
    private void ensureDataInitialized(Long itemId, Long objectId, String addressCoding) {
        // 检查统计表是否已有数据
        Long total = commentStatisticMapper.selectCount(null);
        if (total != null && total > 0) {
            return; // 统计表已有数据，跳过初始化
        }

        // 统计表为空，查询巡查表有多少去重组合
        Long count = patrolInspectionMapper.selectPatrolGroupCount(itemId, objectId, addressCoding);
        if (count == null || count == 0) {
            return; // 巡查表也为空，无需初始化
        }

        // 分批从巡查表读取组合，逐条同步到统计表
        int batchSize = 100;
        long offset = 0;
        while (offset < count) {
            List<PatrolInspectionDO> patrolList = patrolInspectionMapper.selectPatrolGroupList(
                    itemId, objectId, addressCoding, batchSize, offset);
            if (patrolList == null || patrolList.isEmpty()) {
                break;
            }
            for (PatrolInspectionDO patrol : patrolList) {
                if (patrol == null || patrol.getSystemId() == null) {
                    continue;
                }
                syncCount(patrol.getSystemId(), patrol.getItemId(), patrol.getObjectId(),
                        patrol.getAddressCoding());
            }
            offset += batchSize;
        }
    }

    /**
     * 将 CommentStatisticDO 分页结果转换为 CommentStatisticRespVO 并填充关联表名称
     */
    private PageResult<CommentStatisticRespVO> convertToRespVOPage(PageResult<CommentStatisticDO> pageResult) {
        if (pageResult == null || pageResult.getList() == null || pageResult.getList().isEmpty()) {
            return PageResult.empty();
        }

        List<CommentStatisticDO> list = pageResult.getList();

        // 1. 收集所有关联 ID
        Set<Long> itemIds = list.stream()
                .map(CommentStatisticDO::getItemId)
                .filter(id -> id != null && id > 0)
                .collect(Collectors.toSet());
        Set<Long> objectIds = list.stream()
                .map(CommentStatisticDO::getObjectId)
                .filter(id -> id != null && id > 0)
                .collect(Collectors.toSet());
        Set<Long> ruleIds = list.stream()
                .map(CommentStatisticDO::getRuleId)
                .filter(id -> id != null && id > 0)
                .collect(Collectors.toSet());
        Set<Long> systemIds = list.stream()
                .map(CommentStatisticDO::getSystemId)
                .filter(id -> id != null && id > 0)
                .collect(Collectors.toSet());

        // 2. 批量查询指标项名称
        Map<Long, String> itemNameMap = itemIds.isEmpty() ? Map.of() :
                indexItemMapper.selectBatchIds(itemIds).stream()
                        .collect(Collectors.toMap(IndexItemDO::getId, IndexItemDO::getName, (a, b) -> a));

        // 3. 批量查询评价对象名称
        Map<Long, String> objectNameMap = objectIds.isEmpty() ? Map.of() :
                objectMapper.selectBatchIds(objectIds).stream()
                        .collect(Collectors.toMap(ObjectDO::getId, ObjectDO::getName, (a, b) -> a));

        // 4. 批量查询规则名称
        Map<Long, String> ruleNameMap = ruleIds.isEmpty() ? Map.of() :
                commentRuleMapper.selectBatchIds(ruleIds).stream()
                        .collect(Collectors.toMap(cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO::getId,
                                cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO::getRuleName, (a, b) -> a));

        // 5. 批量查询体系名称
        Map<Long, String> systemNameMap = systemIds.isEmpty() ? Map.of() :
                indexSystemMapper.selectBatchIds(systemIds).stream()
                        .collect(Collectors.toMap(IndexSystemDO::getId, IndexSystemDO::getName, (a, b) -> a));

        // 6. 转换并填充名称
        List<CommentStatisticRespVO> voList = list.stream().map(stat -> {
            CommentStatisticRespVO vo = BeanUtils.toBean(stat, CommentStatisticRespVO.class);
            if (stat.getItemId() != null) {
                vo.setItemName(itemNameMap.get(stat.getItemId()));
            }
            if (stat.getObjectId() != null) {
                vo.setObjectName(objectNameMap.get(stat.getObjectId()));
            }
            if (stat.getRuleId() != null) {
                vo.setRuleName(ruleNameMap.get(stat.getRuleId()));
            }
            if (stat.getSystemId() != null) {
                vo.setSystemName(systemNameMap.get(stat.getSystemId()));
            }
            return vo;
        }).collect(Collectors.toList());

        return new PageResult<>(voList, pageResult.getTotal());
    }

    @Override
    public void incrementCount(Long systemId, Long itemId, Long objectId, String addressCoding) {
        // 查询是否存在对应的统计记录（增加systemId条件）
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getSystemId, systemId)
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        // 查询ruleId
        Long ruleId = commentRuleService.getCommentRuleIdBySystemIdAndItemId(systemId, itemId);

        if (existRecord != null) {
            // 记录存在，count + 1
            CommentStatisticDO updateRecord = new CommentStatisticDO();
            updateRecord.setId(existRecord.getId());
            Long newCount = existRecord.getCount() + 1;
            updateRecord.setCount(newCount);
            updateRecord.setRuleId(ruleId);
            // 根据新的 count 和 ruleId 计算分数
            if (ruleId != null) {
                BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, newCount);
                updateRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
            }
            commentStatisticMapper.updateById(updateRecord);
        } else {
            // 记录不存在，创建新记录，count = 1
            CommentStatisticDO newRecord = new CommentStatisticDO();
            newRecord.setSystemId(systemId);
            newRecord.setItemId(itemId);
            newRecord.setObjectId(objectId);
            newRecord.setCount(1L);
            newRecord.setAddressCoding(addressCoding);
            newRecord.setStatus("1"); // 默认待审核状态
            newRecord.setRuleId(ruleId);
            // 根据 count 和 ruleId 计算分数
            if (ruleId != null) {
                BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, 1L);
                newRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
            }
            commentStatisticMapper.insert(newRecord);
        }
    }

    @Override
    public void decrementCount(Long systemId, Long itemId, Long objectId) {
        // 查询是否存在对应的统计记录（添加systemId条件避免返回多条记录）
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getSystemId, systemId)
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        if (existRecord != null) {
            // 查询ruleId
            Long ruleId = commentRuleService.getCommentRuleIdBySystemIdAndItemId(systemId, itemId);

            Long currentCount = existRecord.getCount();
            if (currentCount == null || currentCount <= 1) {
                // count为空或小于等于1，删除该记录
                commentStatisticMapper.deleteById(existRecord.getId());
            } else {
                // count > 1，count - 1
                CommentStatisticDO updateRecord = new CommentStatisticDO();
                updateRecord.setId(existRecord.getId());
                Long newCount = currentCount - 1;
                updateRecord.setCount(newCount);
                // 根据新的 count 和 ruleId 计算分数
                if (ruleId != null) {
                    BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, newCount);
                    updateRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
                }
                commentStatisticMapper.updateById(updateRecord);
            }
        }
    }

    @Override
    public void syncCount(Long systemId, Long itemId, Long objectId, String addressCoding) {
        // 从巡查表重新统计数量
        Long count = commentStatisticMapper.selectCountBySystemIdAndItemIdAndObjectId(systemId, itemId, objectId);
        count = count != null ? count : 0L;

        // 查询是否存在对应的统计记录（添加systemId条件避免返回多条记录）
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getSystemId, systemId)
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        // 查询ruleId
        Long ruleId = commentRuleService.getCommentRuleIdBySystemIdAndItemId(systemId, itemId);

        if (existRecord != null) {
            if (count == 0) {
                // 统计数量为0，删除该记录
                commentStatisticMapper.deleteById(existRecord.getId());
            } else {
                // 更新统计数量
                existRecord.setCount(count);
                existRecord.setAddressCoding(addressCoding);
                existRecord.setRuleId(ruleId);
                existRecord.setSystemId(systemId);
                // 根据 count 和 ruleId 计算分数
                if (ruleId != null) {
                    BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, count);
                    existRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
                }
                commentStatisticMapper.updateById(existRecord);
            }
        } else if (count > 0) {
            // 记录不存在但统计数量大于0，创建新记录
            CommentStatisticDO newRecord = new CommentStatisticDO();
            newRecord.setItemId(itemId);
            newRecord.setObjectId(objectId);
            newRecord.setCount(count);
            newRecord.setAddressCoding(addressCoding);
            newRecord.setStatus("1");
            newRecord.setRuleId(ruleId);
            newRecord.setSystemId(systemId);
            // 根据 count 和 ruleId 计算分数
            if (ruleId != null) {
                BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, count);
                newRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
            }
            commentStatisticMapper.insert(newRecord);
        }
    }

    @Override
    public void updateRuleId(Long systemId, Long itemId, Long objectId, Long ruleId, String addressCoding) {
        // 查询对应的统计记录
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getSystemId, systemId)
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        if (existRecord != null) {
            // 更新 ruleId，并重新计算分数
            CommentStatisticDO updateRecord = new CommentStatisticDO();
            updateRecord.setId(existRecord.getId());
            updateRecord.setRuleId(ruleId);
            // 根据当前的 count 和新的 ruleId 计算分数
            if (ruleId != null && existRecord.getCount() != null) {
                BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, existRecord.getCount());
                updateRecord.setScore(scoreDecimal != null ? scoreDecimal.longValue() : null);
            } else {
                updateRecord.setScore(null);
            }
            commentStatisticMapper.updateById(updateRecord);
        }
    }

    @Override
    public List<CommentStatisticRespVO> getAllCommentStatisticList(CommentStatisticPageReqVO reqVO) {
        // 确保统计表已初始化
        ensureDataInitialized(reqVO.getItemId(), reqVO.getObjectId(), reqVO.getAddressCoding());

        // 查询全量数据（应用与分页相同的过滤条件）
        List<CommentStatisticDO> allList = commentStatisticMapper.selectList(
                new LambdaQueryWrapperX<CommentStatisticDO>()
                        .eqIfPresent(CommentStatisticDO::getSystemId, reqVO.getSystemId())
                        .eqIfPresent(CommentStatisticDO::getItemId, reqVO.getItemId())
                        .eqIfPresent(CommentStatisticDO::getObjectId, reqVO.getObjectId())
                        .eqIfPresent(CommentStatisticDO::getAddressCoding, reqVO.getAddressCoding())
                        .eqIfPresent(CommentStatisticDO::getStatus, reqVO.getStatus())
                        .orderByDesc(CommentStatisticDO::getId));

        return convertToRespVOList(allList);
    }

    /**
     * 将 CommentStatisticDO 列表转换为 RespVO 列表并填充关联名称
     */
    private List<CommentStatisticRespVO> convertToRespVOList(List<CommentStatisticDO> list) {
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        return convertToRespVOPage(new PageResult<>(list, (long) list.size())).getList();
    }

    @Override
    public int reconcileAll() {
        // 1. 从巡查表获取每个 (systemId, itemId, objectId) 组合的真实数量
        List<Map<String, Object>> patrolCountList = patrolInspectionMapper.selectPatrolCountGroupByKeys();
        Map<String, Long> patrolCountMap = patrolCountList.stream()
                .collect(Collectors.toMap(
                        map -> buildKey(
                                ((Number) map.get("systemId")).longValue(),
                                ((Number) map.get("itemId")).longValue(),
                                ((Number) map.get("objectId")).longValue()),
                        map -> ((Number) map.get("count")).longValue(),
                        (a, b) -> a
                ));

        // 2. 查询统计表中所有有效记录
        List<CommentStatisticDO> existingList = commentStatisticMapper.selectList(
                new LambdaQueryWrapperX<CommentStatisticDO>()
                        .isNotNull(CommentStatisticDO::getSystemId)
                        .isNotNull(CommentStatisticDO::getItemId)
                        .isNotNull(CommentStatisticDO::getObjectId));

        int fixedCount = 0;

        // 3. 遍历统计表记录，与巡查表对比修正
        for (CommentStatisticDO stat : existingList) {
            String key = buildKey(stat.getSystemId(), stat.getItemId(), stat.getObjectId());
            Long realCount = patrolCountMap.get(key);
            Long statCount = stat.getCount();

            if (realCount == null) {
                // 巡查表中已无此组合，删除统计记录
                commentStatisticMapper.deleteById(stat.getId());
                fixedCount++;
            } else if (!realCount.equals(statCount)) {
                // 数量不一致，重新同步
                syncCount(stat.getSystemId(), stat.getItemId(), stat.getObjectId(), stat.getAddressCoding());
                fixedCount++;
            }
        }

        // 4. 巡查表中有但统计表中没有的组合，补充插入
        for (Map<String, Object> patrolEntry : patrolCountList) {
            Long systemId = ((Number) patrolEntry.get("systemId")).longValue();
            Long itemId = ((Number) patrolEntry.get("itemId")).longValue();
            Long objectId = ((Number) patrolEntry.get("objectId")).longValue();
            Long realCount = ((Number) patrolEntry.get("count")).longValue();

            String key = buildKey(systemId, itemId, objectId);
            boolean existsInStat = existingList.stream()
                    .anyMatch(s -> buildKey(s.getSystemId(), s.getItemId(), s.getObjectId()).equals(key));

            if (!existsInStat) {
                // 统计表中不存在此组合，插入新记录
                Long ruleId = commentRuleService.getCommentRuleIdBySystemIdAndItemId(systemId, itemId);
                BigDecimal scoreDecimal = ruleDetailService.calculateScoreByCount(ruleId, realCount);
                CommentStatisticDO newStat = CommentStatisticDO.builder()
                        .systemId(systemId)
                        .itemId(itemId)
                        .objectId(objectId)
                        .count(realCount)
                        .ruleId(ruleId)
                        .score(scoreDecimal != null ? scoreDecimal.longValue() : null)
                        .status("1")
                        .build();
                commentStatisticMapper.insert(newStat);
                fixedCount++;
            }
        }

        return fixedCount;
    }

    private String buildKey(Long systemId, Long itemId, Long objectId) {
        return systemId + ":" + itemId + ":" + objectId;
    }

}