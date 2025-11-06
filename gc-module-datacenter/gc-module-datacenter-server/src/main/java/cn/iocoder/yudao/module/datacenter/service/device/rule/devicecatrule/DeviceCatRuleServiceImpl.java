package cn.iocoder.yudao.module.datacenter.service.device.rule.devicecatrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicecatrule.DeviceCatRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.rule.devicecatrule.DeviceCatRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备分类规则配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceCatRuleServiceImpl implements DeviceCatRuleService {

    @Resource
    private DeviceCatRuleMapper deviceCatRuleMapper;

    @Override
    public Long createDeviceCatRule(DeviceCatRuleSaveReqVO createReqVO) {
        // 插入
        DeviceCatRuleDO deviceCatRule = BeanUtils.toBean(createReqVO, DeviceCatRuleDO.class);
        deviceCatRuleMapper.insert(deviceCatRule);
        // 返回
        return deviceCatRule.getId();
    }

    @Override
    public void updateDeviceCatRule(DeviceCatRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceCatRuleExists(updateReqVO.getId());
        // 更新
        DeviceCatRuleDO updateObj = BeanUtils.toBean(updateReqVO, DeviceCatRuleDO.class);
        deviceCatRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceCatRule(Long id) {
        // 校验存在
        validateDeviceCatRuleExists(id);
        // 删除
        deviceCatRuleMapper.deleteById(id);
    }

    private void validateDeviceCatRuleExists(Long id) {
        if (deviceCatRuleMapper.selectById(id) == null) {
            throw exception(DEVICE_CAT_RULE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceCatRuleDO getDeviceCatRule(Long id) {
        return deviceCatRuleMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceCatRuleDO> getDeviceCatRulePage(DeviceCatRulePageReqVO pageReqVO) {
        return deviceCatRuleMapper.selectPage(pageReqVO);
    }

}