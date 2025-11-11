package cn.iocoder.yudao.module.datacenter.service.analysis.analysisasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisasset.AnalysisAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisasset.AnalysisAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按资产分域分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisAssetServiceImpl implements AnalysisAssetService {

    @Resource
    private AnalysisAssetMapper analysisAssetMapper;

    @Override
    public Long createAnalysisAsset(AnalysisAssetSaveReqVO createReqVO) {
        // 插入
        AnalysisAssetDO analysisAsset = BeanUtils.toBean(createReqVO, AnalysisAssetDO.class);
        analysisAssetMapper.insert(analysisAsset);
        // 返回
        return analysisAsset.getId();
    }

    @Override
    public void updateAnalysisAsset(AnalysisAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisAssetExists(updateReqVO.getId());
        // 更新
        AnalysisAssetDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisAssetDO.class);
        analysisAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisAsset(Long id) {
        // 校验存在
        validateAnalysisAssetExists(id);
        // 删除
        analysisAssetMapper.deleteById(id);
    }

    private void validateAnalysisAssetExists(Long id) {
        if (analysisAssetMapper.selectById(id) == null) {
            throw exception(ANALYSIS_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisAssetDO getAnalysisAsset(Long id) {
        return analysisAssetMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisAssetDO> getAnalysisAssetPage(AnalysisAssetPageReqVO pageReqVO) {
        return analysisAssetMapper.selectPage(pageReqVO);
    }

}