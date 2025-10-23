package cn.iocoder.yudao.module.datacenter.service.evalgriddiv;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evalgriddiv.EvalGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalGridDivServiceImpl implements EvalGridDivService {

    @Resource
    private EvalGridDivMapper evalGridDivMapper;

    @Override
    public Long createEvalGridDiv(EvalGridDivSaveReqVO createReqVO) {
        // 插入
        EvalGridDivDO evalGridDiv = BeanUtils.toBean(createReqVO, EvalGridDivDO.class);
        evalGridDivMapper.insert(evalGridDiv);
        // 返回
        return evalGridDiv.getId();
    }

    @Override
    public void updateEvalGridDiv(EvalGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalGridDivExists(updateReqVO.getId());
        // 更新
        EvalGridDivDO updateObj = BeanUtils.toBean(updateReqVO, EvalGridDivDO.class);
        evalGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalGridDiv(Long id) {
        // 校验存在
        validateEvalGridDivExists(id);
        // 删除
        evalGridDivMapper.deleteById(id);
    }

    private void validateEvalGridDivExists(Long id) {
        if (evalGridDivMapper.selectById(id) == null) {
            throw exception(EVAL_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public EvalGridDivDO getEvalGridDiv(Long id) {
        return evalGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<EvalGridDivDO> getEvalGridDivPage(EvalGridDivPageReqVO pageReqVO) {
        return evalGridDivMapper.selectPage(pageReqVO);
    }

}