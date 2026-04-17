package cn.iocoder.yudao.module.inspectop.dal.mysql.oilmonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.oilmonitor.OilMonitorDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 油车占位监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface OilMonitorMapper extends BaseMapperX<OilMonitorDO> {

    default PageResult<OilMonitorDO> selectPage(OilMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OilMonitorDO>()
                .eqIfPresent(OilMonitorDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(OilMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(OilMonitorDO::getIdentifyTime, reqVO.getIdentifyTime())
                .eqIfPresent(OilMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(OilMonitorDO::getProcessUserId, reqVO.getProcessUserId())
                .betweenIfPresent(OilMonitorDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(OilMonitorDO::getIgnoreReason, reqVO.getIgnoreReason())
                .eqIfPresent(OilMonitorDO::getProcessProgress, reqVO.getProcessProgress())
                .eqIfPresent(OilMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(OilMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(OilMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OilMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(OilMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(OilMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(OilMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(OilMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(OilMonitorDO::getId));
    }

    /**
     * 【新增方法】关联查询分页方法
     * 使用自定义SQL进行关联查询，返回包含车位编号和场站名称的结果
     *
     * @param page MyBatis-Plus分页对象
     * @param reqVO 查询条件
     * @return 包含关联信息的结果列表
     */
    Page<OilMonitorRespVO> selectPageWithJoin(@Param("page") Page<OilMonitorRespVO> page,
                                              @Param("reqVO") OilMonitorPageReqVO reqVO);

    // 在 OilMonitorMapper 接口中添加
    /**
     * 查询趋势数据（按小时统计识别次数）
     *
     * @param reqVO 查询参数
     * @return 趋势数据列表
     */
    List<OilMonitorChartRespVO.TrendData> selectTrendData(@Param("reqVO") OilMonitorChartReqVO reqVO);

    /**
     * 查询场站数据（按场站统计占位次数）
     *
     * @param reqVO 查询参数
     * @return 场站数据列表
     */
    List<OilMonitorChartRespVO.StationData> selectStationData(@Param("reqVO") OilMonitorChartReqVO reqVO);

    /**
     * 查询卡片数据（待处置数量和完成率）
     *
     * @param reqVO 查询参数
     * @return 卡片数据
     */
    OilMonitorChartRespVO.CardData selectCardData(@Param("reqVO") OilMonitorChartReqVO reqVO);
}