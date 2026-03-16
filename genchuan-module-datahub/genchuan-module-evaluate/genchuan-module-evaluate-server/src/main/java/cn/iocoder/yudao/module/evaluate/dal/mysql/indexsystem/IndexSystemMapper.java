package cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

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
     * 根据体系ID查询体系详情（基本信息）
     */
    IndexSystemDetailVO.BaseInfo selectDetailBaseInfo(@Param("systemId") String systemId);

    /**
     * 查询指定体系下的所有分类
     */
    List<IndexSystemDetailVO.CategoryVO> selectCategoriesBySystemId(@Param("systemId") String systemId);

    /**
     * 查询指定分类下的所有指标项（带字典信息）
     */
    List<IndexSystemDetailVO.IndexItemVO> selectItemsByCategoryIds(@Param("categoryIds") List<String> categoryIds);

    /**
     * 校验分类权重总和
     */
    Double selectCategoryWeightSum(@Param("systemId") String systemId);

    /**
     * 校验指标项权重总和
     */
    Double selectItemWeightSum(@Param("categoryId") String categoryId);
    default PageResult<IndexSystemRespVO> selectSystemJoinPage(IndexSystemPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<IndexSystemRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 构建查询条件
        MPJLambdaWrapper<IndexSystemDO> wrapper = new MPJLambdaWrapper<IndexSystemDO>()
                .selectAll(IndexSystemDO.class)
                // 关联适用对象类型表
                .selectAs(ObjectTypeDO::getName, IndexSystemRespVO::getObjectTypeName)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, IndexSystemDO::getObjectTypeId)
                // ========== 关联指标分类表 ==========
                .selectAs(IndexCategoryDO::getName, IndexSystemRespVO::getCategoryName)
                .selectAs(IndexCategoryDO::getWeight, IndexSystemRespVO::getCategoryWeight) // 分类权重
                .selectAs(IndexCategoryDO::getSortNo, IndexSystemRespVO::getSortNo)
                .leftJoin(IndexCategoryDO.class, IndexCategoryDO::getSystemId, IndexSystemDO::getSystemId) // 修正关联条件：分类表关联体系ID

                // ========== 关联指标项表 ==========
                .selectAs(IndexItemDO::getName, IndexSystemRespVO::getItemName)
                .selectAs(IndexItemDO::getThreshold, IndexSystemRespVO::getThreshold) // 达标阈值
                .selectAs(IndexItemDO::getWeight, IndexSystemRespVO::getItemWeight) // 指标项权重
                .leftJoin(IndexItemDO.class, IndexItemDO::getCategoryId, IndexCategoryDO::getCategoryId)

                // ========== 关联指标类型字典表 ==========
                .selectAs(IndexTypeDO::getName, IndexSystemRespVO::getIndexTypeName) // 指标类型名称
                .leftJoin(IndexTypeDO.class, IndexTypeDO::getTypeId, IndexItemDO::getIndexTypeId)

                // ========== 关联计算方式字典表 ==========
                .selectAs(CalcWayDO::getName, IndexSystemRespVO::getCalcWayName) // 计算方式名称
                .leftJoin(CalcWayDO.class, CalcWayDO::getWayId, IndexItemDO::getCalcWayId)
                // 关联状态表
                .selectAs(StatusDO::getName, IndexSystemRespVO::getStatusName)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, IndexSystemDO::getStatusId)
                // 关联创建人用户表
                .selectAs("creator", UserDO::getUserName, IndexSystemRespVO::getCreateUserName)
                .leftJoin(UserDO.class, "creator", UserDO::getUserId, IndexSystemDO::getCreateBy)
                // 关联更新人用户表
                .selectAs("updater", UserDO::getUserName, IndexSystemRespVO::getUpdateUserName)
                .leftJoin(UserDO.class, "updater", UserDO::getUserId, IndexSystemDO::getUpdateBy)
                // 动态条件：体系本身字段
                .like(StrUtil.isNotBlank(reqVO.getName()), IndexSystemDO::getName, reqVO.getName())
                .eq(StrUtil.isNotBlank(reqVO.getCode()), IndexSystemDO::getCode, reqVO.getCode())
                .eq(reqVO.getObjectTypeId() != null, IndexSystemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eq(reqVO.getStatusId() != null, IndexSystemDO::getStatusId, reqVO.getStatusId())
                .eq(StrUtil.isNotBlank(reqVO.getVersion()), IndexSystemDO::getVersion, reqVO.getVersion());

        // 3. 分类名称筛选（使用 EXISTS 子查询）
        if (StrUtil.isNotBlank(reqVO.getCategoryName())) {
            wrapper.exists("SELECT 1 FROM eval_index_category c WHERE c.system_id = t.id AND c.name LIKE CONCAT('%', {0}, '%')",
                    reqVO.getCategoryName());
        }

        // 4. 指标项名称筛选（使用 EXISTS 子查询）
        if (StrUtil.isNotBlank(reqVO.getItemName())) {
            wrapper.exists("SELECT 1 FROM eval_index_item i WHERE i.system_id = t.id AND i.name LIKE CONCAT('%', {0}, '%')",
                    reqVO.getItemName());
        }

        // 5. 排序
        wrapper.orderByDesc(IndexSystemDO::getCreateTime);

        // 6. 执行查询
        IPage<IndexSystemRespVO> resultPage = selectJoinPage(page, IndexSystemRespVO.class, wrapper);

        // 7. 后处理：变更日志截取前50字
        resultPage.getRecords().forEach(vo -> {
            if (vo.getChangeLog() != null) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50)
                        : vo.getChangeLog());
            }
            // 兜底：避免权重/阈值为null时前端报错
            if (vo.getCategoryWeight() == null) vo.setCategoryWeight(BigDecimal.ZERO);
            if (vo.getItemWeight() == null) vo.setItemWeight(BigDecimal.ZERO);
            if (vo.getThreshold() == null) vo.setThreshold(BigDecimal.ZERO);
        });

        // 8. 返回分页结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

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