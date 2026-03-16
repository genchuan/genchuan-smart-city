package cn.iocoder.yudao.module.kitchen.dal.mysql.entrectifyrecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业整改记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EntRectifyRecordMapper extends BaseMapperX<EntRectifyRecordDO> {

    default PageResult<EntRectifyRecordDO> selectPage(EntRectifyRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EntRectifyRecordDO>()
                .eqIfPresent(EntRectifyRecordDO::getRectifyNoticeId, reqVO.getRectifyNoticeId())
                .eqIfPresent(EntRectifyRecordDO::getEntId, reqVO.getEntId())
                .eqIfPresent(EntRectifyRecordDO::getRectifyStatus, reqVO.getRectifyStatus())
                .betweenIfPresent(EntRectifyRecordDO::getRectifyCompleteTime, reqVO.getRectifyCompleteTime())
                .eqIfPresent(EntRectifyRecordDO::getRectifyDesc, reqVO.getRectifyDesc())
                .eqIfPresent(EntRectifyRecordDO::getRectifyEvidenceUrl, reqVO.getRectifyEvidenceUrl())
                .eqIfPresent(EntRectifyRecordDO::getAuditResult, reqVO.getAuditResult())
                .eqIfPresent(EntRectifyRecordDO::getAuditBy, reqVO.getAuditBy())
                .eqIfPresent(EntRectifyRecordDO::getRejectReason, reqVO.getRejectReason())
                .betweenIfPresent(EntRectifyRecordDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(EntRectifyRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EntRectifyRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EntRectifyRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EntRectifyRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(EntRectifyRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(EntRectifyRecordDO::getId));
    }

}
