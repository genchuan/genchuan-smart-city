package cn.iocoder.yudao.module.industry.service.park.marketing.parkpointsrule;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRuleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrule.ParkPointsRuleDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpointsrule.ParkPointsRuleMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 积分规则 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPointsRuleServiceImpl implements ParkPointsRuleService {

    @Resource
    private ParkPointsRuleMapper parkPointsRuleMapper;

    @Override
    public Long createParkPointsRule(ParkPointsRuleSaveReqVO createReqVO) {
        // 插入
        ParkPointsRuleDO parkPointsRule = BeanUtils.toBean(createReqVO, ParkPointsRuleDO.class);
        parkPointsRuleMapper.insert(parkPointsRule);
        // 返回
        return parkPointsRule.getId();
    }

    @Override
    public void updateParkPointsRule(ParkPointsRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPointsRuleExists(updateReqVO.getId());
        // 更新
        ParkPointsRuleDO updateObj = BeanUtils.toBean(updateReqVO, ParkPointsRuleDO.class);
        parkPointsRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPointsRule(Long id) {
        // 校验存在
        validateParkPointsRuleExists(id);
        // 删除
        parkPointsRuleMapper.deleteById(id);
    }

    private void validateParkPointsRuleExists(Long id) {
        if (parkPointsRuleMapper.selectById(id) == null) {
            throw exception(PARK_POINTS_RULE_NOT_EXISTS);
        }
    }

    @Override
    public ParkPointsRuleDO getParkPointsRule(Long id) {
        return parkPointsRuleMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPointsRuleDO> getParkPointsRulePage(ParkPointsRulePageReqVO pageReqVO) {
        return parkPointsRuleMapper.selectPage(pageReqVO);
    }

}
