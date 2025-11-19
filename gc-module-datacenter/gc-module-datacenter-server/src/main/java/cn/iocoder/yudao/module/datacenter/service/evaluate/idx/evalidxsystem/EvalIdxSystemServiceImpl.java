package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxsystem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxsystem.EvalIdxSystemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxsystem.EvalIdxSystemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 指标体系管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalIdxSystemServiceImpl implements EvalIdxSystemService {

    @Resource
    private EvalIdxSystemMapper evalIdxSystemMapper;

    @Override
    public Long createEvalIdxSystem(EvalIdxSystemSaveReqVO createReqVO) {
        // 插入
        EvalIdxSystemDO evalIdxSystem = BeanUtils.toBean(createReqVO, EvalIdxSystemDO.class);
        evalIdxSystemMapper.insert(evalIdxSystem);
        // 返回
        return evalIdxSystem.getId();
    }

    @Override
    public void updateEvalIdxSystem(EvalIdxSystemSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalIdxSystemExists(updateReqVO.getId());
        // 更新
        EvalIdxSystemDO updateObj = BeanUtils.toBean(updateReqVO, EvalIdxSystemDO.class);
        evalIdxSystemMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalIdxSystem(Long id) {
        // 校验存在
        validateEvalIdxSystemExists(id);
        // 删除
        evalIdxSystemMapper.deleteById(id);
    }

    private void validateEvalIdxSystemExists(Long id) {
        if (evalIdxSystemMapper.selectById(id) == null) {
            throw exception(EVAL_IDX_SYSTEM_NOT_EXISTS);
        }
    }

    @Override
    public EvalIdxSystemDO getEvalIdxSystem(Long id) {
        return evalIdxSystemMapper.selectById(id);
    }

    @Override
    public PageResult<EvalIdxSystemDO> getEvalIdxSystemPage(EvalIdxSystemPageReqVO pageReqVO) {
        return evalIdxSystemMapper.selectPage(pageReqVO);
    }

}