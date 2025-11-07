package cn.iocoder.yudao.module.datacenter.service.device.rule.devicerelrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo.DeviceRelRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicerelrule.DeviceRelRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备关联规则配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceRelRuleService {

    /**
     * 创建设备关联规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceRelRule(@Valid DeviceRelRuleSaveReqVO createReqVO);

    /**
     * 更新设备关联规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceRelRule(@Valid DeviceRelRuleSaveReqVO updateReqVO);

    /**
     * 删除设备关联规则配置
     *
     * @param id 编号
     */
    void deleteDeviceRelRule(Long id);

    /**
     * 获得设备关联规则配置
     *
     * @param id 编号
     * @return 设备关联规则配置
     */
    DeviceRelRuleDO getDeviceRelRule(Long id);

    /**
     * 获得设备关联规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备关联规则配置分页
     */
    PageResult<DeviceRelRuleDO> getDeviceRelRulePage(DeviceRelRulePageReqVO pageReqVO);

}