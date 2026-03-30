package cn.iocoder.yudao.module.smartcity.dal.mysql.vehicleabnormality;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleabnormality.VehicleAbnormalityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.*;

/**
 * 车辆异常 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface VehicleAbnormalityMapper extends BaseMapperX<VehicleAbnormalityDO> {

    default PageResult<VehicleAbnormalityDO> selectPage(VehicleAbnormalityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleAbnormalityDO>()
                .eqIfPresent(VehicleAbnormalityDO::getVehicleNumber, reqVO.getVehicleNumber())
                .betweenIfPresent(VehicleAbnormalityDO::getAbnormalOccurrenceTime, reqVO.getAbnormalOccurrenceTime())
                .eqIfPresent(VehicleAbnormalityDO::getAbnormalLocation, reqVO.getAbnormalLocation())
                .eqIfPresent(VehicleAbnormalityDO::getMaintenanceCosts, reqVO.getMaintenanceCosts())
                .betweenIfPresent(VehicleAbnormalityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleAbnormalityDO::getId));
    }

}