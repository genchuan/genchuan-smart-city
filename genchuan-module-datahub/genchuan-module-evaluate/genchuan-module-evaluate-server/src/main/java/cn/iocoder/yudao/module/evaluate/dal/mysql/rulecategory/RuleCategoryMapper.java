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

}