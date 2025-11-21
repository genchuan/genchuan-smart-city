package cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publiccomplain;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publiccomplain.PublicComplainDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 投诉举报 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicComplainMapper extends BaseMapperX<PublicComplainDO> {

    default PageResult<PublicComplainDO> selectPage(PublicComplainPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicComplainDO>()
                .eqIfPresent(PublicComplainDO::getComplainId, reqVO.getComplainId())
                .eqIfPresent(PublicComplainDO::getComplainNo, reqVO.getComplainNo())
                .eqIfPresent(PublicComplainDO::getAppealTypeCode, reqVO.getAppealTypeCode())
                .likeIfPresent(PublicComplainDO::getAppealTypeName, reqVO.getAppealTypeName())
                .eqIfPresent(PublicComplainDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PublicComplainDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(PublicComplainDO::getIncidentAddr, reqVO.getIncidentAddr())
                .eqIfPresent(PublicComplainDO::getComplainContent, reqVO.getComplainContent())
                .eqIfPresent(PublicComplainDO::getImgPath, reqVO.getImgPath())
                .likeIfPresent(PublicComplainDO::getContactName, reqVO.getContactName())
                .eqIfPresent(PublicComplainDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(PublicComplainDO::getAcceptDeptCode, reqVO.getAcceptDeptCode())
                .likeIfPresent(PublicComplainDO::getAcceptDeptName, reqVO.getAcceptDeptName())
                .eqIfPresent(PublicComplainDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(PublicComplainDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(PublicComplainDO::getSubmitTime, reqVO.getSubmitTime())
                .betweenIfPresent(PublicComplainDO::getAcceptTime, reqVO.getAcceptTime())
                .betweenIfPresent(PublicComplainDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(PublicComplainDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicComplainDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicComplainDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicComplainDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicComplainDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicComplainDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicComplainDO::getId));
    }

}