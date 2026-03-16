package cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.PublicInstitutionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公共机构 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PublicInstitutionMapper extends BaseMapperX<PublicInstitutionDO> {

    default PageResult<PublicInstitutionDO> selectPage(PublicInstitutionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicInstitutionDO>()
                .eqIfPresent(PublicInstitutionDO::getInstitutionId, reqVO.getInstitutionId())
                .likeIfPresent(PublicInstitutionDO::getName, reqVO.getName())
                .eqIfPresent(PublicInstitutionDO::getInstitutionTypeId, reqVO.getInstitutionTypeId())
                .eqIfPresent(PublicInstitutionDO::getAddress, reqVO.getAddress())
                .eqIfPresent(PublicInstitutionDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PublicInstitutionDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(PublicInstitutionDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(PublicInstitutionDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(PublicInstitutionDO::getProblemRate, reqVO.getProblemRate())
                .eqIfPresent(PublicInstitutionDO::getWasteVolume, reqVO.getWasteVolume())
                .eqIfPresent(PublicInstitutionDO::getInspectionPassRate, reqVO.getInspectionPassRate())
                .eqIfPresent(PublicInstitutionDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(PublicInstitutionDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(PublicInstitutionDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(PublicInstitutionDO::getCleanerIds, reqVO.getCleanerIds())
                .eqIfPresent(PublicInstitutionDO::getResponsibilityArea, reqVO.getResponsibilityArea())
                .betweenIfPresent(PublicInstitutionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublicInstitutionDO::getId));
    }

    /**
     * 查询全局最大序号（用于institution_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(institution_id, '-', -1)), 0) FROM public_institution")
    Integer selectMaxSeq();

    List<PublicInstitutionDetailDO> selectDetailPage(@Param("reqVO") PublicInstitutionPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") PublicInstitutionPageReqVO pageReqVO);
}