package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcoderule.MonCompCodeRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件标识码规则 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompCodeRuleService {

    /**
     * 创建监测部件标识码规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompCodeRule(@Valid MonCompCodeRuleSaveReqVO createReqVO);

    /**
     * 更新监测部件标识码规则
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompCodeRule(@Valid MonCompCodeRuleSaveReqVO updateReqVO);

    /**
     * 删除监测部件标识码规则
     *
     * @param id 编号
     */
    void deleteMonCompCodeRule(Long id);

    /**
     * 获得监测部件标识码规则
     *
     * @param id 编号
     * @return 监测部件标识码规则
     */
    MonCompCodeRuleDO getMonCompCodeRule(Long id);

    /**
     * 获得监测部件标识码规则分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件标识码规则分页
     */
    PageResult<MonCompCodeRuleDO> getMonCompCodeRulePage(MonCompCodeRulePageReqVO pageReqVO);

}