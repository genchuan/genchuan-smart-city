package cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import org.apache.ibatis.annotations.Mapper;

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

}