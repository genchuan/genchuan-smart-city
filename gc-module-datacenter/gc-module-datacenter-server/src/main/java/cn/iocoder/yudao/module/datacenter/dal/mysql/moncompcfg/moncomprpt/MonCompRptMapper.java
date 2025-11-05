package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncomprpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncomprpt.MonCompRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件统计报 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompRptMapper extends BaseMapperX<MonCompRptDO> {

    default PageResult<MonCompRptDO> selectPage(MonCompRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompRptDO>()
                .eqIfPresent(MonCompRptDO::getStatId, reqVO.getStatId())
                .eqIfPresent(MonCompRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MonCompRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MonCompRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(MonCompRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(MonCompRptDO::getCompMajorId, reqVO.getCompMajorId())
                .likeIfPresent(MonCompRptDO::getCompMajorName, reqVO.getCompMajorName())
                .eqIfPresent(MonCompRptDO::getCompMinorId, reqVO.getCompMinorId())
                .likeIfPresent(MonCompRptDO::getCompMinorName, reqVO.getCompMinorName())
                .eqIfPresent(MonCompRptDO::getTotalCompCount, reqVO.getTotalCompCount())
                .eqIfPresent(MonCompRptDO::getNormalCompCount, reqVO.getNormalCompCount())
                .eqIfPresent(MonCompRptDO::getAbnCompCount, reqVO.getAbnCompCount())
                .eqIfPresent(MonCompRptDO::getMntCompCount, reqVO.getMntCompCount())
                .eqIfPresent(MonCompRptDO::getDiscardCompCount, reqVO.getDiscardCompCount())
                .eqIfPresent(MonCompRptDO::getNewCompCount, reqVO.getNewCompCount())
                .eqIfPresent(MonCompRptDO::getUpdateCompCount, reqVO.getUpdateCompCount())
                .eqIfPresent(MonCompRptDO::getStatUser, reqVO.getStatUser())
                .betweenIfPresent(MonCompRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(MonCompRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(MonCompRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MonCompRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MonCompRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonCompRptDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(MonCompRptDO::getId));
    }

}