package cn.iocoder.yudao.module.datacenter.service.analysis.analysismngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo.AnalysisMngMatterSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngmatter.AnalysisMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按管理事项分析研判统计 Service 接口
 *
 * @author 亘川智城
 */
public interface AnalysisMngMatterService {

    /**
     * 创建按管理事项分析研判统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAnalysisMngMatter(@Valid AnalysisMngMatterSaveReqVO createReqVO);

    /**
     * 更新按管理事项分析研判统计
     *
     * @param updateReqVO 更新信息
     */
    void updateAnalysisMngMatter(@Valid AnalysisMngMatterSaveReqVO updateReqVO);

    /**
     * 删除按管理事项分析研判统计
     *
     * @param id 编号
     */
    void deleteAnalysisMngMatter(Long id);

    /**
     * 获得按管理事项分析研判统计
     *
     * @param id 编号
     * @return 按管理事项分析研判统计
     */
    AnalysisMngMatterDO getAnalysisMngMatter(Long id);

    /**
     * 获得按管理事项分析研判统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按管理事项分析研判统计分页
     */
    PageResult<AnalysisMngMatterDO> getAnalysisMngMatterPage(AnalysisMngMatterPageReqVO pageReqVO);

}