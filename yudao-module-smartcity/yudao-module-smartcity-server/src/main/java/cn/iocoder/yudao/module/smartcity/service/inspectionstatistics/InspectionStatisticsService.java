package cn.iocoder.yudao.module.smartcity.service.inspectionstatistics;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionstatistics.vo.InspectionStatisticsSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionstatistics.InspectionStatisticsDO;
import jakarta.validation.Valid;

/**
 * 巡查分析统计 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionStatisticsService {

    /**
     * 创建巡查分析统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionStatistics(@Valid InspectionStatisticsSaveReqVO createReqVO);

    /**
     * 更新巡查分析统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionStatistics(@Valid InspectionStatisticsSaveReqVO updateReqVO);

    /**
     * 删除巡查分析统计
     *
     * @param id 编号
     */
    void deleteInspectionStatistics(Long id);

    /**
     * 获得巡查分析统计
     *
     * @param id 编号
     * @return 巡查分析统计
     */
    InspectionStatisticsDO getInspectionStatistics(Long id);

    /**
     * 获得巡查分析统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查分析统计分页
     */
    PageResult<InspectionStatisticsDO> getInspectionStatisticsPage(InspectionStatisticsPageReqVO pageReqVO);

}