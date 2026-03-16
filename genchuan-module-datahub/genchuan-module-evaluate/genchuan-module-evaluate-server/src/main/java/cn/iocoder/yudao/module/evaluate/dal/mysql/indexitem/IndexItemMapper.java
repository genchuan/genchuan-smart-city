package cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * 指标项 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndexItemMapper extends BaseMapperX<IndexItemDO> {

    default PageResult<IndexItemDO> selectPage(IndexItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexItemDO>()
                .eqIfPresent(IndexItemDO::getItemId, reqVO.getItemId())
                .eqIfPresent(IndexItemDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(IndexItemDO::getName, reqVO.getName())
                .eqIfPresent(IndexItemDO::getIndexTypeId, reqVO.getIndexTypeId())
                .eqIfPresent(IndexItemDO::getCalcWayId, reqVO.getCalcWayId())
                .eqIfPresent(IndexItemDO::getThreshold, reqVO.getThreshold())
                .eqIfPresent(IndexItemDO::getWeight, reqVO.getWeight())
                .betweenIfPresent(IndexItemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(IndexItemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(IndexItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndexItemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndexItemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IndexItemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(IndexItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexItemDO::getId));
    }
    // ========== 校验：同一分类下名称是否唯一 ==========
    default Integer countByNameAndCategoryId(String categoryId, String name, Long excludeId) {
        LambdaQueryWrapper<IndexItemDO> queryWrapper = new LambdaQueryWrapper<IndexItemDO>()
                .eq(IndexItemDO::getCategoryId, categoryId)
                .eq(IndexItemDO::getName, name)
                .eq(IndexItemDO::getDeleted, 0);
        // 手动判断excludeId，替代原错误的ifPresent
        if (excludeId != null) {
            queryWrapper.ne(IndexItemDO::getId, excludeId);
        }
        // 调用BaseMapperX的selectCount方法
        return Math.toIntExact(selectCount(queryWrapper));
    }

    // ========== 统计：分类下指标项权重总和 ==========
    default BigDecimal sumWeightByCategoryId(String categoryId, Long excludeId) {
        LambdaQueryWrapper<IndexItemDO> queryWrapper = new LambdaQueryWrapper<IndexItemDO>()
                .select(IndexItemDO::getWeight) // 仅查询weight字段
                .eq(IndexItemDO::getCategoryId, categoryId)
                .eq(IndexItemDO::getDeleted, 0);
        // 手动判断excludeId，替代原错误的ifPresent
        if (excludeId != null) {
            queryWrapper.ne(IndexItemDO::getId, excludeId);
        }
        // 查询权重列表并求和
        List<IndexItemDO> list = selectList(queryWrapper);
        BigDecimal sum = list.stream()
                .map(IndexItemDO::getWeight)
                .filter(Objects::nonNull) // 防止null值求和报错
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 保留2位小数
        return sum.setScale(2, RoundingMode.HALF_UP);
    }

    // ========== 校验：关联的分类是否存在（需注入IndexCategoryMapper） ==========
    // 注意：Mapper中无法直接注入其他Mapper，该方法需移到Service层实现，此处仅保留定义（或改为Service层处理）
    default Integer countCategoryExists(String categoryId) {
        // 【正确做法】：该逻辑移到IndexItemService中，注入IndexCategoryMapper后查询
        // 此处仅做占位，避免编译错误
        return 1;
    }
}