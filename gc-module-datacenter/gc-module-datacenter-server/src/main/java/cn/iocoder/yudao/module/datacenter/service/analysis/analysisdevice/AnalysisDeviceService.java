package cn.iocoder.yudao.module.datacenter.service.analysis.analysisdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDeviceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisdevice.AnalysisDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按设备分域分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisDeviceService {

    /**
     * 创建按设备分域分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisDevice(@Valid AnalysisDeviceSaveReqVO createReqVO);

    /**
     * 更新按设备分域分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisDevice(@Valid AnalysisDeviceSaveReqVO updateReqVO);

    /**
     * 删除按设备分域分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisDevice(Long id);

    /**
     * 获得按设备分域分析研判统计
     *
     * @param id 编号
     * @return 按设备分域分析研判统计
     */
    AnalysisDeviceDO getAnalysisDevice(Long id);

    /**
     * 获得按设备分域分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按设备分域分析研判统计分页
     */
    PageResult<AnalysisDeviceDO> getAnalysisDevicePage(AnalysisDevicePageReqVO pageReqVO);

}