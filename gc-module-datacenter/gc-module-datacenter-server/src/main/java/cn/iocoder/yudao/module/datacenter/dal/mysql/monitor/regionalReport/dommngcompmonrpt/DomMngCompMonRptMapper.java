package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommngcompmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomMngCompMonRptMapper extends BaseMapperX<DomMngCompMonRptDO> {

    default PageResult<DomMngCompMonRptDO> selectPage(DomMngCompMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomMngCompMonRptDO>()
                .eqIfPresent(DomMngCompMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomMngCompMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomMngCompMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomMngCompMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomMngCompMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomMngCompMonRptDO::getMngCompMajorId, reqVO.getMngCompMajorId())
                .likeIfPresent(DomMngCompMonRptDO::getMngCompMajorName, reqVO.getMngCompMajorName())
                .eqIfPresent(DomMngCompMonRptDO::getMngCompMinorId, reqVO.getMngCompMinorId())
                .likeIfPresent(DomMngCompMonRptDO::getMngCompMinorName, reqVO.getMngCompMinorName())
                .eqIfPresent(DomMngCompMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomMngCompMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomMngCompMonRptDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomMngCompMonRptDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomMngCompMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomMngCompMonRptDO::getCompFaultEvtCount, reqVO.getCompFaultEvtCount())
                .eqIfPresent(DomMngCompMonRptDO::getFaultRepairRate, reqVO.getFaultRepairRate())
                .betweenIfPresent(DomMngCompMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomMngCompMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomMngCompMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomMngCompMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomMngCompMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomMngCompMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomMngCompMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomMngCompMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomMngCompMonRptDO::getId));
    }

}