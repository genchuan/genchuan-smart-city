package cn.iocoder.yudao.module.datacenter.service.evaluate.evalsubject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalsubject.EvalSubjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.evalsubject.EvalSubjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价主体管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalSubjectServiceImpl implements EvalSubjectService {

    @Resource
    private EvalSubjectMapper evalSubjectMapper;

    @Override
    public Long createEvalSubject(EvalSubjectSaveReqVO createReqVO) {
        // 插入
        EvalSubjectDO evalSubject = BeanUtils.toBean(createReqVO, EvalSubjectDO.class);
        evalSubjectMapper.insert(evalSubject);
        // 返回
        return evalSubject.getId();
    }

    @Override
    public void updateEvalSubject(EvalSubjectSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalSubjectExists(updateReqVO.getId());
        // 更新
        EvalSubjectDO updateObj = BeanUtils.toBean(updateReqVO, EvalSubjectDO.class);
        evalSubjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalSubject(Long id) {
        // 校验存在
        validateEvalSubjectExists(id);
        // 删除
        evalSubjectMapper.deleteById(id);
    }

    private void validateEvalSubjectExists(Long id) {
        if (evalSubjectMapper.selectById(id) == null) {
            throw exception(EVAL_SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public EvalSubjectDO getEvalSubject(Long id) {
        return evalSubjectMapper.selectById(id);
    }

    @Override
    public PageResult<EvalSubjectDO> getEvalSubjectPage(EvalSubjectPageReqVO pageReqVO) {
        return evalSubjectMapper.selectPage(pageReqVO);
    }

}