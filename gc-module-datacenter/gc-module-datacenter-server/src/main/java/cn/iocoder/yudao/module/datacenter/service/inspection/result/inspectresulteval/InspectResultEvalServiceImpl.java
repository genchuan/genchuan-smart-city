package cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresulteval.InspectResultEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresulteval.InspectResultEvalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检结果评估 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectResultEvalServiceImpl implements InspectResultEvalService {

    @Resource
    private InspectResultEvalMapper inspectResultEvalMapper;

    @Override
    public Long createInspectResultEval(InspectResultEvalSaveReqVO createReqVO) {
        // 插入
        InspectResultEvalDO inspectResultEval = BeanUtils.toBean(createReqVO, InspectResultEvalDO.class);
        inspectResultEvalMapper.insert(inspectResultEval);
        // 返回
        return inspectResultEval.getId();
    }

    @Override
    public void updateInspectResultEval(InspectResultEvalSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectResultEvalExists(updateReqVO.getId());
        // 更新
        InspectResultEvalDO updateObj = BeanUtils.toBean(updateReqVO, InspectResultEvalDO.class);
        inspectResultEvalMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectResultEval(Long id) {
        // 校验存在
        validateInspectResultEvalExists(id);
        // 删除
        inspectResultEvalMapper.deleteById(id);
    }

    private void validateInspectResultEvalExists(Long id) {
        if (inspectResultEvalMapper.selectById(id) == null) {
            throw exception(INSPECT_RESULT_EVAL_NOT_EXISTS);
        }
    }

    @Override
    public InspectResultEvalDO getInspectResultEval(Long id) {
        return inspectResultEvalMapper.selectById(id);
    }

    @Override
    public PageResult<InspectResultEvalDO> getInspectResultEvalPage(InspectResultEvalPageReqVO pageReqVO) {
        return inspectResultEvalMapper.selectPage(pageReqVO);
    }

}