package cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionInspectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionInspectionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
                .betweenIfPresent(InstitutionInspectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstitutionInspectionDO::getId));
    }

    /**
     * 查询全局最大序号（用于inspection_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(inspection_id, '-', -1)), 0) FROM public_institution_inspection")
    Integer selectMaxSeq();

    List<InstitutionInspectionDetailDO> selectDetailPage(@Param("reqVO") InstitutionInspectionPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") InstitutionInspectionPageReqVO pageReqVO);
}