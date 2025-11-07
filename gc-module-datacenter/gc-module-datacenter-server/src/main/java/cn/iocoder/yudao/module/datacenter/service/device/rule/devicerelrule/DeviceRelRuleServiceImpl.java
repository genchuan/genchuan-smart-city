package cn.iocoder.yudao.module.datacenter.service.device.rule.devicerelrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicerelrule.DeviceRelRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.devicerelrule.DeviceRelRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联规则配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceRelRuleServiceImpl implements DeviceRelRuleService {

    @Resource
    private DeviceRelRuleMapper deviceRelRuleMapper;

    @Override
    public Long createDeviceRelRule(DeviceRelRuleSaveReqVO createReqVO) {
        // 插入
        DeviceRelRuleDO deviceRelRule = BeanUtils.toBean(createReqVO, DeviceRelRuleDO.class);
        deviceRelRuleMapper.insert(deviceRelRule);
        // 返回
        return deviceRelRule.getId();
    }

    @Override
    public void updateDeviceRelRule(DeviceRelRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceRelRuleExists(updateReqVO.getId());
        // 更新
        DeviceRelRuleDO updateObj = BeanUtils.toBean(updateReqVO, DeviceRelRuleDO.class);
        deviceRelRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceRelRule(Long id) {
        // 校验存在
        validateDeviceRelRuleExists(id);
        // 删除
        deviceRelRuleMapper.deleteById(id);
    }

    private void validateDeviceRelRuleExists(Long id) {
        if (deviceRelRuleMapper.selectById(id) == null) {
            throw exception(DEVICE_REL_RULE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceRelRuleDO getDeviceRelRule(Long id) {
        return deviceRelRuleMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceRelRuleDO> getDeviceRelRulePage(DeviceRelRulePageReqVO pageReqVO) {
        return deviceRelRuleMapper.selectPage(pageReqVO);
    }

}