package cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionInspectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionInspectionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共机构核查 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface InstitutionInspectionMapper extends BaseMapperX<InstitutionInspectionDO> {

    default PageResult<InstitutionInspectionDO> selectPage(InstitutionInspectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstitutionInspectionDO>()
                .eqIfPresent(InstitutionInspectionDO::getInspectionId, reqVO.getInspectionId())
                .eqIfPresent(InstitutionInspectionDO::getInstitutionId, reqVO.getInstitutionId())
                .eqIfPresent(InstitutionInspectionDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InstitutionInspectionDO::getTaskTypeId, reqVO.getTaskTypeId())
                .eqIfPresent(InstitutionInspectionDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(InstitutionInspectionDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(InstitutionInspectionDO::getReportResult, reqVO.getReportResult())
                .eqIfPresent(InstitutionInspectionDO::getInspectionStatus, reqVO.getInspectionStatus())
                .eqIfPresent(InstitutionInspectionDO::getInspectBy, reqVO.getInspectBy())
                .betweenIfPresent(InstitutionInspectionDO::getInspectionTime, reqVO.getInspectionTime())
                .eqIfPresent(InstitutionInspectionDO::getReformRequire, reqVO.getReformRequire())
                .eqIfPresent(InstitutionInspectionDO::getInspectionPhoto, reqVO.getInspectionPhoto())
                .eqIfPresent(InstitutionInspectionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InstitutionInspectionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(InstitutionInspectionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(InstitutionInspectionDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(InstitutionInspectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstitutionInspectionDO::getId));
    }

    List<InstitutionInspectionDetailDO> selectDetailPage(@Param("reqVO") InstitutionInspectionPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") InstitutionInspectionPageReqVO pageReqVO);
}