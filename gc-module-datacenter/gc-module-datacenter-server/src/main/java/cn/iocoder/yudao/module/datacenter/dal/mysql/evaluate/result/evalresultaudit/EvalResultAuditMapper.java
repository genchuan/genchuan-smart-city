package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultaudit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultaudit.EvalResultAuditDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果审核 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultAuditMapper extends BaseMapperX<EvalResultAuditDO> {

    default PageResult<EvalResultAuditDO> selectPage(EvalResultAuditPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultAuditDO>()
                .eqIfPresent(EvalResultAuditDO::getResultAuditId, reqVO.getResultAuditId())
                .eqIfPresent(EvalResultAuditDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalResultAuditDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalResultAuditDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalResultAuditDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalResultAuditDO::getAutoCalcResult, reqVO.getAutoCalcResult())
                .eqIfPresent(EvalResultAuditDO::getAutoCalcScore, reqVO.getAutoCalcScore())
                .eqIfPresent(EvalResultAuditDO::getAuditStatus, reqVO.getAuditStatus())
                .eqIfPresent(EvalResultAuditDO::getAuditorId, reqVO.getAuditorId())
                .likeIfPresent(EvalResultAuditDO::getAuditorName, reqVO.getAuditorName())
                .eqIfPresent(EvalResultAuditDO::getAuditOpinion, reqVO.getAuditOpinion())
                .betweenIfPresent(EvalResultAuditDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(EvalResultAuditDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalResultAuditDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultAuditDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultAuditDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultAuditDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultAuditDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultAuditDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultAuditDO::getId));
    }

}