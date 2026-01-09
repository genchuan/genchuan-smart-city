package cn.iocoder.yudao.module.industry.service.park.pay.parkpay;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.*;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoCreateReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity.ParkDiscountActivityDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay.ParkPayDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo.ParkWoDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkdiscountactivity.ParkDiscountActivityMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkpay.ParkPayMapper;
import cn.iocoder.yudao.module.industry.service.park.pay.parkwo.ParkWoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserNickname;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 停车缴费服务 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPayServiceImpl implements ParkPayService {

    @Resource
    private ParkPayMapper parkPayMapper;

    @Resource
    private ParkDiscountActivityMapper parkDiscountActivityMapper;

    @Resource
    private ParkWoService parkWoService;

    @Override
    public Long createParkPay(ParkPaySaveReqVO createReqVO) {
        //一、补全参数
        //1. 去掉id
        createReqVO.setId(null);
        // 2. 生成 payId ，UUID 去掉“-”
        createReqVO.setPayCode(UUID.randomUUID().toString().replace("-", ""));
        //3. TODO 关联订单编号，用新生成的订单编号,目前用UUID弄的
        createReqVO.setWoNo(UUID.randomUUID().toString().replace("-", ""));
        //3. TODO 关联预约编号，是预约成功后，才有订单的，所以需要传,目前用UUID弄的
//        createReqVO.setReservationId(UUID.randomUUID().toString().replace("-", ""));
        //3. TODO 关联第三方支付编号，目前用UUID弄的
        createReqVO.setPayWoNo(UUID.randomUUID().toString().replace("-", ""));
        //4. TODO 入场时间和出场时间、支付时间
        //5. TODO 所在地区
        //6. TODO 停车场ID要关联
        //6. TODO 泊位ID要关联


        //二、1.添加入场记录-入场时间和出场时间？


        // 插入
        ParkPayDO parkPay = BeanUtils.toBean(createReqVO, ParkPayDO.class);
        parkPayMapper.insert(parkPay);
        // 返回
        return parkPay.getId();
    }

    @Override
    public void updateParkPay(ParkPaySaveReqVO updateReqVO) {
        // 校验存在
        validateParkPayExists(updateReqVO.getId());
        // 更新
        ParkPayDO updateObj = BeanUtils.toBean(updateReqVO, ParkPayDO.class);
        parkPayMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPay(Long id) {
        // 校验存在
        validateParkPayExists(id);
        // 删除
        parkPayMapper.deleteById(id);
    }

    private void validateParkPayExists(Long id) {
        if (parkPayMapper.selectById(id) == null) {
            throw exception(PARK_PAY_NOT_EXISTS);
        }
    }

    @Override
    public ParkPayDO getParkPay(Long id) {
        return parkPayMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPayDO> getParkPayPage(ParkPayPageReqVO pageReqVO) {
        //筛选包含起始-终止时间，12位地区码
        return parkPayMapper.selectPage(pageReqVO);
//        return parkPayMapper.pageParkPay(pageReqVO);
    }

    @Override
    public void pay(ParkPayPayReqVO reqVO) {
        // 1. 查询缴费记录
        ParkPayDO parkPay = parkPayMapper.selectById(reqVO.getId());
        if (parkPay == null) {
            throw exception(new ErrorCode(500,"缴费订单不存在"));
        }

        // 2. 校验支付状态（避免重复支付）
        if (!"待支付".equals(parkPay.getPayStatus())) {
            throw exception(new ErrorCode(500,"该订单不是待支付状态"));
        }

        //3. 校验支付实际金额是否为空
        if (parkPay.getActualPayAmount()==null){
            throw exception(new ErrorCode(500,"该订单无实际支付金额"));
        }

        // 3. 更新支付信息
        ParkPayDO update = new ParkPayDO();
        update.setId(parkPay.getId());
        update.setPayStatus("支付成功");
        update.setPayMethod(reqVO.getPayMethod());
//        update.setActualPayAmount(reqVO.getActualPayAmount());
        update.setPayTime(LocalDateTime.now());

        parkPayMapper.updateById(update);
    }

    @Override
    public PageResult<ParkPayDO> drillParkPay(ParkPayDrillReqVO drillReqVO) {
        // 构造 MyBatis-Plus 分页对象
        Page<ParkPayDO> page = new Page<>(drillReqVO.getPageNo(), drillReqVO.getPageSize());
        IPage<ParkPayDO> iPage = parkPayMapper.selectDrillPage(page, drillReqVO,drillReqVO.getRegionFullCode());
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    @Override
    public Long addParkPay(ParkPayAddReqVO addReqVO) {
        //一、补全参数
        //1. 去掉id

        // 2. 生成 payId ，UUID 去掉“-”
        addReqVO.setPayCode(UUID.randomUUID().toString().replace("-", ""));

        //3. TODO 关联订单编号，用新生成的订单编号,目前用UUID弄的 A:订单编号一开始是空的，后面结算的时候才生成
//        addReqVO.setWoNo(UUID.randomUUID().toString().replace("-", ""));

        //3. TODO A：预约成功会自动生成缴费记录，所以不需要这里传（因为预约成功会把预约id放到 新增缴费记录的参数里）
//        addReqVO.setReservationId(UUID.randomUUID().toString().replace("-", ""));

        //4. TODO 入场时间和出场时间、支付时间（创建只弄入场时间）
        addReqVO.setEntryTime(LocalDateTime.now());

        //5.支付状态
        addReqVO.setPayStatus("待支付");

        //6.放行状态
        addReqVO.setReleaseStatus("未放行");
        //5. TODO 所在地区。A：前端通过停车场所在地直接传入
        //6. TODO 停车场ID要关联。A：前端直接传
        //6. TODO 泊位ID要关联。A：前端直接传
//
//        String loginUserName = getLoginUserNickname();
//        System.out.println("cs2025-12-29 15:27:00:"+loginUserName);

        // 插入
        ParkPayDO parkPay = BeanUtils.toBean(addReqVO, ParkPayDO.class);
        parkPayMapper.insert(parkPay);
        // 返回
        return parkPay.getId();
    }

    /**
     * 处理车辆出场逻辑
     *
     * <p>功能：
     * 1. 根据 parkPayId 查询停车缴费记录；
     * 2. 校验出场合法性（防重复出场、入场时间存在）；
     * 3. 计算停车时长、应收金额、优惠金额和实付金额；
     * 4. 调用 ParkWoService 生成停车订单表（biz_park_wo）；
     * 5. 回填生成的订单号到缴费表；
     * 6. 更新缴费记录。
     *
     * @param exitReqVO 出场请求参数，包含 parkPayId 和可选的优惠活动ID
     * @throws ErrorCode 出现数据不存在、重复出场、优惠活动异常、订单生成失败或记录更新失败时抛出
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exitParkPay(ParkPayExitReqVO exitReqVO) {

        // 1. 根据 parkPayId 查询停车缴费记录 A
        ParkPayDO parkPayDO = parkPayMapper.selectById(exitReqVO.getParkPayId());
        if (parkPayDO == null || Boolean.TRUE.equals(parkPayDO.getDeleted())) {
            throw exception(new ErrorCode(500, "停车缴费记录不存在"));
        }

        // 防重复出场
        if (parkPayDO.getExitTime() != null) {
            throw exception(new ErrorCode(500, "车辆已出场，不能重复操作"));
        }

        // 2. 入场时间
        LocalDateTime entryTime = parkPayDO.getEntryTime();
        if (entryTime == null) {
            throw exception(new ErrorCode(500, "入场时间为空，数据异常"));
        }

        // 3. 出场时间（当前时间）
        LocalDateTime exitTime = LocalDateTime.now();

        // 4. 计算停车时长（分钟，至少 1 分钟）
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        int parkEndure = (int) Math.max(minutes, 1);

        // 5. 计算应收金额（示例：1 元 / 30 分钟）
        BigDecimal receivableAmount = calcReceivableAmount(parkEndure);

        // 6. 计算优惠金额（示例：命中一个优惠活动）
        BigDecimal discountAmount = BigDecimal.ZERO;

        //活动id不为空
        ParkDiscountActivityDO activity=null;
        if (exitReqVO.getDiscountActivityId()!=null){
            activity = parkDiscountActivityMapper.selectById(exitReqVO.getDiscountActivityId());
            if (activity==null|| !(activity.getStatus() ==1)){
                throw exception(new ErrorCode(500,"活动不存在或者不在进行中"));
            }

            discountAmount=activity.getDiscountAmount();
        }

        // 实付金额 = 应收 - 优惠（最少 0）
        BigDecimal actualPayAmount = receivableAmount.subtract(discountAmount);
        if (actualPayAmount.compareTo(BigDecimal.ZERO) < 0) {
            actualPayAmount = BigDecimal.ZERO;
        }

        // 7. 生成订单号
        String woNo = generateWoNo();

        // 8. 更新停车缴费表 A
        parkPayDO.setWoNo(woNo);
        parkPayDO.setExitTime(exitTime);
        parkPayDO.setParkEndure(parkEndure);
        parkPayDO.setReceivableAmount(receivableAmount);
        parkPayDO.setDiscountAmount(discountAmount);
        parkPayDO.setActualPayAmount(actualPayAmount);
        if (activity!=null){
            parkPayDO.setActivityName(activity.getActivityName());
        }


        // 9. 调用 ParkWoService 生成停车订单表 B（biz_park_wo）
        ParkWoCreateReqVO createReqVO = new ParkWoCreateReqVO();
        createReqVO.setParkId(parkPayDO.getParkLotId()); // 停车场id
        createReqVO.setParkName(parkPayDO.getParkLotName()); // 停车场名称
        createReqVO.setParkEndure(parkEndure); // 停车时长
        createReqVO.setReceivableAmount(receivableAmount); // 应收金额
        createReqVO.setArrearsReason(null); // 可选字段，如无欠费说明可留空

        // 调用 Service
        Long parkWoId = parkWoService.createParkWo(createReqVO);


        if (parkWoId == null) {
            throw exception(new ErrorCode(500, "生成停车订单失败"));
        }
        // 将生成的 woNo 回填到缴费表 A
        parkPayDO.setWoNo(createReqVO.getWoNo()); // ParkWoServiceImpl 内部已经生成 woNo

        int updateCount = parkPayMapper.updateById(parkPayDO);
        if (updateCount != 1) {
            throw exception(new ErrorCode(500, "更新停车缴费记录失败"));
        }

        return;
    }

    /**
     * 计算应收金额
     * 示例规则：每 30 分钟 1 元，不足按 30 分钟算
     */
    private BigDecimal calcReceivableAmount(int parkEndure) {
        int unitMinutes = 30;
        int unitPrice = 1;

        int units = (int) Math.ceil(parkEndure / (double) unitMinutes);
        return BigDecimal.valueOf(units * unitPrice);
    }

    private String generateWoNo() {
        return UUID.randomUUID().toString().replace("-","");
    }


}
