package cn.iocoder.yudao.module.data.dal.mysql.scenecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategoryPageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.scenecategory.SceneCategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 应用场景分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface SceneCategoryMapper extends BaseMapperX<SceneCategoryDO> {

    default PageResult<SceneCategoryDO> selectPage(SceneCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SceneCategoryDO>()
                .likeIfPresent(SceneCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(SceneCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(SceneCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(SceneCategoryDO::getParentCategory, reqVO.getParentCategory())
                .eqIfPresent(SceneCategoryDO::getApplicableArea, reqVO.getApplicableArea())
                .eqIfPresent(SceneCategoryDO::getDataType, reqVO.getDataType())
                .eqIfPresent(SceneCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .eqIfPresent(SceneCategoryDO::getCategoryType, reqVO.getCategoryType())
                .eqIfPresent(SceneCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(SceneCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(SceneCategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(SceneCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(SceneCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SceneCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(SceneCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SceneCategoryDO::getId));
    }

    // 新增方法：根据ID列表查询分类
    default List<SceneCategoryDO> selectListByIds(List<Long> ids) {
        return selectList(new LambdaQueryWrapper<SceneCategoryDO>()
                .in(SceneCategoryDO::getId, ids));
    }

    // 新增方法：检查给定的parentIds是否有子分类
    default boolean hasChildrenByParentIds(List<String> parentIds) {
        if (parentIds == null || parentIds.isEmpty()) {
            return false;
        }
        return selectCount(new LambdaQueryWrapper<SceneCategoryDO>()
                .in(SceneCategoryDO::getParentId, parentIds)) > 0;
    }

    // 新增方法：查询所有分类（用于构建树）
    default List<SceneCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapper<>());
    }

    // 新增方法：根据分类ID列表分页查询
    default PageResult<SceneCategoryDO> selectPageByCategoryIds(SceneCategoryPageReqVO reqVO, List<Long> categoryIds) {
        LambdaQueryWrapperX<SceneCategoryDO> queryWrapper = new LambdaQueryWrapperX<SceneCategoryDO>()
                .likeIfPresent(SceneCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(SceneCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(SceneCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(SceneCategoryDO::getParentCategory, reqVO.getParentCategory())
                .eqIfPresent(SceneCategoryDO::getApplicableArea, reqVO.getApplicableArea())
                .eqIfPresent(SceneCategoryDO::getDataType, reqVO.getDataType())
                .eqIfPresent(SceneCategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .eqIfPresent(SceneCategoryDO::getCategoryType, reqVO.getCategoryType())
                .eqIfPresent(SceneCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(SceneCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(SceneCategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(SceneCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(SceneCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SceneCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SceneCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(SceneCategoryDO::getCreateTime, reqVO.getCreateTime())
                .in(SceneCategoryDO::getId, categoryIds)  // 关键：按ID列表查询
                .orderByDesc(SceneCategoryDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

}