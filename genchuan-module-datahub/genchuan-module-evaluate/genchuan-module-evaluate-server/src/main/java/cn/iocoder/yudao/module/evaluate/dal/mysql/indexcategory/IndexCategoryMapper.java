package cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 指标分类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndexCategoryMapper extends BaseMapperX<IndexCategoryDO> {

    default PageResult<IndexCategoryDO> selectPage(IndexCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexCategoryDO>()
                .eqIfPresent(IndexCategoryDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(IndexCategoryDO::getSystemId, reqVO.getSystemId())
                .likeIfPresent(IndexCategoryDO::getName, reqVO.getName())
                .eqIfPresent(IndexCategoryDO::getWeight, reqVO.getWeight())
                .eqIfPresent(IndexCategoryDO::getSortNo, reqVO.getSortNo())
                .betweenIfPresent(IndexCategoryDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(IndexCategoryDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(IndexCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndexCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndexCategoryDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IndexCategoryDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(IndexCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(IndexCategoryDO::getSortNo)
                .orderByDesc(IndexCategoryDO::getId));
    }

    /**
     * 根据体系ID查询所有分类，按排序序号升序排列
     *
     * @param systemId 体系ID（字符串形式，对应 eval_index_category.system_id）
     * @return 分类列表
     */
    List<IndexCategoryDO> selectListBySystemId(@Param("systemId") String systemId);

    /**
     * 批量更新分类的权重（用于计算时自动归一化）
     *
     * @param categoryWeights categoryId -> 归一化后的权重
     */
    default void updateBatchCategoryWeight(Map<String, BigDecimal> categoryWeights) {
        if (categoryWeights == null || categoryWeights.isEmpty()) {
            return;
        }
        for (Map.Entry<String, BigDecimal> entry : categoryWeights.entrySet()) {
            updateCategoryWeightByCategoryId(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 根据 categoryId 更新权重
     */
    int updateCategoryWeightByCategoryId(@Param("categoryId") String categoryId,
                                         @Param("weight") BigDecimal weight);

}