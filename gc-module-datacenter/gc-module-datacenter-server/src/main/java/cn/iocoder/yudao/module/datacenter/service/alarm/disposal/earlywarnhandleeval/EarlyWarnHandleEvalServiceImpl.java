package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandleeval;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警处置评估 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EarlyWarnHandleEvalServiceImpl implements EarlyWarnHandleEvalService {

    @Resource
    private EarlyWarnHandleEvalMapper earlyWarnHandleEvalMapper;

    @Override
    public Long createEarlyWarnHandleEval(EarlyWarnHandleEvalSaveReqVO createReqVO) {
        // 插入
        EarlyWarnHandleEvalDO earlyWarnHandleEval = BeanUtils.toBean(createReqVO, EarlyWarnHandleEvalDO.class);
        earlyWarnHandleEvalMapper.insert(earlyWarnHandleEval);
        // 返回
        return earlyWarnHandleEval.getId();
    }

    @Override
    public void updateEarlyWarnHandleEval(EarlyWarnHandleEvalSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnHandleEvalExists(updateReqVO.getId());
        // 更新
        EarlyWarnHandleEvalDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnHandleEvalDO.class);
        earlyWarnHandleEvalMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnHandleEval(Long id) {
        // 校验存在
        validateEarlyWarnHandleEvalExists(id);
        // 删除
        earlyWarnHandleEvalMapper.deleteById(id);
    }

    private void validateEarlyWarnHandleEvalExists(Long id) {
        if (earlyWarnHandleEvalMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_HANDLE_EVAL_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnHandleEvalDO getEarlyWarnHandleEval(Long id) {
        return earlyWarnHandleEvalMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnHandleEvalDO> getEarlyWarnHandleEvalPage(EarlyWarnHandleEvalPageReqVO pageReqVO) {
        return earlyWarnHandleEvalMapper.selectPage(pageReqVO);
    }

}