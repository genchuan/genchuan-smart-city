package cn.iocoder.yudao.module.datacenter.service.evaluate.inspect.evalinspectrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectrec.EvalInspectRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspect.evalinspectrec.EvalInspectRecMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 考察记录数据 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalInspectRecServiceImpl implements EvalInspectRecService {

    @Resource
    private EvalInspectRecMapper evalInspectRecMapper;

    @Override
    public Long createEvalInspectRec(EvalInspectRecSaveReqVO createReqVO) {
        // 插入
        EvalInspectRecDO evalInspectRec = BeanUtils.toBean(createReqVO, EvalInspectRecDO.class);
        evalInspectRecMapper.insert(evalInspectRec);
        // 返回
        return evalInspectRec.getId();
    }

    @Override
    public void updateEvalInspectRec(EvalInspectRecSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalInspectRecExists(updateReqVO.getId());
        // 更新
        EvalInspectRecDO updateObj = BeanUtils.toBean(updateReqVO, EvalInspectRecDO.class);
        evalInspectRecMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalInspectRec(Long id) {
        // 校验存在
        validateEvalInspectRecExists(id);
        // 删除
        evalInspectRecMapper.deleteById(id);
    }

    private void validateEvalInspectRecExists(Long id) {
        if (evalInspectRecMapper.selectById(id) == null) {
            throw exception(EVAL_INSPECT_REC_NOT_EXISTS);
        }
    }

    @Override
    public EvalInspectRecDO getEvalInspectRec(Long id) {
        return evalInspectRecMapper.selectById(id);
    }

    @Override
    public PageResult<EvalInspectRecDO> getEvalInspectRecPage(EvalInspectRecPageReqVO pageReqVO) {
        return evalInspectRecMapper.selectPage(pageReqVO);
    }

}