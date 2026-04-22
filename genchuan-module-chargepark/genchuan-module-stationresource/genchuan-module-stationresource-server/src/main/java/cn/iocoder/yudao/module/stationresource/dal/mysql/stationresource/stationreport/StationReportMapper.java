package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationreport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportCreateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Map;

@Mapper
public interface StationReportMapper extends BaseMapperX<StationReportDO> {
    // ====================== 1. area_info 片区统计 ======================
    Map<String, Object> selectAreaReport(StationOpReportCreateReqVO reqVO);

    // ====================== 2. station_info 场站统计 ======================
    Map<String, Object> selectStationReport(StationOpReportCreateReqVO reqVO);

    // ====================== 3. parking_space_info 车位统计 ======================
    Map<String, Object> selectSpaceReport(StationOpReportCreateReqVO reqVO);

    // ====================== 4. 生效规则统计 ======================
    Map<String, Object> selectEffectiveRuleReport(StationOpReportCreateReqVO reqVO);

    // ====================== 5. 充停联动 订单+营收统计 ======================
    Map<String, Object> selectChargeParkOrderReport(StationOpReportCreateReqVO reqVO);

    // ====================== 6. debt_expand 追缴完成率 ======================
    Map<String, Object> selectDebtExpandReport(StationOpReportCreateReqVO reqVO);

    // ====================== 7. deposit_plan 押金订单量 ======================
    Map<String, Object> selectDepositPlanReport(StationOpReportCreateReqVO reqVO);
}
