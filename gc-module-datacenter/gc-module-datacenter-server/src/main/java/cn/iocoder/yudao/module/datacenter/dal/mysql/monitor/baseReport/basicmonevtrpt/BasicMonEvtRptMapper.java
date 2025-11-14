package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonevtrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测事件报 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface BasicMonEvtRptMapper extends BaseMapperX<BasicMonEvtRptDO> {

    default PageResult<BasicMonEvtRptDO> selectPage(BasicMonEvtRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonEvtRptDO>()
                .eqIfPresent(BasicMonEvtRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(BasicMonEvtRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(BasicMonEvtRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(BasicMonEvtRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(BasicMonEvtRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(BasicMonEvtRptDO::getEvtType, reqVO.getEvtType())
                .eqIfPresent(BasicMonEvtRptDO::getTotalEvtCount, reqVO.getTotalEvtCount())
                .eqIfPresent(BasicMonEvtRptDO::getPendEvtCount, reqVO.getPendEvtCount())
                .eqIfPresent(BasicMonEvtRptDO::getHandlEvtCount, reqVO.getHandlEvtCount())
                .eqIfPresent(BasicMonEvtRptDO::getCompletedEvtCount, reqVO.getCompletedEvtCount())
                .eqIfPresent(BasicMonEvtRptDO::getEvtCompleteRate, reqVO.getEvtCompleteRate())
                .eqIfPresent(BasicMonEvtRptDO::getAvgDealEndure, reqVO.getAvgDealEndure())
                .betweenIfPresent(BasicMonEvtRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(BasicMonEvtRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(BasicMonEvtRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(BasicMonEvtRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonEvtRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonEvtRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonEvtRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonEvtRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonEvtRptDO::getId));
    }

}