package cn.iocoder.yudao.module.park.service.park.resource.inputcar;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 泊位录入车辆 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkInputCarService {

    /**
     * 创建泊位录入车辆
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInputCar(@Valid ParkInputCarSaveReqVO createReqVO);

    /**
     * 更新泊位录入车辆
     *
     * @param updateReqVO 更新信息
     */
    void updateInputCar(@Valid ParkInputCarSaveReqVO updateReqVO);

    /**
     * 删除泊位录入车辆
     *
     * @param id 编号
     */
    void deleteInputCar(Long id);

    /**
     * 获得泊位录入车辆
     *
     * @param id 编号
     * @return 泊位录入车辆
     */
    ParkInputCarDO getInputCar(Long id);

    /**
     * 获得泊位录入车辆分页
     *
     * @param pageReqVO 分页查询
     * @return 泊位录入车辆分页
     */
    PageResult<ParkInputCarDO> getInputCarPage(ParkInputCarPageReqVO pageReqVO);


    /**
     * 车辆进场
     *
     * @param reqVO 进场信息
     * @return 进场记录ID
     */
    Long createEntry(ParkInputCarEntryReqVO reqVO);

    /**
     * 车辆离场
     *
     * @param reqVO 离场信息
     */
    void updateExit(ParkInputCarExitReqVO reqVO);

    /**
     * 模拟地磁检测车辆进入
     *
     * @param reqVO 请求信息，包含目标泊位号和入场时间
     * @return 记录ID
     */
    Long simulateMagneticDetection(@Valid ParkInputCarMagneticDetectionReqVO reqVO);

    /**
     * 模拟地磁检测车辆离场
     *
     * @param reqVO 请求信息，包含车辆记录ID和离场时间
     * @return 是否成功
     */
    Boolean simulateMagneticDetectionExit(@Valid ParkInputCarMagneticDetectionExitReqVO reqVO);
}