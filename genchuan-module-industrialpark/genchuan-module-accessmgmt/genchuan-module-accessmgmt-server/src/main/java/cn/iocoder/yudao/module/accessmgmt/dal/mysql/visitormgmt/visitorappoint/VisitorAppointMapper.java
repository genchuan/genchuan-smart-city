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

/**
 * 访客预约 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface VisitorAppointMapper extends BaseMapperX<VisitorAppointDO> {

    /**
     * 分页查询访客预约，支持按访客姓名(模糊)/身份证号(模糊)/被访企业(模糊)/预约状态/到访时间范围筛选
     */
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

    /**
     * 根据凭证号 ticket 查询访客预约记录（到访验证用）
     */
    VisitorAppointDO selectByTicket(@Param("ticket") String ticket);

    /**
     * 按天统计每日预约数量趋势
     */
    List<VisitorAppointChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    /**
     * 按被访企业统计预约数量
     */
    List<VisitorAppointChartRespVO.CompanyCountItem> selectCompanyCountList(@Param("startTime") Long startTime,
                                                                              @Param("endTime") Long endTime);

}
