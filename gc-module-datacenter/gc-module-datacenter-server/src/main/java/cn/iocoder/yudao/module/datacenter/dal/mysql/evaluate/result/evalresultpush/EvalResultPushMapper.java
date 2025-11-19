package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultpush;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpush.EvalResultPushDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果关联推送 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultPushMapper extends BaseMapperX<EvalResultPushDO> {

    default PageResult<EvalResultPushDO> selectPage(EvalResultPushPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultPushDO>()
                .eqIfPresent(EvalResultPushDO::getResultPushId, reqVO.getResultPushId())
                .eqIfPresent(EvalResultPushDO::getResultPushCode, reqVO.getResultPushCode())
                .eqIfPresent(EvalResultPushDO::getResultArchId, reqVO.getResultArchId())
                .eqIfPresent(EvalResultPushDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalResultPushDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalResultPushDO::getPushTarget, reqVO.getPushTarget())
                .eqIfPresent(EvalResultPushDO::getPushMethod, reqVO.getPushMethod())
                .eqIfPresent(EvalResultPushDO::getPushAddress, reqVO.getPushAddress())
                .eqIfPresent(EvalResultPushDO::getPushContent, reqVO.getPushContent())
                .eqIfPresent(EvalResultPushDO::getPushStatus, reqVO.getPushStatus())
                .betweenIfPresent(EvalResultPushDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(EvalResultPushDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(EvalResultPushDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalResultPushDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultPushDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultPushDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultPushDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultPushDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultPushDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultPushDO::getId));
    }

}