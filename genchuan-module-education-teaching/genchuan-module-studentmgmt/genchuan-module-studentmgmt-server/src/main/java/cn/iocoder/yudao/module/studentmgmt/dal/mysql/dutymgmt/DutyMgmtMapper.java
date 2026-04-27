package cn.iocoder.yudao.module.studentmgmt.dal.mysql.dutymgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 值班管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DutyMgmtMapper extends BaseMapperX<DutyMgmtDO> {

    default PageResult<DutyMgmtDO> selectPage(DutyMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DutyMgmtDO>()
                .betweenIfPresent(DutyMgmtDO::getDutyDate, reqVO.getDutyDate())
                .likeIfPresent(DutyMgmtDO::getDutyUser, reqVO.getDutyUser())
                .betweenIfPresent(DutyMgmtDO::getCheckInTime, reqVO.getCheckInTime())
                .eqIfPresent(DutyMgmtDO::getCheckInStatus, reqVO.getCheckInStatus())
                .eqIfPresent(DutyMgmtDO::getTransferReason, reqVO.getTransferReason())
                .eqIfPresent(DutyMgmtDO::getTransferUser, reqVO.getTransferUser())
                .eqIfPresent(DutyMgmtDO::getTransferStatus, reqVO.getTransferStatus())
                .eqIfPresent(DutyMgmtDO::getCarReason, reqVO.getCarReason())
                .eqIfPresent(DutyMgmtDO::getCarDestination, reqVO.getCarDestination())
                .eqIfPresent(DutyMgmtDO::getCarStatus, reqVO.getCarStatus())
                .eqIfPresent(DutyMgmtDO::getRecordContent, reqVO.getRecordContent())
                .betweenIfPresent(DutyMgmtDO::getRecordUploadTime, reqVO.getRecordUploadTime())
                .eqIfPresent(DutyMgmtDO::getStatus, reqVO.getStatus())
                .likeIfPresent(DutyMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DutyMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DutyMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(DutyMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DutyMgmtDO::getId));
    }

    DutyMgmtChartRespVO selectTotalDutyCount(LocalDateTime startTime, LocalDateTime endTime);
    JSONObject selectTotalDutyCountByCheckInStatus(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("checkInStatus") String checkInStatus);

    List<String> selectMonthList(LocalDateTime startTime, LocalDateTime endTime);

    Integer selectDutyCount(String month);

    JSONObject selectTotalDutyCountByMonth(String month, String status);

    JSONObject selectTotalShiftCountByMonth(String month, String status);

    JSONObject selectTotalVehicleCountByMonth(String month, String status);
}