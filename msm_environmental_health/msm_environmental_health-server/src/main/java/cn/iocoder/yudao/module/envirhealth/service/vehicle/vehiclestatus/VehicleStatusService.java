package cn.iocoder.yudao.module.envirhealth.service.vehicle.vehiclestatus;

import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehiclestatus.VehicleStatusSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车辆状态字典 Service 接口
 *
 * @author 芋道源码
 */
public interface VehicleStatusService {

    /**
     * 创建车辆状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVehicleStatus(@Valid VehicleStatusSaveReqVO createReqVO);

    /**
     * 更新车辆状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updateVehicleStatus(@Valid VehicleStatusSaveReqVO updateReqVO);

    /**
     * 删除车辆状态字典
     *
     * @param id 编号
     */
    void deleteVehicleStatus(Long id);

    /**
     * 获得车辆状态字典
     *
     * @param id 编号
     * @return 车辆状态字典
     */
    VehicleStatusDO getVehicleStatus(Long id);

    /**
     * 获得车辆状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆状态字典分页
     */
    PageResult<VehicleStatusDO> getVehicleStatusPage(VehicleStatusPageReqVO pageReqVO);

}