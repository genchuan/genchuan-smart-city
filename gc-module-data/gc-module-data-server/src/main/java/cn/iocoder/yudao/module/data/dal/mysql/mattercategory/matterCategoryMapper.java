package cn.iocoder.yudao.module.data.dal.mysql.mattercategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 管理事项分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface matterCategoryMapper extends BaseMapperX<matterCategoryDO> {

    default PageResult<matterCategoryDO> selectPage(matterCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<matterCategoryDO>()
                .eqIfPresent(matterCategoryDO::getMatterCategoryId, reqVO.getMatterCategoryId())
                .likeIfPresent(matterCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(matterCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(matterCategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(matterCategoryDO::getParentName, reqVO.getParentName())
                .eqIfPresent(matterCategoryDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(matterCategoryDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(matterCategoryDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(matterCategoryDO::getWorkflowId, reqVO.getWorkflowId())
                .eqIfPresent(matterCategoryDO::getWorkflowCode, reqVO.getWorkflowCode())
                .eqIfPresent(matterCategoryDO::getWorkflowDesc, reqVO.getWorkflowDesc())
                .eqIfPresent(matterCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .likeIfPresent(matterCategoryDO::getCategoryTypeName, reqVO.getCategoryTypeName())
                .likeIfPresent(matterCategoryDO::getStatusName, reqVO.getStatusName())
                .likeIfPresent(matterCategoryDO::getAuditStatusName, reqVO.getAuditStatusName())
                .eqIfPresent(matterCategoryDO::getRelatedMatterCount, reqVO.getRelatedMatterCount())
                .eqIfPresent(matterCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(matterCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(matterCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(matterCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(matterCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(matterCategoryDO::getId));
    }

    /**
     * 根据分类ID列表分页查询
     * @param reqVO 分页参数
     * @param categoryIds 分类ID列表
     * @return 分页结果
     */
    default PageResult<matterCategoryDO> selectPageByCategoryIds(matterCategoryPageReqVO reqVO, List<Long> categoryIds) {
        LambdaQueryWrapperX<matterCategoryDO> queryWrapper = new LambdaQueryWrapperX<matterCategoryDO>()
                .eqIfPresent(matterCategoryDO::getMatterCategoryId, reqVO.getMatterCategoryId())
                .likeIfPresent(matterCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(matterCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(matterCategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(matterCategoryDO::getParentName, reqVO.getParentName())
                .eqIfPresent(matterCategoryDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(matterCategoryDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(matterCategoryDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(matterCategoryDO::getWorkflowId, reqVO.getWorkflowId())
                .eqIfPresent(matterCategoryDO::getWorkflowCode, reqVO.getWorkflowCode())
                .eqIfPresent(matterCategoryDO::getWorkflowDesc, reqVO.getWorkflowDesc())
                .eqIfPresent(matterCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .likeIfPresent(matterCategoryDO::getCategoryTypeName, reqVO.getCategoryTypeName())
                .likeIfPresent(matterCategoryDO::getStatusName, reqVO.getStatusName())
                .likeIfPresent(matterCategoryDO::getAuditStatusName, reqVO.getAuditStatusName())
                .eqIfPresent(matterCategoryDO::getRelatedMatterCount, reqVO.getRelatedMatterCount())
                .eqIfPresent(matterCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(matterCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(matterCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(matterCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(matterCategoryDO::getCreateTime, reqVO.getCreateTime())
                .in(matterCategoryDO::getId, categoryIds)  // 关键：按ID列表查询
                .orderByDesc(matterCategoryDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

    /**
     * 查询所有事项分类（用于构建树）
     * @return 全部分类列表
     */
    default List<matterCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapperX<matterCategoryDO>()
                .orderByAsc(matterCategoryDO::getParentId) // 先按父ID排序，便于构建树
                .orderByAsc(matterCategoryDO::getId));
    }

    /**
     * 根据父ID查询子节点数量
     *
     * @param parentId 父节点ID
     * @return 子节点数量
     */
    Long selectCountByParentId(@Param("parentId") String parentId);

}