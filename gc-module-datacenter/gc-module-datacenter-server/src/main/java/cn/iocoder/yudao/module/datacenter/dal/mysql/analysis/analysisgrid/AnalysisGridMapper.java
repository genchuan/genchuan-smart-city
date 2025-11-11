package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisgrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisgrid.AnalysisGridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按网格分域分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisGridMapper extends BaseMapperX<AnalysisGridDO> {

    default PageResult<AnalysisGridDO> selectPage(AnalysisGridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisGridDO>()
                .eqIfPresent(AnalysisGridDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisGridDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisGridDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisGridDO::getGridId, reqVO.getGridId())
                .likeIfPresent(AnalysisGridDO::getGridName, reqVO.getGridName())
                .eqIfPresent(AnalysisGridDO::getGridType, reqVO.getGridType())
                .eqIfPresent(AnalysisGridDO::getStreetCode, reqVO.getStreetCode())
                .likeIfPresent(AnalysisGridDO::getStreetName, reqVO.getStreetName())
                .eqIfPresent(AnalysisGridDO::getMonCompOnlineRate, reqVO.getMonCompOnlineRate())
                .eqIfPresent(AnalysisGridDO::getEvtTimelyHandleRate, reqVO.getEvtTimelyHandleRate())
                .eqIfPresent(AnalysisGridDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisGridDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisGridDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisGridDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisGridDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisGridDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisGridDO::getId));
    }

}