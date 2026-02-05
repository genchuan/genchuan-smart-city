package cn.iocoder.yudao.module.park.service.park.order.ordertemp;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempGenerateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountRespVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.ordertemp.OrderTempMapper;
import cn.iocoder.yudao.module.park.dal.mysql.park.resource.inputcar.ParkInputCarMapper;
import cn.iocoder.yudao.module.park.service.park.resource.inputcar.ParkInputCarService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 临停订单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OrderTempServiceImpl implements OrderTempService {

    @Resource
    private OrderTempMapper orderTempMapper;
    //录入车辆信息（即车辆入出场记录）
    @Resource
    private ParkInputCarMapper parkInputCarMapper;

//    @Resource
//    private ParkLotMapper parkLotMapper;

    @Override
    public Long createOrderTemp(OrderTempSaveReqVO createReqVO) {
        // 插入
        OrderTempDO orderTemp = BeanUtils.toBean(createReqVO, OrderTempDO.class);
        orderTempMapper.insert(orderTemp);
        // 返回
        return orderTemp.getId();
    }

    @Override
    public void updateOrderTemp(OrderTempSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderTempExists(updateReqVO.getId());
        // 更新
        OrderTempDO updateObj = BeanUtils.toBean(updateReqVO, OrderTempDO.class);
        orderTempMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderTemp(Long id) {
        // 校验存在
        validateOrderTempExists(id);
        // 删除
        orderTempMapper.deleteById(id);
    }

    private void validateOrderTempExists(Long id) {
        if (orderTempMapper.selectById(id) == null) {
            throw exception(ORDER_TEMP_NOT_EXISTS);
        }
    }

    @Override
    public OrderTempDO getOrderTemp(Long id) {
        return orderTempMapper.selectById(id);
    }

    @Override
    public PageResult<OrderTempDO> getOrderTempPage(OrderTempPageReqVO pageReqVO) {
        return orderTempMapper.selectPage(pageReqVO);
    }

    /**
     * 生成临停订单（临时停车订单）
     *
     * <p>流程：
     * 1. 根据请求参数 parkInputCarId 获取车辆入场/出场信息；
     * 2. 计算停车时长，并扣除免费时长得到可计费时间；
     * 3. 根据收费策略计算应收金额，并进行封顶处理；
     * 4. 构造 OrderTempDO 实体对象，设置各字段；
     * 5. 插入数据库并返回生成的订单主键。
     *
     * <p>注意：
     * - 策略 ID 通过车场绑定配置获取，而非请求参数；
     * - 订单状态和支付状态直接使用字符串，不使用枚举；
     * - 金额统一保留两位小数，避免浮点误差。
     *
     * @param reqVO 临停订单生成请求对象，包含车牌、车场ID、泊位ID、parkInputCarId、备注等信息
     * @return 返回生成的临停订单主键ID
     * @throws ServiceException 当车辆出入场信息不存在或时间异常时抛出
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Long generateOrderTemp(OrderTempGenerateReqVO reqVO) {
//
//
//        //一、从请求获取参数、配置
//        //1.获取入场出场时间：   通过 parkInputCarId 获取 车辆出入场记录信息从而得到
//        Long parkInputCarId = reqVO.getParkInputCarId();
//        ParkInputCarDO parkInputCarDO = parkInputCarMapper.selectById(parkInputCarId);
//        if (parkInputCarDO==null){
//            throw  exception(new ErrorCode(500,"对应出入场信息不能为空"));
//        }
//        LocalDateTime entryTime = parkInputCarDO.getEntryTime();
//        LocalDateTime exitTime = parkInputCarDO.getExitTime();
//
//        //2.计算得到停放时长
//        if (entryTime == null || exitTime == null) {
//            throw exception(new ErrorCode(500, "入场时间或出场时间不能为空"));
//        }
//
//        if (exitTime.isBefore(entryTime)) {
//            throw exception(new ErrorCode(500, "出场时间不能早于入场时间"));
//        }
//
//        Duration duration = Duration.between(entryTime, exitTime); // exitTime - entryTime
//        long minutes = duration.toMinutes(); // 得到分钟数
//
//        Integer parkingMinutes = (int) minutes; // 转成 Integer 类型
//
//        //3. TODO 通过车场Id获得策略Id(暂时写死）
//        Long feeStrategyId = 1L;
//
//
//        //4.TODO 获取每日免费时间（分钟）：通过车场Id获取车场信息，通过车场信息的策略Id获取策略信息，通过策略信息获取免费停车时长(分钟)
//        Integer freeDurationMinutes = 60;   //免费分钟
//
//        //二、业务
//        //1.计算收费时间： 停车时间-免费时间
//        Integer chargeableMinutes = parkingMinutes - freeDurationMinutes;
//        if (chargeableMinutes < 0) {
//            chargeableMinutes = 0;
//        }
//
//        //2.计算应收金额
//        BigDecimal originalAmount=BigDecimal.ZERO;//原始金额
//
//        //2.1解析收费策略 TODO 通过收费策略Id解析收费策略
//        //2.2.获取收费规则参数
//        Integer chargingUnitMinute = 60; //收费单位时间（分钟），比如30为30分钟为一个收费时间单位
//        Integer chargingUnitAmount = 10; //每个续费单位的价格（元）
//        Integer firstChargingUnitAmount = 20; //首付单位的价格（元）
//
//        Integer maxDailyChargeAmount = 100; //单日最高收费（元）
//
//        //2.3、计算收费金额-基于收费策略的计算
//        //获取收费单位个数
//        // 根据“可计费时长”计算收费单位数和应收金额
//        // 规则：
//        // 1. 可计费时长 <= 0 ：仍在免费时长内，不收费
//        // 2. 可计费时长 > 0 ：
//        //    - 按收费单位向上取整（不足一个单位按一个算）
//        //    - 收费 = 首费 + (收费单位数 - 1) * 续费单价
//        int chargingUnit ;
//        if (chargeableMinutes <= 0) {
//            chargingUnit = 0;
//            originalAmount = BigDecimal.ZERO;
//        } else {
//            chargingUnit = (int) Math.ceil((double) chargeableMinutes / chargingUnitMinute);
//            originalAmount = BigDecimal.valueOf(
//                    firstChargingUnitAmount + chargingUnitAmount * (chargingUnit - 1)
//            );
//        }
//
//        //封顶判断
//        if (originalAmount.compareTo(BigDecimal.valueOf(maxDailyChargeAmount))>0){
//            originalAmount= BigDecimal.valueOf(maxDailyChargeAmount);
//        }
//        // 统一金额精度，避免小数误差
//        originalAmount = originalAmount.setScale(2, RoundingMode.HALF_UP);
//
//
//        // 三 、构造数据库插入实体DO
//        OrderTempDO insertOrderTempDO = new OrderTempDO();
//
//// ===== 来自请求参数 =====
//        insertOrderTempDO.setCarNumber(reqVO.getCarNumber());
//        insertOrderTempDO.setLotId(reqVO.getLotId());
//        insertOrderTempDO.setSpaceId(reqVO.getSpaceId());
//        insertOrderTempDO.setParkInputCarId(reqVO.getParkInputCarId());
//        insertOrderTempDO.setRemark(reqVO.getRemark());
//
//// ===== 策略Id来自车场绑定配置（非请求）=====
//        insertOrderTempDO.setFeeStrategyId(feeStrategyId);
//
//// ===== 来自 park_input_car =====
//        insertOrderTempDO.setEntryTime(entryTime);
//        insertOrderTempDO.setExitTime(exitTime);
//        insertOrderTempDO.setParkingDuration(parkingMinutes);
//
//// ===== 金额相关（计算得出）=====
//        insertOrderTempDO.setOriginalAmount(originalAmount);
//        insertOrderTempDO.setDiscountAmount(BigDecimal.ZERO);   // 初始无优惠
//        insertOrderTempDO.setPayAmount(originalAmount);         // 初始实付 = 应收
//
//// ===== 状态字段（直接字符串，不用枚举）=====
//        insertOrderTempDO.setOrderStatus("待支付"); // 待支付
//        insertOrderTempDO.setPayStatus("未支付");     // 未支付
//
//// ===== 支付相关（生成订单时为空）=====
//        insertOrderTempDO.setPayType(null);
//        insertOrderTempDO.setPaymentId(null);
//
//// 插入数据库
//        orderTempMapper.insert(insertOrderTempDO);
//
//// 返回主键
//        return insertOrderTempDO.getId();
//
//    }



    //大致逻辑同上，修复了每日封顶bug：比如我2026-02-01 08:00:00进场，2026-02-05 08:00:00出场，只算一天封顶钱
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long generateOrderTemp(OrderTempGenerateReqVO reqVO) {
        // 一、获取基础数据
        //1.获取入场出场时间
        Long parkInputCarId = reqVO.getParkInputCarId();
        ParkInputCarDO parkInputCarDO = parkInputCarMapper.selectById(parkInputCarId);
        if (parkInputCarDO == null) {
            throw exception(new ErrorCode(500, "对应出入场信息不能为空"));
        }

        LocalDateTime entryTime = parkInputCarDO.getEntryTime();
        LocalDateTime exitTime = parkInputCarDO.getExitTime();

        if (entryTime == null || exitTime == null) {
            throw exception(new ErrorCode(500, "入场时间或出场时间不能为空"));
        }
        if (exitTime.isBefore(entryTime)) {
            throw exception(new ErrorCode(500, "出场时间不能早于入场时间"));
        }

        //2. TODO 通过车场Id获得策略Id(暂时写死）
        Long feeStrategyId = 1L;

        // 二、按天计算停车费用
        BigDecimal totalAmount = calculateDailyParkingFee(entryTime, exitTime,feeStrategyId);

        // 三、构造订单实体
        OrderTempDO insertOrderTempDO = new OrderTempDO();
        insertOrderTempDO.setCarNumber(reqVO.getCarNumber());
        insertOrderTempDO.setLotId(reqVO.getLotId());
        insertOrderTempDO.setSpaceId(reqVO.getSpaceId());
        insertOrderTempDO.setParkInputCarId(reqVO.getParkInputCarId());
        insertOrderTempDO.setRemark(reqVO.getRemark());
        insertOrderTempDO.setFeeStrategyId(1L); // TODO: 从配置获取

        insertOrderTempDO.setEntryTime(entryTime);
        insertOrderTempDO.setExitTime(exitTime);

        // 计算总停车分钟数
        long totalMinutes = Duration.between(entryTime, exitTime).toMinutes();
        insertOrderTempDO.setParkingDuration((int) totalMinutes);

        // 金额相关
        insertOrderTempDO.setOriginalAmount(totalAmount);
        insertOrderTempDO.setDiscountAmount(BigDecimal.ZERO);
        insertOrderTempDO.setPayAmount(totalAmount);

        // 状态字段
        insertOrderTempDO.setOrderStatus("待支付");
        insertOrderTempDO.setPayStatus("未支付");

        // 插入数据库
        orderTempMapper.insert(insertOrderTempDO);
        return insertOrderTempDO.getId();
    }

    /**
     * 按天计算停车费用（每天都有免费时长）
     */
    private BigDecimal calculateDailyParkingFee(LocalDateTime entryTime, LocalDateTime exitTime,Long feeStrategyId) {
        // TODO: 从配置获取这些参数(后面从策略id-feeStrategyId配置参数）
        Integer freeDurationMinutes = 60;        // 每天免费时长（分钟）
        Integer chargingUnitMinute = 60;        // 收费单位时间（分钟）
        Integer chargingUnitAmount = 10;        // 续费单位价格（元）
        Integer firstChargingUnitAmount = 10;   // 首单位价格（元）
        Integer maxDailyChargeAmount = 100;     // 单日最高收费（元）

        BigDecimal totalAmount = BigDecimal.ZERO;

        LocalDateTime currentDayStart = entryTime;

        while (currentDayStart.isBefore(exitTime)) {
            // 确定当前计算天
            LocalDate currentDate = currentDayStart.toLocalDate();

            // 计算当天的开始和结束时间
            LocalDateTime currentDayEndOfDay = currentDate.plusDays(1).atStartOfDay(); // 第二天00:00
            if (currentDayEndOfDay.isAfter(exitTime)) {
                currentDayEndOfDay = exitTime;
            }

            // 计算当天实际停车分钟数
            long minutesInDay = Duration.between(currentDayStart, currentDayEndOfDay).toMinutes();

            // 计算当天费用（每天都有免费时长）
            BigDecimal dayAmount = calculateFeeForOneDay(
                    minutesInDay,
                    freeDurationMinutes,
                    chargingUnitMinute,
                    chargingUnitAmount,
                    firstChargingUnitAmount,
                    maxDailyChargeAmount
            );

            totalAmount = totalAmount.add(dayAmount);

            // 移动到下一天
            currentDayStart = currentDayEndOfDay;
        }

        return totalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算单天停车费用（每天都有免费时长）
     */
    private BigDecimal calculateFeeForOneDay(
            long minutesInDay,
            Integer freeDurationMinutes,
            Integer chargingUnitMinute,
            Integer chargingUnitAmount,
            Integer firstChargingUnitAmount,
            Integer maxDailyChargeAmount
    ) {
        // 每天扣除免费时长
        long chargeableMinutes = Math.max(0, minutesInDay - freeDurationMinutes);

        if (chargeableMinutes <= 0) {
            return BigDecimal.ZERO;
        }

        // 计算收费单位数
        int chargingUnit = (int) Math.ceil((double) chargeableMinutes / chargingUnitMinute);

        // 计算原始金额
        BigDecimal amount;
        if (chargingUnit == 1) {
            // 不超过一个收费单位，按首单位价格计算
            amount = BigDecimal.valueOf(firstChargingUnitAmount);
        } else {
            // 多个收费单位
            amount = BigDecimal.valueOf(
                    firstChargingUnitAmount + chargingUnitAmount * (chargingUnit - 1)
            );
        }

        // 单日封顶
        if (amount.compareTo(BigDecimal.valueOf(maxDailyChargeAmount)) > 0) {
            amount = BigDecimal.valueOf(maxDailyChargeAmount);
        }

        return amount;
    }
}
