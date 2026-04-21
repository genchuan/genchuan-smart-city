package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.timepermission;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics.TimePermissionChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 时段权限 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TimePermissionMapper extends BaseMapperX<TimePermissionDO> {

    default PageResult<TimePermissionDO> selectPage(TimePermissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TimePermissionDO>()
                .eqIfPresent(TimePermissionDO::getStationId, reqVO.getStationId())
                .likeIfPresent(TimePermissionDO::getTimeRange, reqVO.getTimeRange())
                .eqIfPresent(TimePermissionDO::getPermission, reqVO.getPermission())
                .eqIfPresent(TimePermissionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TimePermissionDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(TimePermissionDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(TimePermissionDO::getUseCount, reqVO.getUseCount())
                .eqIfPresent(TimePermissionDO::getMaxStay, reqVO.getMaxStay())
                .eqIfPresent(TimePermissionDO::getWorkdayConfig, reqVO.getWorkdayConfig())
                .eqIfPresent(TimePermissionDO::getHolidayConfig, reqVO.getHolidayConfig())
                .eqIfPresent(TimePermissionDO::getPeakConfig, reqVO.getPeakConfig())
                .eqIfPresent(TimePermissionDO::getOffpeakConfig, reqVO.getOffpeakConfig())
                .eqIfPresent(TimePermissionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TimePermissionDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(TimePermissionDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(TimePermissionDO::getCreator, reqVO.getCreator())
                .eqIfPresent(TimePermissionDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(TimePermissionDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(TimePermissionDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(TimePermissionDO::getId));
    }

    /**
     * 查询总使用次数
     */
    Integer selectTotalUseCount();

    /**
     * 按月分组统计使用次数
     */
    List<TimePermissionChartRespVO.UseLine> selectUseCountGroupByMonth();
}
