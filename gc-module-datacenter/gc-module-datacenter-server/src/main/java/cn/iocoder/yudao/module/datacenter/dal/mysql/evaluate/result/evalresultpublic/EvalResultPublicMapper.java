package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultpublic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpublic.EvalResultPublicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果公示 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultPublicMapper extends BaseMapperX<EvalResultPublicDO> {

    default PageResult<EvalResultPublicDO> selectPage(EvalResultPublicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultPublicDO>()
                .eqIfPresent(EvalResultPublicDO::getResultPublicId, reqVO.getResultPublicId())
                .eqIfPresent(EvalResultPublicDO::getResultAuditId, reqVO.getResultAuditId())
                .eqIfPresent(EvalResultPublicDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalResultPublicDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalResultPublicDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalResultPublicDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalResultPublicDO::getEvalLevel, reqVO.getEvalLevel())
                .eqIfPresent(EvalResultPublicDO::getEvalScore, reqVO.getEvalScore())
                .eqIfPresent(EvalResultPublicDO::getPublicScope, reqVO.getPublicScope())
                .betweenIfPresent(EvalResultPublicDO::getPublicStartTime, reqVO.getPublicStartTime())
                .betweenIfPresent(EvalResultPublicDO::getPublicEndTime, reqVO.getPublicEndTime())
                .eqIfPresent(EvalResultPublicDO::getPublicStatus, reqVO.getPublicStatus())
                .eqIfPresent(EvalResultPublicDO::getPublicUrl, reqVO.getPublicUrl())
                .eqIfPresent(EvalResultPublicDO::getObjectCount, reqVO.getObjectCount())
                .eqIfPresent(EvalResultPublicDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalResultPublicDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultPublicDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultPublicDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultPublicDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultPublicDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultPublicDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultPublicDO::getId));
    }

}