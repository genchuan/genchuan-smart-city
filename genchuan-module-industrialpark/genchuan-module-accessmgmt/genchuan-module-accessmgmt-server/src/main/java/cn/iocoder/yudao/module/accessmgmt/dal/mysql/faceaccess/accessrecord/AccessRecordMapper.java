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

    default PageResult<AccessRecordDO> selectPage(AccessRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccessRecordDO>()
                .likeIfPresent(AccessRecordDO::getUserName, reqVO.getUserName())
                .eqIfPresent(AccessRecordDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(AccessRecordDO::getVerifyType, reqVO.getVerifyType())
                .eqIfPresent(AccessRecordDO::getAccessStatus, reqVO.getAccessStatus())
                .betweenIfPresent(AccessRecordDO::getAccessTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochSecond(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochSecond(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(AccessRecordDO::getId));
    }

    List<AccessRecordChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

    List<AccessRecordChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") Long startTime,
                                                                   @Param("endTime") Long endTime);

    List<AccessRecordChartRespVO.AreaCountItem> selectAreaCountList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

    List<AccessRecordChartRespVO.UserCountItem> selectUserCountList(@Param("startTime") Long startTime,
                                                                     @Param("endTime") Long endTime);

}
