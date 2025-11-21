package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectdeviceinfo.InspectDeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检设备信息 Service 接口
 *
 * @author zcq
 */
public interface InspectDeviceInfoService {

    /**
     * 创建巡查巡检设备信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectDeviceInfo(@Valid InspectDeviceInfoSaveReqVO createReqVO);

    /**
     * 更新巡查巡检设备信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectDeviceInfo(@Valid InspectDeviceInfoSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检设备信息
     *
     * @param id 编号
     */
    void deleteInspectDeviceInfo(Long id);

    /**
     * 获得巡查巡检设备信息
     *
     * @param id 编号
     * @return 巡查巡检设备信息
     */
    InspectDeviceInfoDO getInspectDeviceInfo(Long id);

    /**
     * 获得巡查巡检设备信息分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检设备信息分页
     */
    PageResult<InspectDeviceInfoDO> getInspectDeviceInfoPage(InspectDeviceInfoPageReqVO pageReqVO);

}