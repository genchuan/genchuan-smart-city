package cn.iocoder.yudao.module.datacenter.service.analysis.analysismonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismonevt.AnalysisMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按监测事件分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisMonEvtService {

    /**
     * 创建按监测事件分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisMonEvt(@Valid AnalysisMonEvtSaveReqVO createReqVO);

    /**
     * 更新按监测事件分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisMonEvt(@Valid AnalysisMonEvtSaveReqVO updateReqVO);

    /**
     * 删除按监测事件分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisMonEvt(Long id);

    /**
     * 获得按监测事件分析研判统计
     *
     * @param id 编号
     * @return 按监测事件分析研判统计
     */
    AnalysisMonEvtDO getAnalysisMonEvt(Long id);

    /**
     * 获得按监测事件分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按监测事件分析研判统计分页
     */
    PageResult<AnalysisMonEvtDO> getAnalysisMonEvtPage(AnalysisMonEvtPageReqVO pageReqVO);

}