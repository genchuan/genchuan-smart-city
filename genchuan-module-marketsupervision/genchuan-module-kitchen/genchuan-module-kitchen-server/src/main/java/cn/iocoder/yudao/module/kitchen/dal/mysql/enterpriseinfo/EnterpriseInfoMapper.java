package cn.iocoder.yudao.module.kitchen.dal.mysql.enterpriseinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EnterpriseInfoMapper extends BaseMapperX<EnterpriseInfoDO> {

    default PageResult<EnterpriseInfoDO> selectPage(EnterpriseInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnterpriseInfoDO>()
                .eqIfPresent(EnterpriseInfoDO::getEntCode, reqVO.getEntCode())
                .likeIfPresent(EnterpriseInfoDO::getEntName, reqVO.getEntName())
                .eqIfPresent(EnterpriseInfoDO::getAreaId, reqVO.getAreaId())
                .likeIfPresent(EnterpriseInfoDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(EnterpriseInfoDO::getEntTypeId, reqVO.getEntTypeId())
                .eqIfPresent(EnterpriseInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EnterpriseInfoDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(EnterpriseInfoDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(EnterpriseInfoDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(EnterpriseInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EnterpriseInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EnterpriseInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EnterpriseInfoDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(EnterpriseInfoDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(EnterpriseInfoDO::getId));
    }

}
