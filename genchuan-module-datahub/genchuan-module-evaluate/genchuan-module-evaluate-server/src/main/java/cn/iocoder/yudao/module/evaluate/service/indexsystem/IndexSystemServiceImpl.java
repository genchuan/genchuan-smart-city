package cn.iocoder.yudao.module.evaluate.service.indexsystem;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory.IndexCategoryMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype.ObjectTypeMapper;
import cn.iocoder.yudao.module.evaluate.service.indextype.IndexTypeService;
import cn.iocoder.yudao.module.evaluate.service.calcway.CalcWayService;
import cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.INDEX_SYSTEM_NOT_EXISTS;

/**
 * 指标体系 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class IndexSystemServiceImpl implements IndexSystemService {

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Resource
    private IndexCategoryMapper indexCategoryMapper;

    @Resource
    private IndexItemMapper indexItemMapper;

    @Resource
    private StatusMapper statusMapper;

    @Resource
    private ObjectTypeMapper objectTypeMapper;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private IndexTypeService indexTypeService;

    @Resource
    private CalcWayService calcWayService;

    @Override
    public Long createIndexSystem(IndexSystemSaveReqVO createReqVO) {
        // 插入
        IndexSystemDO indexSystem = BeanUtils.toBean(createReqVO, IndexSystemDO.class);
        indexSystemMapper.insert(indexSystem);
        // 返回
        return indexSystem.getId();
    }

    @Override
    public void updateIndexSystem(IndexSystemSaveReqVO updateReqVO) {
        // 校验存在
        validateIndexSystemExists(updateReqVO.getId());
        // 查询现有体系获取systemId
        IndexSystemDO existing = indexSystemMapper.selectById(updateReqVO.getId());
        // 更新
        IndexSystemDO updateObj = BeanUtils.toBean(updateReqVO, IndexSystemDO.class);
        indexSystemMapper.updateById(updateObj);
        // 记录变更日志
        if (existing != null && existing.getSystemId() != null) {
            // 对比状态变化，记录变更日志
            if (updateReqVO.getStatusId() != null) {
                String oldStatusIdStr = existing.getStatusId();
                String newStatusIdStr = String.valueOf(updateReqVO.getStatusId());
                if (!Objects.equals(oldStatusIdStr, newStatusIdStr)) {
                    String oldStatusName = getStatusNameById(oldStatusIdStr);
                    String newStatusName = getStatusNameById(newStatusIdStr);
                    updateChangeLog(existing.getSystemId(), "UPDATE",
                            StrUtil.format("状态由【{}】改为【{}】", oldStatusName, newStatusName));
                }
            }
            // 记录名称变更
            if (updateReqVO.getName() != null && !Objects.equals(existing.getName(), updateReqVO.getName())) {
                String oldName = StrUtil.blankToDefault(existing.getName(), "空");
                String newName = StrUtil.blankToDefault(updateReqVO.getName(), "空");
                updateChangeLog(existing.getSystemId(), "UPDATE",
                        StrUtil.format("体系名称由【{}】改为【{}】", oldName, newName));
            }
        }
    }

    @Override
    public void deleteIndexSystem(Long id) {
        // 校验存在
        IndexSystemDO existing = indexSystemMapper.selectById(id);
        String systemId = existing != null ? existing.getSystemId() : null;
        String systemName = existing != null ? existing.getName() : null;
        validateIndexSystemExists(id);

        // 1. 先查询该体系下的所有分类
        List<IndexCategoryDO> categories = indexCategoryMapper.selectList(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getSystemId, existing.getSystemId())
                        .eq(IndexCategoryDO::getDeleted, 0)
        );

        // 2. 删除每个分类下的所有指标项
        for (IndexCategoryDO category : categories) {
            List<IndexItemDO> items = indexItemMapper.selectList(
                    new LambdaQueryWrapper<IndexItemDO>()
                            .eq(IndexItemDO::getCategoryId, category.getCategoryId())
                            .eq(IndexItemDO::getDeleted, 0)
            );
            if (!items.isEmpty()) {
                List<Long> itemIds = items.stream()
                        .map(IndexItemDO::getId)
                        .collect(Collectors.toList());
                indexItemMapper.deleteBatchIds(itemIds);
            }
        }

        // 3. 删除所有分类
        if (!categories.isEmpty()) {
            List<Long> categoryIds = categories.stream()
                    .map(IndexCategoryDO::getId)
                    .collect(Collectors.toList());
            indexCategoryMapper.deleteBatchIds(categoryIds);
        }

        // 4. 删除体系
        indexSystemMapper.deleteById(id);

        // 5. 记录变更日志（在删除前记录）
        if (systemId != null) {
            updateChangeLog(systemId, "DELETE",
                    StrUtil.format("体系名称：{}，体系编码：{}", systemName, existing.getCode()));
        }
    }

    private void validateIndexSystemExists(Long id) {
        if (indexSystemMapper.selectById(id) == null) {
            throw exception(INDEX_SYSTEM_NOT_EXISTS);
        }
    }

    @Override
    public IndexSystemDO getIndexSystem(Long id) {
        return indexSystemMapper.selectById(id);
    }

    @Override
    public PageResult<IndexSystemDO> getIndexSystemPage(IndexSystemPageReqVO pageReqVO) {
        return indexSystemMapper.selectPage(pageReqVO);
    }
//---------------------------------新增---------------------------------------------
    @Override
    public PageResult<IndexSystemPageItemVO> getIndexSystemPageWithJoin(IndexSystemPageReqVO pageReqVO) {
        // 1. 先查询符合条件的唯一体系数量（确保分页计数准确）
        Long totalCount = indexSystemMapper.selectCountWithJoin(pageReqVO);

        // 2. 如果总数为0，直接返回空结果
        if (totalCount == null || totalCount == 0) {
            return new PageResult<>(new ArrayList<>(), 0L);
        }

        // 3. 执行分页查询
        PageResult<IndexSystemPageItemVO> pageResult = indexSystemMapper.selectPageWithJoin(pageReqVO);

        // 4. 设置准确的总数
        pageResult.setTotal(totalCount);

        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IndexSystemDetailVO getIndexSystemDetail(Long id) {
        // 1. 查询指标体系基本信息
        IndexSystemDetailVO.BaseInfo baseInfo = indexSystemMapper.selectDetailBaseInfo(id);
        if (baseInfo == null) {
            throw exception(INDEX_SYSTEM_NOT_EXISTS);
        }

        // 2. 回填创建人和更新人姓名
        fillUserNames(baseInfo);

        // 3. 查询分类列表（使用体系的 UUID 查询）
        List<IndexSystemDetailVO.CategoryVO> categories = indexSystemMapper.selectCategoriesBySystemId(baseInfo.getSystemUuid());

        // 4. 提取分类ID列表（主键ID）
        List<Long> categoryIds = categories.stream()
                .map(IndexSystemDetailVO.CategoryVO::getCategoryId)
                .collect(Collectors.toList());

        // 5. 批量查询指标项
        if (!categoryIds.isEmpty()) {
            List<IndexSystemDetailVO.IndexItemVO> allItems = indexSystemMapper.selectItemsByCategoryIds(categoryIds);

            // 6. 按分类ID分组
            Map<Long, List<IndexSystemDetailVO.IndexItemVO>> itemsByCategory = allItems.stream()
                    .collect(Collectors.groupingBy(IndexSystemDetailVO.IndexItemVO::getCategoryId));

            // 7. 将指标项设置到对应的分类中
            categories.forEach(category ->
                    category.setItems(itemsByCategory.get(category.getCategoryId()))
            );
        }

        // 8. 组装返回结果
        IndexSystemDetailVO detailVO = new IndexSystemDetailVO();
        detailVO.setBaseInfo(baseInfo);
        detailVO.setCategories(categories);

        return detailVO;
    }

    /**
     * 回填创建人和更新人姓名
     */
    private void fillUserNames(IndexSystemDetailVO.BaseInfo baseInfo) {
        List<String> userIds = new ArrayList<>();
        if (StrUtil.isNotBlank(baseInfo.getCreator())) {
            userIds.add(baseInfo.getCreator());
        }
        if (StrUtil.isNotBlank(baseInfo.getUpdater())) {
            userIds.add(baseInfo.getUpdater());
        }
        if (userIds.isEmpty()) {
            return;
        }

        // 转换为 Long 类型批量查询用户
        List<Long> userIdLongs = userIds.stream()
                .map(id -> Convert.toLong(id, null))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (!userIdLongs.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIdLongs);
            if (StrUtil.isNotBlank(baseInfo.getCreator())) {
                Long creatorId = Convert.toLong(baseInfo.getCreator());
                AdminUserRespDTO creator = userMap.get(creatorId);
                if (creator != null) {
                    baseInfo.setCreateUserName(creator.getNickname());
                }
            }
            if (StrUtil.isNotBlank(baseInfo.getUpdater())) {
                Long updaterId = Convert.toLong(baseInfo.getUpdater());
                AdminUserRespDTO updater = userMap.get(updaterId);
                if (updater != null) {
                    baseInfo.setUpdateUserName(updater.getNickname());
                }
            }
        }
    }

    @Override
    public WeightCheckRespVO checkWeight(WeightCheckReqVO reqVO) {
        WeightCheckRespVO respVO = new WeightCheckRespVO();

        if (reqVO.getCategoryId() != null) {
            // 校验指标项权重（某个分类下的所有指标项权重总和）
            Double totalWeight = indexSystemMapper.selectItemWeightSum(reqVO.getCategoryId());
            respVO.setCheckType("ITEM_WEIGHT");
            respVO.setTotalWeight(totalWeight != null ? totalWeight : 0.0);
            respVO.setPassed(Math.abs((totalWeight != null ? totalWeight : 0.0) - 100.0) < 0.01);

            if (!respVO.getPassed()) {
                respVO.setErrorMessage(String.format("指标项权重总和应为100%，当前为%.2f%%", totalWeight));
            }
        } else {
            // 校验分类权重（某个体系下的所有分类权重总和）
            Double totalWeight = indexSystemMapper.selectCategoryWeightSum(reqVO.getSystemId());
            respVO.setCheckType("CATEGORY_WEIGHT");
            respVO.setTotalWeight(totalWeight != null ? totalWeight : 0.0);
            respVO.setPassed(Math.abs((totalWeight != null ? totalWeight : 0.0) - 100.0) < 0.01);

            if (!respVO.getPassed()) {
                respVO.setErrorMessage(String.format("分类权重总和应为100%，当前为%.2f%%", totalWeight));
            }
        }

        return respVO;
    }
    //----------------------xin-------------------
    @Override
    public PageResult<IndexSystemRespVO> getIndexSystemJoinPage(IndexSystemPageReqVO reqVO) {
        // 直接调用 Mapper 层方法进行分页查询
        // Mapper 层已实现正确的分页逻辑：先按主表分页，再关联查询
        PageResult<IndexSystemRespVO> pageResult = indexSystemMapper.selectSystemJoinPage(reqVO);

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

        // 3. 回填创建人和更新人姓名
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
        });

        return pageResult;
    }

    @Override
    public IndexSystemRespVO getStatusCount(Integer statusId) {
        // 0. 清理孤儿数据
        cleanOrphanData();

        IndexSystemRespVO respVO = new IndexSystemRespVO();

        // 1. 改用普通QueryWrapper，支持字符串SQL片段
        QueryWrapper<IndexSystemDO> wrapper = new QueryWrapper<IndexSystemDO>()
                .ne("deleted", 1); // 对应原过滤条件，使用数据库下划线字段名

        // 动态拼接statusId条件
        if (statusId != null) {
            wrapper.eq("status_id", statusId);
        }

        // 2. 普通QueryWrapper原生支持直接传入SQL片段
        wrapper.select(
                "COUNT(*) AS totalCount",
                "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS status1Count",
                "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS status2Count"
        );

        // 3. 仅执行1次数据库查询，拿到聚合结果
        Map<String, Object> result = indexSystemMapper.selectMaps(wrapper).get(0);

        // 4. 结果转换+空值保护（Convert是芋道框架内置工具类，自动处理null）
        respVO.setTotalCount(Convert.toLong(result.get("totalCount"), 0L));
        respVO.setStatus1Count(Convert.toLong(result.get("status1Count"), 0L));
        respVO.setStatus2Count(Convert.toLong(result.get("status2Count"), 0L));

        return respVO;
    }
    @Override
    public IndexSystemOverviewVO getOverview() {
        IndexSystemOverviewVO vo = new IndexSystemOverviewVO();

        // 1. 组装卡片数据（总体系数、启用数、各版本数、指标项总数）
        IndexSystemOverviewVO.CardData cardData = indexSystemMapper.selectCardCoreData();
        cardData.setVersionCounts(indexSystemMapper.selectVersionCounts());
        vo.setCardData(cardData);

        // 2. 组装圆环图数据
        vo.setObjectTypePieChart(indexSystemMapper.selectObjectTypePieChart());  // 适用对象类型占比
        vo.setIndexTypePieChart(indexSystemMapper.selectIndexTypePieChart());    // 指标类型占比
        vo.setCategoryWeightPieChart(indexSystemMapper.selectCategoryWeightPieChart()); // 分类权重分布

        // 3. 组装柱状图数据（各体系指标项数量对比）
        vo.setSystemItemCountBarChart(indexSystemMapper.selectSystemItemCountBarChart());

        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IndexSystemDO refreshSystemCountsBySystemId(String systemId) {
        if (StrUtil.isBlank(systemId)) {
            return null;
        }
        // 1. 查询当前体系
        IndexSystemDO system = indexSystemMapper.selectOne(new LambdaQueryWrapper<IndexSystemDO>()
                .eq(IndexSystemDO::getSystemId, systemId)
                .eq(IndexSystemDO::getDeleted, 0));
        if (system == null) {
            return null;
        }

        // 2. 查询该体系下的所有分类
        List<IndexCategoryDO> categories = indexCategoryMapper.selectList(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getSystemId, systemId)
                        .eq(IndexCategoryDO::getDeleted, 0)
        );
        int categoryCount = categories.size();

        // 3. 查询该体系下的所有指标项（通过分类ID聚合）
        int itemCount = 0;
        if (!categories.isEmpty()) {
            List<String> categoryIds = categories.stream()
                    .map(IndexCategoryDO::getCategoryId)
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());
            if (!categoryIds.isEmpty()) {
                Long cnt = indexItemMapper.selectCount(new LambdaQueryWrapper<IndexItemDO>()
                        .in(IndexItemDO::getCategoryId, categoryIds)
                        .eq(IndexItemDO::getDeleted, 0));
                itemCount = cnt != null ? cnt.intValue() : 0;
            }
        }

        // 4. 回写到指标体系表
        system.setCategoryCount(categoryCount);
        system.setItemCount(itemCount);
        indexSystemMapper.updateById(system);
        return system;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refreshSystemCountsByCategoryId(String categoryId) {
        if (StrUtil.isBlank(categoryId)) {
            return;
        }
        // 通过分类业务ID反查所属体系ID
        IndexCategoryDO category = indexCategoryMapper.selectOne(new LambdaQueryWrapper<IndexCategoryDO>()
                .eq(IndexCategoryDO::getCategoryId, categoryId)
                .eq(IndexCategoryDO::getDeleted, 0));
        if (category == null || StrUtil.isBlank(category.getSystemId())) {
            return;
        }
        refreshSystemCountsBySystemId(category.getSystemId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFull(IndexSystemSaveFullReqVO saveFullReqVO) {
        // 1. 处理体系基本信息
        String systemId;
        if (StrUtil.isNotBlank(saveFullReqVO.getSystemId())) {
            // 修改体系
            systemId = saveFullReqVO.getSystemId();
            IndexSystemDO existing = indexSystemMapper.selectOne(
                    new LambdaQueryWrapper<IndexSystemDO>()
                            .eq(IndexSystemDO::getSystemId, systemId)
                            .eq(IndexSystemDO::getDeleted, 0)
            );
            if (existing == null) {
                throw exception(INDEX_SYSTEM_NOT_EXISTS);
            }

            // ====================== 变更日志核心逻辑 start ======================
            // 1.1 初始化变更内容拼接器（只记录有变化的字段）
            StringJoiner changeContent = new StringJoiner("；");

            // 1.2 对比基础字段 - 体系名称
            if (saveFullReqVO.getName() != null && !Objects.equals(existing.getName(), saveFullReqVO.getName())) {
                String oldName = StrUtil.blankToDefault(existing.getName(), "空");
                String newName = StrUtil.blankToDefault(saveFullReqVO.getName(), "空");
                changeContent.add(StrUtil.format("体系名称由【{}】改为【{}】", oldName, newName));
            }

            // 1.3 对比基础字段 - 体系编码
            if (saveFullReqVO.getCode() != null && !Objects.equals(existing.getCode(), saveFullReqVO.getCode())) {
                String oldCode = StrUtil.blankToDefault(existing.getCode(), "空");
                String newCode = StrUtil.blankToDefault(saveFullReqVO.getCode(), "空");
                changeContent.add(StrUtil.format("体系编码由【{}】改为【{}】", oldCode, newCode));
            }

            // 1.4 对比基础字段 - 版本号
            if (saveFullReqVO.getVersion() != null && !Objects.equals(existing.getVersion(), saveFullReqVO.getVersion())) {
                String oldVersion = StrUtil.blankToDefault(existing.getVersion(), "空");
                String newVersion = StrUtil.blankToDefault(saveFullReqVO.getVersion(), "空");
                changeContent.add(StrUtil.format("版本号由【{}】改为【{}】", oldVersion, newVersion));
            }

            // 1.5 对比基础字段 - 适用对象类型
            if (saveFullReqVO.getObjectTypeId() != null && !Objects.equals(existing.getObjectTypeId(), saveFullReqVO.getObjectTypeId())) {
                String oldObjectTypeName = getObjectTypeNameById(existing.getObjectTypeId());
                String newObjectTypeName = getObjectTypeNameById(saveFullReqVO.getObjectTypeId());
                changeContent.add(StrUtil.format("适用对象类型由【{}】改为【{}】", oldObjectTypeName, newObjectTypeName));
            }

            // 1.6 对比基础字段 - 描述
            if (saveFullReqVO.getDesc() != null && !Objects.equals(existing.getDesc(), saveFullReqVO.getDesc())) {
                String oldDesc = StrUtil.blankToDefault(existing.getDesc(), "空");
                String newDesc = StrUtil.blankToDefault(saveFullReqVO.getDesc(), "空");
                changeContent.add(StrUtil.format("描述由【{}】改为【{}】", oldDesc, newDesc));
            }

            // 1.7 对比基础字段 - 状态（ID转名称）
            if (saveFullReqVO.getStatusId() != null) {
                String oldStatusIdStr = existing.getStatusId();
                String newStatusIdStr = String.valueOf(saveFullReqVO.getStatusId());
                if (!Objects.equals(oldStatusIdStr, newStatusIdStr)) {
                    String oldStatusName = getStatusNameById(oldStatusIdStr);
                    String newStatusName = getStatusNameById(newStatusIdStr);
                    changeContent.add(StrUtil.format("状态由【{}】改为【{}】", oldStatusName, newStatusName));
                }
            }

            // 1.8 边界处理：无任何字段变更，直接返回（避免无效更新）
            if (changeContent.length() == 0) {
                log.info("指标体系{}无任何字段变更，无需更新日志和数据", systemId);
            } else {
                // 1.9 调用工具类追加日志（不覆盖原有历史日志）
                String newChangeLog = ChangeLogUtils.appendLog(existing.getChangeLog(), "【编辑】", changeContent.toString());
                // 1.10 更新体系信息
                IndexSystemDO updateObj = BeanUtils.toBean(saveFullReqVO, IndexSystemDO.class);
                // 显式设置更新人，确保自动填充生效
                Long loginUserId = WebFrameworkUtils.getLoginUserId();
                if (loginUserId != null) {
                    updateObj.setUpdater(loginUserId.toString());
                }
                updateObj.setChangeLog(newChangeLog);
                indexSystemMapper.update(updateObj, new LambdaQueryWrapper<IndexSystemDO>()
                        .eq(IndexSystemDO::getSystemId, systemId));
                log.info("指标体系{}基础信息更新完成，变更日志已追加", systemId);
            }
            // ====================== 变更日志核心逻辑 end ======================
        } else {
            // 新增体系
            systemId = generateUniqueId();
            IndexSystemDO newSystem = BeanUtils.toBean(saveFullReqVO, IndexSystemDO.class);
            newSystem.setSystemId(systemId);
            // 显式设置创建人，确保自动填充生效
            Long loginUserId = WebFrameworkUtils.getLoginUserId();
            if (loginUserId != null) {
                newSystem.setCreator(loginUserId.toString());
                newSystem.setUpdater(loginUserId.toString());
            }
            indexSystemMapper.insert(newSystem);
            // 记录变更日志
            updateChangeLog(systemId, "CREATE", StrUtil.format("体系名称：{}，体系编码：{}，版本：{}，适用对象类型：{}",
                    saveFullReqVO.getName(), saveFullReqVO.getCode(), saveFullReqVO.getVersion(),
                    saveFullReqVO.getObjectTypeId()));
        }

        // 2. 处理分类和指标项
        List<IndexSystemSaveFullReqVO.CategoryVO> categories = saveFullReqVO.getCategories();
        if (categories != null && !categories.isEmpty()) {
            // 获取现有分类的ID集合（用于比较）
            List<IndexCategoryDO> existingCategories = indexCategoryMapper.selectList(
                    new LambdaQueryWrapper<IndexCategoryDO>()
                            .eq(IndexCategoryDO::getSystemId, systemId)
                            .eq(IndexCategoryDO::getDeleted, 0)
            );
            Map<String, IndexCategoryDO> existingCategoryMap = existingCategories.stream()
                    .collect(Collectors.toMap(IndexCategoryDO::getCategoryId, c -> c));

            // 收集请求中所有分类ID
            Set<String> requestCategoryIds = categories.stream()
                    .map(IndexSystemSaveFullReqVO.CategoryVO::getCategoryId)
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toSet());

            // 删除不在请求中的分类及其指标项
            for (IndexCategoryDO existingCategory : existingCategories) {
                if (!requestCategoryIds.contains(existingCategory.getCategoryId())) {
                    // 删除分类下的指标项
                    deleteItemsByCategoryId(existingCategory.getCategoryId());
                    // 删除分类
                    indexCategoryMapper.deleteById(existingCategory.getId());
                }
            }

            // 处理每个分类
            for (IndexSystemSaveFullReqVO.CategoryVO categoryVO : categories) {
                String categoryId;
                if (StrUtil.isNotBlank(categoryVO.getCategoryId())) {
                    // 修改分类
                    categoryId = categoryVO.getCategoryId();
                    IndexCategoryDO existingCategory = existingCategoryMap.get(categoryId);
                    if (existingCategory != null) {
                        // 更新分类
                        IndexCategoryDO updateCategory = BeanUtils.toBean(categoryVO, IndexCategoryDO.class);
                        indexCategoryMapper.update(updateCategory, new LambdaQueryWrapper<IndexCategoryDO>()
                                .eq(IndexCategoryDO::getCategoryId, categoryId));
                    } else {
                        // 分类ID不存在，可能数据不一致，按新增处理
                        categoryId = generateUniqueId();
                        IndexCategoryDO newCategory = BeanUtils.toBean(categoryVO, IndexCategoryDO.class);
                        newCategory.setCategoryId(categoryId);
                        newCategory.setSystemId(systemId);
                        indexCategoryMapper.insert(newCategory);
                    }
                } else {
                    // 新增分类
                    categoryId = generateUniqueId();
                    IndexCategoryDO newCategory = BeanUtils.toBean(categoryVO, IndexCategoryDO.class);
                    newCategory.setCategoryId(categoryId);
                    newCategory.setSystemId(systemId);
                    indexCategoryMapper.insert(newCategory);
                }

                // 3. 处理指标项
                List<IndexSystemSaveFullReqVO.IndexItemVO> items = categoryVO.getItems();
                if (items != null && !items.isEmpty()) {
                    // 获取现有指标项
                    List<IndexItemDO> existingItems = indexItemMapper.selectList(
                            new LambdaQueryWrapper<IndexItemDO>()
                                    .eq(IndexItemDO::getCategoryId, categoryId)
                                    .eq(IndexItemDO::getDeleted, 0)
                    );
                    Map<String, IndexItemDO> existingItemMap = existingItems.stream()
                            .collect(Collectors.toMap(IndexItemDO::getItemId, i -> i));

                    // 收集请求中所有指标项ID
                    Set<String> requestItemIds = items.stream()
                            .map(IndexSystemSaveFullReqVO.IndexItemVO::getItemId)
                            .filter(StrUtil::isNotBlank)
                            .collect(Collectors.toSet());

                    // 删除不在请求中的指标项
                    for (IndexItemDO existingItem : existingItems) {
                        if (!requestItemIds.contains(existingItem.getItemId())) {
                            indexItemMapper.deleteById(existingItem.getId());
                        }
                    }

                    // 处理每个指标项
                    for (IndexSystemSaveFullReqVO.IndexItemVO itemVO : items) {
                        if (StrUtil.isNotBlank(itemVO.getItemId())) {
                            // 修改指标项
                            IndexItemDO existingItem = existingItemMap.get(itemVO.getItemId());
                            if (existingItem != null) {
                                IndexItemDO updateItem = BeanUtils.toBean(itemVO, IndexItemDO.class);
                                // 手动映射字段，支持传入ID或名称
                                updateItem.setIndexTypeId(parseIndexTypeId(itemVO.getIndexType()));
                                updateItem.setCalcWayId(parseCalcWayId(itemVO.getCalcWay()));
                                indexItemMapper.update(updateItem, new LambdaQueryWrapper<IndexItemDO>()
                                        .eq(IndexItemDO::getItemId, itemVO.getItemId()));
                            } else {
                                // 指标项ID不存在，按新增处理
                                String itemId = generateUniqueId();
                                IndexItemDO newItem = BeanUtils.toBean(itemVO, IndexItemDO.class);
                                newItem.setItemId(itemId);
                                newItem.setCategoryId(categoryId);
                                // 支持传入ID或名称
                                newItem.setIndexTypeId(parseIndexTypeId(itemVO.getIndexType()));
                                newItem.setCalcWayId(parseCalcWayId(itemVO.getCalcWay()));
                                indexItemMapper.insert(newItem);
                            }
                        } else {
                            // 新增指标项
                            String itemId = generateUniqueId();
                            IndexItemDO newItem = BeanUtils.toBean(itemVO, IndexItemDO.class);
                            newItem.setItemId(itemId);
                            newItem.setCategoryId(categoryId);
                            // 支持传入ID或名称
                            newItem.setIndexTypeId(parseIndexTypeId(itemVO.getIndexType()));
                            newItem.setCalcWayId(parseCalcWayId(itemVO.getCalcWay()));
                            indexItemMapper.insert(newItem);
                        }
                    }
                } else {
                    // 请求中没有指标项，删除原有指标项
                    deleteItemsByCategoryId(categoryId);
                }
            }
        } else {
            // 请求中没有分类，删除原有分类和指标项
            List<IndexCategoryDO> existingCategories = indexCategoryMapper.selectList(
                    new LambdaQueryWrapper<IndexCategoryDO>()
                            .eq(IndexCategoryDO::getSystemId, systemId)
                            .eq(IndexCategoryDO::getDeleted, 0)
            );
            for (IndexCategoryDO category : existingCategories) {
                deleteItemsByCategoryId(category.getCategoryId());
                indexCategoryMapper.deleteById(category.getId());
            }
        }

        // 4. 刷新体系统计
        refreshSystemCountsBySystemId(systemId);

        return systemId;
    }

    /**
     * 根据分类ID删除所有指标项
     */
    private void deleteItemsByCategoryId(String categoryId) {
        List<IndexItemDO> items = indexItemMapper.selectList(
                new LambdaQueryWrapper<IndexItemDO>()
                        .eq(IndexItemDO::getCategoryId, categoryId)
                        .eq(IndexItemDO::getDeleted, 0)
        );
        if (!items.isEmpty()) {
            List<Long> itemIds = items.stream()
                    .map(IndexItemDO::getId)
                    .collect(Collectors.toList());
            indexItemMapper.deleteBatchIds(itemIds);
        }
    }

    /**
     * 解析指标类型ID，支持传入typeId或typeName
     * @param indexType 指标类型（可以是ID或名称）
     * @return 解析后的typeId，如果解析失败返回原值
     */
    private String parseIndexTypeId(String indexType) {
        if (StrUtil.isBlank(indexType)) {
            return null;
        }
        // 尝试按typeId查询
        IndexTypeDO indexTypeDO = indexTypeService.getIndexTypeByTypeId(indexType);
        if (indexTypeDO != null) {
            return indexTypeDO.getTypeId();
        }
        // 尝试按name查询
        indexTypeDO = indexTypeService.getIndexTypeByName(indexType);
        if (indexTypeDO != null) {
            return indexTypeDO.getTypeId();
        }
        // 解析失败，返回原值（可能是直接传ID的情况）
        log.warn("无法解析指标类型: {}，将直接使用原值", indexType);
        return indexType;
    }

    /**
     * 解析计算方式ID，支持传入wayId或wayName
     * @param calcWay 计算方式（可以是ID或名称）
     * @return 解析后的wayId，如果解析失败返回原值
     */
    private String parseCalcWayId(String calcWay) {
        if (StrUtil.isBlank(calcWay)) {
            return null;
        }
        // 尝试按wayId查询
        CalcWayDO calcWayDO = calcWayService.getCalcWayByWayId(calcWay);
        if (calcWayDO != null) {
            return calcWayDO.getWayId();
        }
        // 尝试按name查询
        calcWayDO = calcWayService.getCalcWayByName(calcWay);
        if (calcWayDO != null) {
            return calcWayDO.getWayId();
        }
        // 解析失败，返回原值（可能是直接传ID的情况）
        log.warn("无法解析计算方式: {}，将直接使用原值", calcWay);
        return calcWay;
    }

    /**
     * 清理孤儿数据
     * 1. 删除 category 表中 systemId 在 system 表中不存在的记录
     * 2. 删除 item 表中 categoryId 在 category 表中不存在的记录
     */
    private void cleanOrphanData() {
        // 1. 获取所有有效的 systemId
        List<String> validSystemIds = indexSystemMapper.selectList(
                new LambdaQueryWrapper<IndexSystemDO>()
                        .eq(IndexSystemDO::getDeleted, 0)
        ).stream()
                .map(IndexSystemDO::getSystemId)
                .collect(Collectors.toList());

        // 2. 查询 category 表中 systemId 不在有效 systemId 集合中的记录
        if (!validSystemIds.isEmpty()) {
            List<IndexCategoryDO> orphanCategories = indexCategoryMapper.selectList(
                    new LambdaQueryWrapper<IndexCategoryDO>()
                            .eq(IndexCategoryDO::getDeleted, 0)
                            .notIn(IndexCategoryDO::getSystemId, validSystemIds)
            );

            // 删除孤儿分类及其指标项
            for (IndexCategoryDO orphanCategory : orphanCategories) {
                deleteItemsByCategoryId(orphanCategory.getCategoryId());
                indexCategoryMapper.deleteById(orphanCategory.getId());
            }
        }

        // 3. 获取所有有效的 categoryId
        List<String> validCategoryIds = indexCategoryMapper.selectList(
                new LambdaQueryWrapper<IndexCategoryDO>()
                        .eq(IndexCategoryDO::getDeleted, 0)
        ).stream()
                .map(IndexCategoryDO::getCategoryId)
                .collect(Collectors.toList());

        // 4. 查询 item 表中 categoryId 不在有效 categoryId 集合中的记录
        if (!validCategoryIds.isEmpty()) {
            List<IndexItemDO> orphanItems = indexItemMapper.selectList(
                    new LambdaQueryWrapper<IndexItemDO>()
                            .eq(IndexItemDO::getDeleted, 0)
                            .notIn(IndexItemDO::getCategoryId, validCategoryIds)
            );

            // 删除孤儿指标项
            if (!orphanItems.isEmpty()) {
                List<Long> orphanItemIds = orphanItems.stream()
                        .map(IndexItemDO::getId)
                        .collect(Collectors.toList());
                indexItemMapper.deleteBatchIds(orphanItemIds);
            }
        }
    }

    /**
     * 生成唯一ID
     */
    private String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String createFull(IndexSystemSaveFullReqVO createReqVO) {
        // 新增时清空systemId，确保走新增逻辑
        createReqVO.setSystemId(null);
        return saveFull(createReqVO);
    }

    @Override
    public void updateFull(IndexSystemSaveFullReqVO updateReqVO) {
        // 更新时必须传入systemId
        if (StrUtil.isBlank(updateReqVO.getSystemId())) {
            throw exception(INDEX_SYSTEM_NOT_EXISTS);
        }
        saveFull(updateReqVO);
    }

    @Override
    public void updateChangeLog(String systemId, String actionType, String detail) {
        if (StrUtil.isBlank(systemId)) {
            return;
        }
        // 查询当前体系
        IndexSystemDO system = indexSystemMapper.selectOne(
                new LambdaQueryWrapper<IndexSystemDO>()
                        .eq(IndexSystemDO::getSystemId, systemId)
                        .eq(IndexSystemDO::getDeleted, 0));
        if (system == null) {
            return;
        }

        // 构建操作前缀（与SubjectService保持一致）
        String prefix = switch (actionType) {
            case "CREATE" -> "【新增】";
            case "UPDATE" -> "【编辑】";
            case "DELETE" -> "【删除】";
            case "ENABLE" -> "【启用】";
            case "DISABLE" -> "【停用】";
            default -> "【操作】";
        };

        // 使用工具类构建日志（新日志在前面）
        String newChangeLog = ChangeLogUtils.appendLog(system.getChangeLog(), prefix, detail);

        // 更新体系
        system.setChangeLog(newChangeLog);
        indexSystemMapper.updateById(system);
    }

    /**
     * 根据statusId获取状态名称
     */
    private String getStatusNameById(String statusId) {
        if (StrUtil.isBlank(statusId)) {
            return "空";
        }
        StatusDO status = statusMapper.selectById(statusId);
        return status != null ? status.getName() : "未知(" + statusId + ")";
    }

    /**
     * 根据objectTypeId获取对象类型名称
     */
    private String getObjectTypeNameById(String objectTypeId) {
        if (StrUtil.isBlank(objectTypeId)) {
            return "空";
        }
        ObjectTypeDO objectType = objectTypeMapper.selectOne(
                new LambdaQueryWrapper<ObjectTypeDO>()
                        .eq(ObjectTypeDO::getTypeId, objectTypeId)
                        .eq(ObjectTypeDO::getDeleted, 0)
        );
        return objectType != null ? objectType.getName() : "未知(" + objectTypeId + ")";
    }
}