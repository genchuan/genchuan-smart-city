package cn.iocoder.yudao.module.datacenter.dal.mysql.polcomplianceck;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.polcomplianceck.PolComplianceCkDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck.vo.*;

/**
 * 政策合规校验 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PolComplianceCkMapper extends BaseMapperX<PolComplianceCkDO> {

    default PageResult<PolComplianceCkDO> selectPage(PolComplianceCkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PolComplianceCkDO>()
                .eqIfPresent(PolComplianceCkDO::getComplianceCkId, reqVO.getComplianceCkId())
                .eqIfPresent(PolComplianceCkDO::getRelSchemeId, reqVO.getRelSchemeId())
                .likeIfPresent(PolComplianceCkDO::getRelSchemeName, reqVO.getRelSchemeName())
                .eqIfPresent(PolComplianceCkDO::getCkPolIds, reqVO.getCkPolIds())
                .eqIfPresent(PolComplianceCkDO::getCkPolNames, reqVO.getCkPolNames())
                .eqIfPresent(PolComplianceCkDO::getCkStatus, reqVO.getCkStatus())
                .eqIfPresent(PolComplianceCkDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(PolComplianceCkDO::getRiskPtCount, reqVO.getRiskPtCount())
                .eqIfPresent(PolComplianceCkDO::getComplianceRate, reqVO.getComplianceRate())
                .eqIfPresent(PolComplianceCkDO::getCkConclusion, reqVO.getCkConclusion())
                .eqIfPresent(PolComplianceCkDO::getCkUser, reqVO.getCkUser())
                .betweenIfPresent(PolComplianceCkDO::getCkTime, reqVO.getCkTime())
                .eqIfPresent(PolComplianceCkDO::getRectificationSugg, reqVO.getRectificationSugg())
                .eqIfPresent(PolComplianceCkDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(PolComplianceCkDO::getReviewUser, reqVO.getReviewUser())
                .betweenIfPresent(PolComplianceCkDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(PolComplianceCkDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PolComplianceCkDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PolComplianceCkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PolComplianceCkDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PolComplianceCkDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PolComplianceCkDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PolComplianceCkDO::getId));
    }

}