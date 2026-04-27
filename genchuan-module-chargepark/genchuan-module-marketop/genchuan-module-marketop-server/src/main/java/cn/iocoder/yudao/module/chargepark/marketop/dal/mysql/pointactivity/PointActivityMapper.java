package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
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
                .likeIfPresent(PointActivityDO::getRule, reqVO.getRule())
                .likeIfPresent(PointActivityDO::getDescription, reqVO.getDescription())
                .eqIfPresent(PointActivityDO::getAuditorId, reqVO.getAuditorId())
                .orderByDesc(PointActivityDO::getId);
        if (reqVO.getStationId() != null) {
            queryWrapperX.apply("FIND_IN_SET({0}, station_ids)", reqVO.getStationId());
        }
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

    default Long selectCountByChart() {
        return selectCount(new LambdaQueryWrapperX<>());
    }

    @Select("SELECT type, COUNT(*) as count FROM point_activity GROUP BY type")
    List<java.util.Map<String, Object>> selectTypeCountList();

    @Select("SELECT IFNULL(SUM(join_count), 0) FROM point_activity")
    Long selectSumJoinCount();

}
