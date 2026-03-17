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
        MPJLambdaWrapper<RuleCategoryDO> wrapper = new MPJLambdaWrapper<RuleCategoryDO>()
                // ===== 主表字段 =====
                .selectAll(RuleCategoryDO.class)

                // ===== 关联表字段映射 =====
                .selectAs(IndexSystemDO::getName, RuleCategoryRespVO::getSystemName)
                .selectAs(StatusDO::getName, RuleCategoryRespVO::getStatusName)
                .selectAs(IndexItemDO::getName, RuleCategoryRespVO::getItemName)
                .selectAs(RuleTypeDO::getName, RuleCategoryRespVO::getRuleName)
                .selectAs(ObjectTypeDO::getName, RuleCategoryRespVO::getObjectTypeName)

                // ===== 联表关系 =====
                .leftJoin(IndexSystemDO.class, IndexSystemDO::getSystemId, RuleCategoryDO::getSystemId)
                .leftJoin(StatusDO.class, StatusDO::getId, RuleCategoryDO::getStatusId)
                .leftJoin(IndexItemDO.class, IndexItemDO::getId, RuleCategoryDO::getItemId)
                .leftJoin(RuleTypeDO.class, RuleTypeDO::getId, RuleCategoryDO::getRuleId)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getId, RuleCategoryDO::getObjectTypeId)

                // ===== 查询条件 =====
                .like(StrUtil.isNotBlank(reqVO.getName()), RuleCategoryDO::getName, reqVO.getName())
                .eq(reqVO.getStatusId() != null, RuleCategoryDO::getStatusId, reqVO.getStatusId())
                // 创建时间区间
                .ge(reqVO.getCreateTime() != null, RuleCategoryDO::getCreateTime, reqVO.getCreateTime() != null ? reqVO.getCreateTime()[0] : null)
                .le(reqVO.getCreateTime() != null, RuleCategoryDO::getCreateTime, reqVO.getCreateTime() != null ? reqVO.getCreateTime()[1] : null)
                // 更新时间区间
                .ge(reqVO.getUpdateTime() != null, RuleCategoryDO::getUpdateTime, reqVO.getUpdateTime() != null ? reqVO.getUpdateTime()[0] : null)
                .le(reqVO.getUpdateTime() != null, RuleCategoryDO::getUpdateTime, reqVO.getUpdateTime() != null ? reqVO.getUpdateTime()[1] : null)

                // ===== 排序 =====
                .orderByDesc(RuleCategoryDO::getId);

        // 3. 执行联表分页查询
        IPage<RuleCategoryRespVO> resultPage = selectJoinPage(page, RuleCategoryRespVO.class, wrapper);

        // 4. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    // 批量查询指标体系名称
    @Select("<script>" +
            "SELECT system_id, name FROM eval_index_system WHERE deleted = 0 AND system_id IN " +
            "<foreach collection='systemIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<IndexSystemDO> selectSystemByIds(@Param("systemIds") List<String> systemIds);

    // 批量查询状态名称
    @Select("<script>" +
            "SELECT status_id, name FROM sys_status WHERE deleted = 0 AND status_id IN " +
            "<foreach collection='statusIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<StatusDO> selectStatusByIds(@Param("statusIds") List<String> statusIds);

    // 批量查询指标项名称
    @Select("<script>" +
            "SELECT item_id, name FROM eval_index_item WHERE deleted = 0 AND item_id IN " +
            "<foreach collection='itemIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<IndexItemDO> selectItemByIds(@Param("itemIds") List<String> itemIds);

    // 批量查询规则类型名称
    @Select("<script>" +
            "SELECT type_id, name FROM sys_rule_type WHERE deleted = 0 AND type_id IN " +
            "<foreach collection='ruleIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<RuleTypeDO> selectRuleTypeByIds(@Param("ruleIds") List<String> ruleIds);

    // 批量查询对象类型名称
    @Select("<script>" +
            "SELECT type_id, name FROM sys_object_type WHERE deleted = 0 AND type_id IN " +
            "<foreach collection='objectTypeIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<ObjectTypeDO> selectObjectTypeByIds(@Param("objectTypeIds") List<String> objectTypeIds);

}