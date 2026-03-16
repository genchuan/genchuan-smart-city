package cn.iocoder.yudao.module.kitchen.dal.mysql.rectifynotice;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 整改通知书 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RectifyNoticeMapper extends BaseMapperX<RectifyNoticeDO> {

    default PageResult<RectifyNoticeDO> selectPage(RectifyNoticePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RectifyNoticeDO>()
                .eqIfPresent(RectifyNoticeDO::getNoticeCode, reqVO.getNoticeCode())
                .eqIfPresent(RectifyNoticeDO::getRectifyReviewId, reqVO.getRectifyReviewId())
                .betweenIfPresent(RectifyNoticeDO::getIssueTime, reqVO.getIssueTime())
                .eqIfPresent(RectifyNoticeDO::getRectifyDeadline, reqVO.getRectifyDeadline())
                .eqIfPresent(RectifyNoticeDO::getReceiveStatus, reqVO.getReceiveStatus())
                .betweenIfPresent(RectifyNoticeDO::getReceiveTime, reqVO.getReceiveTime())
                .eqIfPresent(RectifyNoticeDO::getNoticeContent, reqVO.getNoticeContent())
                .betweenIfPresent(RectifyNoticeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RectifyNoticeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RectifyNoticeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RectifyNoticeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RectifyNoticeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RectifyNoticeDO::getId));
    }

    RectifyNoticeDO selectByRectifyReviewId(Long rectifyReviewId);
}
