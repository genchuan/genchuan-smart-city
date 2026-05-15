package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingspace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo.*;

import java.util.List;

/**
 * 车位信息 Service 接口
 *
 * @author 亘川智城
 */
public interface ParkingSpaceService {

    /**
     * 获得车位信息分页
     */
    PageResult<ParkingSpaceRespVO> getParkingSpacePage(ParkingSpacePageReqVO pageReqVO);

    /**
     * 获得车位信息
     */
    ParkingSpaceRespVO getParkingSpace(Long id);

    /**
     * 创建车位信息
     */
    Boolean createParkingSpace(ParkingSpaceCreateReqVO createReqVO);

    /**
     * 分配车位
     */
    Boolean allocateParkingSpace(ParkingSpaceAllocateReqVO reqVO);

    /**
     * 预约车位
     */
    Boolean reserveParkingSpace(ParkingSpaceReserveReqVO reqVO);

    /**
     * 释放车位
     */
    Boolean releaseParkingSpace(ParkingSpaceReleaseReqVO reqVO);

    /**
     * 禁用车位
     */
    Boolean disableParkingSpace(ParkingSpaceDisableReqVO reqVO);

    /**
     * 取消车位
     */
    Boolean cancelParkingSpace(ParkingSpaceCancelReqVO reqVO);

    /**
     * 确认车位
     */
    Boolean confirmParkingSpace(ParkingSpaceConfirmReqVO reqVO);

    /**
     * 获得车位信息列表（导出用）
     */
    List<ParkingSpaceRespVO> getParkingSpaceList(ParkingSpacePageReqVO pageReqVO);

    /**
     * 车位管理态势
     */
    ParkingSpaceChartRespVO getParkingSpaceChart(String parkName);

}
