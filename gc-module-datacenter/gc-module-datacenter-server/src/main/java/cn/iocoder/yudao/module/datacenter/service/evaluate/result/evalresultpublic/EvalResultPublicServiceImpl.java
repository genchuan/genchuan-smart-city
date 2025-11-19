package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpublic.EvalResultPublicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultpublic.EvalResultPublicMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果公示 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultPublicServiceImpl implements EvalResultPublicService {

    @Resource
    private EvalResultPublicMapper evalResultPublicMapper;

    @Override
    public Long createEvalResultPublic(EvalResultPublicSaveReqVO createReqVO) {
        // 插入
        EvalResultPublicDO evalResultPublic = BeanUtils.toBean(createReqVO, EvalResultPublicDO.class);
        evalResultPublicMapper.insert(evalResultPublic);
        // 返回
        return evalResultPublic.getId();
    }

    @Override
    public void updateEvalResultPublic(EvalResultPublicSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultPublicExists(updateReqVO.getId());
        // 更新
        EvalResultPublicDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultPublicDO.class);
        evalResultPublicMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultPublic(Long id) {
        // 校验存在
        validateEvalResultPublicExists(id);
        // 删除
        evalResultPublicMapper.deleteById(id);
    }

    private void validateEvalResultPublicExists(Long id) {
        if (evalResultPublicMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_PUBLIC_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultPublicDO getEvalResultPublic(Long id) {
        return evalResultPublicMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultPublicDO> getEvalResultPublicPage(EvalResultPublicPageReqVO pageReqVO) {
        return evalResultPublicMapper.selectPage(pageReqVO);
    }

}