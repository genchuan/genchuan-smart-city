package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnhandletrace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警处置跟踪 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnHandleTraceMapper extends BaseMapperX<EarlyWarnHandleTraceDO> {

    default PageResult<EarlyWarnHandleTraceDO> selectPage(EarlyWarnHandleTracePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnHandleTraceDO>()
                .eqIfPresent(EarlyWarnHandleTraceDO::getHandleTraceId, reqVO.getHandleTraceId())
                .eqIfPresent(EarlyWarnHandleTraceDO::getDispatchId, reqVO.getDispatchId())
                .eqIfPresent(EarlyWarnHandleTraceDO::getDispatchNo, reqVO.getDispatchNo())
                .eqIfPresent(EarlyWarnHandleTraceDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .eqIfPresent(EarlyWarnHandleTraceDO::getHandleProgress, reqVO.getHandleProgress())
                .eqIfPresent(EarlyWarnHandleTraceDO::getProgressDesc, reqVO.getProgressDesc())
                .eqIfPresent(EarlyWarnHandleTraceDO::getOperUserId, reqVO.getOperUserId())
                .likeIfPresent(EarlyWarnHandleTraceDO::getOperUserName, reqVO.getOperUserName())
                .betweenIfPresent(EarlyWarnHandleTraceDO::getOperTime, reqVO.getOperTime())
                .eqIfPresent(EarlyWarnHandleTraceDO::getAttachUrl, reqVO.getAttachUrl())
                .eqIfPresent(EarlyWarnHandleTraceDO::getIsKeyNode, reqVO.getIsKeyNode())
                .eqIfPresent(EarlyWarnHandleTraceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnHandleTraceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnHandleTraceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnHandleTraceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnHandleTraceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnHandleTraceDO::getId));
    }

}