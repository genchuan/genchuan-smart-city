package cn.iocoder.yudao.module.datacenter.service.analysis.analysisregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisregion.AnalysisRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisregion.AnalysisRegionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按行政区划分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisRegionServiceImpl implements AnalysisRegionService {

    @Resource
    private AnalysisRegionMapper analysisRegionMapper;

    @Override
    public Long createAnalysisRegion(AnalysisRegionSaveReqVO createReqVO) {
        // 插入
        AnalysisRegionDO analysisRegion = BeanUtils.toBean(createReqVO, AnalysisRegionDO.class);
        analysisRegionMapper.insert(analysisRegion);
        // 返回
        return analysisRegion.getId();
    }

    @Override
    public void updateAnalysisRegion(AnalysisRegionSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisRegionExists(updateReqVO.getId());
        // 更新
        AnalysisRegionDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisRegionDO.class);
        analysisRegionMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisRegion(Long id) {
        // 校验存在
        validateAnalysisRegionExists(id);
        // 删除
        analysisRegionMapper.deleteById(id);
    }

    private void validateAnalysisRegionExists(Long id) {
        if (analysisRegionMapper.selectById(id) == null) {
            throw exception(ANALYSIS_REGION_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisRegionDO getAnalysisRegion(Long id) {
        return analysisRegionMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisRegionDO> getAnalysisRegionPage(AnalysisRegionPageReqVO pageReqVO) {
        return analysisRegionMapper.selectPage(pageReqVO);
    }

}