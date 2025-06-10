package cn.iocoder.yudao.module.smartcity.service.parking;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo.ParkingPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo.ParkingSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parking.ParkingDO;
import jakarta.validation.Valid;

/**
 * 停车管理用户管理与服务 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ParkingService {

    /**
     * 创建停车管理用户管理与服务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParking(@Valid ParkingSaveReqVO createReqVO);

    /**
     * 更新停车管理用户管理与服务
     *
     * @param updateReqVO 更新信息
     */
    void updateParking(@Valid ParkingSaveReqVO updateReqVO);

    /**
     * 删除停车管理用户管理与服务
     *
     * @param id 编号
     */
    void deleteParking(Long id);

    /**
     * 获得停车管理用户管理与服务
     *
     * @param id 编号
     * @return 停车管理用户管理与服务
     */
    ParkingDO getParking(Long id);

    /**
     * 获得停车管理用户管理与服务分页
     *
     * @param pageReqVO 分页查询
     * @return 停车管理用户管理与服务分页
     */
    PageResult<ParkingDO> getParkingPage(ParkingPageReqVO pageReqVO);

}