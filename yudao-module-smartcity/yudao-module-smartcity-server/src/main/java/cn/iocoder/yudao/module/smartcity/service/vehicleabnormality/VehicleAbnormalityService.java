package cn.iocoder.yudao.module.smartcity.service.vehicleabnormality;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.VehicleAbnormalityPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.VehicleAbnormalitySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleabnormality.VehicleAbnormalityDO;
import jakarta.validation.Valid;

/**
 * 车辆异常 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface VehicleAbnormalityService {

    /**
     * 创建车辆异常
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVehicleAbnormality(@Valid VehicleAbnormalitySaveReqVO createReqVO);

    /**
     * 更新车辆异常
     *
     * @param updateReqVO 更新信息
     */
    void updateVehicleAbnormality(@Valid VehicleAbnormalitySaveReqVO updateReqVO);

    /**
     * 删除车辆异常
     *
     * @param id 编号
     */
    void deleteVehicleAbnormality(Long id);

    /**
     * 获得车辆异常
     *
     * @param id 编号
     * @return 车辆异常
     */
    VehicleAbnormalityDO getVehicleAbnormality(Long id);

    /**
     * 获得车辆异常分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆异常分页
     */
    PageResult<VehicleAbnormalityDO> getVehicleAbnormalityPage(VehicleAbnormalityPageReqVO pageReqVO);

}