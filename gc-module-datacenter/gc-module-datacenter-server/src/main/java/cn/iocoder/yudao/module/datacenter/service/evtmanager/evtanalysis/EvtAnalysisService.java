package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtanalysis;


import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtanalysis.EvtAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
/**
 * 事件研判分析 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtAnalysisService {

    /**
     * 创建事件研判分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtAnalysis(@Valid EvtAnalysisSaveReqVO createReqVO);

    /**
     * 更新事件研判分析
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtAnalysis(@Valid EvtAnalysisSaveReqVO updateReqVO);

    /**
     * 删除事件研判分析
     *
     * @param id 编号
     */
    void deleteEvtAnalysis(Long id);

    /**
     * 获得事件研判分析
     *
     * @param id 编号
     * @return 事件研判分析
     */
    EvtAnalysisDO getEvtAnalysis(Long id);

    /**
     * 获得事件研判分析分页
     *
     * @param pageReqVO 分页查询
     * @return 事件研判分析分页
     */
    PageResult<EvtAnalysisDO> getEvtAnalysisPage(EvtAnalysisPageReqVO pageReqVO);

}