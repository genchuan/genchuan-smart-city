package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultrpt.EvalResultRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果报表管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultRptMapper extends BaseMapperX<EvalResultRptDO> {

    default PageResult<EvalResultRptDO> selectPage(EvalResultRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultRptDO>()
                .eqIfPresent(EvalResultRptDO::getResultRptId, reqVO.getResultRptId())
                .likeIfPresent(EvalResultRptDO::getResultRptName, reqVO.getResultRptName())
                .eqIfPresent(EvalResultRptDO::getResultRptCode, reqVO.getResultRptCode())
                .eqIfPresent(EvalResultRptDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalResultRptDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalResultRptDO::getRptType, reqVO.getRptType())
                .eqIfPresent(EvalResultRptDO::getStatDimension, reqVO.getStatDimension())
                .eqIfPresent(EvalResultRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EvalResultRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EvalResultRptDO::getRptFields, reqVO.getRptFields())
                .betweenIfPresent(EvalResultRptDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(EvalResultRptDO::getRptStatus, reqVO.getRptStatus())
                .eqIfPresent(EvalResultRptDO::getFilePath, reqVO.getFilePath())
                .eqIfPresent(EvalResultRptDO::getGenerateUser, reqVO.getGenerateUser())
                .eqIfPresent(EvalResultRptDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultRptDO::getId));
    }

}