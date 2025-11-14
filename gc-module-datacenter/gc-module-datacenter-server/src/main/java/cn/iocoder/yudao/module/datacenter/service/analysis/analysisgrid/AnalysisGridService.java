package cn.iocoder.yudao.module.datacenter.service.analysis.analysisgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisgrid.AnalysisGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按网格分域分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisGridService {

    /**
     * 创建按网格分域分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisGrid(@Valid AnalysisGridSaveReqVO createReqVO);

    /**
     * 更新按网格分域分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisGrid(@Valid AnalysisGridSaveReqVO updateReqVO);

    /**
     * 删除按网格分域分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisGrid(Long id);

    /**
     * 获得按网格分域分析研判统计
     *
     * @param id 编号
     * @return 按网格分域分析研判统计
     */
    AnalysisGridDO getAnalysisGrid(Long id);

    /**
     * 获得按网格分域分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按网格分域分析研判统计分页
     */
    PageResult<AnalysisGridDO> getAnalysisGridPage(AnalysisGridPageReqVO pageReqVO);

}