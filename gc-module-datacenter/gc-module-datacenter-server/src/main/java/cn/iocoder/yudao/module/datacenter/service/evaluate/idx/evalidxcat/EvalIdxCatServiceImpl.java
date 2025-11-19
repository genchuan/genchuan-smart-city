package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxcat.EvalIdxCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxcat.EvalIdxCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 指标分类管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalIdxCatServiceImpl implements EvalIdxCatService {

    @Resource
    private EvalIdxCatMapper evalIdxCatMapper;

    @Override
    public Long createEvalIdxCat(EvalIdxCatSaveReqVO createReqVO) {
        // 插入
        EvalIdxCatDO evalIdxCat = BeanUtils.toBean(createReqVO, EvalIdxCatDO.class);
        evalIdxCatMapper.insert(evalIdxCat);
        // 返回
        return evalIdxCat.getId();
    }

    @Override
    public void updateEvalIdxCat(EvalIdxCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalIdxCatExists(updateReqVO.getId());
        // 更新
        EvalIdxCatDO updateObj = BeanUtils.toBean(updateReqVO, EvalIdxCatDO.class);
        evalIdxCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalIdxCat(Long id) {
        // 校验存在
        validateEvalIdxCatExists(id);
        // 删除
        evalIdxCatMapper.deleteById(id);
    }

    private void validateEvalIdxCatExists(Long id) {
        if (evalIdxCatMapper.selectById(id) == null) {
            throw exception(EVAL_IDX_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EvalIdxCatDO getEvalIdxCat(Long id) {
        return evalIdxCatMapper.selectById(id);
    }

    @Override
    public PageResult<EvalIdxCatDO> getEvalIdxCatPage(EvalIdxCatPageReqVO pageReqVO) {
        return evalIdxCatMapper.selectPage(pageReqVO);
    }

}