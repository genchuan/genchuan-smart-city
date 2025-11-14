package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisregion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisregion.AnalysisRegionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按行政区划分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisRegionMapper extends BaseMapperX<AnalysisRegionDO> {

    default PageResult<AnalysisRegionDO> selectPage(AnalysisRegionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisRegionDO>()
                .eqIfPresent(AnalysisRegionDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisRegionDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisRegionDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisRegionDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(AnalysisRegionDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(AnalysisRegionDO::getRegionLevel, reqVO.getRegionLevel())
                .eqIfPresent(AnalysisRegionDO::getNormalMonCompCount, reqVO.getNormalMonCompCount())
                .eqIfPresent(AnalysisRegionDO::getAbnMonCompCount, reqVO.getAbnMonCompCount())
                .eqIfPresent(AnalysisRegionDO::getMonEvtCompleteRate, reqVO.getMonEvtCompleteRate())
                .eqIfPresent(AnalysisRegionDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(AnalysisRegionDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(AnalysisRegionDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(AnalysisRegionDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisRegionDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisRegionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisRegionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AnalysisRegionDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisRegionDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(AnalysisRegionDO::getId));
    }

}