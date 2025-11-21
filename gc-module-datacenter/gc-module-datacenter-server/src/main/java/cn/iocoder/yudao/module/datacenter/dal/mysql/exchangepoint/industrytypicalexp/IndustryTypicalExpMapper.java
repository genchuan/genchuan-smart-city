package cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.industrytypicalexp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo.IndustryTypicalExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.industrytypicalexp.IndustryTypicalExpDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业典型经验 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndustryTypicalExpMapper extends BaseMapperX<IndustryTypicalExpDO> {

    default PageResult<IndustryTypicalExpDO> selectPage(IndustryTypicalExpPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndustryTypicalExpDO>()
                .eqIfPresent(IndustryTypicalExpDO::getIndustryTypicalExpId, reqVO.getIndustryTypicalExpId())
                .eqIfPresent(IndustryTypicalExpDO::getExpTitle, reqVO.getExpTitle())
                .eqIfPresent(IndustryTypicalExpDO::getIndustryFieldCode, reqVO.getIndustryFieldCode())
                .likeIfPresent(IndustryTypicalExpDO::getIndustryFieldName, reqVO.getIndustryFieldName())
                .eqIfPresent(IndustryTypicalExpDO::getCaseOrg, reqVO.getCaseOrg())
                .eqIfPresent(IndustryTypicalExpDO::getExpHighlights, reqVO.getExpHighlights())
                .eqIfPresent(IndustryTypicalExpDO::getImplementationEffect, reqVO.getImplementationEffect())
                .eqIfPresent(IndustryTypicalExpDO::getAttachId, reqVO.getAttachId())
                .eqIfPresent(IndustryTypicalExpDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(IndustryTypicalExpDO::getPublishStatus, reqVO.getPublishStatus())
                .eqIfPresent(IndustryTypicalExpDO::getLikeCount, reqVO.getLikeCount())
                .eqIfPresent(IndustryTypicalExpDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(IndustryTypicalExpDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(IndustryTypicalExpDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(IndustryTypicalExpDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(IndustryTypicalExpDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(IndustryTypicalExpDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndustryTypicalExpDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndustryTypicalExpDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(IndustryTypicalExpDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(IndustryTypicalExpDO::getId));
    }

}