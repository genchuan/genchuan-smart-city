package cn.iocoder.yudao.module.evaluate.dal.mysql.relatedobject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo.RelatedObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关联对象 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RelatedObjectMapper extends BaseMapperX<RelatedObjectDO> {

    default PageResult<RelatedObjectDO> selectPage(RelatedObjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RelatedObjectDO>()
                .eqIfPresent(RelatedObjectDO::getRelatedId, reqVO.getRelatedId())
                .likeIfPresent(RelatedObjectDO::getRelatedName, reqVO.getRelatedName())
                .eqIfPresent(RelatedObjectDO::getRelatedType, reqVO.getRelatedType())
                .eqIfPresent(RelatedObjectDO::getParentId, reqVO.getParentId())
                .eqIfPresent(RelatedObjectDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(RelatedObjectDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(RelatedObjectDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(RelatedObjectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RelatedObjectDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RelatedObjectDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RelatedObjectDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RelatedObjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RelatedObjectDO::getId));
    }

}