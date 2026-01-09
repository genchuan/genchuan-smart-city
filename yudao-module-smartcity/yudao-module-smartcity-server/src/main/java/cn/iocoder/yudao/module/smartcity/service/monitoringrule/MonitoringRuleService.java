package cn.iocoder.yudao.module.smartcity.service.monitoringrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo.MonitoringRulePageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo.MonitoringRuleSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringrule.MonitoringRuleDO;
import jakarta.validation.Valid;

/**
 * 监测项规则设置 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface MonitoringRuleService {

    /**
     * 创建监测项规则设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitoringRule(@Valid MonitoringRuleSaveReqVO createReqVO);

    /**
     * 更新监测项规则设置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitoringRule(@Valid MonitoringRuleSaveReqVO updateReqVO);

    /**
     * 删除监测项规则设置
     *
     * @param id 编号
     */
    void deleteMonitoringRule(Long id);

    /**
     * 获得监测项规则设置
     *
     * @param id 编号
     * @return 监测项规则设置
     */
    MonitoringRuleDO getMonitoringRule(Long id);

    /**
     * 获得监测项规则设置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测项规则设置分页
     */
    PageResult<MonitoringRuleDO> getMonitoringRulePage(MonitoringRulePageReqVO pageReqVO);

}