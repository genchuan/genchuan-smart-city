package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domdevicemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomDeviceMonRptService {

    /**
     * 创建设备分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDeviceMonRpt(@Valid DomDeviceMonRptSaveReqVO createReqVO);

    /**
     * 更新设备分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDeviceMonRpt(@Valid DomDeviceMonRptSaveReqVO updateReqVO);

    /**
     * 删除设备分域监测报表
     *
     * @param id 编号
     */
    void deleteDomDeviceMonRpt(Long id);

    /**
     * 获得设备分域监测报表
     *
     * @param id 编号
     * @return 设备分域监测报表
     */
    DomDeviceMonRptDO getDomDeviceMonRpt(Long id);

    /**
     * 获得设备分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分域监测报表分页
     */
    PageResult<DomDeviceMonRptDO> getDomDeviceMonRptPage(DomDeviceMonRptPageReqVO pageReqVO);

}