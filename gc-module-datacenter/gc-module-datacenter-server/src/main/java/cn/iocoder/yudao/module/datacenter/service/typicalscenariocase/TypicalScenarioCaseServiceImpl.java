package cn.iocoder.yudao.module.datacenter.service.typicalscenariocase;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.typicalscenariocase.TypicalScenarioCaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.typicalscenariocase.TypicalScenarioCaseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 典型场景案例库 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TypicalScenarioCaseServiceImpl implements TypicalScenarioCaseService {

    @Resource
    private TypicalScenarioCaseMapper typicalScenarioCaseMapper;

    @Override
    public Long createTypicalScenarioCase(TypicalScenarioCaseSaveReqVO createReqVO) {
        // 插入
        TypicalScenarioCaseDO typicalScenarioCase = BeanUtils.toBean(createReqVO, TypicalScenarioCaseDO.class);
        typicalScenarioCaseMapper.insert(typicalScenarioCase);
        // 返回
        return typicalScenarioCase.getId();
    }

    @Override
    public void updateTypicalScenarioCase(TypicalScenarioCaseSaveReqVO updateReqVO) {
        // 校验存在
        validateTypicalScenarioCaseExists(updateReqVO.getId());
        // 更新
        TypicalScenarioCaseDO updateObj = BeanUtils.toBean(updateReqVO, TypicalScenarioCaseDO.class);
        typicalScenarioCaseMapper.updateById(updateObj);
    }

    @Override
    public void deleteTypicalScenarioCase(Long id) {
        // 校验存在
        validateTypicalScenarioCaseExists(id);
        // 删除
        typicalScenarioCaseMapper.deleteById(id);
    }

    private void validateTypicalScenarioCaseExists(Long id) {
        if (typicalScenarioCaseMapper.selectById(id) == null) {
            throw exception(TYPICAL_SCENARIO_CASE_NOT_EXISTS);
        }
    }

    @Override
    public TypicalScenarioCaseDO getTypicalScenarioCase(Long id) {
        return typicalScenarioCaseMapper.selectById(id);
    }

    @Override
    public PageResult<TypicalScenarioCaseDO> getTypicalScenarioCasePage(TypicalScenarioCasePageReqVO pageReqVO) {
        return typicalScenarioCaseMapper.selectPage(pageReqVO);
    }

}