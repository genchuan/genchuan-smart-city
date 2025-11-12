package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.compareanalysisrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 综合对比分析报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface CompareAnalysisRptMapper extends BaseMapperX<CompareAnalysisRptDO> {

    default PageResult<CompareAnalysisRptDO> selectPage(CompareAnalysisRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompareAnalysisRptDO>()
                .eqIfPresent(CompareAnalysisRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(CompareAnalysisRptDO::getCompareDimType, reqVO.getCompareDimType())
                .eqIfPresent(CompareAnalysisRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(CompareAnalysisRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(CompareAnalysisRptDO::getStatIdx, reqVO.getStatIdx())
                .eqIfPresent(CompareAnalysisRptDO::getCompareObj1Id, reqVO.getCompareObj1Id())
                .likeIfPresent(CompareAnalysisRptDO::getCompareObj1Name, reqVO.getCompareObj1Name())
                .eqIfPresent(CompareAnalysisRptDO::getCompareObj1Value, reqVO.getCompareObj1Value())
                .eqIfPresent(CompareAnalysisRptDO::getCompareObj2Id, reqVO.getCompareObj2Id())
                .likeIfPresent(CompareAnalysisRptDO::getCompareObj2Name, reqVO.getCompareObj2Name())
                .eqIfPresent(CompareAnalysisRptDO::getCompareObj2Value, reqVO.getCompareObj2Value())
                .eqIfPresent(CompareAnalysisRptDO::getAvgValue, reqVO.getAvgValue())
                .eqIfPresent(CompareAnalysisRptDO::getMaxObjId, reqVO.getMaxObjId())
                .likeIfPresent(CompareAnalysisRptDO::getMaxObjName, reqVO.getMaxObjName())
                .eqIfPresent(CompareAnalysisRptDO::getMaxValue, reqVO.getMaxValue())
                .eqIfPresent(CompareAnalysisRptDO::getMinObjId, reqVO.getMinObjId())
                .likeIfPresent(CompareAnalysisRptDO::getMinObjName, reqVO.getMinObjName())
                .eqIfPresent(CompareAnalysisRptDO::getMinValue, reqVO.getMinValue())
                .eqIfPresent(CompareAnalysisRptDO::getMaxDiffValue, reqVO.getMaxDiffValue())
                .eqIfPresent(CompareAnalysisRptDO::getMaxDiffRate, reqVO.getMaxDiffRate())
                .betweenIfPresent(CompareAnalysisRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(CompareAnalysisRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(CompareAnalysisRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(CompareAnalysisRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(CompareAnalysisRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(CompareAnalysisRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CompareAnalysisRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(CompareAnalysisRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompareAnalysisRptDO::getId));
    }

}