package cn.iocoder.yudao.module.data.dal.mysql.mattercategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategoryPageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.MatterCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理事项分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MatterCategoryMapper extends BaseMapperX<MatterCategoryDO> {

    default PageResult<MatterCategoryDO> selectPage(MatterCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MatterCategoryDO>()
                .eqIfPresent(MatterCategoryDO::getMatterCategoryId, reqVO.getMatterCategoryId())
                .likeIfPresent(MatterCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(MatterCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(MatterCategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(MatterCategoryDO::getParentName, reqVO.getParentName())
                .eqIfPresent(MatterCategoryDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(MatterCategoryDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MatterCategoryDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(MatterCategoryDO::getWorkflowId, reqVO.getWorkflowId())
                .eqIfPresent(MatterCategoryDO::getWorkflowCode, reqVO.getWorkflowCode())
                .eqIfPresent(MatterCategoryDO::getWorkflowDesc, reqVO.getWorkflowDesc())
                .eqIfPresent(MatterCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .likeIfPresent(MatterCategoryDO::getCategoryType, reqVO.getCategoryType())
                .likeIfPresent(MatterCategoryDO::getStatus, reqVO.getStatus())
                .likeIfPresent(MatterCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(MatterCategoryDO::getRelatedMatterCount, reqVO.getRelatedMatterCount())
                .eqIfPresent(MatterCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(MatterCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MatterCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MatterCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MatterCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MatterCategoryDO::getId));
    }

    /**
     * 根据分类ID列表分页查询
     * @param reqVO 分页参数
     * @param categoryIds 分类ID列表
     * @return 分页结果
     */
    default PageResult<MatterCategoryDO> selectPageByCategoryIds(MatterCategoryPageReqVO reqVO, List<Long> categoryIds) {
        LambdaQueryWrapperX<MatterCategoryDO> queryWrapper = new LambdaQueryWrapperX<MatterCategoryDO>()
                .eqIfPresent(MatterCategoryDO::getMatterCategoryId, reqVO.getMatterCategoryId())
                .likeIfPresent(MatterCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(MatterCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(MatterCategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(MatterCategoryDO::getParentName, reqVO.getParentName())
                .eqIfPresent(MatterCategoryDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(MatterCategoryDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MatterCategoryDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(MatterCategoryDO::getWorkflowId, reqVO.getWorkflowId())
                .eqIfPresent(MatterCategoryDO::getWorkflowCode, reqVO.getWorkflowCode())
                .eqIfPresent(MatterCategoryDO::getWorkflowDesc, reqVO.getWorkflowDesc())
                .eqIfPresent(MatterCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .likeIfPresent(MatterCategoryDO::getCategoryType, reqVO.getCategoryType())
                .likeIfPresent(MatterCategoryDO::getStatus, reqVO.getStatus())
                .likeIfPresent(MatterCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(MatterCategoryDO::getRelatedMatterCount, reqVO.getRelatedMatterCount())
                .eqIfPresent(MatterCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(MatterCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MatterCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MatterCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MatterCategoryDO::getCreateTime, reqVO.getCreateTime())
                .in(MatterCategoryDO::getId, categoryIds)  // 关键：按ID列表查询
                .orderByDesc(MatterCategoryDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

    /**
     * 查询所有事项分类（用于构建树）
     * @return 全部分类列表
     */
    default List<MatterCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapperX<MatterCategoryDO>()
                .orderByAsc(MatterCategoryDO::getParentId) // 先按父ID排序，便于构建树
                .orderByAsc(MatterCategoryDO::getId));
    }

    /**
     * 根据父ID查询子节点数量
     *
     * @param parentId 父节点ID
     * @return 子节点数量
     */
    Long selectCountByParentId(@Param("parentId") String parentId);

}