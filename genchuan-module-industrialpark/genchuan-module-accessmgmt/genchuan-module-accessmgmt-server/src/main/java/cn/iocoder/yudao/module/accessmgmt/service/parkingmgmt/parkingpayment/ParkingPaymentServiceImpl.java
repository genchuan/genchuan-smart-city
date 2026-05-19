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
 * <p>
 * 提供停车缴费的全流程业务：分页查询、账单生成、费用计算、缴费支付、发票开具、催缴、优惠减免、补缴、数据导出及态势统计。
 * 账单状态流转：待缴费 → 已缴费 / 已欠费。
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParkingPaymentServiceImpl implements ParkingPaymentService {

    @Resource
    private ParkingPaymentMapper parkingPaymentMapper;

    // ==================== 账单查询 ====================

    @Override
    public PageResult<ParkingPaymentRespVO> getParkingPaymentPage(ParkingPaymentPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 billCode(模糊)/plateNo(模糊)/billStatus(精确)/payType(精确)/invoiceStatus(精确)/payTime 范围动态条件筛选，按主键倒序
        PageResult<ParkingPaymentDO> pageResult = parkingPaymentMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, ParkingPaymentRespVO.class);
    }

    @Override
    public ParkingPaymentRespVO getParkingPayment(Long id) {
        // 按主键查单条，不存在抛 PARKING_PAYMENT_NOT_EXISTS 业务异常
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(id);
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, ParkingPaymentRespVO.class);
    }

    // ==================== 账单生成 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentGenerateRespVO generateParkingPayment(ParkingPaymentGenerateReqVO reqVO) {
        // 1. 构造 DO，写入车牌号、停车时长、费用金额
        ParkingPaymentDO entity = new ParkingPaymentDO();
        entity.setPlateNo(reqVO.getPlateNo());
        entity.setParkDuration(reqVO.getParkDuration());
        entity.setFeeAmount(reqVO.getFeeAmount());
        // 2. 初始状态：账单"待缴费"，发票"未开具"
        entity.setBillStatus("待缴费");
        entity.setInvoiceStatus("未开具");
        // 3. 生成账单编号: PP + yyyyMMddHHmmss + 4位随机数（如 PP202605181430251234）
        String billCode = "PP" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + String.format("%04d", (int) (Math.random() * 10000));
        entity.setBillCode(billCode);
        // 4. 写入数据库
        parkingPaymentMapper.insert(entity);
        // 5. 返回账单编号
        ParkingPaymentGenerateRespVO respVO = new ParkingPaymentGenerateRespVO();
        respVO.setSuccess(true);
        respVO.setBillCode(billCode);
        return respVO;
    }

    // ==================== 费用计算 ====================

    @Override
    public ParkingPaymentCalculateRespVO calculateParkingPayment(ParkingPaymentCalculateReqVO reqVO) {
        // 1. 校验账单存在
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        // 2. 计算费用明细：原始费用 → 优惠费用（默认0） → 实付费用 = 原始 - 优惠
        ParkingPaymentCalculateRespVO respVO = new ParkingPaymentCalculateRespVO();
        respVO.setOriginalFee(entity.getFeeAmount());
        BigDecimal discountFee = entity.getDiscountAmount() != null ? entity.getDiscountAmount() : BigDecimal.ZERO;
        respVO.setDiscountFee(discountFee);
        respVO.setActualFee(entity.getFeeAmount().subtract(discountFee));
        return respVO;
    }

    // ==================== 缴费支付 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentPayRespVO payParkingPayment(ParkingPaymentPayReqVO reqVO) {
        // 1. 校验账单存在
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        // 2. 记录支付方式、支付时间（当前时间），状态 → "已缴费"
        entity.setPayType(reqVO.getPayType());
        entity.setPayTime(LocalDateTime.now());
        entity.setBillStatus("已缴费");
        parkingPaymentMapper.updateById(entity);
        // 3. 返回支付链接
        ParkingPaymentPayRespVO respVO = new ParkingPaymentPayRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    // ==================== 发票管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentInvoiceRespVO invoiceParkingPayment(ParkingPaymentInvoiceReqVO reqVO) {
        // 1. 校验账单存在
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        // 2. 发票状态 → "已开具"
        entity.setInvoiceStatus("已开具");
        parkingPaymentMapper.updateById(entity);
        // 3. 返回发票链接
        ParkingPaymentInvoiceRespVO respVO = new ParkingPaymentInvoiceRespVO();
        respVO.setSuccess(true);
        respVO.setInvoiceUrl("https://invoice.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    // ==================== 催缴管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remindParkingPayment(ParkingPaymentRemindReqVO reqVO) {
        // 催缴：校验账单存在 → 状态变更为"已欠费"
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        entity.setBillStatus("已欠费");
        parkingPaymentMapper.updateById(entity);
        return true;
    }

    // ==================== 优惠减免 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean discountParkingPayment(ParkingPaymentDiscountReqVO reqVO) {
        // 优惠减免：校验账单存在 → 写入优惠金额和优惠原因
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        entity.setDiscountAmount(reqVO.getDiscountAmount());
        entity.setDiscountReason(reqVO.getDiscountReason());
        parkingPaymentMapper.updateById(entity);
        return true;
    }

    // ==================== 补缴 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingPaymentRepairRespVO repairParkingPayment(ParkingPaymentRepairReqVO reqVO) {
        // 补缴：校验账单存在 → 重新记录支付方式和支付时间 → 状态变更为"已缴费"
        ParkingPaymentDO entity = parkingPaymentMapper.selectById(reqVO.getId());
        if (entity == null) {
            throw exception(PARKING_PAYMENT_NOT_EXISTS);
        }
        entity.setPayType(reqVO.getPayType());
        entity.setPayTime(LocalDateTime.now());
        entity.setBillStatus("已缴费");
        parkingPaymentMapper.updateById(entity);
        // 返回支付链接
        ParkingPaymentRepairRespVO respVO = new ParkingPaymentRepairRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/parking/" + entity.getBillCode());
        return respVO;
    }

    // ==================== 数据导出 ====================

    @Override
    public List<ParkingPaymentRespVO> getParkingPaymentList(ParkingPaymentPageReqVO pageReqVO) {
        // 设置 PAGE_SIZE_NONE 绕过 MyBatis-Plus 分页限制，查询全量数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ParkingPaymentDO> pageResult = parkingPaymentMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), ParkingPaymentRespVO.class);
    }

    // ==================== 统计态势 ====================

    @Override
    public ParkingPaymentChartRespVO getParkingPaymentChart(Long startTime, Long endTime) {
        // 1. 查询卡片统计数据（总支付笔数/欠费笔数/总收入/缴费率），若为空则默认 0 防止 NPE
        ParkingPaymentChartRespVO chartVO = parkingPaymentMapper.selectCardStats(startTime, endTime);
        if (chartVO == null) {
            chartVO = new ParkingPaymentChartRespVO();
            chartVO.setTotalPay(0);
            chartVO.setArrearsCount(0);
            chartVO.setTotalIncome(BigDecimal.ZERO);
            chartVO.setPayRate(BigDecimal.ZERO);
        }
        // 2. 补充两个趋势维度：每日收入趋势 + 每日支付笔数趋势
        chartVO.setDayIncomeList(parkingPaymentMapper.selectDayIncomeList(startTime, endTime));
        chartVO.setDayPayList(parkingPaymentMapper.selectDayPayList(startTime, endTime));
        return chartVO;
    }

}
