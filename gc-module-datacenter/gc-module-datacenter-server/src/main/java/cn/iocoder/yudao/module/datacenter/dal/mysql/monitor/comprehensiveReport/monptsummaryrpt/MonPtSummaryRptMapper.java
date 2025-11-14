package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.monptsummaryrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测点位汇总报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MonPtSummaryRptMapper extends BaseMapperX<MonPtSummaryRptDO> {

    default PageResult<MonPtSummaryRptDO> selectPage(MonPtSummaryRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonPtSummaryRptDO>()
                .eqIfPresent(MonPtSummaryRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(MonPtSummaryRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MonPtSummaryRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MonPtSummaryRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(MonPtSummaryRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(MonPtSummaryRptDO::getTotalPtCount, reqVO.getTotalPtCount())
                .eqIfPresent(MonPtSummaryRptDO::getAirPtCount, reqVO.getAirPtCount())
                .eqIfPresent(MonPtSummaryRptDO::getTrafPtCount, reqVO.getTrafPtCount())
                .eqIfPresent(MonPtSummaryRptDO::getManholePtCount, reqVO.getManholePtCount())
                .eqIfPresent(MonPtSummaryRptDO::getOtherPtCount, reqVO.getOtherPtCount())
                .eqIfPresent(MonPtSummaryRptDO::getNewPtCount, reqVO.getNewPtCount())
                .eqIfPresent(MonPtSummaryRptDO::getDisablePtCount, reqVO.getDisablePtCount())
                .eqIfPresent(MonPtSummaryRptDO::getPtCoverageRate, reqVO.getPtCoverageRate())
                .betweenIfPresent(MonPtSummaryRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(MonPtSummaryRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(MonPtSummaryRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(MonPtSummaryRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MonPtSummaryRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MonPtSummaryRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonPtSummaryRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MonPtSummaryRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonPtSummaryRptDO::getId));
    }

}