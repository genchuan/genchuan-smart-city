package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.depositplan;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 押金方案 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DepositPlanServiceImpl implements DepositPlanService {

    @Resource
    private DepositPlanMapper depositPlanMapper;

    @Override
    public Long createDepositPlan(DepositPlanSaveReqVO createReqVO) {
        // 插入
        DepositPlanDO depositPlan = BeanUtils.toBean(createReqVO, DepositPlanDO.class);
        depositPlanMapper.insert(depositPlan);

        // 返回
        return depositPlan.getId();
    }

    @Override
    public void updateDepositPlan(DepositPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateDepositPlanExists(updateReqVO.getId());
        // 更新
        DepositPlanDO updateObj = BeanUtils.toBean(updateReqVO, DepositPlanDO.class);
        depositPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteDepositPlan(Long id) {
        // 校验存在
        validateDepositPlanExists(id);
        // 删除
        depositPlanMapper.deleteById(id);
    }

    @Override
        public void deleteDepositPlanListByIds(List<Long> ids) {
        // 删除
        depositPlanMapper.deleteByIds(ids);
        }


    private void validateDepositPlanExists(Long id) {
        if (depositPlanMapper.selectById(id) == null) {
            throw exception(DEPOSIT_PLAN_NOT_EXISTS);
        }
    }

    @Override
    public DepositPlanDO getDepositPlan(Long id) {
        return depositPlanMapper.selectById(id);
    }

    @Override
    public PageResult<DepositPlanDO> getDepositPlanPage(DepositPlanPageReqVO pageReqVO) {
        return depositPlanMapper.selectPage(pageReqVO);
    }

}
