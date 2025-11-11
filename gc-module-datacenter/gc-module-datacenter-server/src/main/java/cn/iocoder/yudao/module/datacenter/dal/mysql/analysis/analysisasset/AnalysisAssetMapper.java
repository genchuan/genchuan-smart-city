package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisasset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisasset.AnalysisAssetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按资产分域分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisAssetMapper extends BaseMapperX<AnalysisAssetDO> {

    default PageResult<AnalysisAssetDO> selectPage(AnalysisAssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisAssetDO>()
                .eqIfPresent(AnalysisAssetDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisAssetDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisAssetDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisAssetDO::getAssetMajorId, reqVO.getAssetMajorId())
                .likeIfPresent(AnalysisAssetDO::getAssetMajorName, reqVO.getAssetMajorName())
                .eqIfPresent(AnalysisAssetDO::getAssetMinorId, reqVO.getAssetMinorId())
                .likeIfPresent(AnalysisAssetDO::getAssetMinorName, reqVO.getAssetMinorName())
                .eqIfPresent(AnalysisAssetDO::getTotalAssetCount, reqVO.getTotalAssetCount())
                .eqIfPresent(AnalysisAssetDO::getAssetIntactRate, reqVO.getAssetIntactRate())
                .eqIfPresent(AnalysisAssetDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisAssetDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisAssetDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisAssetDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisAssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisAssetDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisAssetDO::getId));
    }

}