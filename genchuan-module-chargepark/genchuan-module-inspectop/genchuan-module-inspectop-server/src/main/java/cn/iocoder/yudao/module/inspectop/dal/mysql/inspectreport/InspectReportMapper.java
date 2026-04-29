package cn.iocoder.yudao.module.inspectop.dal.mysql.inspectreport;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 巡检上报 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectReportMapper extends BaseMapperX<InspectReportDO> {

    default PageResult<InspectReportDO> selectPage(InspectReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectReportDO>()
                .eqIfPresent(InspectReportDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectReportDO::getType, reqVO.getType())
                .betweenIfPresent(InspectReportDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(InspectReportDO::getStatus, reqVO.getStatus())
//                .eqIfPresent(InspectReportDO::getAuditUserId, reqVO.getAuditUserId())
                .betweenIfPresent(InspectReportDO::getAuditTime, reqVO.getAuditTime())
//                .eqIfPresent(InspectReportDO::getProcessUserId, reqVO.getProcessUserId())
                .betweenIfPresent(InspectReportDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(InspectReportDO::getContent, reqVO.getContent())
                .eqIfPresent(InspectReportDO::getRemark, reqVO.getRemark())
//                .eqIfPresent(InspectReportDO::getReserve1, reqVO.getReserve1())
//                .eqIfPresent(InspectReportDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectReportDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectReportDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectReportDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectReportDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectReportDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询处置人姓名
     *
     * @param page  MyBatis-Plus 分页参数
     * @param reqVO 查询条件
     * @return 包含处置人姓名的分页结果
     */
    Page<InspectReportRespVO> selectPageWithJoin(@Param("page") Page<InspectReportRespVO> page,
                                                 @Param("reqVO") InspectReportPageReqVO reqVO);

    /**
     * 查询上报量趋势数据
     *
     * @param timeRange 时间范围
     * @return 上报量趋势数据列表
     */
    List<InspectReportChartRespVO.TrendData> selectReportTrendData(@Param("timeRange") String[] timeRange);

    /**
     * 查询上报类型分布数据
     *
     * @param timeRange 时间范围
     * @return 上报类型分布数据列表
     */
    List<InspectReportChartRespVO.TypeData> selectReportTypeDistribution(@Param("timeRange") String[] timeRange);

    /**
     * 查询上报卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 卡片统计数据
     */
    InspectReportChartRespVO.CardData selectReportCardData(@Param("timeRange") String[] timeRange);
}