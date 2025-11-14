package cn.iocoder.yudao.module.datacenter.service.analysis.analysisgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisgrid.AnalysisGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisgrid.AnalysisGridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按网格分域分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisGridServiceImpl implements AnalysisGridService {

    @Resource
    private AnalysisGridMapper analysisGridMapper;

    @Override
    public Long createAnalysisGrid(AnalysisGridSaveReqVO createReqVO) {
        // 插入
        AnalysisGridDO analysisGrid = BeanUtils.toBean(createReqVO, AnalysisGridDO.class);
        analysisGridMapper.insert(analysisGrid);
        // 返回
        return analysisGrid.getId();
    }

    @Override
    public void updateAnalysisGrid(AnalysisGridSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisGridExists(updateReqVO.getId());
        // 更新
        AnalysisGridDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisGridDO.class);
        analysisGridMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisGrid(Long id) {
        // 校验存在
        validateAnalysisGridExists(id);
        // 删除
        analysisGridMapper.deleteById(id);
    }

    private void validateAnalysisGridExists(Long id) {
        if (analysisGridMapper.selectById(id) == null) {
            throw exception(ANALYSIS_GRID_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisGridDO getAnalysisGrid(Long id) {
        return analysisGridMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisGridDO> getAnalysisGridPage(AnalysisGridPageReqVO pageReqVO) {
        return analysisGridMapper.selectPage(pageReqVO);
    }

}