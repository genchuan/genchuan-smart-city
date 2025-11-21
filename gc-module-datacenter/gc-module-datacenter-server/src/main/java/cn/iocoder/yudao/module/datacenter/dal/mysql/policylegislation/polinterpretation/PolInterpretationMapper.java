package cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.polinterpretation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo.PolInterpretationPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.polinterpretation.PolInterpretationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 政策解读信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PolInterpretationMapper extends BaseMapperX<PolInterpretationDO> {

    default PageResult<PolInterpretationDO> selectPage(PolInterpretationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PolInterpretationDO>()
                .eqIfPresent(PolInterpretationDO::getPolInterpretationId, reqVO.getPolInterpretationId())
                .eqIfPresent(PolInterpretationDO::getRelatedPolType, reqVO.getRelatedPolType())
                .eqIfPresent(PolInterpretationDO::getRelatedPolId, reqVO.getRelatedPolId())
                .eqIfPresent(PolInterpretationDO::getRelatedPolTitle, reqVO.getRelatedPolTitle())
                .eqIfPresent(PolInterpretationDO::getInterpretationTitle, reqVO.getInterpretationTitle())
                .eqIfPresent(PolInterpretationDO::getInterpretationContent, reqVO.getInterpretationContent())
                .eqIfPresent(PolInterpretationDO::getInterpreterId, reqVO.getInterpreterId())
                .likeIfPresent(PolInterpretationDO::getInterpreterName, reqVO.getInterpreterName())
                .betweenIfPresent(PolInterpretationDO::getInterpretationTime, reqVO.getInterpretationTime())
                .eqIfPresent(PolInterpretationDO::getInterpretationStatus, reqVO.getInterpretationStatus())
                .eqIfPresent(PolInterpretationDO::getAttachId, reqVO.getAttachId())
                .eqIfPresent(PolInterpretationDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(PolInterpretationDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(PolInterpretationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PolInterpretationDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(PolInterpretationDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PolInterpretationDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PolInterpretationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PolInterpretationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PolInterpretationDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PolInterpretationDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PolInterpretationDO::getId));
    }

}