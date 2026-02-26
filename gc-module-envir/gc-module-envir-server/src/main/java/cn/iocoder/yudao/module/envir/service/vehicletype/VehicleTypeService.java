package cn.iocoder.yudao.module.envir.service.vehicletype;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.vehicletype.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicletype.VehicleTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车辆类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface VehicleTypeService {

    /**
     * 创建车辆类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVehicleType(@Valid VehicleTypeSaveReqVO createReqVO);

    /**
     * 更新车辆类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateVehicleType(@Valid VehicleTypeSaveReqVO updateReqVO);

    /**
     * 删除车辆类型字典
     *
     * @param id 编号
     */
    void deleteVehicleType(Long id);

    /**
     * 获得车辆类型字典
     *
     * @param id 编号
     * @return 车辆类型字典
     */
    VehicleTypeDO getVehicleType(Long id);

    /**
     * 获得车辆类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆类型字典分页
     */
    PageResult<VehicleTypeDO> getVehicleTypePage(VehicleTypePageReqVO pageReqVO);

}