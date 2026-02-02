package cn.iocoder.yudao.module.park.service.park.resource.inputcar;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarEntryReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarExitReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarSaveReqVO;
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
}