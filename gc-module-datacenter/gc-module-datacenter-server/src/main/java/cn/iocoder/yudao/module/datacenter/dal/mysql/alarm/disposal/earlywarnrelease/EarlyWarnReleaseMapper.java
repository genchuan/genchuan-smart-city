package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnrelease;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleasePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnrelease.EarlyWarnReleaseDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警解除 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnReleaseMapper extends BaseMapperX<EarlyWarnReleaseDO> {

    default PageResult<EarlyWarnReleaseDO> selectPage(EarlyWarnReleasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnReleaseDO>()
                .eqIfPresent(EarlyWarnReleaseDO::getReleaseId, reqVO.getReleaseId())
                .eqIfPresent(EarlyWarnReleaseDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .likeIfPresent(EarlyWarnReleaseDO::getEarlyWarnName, reqVO.getEarlyWarnName())
                .eqIfPresent(EarlyWarnReleaseDO::getApplyUserId, reqVO.getApplyUserId())
                .likeIfPresent(EarlyWarnReleaseDO::getApplyUserName, reqVO.getApplyUserName())
                .eqIfPresent(EarlyWarnReleaseDO::getAuditUserId, reqVO.getAuditUserId())
                .likeIfPresent(EarlyWarnReleaseDO::getAuditUserName, reqVO.getAuditUserName())
                .eqIfPresent(EarlyWarnReleaseDO::getReleaseStatus, reqVO.getReleaseStatus())
                .eqIfPresent(EarlyWarnReleaseDO::getReleaseReason, reqVO.getReleaseReason())
                .betweenIfPresent(EarlyWarnReleaseDO::getApplyTime, reqVO.getApplyTime())
                .betweenIfPresent(EarlyWarnReleaseDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(EarlyWarnReleaseDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(EarlyWarnReleaseDO::getIsRecurrence, reqVO.getIsRecurrence())
                .eqIfPresent(EarlyWarnReleaseDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnReleaseDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnReleaseDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnReleaseDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnReleaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnReleaseDO::getId));
    }

}