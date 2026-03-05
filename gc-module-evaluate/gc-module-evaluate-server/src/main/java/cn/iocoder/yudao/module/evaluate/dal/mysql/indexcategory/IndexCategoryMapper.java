package cn.iocoder.yudao.module.evaluate.dal.mysql.indexcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import org.apache.ibatis.annotations.Mapper;

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
                .orderByDesc(IndexCategoryDO::getId));
    }

}