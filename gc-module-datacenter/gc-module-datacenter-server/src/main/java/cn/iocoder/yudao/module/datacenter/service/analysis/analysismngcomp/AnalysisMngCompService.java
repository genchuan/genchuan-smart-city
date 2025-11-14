package cn.iocoder.yudao.module.datacenter.service.analysis.analysismngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngcomp.AnalysisMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按管理部件分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisMngCompService {

    /**
     * 创建按管理部件分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisMngComp(@Valid AnalysisMngCompSaveReqVO createReqVO);

    /**
     * 更新按管理部件分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisMngComp(@Valid AnalysisMngCompSaveReqVO updateReqVO);

    /**
     * 删除按管理部件分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisMngComp(Long id);

    /**
     * 获得按管理部件分析研判统计
     *
     * @param id 编号
     * @return 按管理部件分析研判统计
     */
    AnalysisMngCompDO getAnalysisMngComp(Long id);

    /**
     * 获得按管理部件分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按管理部件分析研判统计分页
     */
    PageResult<AnalysisMngCompDO> getAnalysisMngCompPage(AnalysisMngCompPageReqVO pageReqVO);

}