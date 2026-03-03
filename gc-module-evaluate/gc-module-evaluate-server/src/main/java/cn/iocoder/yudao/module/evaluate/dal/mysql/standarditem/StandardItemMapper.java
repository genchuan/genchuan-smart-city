package cn.iocoder.yudao.module.evaluate.dal.mysql.standarditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标准项 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StandardItemMapper extends BaseMapperX<StandardItemDO> {

    default PageResult<StandardItemDO> selectPage(StandardItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StandardItemDO>()
                .eqIfPresent(StandardItemDO::getStandardItemId, reqVO.getStandardItemId())
                .eqIfPresent(StandardItemDO::getStandardCategoryId, reqVO.getStandardCategoryId())
                .eqIfPresent(StandardItemDO::getGrade, reqVO.getGrade())
                .eqIfPresent(StandardItemDO::getScoreRange, reqVO.getScoreRange())
                .eqIfPresent(StandardItemDO::getSortNo, reqVO.getSortNo())
                .betweenIfPresent(StandardItemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(StandardItemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(StandardItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StandardItemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(StandardItemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(StandardItemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(StandardItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StandardItemDO::getId));
    }

}