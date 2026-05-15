package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.vehicleaccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.*;

import java.util.List;

/**
 * 车辆通行 Service 接口
 *
 * @author 亘川智城
 */
public interface VehicleAccessService {

    /**
     * 获得车辆通行分页
     */
    PageResult<VehicleAccessRespVO> getVehicleAccessPage(VehicleAccessPageReqVO pageReqVO);

    /**
     * 获得车辆通行
     */
    VehicleAccessRespVO getVehicleAccess(Long id);

    /**
     * 车牌识别
     */
    VehicleAccessRecognizeRespVO recognizeVehicleAccess(VehicleAccessRecognizeReqVO reqVO);

    /**
     * 车辆放行
     */
    Boolean passVehicleAccess(VehicleAccessPassReqVO reqVO);

    /**
     * 车辆拦截
     */
    Boolean blockVehicleAccess(VehicleAccessBlockReqVO reqVO);

    /**
     * 费用计算
     */
    VehicleAccessCalculateRespVO calculateVehicleAccess(VehicleAccessCalculateReqVO reqVO);

    /**
     * 停车缴费
     */
    VehicleAccessPayRespVO payVehicleAccess(VehicleAccessPayReqVO reqVO);

    /**
     * 获得车辆通行列表（导出用）
     */
    List<VehicleAccessRespVO> getVehicleAccessList(VehicleAccessPageReqVO pageReqVO);

    /**
     * 车辆通行态势
     */
    VehicleAccessChartRespVO getVehicleAccessChart(String startTime, String endTime);

}
