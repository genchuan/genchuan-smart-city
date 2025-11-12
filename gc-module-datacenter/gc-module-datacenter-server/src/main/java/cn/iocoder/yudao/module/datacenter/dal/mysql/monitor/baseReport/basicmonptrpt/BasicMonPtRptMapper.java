package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonptrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonptrpt.BasicMonPtRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测点位报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface BasicMonPtRptMapper extends BaseMapperX<BasicMonPtRptDO> {

    default PageResult<BasicMonPtRptDO> selectPage(BasicMonPtRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonPtRptDO>()
                .eqIfPresent(BasicMonPtRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(BasicMonPtRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(BasicMonPtRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(BasicMonPtRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(BasicMonPtRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(BasicMonPtRptDO::getPtType, reqVO.getPtType())
                .eqIfPresent(BasicMonPtRptDO::getTotalPtCount, reqVO.getTotalPtCount())
                .eqIfPresent(BasicMonPtRptDO::getEnablePtCount, reqVO.getEnablePtCount())
                .eqIfPresent(BasicMonPtRptDO::getDisablePtCount, reqVO.getDisablePtCount())
                .eqIfPresent(BasicMonPtRptDO::getNewPtCount, reqVO.getNewPtCount())
                .eqIfPresent(BasicMonPtRptDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .betweenIfPresent(BasicMonPtRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(BasicMonPtRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(BasicMonPtRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(BasicMonPtRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonPtRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonPtRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonPtRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonPtRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonPtRptDO::getId));
    }

}