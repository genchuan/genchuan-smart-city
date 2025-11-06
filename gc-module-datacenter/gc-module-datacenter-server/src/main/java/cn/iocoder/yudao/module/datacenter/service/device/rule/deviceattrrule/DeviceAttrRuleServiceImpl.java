package cn.iocoder.yudao.module.datacenter.service.device.rule.deviceattrrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.deviceattrrule.DeviceAttrRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.deviceattrrule.DeviceAttrRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备属性规则配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceAttrRuleServiceImpl implements DeviceAttrRuleService {

    @Resource
    private DeviceAttrRuleMapper deviceAttrRuleMapper;

    @Override
    public Long createDeviceAttrRule(DeviceAttrRuleSaveReqVO createReqVO) {
        // 插入
        DeviceAttrRuleDO deviceAttrRule = BeanUtils.toBean(createReqVO, DeviceAttrRuleDO.class);
        deviceAttrRuleMapper.insert(deviceAttrRule);
        // 返回
        return deviceAttrRule.getId();
    }

    @Override
    public void updateDeviceAttrRule(DeviceAttrRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceAttrRuleExists(updateReqVO.getId());
        // 更新
        DeviceAttrRuleDO updateObj = BeanUtils.toBean(updateReqVO, DeviceAttrRuleDO.class);
        deviceAttrRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceAttrRule(Long id) {
        // 校验存在
        validateDeviceAttrRuleExists(id);
        // 删除
        deviceAttrRuleMapper.deleteById(id);
    }

    private void validateDeviceAttrRuleExists(Long id) {
        if (deviceAttrRuleMapper.selectById(id) == null) {
            throw exception(DEVICE_ATTR_RULE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceAttrRuleDO getDeviceAttrRule(Long id) {
        return deviceAttrRuleMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceAttrRuleDO> getDeviceAttrRulePage(DeviceAttrRulePageReqVO pageReqVO) {
        return deviceAttrRuleMapper.selectPage(pageReqVO);
    }

}