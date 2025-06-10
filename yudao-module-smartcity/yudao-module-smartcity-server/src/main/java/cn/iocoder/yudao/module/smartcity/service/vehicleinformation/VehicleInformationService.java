package cn.iocoder.yudao.module.smartcity.service.vehicleinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.VehicleInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.VehicleInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleinformation.VehicleInformationDO;
import jakarta.validation.Valid;

/**
 * 车辆信息 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface VehicleInformationService {

    /**
     * 创建车辆信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVehicleInformation(@Valid VehicleInformationSaveReqVO createReqVO);

    /**
     * 更新车辆信息
     *
     * @param updateReqVO 更新信息
     */
    void updateVehicleInformation(@Valid VehicleInformationSaveReqVO updateReqVO);

    /**
     * 删除车辆信息
     *
     * @param id 编号
     */
    void deleteVehicleInformation(Long id);

    /**
     * 获得车辆信息
     *
     * @param id 编号
     * @return 车辆信息
     */
    VehicleInformationDO getVehicleInformation(Long id);

    /**
     * 获得车辆信息分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆信息分页
     */
    PageResult<VehicleInformationDO> getVehicleInformationPage(VehicleInformationPageReqVO pageReqVO);

}