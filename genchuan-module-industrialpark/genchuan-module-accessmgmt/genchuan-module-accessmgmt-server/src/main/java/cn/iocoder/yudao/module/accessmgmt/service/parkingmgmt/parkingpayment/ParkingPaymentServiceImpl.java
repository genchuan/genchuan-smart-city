package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.parkingpayment;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingpayment.ParkingPaymentDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.parkingpayment.ParkingPaymentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 停车缴费 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingPaymentServiceImpl implements ParkingPaymentService {

    @Resource
    private ParkingPaymentMapper parkingPaymentMapper;

    @Override
    public PageResult<ParkingPaymentRespVO> getParkingPaymentPage(ParkingPaymentPageReqVO pageReqVO) {
        PageResult<ParkingPaymentDO> pageResult = parkingPaymentMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ParkingPaymentRespVO.class);
    }

    @Override
    public ParkingPaymentRespVO getParkingPayment(Long id) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(id);
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, ParkingPaymentRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentGenerateRespVO generateParkingPayment(ParkingPaymentGenerateReqVO reqVO) {
        ParkingPaymentDO entity = new ParkingPaymentDO();
        entity.setPlateNo(reqVO.getPlateNo());
        entity.setParkDuration(reqVO.getParkDuration());
        entity.setFeeAmount(reqVO.getFeeAmount());
        entity.setBillStatus("待缴费");
        entity.setInvoiceStatus("未开具");
        // 生成账单编号: PP + yyyyMMddHHmmss + 4位随机数
        String billCode = "PP" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + String.format("%04d", (int) (Math.random() * 10000));
        entity.setBillCode(billCode);

        parkingPaymentMapper.insert(entity);

        ParkingPaymentGenerateRespVO respVO = new ParkingPaymentGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setBillCode(billCode);
        return respVO;
    }

    @Override
    public ParkingPaymentCalculateRespVO calculateParkingPayment(ParkingPaymentCalculateReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        ParkingPaymentCalculateRespVO respVO = new ParkingPaymentCalculateRespVO();
        respVO.setOriginalFee(entity.getFeeAmount());
        // 优惠费用：如果有优惠金额则使用，否则为0
        BigDecimal discountFee = entity.getDiscountAmount() != null ? entity.getDiscountAmount() : BigDecimal.ZERO;
        respVO.setDiscountFee(discountFee);
        // 实付费用 = 原始费用 - 优惠费用
        respVO.setActualFee(entity.getFeeAmount().subtract(discountFee));
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentPayRespVO payParkingPayment(ParkingPaymentPayReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        entity.setPayType(reqVO.getPayType());
        entity.setPayTime(LocalDateTime.now());
        entity.setBillStatus("已缴费");
        parkingPaymentMapper.updateById(entity);

        ParkingPaymentPayRespVO respVO = new ParkingPaymentPayRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentInvoiceRespVO invoiceParkingPayment(ParkingPaymentInvoiceReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        entity.setInvoiceStatus("已开具");
        parkingPaymentMapper.updateById(entity);

        ParkingPaymentInvoiceRespVO respVO = new ParkingPaymentInvoiceRespVO();
        respVO.setSuccess(true);
        respVO.setInvoiceUrl("https://invoice.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remindParkingPayment(ParkingPaymentRemindReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        // 模拟催缴：将账单状态改为已欠费
        entity.setBillStatus("已欠费");
        parkingPaymentMapper.updateById(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean discountParkingPayment(ParkingPaymentDiscountReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        entity.setDiscountAmount(reqVO.getDiscountAmount());
        entity.setDiscountReason(reqVO.getDiscountReason());
        parkingPaymentMapper.updateById(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentRepairRespVO repairParkingPayment(ParkingPaymentRepairReqVO reqVO) {
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }

        entity.setPayType(reqVO.getPayType());
        entity.setPayTime(LocalDateTime.now());
        entity.setBillStatus("已缴费");
        parkingPaymentMapper.updateById(entity);

        ParkingPaymentRepairRespVO respVO = new ParkingPaymentRepairRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    @Override
    public List<ParkingPaymentRespVO> getParkingPaymentList(ParkingPaymentPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ParkingPaymentDO> pageResult = parkingPaymentMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), ParkingPaymentRespVO.class);
    }

    @Override
    public ParkingPaymentChartRespVO getParkingPaymentChart(Long startTime, Long endTime) {
        ParkingPaymentChartRespVO chartVO = parkingPaymentMapper.selectCardStats(startTime, endTime);
        if (chartVO == null) {
            chartVO = new ParkingPaymentChartRespVO();
            chartVO.setTotalPay(0);
            chartVO.setArrearsCount(0);
            chartVO.setTotalIncome(BigDecimal.ZERO);
            chartVO.setPayRate(BigDecimal.ZERO);
        }
        chartVO.setDayIncomeList(parkingPaymentMapper.selectDayIncomeList(startTime, endTime));
        chartVO.setDayPayList(parkingPaymentMapper.selectDayPayList(startTime, endTime));
        return chartVO;
    }

}
