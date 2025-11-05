package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtanalysis;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtanalysis.EvtAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtanalysis.EvtAnalysisMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件研判分析 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EvtAnalysisServiceImpl implements EvtAnalysisService {

    @Resource
    private EvtAnalysisMapper evtAnalysisMapper;

    @Override
    public Long createEvtAnalysis(EvtAnalysisSaveReqVO createReqVO) {
        // 插入
        EvtAnalysisDO evtAnalysis = BeanUtils.toBean(createReqVO, EvtAnalysisDO.class);
        evtAnalysisMapper.insert(evtAnalysis);
        // 返回
        return evtAnalysis.getId();
    }

    @Override
    public void updateEvtAnalysis(EvtAnalysisSaveReqVO updateReqVO) {
        // 校验存在
        validateEvtAnalysisExists(updateReqVO.getId());
        // 更新
        EvtAnalysisDO updateObj = BeanUtils.toBean(updateReqVO, EvtAnalysisDO.class);
        evtAnalysisMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvtAnalysis(Long id) {
        // 校验存在
        validateEvtAnalysisExists(id);
        // 删除
        evtAnalysisMapper.deleteById(id);
    }

    private void validateEvtAnalysisExists(Long id) {
        if (evtAnalysisMapper.selectById(id) == null) {
            throw exception(EVT_ANALYSIS_NOT_EXISTS);
        }
    }

    @Override
    public EvtAnalysisDO getEvtAnalysis(Long id) {
        return evtAnalysisMapper.selectById(id);
    }

    @Override
    public PageResult<EvtAnalysisDO> getEvtAnalysisPage(EvtAnalysisPageReqVO pageReqVO) {
        return evtAnalysisMapper.selectPage(pageReqVO);
    }

}