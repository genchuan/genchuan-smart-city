package cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.leaverecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 离场记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LeaveRecordMapper extends BaseMapperX<LeaveRecordDO> {

    default PageResult<LeaveRecordDO> selectPage(LeaveRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeaveRecordDO>()
                .eqIfPresent(LeaveRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(LeaveRecordDO::getParkDuration, reqVO.getParkDuration())
                .eqIfPresent(LeaveRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(LeaveRecordDO::getStationId, reqVO.getStationId())
                .eqIfPresent(LeaveRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(LeaveRecordDO::getProofImage, reqVO.getProofImage())
                .eqIfPresent(LeaveRecordDO::getIsCorrected, reqVO.getIsCorrected())
                .eqIfPresent(LeaveRecordDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(LeaveRecordDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(LeaveRecordDO::getCreator, reqVO.getCreator())
                .eqIfPresent(LeaveRecordDO::getUpdater, reqVO.getUpdater())
                .orderByDesc(LeaveRecordDO::getId));
    }

    IPage<LeaveRecordRespVO> selectPageJoin(Page<?> page, @Param("reqVO") LeaveRecordPageReqVO reqVO);

    LeaveRecordRespVO selectByIdJoinStation(@Param("id") Long id);

    /**
     * 查询离场量趋势（按天统计）
     */
    List<Map<String, Object>> selectLeaveCountTrend(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询各时段离场量
     */
    List<Map<String, Object>> selectHourLeaveCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询今日离场量
     */
    Long selectTodayLeaveCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

    /**
     * 查询今日离场峰值（某小时最大离场量）
     */
    Long selectTodayLeavePeak(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("stationId") Long stationId);

}