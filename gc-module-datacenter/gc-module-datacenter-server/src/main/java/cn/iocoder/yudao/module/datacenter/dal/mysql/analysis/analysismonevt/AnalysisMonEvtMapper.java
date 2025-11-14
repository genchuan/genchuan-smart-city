package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismonevt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismonevt.AnalysisMonEvtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按监测事件分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisMonEvtMapper extends BaseMapperX<AnalysisMonEvtDO> {

    default PageResult<AnalysisMonEvtDO> selectPage(AnalysisMonEvtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisMonEvtDO>()
                .eqIfPresent(AnalysisMonEvtDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisMonEvtDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisMonEvtDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisMonEvtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisMonEvtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisMonEvtDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisMonEvtDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisMonEvtDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisMonEvtDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisMonEvtDO::getId));
    }

}