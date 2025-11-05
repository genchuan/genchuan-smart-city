package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentmajorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentmajorconfig.ManagedComponentMajorConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件大类配置表 Service 接口
 *
 * @author 亘川智城
 */
public interface ManagedComponentMajorConfigService {

    /**
     * 创建管理部件大类配置表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManagedComponentMajorConfig(@Valid ManagedComponentMajorConfigSaveReqVO createReqVO);

    /**
     * 更新管理部件大类配置表
     *
     * @param updateReqVO 更新信息
     */
    void updateManagedComponentMajorConfig(@Valid ManagedComponentMajorConfigSaveReqVO updateReqVO);

    /**
     * 删除管理部件大类配置表
     *
     * @param id 编号
     */
    void deleteManagedComponentMajorConfig(Long id);

    /**
     * 获得管理部件大类配置表
     *
     * @param id 编号
     * @return 管理部件大类配置表
     */
    ManagedComponentMajorConfigDO getManagedComponentMajorConfig(Long id);

    /**
     * 获得管理部件大类配置表分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件大类配置表分页
     */
    PageResult<ManagedComponentMajorConfigDO> getManagedComponentMajorConfigPage(ManagedComponentMajorConfigPageReqVO pageReqVO);

}