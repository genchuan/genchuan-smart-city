package cn.iocoder.yudao.module.data.dal.mysql.partinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.partinstance.vo.InstancePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.partinstance.InstanceDO;
import cn.iocoder.yudao.module.data.service.partcategory.CategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理部件实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InstanceMapper extends BaseMapperX<InstanceDO> {

    /**
     * 自定义分页查询，支持树形结构联动和分类名称查询
     */
    default PageResult<InstanceDO> selectPageWithCategory(InstancePageReqVO reqVO, CategoryService categoryService) {
        // 创建分页对象
        Page<InstanceDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapperX<InstanceDO> wrapper = new LambdaQueryWrapperX<InstanceDO>()
                .likeIfPresent(InstanceDO::getPartName, reqVO.getPartName())
                .eqIfPresent(InstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(InstanceDO::getParentCategoryId, reqVO.getParentCategoryId())
                .eqIfPresent(InstanceDO::getGridId, reqVO.getGridId())
                .eqIfPresent(InstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(InstanceDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(InstanceDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(InstanceDO::getCoordVerifyFlag, reqVO.getCoordVerifyFlag())
                .eqIfPresent(InstanceDO::getCoordinate, reqVO.getCoordinate())
                .eqIfPresent(InstanceDO::getRunStatus, reqVO.getRunStatus())
                .eqIfPresent(InstanceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(InstanceDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(InstanceDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InstanceDO::getMonitorIds, reqVO.getMonitorIds())
                .eqIfPresent(InstanceDO::getMonitorCount, reqVO.getMonitorCount())
                .eqIfPresent(InstanceDO::getEventCount, reqVO.getEventCount())
                .eqIfPresent(InstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(InstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstanceDO::getId);

        // 处理树形查询
        if (reqVO.getTreeParentId() != null && !reqVO.getTreeParentId().trim().isEmpty()) {
            // 获取该节点及其所有子节点的ID
            List<Long> subCategoryIds = categoryService.getSubCategoryIds(
                    reqVO.getTreeParentId(),
                    reqVO.getIncludeSelf() != null ? reqVO.getIncludeSelf() : true
            );

            if (!subCategoryIds.isEmpty()) {
                // 将Long类型的ID转换为String类型（因为parentCategoryId是String类型）
                List<String> categoryIdStrs = subCategoryIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());

                // 使用in条件查询
                wrapper.in(InstanceDO::getParentCategoryId, categoryIdStrs);
            } else {
                // 如果没有找到任何子节点，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }
        }

        // 执行分页查询
        IPage<InstanceDO> resultPage = selectPage(page, wrapper);

        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

}