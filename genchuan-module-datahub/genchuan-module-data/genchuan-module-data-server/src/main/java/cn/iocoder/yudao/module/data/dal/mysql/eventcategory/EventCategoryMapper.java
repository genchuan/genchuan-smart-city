package cn.iocoder.yudao.module.data.dal.mysql.eventcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.eventcategory.vo.EventCategoryPageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.eventcategory.EventCategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 监测事件分类 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EventCategoryMapper extends BaseMapperX<EventCategoryDO> {

    default PageResult<EventCategoryDO> selectPage(EventCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventCategoryDO>()
                .likeIfPresent(EventCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(EventCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(EventCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(EventCategoryDO::getParentCategory, reqVO.getParentCategory())
                .eqIfPresent(EventCategoryDO::getMonitorTypeId, reqVO.getMonitorTypeId())
                .eqIfPresent(EventCategoryDO::getRelatedMonitorType, reqVO.getRelatedMonitorType())
                .eqIfPresent(EventCategoryDO::getMatterTypeId, reqVO.getMatterTypeId())
                .eqIfPresent(EventCategoryDO::getRelatedMatterType, reqVO.getRelatedMatterType())
                .eqIfPresent(EventCategoryDO::getEventLevel, reqVO.getEventLevel())
                .eqIfPresent(EventCategoryDO::getPushRule, reqVO.getPushRule())
                .eqIfPresent(EventCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EventCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(EventCategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(EventCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(EventCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EventCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EventCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EventCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventCategoryDO::getId));
    }

    // 新增方法：根据ID列表查询分类
    default List<EventCategoryDO> selectListByIds(List<Long> ids) {
        return selectList(new LambdaQueryWrapper<EventCategoryDO>()
                .in(EventCategoryDO::getId, ids));
    }

    // 新增方法：检查给定的parentIds是否有子分类
    default boolean hasChildrenByParentIds(List<String> parentIds) {
        if (parentIds == null || parentIds.isEmpty()) {
            return false;
        }
        return selectCount(new LambdaQueryWrapper<EventCategoryDO>()
                .in(EventCategoryDO::getParentId, parentIds)) > 0;
    }

    // 新增方法：查询所有分类（用于构建树）
    default List<EventCategoryDO> selectList() {
        return selectList(new LambdaQueryWrapper<>());
    }

    // 新增方法：根据分类ID列表分页查询
    default PageResult<EventCategoryDO> selectPageByCategoryIds(EventCategoryPageReqVO reqVO, List<Long> categoryIds) {
        LambdaQueryWrapperX<EventCategoryDO> queryWrapper = new LambdaQueryWrapperX<EventCategoryDO>()
                .likeIfPresent(EventCategoryDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(EventCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(EventCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(EventCategoryDO::getParentCategory, reqVO.getParentCategory())
                .eqIfPresent(EventCategoryDO::getMonitorTypeId, reqVO.getMonitorTypeId())
                .eqIfPresent(EventCategoryDO::getRelatedMonitorType, reqVO.getRelatedMonitorType())
                .eqIfPresent(EventCategoryDO::getMatterTypeId, reqVO.getMatterTypeId())
                .eqIfPresent(EventCategoryDO::getRelatedMatterType, reqVO.getRelatedMatterType())
                .eqIfPresent(EventCategoryDO::getEventLevel, reqVO.getEventLevel())
                .eqIfPresent(EventCategoryDO::getPushRule, reqVO.getPushRule())
                .eqIfPresent(EventCategoryDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EventCategoryDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(EventCategoryDO::getInstanceCount, reqVO.getInstanceCount())
                .eqIfPresent(EventCategoryDO::getPurpose, reqVO.getPurpose())
                .eqIfPresent(EventCategoryDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EventCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EventCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EventCategoryDO::getCreateTime, reqVO.getCreateTime())
                .in(EventCategoryDO::getId, categoryIds)  // 关键：按ID列表查询
                .orderByDesc(EventCategoryDO::getId);

        return selectPage(reqVO, queryWrapper);
    }

}