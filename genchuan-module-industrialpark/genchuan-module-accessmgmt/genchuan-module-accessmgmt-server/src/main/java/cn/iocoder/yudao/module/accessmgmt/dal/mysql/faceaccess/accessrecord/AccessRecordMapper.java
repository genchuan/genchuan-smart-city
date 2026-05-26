package cn.iocoder.yudao.module.accessmgmt.dal.mysql.faceaccess.accessrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.AccessRecordChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo.AccessRecordPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.accessrecord.AccessRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 通行记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AccessRecordMapper extends BaseMapperX<AccessRecordDO> {

    /**
     * 分页查询通行记录，支持按姓名/区域/验证方式/通行状态/时间范围筛选
     */
    default PageResult<AccessRecordDO> selectPage(AccessRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccessRecordDO>()
                .likeIfPresent(AccessRecordDO::getUserName, reqVO.getUserName())
                .eqIfPresent(AccessRecordDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(AccessRecordDO::getVerifyType, reqVO.getVerifyType())
                .eqIfPresent(AccessRecordDO::getAccessStatus, reqVO.getAccessStatus())
                .betweenIfPresent(AccessRecordDO::getAccessTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(AccessRecordDO::getId));
    }

    /**
     * 按小时统计各时段通行人数趋势
     */
    List<AccessRecordChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

    /**
     * 按天统计每日通行总量趋势
     */
    List<AccessRecordChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") Long startTime,
                                                                   @Param("endTime") Long endTime);

    /**
     * 按区域统计通行次数分布
     */
    List<AccessRecordChartRespVO.AreaCountItem> selectAreaCountList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

    /**
     * 按人员统计通行频次排名
     */
    List<AccessRecordChartRespVO.UserCountItem> selectUserCountList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

}
