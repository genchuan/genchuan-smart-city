package cn.iocoder.yudao.module.industry.service.park.through.carparking;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carparking.ParkCarParkingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 在停车辆 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkCarParkingService {

    /**
     * 创建在停车辆
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkCarParking(@Valid ParkCarParkingSaveReqVO createReqVO);

    /**
     * 更新在停车辆
     *
     * @param updateReqVO 更新信息
     */
    void updateParkCarParking(@Valid ParkCarParkingSaveReqVO updateReqVO);

    /**
     * 删除在停车辆
     *
     * @param id 编号
     */
    void deleteParkCarParking(Long id);

    /**
     * 获得在停车辆
     *
     * @param id 编号
     * @return 在停车辆
     */
    ParkCarParkingDO getParkCarParking(Long id);

    /**
     * 获得在停车辆分页
     *
     * @param pageReqVO 分页查询
     * @return 在停车辆分页
     */
    PageResult<ParkCarParkingDO> getParkCarParkingPage(ParkCarParkingPageReqVO pageReqVO);

}