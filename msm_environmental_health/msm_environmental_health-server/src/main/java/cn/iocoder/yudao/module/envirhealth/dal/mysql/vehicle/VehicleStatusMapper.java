package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆状态字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VehicleStatusMapper extends BaseMapperX<VehicleStatusDO> {

    default PageResult<VehicleStatusDO> selectPage(VehicleStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleStatusDO>()
                .eqIfPresent(VehicleStatusDO::getSysVehicleStatusId, reqVO.getSysVehicleStatusId())
                .likeIfPresent(VehicleStatusDO::getName, reqVO.getName())
                .eqIfPresent(VehicleStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(VehicleStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(VehicleStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(VehicleStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(VehicleStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(VehicleStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(VehicleStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(VehicleStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleStatusDO::getId));
    }

}