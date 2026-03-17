package cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 指标体系 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndexSystemMapper extends BaseMapperX<IndexSystemDO> {

    default PageResult<IndexSystemDO> selectPage(IndexSystemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexSystemDO>()
                .eqIfPresent(IndexSystemDO::getSystemId, reqVO.getSystemId())
                .likeIfPresent(IndexSystemDO::getName, reqVO.getName())
                .eqIfPresent(IndexSystemDO::getCode, reqVO.getCode())
                .eqIfPresent(IndexSystemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(IndexSystemDO::getVersion, reqVO.getVersion())
                .eqIfPresent(IndexSystemDO::getDesc, reqVO.getDesc())
                .eqIfPresent(IndexSystemDO::getCategoryCount, reqVO.getCategoryCount())
                .eqIfPresent(IndexSystemDO::getItemCount, reqVO.getItemCount())
                .eqIfPresent(IndexSystemDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(IndexSystemDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(IndexSystemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(IndexSystemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(IndexSystemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndexSystemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndexSystemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IndexSystemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(IndexSystemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexSystemDO::getId));
    }
    /**
     * 联表分页查询指标体系列表（带关联字典信息）
     */
    PageResult<IndexSystemPageItemVO> selectPageWithJoin(@Param("reqVO") IndexSystemPageReqVO reqVO);

    /**
     * 统计符合条件的唯一体系数量（用于分页计数）
     */
    Long selectCountWithJoin(@Param("reqVO") IndexSystemPageReqVO reqVO);

    /**
     * 根据主键ID查询体系详情（基本信息）
     */
    IndexSystemDetailVO.BaseInfo selectDetailBaseInfo(@Param("id") Long id);

    /**
     * 查询指定体系下的所有分类
     */
    List<IndexSystemDetailVO.CategoryVO> selectCategoriesBySystemId(@Param("systemUuid") String systemUuid);

    /**
     * 查询指定分类下的所有指标项（带字典信息）
     */
    List<IndexSystemDetailVO.IndexItemVO> selectItemsByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    /**
     * 校验分类权重总和
     */
    Double selectCategoryWeightSum(@Param("systemId") String systemId);

    /**
     * 校验指标项权重总和
     */
    Double selectItemWeightSum(@Param("categoryId") String categoryId);
    /**
     * 联表分页查询指标体系列表（先按主表分页，再关联查询）
     * 分页逻辑：先按 eval_index_system 主表分页获取 system_id，再关联查询其他表数据
     */
    default PageResult<IndexSystemRespVO> selectSystemJoinPage(IndexSystemPageReqVO reqVO) {
        // 1. 先按主表分页查询，获取分页后的体系数据
        Page<IndexSystemDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 构建主表查询条件
        LambdaQueryWrapperX<IndexSystemDO> wrapper = new LambdaQueryWrapperX<IndexSystemDO>()
                .likeIfPresent(IndexSystemDO::getName, reqVO.getName())
                .eqIfPresent(IndexSystemDO::getCode, reqVO.getCode())
                .eqIfPresent(IndexSystemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(IndexSystemDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(IndexSystemDO::getVersion, reqVO.getVersion())
                .eqIfPresent(IndexSystemDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(IndexSystemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexSystemDO::getCreateTime);

        // 分类名称筛选（使用子查询）
        if (StrUtil.isNotBlank(reqVO.getCategoryName())) {
            wrapper.exists("SELECT 1 FROM eval_index_category c WHERE c.system_id = t.system_id AND c.deleted = 0 AND c.name LIKE CONCAT('%', {0}, '%')",
                    reqVO.getCategoryName());
        }

        // 指标项名称筛选（使用子查询）
        if (StrUtil.isNotBlank(reqVO.getItemName())) {
            wrapper.exists("SELECT 1 FROM eval_index_category c WHERE c.system_id = t.system_id AND c.deleted = 0 " +
                    "AND EXISTS (SELECT 1 FROM eval_index_item i WHERE i.category_id = c.category_id AND i.deleted = 0 AND i.name LIKE CONCAT('%', {0}, '%'))",
                    reqVO.getItemName());
        }

        // 查询主表分页数据
        IPage<IndexSystemDO> systemPage = selectPage(page, wrapper);

        // 如果没有数据，直接返回空结果
        if (systemPage.getRecords() == null || systemPage.getRecords().isEmpty()) {
            return new PageResult<>(new ArrayList<>(), systemPage.getTotal());
        }

        // 2. 获取分页后的 systemId 列表，并批量更新统计字段
        List<String> systemIds = systemPage.getRecords().stream()
                .map(IndexSystemDO::getSystemId)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());

        if (!systemIds.isEmpty()) {
            // 批量更新分类总数和指标项总数
            batchUpdateCounts(systemIds);
            // 重新查询更新后的数据
            systemPage = selectPage(new Page<>(reqVO.getPageNo(), reqVO.getPageSize()),
                    new LambdaQueryWrapperX<IndexSystemDO>()
                            .in(IndexSystemDO::getSystemId, systemIds)
                            .orderByDesc(IndexSystemDO::getCreateTime));
        }

        // 3. 收集需要查询的关联ID
        List<String> objectTypeIds = systemPage.getRecords().stream()
                .map(IndexSystemDO::getObjectTypeId)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .collect(Collectors.toList());

        List<String> statusIds = systemPage.getRecords().stream()
                .map(IndexSystemDO::getStatusId)
                .filter(StrUtil::isNotBlank)
                .distinct()
                .collect(Collectors.toList());

        // 3. 批量查询关联数据
        // 3.1 查询适用对象类型
        Map<String, String> objectTypeMap;
        if (!objectTypeIds.isEmpty()) {
            List<ObjectTypeDO> objectTypes = selectObjectTypeByIds(objectTypeIds);
            objectTypeMap = objectTypes.stream()
                    .collect(Collectors.toMap(ObjectTypeDO::getTypeId, ObjectTypeDO::getName, (a, b) -> a));
        } else {
            objectTypeMap = new HashMap<>();
        }

        // 3.2 查询状态
        Map<String, String> statusMap;
        if (!statusIds.isEmpty()) {
            List<StatusDO> statusList = selectStatusByIds(statusIds);
            statusMap = statusList.stream()
                    .collect(Collectors.toMap(StatusDO::getStatusId, StatusDO::getName, (a, b) -> a));
        } else {
            statusMap = new HashMap<>();
        }

        // 4. 转换结果
        List<IndexSystemRespVO> resultList = systemPage.getRecords().stream().map(system -> {
            IndexSystemRespVO vo = BeanUtils.toBean(system, IndexSystemRespVO.class);

            // 填充关联的字典数据
            vo.setObjectTypeName(objectTypeMap.get(system.getObjectTypeId()));
            vo.setStatusName(statusMap.get(system.getStatusId()));

            // 兜底：避免权重/阈值为null时前端报错
            if (vo.getCategoryWeight() == null) vo.setCategoryWeight(BigDecimal.ZERO);
            if (vo.getItemWeight() == null) vo.setItemWeight(BigDecimal.ZERO);
            if (vo.getThreshold() == null) vo.setThreshold(BigDecimal.ZERO);

            // 变更日志截取前50字
            if (vo.getChangeLog() != null && vo.getChangeLog().length() > 50) {
                vo.setChangeLogShort(vo.getChangeLog().substring(0, 50));
            } else {
                vo.setChangeLogShort(vo.getChangeLog());
            }

            return vo;
        }).collect(Collectors.toList());

        // 5. 返回分页结果
        return new PageResult<>(resultList, systemPage.getTotal());
    }

    // 批量查询适用对象类型
    @Select("<script>" +
            "SELECT type_id, name FROM sys_object_type WHERE deleted = 0 AND type_id IN " +
            "<foreach collection='typeIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<ObjectTypeDO> selectObjectTypeByIds(@Param("typeIds") List<String> typeIds);

    // 批量查询状态
    @Select("<script>" +
            "SELECT status_id, name FROM sys_status WHERE deleted = 0 AND status_id IN " +
            "<foreach collection='statusIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<StatusDO> selectStatusByIds(@Param("statusIds") List<String> statusIds);

    // 批量更新分类总数和指标项总数
    @Update("<script>" +
            "UPDATE eval_index_system s SET " +
            "category_count = (SELECT COUNT(*) FROM eval_index_category c WHERE c.system_id = s.system_id AND c.deleted = 0), " +
            "item_count = COALESCE((SELECT COUNT(*) FROM eval_index_category c " +
            "   INNER JOIN eval_index_item i ON i.category_id = c.category_id AND i.deleted = 0 " +
            "   WHERE c.system_id = s.system_id AND c.deleted = 0), 0) " +
            "WHERE s.system_id IN " +
            "<foreach collection='systemIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            " AND s.deleted = 0" +
            "</script>")
    void batchUpdateCounts(@Param("systemIds") List<String> systemIds);

    // ========== 1. 卡片核心数据（总体系数、启用体系数、指标项总数） ==========
    @Select("SELECT " +
            "COUNT(*) AS totalSystemCount, " +
            "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS enableSystemCount, " +
            "SUM(item_count) AS totalItemCount " + // 所有体系的指标项总数求和
            "FROM eval_index_system " +
            "WHERE deleted = 0")
    IndexSystemOverviewVO.CardData selectCardCoreData();

    // ========== 2. 各版本体系数（卡片子项） ==========
    @Select("SELECT " +
            "version AS version, " +
            "COUNT(*) AS count " +
            "FROM eval_index_system " +
            "WHERE deleted = 0 " +
            "GROUP BY version " +
            "ORDER BY count DESC")
    List<IndexSystemOverviewVO.VersionCountItem> selectVersionCounts();

    // ========== 3. 适用对象类型占比（圆环图） ==========
    @Select("SELECT " +
            "t.name AS name, " +
            "COUNT(*) AS value " +
            "FROM eval_index_system o " +
            "LEFT JOIN sys_object_type t ON o.object_type_id = t.type_id " + // 关联适用对象类型表
            "WHERE o.deleted = 0 " +
            "GROUP BY o.object_type_id, t.name")
    List<IndexSystemOverviewVO.PieChartItem> selectObjectTypePieChart();

    // ========== 4. 指标类型占比（圆环图） ==========
    // 注：sys_index_type 为指标类型表（需确认实际表名/字段，字段建议：type_id/name）

    /**
     *
     * @return
     */
    @Select("SELECT " +
            "it.name AS name, " +
            "COUNT(*) AS value " +
            "FROM eval_index_system o " +
            "LEFT JOIN sys_index_type it ON o.index_type_id = it.type_id " + // 指标类型ID存在index_type_id
            "WHERE o.deleted = 0 " +
            "GROUP BY o.index_type_id, it.name")
    List<IndexSystemOverviewVO.PieChartItem> selectIndexTypePieChart();

    // ========== 5. 分类权重分布（圆环图） ==========
    // 注：eval_index_category 为指标分类表（分类权重表）（需确认实际表名/字段，字段建议：name/weight）
    @Select("SELECT " +
            "cw.name AS name, " +
            "cw.weight AS value " +
            "FROM eval_index_system o " +
            "LEFT JOIN eval_index_category cw ON o.system_id = cw.system_id " + // 关联指标体系ID
            "WHERE o.deleted = 0 " +
            "GROUP BY cw.name, cw.weight")
    List<IndexSystemOverviewVO.PieChartItem> selectCategoryWeightPieChart();

    // ========== 6. 各体系指标项数量对比（基础柱状图） ==========
    @Select("SELECT " +
            "name AS systemName, " +
            "item_count AS itemCount " +
            "FROM eval_index_system " +
            "WHERE deleted = 0 " +
            "ORDER BY item_count DESC")
    List<IndexSystemOverviewVO.BarChartItem> selectSystemItemCountBarChart();
}