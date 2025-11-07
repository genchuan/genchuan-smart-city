package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdeviceinfo.EmerDeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援设备信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerDeviceInfoService {

    /**
     * 创建救援设备信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerDeviceInfo(@Valid EmerDeviceInfoSaveReqVO createReqVO);

    /**
     * 更新救援设备信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerDeviceInfo(@Valid EmerDeviceInfoSaveReqVO updateReqVO);

    /**
     * 删除救援设备信息
     *
     * @param id 编号
     */
    void deleteEmerDeviceInfo(Long id);

    /**
     * 获得救援设备信息
     *
     * @param id 编号
     * @return 救援设备信息
     */
    EmerDeviceInfoDO getEmerDeviceInfo(Long id);

    /**
     * 获得救援设备信息分页
     *
     * @param pageReqVO 分页查询
     * @return 救援设备信息分页
     */
    PageResult<EmerDeviceInfoDO> getEmerDeviceInfoPage(EmerDeviceInfoPageReqVO pageReqVO);

}