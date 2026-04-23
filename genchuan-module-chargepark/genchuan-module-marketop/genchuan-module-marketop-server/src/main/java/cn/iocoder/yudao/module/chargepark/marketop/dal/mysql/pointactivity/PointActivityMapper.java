package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.framework.utils.DateUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PointActivityMapper extends BaseMapperX<PointActivityDO> {

    default PageResult<PointActivityDO> selectPage(PointActivityPageReqVO reqVO) {
        LambdaQueryWrapperX<PointActivityDO> queryWrapperX = new LambdaQueryWrapperX<PointActivityDO>()
                .likeIfPresent(PointActivityDO::getName, reqVO.getName())
                .eqIfPresent(PointActivityDO::getType, reqVO.getType())
                .eqIfPresent(PointActivityDO::getStatus, reqVO.getStatus())
                .orderByDesc(PointActivityDO::getId);
        if (reqVO.getStartTime() != null) {
            String startTime = DateUtils.longToDateTime(reqVO.getStartTime());
            queryWrapperX.le(PointActivityDO::getStartTime, startTime);
        }
        if (reqVO.getEndTime() != null) {
            String endTime = DateUtils.longToDateTime(reqVO.getEndTime());
            queryWrapperX.ge(PointActivityDO::getEndTime, endTime);
        }
        return selectPage(reqVO, queryWrapperX);
    }

    default Long selectCountByChart(PointActivityChartReqVO reqVO) {
        LambdaQueryWrapperX<PointActivityDO> queryWrapperX = new LambdaQueryWrapperX<>();
        if (reqVO.getStartTime() != null) {
            String startTime = DateUtils.longToDateTime(reqVO.getStartTime());
            queryWrapperX.le(PointActivityDO::getStartTime, startTime);
        }
        if (reqVO.getEndTime() != null) {
            String endTime = DateUtils.longToDateTime(reqVO.getEndTime());
            queryWrapperX.ge(PointActivityDO::getEndTime, endTime);
        }
        if (reqVO.getStationId() != null) {
            queryWrapperX.apply("FIND_IN_SET({0}, station_ids)", reqVO.getStationId());
        }
        return selectCount(queryWrapperX);
    }

    @Select("<script>" +
            "SELECT type, COUNT(*) as count FROM point_activity " +
            "<where>" +
            "<if test='startTime != null'> AND start_time &lt;= #{startTime}</if>" +
            "<if test='endTime != null'> AND end_time &gt;= #{endTime}</if>" +
            "<if test='stationId != null'> AND FIND_IN_SET(#{stationId}, station_ids) &gt; 0</if>" +
            "</where>" +
            "GROUP BY type" +
            "</script>")
    List<java.util.Map<String, Object>> selectTypeCountList(PointActivityChartReqVO reqVO);

}
