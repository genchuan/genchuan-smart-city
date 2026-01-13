package cn.iocoder.yudao.module.industry.dal.mysql.park.through.inspectionevidence;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidencePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.inspectionevidence.ParkInspectionEvidenceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 稽查证据 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkInspectionEvidenceMapper extends BaseMapperX<ParkInspectionEvidenceDO> {

    default PageResult<ParkInspectionEvidenceDO> selectPage(ParkInspectionEvidencePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkInspectionEvidenceDO>()
                .eqIfPresent(ParkInspectionEvidenceDO::getEvidenceId, reqVO.getEvidenceId())
                .eqIfPresent(ParkInspectionEvidenceDO::getInspectionId, reqVO.getInspectionId())
                .eqIfPresent(ParkInspectionEvidenceDO::getEvidenceType, reqVO.getEvidenceType())
                .eqIfPresent(ParkInspectionEvidenceDO::getEvidenceUrl, reqVO.getEvidenceUrl())
                .eqIfPresent(ParkInspectionEvidenceDO::getEvidenceDesc, reqVO.getEvidenceDesc())
                .betweenIfPresent(ParkInspectionEvidenceDO::getUploadTime, reqVO.getUploadTime())
                .eqIfPresent(ParkInspectionEvidenceDO::getUploadBy, reqVO.getUploadBy())
                .betweenIfPresent(ParkInspectionEvidenceDO::getEvidenceCreateTime, reqVO.getEvidenceCreateTime())
                .eqIfPresent(ParkInspectionEvidenceDO::getEvidenceRemark, reqVO.getEvidenceRemark())
                .betweenIfPresent(ParkInspectionEvidenceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkInspectionEvidenceDO::getId));
    }

}