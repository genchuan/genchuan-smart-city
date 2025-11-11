package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngcomp.AnalysisMngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按管理部件分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisMngCompMapper extends BaseMapperX<AnalysisMngCompDO> {

    default PageResult<AnalysisMngCompDO> selectPage(AnalysisMngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisMngCompDO>()
                .eqIfPresent(AnalysisMngCompDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisMngCompDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisMngCompDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisMngCompDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(AnalysisMngCompDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(AnalysisMngCompDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(AnalysisMngCompDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(AnalysisMngCompDO::getTotalCompStock, reqVO.getTotalCompStock())
                .eqIfPresent(AnalysisMngCompDO::getDamagedCompCount, reqVO.getDamagedCompCount())
                .eqIfPresent(AnalysisMngCompDO::getCompDamageRate, reqVO.getCompDamageRate())
                .eqIfPresent(AnalysisMngCompDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisMngCompDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisMngCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisMngCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisMngCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisMngCompDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisMngCompDO::getId));
    }

}