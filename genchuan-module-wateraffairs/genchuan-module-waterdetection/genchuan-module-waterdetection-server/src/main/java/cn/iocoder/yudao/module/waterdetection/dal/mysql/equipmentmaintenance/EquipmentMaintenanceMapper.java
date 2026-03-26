package cn.iocoder.yudao.module.waterdetection.dal.mysql.equipmentmaintenance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentmaintenance.EquipmentMaintenanceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentmaintenance.vo.*;

/**
 * 设备保养计划管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EquipmentMaintenanceMapper extends BaseMapperX<EquipmentMaintenanceDO> {

    default PageResult<EquipmentMaintenanceDO> selectPage(EquipmentMaintenancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EquipmentMaintenanceDO>()
                .eqIfPresent(EquipmentMaintenanceDO::getEquipmentId, reqVO.getEquipmentId())
                .eqIfPresent(EquipmentMaintenanceDO::getEquipmentType, reqVO.getEquipmentType())
                .eqIfPresent(EquipmentMaintenanceDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .betweenIfPresent(EquipmentMaintenanceDO::getPlanMaintenanceDate, reqVO.getPlanMaintenanceDate())
                .betweenIfPresent(EquipmentMaintenanceDO::getActualMaintenanceDate, reqVO.getActualMaintenanceDate())
                .eqIfPresent(EquipmentMaintenanceDO::getMaintenanceContent, reqVO.getMaintenanceContent())
                .eqIfPresent(EquipmentMaintenanceDO::getReplacedParts, reqVO.getReplacedParts())
                .eqIfPresent(EquipmentMaintenanceDO::getPostMaintenanceParams, reqVO.getPostMaintenanceParams())
                .eqIfPresent(EquipmentMaintenanceDO::getMaintenanceStaffId, reqVO.getMaintenanceStaffId())
                .betweenIfPresent(EquipmentMaintenanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EquipmentMaintenanceDO::getId));
    }

}