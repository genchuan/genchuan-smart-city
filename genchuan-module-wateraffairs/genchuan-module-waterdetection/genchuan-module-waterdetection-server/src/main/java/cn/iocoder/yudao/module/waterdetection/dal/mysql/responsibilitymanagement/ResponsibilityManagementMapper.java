package cn.iocoder.yudao.module.waterdetection.dal.mysql.responsibilitymanagement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.responsibilitymanagement.ResponsibilityManagementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo.*;

/**
 * 责任单位及责任人管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface ResponsibilityManagementMapper extends BaseMapperX<ResponsibilityManagementDO> {

    default PageResult<ResponsibilityManagementDO> selectPage(ResponsibilityManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ResponsibilityManagementDO>()
                .eqIfPresent(ResponsibilityManagementDO::getResponsibilityType, reqVO.getResponsibilityType())
                .eqIfPresent(ResponsibilityManagementDO::getResponsibleUnit, reqVO.getResponsibleUnit())
                .eqIfPresent(ResponsibilityManagementDO::getResponsiblePerson, reqVO.getResponsiblePerson())
                .eqIfPresent(ResponsibilityManagementDO::getPosition, reqVO.getPosition())
                .eqIfPresent(ResponsibilityManagementDO::getContactInfo, reqVO.getContactInfo())
                .eqIfPresent(ResponsibilityManagementDO::getResponsibilityScope, reqVO.getResponsibilityScope())
                .betweenIfPresent(ResponsibilityManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ResponsibilityManagementDO::getId));
    }

}