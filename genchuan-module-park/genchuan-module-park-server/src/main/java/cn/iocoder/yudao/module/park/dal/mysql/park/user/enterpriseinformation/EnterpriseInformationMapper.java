package cn.iocoder.yudao.module.park.dal.mysql.park.user.enterpriseinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.enterpriseinformation.EnterpriseInformationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EnterpriseInformationMapper extends BaseMapperX<EnterpriseInformationDO> {

    default PageResult<EnterpriseInformationDO> selectPage(EnterpriseInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnterpriseInformationDO>()
                .likeIfPresent(EnterpriseInformationDO::getEnterpriseName, reqVO.getEnterpriseName())
                .eqIfPresent(EnterpriseInformationDO::getCreditCode, reqVO.getCreditCode())
                .eqIfPresent(EnterpriseInformationDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(EnterpriseInformationDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(EnterpriseInformationDO::getRegisterAddress, reqVO.getRegisterAddress())
                .eqIfPresent(EnterpriseInformationDO::getIndustryType, reqVO.getIndustryType())
                .eqIfPresent(EnterpriseInformationDO::getAdminId, reqVO.getAdminId())
                .eqIfPresent(EnterpriseInformationDO::getProxyId, reqVO.getProxyId())
                .eqIfPresent(EnterpriseInformationDO::getCertStatus, reqVO.getCertStatus())
                .betweenIfPresent(EnterpriseInformationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EnterpriseInformationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EnterpriseInformationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EnterpriseInformationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EnterpriseInformationDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(EnterpriseInformationDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(EnterpriseInformationDO::getId));
    }

}
