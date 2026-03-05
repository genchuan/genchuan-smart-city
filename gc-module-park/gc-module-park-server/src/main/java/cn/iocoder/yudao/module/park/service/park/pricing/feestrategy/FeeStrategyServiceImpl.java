package cn.iocoder.yudao.module.park.service.park.pricing.feestrategy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo.FeeStrategySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feestrategy.FeeStrategyDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.feestrategy.FeeStrategyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.FEE_STRATEGY_NOT_EXISTS;

/**
 * 费率策略 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FeeStrategyServiceImpl implements FeeStrategyService {

    @Resource
    private FeeStrategyMapper feeStrategyMapper;

    @Override
    public Long createFeeStrategy(FeeStrategySaveReqVO createReqVO) {
        // 插入
        FeeStrategyDO feeStrategy = BeanUtils.toBean(createReqVO, FeeStrategyDO.class);
        feeStrategyMapper.insert(feeStrategy);
        // 返回
        return feeStrategy.getId();
    }

    @Override
    public void updateFeeStrategy(FeeStrategySaveReqVO updateReqVO) {
        // 校验存在
        validateFeeStrategyExists(updateReqVO.getId());
        // 更新
        FeeStrategyDO updateObj = BeanUtils.toBean(updateReqVO, FeeStrategyDO.class);
        feeStrategyMapper.updateById(updateObj);
    }

    @Override
    public void deleteFeeStrategy(Long id) {
        // 校验存在
        validateFeeStrategyExists(id);
        // 删除
        feeStrategyMapper.deleteById(id);
    }

    private void validateFeeStrategyExists(Long id) {
        if (feeStrategyMapper.selectById(id) == null) {
            throw exception(FEE_STRATEGY_NOT_EXISTS);
        }
    }

    @Override
    public FeeStrategyDO getFeeStrategy(Long id) {
        return feeStrategyMapper.selectById(id);
    }

    @Override
    public PageResult<FeeStrategyDO> getFeeStrategyPage(FeeStrategyPageReqVO pageReqVO) {
        return feeStrategyMapper.selectPage(pageReqVO);
    }

}
