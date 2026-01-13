package cn.iocoder.yudao.module.industry.dal.mysql.park.through.trafficinspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.trafficinspection.ParkTrafficInspectionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通行稽查 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkTrafficInspectionMapper extends BaseMapperX<ParkTrafficInspectionDO> {

    default PageResult<ParkTrafficInspectionDO> selectPage(ParkTrafficInspectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkTrafficInspectionDO>()
                .eqIfPresent(ParkTrafficInspectionDO::getInspectionId, reqVO.getInspectionId())
                .eqIfPresent(ParkTrafficInspectionDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkTrafficInspectionDO::getEntryId, reqVO.getEntryId())
                .eqIfPresent(ParkTrafficInspectionDO::getExitId, reqVO.getExitId())
                .eqIfPresent(ParkTrafficInspectionDO::getViolationType, reqVO.getViolationType())
                .betweenIfPresent(ParkTrafficInspectionDO::getViolationTime, reqVO.getViolationTime())
                .eqIfPresent(ParkTrafficInspectionDO::getEvidenceIds, reqVO.getEvidenceIds())
                .eqIfPresent(ParkTrafficInspectionDO::getDisposalStatus, reqVO.getDisposalStatus())
                .eqIfPresent(ParkTrafficInspectionDO::getDisposalContent, reqVO.getDisposalContent())
                .eqIfPresent(ParkTrafficInspectionDO::getDisposalBy, reqVO.getDisposalBy())
                .betweenIfPresent(ParkTrafficInspectionDO::getDisposalTime, reqVO.getDisposalTime())
                .betweenIfPresent(ParkTrafficInspectionDO::getInspectionCreateTime, reqVO.getInspectionCreateTime())
                .betweenIfPresent(ParkTrafficInspectionDO::getInspectionUpdateTime, reqVO.getInspectionUpdateTime())
                .eqIfPresent(ParkTrafficInspectionDO::getInspectionRemark, reqVO.getInspectionRemark())
                .betweenIfPresent(ParkTrafficInspectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkTrafficInspectionDO::getId));
    }

}