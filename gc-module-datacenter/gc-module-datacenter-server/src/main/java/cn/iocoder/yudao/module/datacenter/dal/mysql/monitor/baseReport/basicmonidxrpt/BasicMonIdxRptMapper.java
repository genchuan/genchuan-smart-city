package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonidxrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测指标报 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface BasicMonIdxRptMapper extends BaseMapperX<BasicMonIdxRptDO> {

    default PageResult<BasicMonIdxRptDO> selectPage(BasicMonIdxRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonIdxRptDO>()
                .eqIfPresent(BasicMonIdxRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(BasicMonIdxRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(BasicMonIdxRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(BasicMonIdxRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(BasicMonIdxRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(BasicMonIdxRptDO::getPtType, reqVO.getPtType())
                .eqIfPresent(BasicMonIdxRptDO::getTotalPtCount, reqVO.getTotalPtCount())
                .eqIfPresent(BasicMonIdxRptDO::getEnablePtCount, reqVO.getEnablePtCount())
                .eqIfPresent(BasicMonIdxRptDO::getDisablePtCount, reqVO.getDisablePtCount())
                .eqIfPresent(BasicMonIdxRptDO::getNewPtCount, reqVO.getNewPtCount())
                .eqIfPresent(BasicMonIdxRptDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .betweenIfPresent(BasicMonIdxRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(BasicMonIdxRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(BasicMonIdxRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(BasicMonIdxRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonIdxRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonIdxRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonIdxRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonIdxRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonIdxRptDO::getId));
    }

}