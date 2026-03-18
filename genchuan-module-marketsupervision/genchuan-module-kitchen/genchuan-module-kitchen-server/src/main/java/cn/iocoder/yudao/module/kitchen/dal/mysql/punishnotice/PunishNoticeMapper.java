package cn.iocoder.yudao.module.kitchen.dal.mysql.punishnotice;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice.PunishNoticeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 处罚通知书 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PunishNoticeMapper extends BaseMapperX<PunishNoticeDO> {

    default PageResult<PunishNoticeDO> selectPage(PunishNoticePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PunishNoticeDO>()
                .eqIfPresent(PunishNoticeDO::getNoticeCode, reqVO.getNoticeCode())
                .eqIfPresent(PunishNoticeDO::getPunishReviewId, reqVO.getPunishReviewId())
                .eqIfPresent(PunishNoticeDO::getEntPayRecordId, reqVO.getEntPayRecordId())
                .betweenIfPresent(PunishNoticeDO::getIssueTime, reqVO.getIssueTime())
                .betweenIfPresent(PunishNoticeDO::getReceiveTime, reqVO.getReceiveTime())
                .eqIfPresent(PunishNoticeDO::getPayDeadline, reqVO.getPayDeadline())
                .eqIfPresent(PunishNoticeDO::getReceiveStatus, reqVO.getReceiveStatus())
                .eqIfPresent(PunishNoticeDO::getActualPunishAmt, reqVO.getActualPunishAmt())
                .eqIfPresent(PunishNoticeDO::getDecisionContent, reqVO.getDecisionContent())
                .betweenIfPresent(PunishNoticeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PunishNoticeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PunishNoticeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PunishNoticeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PunishNoticeDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(PunishNoticeDO::getId));
    }

}
