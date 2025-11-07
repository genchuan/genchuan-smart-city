package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdevicecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdevicecat.EmerDeviceCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 救援设备分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerDeviceCatService {

    /**
     * 创建救援设备分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerDeviceCat(@Valid EmerDeviceCatSaveReqVO createReqVO);

    /**
     * 更新救援设备分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerDeviceCat(@Valid EmerDeviceCatSaveReqVO updateReqVO);

    /**
     * 删除救援设备分类配置
     *
     * @param id 编号
     */
    void deleteEmerDeviceCat(Long id);

    /**
     * 获得救援设备分类配置
     *
     * @param id 编号
     * @return 救援设备分类配置
     */
    EmerDeviceCatDO getEmerDeviceCat(Long id);

    /**
     * 获得救援设备分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 救援设备分类配置分页
     */
    PageResult<EmerDeviceCatDO> getEmerDeviceCatPage(EmerDeviceCatPageReqVO pageReqVO);

}