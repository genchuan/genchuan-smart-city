package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalappeal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalappeal.EvalAppealDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申诉管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EvalAppealMapper extends BaseMapperX<EvalAppealDO> {

    default PageResult<EvalAppealDO> selectPage(EvalAppealPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalAppealDO>()
                .eqIfPresent(EvalAppealDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(EvalAppealDO::getAppealCode, reqVO.getAppealCode())
                .eqIfPresent(EvalAppealDO::getResultPublicId, reqVO.getResultPublicId())
                .eqIfPresent(EvalAppealDO::getEvalTaskId, reqVO.getEvalTaskId())
                .eqIfPresent(EvalAppealDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalAppealDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalAppealDO::getAppealUserId, reqVO.getAppealUserId())
                .likeIfPresent(EvalAppealDO::getAppealUserName, reqVO.getAppealUserName())
                .eqIfPresent(EvalAppealDO::getAppealReason, reqVO.getAppealReason())
                .eqIfPresent(EvalAppealDO::getProofMats, reqVO.getProofMats())
                .eqIfPresent(EvalAppealDO::getAppealStatus, reqVO.getAppealStatus())
                .betweenIfPresent(EvalAppealDO::getSubmitTime, reqVO.getSubmitTime())
                .betweenIfPresent(EvalAppealDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(EvalAppealDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalAppealDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalAppealDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalAppealDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalAppealDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalAppealDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalAppealDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalAppealDO::getId));
    }

}