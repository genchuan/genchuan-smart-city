package cn.iocoder.yudao.module.evaluate.service.timeaccessrule;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRulePageReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.timeaccessrule.TimeAccessRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 实时接入规则 Service 接口
 *
 * @author 亘川智城
 */
public interface TimeAccessRuleService {

    /**
     * 创建实时接入规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTimeAccessRule(@Valid TimeAccessRuleSaveReqVO createReqVO);

    /**
     * 更新实时接入规则
     *
     * @param updateReqVO 更新信息
     */
    void updateTimeAccessRule(@Valid TimeAccessRuleSaveReqVO updateReqVO);

    /**
     * 删除实时接入规则
     *
     * @param id 编号
     */
    void deleteTimeAccessRule(Long id);

    /**
     * 获得实时接入规则
     *
     * @param id 编号
     * @return 实时接入规则
     */
    TimeAccessRuleDO getTimeAccessRule(Long id);

    /**
     * 获得实时接入规则分页
     *
     * @param pageReqVO 分页查询
     * @return 实时接入规则分页
     */
    PageResult<TimeAccessRuleDO> getTimeAccessRulePage(@Valid TimeAccessRulePageReqVO pageReqVO);

}