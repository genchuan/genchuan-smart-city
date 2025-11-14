package cn.iocoder.yudao.module.datacenter.service.device.data.devicedataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicedataqualck.DeviceDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备数据质量检查 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceDataQualCkService {

    /**
     * 创建设备数据质量检查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceDataQualCk(@Valid DeviceDataQualCkSaveReqVO createReqVO);

    /**
     * 更新设备数据质量检查
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceDataQualCk(@Valid DeviceDataQualCkSaveReqVO updateReqVO);

    /**
     * 删除设备数据质量检查
     *
     * @param id 编号
     */
    void deleteDeviceDataQualCk(Long id);

    /**
     * 获得设备数据质量检查
     *
     * @param id 编号
     * @return 设备数据质量检查
     */
    DeviceDataQualCkDO getDeviceDataQualCk(Long id);

    /**
     * 获得设备数据质量检查分页
     *
     * @param pageReqVO 分页查询
     * @return 设备数据质量检查分页
     */
    PageResult<DeviceDataQualCkDO> getDeviceDataQualCkPage(DeviceDataQualCkPageReqVO pageReqVO);

}