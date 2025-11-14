package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.keyidxsummaryrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关键指标汇总报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface KeyIdxSummaryRptMapper extends BaseMapperX<KeyIdxSummaryRptDO> {

    default PageResult<KeyIdxSummaryRptDO> selectPage(KeyIdxSummaryRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<KeyIdxSummaryRptDO>()
                .eqIfPresent(KeyIdxSummaryRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(KeyIdxSummaryRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(KeyIdxSummaryRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(KeyIdxSummaryRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(KeyIdxSummaryRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(KeyIdxSummaryRptDO::getIdxId, reqVO.getIdxId())
                .likeIfPresent(KeyIdxSummaryRptDO::getIdxName, reqVO.getIdxName())
                .eqIfPresent(KeyIdxSummaryRptDO::getIdxUnit, reqVO.getIdxUnit())
                .eqIfPresent(KeyIdxSummaryRptDO::getIdxLevel, reqVO.getIdxLevel())
                .eqIfPresent(KeyIdxSummaryRptDO::getIdxAvg, reqVO.getIdxAvg())
                .eqIfPresent(KeyIdxSummaryRptDO::getIdxMax, reqVO.getIdxMax())
                .eqIfPresent(KeyIdxSummaryRptDO::getExceedCount, reqVO.getExceedCount())
                .eqIfPresent(KeyIdxSummaryRptDO::getMomImproveRate, reqVO.getMomImproveRate())
                .betweenIfPresent(KeyIdxSummaryRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(KeyIdxSummaryRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(KeyIdxSummaryRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(KeyIdxSummaryRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(KeyIdxSummaryRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(KeyIdxSummaryRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(KeyIdxSummaryRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(KeyIdxSummaryRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(KeyIdxSummaryRptDO::getId));
    }

}