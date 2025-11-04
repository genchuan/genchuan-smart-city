package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.rpt.evalgridrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.evalgridrpt.EvalGridRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价网格统计 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EvalGridRptMapper extends BaseMapperX<EvalGridRptDO> {

    default PageResult<EvalGridRptDO> selectPage(EvalGridRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalGridRptDO>()
                .eqIfPresent(EvalGridRptDO::getEvalStatId, reqVO.getEvalStatId())
                .eqIfPresent(EvalGridRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(EvalGridRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(EvalGridRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EvalGridRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EvalGridRptDO::getGridType, reqVO.getGridType())
                .eqIfPresent(EvalGridRptDO::getIdxId, reqVO.getIdxId())
                .likeIfPresent(EvalGridRptDO::getIdxName, reqVO.getIdxName())
                .eqIfPresent(EvalGridRptDO::getTotalCount, reqVO.getTotalCount())
                .eqIfPresent(EvalGridRptDO::getTotalArea, reqVO.getTotalArea())
                .eqIfPresent(EvalGridRptDO::getAvgMgCount, reqVO.getAvgMgCount())
                .eqIfPresent(EvalGridRptDO::getTotalMgCount, reqVO.getTotalMgCount())
                .eqIfPresent(EvalGridRptDO::getTypeRatio, reqVO.getTypeRatio())
                .eqIfPresent(EvalGridRptDO::getNewCount, reqVO.getNewCount())
                .eqIfPresent(EvalGridRptDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EvalGridRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EvalGridRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(EvalGridRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalGridRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalGridRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalGridRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalGridRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalGridRptDO::getId));
    }

}