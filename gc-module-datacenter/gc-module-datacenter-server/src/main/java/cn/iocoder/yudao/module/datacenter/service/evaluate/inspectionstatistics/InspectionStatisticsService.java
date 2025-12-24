package cn.iocoder.yudao.module.datacenter.service.evaluate.inspectionstatistics;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics.InspectionStatisticsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 环卫考核统计结果 Service 接口
 *
 * @author zhucongquan
 */
public interface InspectionStatisticsService {

    /**
     * 创建环卫考核统计结果
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionStatistics(@Valid InspectionStatisticsSaveReqVO createReqVO);

    /**
     * 更新环卫考核统计结果
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionStatistics(@Valid InspectionStatisticsSaveReqVO updateReqVO);

    /**
     * 删除环卫考核统计结果
     *
     * @param id 编号
     */
    void deleteInspectionStatistics(Long id);

    /**
     * 获得环卫考核统计结果
     *
     * @param id 编号
     * @return 环卫考核统计结果
     */
    InspectionStatisticsDO getInspectionStatistics(Long id);

    /**
     * 获得环卫考核统计结果分页
     *
     * @param pageReqVO 分页查询
     * @return 环卫考核统计结果分页
     */
    PageResult<InspectionStatisticsDO> getInspectionStatisticsPage(InspectionStatisticsPageReqVO pageReqVO);

}