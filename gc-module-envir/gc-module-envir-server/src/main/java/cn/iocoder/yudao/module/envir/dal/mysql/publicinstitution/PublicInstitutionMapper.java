package cn.iocoder.yudao.module.envir.dal.mysql.publicinstitution;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.publicinstitution.vo.*;

/**
 * 公共机构 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PublicInstitutionMapper extends BaseMapperX<PublicInstitutionDO> {

    default PageResult<PublicInstitutionDO> selectPage(PublicInstitutionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicInstitutionDO>()
                .eqIfPresent(PublicInstitutionDO::getPublicInstitutionId, reqVO.getPublicInstitutionId())
                .likeIfPresent(PublicInstitutionDO::getName, reqVO.getName())
                .eqIfPresent(PublicInstitutionDO::getInstitutionTypeId, reqVO.getInstitutionTypeId())
                .eqIfPresent(PublicInstitutionDO::getAddress, reqVO.getAddress())
                .eqIfPresent(PublicInstitutionDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PublicInstitutionDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(PublicInstitutionDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(PublicInstitutionDO::getCollectionTime, reqVO.getCollectionTime())
                .eqIfPresent(PublicInstitutionDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(PublicInstitutionDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(PublicInstitutionDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(PublicInstitutionDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(PublicInstitutionDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(PublicInstitutionDO::getProblemRate, reqVO.getProblemRate())
                .eqIfPresent(PublicInstitutionDO::getWasteVolume, reqVO.getWasteVolume())
                .eqIfPresent(PublicInstitutionDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(PublicInstitutionDO::getCleaningPhotoUrl, reqVO.getCleaningPhotoUrl())
                .eqIfPresent(PublicInstitutionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicInstitutionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicInstitutionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PublicInstitutionDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PublicInstitutionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublicInstitutionDO::getId));
    }

    /**
     * 查询公共机构详情列表
     */
    List<PublicInstitutionDetailDO> selectListDetail();

}