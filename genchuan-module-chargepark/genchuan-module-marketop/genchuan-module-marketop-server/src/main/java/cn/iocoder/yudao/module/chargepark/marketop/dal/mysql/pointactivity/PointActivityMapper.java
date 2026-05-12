package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.framework.utils.DateUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Mapper
public interface PointActivityMapper extends BaseMapperX<PointActivityDO> {

    default PageResult<PointActivityDO> selectPage(PointActivityPageReqVO reqVO) {
        LambdaQueryWrapperX<PointActivityDO> queryWrapperX = new LambdaQueryWrapperX<PointActivityDO>()
                .likeIfPresent(PointActivityDO::getName, reqVO.getName())
                .eqIfPresent(PointActivityDO::getType, reqVO.getType())
                .eqIfPresent(PointActivityDO::getStatus, reqVO.getStatus())
                .likeIfPresent(PointActivityDO::getRule, reqVO.getRule())
                .likeIfPresent(PointActivityDO::getDescription, reqVO.getDescription())
                .eqIfPresent(PointActivityDO::getAuditorId, reqVO.getAuditorId())
                .betweenIfPresent(PointActivityDO::getCreateTime, reqVO.getStartTime())
                .betweenIfPresent(PointActivityDO::getUpdateTime, reqVO.getEndTime())
                .orderByDesc(PointActivityDO::getId);
        if (reqVO.getStationId() != null) {
            queryWrapperX.apply("FIND_IN_SET({0}, station_ids)", reqVO.getStationId());
        }

        // 如果没有传startTime和endTime，但传了date，则用date转换
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null && StrUtil.isNotBlank(reqVO.getDate())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(reqVO.getDate(), formatter);
            LocalDateTime startDateTime = localDate.atStartOfDay();
            LocalDateTime endDateTime = localDate.atTime(LocalTime.MAX);
            String startTime = DateUtils.longToDateTime(startDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            queryWrapperX.le(PointActivityDO::getStartTime, startTime);
            String endTime = DateUtils.longToDateTime(endDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            queryWrapperX.ge(PointActivityDO::getEndTime, endTime);
        }

        return selectPage(reqVO, queryWrapperX);
    }

    default Long selectCountByChart() {
        return selectCount(new LambdaQueryWrapperX<>());
    }

    @Select("SELECT type, COUNT(*) as count FROM point_activity GROUP BY type")
    List<java.util.Map<String, Object>> selectTypeCountList();

    @Select("SELECT IFNULL(SUM(join_count), 0) FROM point_activity")
    Long selectSumJoinCount();

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM point_activity " +
            "WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY DATE(create_time) ASC")
    List<Map<String, Object>> selectCountByDay(@Param("startTime") LocalDateTime startTime);

}
