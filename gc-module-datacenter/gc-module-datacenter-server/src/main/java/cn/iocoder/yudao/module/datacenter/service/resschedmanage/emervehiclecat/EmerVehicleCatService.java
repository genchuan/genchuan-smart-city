package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehiclecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehiclecat.EmerVehicleCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援车辆分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerVehicleCatService {

    /**
     * 创建救援车辆分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerVehicleCat(@Valid EmerVehicleCatSaveReqVO createReqVO);

    /**
     * 更新救援车辆分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerVehicleCat(@Valid EmerVehicleCatSaveReqVO updateReqVO);

    /**
     * 删除救援车辆分类配置
     *
     * @param id 编号
     */
    void deleteEmerVehicleCat(Long id);

    /**
     * 获得救援车辆分类配置
     *
     * @param id 编号
     * @return 救援车辆分类配置
     */
    EmerVehicleCatDO getEmerVehicleCat(Long id);

    /**
     * 获得救援车辆分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 救援车辆分类配置分页
     */
    PageResult<EmerVehicleCatDO> getEmerVehicleCatPage(EmerVehicleCatPageReqVO pageReqVO);

}