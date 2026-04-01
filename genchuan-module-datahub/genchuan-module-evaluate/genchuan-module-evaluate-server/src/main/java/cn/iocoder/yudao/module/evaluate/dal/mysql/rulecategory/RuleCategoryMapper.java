package cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 规则分类管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RuleCategoryMapper extends BaseMapperX<RuleCategoryDO> {

    default PageResult<RuleCategoryDO> selectPage(RuleCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleCategoryDO>()
                .likeIfPresent(RuleCategoryDO::getName, reqVO.getName())
                .eqIfPresent(RuleCategoryDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(RuleCategoryDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(RuleCategoryDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(RuleCategoryDO::getId));
    }

    /**
     * 联表分页查询（使用 MPJ 插件）
     */
    default PageResult<RuleCategoryRespVO> selectJoinPage(RuleCategoryPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<RuleCategoryRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<RuleCategoryDO> wrapper = new MPJLambdaWrapper<RuleCategoryDO>();
        wrapper.selectAll(RuleCategoryDO.class);
        wrapper.selectAs(IndexSystemDO::getName, RuleCategoryRespVO::getSystemName);
        wrapper.selectAs(IndexSystemDO::getId, RuleCategoryRespVO::getSystemIdPk);
        wrapper.selectAs(StatusDO::getName, RuleCategoryRespVO::getStatusName);
        wrapper.selectAs(StatusDO::getId, RuleCategoryRespVO::getStatusIdPk);
        wrapper.selectAs(IndexItemDO::getName, RuleCategoryRespVO::getItemName);
        wrapper.selectAs(IndexItemDO::getId, RuleCategoryRespVO::getItemIdPk);
        wrapper.selectAs(RuleTypeDO::getName, RuleCategoryRespVO::getRuleName);
        wrapper.selectAs(RuleTypeDO::getId, RuleCategoryRespVO::getRuleIdPk);
        wrapper.selectAs(ObjectTypeDO::getName, RuleCategoryRespVO::getObjectTypeName);
        wrapper.selectAs(ObjectTypeDO::getId, RuleCategoryRespVO::getObjectTypeIdPk);
        wrapper.leftJoin(IndexSystemDO.class, IndexSystemDO::getId, RuleCategoryDO::getSystemId);
        wrapper.leftJoin(StatusDO.class, StatusDO::getId, RuleCategoryDO::getStatusId);
        wrapper.leftJoin(IndexItemDO.class, IndexItemDO::getId, RuleCategoryDO::getItemId);
        wrapper.leftJoin(RuleTypeDO.class, RuleTypeDO::getId, RuleCategoryDO::getRuleId);
        wrapper.leftJoin(ObjectTypeDO.class, ObjectTypeDO::getId, RuleCategoryDO::getObjectTypeId);
        wrapper.like(StrUtil.isNotBlank(reqVO.getName()), RuleCategoryDO::getName, reqVO.getName());
        wrapper.like(StrUtil.isNotBlank(reqVO.getSystemName()), IndexSystemDO::getName, reqVO.getSystemName());
        wrapper.eq(reqVO.getStatusId() != null, RuleCategoryDO::getStatusId, reqVO.getStatusId());
        wrapper.ge(reqVO.getCreateTime() != null, RuleCategoryDO::getCreateTime, reqVO.getCreateTime() != null ? reqVO.getCreateTime()[0] : null);
        wrapper.le(reqVO.getCreateTime() != null, RuleCategoryDO::getCreateTime, reqVO.getCreateTime() != null ? reqVO.getCreateTime()[1] : null);
        wrapper.ge(reqVO.getUpdateTime() != null, RuleCategoryDO::getUpdateTime, reqVO.getUpdateTime() != null ? reqVO.getUpdateTime()[0] : null);
        wrapper.le(reqVO.getUpdateTime() != null, RuleCategoryDO::getUpdateTime, reqVO.getUpdateTime() != null ? reqVO.getUpdateTime()[1] : null);
        wrapper.orderByDesc(RuleCategoryDO::getId);// ===== 主表字段 =====

        // 3. 执行联表分页查询
        IPage<RuleCategoryRespVO> resultPage = selectJoinPage(page, RuleCategoryRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    // 批量查询指标体系名称（使用主键ID查询）
    @Select("<script>" +
            "SELECT id, name FROM eval_index_system WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<IndexSystemDO> selectSystemByIds(@Param("ids") List<Long> ids);

    // 批量查询状态名称（使用主键ID查询）
    @Select("<script>" +
            "SELECT id, name FROM sys_status WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<StatusDO> selectStatusByIds(@Param("ids") List<Long> ids);

    // 批量查询指标项名称（使用主键ID查询）
    @Select("<script>" +
            "SELECT id, name FROM eval_index_item WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<IndexItemDO> selectItemByIds(@Param("ids") List<Long> ids);

    // 批量查询规则类型名称（使用主键ID查询）
    @Select("<script>" +
            "SELECT id, name FROM sys_rule_type WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<RuleTypeDO> selectRuleTypeByIds(@Param("ids") List<Long> ids);

    // 批量查询对象类型名称（使用主键ID查询）
    @Select("<script>" +
            "SELECT id, name FROM sys_object_type WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<ObjectTypeDO> selectObjectTypeByIds(@Param("ids") List<Long> ids);

    /**
     * 根据规则分类名称和体系ID查询（用于校验名称唯一性）
     */
    default RuleCategoryDO selectByNameAndSystemId(String name, String systemId) {
        return selectOne(new LambdaQueryWrapperX<RuleCategoryDO>()
                .eq(RuleCategoryDO::getName, name)
                .eq(RuleCategoryDO::getSystemId, systemId));
    }

    // ==================== 统计查询 ====================

    /**
     * 统计规则分类总数和规则总数
     */
    @Select("SELECT COUNT(DISTINCT rc.id) AS categoryCount, " +
            "COUNT(DISTINCT cr.id) AS ruleCount " +
            "FROM eval_rule_category rc " +
            "LEFT JOIN eval_comment_rule cr ON rc.id = cr.rule_category_id AND cr.deleted = 0 " +
            "WHERE rc.deleted = 0")
    Map<String, Object> selectStatisticsCount();

    /**
     * 统计指标项总数
     */
    @Select("SELECT COUNT(*) AS indexItemCount FROM eval_index_item WHERE deleted = 0")
    Map<String, Object> selectIndexItemCount();

    /**
     * 按规则分类ID统计每个分类下的指标项数量
     */
    @Select("SELECT comment_category_id, COUNT(*) AS indexItemCount " +
            "FROM eval_index_item WHERE deleted = 0 AND comment_category_id IS NOT NULL " +
            "GROUP BY comment_category_id")
    List<Map<String, Object>> selectCategoryIndexItemCount();

    /**
     * 按规则ID统计每个规则的指标项数量
     */
    @Select("SELECT comment_rule_id, COUNT(*) AS indexItemCount " +
            "FROM eval_index_item WHERE deleted = 0 AND comment_rule_id IS NOT NULL " +
            "GROUP BY comment_rule_id")
    List<Map<String, Object>> selectRuleIndexItemCount();

    /**
     * 统计启用状态（status=1）的规则总数
     */
    @Select("SELECT COUNT(*) AS enabledRuleCount " +
            "FROM eval_comment_rule WHERE deleted = 0 AND status = 1")
    Map<String, Object> selectEnabledRuleCount();

    /**
     * 按规则类型（rule_type）分组统计规则数量
     */
    @Select("SELECT rule_type, COUNT(*) AS ruleCount " +
            "FROM eval_comment_rule WHERE deleted = 0 AND rule_type IS NOT NULL " +
            "GROUP BY rule_type")
    List<Map<String, Object>> selectRuleTypeGroupCount();

    /**
     * 按适用对象类型（apply_object_type）分组统计规则数量
     */
    @Select("SELECT apply_object_type, COUNT(*) AS ruleCount " +
            "FROM eval_comment_rule WHERE deleted = 0 AND apply_object_type IS NOT NULL AND apply_object_type != '' " +
            "GROUP BY apply_object_type")
    List<Map<String, Object>> selectApplyObjectTypeGroupCount();

    /**
     * 按状态（status）分组统计规则数量
     */
    @Select("SELECT status, COUNT(*) AS ruleCount " +
            "FROM eval_comment_rule WHERE deleted = 0 AND status IS NOT NULL " +
            "GROUP BY status")
    List<Map<String, Object>> selectStatusGroupCount();

    /**
     * 按规则分类ID（rule_category_id）分组统计规则数量
     */
    @Select("SELECT rule_category_id, COUNT(*) AS ruleCount " +
            "FROM eval_comment_rule WHERE deleted = 0 AND rule_category_id IS NOT NULL " +
            "GROUP BY rule_category_id")
    List<Map<String, Object>> selectCategoryRuleCount();

    /**
     * 批量查询规则分类的 item_count（直接从 eval_rule_category 表读取）
     */
    @Select("<script>" +
            "SELECT id, IFNULL(item_count, 0) AS itemCount " +
            "FROM eval_rule_category " +
            "WHERE deleted = 0 " +
            "<if test='categoryIds != null and categoryIds.size() > 0'>" +
            "  AND id IN " +
            "  <foreach collection='categoryIds' item='cid' open='(' separator=',' close=')'>#{cid}</foreach>" +
            "</if>" +
            "</script>")
    List<Map<String, Object>> selectItemCountByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

}