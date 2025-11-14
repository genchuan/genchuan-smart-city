package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommngmatterrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomMngMatterRptMapper extends BaseMapperX<DomMngMatterRptDO> {

    default PageResult<DomMngMatterRptDO> selectPage(DomMngMatterRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomMngMatterRptDO>()
                .eqIfPresent(DomMngMatterRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomMngMatterRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomMngMatterRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomMngMatterRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomMngMatterRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomMngMatterRptDO::getMngMatterMajorId, reqVO.getMngMatterMajorId())
                .likeIfPresent(DomMngMatterRptDO::getMngMatterMajorName, reqVO.getMngMatterMajorName())
                .eqIfPresent(DomMngMatterRptDO::getMngMatterMinorId, reqVO.getMngMatterMinorId())
                .likeIfPresent(DomMngMatterRptDO::getMngMatterMinorName, reqVO.getMngMatterMinorName())
                .eqIfPresent(DomMngMatterRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomMngMatterRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomMngMatterRptDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomMngMatterRptDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomMngMatterRptDO::getTotalEvtCount, reqVO.getTotalEvtCount())
                .eqIfPresent(DomMngMatterRptDO::getPendEvtCount, reqVO.getPendEvtCount())
                .eqIfPresent(DomMngMatterRptDO::getHandlEvtCount, reqVO.getHandlEvtCount())
                .eqIfPresent(DomMngMatterRptDO::getCompletedEvtCount, reqVO.getCompletedEvtCount())
                .eqIfPresent(DomMngMatterRptDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .eqIfPresent(DomMngMatterRptDO::getAvgHandleEndure, reqVO.getAvgHandleEndure())
                .betweenIfPresent(DomMngMatterRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomMngMatterRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomMngMatterRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomMngMatterRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomMngMatterRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomMngMatterRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomMngMatterRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomMngMatterRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomMngMatterRptDO::getId));
    }

}