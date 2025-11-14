package cn.iocoder.yudao.module.datacenter.service.device.report.devicecatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.devicecatrpt.DeviceCatRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备分类统计 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceCatRptService {

    /**
     * 创建设备分类统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceCatRpt(@Valid DeviceCatRptSaveReqVO createReqVO);

    /**
     * 更新设备分类统计
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceCatRpt(@Valid DeviceCatRptSaveReqVO updateReqVO);

    /**
     * 删除设备分类统计
     *
     * @param id 编号
     */
    void deleteDeviceCatRpt(Long id);

    /**
     * 获得设备分类统计
     *
     * @param id 编号
     * @return 设备分类统计
     */
    DeviceCatRptDO getDeviceCatRpt(Long id);

    /**
     * 获得设备分类统计分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分类统计分页
     */
    PageResult<DeviceCatRptDO> getDeviceCatRptPage(DeviceCatRptPageReqVO pageReqVO);

}