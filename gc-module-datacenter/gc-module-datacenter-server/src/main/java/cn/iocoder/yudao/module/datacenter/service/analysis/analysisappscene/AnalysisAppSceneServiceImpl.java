package cn.iocoder.yudao.module.datacenter.service.analysis.analysisappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppSceneSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisappscene.AnalysisAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisappscene.AnalysisAppSceneMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按应用场景分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisAppSceneServiceImpl implements AnalysisAppSceneService {

    @Resource
    private AnalysisAppSceneMapper analysisAppSceneMapper;

    @Override
    public Long createAnalysisAppScene(AnalysisAppSceneSaveReqVO createReqVO) {
        // 插入
        AnalysisAppSceneDO analysisAppScene = BeanUtils.toBean(createReqVO, AnalysisAppSceneDO.class);
        analysisAppSceneMapper.insert(analysisAppScene);
        // 返回
        return analysisAppScene.getId();
    }

    @Override
    public void updateAnalysisAppScene(AnalysisAppSceneSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisAppSceneExists(updateReqVO.getId());
        // 更新
        AnalysisAppSceneDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisAppSceneDO.class);
        analysisAppSceneMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisAppScene(Long id) {
        // 校验存在
        validateAnalysisAppSceneExists(id);
        // 删除
        analysisAppSceneMapper.deleteById(id);
    }

    private void validateAnalysisAppSceneExists(Long id) {
        if (analysisAppSceneMapper.selectById(id) == null) {
            throw exception(ANALYSIS_APP_SCENE_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisAppSceneDO getAnalysisAppScene(Long id) {
        return analysisAppSceneMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisAppSceneDO> getAnalysisAppScenePage(AnalysisAppScenePageReqVO pageReqVO) {
        return analysisAppSceneMapper.selectPage(pageReqVO);
    }

}