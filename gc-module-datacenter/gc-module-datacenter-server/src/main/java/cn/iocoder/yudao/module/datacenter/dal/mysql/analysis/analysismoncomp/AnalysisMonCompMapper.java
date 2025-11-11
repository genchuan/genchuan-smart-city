package cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismoncomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismoncomp.AnalysisMonCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按监测部件分析研判统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AnalysisMonCompMapper extends BaseMapperX<AnalysisMonCompDO> {

    default PageResult<AnalysisMonCompDO> selectPage(AnalysisMonCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AnalysisMonCompDO>()
                .eqIfPresent(AnalysisMonCompDO::getStatAnalysisId, reqVO.getStatAnalysisId())
                .eqIfPresent(AnalysisMonCompDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AnalysisMonCompDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AnalysisMonCompDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(AnalysisMonCompDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(AnalysisMonCompDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(AnalysisMonCompDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(AnalysisMonCompDO::getTotalCompCount, reqVO.getTotalCompCount())
                .eqIfPresent(AnalysisMonCompDO::getOnlineCompCount, reqVO.getOnlineCompCount())
                .eqIfPresent(AnalysisMonCompDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AnalysisMonCompDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(AnalysisMonCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AnalysisMonCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AnalysisMonCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AnalysisMonCompDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(AnalysisMonCompDO::getId));
    }

}