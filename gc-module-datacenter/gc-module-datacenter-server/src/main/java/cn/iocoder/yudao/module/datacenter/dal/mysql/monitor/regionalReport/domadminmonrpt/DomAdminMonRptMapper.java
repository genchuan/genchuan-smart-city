package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domadminmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domadminmonrpt.DomAdminMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行政区划分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomAdminMonRptMapper extends BaseMapperX<DomAdminMonRptDO> {

    default PageResult<DomAdminMonRptDO> selectPage(DomAdminMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomAdminMonRptDO>()
                .eqIfPresent(DomAdminMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomAdminMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomAdminMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomAdminMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomAdminMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomAdminMonRptDO::getAdminLevel, reqVO.getAdminLevel())
                .eqIfPresent(DomAdminMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomAdminMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomAdminMonRptDO::getMonPtCount, reqVO.getMonPtCount())
                .eqIfPresent(DomAdminMonRptDO::getDeviceTotalCount, reqVO.getDeviceTotalCount())
                .eqIfPresent(DomAdminMonRptDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomAdminMonRptDO::getEvtTotalCount, reqVO.getEvtTotalCount())
                .eqIfPresent(DomAdminMonRptDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .betweenIfPresent(DomAdminMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomAdminMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomAdminMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomAdminMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomAdminMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomAdminMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomAdminMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomAdminMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomAdminMonRptDO::getId));
    }

}