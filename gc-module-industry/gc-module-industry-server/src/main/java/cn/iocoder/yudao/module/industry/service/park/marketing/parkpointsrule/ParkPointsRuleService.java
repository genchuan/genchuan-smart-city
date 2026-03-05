package cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrule;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRuleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrule.ParkPointsRuleDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 积分规则 Service 接口
 *
 * @author lxs
 */
public interface ParkPointsRuleService {

    /**
     * 创建积分规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPointsRule(@Valid ParkPointsRuleSaveReqVO createReqVO);

    /**
     * 更新积分规则
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPointsRule(@Valid ParkPointsRuleSaveReqVO updateReqVO);

    /**
     * 删除积分规则
     *
     * @param id 编号
     */
    void deleteParkPointsRule(Long id);

    /**
     * 获得积分规则
     *
     * @param id 编号
     * @return 积分规则
     */
    ParkPointsRuleDO getParkPointsRule(Long id);

    /**
     * 获得积分规则分页
     *
     * @param pageReqVO 分页查询
     * @return 积分规则分页
     */
    PageResult<ParkPointsRuleDO> getParkPointsRulePage(ParkPointsRulePageReqVO pageReqVO);

}
