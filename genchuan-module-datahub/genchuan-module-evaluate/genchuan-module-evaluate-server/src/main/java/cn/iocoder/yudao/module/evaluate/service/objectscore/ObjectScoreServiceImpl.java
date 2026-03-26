package cn.iocoder.yudao.module.evaluate.service.objectscore;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreCalculateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScorePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objectscore.ObjectScoreDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentstatistic.CommentStatisticMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objectscore.ObjectScoreMapper;
import cn.iocoder.yudao.module.evaluate.service.indexcategory.IndexCategoryService;
import cn.iocoder.yudao.module.evaluate.service.indexitem.IndexItemService;
import cn.iocoder.yudao.module.evaluate.service.indexsystem.IndexSystemService;
import cn.iocoder.yudao.module.evaluate.service.object.ObjectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_SCORE_NOT_EXISTS;

/**
 * 公司得分 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ObjectScoreServiceImpl implements ObjectScoreService {

    @Resource
    private ObjectScoreMapper objectScoreMapper;

    @Resource
    private CommentStatisticMapper commentStatisticMapper;

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Resource
    private IndexItemService indexItemService;

    @Resource
    private IndexCategoryService indexCategoryService;

    @Resource
    private ObjectService objectService;

    @Resource
    private IndexSystemService indexSystemService;

    /**
     * 满分基准分数
     */
    private static final BigDecimal FULL_SCORE = new BigDecimal("100");

    @Override
    public Long createObjectScore(ObjectScoreSaveReqVO createReqVO) {
        ObjectScoreDO objectScore = BeanUtils.toBean(createReqVO, ObjectScoreDO.class);
        objectScoreMapper.insert(objectScore);

        // 根据统计表数据重新计算真实得分
        if (objectScore.getSystemId() != null && objectScore.getObjectId() != null) {
            recalculateScore(objectScore.getSystemId(), objectScore.getObjectId());
        }

        return objectScore.getId();
    }

    @Override
    public void updateObjectScore(ObjectScoreSaveReqVO updateReqVO) {
        // 校验存在，获取旧值
        ObjectScoreDO exist = validateObjectScoreExists(updateReqVO.getId());
        Long oldSystemId = exist.getSystemId();
        Long oldObjectId = exist.getObjectId();

        // 更新
        ObjectScoreDO updateObj = BeanUtils.toBean(updateReqVO, ObjectScoreDO.class);
        objectScoreMapper.updateById(updateObj);

        // 重新计算得分（old 和 new 都需要重算）
        if (oldSystemId != null && oldObjectId != null) {
            recalculateScore(oldSystemId, oldObjectId);
        }
        Long newSystemId = updateReqVO.getSystemId();
        Long newObjectId = updateReqVO.getObjectId();
        if (newSystemId != null && newObjectId != null
                && !(oldSystemId != null && oldSystemId.equals(newSystemId)
                    && oldObjectId != null && oldObjectId.equals(newObjectId))) {
            recalculateScore(newSystemId, newObjectId);
        }
    }

    @Override
    public void deleteObjectScore(Long id) {
        validateObjectScoreExists(id);
        objectScoreMapper.deleteById(id);
    }

    @Override
    public void deleteObjectScoreListByIds(List<Long> ids) {
        objectScoreMapper.deleteByIds(ids);
    }

    private ObjectScoreDO validateObjectScoreExists(Long id) {
        if (objectScoreMapper.selectById(id) == null) {
            throw exception(OBJECT_SCORE_NOT_EXISTS);
        }
        return null;
    }

    @Override
    public ObjectScoreDO getObjectScore(Long id) {
        return objectScoreMapper.selectById(id);
    }

    @Override
    public PageResult<ObjectScoreRespVO> getObjectScorePage(ObjectScorePageReqVO pageReqVO) {
        // 1. 分页查询得分记录
        PageResult<ObjectScoreDO> pageResult = objectScoreMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return new PageResult<>(Collections.emptyList(), pageResult.getTotal());
        }

        // 2. 批量查询体系名称
        List<Long> systemIds = pageResult.getList().stream()
                .map(ObjectScoreDO::getSystemId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, String> systemNameMap = new HashMap<>();
        for (Long sid : systemIds) {
            IndexSystemDO sys = indexSystemService.getIndexSystem(sid);
            if (sys != null) {
                systemNameMap.put(sid, sys.getName());
            }
        }

        // 3. 批量查询对象名称
        List<Long> objectIds = pageResult.getList().stream()
                .map(ObjectScoreDO::getObjectId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        Map<Long, String> objectNameMap = new HashMap<>();
        for (Long oid : objectIds) {
            try {
                var obj = objectService.getObject(oid);
                if (obj != null) {
                    objectNameMap.put(oid, obj.getName());
                }
            } catch (Exception ignored) {
            }
        }

        // 4. 转换为 RespVO 并填充名称
        List<ObjectScoreRespVO> voList = BeanUtils.toBean(pageResult.getList(), ObjectScoreRespVO.class);
        for (ObjectScoreRespVO vo : voList) {
            if (vo.getSystemId() != null) {
                vo.setSystemName(systemNameMap.get(vo.getSystemId()));
            }
            if (vo.getObjectId() != null) {
                vo.setObjectName(objectNameMap.get(vo.getObjectId()));
            }
        }

        return new PageResult<>(voList, pageResult.getTotal());
    }

    /**
     * 刷新公司得分表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int refreshScoreTable() {
        List<CommentStatisticDO> distinctList = commentStatisticMapper.selectDistinctSystemIdAndObjectId();
        if (CollUtil.isEmpty(distinctList)) {
            return 0;
        }

        // 收集所有 systemId 和 objectId，用于批量查询 userId
        List<Long> systemIds = distinctList.stream()
                .map(CommentStatisticDO::getSystemId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        List<Long> objectIds = distinctList.stream()
                .map(CommentStatisticDO::getObjectId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 批量查询每个(systemId, objectId)组合下最近一条巡查记录的userId
        Map<String, Long> userIdMap = new HashMap<>();
        List<Map<String, Object>> userIdList =
                commentStatisticMapper.selectLatestUserIdBySystemIdAndObjectId(systemIds, objectIds);
        for (Map<String, Object> row : userIdList) {
            Long sid = ((Number) row.get("systemId")).longValue();
            Long oid = ((Number) row.get("objectId")).longValue();
            Number uidNum = (Number) row.get("userId");
            Long uid = uidNum != null ? uidNum.longValue() : null;
            userIdMap.put(sid + ":" + oid, uid);
        }

        for (CommentStatisticDO item : distinctList) {
            Long systemId = item.getSystemId();
            Long objectId = item.getObjectId();
            String key = systemId + ":" + objectId;
            Long userId = userIdMap.get(key);

            ObjectScoreDO existScore = objectScoreMapper.selectOne(
                    new LambdaQueryWrapperX<ObjectScoreDO>()
                            .eq(ObjectScoreDO::getSystemId, systemId)
                            .eq(ObjectScoreDO::getObjectId, objectId)
                            .eq(ObjectScoreDO::getDeleted, 0)
            );

            ObjectScoreCalculateRespVO calculateResp = calculateScoreInternal(systemId, objectId, null);

            if (existScore != null) {
                objectScoreMapper.updateById(ObjectScoreDO.builder()
                        .id(existScore.getId())
                        .score(calculateResp.getTotalScore().longValue())
                        .userId(userId)
                        .build());
            } else {
                objectScoreMapper.insert(ObjectScoreDO.builder()
                        .objectId(objectId)
                        .systemId(systemId)
                        .score(calculateResp.getTotalScore().longValue())
                        .status("1")
                        .userId(userId)
                        .build());
            }
        }
        return distinctList.size();
    }

    @Override
    public ObjectScoreCalculateRespVO calculateScore(Long id) {
        ObjectScoreDO objectScore = objectScoreMapper.selectById(id);
        if (objectScore == null) {
            throw exception(OBJECT_SCORE_NOT_EXISTS);
        }
        return calculateScoreInternal(objectScore.getSystemId(), objectScore.getObjectId(), id);
    }

    @Override
    public void recalculateScore(Long systemId, Long objectId) {
        if (systemId == null || objectId == null) {
            return;
        }

        // 1. 查询 (systemId, objectId) 对应的得分记录
        ObjectScoreDO existScore = objectScoreMapper.selectOne(
                new LambdaQueryWrapperX<ObjectScoreDO>()
                        .eq(ObjectScoreDO::getSystemId, systemId)
                        .eq(ObjectScoreDO::getObjectId, objectId)
                        .eq(ObjectScoreDO::getDeleted, 0)
        );

        // 2. 从巡查表获取该组合下最近一条记录的 userId
        List<Map<String, Object>> userIdList =
                commentStatisticMapper.selectLatestUserIdBySystemIdAndObjectId(
                        List.of(systemId), List.of(objectId));
        Long userId = null;
        if (!userIdList.isEmpty()) {
            Map<String, Object> row = userIdList.get(0);
            Number uidNum = (Number) row.get("userId");
            userId = uidNum != null ? uidNum.longValue() : null;
        }

        // 3. 调用内部方法计算得分
        ObjectScoreCalculateRespVO calculateResp = calculateScoreInternal(systemId, objectId,
                existScore != null ? existScore.getId() : null);

        if (existScore != null) {
            // 记录已存在，更新 score 和 userId
            objectScoreMapper.updateById(ObjectScoreDO.builder()
                    .id(existScore.getId())
                    .score(calculateResp.getTotalScore().longValue())
                    .userId(userId)
                    .build());
        } else {
            // 记录不存在，先插入再更新（确保有主键ID用于 calculateScoreInternal）
            ObjectScoreDO newScore = ObjectScoreDO.builder()
                    .systemId(systemId)
                    .objectId(objectId)
                    .score(0L)
                    .status("1")
                    .userId(userId)
                    .build();
            objectScoreMapper.insert(newScore);
            // 重新计算并写入真实得分
            ObjectScoreCalculateRespVO realResp = calculateScoreInternal(systemId, objectId, newScore.getId());
            objectScoreMapper.updateById(ObjectScoreDO.builder()
                    .id(newScore.getId())
                    .score(realResp.getTotalScore().longValue())
                    .userId(userId)
                    .build());
        }
    }

    /**
     * 内部计算方法
     *
     * 性能优化说明：
     * 1. 分类列表、指标项列表均从 Redis 缓存读取（分类/指标项变更时失效），避免每次重复查询 DB
     * 2. 权重归一化仅在权重和 ≠ 100 时执行一次 UPDATE，写入后后续请求直接命中 DB 缓存的权重值
     * 3. 统计表记录一次查询构建 Map，O(N) 查找
     */
    private ObjectScoreCalculateRespVO calculateScoreInternal(Long systemId, Long objectId, Long objectScoreId) {
        // 0. 通过主键ID查询体系的业务UUID
        String systemUuid = indexSystemMapper.selectSystemIdById(systemId);
        if (systemUuid == null || systemUuid.isEmpty()) {
            return emptyResult(objectScoreId, systemId, objectId);
        }

        // 1. 从缓存获取分类列表（缓存未命中时从DB加载，TTL=24h）
        List<IndexCategoryDO> categoryList = indexCategoryService.getCategoryListBySystemIdFromCache(systemUuid);
        if (CollUtil.isEmpty(categoryList)) {
            return emptyResult(objectScoreId, systemId, objectId);
        }

        // 2. 从缓存获取指标项列表（缓存未命中时从DB加载，TTL=24h）
        List<IndexItemDO> allItemList = indexItemService.getItemListBySystemIdFromCache(systemUuid);
        Map<String, List<IndexItemDO>> itemsByCategory = CollUtil.isEmpty(allItemList)
                ? Collections.emptyMap()
                : allItemList.stream()
                        .collect(Collectors.groupingBy(item ->
                                item.getCategoryId() != null ? item.getCategoryId() : ""));

        // 3. 一次性查询统计表中的记录，构建 Map
        List<CommentStatisticDO> statisticList =
                commentStatisticMapper.selectMapBySystemIdAndObjectId(systemId, objectId);
        Map<Long, CommentStatisticDO> statisticMap = CollUtil.isEmpty(statisticList)
                ? Collections.emptyMap()
                : statisticList.stream()
                        .filter(s -> s.getItemId() != null && s.getItemId() > 0)
                        .collect(Collectors.toMap(CommentStatisticDO::getItemId, s -> s, (a, b) -> a));

        // 4. 分类权重归一化（仅在权重和 ≠ 100 时执行一次 UPDATE）
        normalizeCategoryWeightsIfNeeded(categoryList);

        // 5. 指标项权重归一化（仅在权重未归一化时执行 UPDATE）
        normalizeItemWeightsIfNeeded(itemsByCategory, allItemList);

        // 6. 计算加权得分
        BigDecimal totalScore = BigDecimal.ZERO;
        List<ObjectScoreCalculateRespVO.ScoreDetail> details = new ArrayList<>();

        for (IndexCategoryDO category : categoryList) {
            String categoryId = category.getCategoryId();
            List<IndexItemDO> categoryItems = itemsByCategory.getOrDefault(categoryId, Collections.emptyList());

            BigDecimal categoryWeightPercent = category.getWeight() != null
                    ? category.getWeight().divide(FULL_SCORE, 4, RoundingMode.HALF_UP)
                    : BigDecimal.ONE;

            for (IndexItemDO item : categoryItems) {
                Long itemId = item.getId();

                BigDecimal itemWeightPercent = item.getWeight() != null
                        ? item.getWeight().divide(FULL_SCORE, 4, RoundingMode.HALF_UP)
                        : BigDecimal.ZERO;

                CommentStatisticDO stat = statisticMap.get(itemId);
                Long scoreValue = (stat != null && stat.getScore() != null)
                        ? stat.getScore()
                        : FULL_SCORE.longValue();
                Long statisticId = stat != null ? stat.getId() : null;

                // 原始值累加，只在 detail 和最终结果做四舍五入
                BigDecimal weightedScore = new BigDecimal(scoreValue.toString())
                        .multiply(categoryWeightPercent)
                        .multiply(itemWeightPercent);
                totalScore = totalScore.add(weightedScore);

                details.add(ObjectScoreCalculateRespVO.ScoreDetail.builder()
                        .statisticId(statisticId)
                        .itemId(itemId)
                        .itemName(item.getName())
                        .categoryId(categoryId)
                        .categoryName(category.getName())
                        .itemWeight(item.getWeight() != null ? item.getWeight() : BigDecimal.ZERO)
                        .categoryWeight(category.getWeight() != null ? category.getWeight() : FULL_SCORE)
                        .score(scoreValue)
                        .weightedScore(weightedScore.setScale(2, RoundingMode.HALF_UP))
                        .build());
            }
        }

        String systemName = null;
        String objectName = null;
        if (systemId != null) {
            var system = indexSystemService.getIndexSystem(systemId);
            if (system != null) {
                systemName = system.getName();
            }
        }
        if (objectId != null) {
            var object = objectService.getObject(objectId);
            if (object != null) {
                objectName = object.getName();
            }
        }

        return ObjectScoreCalculateRespVO.builder()
                .objectScoreId(objectScoreId)
                .systemId(systemId)
                .systemName(systemName)
                .objectId(objectId)
                .objectName(objectName)
                .totalScore(totalScore.setScale(2, RoundingMode.HALF_UP))
                .details(details)
                .build();
    }

    /**
     * 分类权重归一化，仅在权重和 ≠ 100 时执行一次 UPDATE
     */
    private void normalizeCategoryWeightsIfNeeded(List<IndexCategoryDO> categoryList) {
        BigDecimal totalWeightSum = BigDecimal.ZERO;
        for (IndexCategoryDO c : categoryList) {
            if (c.getWeight() != null && c.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                totalWeightSum = totalWeightSum.add(c.getWeight());
            }
        }
        if (totalWeightSum.compareTo(BigDecimal.ZERO) <= 0
                || totalWeightSum.compareTo(FULL_SCORE) == 0) {
            return;
        }
        Map<String, BigDecimal> toUpdate = new HashMap<>();
        for (IndexCategoryDO c : categoryList) {
            if (c.getWeight() != null && c.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                toUpdate.put(c.getCategoryId(),
                        c.getWeight().multiply(FULL_SCORE)
                                .divide(totalWeightSum, 2, RoundingMode.HALF_UP));
            }
        }
        // 写 DB + 同步更新 Redis 缓存
        indexCategoryService.updateBatchCategoryWeight(toUpdate);
        for (IndexCategoryDO c : categoryList) {
            BigDecimal normalized = toUpdate.get(c.getCategoryId());
            if (normalized != null) {
                c.setWeight(normalized);
            }
        }
    }

    /**
     * 指标项权重归一化检查
     *
     * 规则：
     * - 有权重 item 之和 < 100：有权重的归一化到 100，剩余权重在所有 item（含权重=0）间平均分配
     * - 有权重 item 之和 > 100：所有有权重的按比例归一化到 100
     * - 有权重 item 之和 = 100：不处理
     */
    private void normalizeItemWeightsIfNeeded(Map<String, List<IndexItemDO>> itemsByCategory,
                                              List<IndexItemDO> allItemList) {
        Map<Long, BigDecimal> toUpdate = new HashMap<>();
        for (List<IndexItemDO> items : itemsByCategory.values()) {
            if (items.isEmpty()) {
                continue;
            }
            int itemCount = items.size();
            BigDecimal positiveSum = BigDecimal.ZERO;
            for (IndexItemDO item : items) {
                if (item.getWeight() != null && item.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                    positiveSum = positiveSum.add(item.getWeight());
                }
            }
            if (positiveSum.compareTo(FULL_SCORE) < 0) {
                // 先归一化有权重的 item
                if (positiveSum.compareTo(BigDecimal.ZERO) > 0) {
                    for (IndexItemDO item : items) {
                        if (item.getWeight() != null && item.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                            toUpdate.put(item.getId(),
                                    item.getWeight().multiply(FULL_SCORE)
                                            .divide(positiveSum, 2, RoundingMode.HALF_UP));
                        }
                    }
                }
                // 剩余权重只在有权重的 item 之间平均分配，零权重 item 保持 0
                BigDecimal normalizedPositiveSum = positiveSum.compareTo(BigDecimal.ZERO) > 0
                        ? toUpdate.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add)
                        : BigDecimal.ZERO;
                BigDecimal remaining = FULL_SCORE.subtract(normalizedPositiveSum);
                long positiveItemCount = items.stream()
                        .filter(item -> item.getWeight() != null && item.getWeight().compareTo(BigDecimal.ZERO) > 0)
                        .count();
                if (positiveItemCount > 0 && remaining.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal perPositiveItem = remaining.divide(new BigDecimal(positiveItemCount), 2, RoundingMode.HALF_UP);
                    for (IndexItemDO item : items) {
                        if (item.getWeight() != null && item.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                            BigDecimal base = toUpdate.get(item.getId());
                            toUpdate.put(item.getId(), base.add(perPositiveItem));
                        }
                    }
                }
            } else if (positiveSum.compareTo(FULL_SCORE) > 0) {
                for (IndexItemDO item : items) {
                    if (item.getWeight() != null && item.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                        toUpdate.put(item.getId(),
                                item.getWeight().multiply(FULL_SCORE)
                                        .divide(positiveSum, 2, RoundingMode.HALF_UP));
                    }
                }
            }
        }
        if (toUpdate.isEmpty()) {
            return;
        }
        indexItemService.updateBatchItemWeight(toUpdate);
        for (IndexItemDO item : allItemList) {
            BigDecimal normalized = toUpdate.get(item.getId());
            if (normalized != null) {
                item.setWeight(normalized);
            }
        }
    }

    private ObjectScoreCalculateRespVO emptyResult(Long objectScoreId, Long systemId, Long objectId) {
        String systemName = null;
        String objectName = null;
        if (systemId != null) {
            var system = indexSystemService.getIndexSystem(systemId);
            if (system != null) {
                systemName = system.getName();
            }
        }
        if (objectId != null) {
            var object = objectService.getObject(objectId);
            if (object != null) {
                objectName = object.getName();
            }
        }

        return ObjectScoreCalculateRespVO.builder()
                .objectScoreId(objectScoreId)
                .systemId(systemId)
                .systemName(systemName)
                .objectId(objectId)
                .objectName(objectName)
                .totalScore(BigDecimal.ZERO)
                .details(Collections.emptyList())
                .build();
    }

}
