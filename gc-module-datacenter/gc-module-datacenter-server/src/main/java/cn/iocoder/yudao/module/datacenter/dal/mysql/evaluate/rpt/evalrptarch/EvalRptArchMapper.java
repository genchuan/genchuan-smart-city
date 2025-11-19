package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrptarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrptarch.EvalRptArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报告归档导出 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRptArchMapper extends BaseMapperX<EvalRptArchDO> {

    default PageResult<EvalRptArchDO> selectPage(EvalRptArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRptArchDO>()
                .eqIfPresent(EvalRptArchDO::getRptArchId, reqVO.getRptArchId())
                .eqIfPresent(EvalRptArchDO::getRptArchCode, reqVO.getRptArchCode())
                .eqIfPresent(EvalRptArchDO::getEvalRptId, reqVO.getEvalRptId())
                .likeIfPresent(EvalRptArchDO::getEvalRptName, reqVO.getEvalRptName())
                .eqIfPresent(EvalRptArchDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalRptArchDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalRptArchDO::getArchLocation, reqVO.getArchLocation())
                .betweenIfPresent(EvalRptArchDO::getArchTime, reqVO.getArchTime())
                .eqIfPresent(EvalRptArchDO::getArchMethod, reqVO.getArchMethod())
                .eqIfPresent(EvalRptArchDO::getRptVersion, reqVO.getRptVersion())
                .eqIfPresent(EvalRptArchDO::getArchStatus, reqVO.getArchStatus())
                .eqIfPresent(EvalRptArchDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(EvalRptArchDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalRptArchDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalRptArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRptArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRptArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRptArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalRptArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalRptArchDO::getId));
    }

}