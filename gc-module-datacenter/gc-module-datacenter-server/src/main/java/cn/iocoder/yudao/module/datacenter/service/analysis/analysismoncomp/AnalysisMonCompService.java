package cn.iocoder.yudao.module.datacenter.service.analysis.analysismoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismoncomp.AnalysisMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按监测部件分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisMonCompService {

    /**
     * 创建按监测部件分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisMonComp(@Valid AnalysisMonCompSaveReqVO createReqVO);

    /**
     * 更新按监测部件分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisMonComp(@Valid AnalysisMonCompSaveReqVO updateReqVO);

    /**
     * 删除按监测部件分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisMonComp(Long id);

    /**
     * 获得按监测部件分析研判统计
     *
     * @param id 编号
     * @return 按监测部件分析研判统计
     */
    AnalysisMonCompDO getAnalysisMonComp(Long id);

    /**
     * 获得按监测部件分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按监测部件分析研判统计分页
     */
    PageResult<AnalysisMonCompDO> getAnalysisMonCompPage(AnalysisMonCompPageReqVO pageReqVO);

}