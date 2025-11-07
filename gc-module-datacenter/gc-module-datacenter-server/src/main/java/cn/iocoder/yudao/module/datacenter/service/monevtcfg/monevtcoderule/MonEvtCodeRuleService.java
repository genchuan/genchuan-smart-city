package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcoderule.MonEvtCodeRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件标识码规则 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtCodeRuleService {

    /**
     * 创建监测事件标识码规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtCodeRule(@Valid MonEvtCodeRuleSaveReqVO createReqVO);

    /**
     * 更新监测事件标识码规则
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtCodeRule(@Valid MonEvtCodeRuleSaveReqVO updateReqVO);

    /**
     * 删除监测事件标识码规则
     *
     * @param id 编号
     */
    void deleteMonEvtCodeRule(Long id);

    /**
     * 获得监测事件标识码规则
     *
     * @param id 编号
     * @return 监测事件标识码规则
     */
    MonEvtCodeRuleDO getMonEvtCodeRule(Long id);

    /**
     * 获得监测事件标识码规则分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件标识码规则分页
     */
    PageResult<MonEvtCodeRuleDO> getMonEvtCodeRulePage(MonEvtCodeRulePageReqVO pageReqVO);

}