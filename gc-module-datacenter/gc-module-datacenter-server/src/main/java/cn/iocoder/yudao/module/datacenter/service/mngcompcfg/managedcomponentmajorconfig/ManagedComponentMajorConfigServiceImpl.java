package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.managedcomponentmajorconfig;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentmajorconfig.vo.ManagedComponentMajorConfigSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.managedcomponentmajorconfig.ManagedComponentMajorConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.managedcomponentmajorconfig.ManagedComponentMajorConfigMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件大类配置表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManagedComponentMajorConfigServiceImpl implements ManagedComponentMajorConfigService {

    @Resource
    private ManagedComponentMajorConfigMapper managedComponentMajorConfigMapper;

    @Override
    public Long createManagedComponentMajorConfig(ManagedComponentMajorConfigSaveReqVO createReqVO) {
        // 插入
        ManagedComponentMajorConfigDO managedComponentMajorConfig = BeanUtils.toBean(createReqVO, ManagedComponentMajorConfigDO.class);
        managedComponentMajorConfigMapper.insert(managedComponentMajorConfig);
        // 返回
        return managedComponentMajorConfig.getId();
    }

    @Override
    public void updateManagedComponentMajorConfig(ManagedComponentMajorConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateManagedComponentMajorConfigExists(updateReqVO.getId());
        // 更新
        ManagedComponentMajorConfigDO updateObj = BeanUtils.toBean(updateReqVO, ManagedComponentMajorConfigDO.class);
        managedComponentMajorConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteManagedComponentMajorConfig(Long id) {
        // 校验存在
        validateManagedComponentMajorConfigExists(id);
        // 删除
        managedComponentMajorConfigMapper.deleteById(id);
    }

    private void validateManagedComponentMajorConfigExists(Long id) {
        if (managedComponentMajorConfigMapper.selectById(id) == null) {
            throw exception(MANAGED_COMPONENT_MAJOR_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public ManagedComponentMajorConfigDO getManagedComponentMajorConfig(Long id) {
        return managedComponentMajorConfigMapper.selectById(id);
    }

    @Override
    public PageResult<ManagedComponentMajorConfigDO> getManagedComponentMajorConfigPage(ManagedComponentMajorConfigPageReqVO pageReqVO) {
        return managedComponentMajorConfigMapper.selectPage(pageReqVO);
    }

}