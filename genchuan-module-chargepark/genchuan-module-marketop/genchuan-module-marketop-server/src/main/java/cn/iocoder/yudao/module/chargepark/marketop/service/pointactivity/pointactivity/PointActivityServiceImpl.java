package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointActivityStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

@Service
@Validated
public class PointActivityServiceImpl implements PointActivityService {

    @Resource
    private PointActivityMapper pointActivityMapper;

    @Resource
    private PointLotteryMapper pointLotteryMapper;

    @Override
    public PageResult<PointActivityDO> getPage(PointActivityPageReqVO reqVO) {
        return pointActivityMapper.selectPage(reqVO);
    }

    @Override
    public PointActivityDO get(Long id) {
        return pointActivityMapper.selectById(id);
    }

    @Override
    @LogRecord(type = POINT_ACTIVITY_TYPE, subType = POINT_ACTIVITY_CREATE_SUB_TYPE, bizNo = "{{#pointActivity.id}}",
            success = POINT_ACTIVITY_CREATE_SUCCESS)
    public Long create(PointActivityCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        // 时间戳转换为 LocalDateTime
        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(reqVO.getStartTime()), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(reqVO.getEndTime()), ZoneId.systemDefault());
        // 校验时间合法性
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("开始时间不能晚于结束时间");
        }
        PointActivityDO pointActivity = BeanUtils.toBean(reqVO, PointActivityDO.class);
        pointActivity.setStartTime(startTime);
        pointActivity.setEndTime(endTime);
        pointActivity.setStatus(PointActivityStatusEnum.PENDING.getValue()); // 待生效
        pointActivity.setJoinCount(0);
        pointActivity.setRemainPoint(reqVO.getRemainPoint());
        pointActivityMapper.insert(pointActivity);
        // 记录操作日志上下文
        LogRecordContext.putVariable("pointActivity", pointActivity);
        return pointActivity.getId();
    }

    @Override
    @LogRecord(type = POINT_ACTIVITY_TYPE, subType = POINT_ACTIVITY_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = POINT_ACTIVITY_UPDATE_SUCCESS)
    public void update(PointActivityUpdateReqVO reqVO) {
        PointActivityDO pointActivityDO = validateExists(reqVO.getId());
        PointActivityDO updateObj = BeanUtils.toBean(reqVO, PointActivityDO.class);
        pointActivityMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(pointActivityDO, PointActivityUpdateReqVO.class));
        LogRecordContext.putVariable("pointActivity", updateObj);
    }

    @Override
    @LogRecord(type = POINT_ACTIVITY_TYPE, subType = POINT_ACTIVITY_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = POINT_ACTIVITY_ENABLE_SUCCESS)
    public void enable(Long id) {
        PointActivityDO pointActivity = validateExists(id);
//        if (!PointActivityStatusEnum.PAUSED.getValue().equals(pointActivity.getStatus())) { // 暂停中
//            throw exception(POINT_ACTIVITY_STATUS_ERROR);
//        }
        pointActivity.setStatus(PointActivityStatusEnum.IN_PROGRESS.getValue()); // 进行中
        pointActivity.setAuditTime(LocalDateTime.now());
        // auditorId 由 Controller 层通过 SecurityFrameworkUtils 获取后设置
        pointActivityMapper.updateById(pointActivity);
        // 记录操作日志上下文
        LogRecordContext.putVariable("pointActivityName", pointActivity.getName());
    }

    @Override
    @LogRecord(type = POINT_ACTIVITY_TYPE, subType = POINT_ACTIVITY_PAUSE_SUB_TYPE, bizNo = "{{#id}}",
            success = POINT_ACTIVITY_PAUSE_SUCCESS)
    public void pause(Long id) {
        PointActivityDO pointActivity = validateExists(id);
//        if (Objects.equals(PointActivityStatusEnum.IN_PROGRESS.getValue(), pointActivity.getStatus())) { // 进行中
//            throw exception(POINT_ACTIVITY_STATUS_ERROR);
//        }
        pointActivity.setStatus(PointActivityStatusEnum.PAUSED.getValue()); // 已暂停
        pointActivityMapper.updateById(pointActivity);
        // 记录操作日志上下文
        LogRecordContext.putVariable("pointActivityName", pointActivity.getName());
    }

    @Override
    public PointActivityChartRespVO getChart() {
        // 活动总数
        Long activityCount = pointActivityMapper.selectCountByChart();
        // 累计参与用户数 = join_count 相加
        Long userCount = pointActivityMapper.selectSumJoinCount();
        // 按类型分组的数量
        List<Map<String, Object>> typeCountList = pointActivityMapper.selectTypeCountList();
        List<PointActivityChartRespVO.TypeCountItem> typeItems = typeCountList.stream()
                .map(map -> {
                    PointActivityChartRespVO.TypeCountItem item = new PointActivityChartRespVO.TypeCountItem();
                    item.setType((String) map.get("type"));
                    item.setCount(((Number) map.get("count")).intValue());
                    return item;
                }).toList();

        // 近30天 point_lottery 按天统计记录数，date 使用 startTime
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> lotteryByDay = pointLotteryMapper.selectCountByDay(startTime);
        // 构建近30天每日数据，补全缺失日期
        Map<String, Integer> dayCountMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            dayCountMap.put(today.minusDays(i).toString(), 0);
        }
        for (Map<String, Object> row : lotteryByDay) {
            String date = row.get("date").toString();
            int count = ((Number) row.get("count")).intValue();
            dayCountMap.put(date, count);
        }
        List<PointActivityChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            PointActivityChartRespVO.TrendItem item = new PointActivityChartRespVO.TrendItem();
            item.setDate(entry.getKey());
            item.setCount(entry.getValue());
            trendList.add(item);
        }

        PointActivityChartRespVO respVO = new PointActivityChartRespVO();
        respVO.setActivityCount(activityCount.intValue());
        respVO.setUserCount(userCount.intValue());
        respVO.setTrendList(trendList);
        respVO.setTypeCountList(typeItems);
        return respVO;
    }

    @Override
    public void importPointActivityList(List<PointActivityImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (PointActivityImportExcelVO excelVO : list) {
            // 校验名称唯一
            validateNameUnique(null, excelVO.getName());
            // 解析时间
            LocalDateTime startTime = LocalDateTime.parse(excelVO.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(excelVO.getEndTime(), formatter);
            // 校验时间合法性
            if (startTime.isAfter(endTime)) {
                throw new IllegalArgumentException("活动[" + excelVO.getName() + "]的开始时间不能晚于结束时间");
            }
            // 转换并保存
            PointActivityDO pointActivity = PointActivityDO.builder()
                    .name(excelVO.getName())
                    .type(excelVO.getType())
                    .startTime(startTime)
                    .endTime(endTime)
                    .rule(excelVO.getRule())
                    .description(excelVO.getDescription())
                    .stationIds(excelVO.getStationIds())
                    .status("1") // 待生效
                    .joinCount(0)
                    .remainPoint(0)
                    .build();
            pointActivityMapper.insert(pointActivity);
        }
    }

    private PointActivityDO validateExists(Long id) {
        PointActivityDO pointActivity = pointActivityMapper.selectById(id);
        if (pointActivity == null) {
            throw exception(POINT_ACTIVITY_NOT_EXISTS);
        }
        return pointActivity;
    }

    private void validateNameUnique(Long id, String name) {
        PointActivityDO existing = pointActivityMapper.selectOne(PointActivityDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(POINT_ACTIVITY_NAME_EXISTS);
        }
    }

    @Override
    public List<PointActivityDO> getSimpleList() {
        return pointActivityMapper.selectList();
    }

    @Override
    public List<Map<String, Object>> getStationSimpleList() {
        return pointActivityMapper.selectStationSimpleList();
    }

    @Override
    @LogRecord(type = POINT_ACTIVITY_TYPE, subType = POINT_ACTIVITY_ACTIVATE_SUB_TYPE, bizNo = "{{#id}}",
            success = POINT_ACTIVITY_ACTIVATE_SUCCESS)
    public void activate(Long id) {
        PointActivityDO pointActivity = validateExists(id);
//        if (Objects.equals(PointActivityStatusEnum.PENDING.getValue(), pointActivity.getStatus())) { // 待生效
//            throw exception(POINT_ACTIVITY_STATUS_ERROR);
//        }
        pointActivity.setStatus(PointActivityStatusEnum.IN_PROGRESS.getValue()); // 进行中
        pointActivity.setAuditTime(LocalDateTime.now());
        // auditorId 由 Controller 层通过 SecurityFrameworkUtils 获取后设置
        pointActivityMapper.updateById(pointActivity);
        // 记录操作日志上下文
        LogRecordContext.putVariable("pointActivityName", pointActivity.getName());
    }
}
