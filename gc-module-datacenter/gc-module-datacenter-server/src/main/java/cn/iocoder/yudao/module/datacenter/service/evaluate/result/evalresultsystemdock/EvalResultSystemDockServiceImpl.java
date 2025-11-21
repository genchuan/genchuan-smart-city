package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultsystemdock;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultsystemdock.EvalResultSystemDockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultsystemdock.EvalResultSystemDockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 结果系统对接 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalResultSystemDockServiceImpl implements EvalResultSystemDockService {

    @Resource
    private EvalResultSystemDockMapper evalResultSystemDockMapper;

    @Override
    public Long createEvalResultSystemDock(EvalResultSystemDockSaveReqVO createReqVO) {
        // 插入
        EvalResultSystemDockDO evalResultSystemDock = BeanUtils.toBean(createReqVO, EvalResultSystemDockDO.class);
        evalResultSystemDockMapper.insert(evalResultSystemDock);
        // 返回
        return evalResultSystemDock.getId();
    }

    @Override
    public void updateEvalResultSystemDock(EvalResultSystemDockSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalResultSystemDockExists(updateReqVO.getId());
        // 更新
        EvalResultSystemDockDO updateObj = BeanUtils.toBean(updateReqVO, EvalResultSystemDockDO.class);
        evalResultSystemDockMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalResultSystemDock(Long id) {
        // 校验存在
        validateEvalResultSystemDockExists(id);
        // 删除
        evalResultSystemDockMapper.deleteById(id);
    }

    private void validateEvalResultSystemDockExists(Long id) {
        if (evalResultSystemDockMapper.selectById(id) == null) {
            throw exception(EVAL_RESULT_SYSTEM_DOCK_NOT_EXISTS);
        }
    }

    @Override
    public EvalResultSystemDockDO getEvalResultSystemDock(Long id) {
        return evalResultSystemDockMapper.selectById(id);
    }

    @Override
    public PageResult<EvalResultSystemDockDO> getEvalResultSystemDockPage(EvalResultSystemDockPageReqVO pageReqVO) {
        return evalResultSystemDockMapper.selectPage(pageReqVO);
    }

}