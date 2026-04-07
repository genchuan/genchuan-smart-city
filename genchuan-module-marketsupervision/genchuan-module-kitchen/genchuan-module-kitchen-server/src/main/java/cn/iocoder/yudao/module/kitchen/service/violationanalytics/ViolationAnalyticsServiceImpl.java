package cn.iocoder.yudao.module.kitchen.service.violationanalytics;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.violationanalytics.ViolationAnalyticsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
public class ViolationAnalyticsServiceImpl implements ViolationAnalyticsService{
    @Resource
    private ViolationAnalyticsMapper violationAnalyticsMapper;


    @Override
    public PageResult<ViolationAnalyticsPageResp> getViolationAnalyticsPage(ViolationAnalyticsPageReq req) {
        LocalDateTime beginTime;
        LocalDateTime endTime;
        String period = req.getStatisticPeriod();
        String timeLabel = "";

        // 1. 优先使用 日/周/月，如果没有则使用前端传入的起止时间
        if (StrUtil.isNotBlank(period)) {
            // 纯时间类型，全程使用 LocalDateTime，不再使用 LocalDate
            LocalDateTime now = LocalDateTime.now();
            if ("日".equals(period)) {
                beginTime = now.toLocalDate().atStartOfDay(); // 今日 00:00:00
                endTime = beginTime.plusDays(1);              // 明日 00:00:00
                timeLabel = now.toLocalDate().toString(); // 2026-04-07
            } else if ("周".equals(period)) {
                // 本周一 00:00:00
                LocalDate nowDate = LocalDate.now();
                beginTime = now.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
                endTime = beginTime.plusWeeks(1); // 下周一 00:00:00
                int week = nowDate.get(WeekFields.ISO.weekOfWeekBasedYear());
                timeLabel = nowDate.getYear() + "年第" + week + "周";
            } else if ("月".equals(period)) {
                // 本月1号 00:00:00
                beginTime = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
                endTime = beginTime.plusMonths(1); // 下月1号 00:00:00
                timeLabel = now.getYear() + "-" + String.format("%02d", now.getMonthValue()); // 2026-04
            } else {
                // 默认当天
                beginTime = now.toLocalDate().atStartOfDay();
                endTime = beginTime.plusDays(1);
                timeLabel = now.toLocalDate().toString();
            }
        } else {
            // 2. 没有快捷周期 → 直接使用前端传入的时间
            beginTime = req.getBeginTime();
            endTime = req.getEndTime();
            if (beginTime != null && endTime != null) {
                // 格式：2026-04-01 00:00:00 - 2026-04-07 23:59:59
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String start = beginTime.format(formatter);
                String end = endTime.format(formatter);
                timeLabel = start + "-" + end;
            }else {
                timeLabel="全部时间";
            }
        }

        // 回填时间到 req
        req.setBeginTime(beginTime);
        req.setEndTime(endTime);


        // 查询数据
        List<ViolationAnalyticsPageResp> list = violationAnalyticsMapper.selectViolationAnalyticsList(req);
        Long total = violationAnalyticsMapper.selectViolationAnalyticsCount(req);


        // 回填返回值
        String finalTimeLabel = timeLabel;
        list.forEach(resp -> {
            //period 为空 → 赋值为“自定义”
            resp.setStatisticPeriod(StrUtil.isBlank(period) ? "自定义" : period);
            resp.setBeginTime(beginTime);
            resp.setEndTime(endTime);
            resp.setTimeLabel(finalTimeLabel);
        });

        return new PageResult<>(list, total);
    }

    @Override
    public ViolationAnalyticsDrillResp getViolationAnalyticsDrill(ViolationAnalyticsDrillReq req) {
        LocalDateTime beginTime;
        LocalDateTime endTime;
        String period = req.getStatisticPeriod();
        String timeLabel = "";

        // 1. 优先使用 日/周/月，如果没有则使用前端传入的起止时间
        if (StrUtil.isNotBlank(period)) {
            // 纯时间类型，全程使用 LocalDateTime，不再使用 LocalDate
            LocalDateTime now = LocalDateTime.now();
            if ("日".equals(period)) {
                beginTime = now.toLocalDate().atStartOfDay(); // 今日 00:00:00
                endTime = beginTime.plusDays(1);              // 明日 00:00:00
                timeLabel = now.toLocalDate().toString(); // 2026-04-07
            } else if ("周".equals(period)) {
                // 本周一 00:00:00
                LocalDate nowDate = LocalDate.now();
                beginTime = now.with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
                endTime = beginTime.plusWeeks(1); // 下周一 00:00:00
                int week = nowDate.get(WeekFields.ISO.weekOfWeekBasedYear());
                timeLabel = nowDate.getYear() + "年第" + week + "周";
            } else if ("月".equals(period)) {
                // 本月1号 00:00:00
                beginTime = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
                endTime = beginTime.plusMonths(1); // 下月1号 00:00:00
                timeLabel = now.getYear() + "-" + String.format("%02d", now.getMonthValue()); // 2026-04
            } else {
                // 默认当天
                beginTime = now.toLocalDate().atStartOfDay();
                endTime = beginTime.plusDays(1);
                timeLabel = now.toLocalDate().toString();
            }
        } else {
            // 2. 没有快捷周期 → 直接使用前端传入的时间
            beginTime = req.getBeginTime();
            endTime = req.getEndTime();
            if (beginTime != null && endTime != null) {
                // 格式：2026-04-01 00:00:00 - 2026-04-07 23:59:59
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String start = beginTime.format(formatter);
                String end = endTime.format(formatter);
                timeLabel = start + "-" + end;
            }else {
                timeLabel="全部时间";
            }
        }

        // 回填时间到 req
        req.setBeginTime(beginTime);
        req.setEndTime(endTime);


        // 查询数据
        // ====================== 【关键：钻取纬度逻辑】 ======================
        String drillDimension = req.getDrillDimension();
        List<AiAlertMessageDO> alarmList = null;
        List<RectifyReviewDO> rectifyReviewDOList = null;
        List<SysDeviceDO> normalSysDeviceDOList = null;
        List<RectifyReviewDO> finishRectifyReviewDOList = null;

        // 1. 告警钻取
        if (drillDimension == null || "告警".equals(drillDimension)) {
            alarmList = violationAnalyticsMapper.drillAlarmList(req);
        }
        // 2. 违规钻取
        if (drillDimension == null || "违规".equals(drillDimension)) {
            rectifyReviewDOList = violationAnalyticsMapper.drillrectifyReviewDOList(req);
        }
        // 3. 正常设备钻取
        if (drillDimension == null || "正常设备".equals(drillDimension)) {
            normalSysDeviceDOList = violationAnalyticsMapper.drillNormalSysDeviceDOList(req);
        }
        // 4. 整改完成钻取
        if (drillDimension == null || "整改完成".equals(drillDimension)) {
            finishRectifyReviewDOList = violationAnalyticsMapper.drillFinishRectifyReviewDOList(req);
        }

        // 组装返回
        ViolationAnalyticsDrillResp resp = new ViolationAnalyticsDrillResp();
        //period 为空 → 赋值为“自定义”
        resp.setStatisticPeriod(StrUtil.isBlank(period) ? "自定义" : period);
        resp.setBeginTime(beginTime);
        resp.setEndTime(endTime);
        resp.setTimeLabel(timeLabel);

        resp.setAlarmList(alarmList);
        resp.setRectifyReviewDOList(rectifyReviewDOList);
        resp.setDeviceNormalList(normalSysDeviceDOList);
        resp.setRectifyFinishList(finishRectifyReviewDOList);

        return resp;
    }

}
