package cn.iocoder.yudao.module.datacenter.service.analysis.analysisappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppSceneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisappscene.AnalysisAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按应用场景分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisAppSceneService {

    /**
     * 创建按应用场景分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisAppScene(@Valid AnalysisAppSceneSaveReqVO createReqVO);

    /**
     * 更新按应用场景分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisAppScene(@Valid AnalysisAppSceneSaveReqVO updateReqVO);

    /**
     * 删除按应用场景分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisAppScene(Long id);

    /**
     * 获得按应用场景分析研判统计
     *
     * @param id 编号
     * @return 按应用场景分析研判统计
     */
    AnalysisAppSceneDO getAnalysisAppScene(Long id);

    /**
     * 获得按应用场景分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按应用场景分析研判统计分页
     */
    PageResult<AnalysisAppSceneDO> getAnalysisAppScenePage(AnalysisAppScenePageReqVO pageReqVO);

}