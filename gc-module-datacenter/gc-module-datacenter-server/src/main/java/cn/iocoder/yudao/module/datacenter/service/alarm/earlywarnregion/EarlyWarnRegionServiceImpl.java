package cn.iocoder.yudao.module.datacenter.service.alarm.earlywarnregion;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo.EarlyWarnRegionGenerateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo.EarlyWarnRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo.EarlyWarnRegionSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo.WarningAlertRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty.GridCountyDO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.gridcounty.GridCountyMapper;
import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.warningalertlisttable.WarningAlertListTableMapper;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.earlywarnregion.EarlyWarnRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.earlywarnregion.EarlyWarnRegionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按行政区划预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnRegionServiceImpl implements EarlyWarnRegionService {

    @Resource
    private EarlyWarnRegionMapper earlyWarnRegionMapper;

    @Resource
    private WarningAlertListTableMapper warningAlertListTableMapper;

    @Resource
    private GridCountyMapper gridCountyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long generateEarlyWarnRegionStat(EarlyWarnRegionGenerateReqVO generateReqVO) {
        // 1. 构建统计周期
        String statCycle = buildStatCycle(generateReqVO);

        // 2. 获取目标行政区划列表
        List<GridCountyDO> targetAreas = getTargetAreas(generateReqVO);

        // 3. 为每个行政区划生成统计
        List<EarlyWarnRegionDO> statList = new ArrayList<>();
        for (GridCountyDO area : targetAreas) {
            EarlyWarnRegionDO stat = generateStatForArea(area, generateReqVO, statCycle);
            if (stat != null) {
                statList.add(stat);
            }
        }

        // 4. 批量插入统计结果
        if (!CollectionUtils.isEmpty(statList)) {
            // 先删除该周期下可能存在的旧数据
            earlyWarnRegionMapper.delete(new LambdaQueryWrapperX<EarlyWarnRegionDO>()
                    .eq(EarlyWarnRegionDO::getStatCycle, statCycle)
                    .in(EarlyWarnRegionDO::getRegionCode,
                            statList.stream().map(EarlyWarnRegionDO::getRegionCode).collect(Collectors.toList())));

            // 批量插入新数据
            for (EarlyWarnRegionDO stat : statList) {
                earlyWarnRegionMapper.insert(stat);
            }
        }

        return statList.isEmpty() ? null : statList.get(0).getId();
    }

    @Override
    public PageResult<WarningAlertRespVO> getWarningAlertPage(String regionCode, String statCycle, PageParam pageReqVO) {
        // 1. 根据统计周期解析时间范围
        LocalDateTime[] timeRange = parseStatCycle(statCycle);

        // 2. 查询对应的行政区划ID
        GridCountyDO gridCounty = gridCountyMapper.selectOne(new LambdaQueryWrapperX<GridCountyDO>()
                .eq(GridCountyDO::getAdminDivisionId, regionCode));

        if (gridCounty == null) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }

        // 3. 查询预警明细（通过relatedObjectId关联）
        PageResult<WarningAlertListTableDO> alertPage = warningAlertListTableMapper.selectPage(pageReqVO,
                new LambdaQueryWrapperX<WarningAlertListTableDO>()
                        .eq(WarningAlertListTableDO::getRelatedObjectId, gridCounty.getAdminDivisionId())
                        .between(WarningAlertListTableDO::getTriggerTime, timeRange[0], timeRange[1])
                        .orderByDesc(WarningAlertListTableDO::getTriggerTime));

        // 4. 转换为响应VO
        return BeanUtils.toBean(alertPage, WarningAlertRespVO.class);
    }

    @Override
    public Long createEarlyWarnRegion(EarlyWarnRegionSaveReqVO createReqVO) {
        // 插入
        EarlyWarnRegionDO earlyWarnRegion = BeanUtils.toBean(createReqVO, EarlyWarnRegionDO.class);
        earlyWarnRegionMapper.insert(earlyWarnRegion);
        // 返回
        return earlyWarnRegion.getId();
    }

    @Override
    public void updateEarlyWarnRegion(EarlyWarnRegionSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnRegionExists(updateReqVO.getId());
        // 更新
        EarlyWarnRegionDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnRegionDO.class);
        earlyWarnRegionMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnRegion(Long id) {
        // 校验存在
        validateEarlyWarnRegionExists(id);
        // 删除
        earlyWarnRegionMapper.deleteById(id);
    }

    private void validateEarlyWarnRegionExists(Long id) {
        if (earlyWarnRegionMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_REGION_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnRegionDO getEarlyWarnRegion(Long id) {
        return earlyWarnRegionMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnRegionDO> getEarlyWarnRegionPage(EarlyWarnRegionPageReqVO pageReqVO) {
        return earlyWarnRegionMapper.selectPage(pageReqVO);
    }

    private String buildStatCycle(EarlyWarnRegionGenerateReqVO generateReqVO) {
        String cycleType = generateReqVO.getStatCycleType();
        String cycleValue = generateReqVO.getStatCycleValue();

        switch (cycleType) {
            case "day":
                return cycleValue; // YYYYMMDD
            case "week":
                return cycleValue; // YYYYWW
            case "month":
                return cycleValue; // YYYYMM
            case "custom":
                // 修正：先将字符串时间转换为LocalDateTime，再格式化
                String startTimeStr = generateReqVO.getStartTime();
                String endTimeStr = generateReqVO.getEndTime();

                if (startTimeStr == null || startTimeStr.isEmpty() || endTimeStr == null || endTimeStr.isEmpty()) {
                    throw new IllegalArgumentException("自定义周期必须提供开始时间和结束时间");
                }

                LocalDateTime startTime = parseDateTime(startTimeStr);
                LocalDateTime endTime = parseDateTime(endTimeStr);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                return startTime.format(formatter) + "-" + endTime.format(formatter);
            default:
                throw new IllegalArgumentException("不支持的统计周期类型: " + cycleType);
        }
    }

    private List<GridCountyDO> getTargetAreas(EarlyWarnRegionGenerateReqVO generateReqVO) {
        System.out.println("=== getTargetAreas 方法开始 ===");

        // 先直接查询所有三级行政区划，看看数据库里有什么
        List<GridCountyDO> allAreas = gridCountyMapper.selectList(new LambdaQueryWrapperX<>());
        System.out.println("数据库中所有行政区划数量: " + allAreas.size());
        for (GridCountyDO area : allAreas) {
            System.out.println("区域: " + area.getAdminName() +
                    ", ID: " + area.getAdminDivisionId() +
                    ", Code: " + area.getAdminCode() +
                    ", Level: " + area.getAdminLevel());
        }

        LambdaQueryWrapperX<GridCountyDO> queryWrapper = new LambdaQueryWrapperX<GridCountyDO>();

        // 根据级别查询
        queryWrapper.eq(GridCountyDO::getAdminLevel, generateReqVO.getRegionLevel());

        // 如果有目标区域代码，查询该区域
        if (generateReqVO.getTargetRegionCode() != null && !generateReqVO.getTargetRegionCode().isEmpty()) {
            System.out.println("查询目标区域: " + generateReqVO.getTargetRegionCode());

            GridCountyDO targetArea = gridCountyMapper.selectOne(new LambdaQueryWrapperX<GridCountyDO>()
                    .eq(GridCountyDO::getAdminDivisionId, generateReqVO.getTargetRegionCode()));

            if (targetArea != null) {
                System.out.println("找到目标区域: " + targetArea.getAdminName());
                // 简化逻辑：直接返回目标区域
                return Arrays.asList(targetArea);
            } else {
                System.out.println("未找到目标区域，尝试使用admin_code查询...");
                // 尝试用admin_code查询
                targetArea = gridCountyMapper.selectOne(new LambdaQueryWrapperX<GridCountyDO>()
                        .eq(GridCountyDO::getAdminCode, generateReqVO.getTargetRegionCode()));
                if (targetArea != null) {
                    System.out.println("使用admin_code找到目标区域: " + targetArea.getAdminName());
                    return Arrays.asList(targetArea);
                }
            }
        }

        // 如果没指定目标区域或没找到，返回所有三级行政区划
        List<GridCountyDO> result = gridCountyMapper.selectList(queryWrapper);
        System.out.println("最终返回区域数量: " + result.size());
        return result;
    }

    private EarlyWarnRegionDO generateStatForArea(GridCountyDO area, EarlyWarnRegionGenerateReqVO generateReqVO, String statCycle) {
        // 添加详细的调试信息
        System.out.println("=== 开始处理区域: " + area.getAdminName() + " ===");
        System.out.println("区域ID: " + area.getAdminDivisionId());
        System.out.println("区域代码: " + area.getAdminCode());
        System.out.println("区域级别: " + area.getAdminLevel());
        System.out.println("父级ID: " + area.getParentAdminId());

        // 1. 查询该区域在统计周期内的预警数据
        LocalDateTime[] timeRange = parseTimeRangeFromStatCycle(generateReqVO, statCycle);

        System.out.println("统计周期: " + statCycle);
        System.out.println("时间范围: " + timeRange[0] + " 到 " + timeRange[1]);

        // 查询预警数据
        List<WarningAlertListTableDO> alerts = warningAlertListTableMapper.selectList(
                new LambdaQueryWrapperX<WarningAlertListTableDO>()
                        .eq(WarningAlertListTableDO::getRelatedObjectId, area.getAdminDivisionId())
                        .between(WarningAlertListTableDO::getTriggerTime, timeRange[0], timeRange[1])
        );

        System.out.println("查询到的预警数据数量: " + alerts.size());

        // 打印查询的SQL条件，用于调试
        System.out.println("查询条件: relatedObjectId = " + area.getAdminDivisionId());
        System.out.println("时间条件: " + timeRange[0] + " <= triggerTime <= " + timeRange[1]);

        if (alerts.isEmpty()) {
            System.out.println("该区域在指定时间内没有预警数据");
            return null;
        }

        // 2. 统计各项数据（适配您的预警等级和状态字段）
        int total = alerts.size();
        int normal = (int) alerts.stream().filter(a -> "一般".equals(a.getWarningLevel())).count();
        int heavy = (int) alerts.stream().filter(a -> "较重".equals(a.getWarningLevel())).count();
        int serious = (int) alerts.stream().filter(a -> "严重".equals(a.getWarningLevel())).count();
        int critical = (int) alerts.stream().filter(a -> "特别严重".equals(a.getWarningLevel())).count();

        // 根据您的status字段判断状态（需要根据实际业务逻辑调整）
        int completed = (int) alerts.stream().filter(a -> a.getStatus() != null && a.getStatus() == 2).count(); // 假设2表示已完成
        int pending = (int) alerts.stream().filter(a -> a.getStatus() != null && a.getStatus() == 1).count(); // 假设1表示待处理

        // 3. 计算处置率
        BigDecimal handleRate = total > 0 ?
                BigDecimal.valueOf((double) completed / total * 100).setScale(2, BigDecimal.ROUND_HALF_UP) :
                BigDecimal.ZERO;

        // 4. 构建统计对象
        return EarlyWarnRegionDO.builder()
                .warnRegionStatId(UUID.randomUUID().toString().replace("-", ""))
                .statCycle(statCycle)
                .regionCode(area.getAdminCode())
                .regionName(area.getAdminName())
                .regionLevel(getRegionLevelString(area.getAdminLevel()))
                .totalWarnCount(total)
                .normalLevelCount(normal)
                .heavyLevelCount(heavy)
                .seriousLevelCount(serious)
                .criticalLevelCount(critical)
                .completedCount(completed)
                .pendCount(pending)
                .handleRate(handleRate)
                .statUserId(generateReqVO.getScheduledTask() ? "system" : "admin")
                .statTime(LocalDateTime.now())
                .build();
    }

    private String getRegionLevelString(Integer adminLevel) {
        switch (adminLevel) {
            case 1: return "省级";
            case 2: return "市级";
            case 3: return "县级";
            default: return "未知";
        }
    }

    private LocalDateTime[] parseTimeRangeFromStatCycle(EarlyWarnRegionGenerateReqVO generateReqVO, String statCycle) {
        String cycleType = generateReqVO.getStatCycleType();

        System.out.println("解析时间范围，周期类型: " + cycleType + ", 周期值: " + statCycle);

        switch (cycleType) {
            case "custom":
                // 处理字符串格式的时间
                String startTimeStr = generateReqVO.getStartTime();
                String endTimeStr = generateReqVO.getEndTime();

                System.out.println("自定义周期，开始时间: " + startTimeStr + ", 结束时间: " + endTimeStr);

                if (startTimeStr == null || startTimeStr.isEmpty() || endTimeStr == null || endTimeStr.isEmpty()) {
                    throw new IllegalArgumentException("自定义周期必须提供开始时间和结束时间");
                }

                LocalDateTime startTime = parseDateTime(startTimeStr);
                LocalDateTime endTime = parseDateTime(endTimeStr);

                if (startTime == null || endTime == null) {
                    throw new IllegalArgumentException("开始时间或结束时间格式错误");
                }

                if (startTime.isAfter(endTime)) {
                    throw new IllegalArgumentException("开始时间不能晚于结束时间");
                }

                return new LocalDateTime[] { startTime, endTime };

            case "day":
                // 日统计：YYYYMMDD
                System.out.println("日统计，周期值: " + statCycle);
                if (statCycle.length() != 8) {
                    throw new IllegalArgumentException("日统计周期格式错误，应为YYYYMMDD，实际: " + statCycle);
                }
                int year = Integer.parseInt(statCycle.substring(0, 4));
                int month = Integer.parseInt(statCycle.substring(4, 6));
                int day = Integer.parseInt(statCycle.substring(6, 8));
                return new LocalDateTime[] {
                        LocalDateTime.of(year, month, day, 0, 0, 0),
                        LocalDateTime.of(year, month, day, 23, 59, 59)
                };

            case "month":
                // 月统计：YYYYMM
                if (statCycle.length() != 6) {
                    throw new IllegalArgumentException("月统计周期格式错误，应为YYYYMM");
                }
                year = Integer.parseInt(statCycle.substring(0, 4));
                month = Integer.parseInt(statCycle.substring(4, 6));
                return new LocalDateTime[] {
                        LocalDateTime.of(year, month, 1, 0, 0, 0),
                        LocalDateTime.of(year, month, 1, 23, 59, 59).plusMonths(1).minusDays(1)
                };

            case "week":
                // 周统计：YYYYWW
                if (statCycle.length() != 6) {
                    throw new IllegalArgumentException("周统计周期格式错误，应为YYYYWW");
                }
                year = Integer.parseInt(statCycle.substring(0, 4));
                int week = Integer.parseInt(statCycle.substring(4, 6));
                LocalDateTime weekStart = LocalDateTime.of(year, 1, 1, 0, 0, 0)
                        .plusWeeks(week - 1)
                        .with(java.time.DayOfWeek.MONDAY);
                return new LocalDateTime[] {
                        weekStart,
                        weekStart.plusDays(6).withHour(23).withMinute(59).withSecond(59)
                };

            default:
                throw new IllegalArgumentException("不支持的统计周期类型: " + cycleType);
        }
    }

    // 添加时间格式转换方法
    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }

        System.out.println("解析时间字符串: " + dateTimeStr);

        try {
            // 移除可能的多余引号
            dateTimeStr = dateTimeStr.replace("\"", "").trim();

            // 尝试不同的时间格式
            DateTimeFormatter[] formatters = {
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
                    DateTimeFormatter.ofPattern("yyyyMMdd HHmmss"),
                    DateTimeFormatter.ofPattern("yyyyMMdd"),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME
            };

            for (DateTimeFormatter formatter : formatters) {
                try {
                    if (dateTimeStr.length() == 8) { // 纯日期，如20231201
                        dateTimeStr = dateTimeStr + " 000000";
                    }
                    LocalDateTime result = LocalDateTime.parse(dateTimeStr.replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    System.out.println("成功解析时间: " + result);
                    return result;
                } catch (Exception e) {
                    continue;
                }
            }
            throw new IllegalArgumentException("无法解析时间格式: " + dateTimeStr);
        } catch (Exception e) {
            System.err.println("时间解析失败: " + dateTimeStr + ", 错误: " + e.getMessage());
            throw new IllegalArgumentException("时间格式错误: " + dateTimeStr, e);
        }
    }

    private LocalDateTime[] parseStatCycle(String statCycle) {
        try {
            if (statCycle.contains("-")) {
                // 自定义周期格式：yyyyMMdd-yyyyMMdd
                String[] parts = statCycle.split("-");
                if (parts.length == 2 && parts[0].length() == 8 && parts[1].length() == 8) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    return new LocalDateTime[] {
                            LocalDateTime.parse(parts[0] + "000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                            LocalDateTime.parse(parts[1] + "235959", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                    };
                }
            } else if (statCycle.length() == 8) {
                // 日统计：YYYYMMDD
                int year = Integer.parseInt(statCycle.substring(0, 4));
                int month = Integer.parseInt(statCycle.substring(4, 6));
                int day = Integer.parseInt(statCycle.substring(6, 8));
                return new LocalDateTime[] {
                        LocalDateTime.of(year, month, day, 0, 0, 0),
                        LocalDateTime.of(year, month, day, 23, 59, 59)
                };
            } else if (statCycle.length() == 6) {
                // 月统计：YYYYMM
                int year = Integer.parseInt(statCycle.substring(0, 4));
                int month = Integer.parseInt(statCycle.substring(4, 6));
                return new LocalDateTime[] {
                        LocalDateTime.of(year, month, 1, 0, 0, 0),
                        LocalDateTime.of(year, month, 1, 23, 59, 59).plusMonths(1).minusDays(1)
                };
            }
        } catch (Exception e) {
            // 解析失败，使用默认时间范围
            System.err.println("统计周期解析失败: " + statCycle + ", 使用默认时间范围");
        }

        // 默认返回最近30天
        return new LocalDateTime[] {
                LocalDateTime.now().minusDays(30),
                LocalDateTime.now()
        };
    }


}