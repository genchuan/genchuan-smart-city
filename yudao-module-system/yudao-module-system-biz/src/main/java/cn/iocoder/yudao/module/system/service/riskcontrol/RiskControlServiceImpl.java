package cn.iocoder.yudao.module.system.service.riskcontrol;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.riskcontrol.RiskControlDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.riskcontrol.RiskControlMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 风险管控 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class RiskControlServiceImpl implements RiskControlService {

    @Resource
    private RiskControlMapper riskControlMapper;

    @Override
    public Integer createRiskControl(RiskControlSaveReqVO createReqVO) {
        // 插入
        RiskControlDO riskControl = BeanUtils.toBean(createReqVO, RiskControlDO.class);
        riskControlMapper.insert(riskControl);
        // 返回
        return riskControl.getId();
    }

    @Override
    public void updateRiskControl(RiskControlSaveReqVO updateReqVO) {
        // 校验存在
        validateRiskControlExists(updateReqVO.getId());
        // 更新
        RiskControlDO updateObj = BeanUtils.toBean(updateReqVO, RiskControlDO.class);
        riskControlMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiskControl(Integer id) {
        // 校验存在
        validateRiskControlExists(id);
        // 删除
        riskControlMapper.deleteById(id);
    }

    private void validateRiskControlExists(Integer id) {
        if (riskControlMapper.selectById(id) == null) {
            throw exception(RISK_CONTROL_NOT_EXISTS);
        }
    }

    @Override
    public RiskControlDO getRiskControl(Integer id) {
        return riskControlMapper.selectById(id);
    }

    @Override
    public PageResult<RiskControlDO> getRiskControlPage(RiskControlPageReqVO pageReqVO) {
        return riskControlMapper.selectPage(pageReqVO);
    }

}