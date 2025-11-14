package cn.iocoder.yudao.module.datacenter.service.analysis.analysismoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismoncomp.AnalysisMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysismoncomp.AnalysisMonCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按监测部件分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisMonCompServiceImpl implements AnalysisMonCompService {

    @Resource
    private AnalysisMonCompMapper analysisMonCompMapper;

    @Override
    public Long createAnalysisMonComp(AnalysisMonCompSaveReqVO createReqVO) {
        // 插入
        AnalysisMonCompDO analysisMonComp = BeanUtils.toBean(createReqVO, AnalysisMonCompDO.class);
        analysisMonCompMapper.insert(analysisMonComp);
        // 返回
        return analysisMonComp.getId();
    }

    @Override
    public void updateAnalysisMonComp(AnalysisMonCompSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisMonCompExists(updateReqVO.getId());
        // 更新
        AnalysisMonCompDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisMonCompDO.class);
        analysisMonCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisMonComp(Long id) {
        // 校验存在
        validateAnalysisMonCompExists(id);
        // 删除
        analysisMonCompMapper.deleteById(id);
    }

    private void validateAnalysisMonCompExists(Long id) {
        if (analysisMonCompMapper.selectById(id) == null) {
            throw exception(ANALYSIS_MON_COMP_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisMonCompDO getAnalysisMonComp(Long id) {
        return analysisMonCompMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisMonCompDO> getAnalysisMonCompPage(AnalysisMonCompPageReqVO pageReqVO) {
        return analysisMonCompMapper.selectPage(pageReqVO);
    }

}