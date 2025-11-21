package cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publicconsultation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicconsultation.PublicConsultationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 咨询建议 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicConsultationMapper extends BaseMapperX<PublicConsultationDO> {

    default PageResult<PublicConsultationDO> selectPage(PublicConsultationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicConsultationDO>()
                .eqIfPresent(PublicConsultationDO::getConsultId, reqVO.getConsultId())
                .eqIfPresent(PublicConsultationDO::getConsultNo, reqVO.getConsultNo())
                .eqIfPresent(PublicConsultationDO::getConsultTypeCode, reqVO.getConsultTypeCode())
                .likeIfPresent(PublicConsultationDO::getConsultTypeName, reqVO.getConsultTypeName())
                .eqIfPresent(PublicConsultationDO::getConsultContent, reqVO.getConsultContent())
                .likeIfPresent(PublicConsultationDO::getContactName, reqVO.getContactName())
                .eqIfPresent(PublicConsultationDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(PublicConsultationDO::getAcceptDeptCode, reqVO.getAcceptDeptCode())
                .likeIfPresent(PublicConsultationDO::getAcceptDeptName, reqVO.getAcceptDeptName())
                .eqIfPresent(PublicConsultationDO::getReplyContent, reqVO.getReplyContent())
                .eqIfPresent(PublicConsultationDO::getConsultStatus, reqVO.getConsultStatus())
                .betweenIfPresent(PublicConsultationDO::getSubmitTime, reqVO.getSubmitTime())
                .betweenIfPresent(PublicConsultationDO::getReplyTime, reqVO.getReplyTime())
                .eqIfPresent(PublicConsultationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicConsultationDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicConsultationDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicConsultationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicConsultationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicConsultationDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicConsultationDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicConsultationDO::getId));
    }

}