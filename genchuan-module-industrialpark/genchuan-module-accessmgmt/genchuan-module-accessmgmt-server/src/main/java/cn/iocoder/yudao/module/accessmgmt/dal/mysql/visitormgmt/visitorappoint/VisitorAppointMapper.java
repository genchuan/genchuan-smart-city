package cn.iocoder.yudao.module.accessmgmt.dal.mysql.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.VisitorAppointChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.VisitorAppointPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitorappoint.VisitorAppointDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Mapper
public interface VisitorAppointMapper extends BaseMapperX<VisitorAppointDO> {

    default PageResult<VisitorAppointDO> selectPage(VisitorAppointPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VisitorAppointDO>()
                .likeIfPresent(VisitorAppointDO::getVisitorName, reqVO.getVisitorName())
                .likeIfPresent(VisitorAppointDO::getIdCard, reqVO.getIdCard())
                .likeIfPresent(VisitorAppointDO::getVisitCompany, reqVO.getVisitCompany())
                .eqIfPresent(VisitorAppointDO::getAppointStatus, reqVO.getAppointStatus())
                .betweenIfPresent(VisitorAppointDO::getVisitTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(VisitorAppointDO::getId));
    }

    VisitorAppointDO selectByTicket(@Param("ticket") String ticket);

    List<VisitorAppointChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    List<VisitorAppointChartRespVO.CompanyCountItem> selectCompanyCountList(@Param("startTime") Long startTime,
                                                                              @Param("endTime") Long endTime);

}
