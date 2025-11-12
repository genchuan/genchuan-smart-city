package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domappscenemonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用场景分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomAppSceneMonRptMapper extends BaseMapperX<DomAppSceneMonRptDO> {

    default PageResult<DomAppSceneMonRptDO> selectPage(DomAppSceneMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomAppSceneMonRptDO>()
                .eqIfPresent(DomAppSceneMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomAppSceneMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomAppSceneMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomAppSceneMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomAppSceneMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomAppSceneMonRptDO::getAppSceneMajorId, reqVO.getAppSceneMajorId())
                .likeIfPresent(DomAppSceneMonRptDO::getAppSceneMajorName, reqVO.getAppSceneMajorName())
                .eqIfPresent(DomAppSceneMonRptDO::getAppSceneMidId, reqVO.getAppSceneMidId())
                .likeIfPresent(DomAppSceneMonRptDO::getAppSceneMidName, reqVO.getAppSceneMidName())
                .eqIfPresent(DomAppSceneMonRptDO::getAppSceneMinorId, reqVO.getAppSceneMinorId())
                .likeIfPresent(DomAppSceneMonRptDO::getAppSceneMinorName, reqVO.getAppSceneMinorName())
                .eqIfPresent(DomAppSceneMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomAppSceneMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomAppSceneMonRptDO::getTotalDeviceCount, reqVO.getTotalDeviceCount())
                .eqIfPresent(DomAppSceneMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomAppSceneMonRptDO::getSceneEvtCount, reqVO.getSceneEvtCount())
                .eqIfPresent(DomAppSceneMonRptDO::getEvtHandleRate, reqVO.getEvtHandleRate())
                .eqIfPresent(DomAppSceneMonRptDO::getResDispatchCount, reqVO.getResDispatchCount())
                .eqIfPresent(DomAppSceneMonRptDO::getSceneHealthRate, reqVO.getSceneHealthRate())
                .betweenIfPresent(DomAppSceneMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomAppSceneMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomAppSceneMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomAppSceneMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomAppSceneMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomAppSceneMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomAppSceneMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomAppSceneMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomAppSceneMonRptDO::getId));
    }

}