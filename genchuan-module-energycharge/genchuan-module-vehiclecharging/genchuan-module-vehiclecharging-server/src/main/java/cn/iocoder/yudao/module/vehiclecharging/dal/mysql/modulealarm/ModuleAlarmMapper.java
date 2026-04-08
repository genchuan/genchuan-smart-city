package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.modulealarm;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.modulealarm.ModuleAlarmDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo.*;
import java.time.LocalDateTime;

/**
 * 模块告警记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ModuleAlarmMapper extends BaseMapperX<ModuleAlarmDO> {

    default PageResult<ModuleAlarmDO> selectPage(ModuleAlarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ModuleAlarmDO>()
                .eqIfPresent(ModuleAlarmDO::getAlarmCode, reqVO.getAlarmCode())
                .likeIfPresent(ModuleAlarmDO::getModuleName, reqVO.getModuleName())
                .eqIfPresent(ModuleAlarmDO::getAbnormalTypeId, reqVO.getAbnormalTypeId())
                .eqIfPresent(ModuleAlarmDO::getAlarmLevelId, reqVO.getAlarmLevelId())
                .betweenIfPresent(ModuleAlarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(ModuleAlarmDO::getServerInfo, reqVO.getServerInfo())
                .eqIfPresent(ModuleAlarmDO::getAlarmStatusId, reqVO.getAlarmStatusId())
                .eqIfPresent(ModuleAlarmDO::getCheckReason, reqVO.getCheckReason())
                .eqIfPresent(ModuleAlarmDO::getRepairVoucher, reqVO.getRepairVoucher())
                .betweenIfPresent(ModuleAlarmDO::getRepairTime, reqVO.getRepairTime())
                .eqIfPresent(ModuleAlarmDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ModuleAlarmDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ModuleAlarmDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ModuleAlarmDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ModuleAlarmDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(ModuleAlarmDO::getDeleted, reqVO.getDeleted())
                .betweenIfPresent(ModuleAlarmDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ModuleAlarmDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ModuleAlarmDO::getId));
    }

    IPage<ModuleAlarmRespVO> selectPageWithNames(IPage<ModuleAlarmRespVO> page, ModuleAlarmPageReqVO reqVO);

    ModuleAlarmRespVO selectWithNamesById(@Param("id") Long id);

    List<Map<String, Object>> selectChartCardData(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    List<ModuleAlarmChartRespVO.BarData> selectChartBarData(@Param("startTime") LocalDateTime startTime,
                                                            @Param("endTime") LocalDateTime endTime);

    List<ModuleAlarmChartRespVO.LineData> selectChartLineData(@Param("startTime") LocalDateTime startTime,
                                                               @Param("endTime") LocalDateTime endTime);

}