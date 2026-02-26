package cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.ReviewResultDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 复核结果字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ReviewResultMapper extends BaseMapperX<ReviewResultDO> {

    default PageResult<ReviewResultDO> selectPage(ReviewResultPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReviewResultDO>()
                .eqIfPresent(ReviewResultDO::getReviewResultId, reqVO.getReviewResultId())
                .likeIfPresent(ReviewResultDO::getReviewResultName, reqVO.getReviewResultName())
                .eqIfPresent(ReviewResultDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ReviewResultDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ReviewResultDO::getSort, reqVO.getSort())
                .eqIfPresent(ReviewResultDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ReviewResultDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ReviewResultDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ReviewResultDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ReviewResultDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ReviewResultDO::getId));
    }

}