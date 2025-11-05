package cn.iocoder.yudao.module.datacenter.service.grid.rpt.evalgridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.evalgridrpt.EvalGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.rpt.evalgridrpt.EvalGridRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价网格统计 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EvalGridRptServiceImpl implements EvalGridRptService {

    @Resource
    private EvalGridRptMapper evalGridRptMapper;

    @Override
    public Long createEvalGridRpt(EvalGridRptSaveReqVO createReqVO) {
        // 插入
        EvalGridRptDO evalGridRpt = BeanUtils.toBean(createReqVO, EvalGridRptDO.class);
        evalGridRptMapper.insert(evalGridRpt);
        // 返回
        return evalGridRpt.getId();
    }

    @Override
    public void updateEvalGridRpt(EvalGridRptSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalGridRptExists(updateReqVO.getId());
        // 更新
        EvalGridRptDO updateObj = BeanUtils.toBean(updateReqVO, EvalGridRptDO.class);
        evalGridRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalGridRpt(Long id) {
        // 校验存在
        validateEvalGridRptExists(id);
        // 删除
        evalGridRptMapper.deleteById(id);
    }

    private void validateEvalGridRptExists(Long id) {
        if (evalGridRptMapper.selectById(id) == null) {
            throw exception(EVAL_GRID_RPT_NOT_EXISTS);
        }
    }

    @Override
    public EvalGridRptDO getEvalGridRpt(Long id) {
        return evalGridRptMapper.selectById(id);
    }

    @Override
    public PageResult<EvalGridRptDO> getEvalGridRptPage(EvalGridRptPageReqVO pageReqVO) {
        return evalGridRptMapper.selectPage(pageReqVO);
    }

}