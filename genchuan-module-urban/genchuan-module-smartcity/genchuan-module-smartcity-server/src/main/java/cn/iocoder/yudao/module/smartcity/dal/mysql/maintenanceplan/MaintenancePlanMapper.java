package cn.iocoder.yudao.module.smartcity.dal.mysql.maintenanceplan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplan.MaintenancePlanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo.*;

/**
 * 养护计划 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MaintenancePlanMapper extends BaseMapperX<MaintenancePlanDO> {

    default PageResult<MaintenancePlanDO> selectPage(MaintenancePlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintenancePlanDO>()
                .eqIfPresent(MaintenancePlanDO::getPlanNumber, reqVO.getPlanNumber())
                .likeIfPresent(MaintenancePlanDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(MaintenancePlanDO::getMaintainParcel, reqVO.getMaintainParcel())
                .eqIfPresent(MaintenancePlanDO::getMaintenanceContent, reqVO.getMaintenanceContent())
                .betweenIfPresent(MaintenancePlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintenancePlanDO::getId));
    }

}