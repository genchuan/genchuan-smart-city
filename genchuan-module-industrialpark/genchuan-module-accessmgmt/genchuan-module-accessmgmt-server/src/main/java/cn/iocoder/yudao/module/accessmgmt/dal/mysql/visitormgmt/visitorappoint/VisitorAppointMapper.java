package cn.iocoder.yudao.module.accessmgmt.dal.mysql.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.VisitorAppointChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.VisitorAppointPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitorappoint.VisitorAppointDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitorAppointMapper extends BaseMapperX<VisitorAppointDO> {

    default PageResult<VisitorAppointDO> selectPage(VisitorAppointPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VisitorAppointDO>()
                .likeIfPresent(VisitorAppointDO::getVisitorName, reqVO.getVisitorName())
                .likeIfPresent(VisitorAppointDO::getIdCard, reqVO.getIdCard())
                .likeIfPresent(VisitorAppointDO::getVisitCompany, reqVO.getVisitCompany())
                .eqIfPresent(VisitorAppointDO::getAppointStatus, reqVO.getAppointStatus())
                .betweenIfPresent(VisitorAppointDO::getVisitTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(VisitorAppointDO::getId));
    }

    VisitorAppointDO selectByTicket(@Param("ticket") String ticket);

    List<VisitorAppointChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") String startTime,
                                                                      @Param("endTime") String endTime);

    List<VisitorAppointChartRespVO.CompanyCountItem> selectCompanyCountList(@Param("startTime") String startTime,
                                                                              @Param("endTime") String endTime);

}
