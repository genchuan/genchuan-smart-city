package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnhandleeval;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警处置评估 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EarlyWarnHandleEvalMapper extends BaseMapperX<EarlyWarnHandleEvalDO> {

    default PageResult<EarlyWarnHandleEvalDO> selectPage(EarlyWarnHandleEvalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnHandleEvalDO>()
                .eqIfPresent(EarlyWarnHandleEvalDO::getEvalId, reqVO.getEvalId())
                .eqIfPresent(EarlyWarnHandleEvalDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .eqIfPresent(EarlyWarnHandleEvalDO::getDispatchId, reqVO.getDispatchId())
                .eqIfPresent(EarlyWarnHandleEvalDO::getEvalUserId, reqVO.getEvalUserId())
                .likeIfPresent(EarlyWarnHandleEvalDO::getEvalUserName, reqVO.getEvalUserName())
                .eqIfPresent(EarlyWarnHandleEvalDO::getProblemSolveScore, reqVO.getProblemSolveScore())
                .eqIfPresent(EarlyWarnHandleEvalDO::getTimelinessScore, reqVO.getTimelinessScore())
                .eqIfPresent(EarlyWarnHandleEvalDO::getStdizationScore, reqVO.getStdizationScore())
                .eqIfPresent(EarlyWarnHandleEvalDO::getComprehensiveScore, reqVO.getComprehensiveScore())
                .eqIfPresent(EarlyWarnHandleEvalDO::getEvalLevel, reqVO.getEvalLevel())
                .betweenIfPresent(EarlyWarnHandleEvalDO::getEvalTime, reqVO.getEvalTime())
                .eqIfPresent(EarlyWarnHandleEvalDO::getEvalRemark, reqVO.getEvalRemark())
                .eqIfPresent(EarlyWarnHandleEvalDO::getIsSecondHandle, reqVO.getIsSecondHandle())
                .eqIfPresent(EarlyWarnHandleEvalDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnHandleEvalDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnHandleEvalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnHandleEvalDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnHandleEvalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnHandleEvalDO::getId));
    }

}