package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.relatedriskident;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo.RelatedRiskIdentPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.relatedriskident.RelatedRiskIdentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关联风险识别 Mapper
 *
 * @author zcq
 */
@Mapper
public interface RelatedRiskIdentMapper extends BaseMapperX<RelatedRiskIdentDO> {

    default PageResult<RelatedRiskIdentDO> selectPage(RelatedRiskIdentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RelatedRiskIdentDO>()
                .eqIfPresent(RelatedRiskIdentDO::getIdentId, reqVO.getIdentId())
                .eqIfPresent(RelatedRiskIdentDO::getTriggerWarnId, reqVO.getTriggerWarnId())
                .likeIfPresent(RelatedRiskIdentDO::getTriggerWarnName, reqVO.getTriggerWarnName())
                .eqIfPresent(RelatedRiskIdentDO::getTriggerWarnType, reqVO.getTriggerWarnType())
                .likeIfPresent(RelatedRiskIdentDO::getRelatedRiskName, reqVO.getRelatedRiskName())
                .eqIfPresent(RelatedRiskIdentDO::getRelatedRiskType, reqVO.getRelatedRiskType())
                .eqIfPresent(RelatedRiskIdentDO::getRelatedBasis, reqVO.getRelatedBasis())
                .eqIfPresent(RelatedRiskIdentDO::getRiskLevel, reqVO.getRiskLevel())
                .betweenIfPresent(RelatedRiskIdentDO::getIdentTime, reqVO.getIdentTime())
                .eqIfPresent(RelatedRiskIdentDO::getIdentMethod, reqVO.getIdentMethod())
                .eqIfPresent(RelatedRiskIdentDO::getIdentifyUserId, reqVO.getIdentifyUserId())
                .likeIfPresent(RelatedRiskIdentDO::getIdentifyUserName, reqVO.getIdentifyUserName())
                .eqIfPresent(RelatedRiskIdentDO::getIsHandled, reqVO.getIsHandled())
                .eqIfPresent(RelatedRiskIdentDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(RelatedRiskIdentDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(RelatedRiskIdentDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RelatedRiskIdentDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(RelatedRiskIdentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RelatedRiskIdentDO::getId));
    }

}