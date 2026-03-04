package cn.iocoder.yudao.module.data.dal.mysql.instance;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import cn.iocoder.yudao.module.data.service.category.CategoryService;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

        // 获取查询结果
        List<InstanceDO> list = resultPage.getRecords();

        // 如果查询结果不为空，批量获取分类名称
        if (list != null && !list.isEmpty()) {
            // 提取所有的分类ID
            List<String> categoryIds = list.stream()
                    .map(InstanceDO::getParentCategoryId)
                    .filter(id -> id != null && !id.trim().isEmpty())
                    .distinct()
                    .collect(Collectors.toList());

            if (!categoryIds.isEmpty()) {
                // 批量查询分类信息
                Map<String, Map<String, Object>> categoryMap = batchSelectCategoryNames(categoryIds);

                // 设置分类名称到实例对象
                for (InstanceDO instance : list) {
                    if (instance.getParentCategoryId() != null) {
                        Map<String, Object> row = null;

                        // 先尝试用字符串键
                        row = categoryMap.get(instance.getParentCategoryId());

                        // 如果为空，可能是键是整数类型
                        if (row == null) {
                            try {
                                Long idLong = Long.parseLong(instance.getParentCategoryId());
                                // 尝试用 Long 类型
                                row = categoryMap.get(idLong);
                                // 如果还为空，尝试用 Integer 类型
                                if (row == null) {
                                    row = categoryMap.get(Integer.valueOf(idLong.intValue()));
                                }
                            } catch (NumberFormatException e) {
                                // 忽略转换错误
                            }
                        }

                        if (row != null) {
                            // 尝试不同的键名获取分类名称
                            String categoryName = null;
                            if (row.containsKey("category_name")) {
                                categoryName = (String) row.get("category_name");
                            } else if (row.containsKey("CATEGORY_NAME")) {
                                categoryName = (String) row.get("CATEGORY_NAME");
                            } else if (row.containsKey("categoryName")) {
                                categoryName = (String) row.get("categoryName");
                            }
                            instance.setCategoryName(categoryName);
                        }
                    }
                }
            }
        }

        return new PageResult<>(list, resultPage.getTotal());
    }

    /**
     * 批量查询分类名称
     * @param categoryIds 分类ID列表
     * @return 分类ID到分类名称的映射
     */
    @MapKey("id") // 指定使用 id 列作为 Map 的键
    @Select("<script>" +
            "SELECT id, category_name FROM part_category WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "   #{id}" +
            "</foreach>" +
            "</script>")
    Map<String, Map<String, Object>> batchSelectCategoryNames(@Param("ids") List<String> categoryIds);
}