package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultarch.EvalResultArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果存档管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultArchMapper extends BaseMapperX<EvalResultArchDO> {

    default PageResult<EvalResultArchDO> selectPage(EvalResultArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultArchDO>()
                .eqIfPresent(EvalResultArchDO::getResultArchId, reqVO.getResultArchId())
                .eqIfPresent(EvalResultArchDO::getArchCode, reqVO.getArchCode())
                .eqIfPresent(EvalResultArchDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalResultArchDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalResultArchDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalResultArchDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalResultArchDO::getFinalEvalLevel, reqVO.getFinalEvalLevel())
                .eqIfPresent(EvalResultArchDO::getFinalEvalScore, reqVO.getFinalEvalScore())
                .eqIfPresent(EvalResultArchDO::getArchReason, reqVO.getArchReason())
                .betweenIfPresent(EvalResultArchDO::getArchTime, reqVO.getArchTime())
                .eqIfPresent(EvalResultArchDO::getArchAttachs, reqVO.getArchAttachs())
                .eqIfPresent(EvalResultArchDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalResultArchDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultArchDO::getId));
    }

}