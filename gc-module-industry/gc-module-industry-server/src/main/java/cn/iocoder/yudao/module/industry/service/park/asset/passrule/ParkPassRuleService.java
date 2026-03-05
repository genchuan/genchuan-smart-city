package cn.iocoder.yudao.module.industry.service.park.asset.passrule;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.passrule.ParkPassRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 通行规则 Service 接口
 *
 * @author 亘川智城
 */
public interface ParkPassRuleService {

    /**
     * 创建通行规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPassRule(@Valid ParkPassRuleSaveReqVO createReqVO);

    /**
     * 更新通行规则
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPassRule(@Valid ParkPassRuleSaveReqVO updateReqVO);

    /**
     * 删除通行规则
     *
     * @param id 编号
     */
    void deleteParkPassRule(Long id);

    /**
     * 获得通行规则
     *
     * @param id 编号
     * @return 通行规则
     */
    ParkPassRuleDO getParkPassRule(Long id);

    /**
     * 获得通行规则分页
     *
     * @param pageReqVO 分页查询
     * @return 通行规则分页
     */
    PageResult<ParkPassRuleDO> getParkPassRulePage(ParkPassRulePageReqVO pageReqVO);

}