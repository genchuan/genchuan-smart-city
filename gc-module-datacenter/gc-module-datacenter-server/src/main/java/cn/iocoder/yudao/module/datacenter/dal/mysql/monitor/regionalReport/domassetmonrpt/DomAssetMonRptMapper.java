package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domassetmonrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domassetmonrpt.DomAssetMonRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分域监测报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomAssetMonRptMapper extends BaseMapperX<DomAssetMonRptDO> {

    default PageResult<DomAssetMonRptDO> selectPage(DomAssetMonRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomAssetMonRptDO>()
                .eqIfPresent(DomAssetMonRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(DomAssetMonRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(DomAssetMonRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(DomAssetMonRptDO::getAdminCode, reqVO.getAdminCode())
                .likeIfPresent(DomAssetMonRptDO::getAdminName, reqVO.getAdminName())
                .eqIfPresent(DomAssetMonRptDO::getAssetMajorId, reqVO.getAssetMajorId())
                .likeIfPresent(DomAssetMonRptDO::getAssetMajorName, reqVO.getAssetMajorName())
                .eqIfPresent(DomAssetMonRptDO::getAssetMinorId, reqVO.getAssetMinorId())
                .likeIfPresent(DomAssetMonRptDO::getAssetMinorName, reqVO.getAssetMinorName())
                .eqIfPresent(DomAssetMonRptDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomAssetMonRptDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomAssetMonRptDO::getTotalAssetCount, reqVO.getTotalAssetCount())
                .eqIfPresent(DomAssetMonRptDO::getInUseAssetCount, reqVO.getInUseAssetCount())
                .eqIfPresent(DomAssetMonRptDO::getAssetInUseRate, reqVO.getAssetInUseRate())
                .eqIfPresent(DomAssetMonRptDO::getTotalMntCount, reqVO.getTotalMntCount())
                .eqIfPresent(DomAssetMonRptDO::getAvgAssetLife, reqVO.getAvgAssetLife())
                .eqIfPresent(DomAssetMonRptDO::getAssetFaultEvtCount, reqVO.getAssetFaultEvtCount())
                .betweenIfPresent(DomAssetMonRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(DomAssetMonRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(DomAssetMonRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(DomAssetMonRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomAssetMonRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomAssetMonRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomAssetMonRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomAssetMonRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomAssetMonRptDO::getId));
    }

}