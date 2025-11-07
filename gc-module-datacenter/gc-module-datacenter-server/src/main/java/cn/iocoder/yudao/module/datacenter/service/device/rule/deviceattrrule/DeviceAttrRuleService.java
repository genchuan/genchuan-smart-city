package cn.iocoder.yudao.module.datacenter.service.device.rule.deviceattrrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo.DeviceAttrRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.deviceattrrule.DeviceAttrRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备属性规则配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceAttrRuleService {

    /**
     * 创建设备属性规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceAttrRule(@Valid DeviceAttrRuleSaveReqVO createReqVO);

    /**
     * 更新设备属性规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceAttrRule(@Valid DeviceAttrRuleSaveReqVO updateReqVO);

    /**
     * 删除设备属性规则配置
     *
     * @param id 编号
     */
    void deleteDeviceAttrRule(Long id);

    /**
     * 获得设备属性规则配置
     *
     * @param id 编号
     * @return 设备属性规则配置
     */
    DeviceAttrRuleDO getDeviceAttrRule(Long id);

    /**
     * 获得设备属性规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备属性规则配置分页
     */
    PageResult<DeviceAttrRuleDO> getDeviceAttrRulePage(DeviceAttrRulePageReqVO pageReqVO);

}