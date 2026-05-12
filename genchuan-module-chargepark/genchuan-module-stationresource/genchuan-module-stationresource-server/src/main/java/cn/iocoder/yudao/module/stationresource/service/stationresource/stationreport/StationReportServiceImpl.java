package cn.iocoder.yudao.module.stationresource.service.stationresource.stationreport;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops.StationOpHistoryReportCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.extraops.StationOpReportBatchBackReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink.ChargeParkLinkDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand.DebtExpandDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.chargeparklink.ChargeParkLinkMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.depositplan.DepositPlanMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.feerule.FeeRuleMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.timepermission.TimePermissionMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.debtexpand.DebtExpandMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo.StationInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationreport.StationReportMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.name.VrvNameUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 场站资源报表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class StationReportServiceImpl implements StationReportService {

    /**
     * 场站资源报表 Mapper
     */
    @Resource
    private StationReportMapper stationReportMapper;

    /**
     * 【独立方法】批量生成：当前周期 + 往前 N 个周期
     * 完全不影响历史报表、原有生成报表
     */
    @Override
    public List<Long> addBatchBackReport(StationOpReportBatchBackReqVO reqVO) {
        // 1. 校验
        String reportCycle = reqVO.getReportCycle();
        Integer backNum = reqVO.getBackNum();
        if (backNum < 0 || backNum > 36) {
            throw exception("回溯周期必须在 0~36 之间");
        }

        List<String> allow = Arrays.asList("日报","周报","月报","季报","半年报","年报");
        if (!allow.contains(reportCycle)) {
            throw exception("仅支持日报/周报/月报/季报/半年报/年报");
        }

        List<Long> resultIds = new ArrayList<>();
        Date now = new Date();

        // 2. 循环生成：0=当前，1=上一个…backNum=往前N个
        for (int i = 0; i <= backNum; i++) {
            // 偏移日期
            Date targetDate = getOffsetDate(now, reportCycle, i);

            // 计算该周期的起止时间
            Date[] cycle = autoCalcHistoryReportTime(reportCycle, targetDate);
            LocalDateTime start = LocalDateTime.ofInstant(cycle[0].toInstant(), ZoneId.systemDefault());
            LocalDateTime end = LocalDateTime.ofInstant(cycle[1].toInstant(), ZoneId.systemDefault());

            // 组装成你原有VO，直接复用你【最稳定的 addReport】
            StationOpReportCreateReqVO newReq = new StationOpReportCreateReqVO();
            newReq.setReportCycle(reportCycle);
            newReq.setReportStartTime(start.withNano(0));
            newReq.setReportEndTime(end.withNano(0));
            newReq.setRemark(reqVO.getRemark());

            // 直接调用你原来的方法！！！
            Long id = addReport(newReq);
            resultIds.add(id);
        }

        return resultIds;
    }

    // ===================== 内部小工具（你可以直接放在ServiceImpl里）=====================
    private Date getOffsetDate(Date baseDate, String cycle, int offset) {
        if (offset == 0) return baseDate;
        switch (cycle) {
            case "日报": return DateUtil.offsetDay(baseDate, -offset);
            case "周报": return DateUtil.offsetWeek(baseDate, -offset);
            case "月报": return DateUtil.offsetMonth(baseDate, -offset);
            case "季报": return DateUtil.offsetMonth(baseDate, -offset * 3);
            case "半年报": return DateUtil.offsetMonth(baseDate, -offset * 6);
            case "年报": return DateUtil.offsetYear(baseDate, -offset);
            default: return baseDate;
        }
    }
    @Override
    public Long addHistoryReport(StationOpHistoryReportCreateReqVO reqVO) {
        System.out.println("历史报表生成请求：" + reqVO);

        // ========== 核心：根据 目标日期 + 周期 自动计算历史时间 ==========
        String reportType = reqVO.getReportCycle();
        Date targetDate = Date.from(reqVO.getTargetDate().atZone(ZoneId.systemDefault()).toInstant());

        // 计算历史周期的开始/结束时间
        Date[] dates = autoCalcHistoryReportTime(reportType, targetDate);
        LocalDateTime startTime = LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault());

        // 组装成你原有 VO，完全复用原有逻辑
        StationOpReportCreateReqVO newReq = new StationOpReportCreateReqVO();
        newReq.setReportCycle(reportType);
        newReq.setReportStartTime(startTime.withNano(0));
        newReq.setReportEndTime(endTime.withNano(0));
        newReq.setRemark(reqVO.getRemark());

        // ========== 直接调用你原来的 addReport 完成统计！！！ ==========
        return addReport(newReq);
    }

    /**
     * 【历史报表专用】根据周期类型 + 目标日期，计算该周期的起止时间
     */
    private Date[] autoCalcHistoryReportTime(String reportType, Date targetDate) {
        Date startTime;
        Date endTime;

        switch (reportType) {
            case "日报":
                startTime = DateUtil.beginOfDay(targetDate);
                endTime = DateUtil.endOfDay(targetDate);
                break;
            case "周报":
                startTime = DateUtil.beginOfWeek(targetDate);
                endTime = DateUtil.endOfWeek(targetDate);
                break;
            case "月报":
                startTime = DateUtil.beginOfMonth(targetDate);
                endTime = DateUtil.endOfMonth(targetDate);
                break;
            case "季报":
                startTime = DateUtil.beginOfQuarter(targetDate);
                endTime = DateUtil.endOfQuarter(targetDate);
                break;
            case "半年报":
                int month = DateUtil.month(targetDate) + 1;
                int year = DateUtil.year(targetDate);
                if (month <= 6) {
                    startTime = DateUtil.parse(year + "-01-01");
                    endTime = DateUtil.parse(year + "-06-30");
                } else {
                    startTime = DateUtil.parse(year + "-07-01");
                    endTime = DateUtil.parse(year + "-12-31");
                }
                break;
            case "年报":
                startTime = DateUtil.beginOfYear(targetDate);
                endTime = DateUtil.endOfYear(targetDate);
                break;
            default:
                startTime = DateUtil.beginOfDay(targetDate);
                endTime = DateUtil.endOfDay(targetDate);
        }
        return new Date[]{startTime, endTime};
    }

    @Override
    public StationOpReportChartRespVO getReportChartData(StationOpReportChartReqVO reqVO) {
        // ========== 核心：自动根据报表类型计算时间(自定义报表：保留前端传入的 startTime、endTime 不变) ==========
        String reportType = reqVO.getReportCycle();
        if (reportType!=null){
            //如果不是自定义报表，自动计算时间
            if (StrUtil.isNotBlank(reportType) && !"自定义报表".equals(reportType)) {
                // 非自定义：自动计算 开始/结束 时间
                Date[] dates = autoCalcReportTime(reportType);
                // 覆盖前端传入的时间（自动生成）
                // 方式1：使用 Date -> LocalDateTime 直接转换（推荐，无格式问题）
                reqVO.setReportStartTime(LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault()));
                reqVO.setReportEndTime(LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault()));
            }
        }

        System.out.println("cs2026-04-22 11:24:10:"+reqVO);

        // 1. 构建返回对象
        StationOpReportChartRespVO resp = new StationOpReportChartRespVO();
        StationOpReportChartRespVO.CardData cardData = new StationOpReportChartRespVO.CardData();

        // ========== 卡片数据统计（全部走 stationReportMapper + XML） ==========
        // 1. 片区统计：总片区数、覆盖场站数
        Map<String, Object> areaMap = stationReportMapper.selectAreaReport(
                BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setTotalAreaCount(((Number) areaMap.get("totalAreaCount")).intValue());
        cardData.setCoverStationCount(((Number) areaMap.get("coverStationCount")).intValue());

        // 2. 场站统计：总站场数、正常运营数
        Map<String, Object> stationMap = stationReportMapper.selectStationReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setTotalStationCount(((Number) stationMap.get("totalStationCount")).intValue());
        cardData.setNormalOperateCount(((Number) stationMap.get("normalOperateCount")).intValue());

        // 3. 车位统计：总车位数、可用车位数
        Map<String, Object> spaceMap = stationReportMapper.selectSpaceReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setTotalSpaceCount(((Number) spaceMap.get("totalSpaceCount")).intValue());
        cardData.setAvailableSpaceCount(((Number) spaceMap.get("availableSpaceCount")).intValue());

        // 4. 生效规则数
        Map<String, Object> ruleMap = stationReportMapper.selectEffectiveRuleReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setEffectiveRuleCount(((Number) ruleMap.get("effectiveRuleCount")).intValue());

        // 5. 订单量 + 营收
        Map<String, Object> orderMap = stationReportMapper.selectChargeParkOrderReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setOrderCount(((Number) orderMap.get("orderCount")).intValue());
        cardData.setRevenue((BigDecimal) orderMap.get("revenue"));

        // 6. 追缴完成率
        Map<String, Object> debtMap = stationReportMapper.selectDebtExpandReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setRecoveryRate((BigDecimal) debtMap.get("recoveryRate"));

        // 7. 押金订单量
        Map<String, Object> depositMap = stationReportMapper.selectDepositPlanReport(BeanUtils.toBean(reqVO, StationOpReportCreateReqVO.class));
        cardData.setDepositOrderCount(((Number) depositMap.get("depositOrderCount")).intValue());

        // 封装卡片数据
        resp.setCardData(cardData);

        // ========== 地图数据、柱状图、折线图（补充完整） ==========
        // 地图数据（片区+经纬度）
        List<StationOpReportChartRespVO.MapData> mapData = stationReportMapper.selectMapData(reqVO);
        resp.setMapData(mapData);
        List<StationOpReportChartRespVO.StationMapData> stationMapData = stationReportMapper.getStationMapData(reqVO);
        resp.setStationMapData(stationMapData);

        // 柱状图数据（片区场站数、场站类型分布）
            //片区场站
        List<StationOpReportChartRespVO.BarData> barData = stationReportMapper.selectBarData(reqVO);
        resp.setBarData(barData);
            // TODO 场站类型 分类 的场站数目
        List<StationOpReportChartRespVO.StationTypeBarData> stationTypeBarData = stationReportMapper.selectStationTypeBarData(reqVO);
        resp.setStationTypeBarData(stationTypeBarData);
            // TODO 场站订单数目
        List<StationOpReportChartRespVO.StationOrderCountBarData> stationOrderCountBarData = stationReportMapper.selectStationOrderCountBarData(reqVO);
        resp.setStationOrderCountBarData(stationOrderCountBarData);
            // TODO 场站 追缴完成率
        List<StationOpReportChartRespVO.StationRecoverFinishBarData> stationRecoverFinishBarData = stationReportMapper.selectStationRecoverFinishBarData(reqVO);
        resp.setStationRecoverFinishBarData(stationRecoverFinishBarData);

            //车位
        List<StationOpReportChartRespVO.ParkSpaceMapData> parkSpaceMapData = stationReportMapper.getParkSpaceMapData(reqVO);
        resp.setParkSpaceMapData(parkSpaceMapData);

        // 折线图数据（日期趋势：订单、拓场进度、权限使用）
        List<StationOpReportChartRespVO.LineData> lineData = stationReportMapper.selectLineData(reqVO);
        resp.setLineData(lineData);

        return resp;
    }

    /**
     * 获得场站资源报表详情
     *
     * @param id 报表ID
     * @return 报表DO
     */
    @Override
    public StationReportDO getReport(Long id) {
        // 根据ID查询报表
        StationReportDO report = stationReportMapper.selectById(id);
        // 校验是否存在
        if (report == null) {
            throw exception("场站报表不存在");
        }
        return report;
    }

    @Override
    public PageResult<StationReportDO> getPage(StationOpReportPageReqVO pageReqVO) {
        // 构建查询条件
        LambdaQueryWrapperX<StationReportDO> wrapper = new LambdaQueryWrapperX<>();

        // 租户ID
//        wrapper.eq(StationReportDO::getTenantId, TenantContextHolder.getTenantId());
        // 逻辑删除
        wrapper.eq(StationReportDO::getDeleted, false);

        // 查询条件：报表周期
        wrapper.eqIfPresent(StationReportDO::getReportCycle, pageReqVO.getReportCycle());
        // 查询条件：生成状态
        wrapper.eqIfPresent(StationReportDO::getGenerateStatus, pageReqVO.getGenerateStatus());

        // 排序
        wrapper.orderByDesc(StationReportDO::getCreateTime);

        // 执行分页（芋道 BaseMapperX 标准写法）
        return stationReportMapper.selectPage(pageReqVO, wrapper);
    }

    @Override
    public Long addReport(StationOpReportCreateReqVO reqVO) {
        System.out.println("cs2026-04-23 16:34:06:"+reqVO);

        // ===================== 1. 报表类型强校验 =====================
        List<String> allowTypes = Arrays.asList(
                "日报", "周报", "月报", "季报", "半年报", "年报", "自定义报表"
        );
        if (StrUtil.isBlank(reqVO.getReportCycle()) || !allowTypes.contains(reqVO.getReportCycle())) {
            throw exception("报表类型只能是：日报/周报/月报/季报/半年报/年报/自定义报表");
        }

        // ===================== 2. 自定义报表：强制校验时间必须传 =====================
        String reportType = reqVO.getReportCycle();
        if ("自定义报表".equals(reportType)) {
            if (reqVO.getReportStartTime() == null) {
                throw exception("自定义报表必须传入开始时间");
            }
            if (reqVO.getReportEndTime() == null) {
                throw exception("自定义报表必须传入结束时间");
            }
            if (reqVO.getReportStartTime().isAfter(reqVO.getReportEndTime())) {
                throw exception("开始时间不能晚于结束时间");
            }
        }

        // ===================== 3. 非自定义：自动计算时间；自定义：不覆盖时间 =====================
// ===================== 3. 非自定义：自动计算时间；自定义：不覆盖时间 =====================
        if (!"自定义报表".equals(reportType)) {
            // 修复：如果已经有时间（历史/批量报表），不再重新计算！
            if (reqVO.getReportStartTime() == null) {
                Date[] dates = autoCalcReportTime(reportType);
                reqVO.setReportStartTime(LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault()));
                reqVO.setReportEndTime(LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault()));
            }
        }
        System.out.println("cs2026-04-23 16:35:07:"+reqVO);
        //去掉毫秒，方便后续的报表唯一性校验：
        reqVO.setReportStartTime(reqVO.getReportStartTime().withNano(0));
        reqVO.setReportEndTime(reqVO.getReportEndTime().withNano(0));
        System.out.println("cs2026-04-22 11:24:10:"+reqVO);

        // ========== 【核心：判断重复报表】 ==========
        // 根据 周期 + 开始时间 + 结束时间 查询是否已存在
        LambdaQueryWrapper<StationReportDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StationReportDO::getReportCycle, reqVO.getReportCycle());
        queryWrapper.eq(StationReportDO::getReportStartTime, reqVO.getReportStartTime());
        queryWrapper.eq(StationReportDO::getReportEndTime, reqVO.getReportEndTime());
        // 按 ID 倒序 → 最新的在最前面
        queryWrapper.orderByDesc(StationReportDO::getId);
        // 只取第一条（完美解决多条重复报错）
        queryWrapper.last("LIMIT 1");

        StationReportDO existReport = stationReportMapper.selectOne(queryWrapper);

        //构造返回参数
        StationReportDO respVO = new StationReportDO();

        //1.设置reportCycle,reportStartTime，reportEndTime
        respVO.setReportCycle(reqVO.getReportCycle());
        respVO.setReportStartTime(reqVO.getReportStartTime());
        respVO.setReportEndTime(reqVO.getReportEndTime());

        // 2. 开始统计业务数据（基于现有业务表，不新增冗余表）
        //----------------------- area_info-------------------------------------------------------------
        // 【总片区数】统计：从 area_info 表统计未删除、有效片区的总数量
        // 【覆盖场站数】统计：从 area_info 表对 station_count 字段进行求和
        Map<String, Object> areaMap = stationReportMapper.selectAreaReport(reqVO);
        respVO.setTotalAreaCount(((Number) areaMap.get("totalAreaCount")).longValue());
        // TODO 5145 场站数 估计得从 场站表
//        respVO.setCoverStationCount(((Number) areaMap.get("coverStationCount")).longValue());

        // 【总站场数】统计：从 station_info 表统计未删除、有效场站的总数量
        // 【正常运营数】统计：从 station_info 表统计 status = 已生效 的场站数量
        Map<String, Object> stationMap = stationReportMapper.selectStationReport(reqVO);
        respVO.setTotalStationCount(((Number) stationMap.get("totalStationCount")).longValue());
        respVO.setNormalOperateCount(((Number) stationMap.get("normalOperateCount")).longValue());
        // TODO 5145 场站数 估计得从 场站表
        respVO.setCoverStationCount(((Number) stationMap.get("totalStationCount")).longValue());

        //-------------------------parking_space_info-----------------------------------------------------------
        // 【总车位数】统计：从 parking_space_info 表统计未删除、有效车位的总数量
        // 【可用车位数】统计：从 parking_space_info 表统计 real_status = 空闲 的车位数量
        Map<String, Object> spaceMap = stationReportMapper.selectSpaceReport(reqVO);
        respVO.setTotalSpaceCount(((Number) spaceMap.get("totalSpaceCount")).longValue());
        respVO.setAvailableSpaceCount(((Number) spaceMap.get("availableSpaceCount")).longValue());

        //------------------------------------------------------------------------------------
        // 【生效规则数】统计：
        //      ① time_permission 表中 status = 已生效 的数量
        //      ② fee_rule 表中 status = 已生效 的数量
        //      ③ charge_park_link 表中 status = 已生效 的数量
        //      三者求和得到总生效规则数
        Map<String, Object> ruleMap = stationReportMapper.selectEffectiveRuleReport(reqVO);
        respVO.setEffectiveRuleCount(((Number) ruleMap.get("effectiveRuleCount")).longValue());

        //----------------------------charge_park_link--------------------------------------------------------
        // 【订单量】统计：从业务表charge_park_link.today_order_count统计当前周期内的订单总数
        // 【营收】统计：从业务表charge_park_link.today_income统计当前周期内的营收总金额
        Map<String, Object> orderMap = stationReportMapper.selectChargeParkOrderReport(reqVO);
        respVO.setOrderCount(((Number) orderMap.get("orderCount")).longValue());
        respVO.setRevenue((BigDecimal) orderMap.get("revenue"));

        //------------------------------------------------------------------------------------
        // 【追缴完成率】统计：从 debt_expand 表计算 recovery_rate 字段的平均值
        Map<String, Object> debtMap = stationReportMapper.selectDebtExpandReport(reqVO);
        respVO.setRecoveryRate((BigDecimal) debtMap.get("recoveryRate"));

        // 【押金订单量】统计：从 deposit_plan 表对 deposit_order_count 字段进行求和
        Map<String, Object> depositMap = stationReportMapper.selectDepositPlanReport(reqVO);
        respVO.setDepositOrderCount(((Number) depositMap.get("depositOrderCount")).longValue());


        //------------------------------------------------------------------------------------
        // 【生成状态】设置：生成中 / 已生成 / 生成失败,默认已生成
        // 【报表生成时间】设置：当前系统时间
        // 【操作人】设置：当前登录用户昵称/姓名（来自 system_user），先固定写死system
        // 【导出次数】设置：默认 0
        respVO.setGenerateStatus("已生成");
        respVO.setGenerateTime(LocalDateTime.now());
        respVO.setOperator("system");
        respVO.setExportCount(0L);
        respVO.setStatCode(VrvNameUtil.generateCode("stationre"));

        //------------------------------------------------------------------------------------
        // 【创建者】设置：当前登录用户，先固定写死1
        // 【创建时间】设置：当前系统时间
        // 【更新时间】设置：当前系统时间
        //------------------------------------------------------------------------------------
        respVO.setCreator("1");
        respVO.setUpdater("1");
//        respVO.setCreateTime(LocalDateTime.now());
//        respVO.setUpdateTime(LocalDateTime.now());


        // ========== 存在则更新最新一条，不存在则插入 ==========
        if (existReport != null) {
            respVO.setId(existReport.getId());
            stationReportMapper.updateById(respVO);
            return existReport.getId();
        } else {
            stationReportMapper.insert(respVO);
            return respVO.getId();
        }
    }



    // ====================== 钻取方法（卡片指标 → 明细数据） ======================

    @Override
    public DrillDownRespVO drillDownArea(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownAreaList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownStation(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownStationList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownCoverStation(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownCoverStationList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownNormalStation(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownNormalStationList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownSpace(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownSpaceList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownAvailableSpace(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownAvailableSpaceList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownEffectiveRule(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownEffectiveRuleList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownOrder(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownOrderList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownDebtExpand(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownDebtExpandList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    @Override
    public DrillDownRespVO drillDownDepositPlan(DrillDownReqVO reqVO) {
        List<Map<String, Object>> list = stationReportMapper.drillDownDepositPlanList(
                reqVO.getReportStartTime(), reqVO.getReportEndTime());
        return buildResp(reqVO, list);
    }

    /** 组装钻取响应，手动分页 */
    private DrillDownRespVO buildResp(DrillDownReqVO reqVO, List<Map<String, Object>> fullList) {
        System.out.println("cs2026-05-11 17:33:04:"+reqVO);
        int pageNo = reqVO.getPageNo() != null ? reqVO.getPageNo() : 1;
        int pageSize = reqVO.getPageSize() != null ? reqVO.getPageSize() : 1000;
        int total = fullList.size();
        int from = Math.min((pageNo - 1) * pageSize, total);
        int to = Math.min(from + pageSize, total);

        DrillDownRespVO respVO = new DrillDownRespVO();
        respVO.setMetric(reqVO.getMetric());
        respVO.setMetricName(metricName(reqVO.getMetric()));
        respVO.setList(fullList.subList(from, to));
        respVO.setTotal((long) total);
        return respVO;
    }

    private String metricName(String metric) {
        switch (metric) {
            case "totalAreaCount":      return "总片区数";
            case "coverStationCount":   return "覆盖场站数";
            case "totalStationCount":   return "总站场数";
            case "normalOperateCount":  return "正常运营数";
            case "totalSpaceCount":     return "总车位数";
            case "availableSpaceCount": return "可用车位数";
            case "effectiveRuleCount":  return "生效规则数";
            case "orderCount":          return "订单量";
            case "revenue":             return "营收";
            case "recoveryRate":        return "追缴完成率";
            case "depositOrderCount":   return "押金订单量";
            default:                    return metric;
        }
    }

    /**
     * 根据报表类型，自动计算 开始时间、结束时间
     * @param reportType 报表类型
     * @return Date[0] = 开始时间，Date[1] = 结束时间
     */
    private Date[] autoCalcReportTime(String reportType) {
        Date now = new Date();
        Date startTime = null;
        Date endTime = null;

        switch (reportType) {
            case "日报":
                // 今天 00:00:00 ~ 23:59:59
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
                break;
            case "周报":
                // 本周一 00:00:00 ~ 本周日 23:59:59
                startTime = DateUtil.beginOfWeek(now);
                endTime = DateUtil.endOfWeek(now);
                break;
            case "月报":
                // 本月1号 ~ 本月最后一天
                startTime = DateUtil.beginOfMonth(now);
                endTime = DateUtil.endOfMonth(now);
                break;
            case "季报":
                // 本季度第一天 ~ 本季度最后一天
                startTime = DateUtil.beginOfQuarter(now);
                endTime = DateUtil.endOfQuarter(now);
                break;
            case "半年报":
                // 上半年/下半年 自动计算
                int month = DateUtil.month(now) + 1;
                if (month <= 6) {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-01-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-06-30");
                } else {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-07-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-12-31");
                }
                break;
            case "年报":
                // 本年1月1日 ~ 12月31日
                startTime = DateUtil.beginOfYear(now);
                endTime = DateUtil.endOfYear(now);
                break;
            default:
                // 默认：今天
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
        }
        return new Date[]{startTime, endTime};
    }
}
