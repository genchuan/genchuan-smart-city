package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnriskpred;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnriskpred.EarlyWarnRiskPredDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnriskpred.EarlyWarnRiskPredMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警风险趋势预测 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnRiskPredServiceImpl implements EarlyWarnRiskPredService {

    @Resource
    private EarlyWarnRiskPredMapper earlyWarnRiskPredMapper;

    @Override
    public Long createEarlyWarnRiskPred(EarlyWarnRiskPredSaveReqVO createReqVO) {
        // 插入
        EarlyWarnRiskPredDO earlyWarnRiskPred = BeanUtils.toBean(createReqVO, EarlyWarnRiskPredDO.class);
        earlyWarnRiskPredMapper.insert(earlyWarnRiskPred);
        // 返回
        return earlyWarnRiskPred.getId();
    }

    @Override
    public void updateEarlyWarnRiskPred(EarlyWarnRiskPredSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnRiskPredExists(updateReqVO.getId());
        // 更新
        EarlyWarnRiskPredDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnRiskPredDO.class);
        earlyWarnRiskPredMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnRiskPred(Long id) {
        // 校验存在
        validateEarlyWarnRiskPredExists(id);
        // 删除
        earlyWarnRiskPredMapper.deleteById(id);
    }

    private void validateEarlyWarnRiskPredExists(Long id) {
        if (earlyWarnRiskPredMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_RISK_PRED_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnRiskPredDO getEarlyWarnRiskPred(Long id) {
        return earlyWarnRiskPredMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnRiskPredDO> getEarlyWarnRiskPredPage(EarlyWarnRiskPredPageReqVO pageReqVO) {
        return earlyWarnRiskPredMapper.selectPage(pageReqVO);
    }

}