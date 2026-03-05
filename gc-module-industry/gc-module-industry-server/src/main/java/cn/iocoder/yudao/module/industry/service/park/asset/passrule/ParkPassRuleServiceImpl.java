package cn.iocoder.yudao.module.industry.service.park.asset.passrule;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.passrule.ParkPassRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.passrule.ParkPassRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 通行规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkPassRuleServiceImpl implements ParkPassRuleService {

    @Resource
    private ParkPassRuleMapper parkPassRuleMapper;

    @Override
    public Long createParkPassRule(ParkPassRuleSaveReqVO createReqVO) {
        // 插入
        ParkPassRuleDO parkPassRule = BeanUtils.toBean(createReqVO, ParkPassRuleDO.class);
        parkPassRuleMapper.insert(parkPassRule);
        // 返回
        return parkPassRule.getId();
    }

    @Override
    public void updateParkPassRule(ParkPassRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPassRuleExists(updateReqVO.getId());
        // 更新
        ParkPassRuleDO updateObj = BeanUtils.toBean(updateReqVO, ParkPassRuleDO.class);
        parkPassRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPassRule(Long id) {
        // 校验存在
        validateParkPassRuleExists(id);
        // 删除
        parkPassRuleMapper.deleteById(id);
    }

    private void validateParkPassRuleExists(Long id) {
        if (parkPassRuleMapper.selectById(id) == null) {
            throw exception(PARK_PASS_RULE_NOT_EXISTS);
        }
    }

    @Override
    public ParkPassRuleDO getParkPassRule(Long id) {
        return parkPassRuleMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPassRuleDO> getParkPassRulePage(ParkPassRulePageReqVO pageReqVO) {
        return parkPassRuleMapper.selectPage(pageReqVO);
    }

}