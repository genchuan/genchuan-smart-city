package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultanalysis;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultanalysis.EvalResultAnalysisDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价结果分析 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultAnalysisMapper extends BaseMapperX<EvalResultAnalysisDO> {

    default PageResult<EvalResultAnalysisDO> selectPage(EvalResultAnalysisPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultAnalysisDO>()
                .eqIfPresent(EvalResultAnalysisDO::getResultAnalysisId, reqVO.getResultAnalysisId())
                .likeIfPresent(EvalResultAnalysisDO::getResultAnalysisName, reqVO.getResultAnalysisName())
                .eqIfPresent(EvalResultAnalysisDO::getResultAnalysisCode, reqVO.getResultAnalysisCode())
                .eqIfPresent(EvalResultAnalysisDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalResultAnalysisDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalResultAnalysisDO::getAnalysisDimension, reqVO.getAnalysisDimension())
                .eqIfPresent(EvalResultAnalysisDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EvalResultAnalysisDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EvalResultAnalysisDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalResultAnalysisDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalResultAnalysisDO::getTimeRange, reqVO.getTimeRange())
                .eqIfPresent(EvalResultAnalysisDO::getAnalysisResult, reqVO.getAnalysisResult())
                .eqIfPresent(EvalResultAnalysisDO::getChartType, reqVO.getChartType())
                .betweenIfPresent(EvalResultAnalysisDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(EvalResultAnalysisDO::getGenerateUser, reqVO.getGenerateUser())
                .eqIfPresent(EvalResultAnalysisDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultAnalysisDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultAnalysisDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultAnalysisDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultAnalysisDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultAnalysisDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultAnalysisDO::getId));
    }

}