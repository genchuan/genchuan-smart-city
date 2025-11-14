package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisappscene;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisappscene.AnalysisAppSceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按应用场景分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisAppSceneMapper extends BaseMapperX<AnalysisAppSceneDO> {

    default PageResult<AnalysisAppSceneDO> selectPage(AnalysisAppScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisAppSceneDO>()
                .eqIfPresent(AnalysisAppSceneDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisAppSceneDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisAppSceneDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisAppSceneDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisAppSceneDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisAppSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisAppSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisAppSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisAppSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisAppSceneDO::getId));
    }

}