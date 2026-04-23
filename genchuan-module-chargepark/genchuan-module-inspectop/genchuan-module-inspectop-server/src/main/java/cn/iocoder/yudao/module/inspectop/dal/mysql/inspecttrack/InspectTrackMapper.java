package cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttrack;

import java.math.BigDecimal;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 巡检轨迹 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectTrackMapper extends BaseMapperX<InspectTrackDO> {

    default PageResult<InspectTrackDO> selectPage(InspectTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTrackDO>()
                .eqIfPresent(InspectTrackDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(InspectTrackDO::getTrackTime, reqVO.getTrackTime())
                .eqIfPresent(InspectTrackDO::getMileage, reqVO.getMileage())
                .eqIfPresent(InspectTrackDO::getDuration, reqVO.getDuration())
                .eqIfPresent(InspectTrackDO::getArea, reqVO.getArea())
                .eqIfPresent(InspectTrackDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectTrackDO::getPoints, reqVO.getPoints())
                .eqIfPresent(InspectTrackDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectTrackDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectTrackDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectTrackDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(InspectTrackDO::getCheckStatus, reqVO.getCheckStatus())
                .betweenIfPresent(InspectTrackDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectTrackDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectTrackDO::getId));
    }

    // 地图数据查询DTO
    @Data
    class MapDataDTO {
        private Long userId;
        private String userName;
        private String points;
    }

    // 趋势数据查询DTO
    @Data
    class TrendDataDTO {
        private String time;
        private BigDecimal totalMileage;
    }

    // 卡片数据查询DTO
    @Data
    class CardDataDTO {
        private BigDecimal totalMileage;
        private Integer totalDuration;
    }


    /**
     * 关联查询分页方法
     * 通过关联 inspect_user 表查询巡检人员姓名
     *
     * @param page  MyBatis-Plus 分页参数
     * @param reqVO 查询条件
     * @return 包含巡检人员姓名的分页结果
     */
    Page<InspectTrackRespVO> selectPageWithJoin(@Param("page") Page<InspectTrackRespVO> page,
                                                @Param("reqVO") InspectTrackPageReqVO reqVO);

    /**
     * 关联查询单条轨迹记录（用于回放）
     *
     * @param id 轨迹ID
     * @return 包含巡检人员姓名的轨迹信息
     */
    InspectTrackRespVO selectWithJoinById(@Param("id") Long id);

    /**
     * 查询地图数据
     */
    List<MapDataDTO> selectUserTrackForMap(@Param("reqVO") InspectTrackChartReqVO reqVO);

    /**
     * 查询趋势数据
     */
    List<TrendDataDTO> selectTrendData(@Param("reqVO") InspectTrackChartReqVO reqVO);

    /**
     * 查询卡片数据
     */
    CardDataDTO selectCardData(@Param("reqVO") InspectTrackChartReqVO reqVO);

}