package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.disposalresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.disposalresulteval.DisposalResultEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.disposalresulteval.DisposalResultEvalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 处置结果评估 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DisposalResultEvalServiceImpl implements DisposalResultEvalService {

    @Resource
    private DisposalResultEvalMapper disposalResultEvalMapper;

    @Override
    public Long createDisposalResultEval(DisposalResultEvalSaveReqVO createReqVO) {
        // 插入
        DisposalResultEvalDO disposalResultEval = BeanUtils.toBean(createReqVO, DisposalResultEvalDO.class);
        disposalResultEvalMapper.insert(disposalResultEval);
        // 返回
        return disposalResultEval.getId();
    }

    @Override
    public void updateDisposalResultEval(DisposalResultEvalSaveReqVO updateReqVO) {
        // 校验存在
        validateDisposalResultEvalExists(updateReqVO.getId());
        // 更新
        DisposalResultEvalDO updateObj = BeanUtils.toBean(updateReqVO, DisposalResultEvalDO.class);
        disposalResultEvalMapper.updateById(updateObj);
    }

    @Override
    public void deleteDisposalResultEval(Long id) {
        // 校验存在
        validateDisposalResultEvalExists(id);
        // 删除
        disposalResultEvalMapper.deleteById(id);
    }

    private void validateDisposalResultEvalExists(Long id) {
        if (disposalResultEvalMapper.selectById(id) == null) {
            throw exception(DISPOSAL_RESULT_EVAL_NOT_EXISTS);
        }
    }

    @Override
    public DisposalResultEvalDO getDisposalResultEval(Long id) {
        return disposalResultEvalMapper.selectById(id);
    }

    @Override
    public PageResult<DisposalResultEvalDO> getDisposalResultEvalPage(DisposalResultEvalPageReqVO pageReqVO) {
        return disposalResultEvalMapper.selectPage(pageReqVO);
    }

}