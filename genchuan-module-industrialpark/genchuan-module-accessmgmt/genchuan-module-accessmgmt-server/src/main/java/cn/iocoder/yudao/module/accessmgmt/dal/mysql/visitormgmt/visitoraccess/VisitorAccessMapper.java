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

    /**
     * 分页查询访客通行，支持按访客姓名(模糊)/通行区域(精确)/凭证状态(精确)/通行状态(精确)/通行时间范围筛选，按主键倒序
     */
    default PageResult<VisitorAccessDO> selectPage(VisitorAccessPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VisitorAccessDO>()
                .likeIfPresent(VisitorAccessDO::getVisitorName, reqVO.getVisitorName())
                .eqIfPresent(VisitorAccessDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(VisitorAccessDO::getTicketStatus, reqVO.getTicketStatus())
                .eqIfPresent(VisitorAccessDO::getAccessStatus, reqVO.getAccessStatus())
                .betweenIfPresent(VisitorAccessDO::getAccessTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(VisitorAccessDO::getId));
    }

    /**
     * 按通行区域统计通行次数分布
     */
    List<VisitorAccessChartRespVO.AreaCountItem> selectAreaCountList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    /**
     * 按时间维度统计通行数量趋势
     */
    List<VisitorAccessChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    /**
     * 按凭证状态统计数量分布
     */
    List<VisitorAccessChartRespVO.TicketStatusItem> selectTicketStatusList(@Param("startTime") Long startTime,
                                                                            @Param("endTime") Long endTime);

}
