package cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.parkingpayment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo.ParkingPaymentChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo.ParkingPaymentPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingpayment.ParkingPaymentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 停车缴费 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ParkingPaymentMapper extends BaseMapperX<ParkingPaymentDO> {

    /**
     * 分页查询停车缴费，支持按账单编号(模糊)/车牌号(模糊)/账单状态(精确)/支付方式(精确)/发票状态(精确)/支付时间范围筛选，按主键倒序
     */
    default PageResult<ParkingPaymentDO> selectPage(ParkingPaymentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingPaymentDO>()
                .likeIfPresent(ParkingPaymentDO::getBillCode, reqVO.getBillCode())
                .likeIfPresent(ParkingPaymentDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(ParkingPaymentDO::getBillStatus, reqVO.getBillStatus())
                .eqIfPresent(ParkingPaymentDO::getPayType, reqVO.getPayType())
                .eqIfPresent(ParkingPaymentDO::getInvoiceStatus, reqVO.getInvoiceStatus())
                .betweenIfPresent(ParkingPaymentDO::getPayTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(ParkingPaymentDO::getId));
    }

    /**
     * 统计卡片数据：总支付笔数、欠费笔数、总收入、缴费率
     */
    ParkingPaymentChartRespVO selectCardStats(@Param("startTime") Long startTime,
                                              @Param("endTime") Long endTime);

    /**
     * 统计每日收入趋势
     */
    List<ParkingPaymentChartRespVO.DayIncomeItem> selectDayIncomeList(@Param("startTime") Long startTime,
                                                                       @Param("endTime") Long endTime);

    /**
     * 统计每日支付笔数趋势
     */
    List<ParkingPaymentChartRespVO.DayPayItem> selectDayPayList(@Param("startTime") Long startTime,
                                                                  @Param("endTime") Long endTime);

}
