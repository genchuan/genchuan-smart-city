package cn.iocoder.yudao.module.evaluate.service.timeaccessrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.timeaccessrule.TimeAccessRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.timeaccessrule.TimeAccessRuleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.TIME_ACCESS_RULE_NOT_EXISTS;

/**
 * 实时接入规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TimeAccessRuleServiceImpl implements TimeAccessRuleService {

    @Resource
    private TimeAccessRuleMapper timeAccessRuleMapper;

    @Override
    public Long createTimeAccessRule(TimeAccessRuleSaveReqVO createReqVO) {
        // 插入
        TimeAccessRuleDO timeAccessRule = BeanUtils.toBean(createReqVO, TimeAccessRuleDO.class);
        timeAccessRuleMapper.insert(timeAccessRule);
        // 返回
        return timeAccessRule.getId();
    }

    @Override
    public void updateTimeAccessRule(TimeAccessRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateTimeAccessRuleExists(updateReqVO.getId());
        // 更新
        TimeAccessRuleDO updateObj = BeanUtils.toBean(updateReqVO, TimeAccessRuleDO.class);
        timeAccessRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteTimeAccessRule(Long id) {
        // 校验存在
        validateTimeAccessRuleExists(id);
        // 删除
        timeAccessRuleMapper.deleteById(id);
    }

    private void validateTimeAccessRuleExists(Long id) {
        if (timeAccessRuleMapper.selectById(id) == null) {
            throw exception(TIME_ACCESS_RULE_NOT_EXISTS);
        }
    }

    @Override
    public TimeAccessRuleDO getTimeAccessRule(Long id) {
        return timeAccessRuleMapper.selectById(id);
    }

    @Override
    public PageResult<TimeAccessRuleDO> getTimeAccessRulePage(TimeAccessRulePageReqVO pageReqVO) {
        return timeAccessRuleMapper.selectPage(pageReqVO);
    }

}