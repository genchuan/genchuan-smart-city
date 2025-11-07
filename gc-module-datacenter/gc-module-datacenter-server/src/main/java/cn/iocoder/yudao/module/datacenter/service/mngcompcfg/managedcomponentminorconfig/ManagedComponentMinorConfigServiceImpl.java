package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentminorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo.ManagedComponentMinorConfigSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.managedcomponentminorconfig.ManagedComponentMinorConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件小类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManagedComponentMinorConfigServiceImpl implements ManagedComponentMinorConfigService {

    @Resource
    private ManagedComponentMinorConfigMapper managedComponentMinorConfigMapper;

    @Override
    public Long createManagedComponentMinorConfig(ManagedComponentMinorConfigSaveReqVO createReqVO) {
        // 插入
        ManagedComponentMinorConfigDO managedComponentMinorConfig = BeanUtils.toBean(createReqVO, ManagedComponentMinorConfigDO.class);
        managedComponentMinorConfigMapper.insert(managedComponentMinorConfig);
        // 返回
        return managedComponentMinorConfig.getId();
    }

    @Override
    public void updateManagedComponentMinorConfig(ManagedComponentMinorConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateManagedComponentMinorConfigExists(updateReqVO.getId());
        // 更新
        ManagedComponentMinorConfigDO updateObj = BeanUtils.toBean(updateReqVO, ManagedComponentMinorConfigDO.class);
        managedComponentMinorConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteManagedComponentMinorConfig(Long id) {
        // 校验存在
        validateManagedComponentMinorConfigExists(id);
        // 删除
        managedComponentMinorConfigMapper.deleteById(id);
    }

    private void validateManagedComponentMinorConfigExists(Long id) {
        if (managedComponentMinorConfigMapper.selectById(id) == null) {
            throw exception(MANAGED_COMPONENT_MINOR_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public ManagedComponentMinorConfigDO getManagedComponentMinorConfig(Long id) {
        return managedComponentMinorConfigMapper.selectById(id);
    }

    @Override
    public PageResult<ManagedComponentMinorConfigDO> getManagedComponentMinorConfigPage(ManagedComponentMinorConfigPageReqVO pageReqVO) {
        return managedComponentMinorConfigMapper.selectPage(pageReqVO);
    }

}