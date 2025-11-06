package cn.iocoder.yudao.module.datacenter.service.device.rule.devicecatrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo.DeviceCatRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.rule.devicecatrule.DeviceCatRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 设备分类规则配置 Service 接口
 *
 * @author zhucongquan
 */
public interface DeviceCatRuleService {

    /**
     * 创建设备分类规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceCatRule(@Valid DeviceCatRuleSaveReqVO createReqVO);

    /**
     * 更新设备分类规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceCatRule(@Valid DeviceCatRuleSaveReqVO updateReqVO);

    /**
     * 删除设备分类规则配置
     *
     * @param id 编号
     */
    void deleteDeviceCatRule(Long id);

    /**
     * 获得设备分类规则配置
     *
     * @param id 编号
     * @return 设备分类规则配置
     */
    DeviceCatRuleDO getDeviceCatRule(Long id);

    /**
     * 获得设备分类规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分类规则配置分页
     */
    PageResult<DeviceCatRuleDO> getDeviceCatRulePage(DeviceCatRulePageReqVO pageReqVO);

}