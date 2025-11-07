package cn.iocoder.yudao.module.datacenter.service.grid.code.evalgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.evalgridcode.EvalGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.evalgridcode.EvalGridCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价网格编码 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalGridCodeServiceImpl implements EvalGridCodeService {

    @Resource
    private EvalGridCodeMapper evalGridCodeMapper;

    @Override
    public Long createEvalGridCode(EvalGridCodeSaveReqVO createReqVO) {
        // 插入
        EvalGridCodeDO evalGridCode = BeanUtils.toBean(createReqVO, EvalGridCodeDO.class);
        evalGridCodeMapper.insert(evalGridCode);
        // 返回
        return evalGridCode.getId();
    }

    @Override
    public void updateEvalGridCode(EvalGridCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalGridCodeExists(updateReqVO.getId());
        // 更新
        EvalGridCodeDO updateObj = BeanUtils.toBean(updateReqVO, EvalGridCodeDO.class);
        evalGridCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalGridCode(Long id) {
        // 校验存在
        validateEvalGridCodeExists(id);
        // 删除
        evalGridCodeMapper.deleteById(id);
    }

    private void validateEvalGridCodeExists(Long id) {
        if (evalGridCodeMapper.selectById(id) == null) {
            throw exception(EVAL_GRID_CODE_NOT_EXISTS);
        }
    }

    @Override
    public EvalGridCodeDO getEvalGridCode(Long id) {
        return evalGridCodeMapper.selectById(id);
    }

    @Override
    public PageResult<EvalGridCodeDO> getEvalGridCodePage(EvalGridCodePageReqVO pageReqVO) {
        return evalGridCodeMapper.selectPage(pageReqVO);
    }

}