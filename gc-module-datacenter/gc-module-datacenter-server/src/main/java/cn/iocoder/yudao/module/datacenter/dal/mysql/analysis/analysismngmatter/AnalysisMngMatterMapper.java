package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismngmatter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngmatter.AnalysisMngMatterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按管理事项分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisMngMatterMapper extends BaseMapperX<AnalysisMngMatterDO> {

    default PageResult<AnalysisMngMatterDO> selectPage(AnalysisMngMatterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisMngMatterDO>()
                .eqIfPresent(AnalysisMngMatterDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisMngMatterDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisMngMatterDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisMngMatterDO::getMatterMajorId, reqVO.getMatterMajorId())
                .likeIfPresent(AnalysisMngMatterDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(AnalysisMngMatterDO::getMatterMinorId, reqVO.getMatterMinorId())
                .likeIfPresent(AnalysisMngMatterDO::getMatterMinorName, reqVO.getMatterMinorName())
                .eqIfPresent(AnalysisMngMatterDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(AnalysisMngMatterDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(AnalysisMngMatterDO::getTotalRptCount, reqVO.getTotalRptCount())
                .eqIfPresent(AnalysisMngMatterDO::getMatterCompleteRate, reqVO.getMatterCompleteRate())
                .eqIfPresent(AnalysisMngMatterDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisMngMatterDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisMngMatterDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisMngMatterDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisMngMatterDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisMngMatterDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisMngMatterDO::getId));
    }

}