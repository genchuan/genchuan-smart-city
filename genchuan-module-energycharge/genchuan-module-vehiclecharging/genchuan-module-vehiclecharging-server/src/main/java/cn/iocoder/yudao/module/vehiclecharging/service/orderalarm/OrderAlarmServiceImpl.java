package cn.iocoder.yudao.module.vehiclecharging.service.orderalarm;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderalarm.OrderAlarmMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 订单告警 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class OrderAlarmServiceImpl implements OrderAlarmService {

    @Resource
    private OrderAlarmMapper orderAlarmMapper;

    @Override
    public Long createOrderAlarm(OrderAlarmSaveReqVO createReqVO) {
        // 插入
        OrderAlarmDO orderAlarm = BeanUtils.toBean(createReqVO, OrderAlarmDO.class);
        orderAlarmMapper.insert(orderAlarm);

        // 返回
        return orderAlarm.getId();
    }

    @Override
    public void updateOrderAlarm(OrderAlarmSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderAlarmExists(updateReqVO.getId());
        // 更新
        OrderAlarmDO updateObj = BeanUtils.toBean(updateReqVO, OrderAlarmDO.class);
        orderAlarmMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderAlarm(Long id) {
        // 校验存在
        validateOrderAlarmExists(id);
        // 删除
        orderAlarmMapper.deleteById(id);
    }

    @Override
        public void deleteOrderAlarmListByIds(List<Long> ids) {
        // 删除
        orderAlarmMapper.deleteByIds(ids);
        }


    private void validateOrderAlarmExists(Long id) {
        if (orderAlarmMapper.selectById(id) == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }
    }

    @Override
    public OrderAlarmDO getOrderAlarm(Long id) {
        return orderAlarmMapper.selectById(id);
    }

    @Override
    public PageResult<OrderAlarmDO> getOrderAlarmPage(OrderAlarmPageReqVO pageReqVO) {
        return orderAlarmMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void verifyOrderAlarm(OrderAlarmVerifyReqVO verifyReqVO) {
        for (Long id : verifyReqVO.getIds()) {
            // 1. 校验告警是否存在并获取告警对象
            OrderAlarmDO orderAlarm = getOrderAlarm(id);
            if (orderAlarm == null) {
                throw exception(ORDER_ALARM_NOT_EXISTS);
            }

            // 2. 校验告警状态是否为"未核实"（状态值 0）
            if (!"0".equals(orderAlarm.getAlarmStatus())) {
                // 如果状态不是0，抛出业务异常
                throw exception("校验告警状态不是为“未核实”");
            }

            // 3. 构建更新对象
            OrderAlarmDO updateObj = new OrderAlarmDO();
            updateObj.setId(id);
            // 3.1 更新告警状态为"已核实"（状态值 1）
            updateObj.setAlarmStatus("1");
            // 3.2 更新核实结果
            updateObj.setVerifyResult(verifyReqVO.getVerifyResult());

            // 4. 执行更新
            orderAlarmMapper.updateById(updateObj);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleOrderAlarm(OrderAlarmHandleReqVO handleReqVO) {
        for (Long id : handleReqVO.getIds()) {
            // 1. 校验告警是否存在并获取告警对象
            OrderAlarmDO orderAlarm = getOrderAlarm(id);
            if (orderAlarm == null) {
                throw exception(ORDER_ALARM_NOT_EXISTS);
            }

            // 2. 校验告警状态是否为"已核实"（状态值 1）
            if (!"1".equals(orderAlarm.getAlarmStatus())) {
                // 如果状态不是1，抛出业务异常
                throw exception("校验告警状态不是为“已核实”");
            }

            // 3. 构建更新对象
            OrderAlarmDO updateObj = new OrderAlarmDO();
            updateObj.setId(id);
            // 3.1 更新告警状态为"处理中"（状态值 2）
            updateObj.setAlarmStatus("2");
            // 3.2 更新处理措施
            updateObj.setHandleMeasure(handleReqVO.getHandleMeasure());
            // 3.3 更新处理时间为当前时间
            updateObj.setHandleTime(LocalDateTime.now());

            // 4. 执行更新
            orderAlarmMapper.updateById(updateObj);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOrderAlarm(OrderAlarmCompleteReqVO completeReqVO) {
        for (Long id : completeReqVO.getIds()) {
            // 1. 校验告警是否存在并获取告警对象
            OrderAlarmDO orderAlarm = getOrderAlarm(id);
            if (orderAlarm == null) {
                throw exception(ORDER_ALARM_NOT_EXISTS);
            }

            // 2. 校验告警状态是否为"处理中"（状态值 2）
            if (!"2".equals(orderAlarm.getAlarmStatus())) {
                // 如果状态不是2，抛出业务异常
                throw exception("校验告警状态不是为“处理中”");
            }

            // 3. 构建更新对象
            OrderAlarmDO updateObj = new OrderAlarmDO();
            updateObj.setId(id);
            // 3.1 更新告警状态为"已完结"（状态值 3）
            updateObj.setAlarmStatus("3");

            // 4. 执行更新
            orderAlarmMapper.updateById(updateObj);
        }
    }

    @Override
    public OrderAlarmChartRespVO getOrderAlarmChartData(OrderAlarmChartReqVO reqVO) {

        // 1. 查询图表统计数据
        Map<String, Object> chartData = orderAlarmMapper.selectChartData(reqVO.getStartTime(), reqVO.getEndTime());

        // 2. 查询折线图数据
        List<Map<String, Object>> lineDataList = orderAlarmMapper.selectLineChartData(reqVO.getStartTime(), reqVO.getEndTime());

        // 3. 查询饼图数据
        List<Map<String, Object>> pieDataList = orderAlarmMapper.selectPieChartData(reqVO.getStartTime(), reqVO.getEndTime());

        // 4. 构建响应对象
        OrderAlarmChartRespVO respVO = new OrderAlarmChartRespVO();

        // 4.1 设置基本统计数据 - 添加空值检查
        Integer totalCount = chartData.get("total_count") != null ? ((Number) chartData.get("total_count")).intValue() : 0;
        Integer handledCount = chartData.get("handled_count") != null ? ((Number) chartData.get("handled_count")).intValue() : 0;
        respVO.setTotalCount(totalCount);
        respVO.setHandledCount(handledCount);

        // 计算处理率
        if (totalCount > 0) {
            BigDecimal handleRate = BigDecimal.valueOf(handledCount)
                    .divide(BigDecimal.valueOf(totalCount), 4, BigDecimal.ROUND_HALF_UP)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            respVO.setHandleRate(handleRate);
        } else {
            respVO.setHandleRate(BigDecimal.ZERO);
        }

        // 4.2 设置折线图数据 - 添加空值检查
        List<OrderAlarmChartRespVO.LineData> lineData = new ArrayList<>();
        for (Map<String, Object> item : lineDataList) {
            OrderAlarmChartRespVO.LineData lineItem = new OrderAlarmChartRespVO.LineData();
            lineItem.setDate(item.get("date") != null ? item.get("date").toString() : "");
            lineItem.setAlarmCount(item.get("alarm_count") != null ? ((Number) item.get("alarm_count")).intValue() : 0);
            lineItem.setHandleCount(item.get("handle_count") != null ? ((Number) item.get("handle_count")).intValue() : 0);
            lineData.add(lineItem);
        }
        respVO.setLineData(lineData);

        // 4.3 设置饼图数据 - 添加空值检查
        List<OrderAlarmChartRespVO.PieData> pieData = new ArrayList<>();
        for (Map<String, Object> item : pieDataList) {
            OrderAlarmChartRespVO.PieData pieItem = new OrderAlarmChartRespVO.PieData();
            pieItem.setName(item.get("name") != null ? (String) item.get("name") : "未知");
            pieItem.setValue(item.get("value") != null ? ((Number) item.get("value")).intValue() : 0);
            pieData.add(pieItem);
        }
        respVO.setPieData(pieData);

        // 4.4 设置卡片数据 - 添加空值检查
        OrderAlarmChartRespVO.CardData cardData = new OrderAlarmChartRespVO.CardData();
        cardData.setUnVerifyCount(chartData.get("unverify_count") != null ? ((Number) chartData.get("unverify_count")).intValue() : 0);
        cardData.setVerifiedCount(chartData.get("verified_count") != null ? ((Number) chartData.get("verified_count")).intValue() : 0);
        cardData.setHandlingCount(chartData.get("handling_count") != null ? ((Number) chartData.get("handling_count")).intValue() : 0);
        cardData.setCompletedCount(chartData.get("completed_count") != null ? ((Number) chartData.get("completed_count")).intValue() : 0);
        respVO.setCardData(cardData);

        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remarkOrderAlarm(OrderAlarmRemarkReqVO remarkReqVO) {
        // 1. 校验告警是否存在
        OrderAlarmDO orderAlarm = getOrderAlarm(remarkReqVO.getId());
        if (orderAlarm == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }

        // 2. 构建更新对象
        OrderAlarmDO updateObj = new OrderAlarmDO();
        updateObj.setId(remarkReqVO.getId());
        updateObj.setRemark(remarkReqVO.getRemark());

        // 3. 执行更新
        orderAlarmMapper.updateById(updateObj);
    }
}