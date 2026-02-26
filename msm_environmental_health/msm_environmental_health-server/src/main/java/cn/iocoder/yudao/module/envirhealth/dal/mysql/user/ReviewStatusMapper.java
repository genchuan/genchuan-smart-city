package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ReviewStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 审核状态字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ReviewStatusMapper extends BaseMapperX<ReviewStatusDO> {

    default PageResult<ReviewStatusDO> selectPage(ReviewStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReviewStatusDO>()
                .eqIfPresent(ReviewStatusDO::getReviewStatusId, reqVO.getReviewStatusId())
                .likeIfPresent(ReviewStatusDO::getReviewStatusName, reqVO.getReviewStatusName())
                .eqIfPresent(ReviewStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ReviewStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ReviewStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(ReviewStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ReviewStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ReviewStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ReviewStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ReviewStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ReviewStatusDO::getId));
    }

}