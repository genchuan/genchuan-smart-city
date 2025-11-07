package cn.iocoder.yudao.module.datacenter.service.device.operation.devicecatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo.DeviceCatMngSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicecatmng.DeviceCatMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备分类管理 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceCatMngService {

    /**
     * 创建设备分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceCatMng(@Valid DeviceCatMngSaveReqVO createReqVO);

    /**
     * 更新设备分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceCatMng(@Valid DeviceCatMngSaveReqVO updateReqVO);

    /**
     * 删除设备分类管理
     *
     * @param id 编号
     */
    void deleteDeviceCatMng(Long id);

    /**
     * 获得设备分类管理
     *
     * @param id 编号
     * @return 设备分类管理
     */
    DeviceCatMngDO getDeviceCatMng(Long id);

    /**
     * 获得设备分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分类管理分页
     */
    PageResult<DeviceCatMngDO> getDeviceCatMngPage(DeviceCatMngPageReqVO pageReqVO);

}