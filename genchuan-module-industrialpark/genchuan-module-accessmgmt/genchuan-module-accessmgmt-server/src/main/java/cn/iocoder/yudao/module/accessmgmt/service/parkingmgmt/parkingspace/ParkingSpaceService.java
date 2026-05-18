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
     * 创建车位 —— 校验车位编号唯一性，初始状态"空闲"
     */
    Boolean createParkingSpace(ParkingSpaceCreateReqVO createReqVO);

    /**
     * 分配车位 —— 状态变更为"占用"，写入预约用户
     */
    Boolean allocateParkingSpace(ParkingSpaceAllocateReqVO reqVO);

    /**
     * 预约车位 —— 状态变更为"预约中"，写入预约用户
     */
    Boolean reserveParkingSpace(ParkingSpaceReserveReqVO reqVO);

    /**
     * 释放车位 —— 状态变更为"空闲"，清空预约用户和使用时长
     */
    Boolean releaseParkingSpace(ParkingSpaceReleaseReqVO reqVO);

    /**
     * 禁用车位 —— 状态变更为"占用"，写入停用原因
     */
    Boolean disableParkingSpace(ParkingSpaceDisableReqVO reqVO);

    /**
     * 取消车位 —— 状态变更为"空闲"，清空预约用户
     */
    Boolean cancelParkingSpace(ParkingSpaceCancelReqVO reqVO);

    /**
     * 确认车位 —— 状态变更为"占用"
     */
    Boolean confirmParkingSpace(ParkingSpaceConfirmReqVO reqVO);

    /**
     * 获得车位信息列表（导出用，全量不分页）
     */
    List<ParkingSpaceRespVO> getParkingSpaceList(ParkingSpacePageReqVO pageReqVO);

    /**
     * 车位管理态势 —— 总数统计 + 地图分布 + 使用率趋势 + 类型占比
     */
    ParkingSpaceChartRespVO getParkingSpaceChart(String parkName);

}
