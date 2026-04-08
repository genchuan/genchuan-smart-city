package cn.iocoder.yudao.module.vehiclecharging.service.abnormalorder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.PageUtil;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.NewPileAlarmRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderrefund.OrderRefundMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.abnormalorder.AbnormalOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;
import static com.github.yulichang.extension.kt.toolkit.KtWrappers.update;

/**
 * 异常订单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AbnormalOrderServiceImpl implements AbnormalOrderService {

    @Resource
    private AbnormalOrderMapper abnormalOrderMapper;

    @Resource
    private OrderRefundMapper orderRefundMapper;

    @Override
    public Long createAbnormalOrder(AbnormalOrderSaveReqVO createReqVO) {
        // 插入
        AbnormalOrderDO abnormalOrder = BeanUtils.toBean(createReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.insert(abnormalOrder);

        // 返回
        return abnormalOrder.getId();
    }

    @Override
    public void updateAbnormalOrder(AbnormalOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateAbnormalOrderExists(updateReqVO.getId());
        // 更新
        AbnormalOrderDO updateObj = BeanUtils.toBean(updateReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteAbnormalOrder(Long id) {
        // 校验存在
        validateAbnormalOrderExists(id);
        // 删除
        abnormalOrderMapper.deleteById(id);
    }

    @Override
    public void deleteAbnormalOrderListByIds(List<Long> ids) {
        // 删除
        abnormalOrderMapper.deleteByIds(ids);
    }


    private void validateAbnormalOrderExists(Long id) {
        if (abnormalOrderMapper.selectById(id) == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }
    }

    @Override
    public AbnormalOrderDO getAbnormalOrder(Long id) {
        return abnormalOrderMapper.selectById(id);
    }

    @Override
    public PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO pageReqVO) {
        return abnormalOrderMapper.selectPage(pageReqVO);
    }

    /**
     * 获取异常订单分页
     *
     * @return 异常订单分页
     */

    @Override
    public PageResult<NewAbnormalOrderRespVO> newgetAbnormalOrderPage(NewAbnormalOrderPageReqVO reqVO) {
        IPage<NewAbnormalOrderRespVO> page = abnormalOrderMapper.selectAbnormalOrderPage(
                MyBatisUtils.buildPage(reqVO),
                reqVO
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public Boolean verifyAbnormalOrder(AbnormalOrderVerifyReqVO reqVO) {
        UpdateWrapper<AbnormalOrderDO> updateWrapper = new UpdateWrapper<>();
        // 批量 ID 条件
        updateWrapper.in("id", reqVO.getIds());

        // 要更新的字段
        updateWrapper.set("abnormal_status", "已核实");
        updateWrapper.set("check_user", SecurityFrameworkUtils.getLoginUserId());
        updateWrapper.set("check_time", LocalDateTime.now());
        updateWrapper.set("verify_result", reqVO.getVerifyResult());
        updateWrapper.set("verify_remark", reqVO.getVerifyRemark());

        abnormalOrderMapper.update(null, updateWrapper);

        return true;
    }

    @Override
    public Boolean handleAbnormalOrder(AbnormalOrderHandleReqVO reqVO) {

        UpdateWrapper<AbnormalOrderDO> wrapper = new UpdateWrapper<>();
        wrapper.in("id", reqVO.getIds());
        wrapper.eq("abnormal_status", "已核实");

        wrapper.set("abnormal_status", "处理中");
        wrapper.set("handle_measure", reqVO.getHandleMeasure());
        wrapper.set("handle_time", LocalDateTime.now());

        abnormalOrderMapper.update(null, wrapper);

        return true;
    }

    @Override
    public Boolean completeAbnormalOrder(AbnormalOrderCompleteReqVO reqVO) {
        // 获取登录用户名
        Long username = SecurityFrameworkUtils.getLoginUserId();

        UpdateWrapper<AbnormalOrderDO> wrapper = new UpdateWrapper<>();
        // 条件：只允许处理 处理中 状态的数据
        wrapper.in("id", reqVO.getIds());
        wrapper.eq("abnormal_status", "处理中");

        // 更新内容
        wrapper.set("abnormal_status", "已完结");
        wrapper.set("remark", reqVO.getCompleteRemark());
        wrapper.set("updater", username);
        wrapper.set("update_time", LocalDateTime.now());

        // 执行更新
        abnormalOrderMapper.update(null, wrapper);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 事务：要么都成功，要么都回滚
    public Boolean refundAbnormalOrder(AbnormalOrderRefundReqVO reqVO) {
        // 1. 查询异常订单信息
        AbnormalOrderDO abnormalOrder = abnormalOrderMapper.selectById(reqVO.getId());
        if (abnormalOrder == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }

        // 2. 状态校验：仅 已核实 / 处理中 可以退款
        String status = abnormalOrder.getAbnormalStatus();
        if (!"已核实".equals(status) && !"处理中".equals(status)) {
            throw exception(ORDER_ALARM_STATUS_ERROR);
        }

        // 3. 获取当前登录用户
        Long username = SecurityFrameworkUtils.getLoginUserId();

        // ======================
        // 4. 更新异常订单表
        // ======================
        UpdateWrapper<AbnormalOrderDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", reqVO.getId());
        updateWrapper.set("refund_amount", reqVO.getRefundAmount());
        updateWrapper.set("updater", username);
        updateWrapper.set("abnormal_status", "已完结");
        updateWrapper.set("refund_reason",reqVO.getRefundReason());
        abnormalOrderMapper.update(null, updateWrapper);

        // ======================
        // 5. 生成退款申请记录（插入 order_refund 表）
        // ======================
        OrderRefundDO refund = new OrderRefundDO();
        refund.setRefundCode("REF" + System.currentTimeMillis()); // 退款单号
        refund.setOrderCode(abnormalOrder.getOrderCode());       // 关联订单号
        refund.setRefundAmount(reqVO.getRefundAmount());         // 退款金额
        refund.setRefundReason(reqVO.getRefundReason());         // 退款原因
        refund.setRefundStatus("待审核");                        // 状态：待审核
        refund.setCreator(String.valueOf(username));
        // 插入退款表
        orderRefundMapper.insert(refund);

        return true;
    }

    @Override
    public void updateAbnormalOrderRemark(AbnormalOrderRemarkReqVO reqVO) {
        // 1. 校验数据是否存在
        AbnormalOrderDO order = abnormalOrderMapper.selectById(reqVO.getId());
        if (order == null) {
            throw exception(ORDER_NOT_EXISTS);
        }

        // 2. 只更新备注字段
        AbnormalOrderDO updateObj = new AbnormalOrderDO();
        updateObj.setId(reqVO.getId());
        updateObj.setRemark(reqVO.getRemark());

        // 3. 执行更新
        abnormalOrderMapper.updateById(updateObj);
    }

    @Override
    public AbnormalOrderChartRespVO getAbnormalOrderChart(AbnormalOrderChartReqVO reqVO) {
        // 1. 时间处理（自动解析近7天/30天/本月）
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (reqVO.getTimeRange() != null) {
            switch (reqVO.getTimeRange()) {
                case "近7天":
                    startTime = LocalDateTime.now().minusDays(7);
                    endTime = LocalDateTime.now();
                    break;
                case "近30天":
                    startTime = LocalDateTime.now().minusDays(30);
                    endTime = LocalDateTime.now();
                    break;
                case "本月":
                    startTime = LocalDateTime.now().withDayOfMonth(1);
                    endTime = LocalDateTime.now();
                    break;
                default:
                    // 自定义时间
                    startTime = LocalDateTimeUtil.parse(reqVO.getStartTime() + " 00:00:00");
                    endTime = LocalDateTimeUtil.parse(reqVO.getEndTime() + " 23:59:59");
            }
        }

        // 2. 组装查询条件
        LambdaQueryWrapper<AbnormalOrderDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AbnormalOrderDO::getDeleted, 0);
        if (startTime != null && endTime != null) {
            wrapper.between(AbnormalOrderDO::getCreateTime, startTime, endTime);
        }

        List<AbnormalOrderDO> list = abnormalOrderMapper.selectList(wrapper);

        // 3. 构建返回结果
        AbnormalOrderChartRespVO resp = new AbnormalOrderChartRespVO();

        // 3.1 柱状图：按天统计
        resp.setBarData(buildBarData(list));

        // 3.2 饼图：按异常类型统计
        resp.setPieData(buildPieData(list));

        // 3.3 卡片统计
        resp.setCardData(buildCardData(list));

        return resp;
    }

    @Override
    public List<AbnormalOrderDailyCountRespVO> getAbnormalOrderDailyCount(AbnormalOrderDailyCountReqVO reqVO) {
        return abnormalOrderMapper.selectDailyCountByStation(
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getStationId()
        );
    }

    @Override
    public List<AbnormalOrderTypeRatioRespVO> getAbnormalOrderTypeRatio(AbnormalOrderDailyCountReqVO reqVO) {
        return abnormalOrderMapper.selectTypeRatio(
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getStationId()
        );
    }

    @Override
    public AbnormalOrderHandleCountRespVO getAbnormalOrderHandleCount(AbnormalOrderDailyCountReqVO reqVO) {
        return abnormalOrderMapper.selectHandleCount(
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getStationId()
        );
    }

// ==================== 内部工具方法 ====================

    // 柱状图：按日期分组
    private List<AbnormalOrderChartRespVO.BarData> buildBarData(List<AbnormalOrderDO> list) {
        Map<String, List<AbnormalOrderDO>> dateMap = list.stream()
                .collect(Collectors.groupingBy(o -> LocalDateTimeUtil.format(o.getCreateTime(), "yyyy-MM-dd")));

        return dateMap.entrySet().stream().map(entry -> {
                    String date = entry.getKey();
                    List<AbnormalOrderDO> dayList = entry.getValue();

                    AbnormalOrderChartRespVO.BarData bar = new AbnormalOrderChartRespVO.BarData();
                    bar.setDate(date);
                    bar.setAbnormalCount(dayList.size());
                    bar.setHandleCount((int) dayList.stream()
                            .filter(o -> "已完结".equals(o.getAbnormalStatus())).count());
                    return bar;
                }).sorted(Comparator.comparing(AbnormalOrderChartRespVO.BarData::getDate))
                .collect(Collectors.toList());
    }

    // 饼图：按异常类型分组
    private List<AbnormalOrderChartRespVO.PieData> buildPieData(List<AbnormalOrderDO> list) {
        Map<String, Long> typeMap = list.stream()
                .collect(Collectors.groupingBy(AbnormalOrderDO::getAbnormalType, Collectors.counting()));

        return typeMap.entrySet().stream().map(entry -> {
            AbnormalOrderChartRespVO.PieData pie = new AbnormalOrderChartRespVO.PieData();
            pie.setName(entry.getKey());
            pie.setValue(entry.getValue().intValue());
            return pie;
        }).collect(Collectors.toList());
    }

    // 卡片统计：总数、未处理、已处理、完成率
    private AbnormalOrderChartRespVO.CardData buildCardData(List<AbnormalOrderDO> list) {
        AbnormalOrderChartRespVO.CardData card = new AbnormalOrderChartRespVO.CardData();

        int total = list.size();
        int handleCount = (int) list.stream().filter(o -> "已完结".equals(o.getAbnormalStatus())).count();
        int unHandleCount = total - handleCount;
        double ratio = total == 0 ? 0 : BigDecimal.valueOf(handleCount)
                .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100)).doubleValue();

        card.setTotalAbnormalCount(total);
        card.setHandleCount(handleCount);
        card.setUnHandleCount(unHandleCount);
        card.setHandleRatio(ratio);

        return card;
    }
}