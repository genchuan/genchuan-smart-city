package cn.iocoder.yudao.module.datacenter.service.analysis.analysismonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismonevt.AnalysisMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismonevt.AnalysisMonEvtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按监测事件分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisMonEvtServiceImpl implements AnalysisMonEvtService {

    @Resource
    private AnalysisMonEvtMapper analysisMonEvtMapper;

    @Override
    public Long createAnalysisMonEvt(AnalysisMonEvtSaveReqVO createReqVO) {
        // 插入
        AnalysisMonEvtDO analysisMonEvt = BeanUtils.toBean(createReqVO, AnalysisMonEvtDO.class);
        analysisMonEvtMapper.insert(analysisMonEvt);
        // 返回
        return analysisMonEvt.getId();
    }

    @Override
    public void updateAnalysisMonEvt(AnalysisMonEvtSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisMonEvtExists(updateReqVO.getId());
        // 更新
        AnalysisMonEvtDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisMonEvtDO.class);
        analysisMonEvtMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisMonEvt(Long id) {
        // 校验存在
        validateAnalysisMonEvtExists(id);
        // 删除
        analysisMonEvtMapper.deleteById(id);
    }

    private void validateAnalysisMonEvtExists(Long id) {
        if (analysisMonEvtMapper.selectById(id) == null) {
            throw exception(ANALYSIS_MON_EVT_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisMonEvtDO getAnalysisMonEvt(Long id) {
        return analysisMonEvtMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisMonEvtDO> getAnalysisMonEvtPage(AnalysisMonEvtPageReqVO pageReqVO) {
        return analysisMonEvtMapper.selectPage(pageReqVO);
    }

}