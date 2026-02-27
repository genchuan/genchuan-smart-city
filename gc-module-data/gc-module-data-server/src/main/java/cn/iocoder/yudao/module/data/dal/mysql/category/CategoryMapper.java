package cn.iocoder.yudao.module.data.dal.mysql.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.data.controller.admin.category.vo.*;

/**
 * 管理部件分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CategoryDO>()
                .likeIfPresent(CategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(CategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(CategoryDO::getCodeSortType, reqVO.getCodeSortType())
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(CategoryDO::getParentCategoryName, reqVO.getParentCategoryName())
                .eqIfPresent(CategoryDO::getIconName, reqVO.getIconName())
                .eqIfPresent(CategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(CategoryDO::getCategoryType, reqVO.getCategoryType())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CategoryDO::getAuditStatusId, reqVO.getAuditStatusId())
                .eqIfPresent(CategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(CategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(CategoryDO::getNotifyFlag, reqVO.getNotifyFlag())
                .eqIfPresent(CategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CategoryDO::getCreator,reqVO.getCreator())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getId));
    }

    /**
     * 查询所有分类（用于构建树）
     * @return 全部分类列表
     */
    default List<CategoryDO> selectList() {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .orderByAsc(CategoryDO::getParentId) // 可按需调整排序
                .orderByAsc(CategoryDO::getId));
    }

    /**
     * 根据ID列表查询分类列表
     * @param ids ID列表
     * @return 分类列表
     */
    default List<CategoryDO> selectListByIds(List<Long> ids) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .inIfPresent(CategoryDO::getId, ids)
                .orderByAsc(CategoryDO::getId));
    }

    /**
     * 检查是否存在指定分类的子分类
     * @param parentIds 父级分类ID列表
     * @return 是否存在子分类
     */
    default boolean hasChildrenByParentIds(List<String> parentIds) {
        if (parentIds == null || parentIds.isEmpty()) {
            return false;
        }
        return selectCount(new LambdaQueryWrapperX<CategoryDO>()
                .in(CategoryDO::getParentId, parentIds)) > 0;
    }

    /**
     * 根据ID列表批量删除
     * @param ids ID列表
     * @return 删除的行数
     */
    default int deleteBatchIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        // 修复递归调用问题：调用父类的方法而不是自身
        return BaseMapperX.super.deleteBatchIds(ids);
    }

}