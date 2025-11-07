package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehicleinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehicleinfo.EmerVehicleInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援车辆信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerVehicleInfoService {

    /**
     * 创建救援车辆信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerVehicleInfo(@Valid EmerVehicleInfoSaveReqVO createReqVO);

    /**
     * 更新救援车辆信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerVehicleInfo(@Valid EmerVehicleInfoSaveReqVO updateReqVO);

    /**
     * 删除救援车辆信息
     *
     * @param id 编号
     */
    void deleteEmerVehicleInfo(Long id);

    /**
     * 获得救援车辆信息
     *
     * @param id 编号
     * @return 救援车辆信息
     */
    EmerVehicleInfoDO getEmerVehicleInfo(Long id);

    /**
     * 获得救援车辆信息分页
     *
     * @param pageReqVO 分页查询
     * @return 救援车辆信息分页
     */
    PageResult<EmerVehicleInfoDO> getEmerVehicleInfoPage(EmerVehicleInfoPageReqVO pageReqVO);

}