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
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getCode, reqVO.getCode())
                .eqIfPresent(CategoryDO::getCodeSortType, reqVO.getCodeSortType())
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(CategoryDO::getParentName, reqVO.getParentName())
                .eqIfPresent(CategoryDO::getIconId, reqVO.getIconId())
                .eqIfPresent(CategoryDO::getIconAuditStatusId, reqVO.getIconAuditStatusId())
                .eqIfPresent(CategoryDO::getCategoryTypeId, reqVO.getCategoryTypeId())
                .eqIfPresent(CategoryDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(CategoryDO::getAuditStatusId, reqVO.getAuditStatusId())
                .eqIfPresent(CategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(CategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(CategoryDO::getNotifyFlag, reqVO.getNotifyFlag())
                .eqIfPresent(CategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CategoryDO::getExtCommon2, reqVO.getExtCommon2())
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

}