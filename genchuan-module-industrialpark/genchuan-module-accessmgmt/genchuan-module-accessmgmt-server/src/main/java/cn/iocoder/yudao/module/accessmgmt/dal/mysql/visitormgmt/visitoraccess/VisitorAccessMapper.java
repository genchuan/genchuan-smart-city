package cn.iocoder.yudao.module.accessmgmt.dal.mysql.visitormgmt.visitoraccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.VisitorAccessChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.VisitorAccessPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.visitormgmt.visitoraccess.VisitorAccessDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 访客通行 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface VisitorAccessMapper extends BaseMapperX<VisitorAccessDO> {

    default PageResult<VisitorAccessDO> selectPage(VisitorAccessPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VisitorAccessDO>()
                .likeIfPresent(VisitorAccessDO::getVisitorName, reqVO.getVisitorName())
                .eqIfPresent(VisitorAccessDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(VisitorAccessDO::getTicketStatus, reqVO.getTicketStatus())
                .eqIfPresent(VisitorAccessDO::getAccessStatus, reqVO.getAccessStatus())
                .betweenIfPresent(VisitorAccessDO::getAccessTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochSecond(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochSecond(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(VisitorAccessDO::getId));
    }

    List<VisitorAccessChartRespVO.AreaCountItem> selectAreaCountList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    List<VisitorAccessChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    List<VisitorAccessChartRespVO.TicketStatusItem> selectTicketStatusList(@Param("startTime") Long startTime,
                                                                            @Param("endTime") Long endTime);

}
