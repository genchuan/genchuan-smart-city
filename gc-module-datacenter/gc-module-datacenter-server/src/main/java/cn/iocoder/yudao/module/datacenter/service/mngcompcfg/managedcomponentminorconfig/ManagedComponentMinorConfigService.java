package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentminorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件小类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface ManagedComponentMinorConfigService {

    /**
     * 创建管理部件小类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManagedComponentMinorConfig(@Valid ManagedComponentMinorConfigSaveReqVO createReqVO);

    /**
     * 更新管理部件小类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateManagedComponentMinorConfig(@Valid ManagedComponentMinorConfigSaveReqVO updateReqVO);

    /**
     * 删除管理部件小类配置
     *
     * @param id 编号
     */
    void deleteManagedComponentMinorConfig(Long id);

    /**
     * 获得管理部件小类配置
     *
     * @param id 编号
     * @return 管理部件小类配置
     */
    ManagedComponentMinorConfigDO getManagedComponentMinorConfig(Long id);

    /**
     * 获得管理部件小类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件小类配置分页
     */
    PageResult<ManagedComponentMinorConfigDO> getManagedComponentMinorConfigPage(ManagedComponentMinorConfigPageReqVO pageReqVO);

}