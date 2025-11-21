package cn.iocoder.yudao.module.datacenter.service.evaluate.std.evalstdcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstdcat.EvalStdCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.std.evalstdcat.EvalStdCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 标准分类管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalStdCatServiceImpl implements EvalStdCatService {

    @Resource
    private EvalStdCatMapper evalStdCatMapper;

    @Override
    public Long createEvalStdCat(EvalStdCatSaveReqVO createReqVO) {
        // 插入
        EvalStdCatDO evalStdCat = BeanUtils.toBean(createReqVO, EvalStdCatDO.class);
        evalStdCatMapper.insert(evalStdCat);
        // 返回
        return evalStdCat.getId();
    }

    @Override
    public void updateEvalStdCat(EvalStdCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalStdCatExists(updateReqVO.getId());
        // 更新
        EvalStdCatDO updateObj = BeanUtils.toBean(updateReqVO, EvalStdCatDO.class);
        evalStdCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalStdCat(Long id) {
        // 校验存在
        validateEvalStdCatExists(id);
        // 删除
        evalStdCatMapper.deleteById(id);
    }

    private void validateEvalStdCatExists(Long id) {
        if (evalStdCatMapper.selectById(id) == null) {
            throw exception(EVAL_STD_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EvalStdCatDO getEvalStdCat(Long id) {
        return evalStdCatMapper.selectById(id);
    }

    @Override
    public PageResult<EvalStdCatDO> getEvalStdCatPage(EvalStdCatPageReqVO pageReqVO) {
        return evalStdCatMapper.selectPage(pageReqVO);
    }

}