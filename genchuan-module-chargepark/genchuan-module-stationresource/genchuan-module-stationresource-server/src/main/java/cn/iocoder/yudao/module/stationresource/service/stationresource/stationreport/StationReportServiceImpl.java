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
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.StationOpReportRespVO;
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
     * 片区信息 Mapper
     */
    @Resource
    private AreaInfoMapper areaInfoMapper;

    /**
     * 场站信息 Mapper
     */
    @Resource
    private StationInfoMapper stationInfoMapper;

    /**
     * 车位信息 Mapper
     */
    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    /**
     * 时段权限 Mapper
     */
    @Resource
    private TimePermissionMapper timePermissionMapper;

    /**
     * 收费规则 Mapper
     */
    @Resource
    private FeeRuleMapper feeRuleMapper;

    /**
     * 充停联动 Mapper
     */
    @Resource
    private ChargeParkLinkMapper chargeParkLinkMapper;

    /**
     * 欠费补缴 Mapper
     */
    @Resource
    private DebtExpandMapper debtExpandMapper;

    /**
     * 押金方案 Mapper
     */
    @Resource
    private DepositPlanMapper depositPlanMapper;

    /**
     * 获得场站资源报表分页
     *
     * @param pageReqVO 分页查询条件
     * @return 分页结果
     */
    @Override
    public PageResult<StationReportDO> getReportPage(StationReportPageReqVO pageReqVO) {
        // 构建查询条件
        LambdaQueryWrapper<StationReportDO> qw = new LambdaQueryWrapper<StationReportDO>()
                // 报表周期查询
                .eq(StrUtil.isNotBlank(pageReqVO.getReportCycle()), StationReportDO::getReportCycle, pageReqVO.getReportCycle())
                // 生成状态查询
                .eq(StrUtil.isNotBlank(pageReqVO.getGenerateStatus()), StationReportDO::getGenerateStatus, pageReqVO.getGenerateStatus())
                // 时间范围查询
                .between(StrUtil.isAllNotBlank(pageReqVO.getStartTime(), pageReqVO.getEndTime()),
                        StationReportDO::getReportStartTime, pageReqVO.getStartTime(), pageReqVO.getEndTime())
                // 按ID倒序
                .orderByDesc(StationReportDO::getId);

        // 分页查询
        IPage<StationReportDO> page = stationReportMapper.selectPage(MyBatisUtils.buildPage(pageReqVO), qw);
//        return PageResult.of(page);
        return null;
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

    /**
     * 创建并生成场站资源报表
     *
     * @param reqVO 创建参数
     * @return 生成结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> createReport(StationReportCreateReqVO reqVO) {
        // 1. 构建报表DO，设置初始状态：生成中
        StationReportDO report = BeanUtils.toBean(reqVO, StationReportDO.class);
        report.setGenerateStatus("生成中");
        String username = SecurityFrameworkUtils.getLoginUserNickname() != null ? SecurityFrameworkUtils.getLoginUserNickname() : "系统";
        report.setOperator(username);
        report.setExportCount(0L);
        report.setGenerateTime(null);

        // 2. 插入数据库
        stationReportMapper.insert(report);

        // 3. 异步执行数据统计
        asyncGenerateReport(report.getId(), reqVO);

        // 4. 返回前端提示信息
        Map<String, Object> map = new HashMap<>();
        map.put("id", report.getId());
        map.put("success", true);
        map.put("msg", "报表生成中，生成完成后自动刷新");
        return map;
    }

    /**
     * 异步生成报表数据（统计业务数据）
     *
     * @param reportId 报表ID
     * @param reqVO    参数
     */
    @Async
    public void asyncGenerateReport(Long reportId, StationReportCreateReqVO reqVO) {
        try {
            log.info("[asyncGenerateReport] 开始生成报表：{}", reportId);

            // 执行统计逻辑，获取数据
            StationReportDO update = buildRealTimeData(reqVO);
            update.setId(reportId);
            update.setGenerateStatus("已生成");
            update.setGenerateTime(LocalDateTime.now());

            // 更新报表状态和统计数据
            stationReportMapper.updateById(update);
            log.info("[asyncGenerateReport] 报表生成成功：{}", reportId);
        } catch (Exception e) {
            // 异常时标记为生成失败
            log.error("[asyncGenerateReport] 报表生成失败", e);
            StationReportDO update = new StationReportDO();
            update.setId(reportId);
            update.setGenerateStatus("生成失败");
            stationReportMapper.updateById(update);
        }
    }

    /**
     * 实时统计所有业务数据，组装报表信息
     *
     * @param reqVO 请求参数
     * @return 统计后的报表DO
     */
    private StationReportDO buildRealTimeData(StationReportCreateReqVO reqVO) {
//        StationReportDO data = new StationReportDO();
//
//        // 1. 统计总片区数量 → Long
//        Long totalAreaNum = areaInfoMapper.selectCount(new LambdaQueryWrapper<AreaInfoDO>()
//                .eq(AreaInfoDO::getDeleted, false));
//        data.setTotalAreaNum(totalAreaNum);
//
//        // 2. 统计覆盖场站总数 → Integer
//        Long coverStationNum = areaInfoMapper.selectSumStationCount();
//        data.setCoverStationNum(coverStationNum == null ? 0 : coverStationNum);
//
//        // 3. 统计总站场数量 → Long
//        Long totalStationNum = stationInfoMapper.selectCount(new LambdaQueryWrapper<StationInfoDO>()
//                .eq(StationInfoDO::getDeleted, false));
//        data.setTotalStationNum(totalStationNum);
//
//        // 4. 统计正常运营（已生效）场站数量 → Long
//        Long normalStationNum = stationInfoMapper.selectCount(new LambdaQueryWrapper<StationInfoDO>()
//                .eq(StationInfoDO::getDeleted, false)
//                .eq(StationInfoDO::getStatus, "已生效"));
//        data.setNormalOperateStationNum(normalStationNum);
//
//        // 5. 统计总车位数 → Long
//        Long totalSpaceNum = parkingSpaceInfoMapper.selectCount(new LambdaQueryWrapper<ParkingSpaceInfoDO>()
//                .eq(ParkingSpaceInfoDO::getDeleted, false));
//        data.setTotalSpaceNum(totalSpaceNum);
//
//        // 6. 统计可用车位（空闲状态） → Long
//        Long availableSpaceNum = parkingSpaceInfoMapper.selectCount(new LambdaQueryWrapper<ParkingSpaceInfoDO>()
//                .eq(ParkingSpaceInfoDO::getDeleted, false)
//                .eq(ParkingSpaceInfoDO::getRealStatus, "空闲"));
//        data.setAvailableSpaceNum(availableSpaceNum);
//
//        // 7. 统计已生效的规则总数 → Long
//        Long ruleNum = 0L;
//        ruleNum += timePermissionMapper.selectCount(new LambdaQueryWrapper<TimePermissionDO>()
//                .eq(TimePermissionDO::getDeleted, false).eq(TimePermissionDO::getStatus, "已生效"));
//        ruleNum += feeRuleMapper.selectCount(new LambdaQueryWrapper<FeeRuleDO>()
//                .eq(FeeRuleDO::getDeleted, false).eq(FeeRuleDO::getStatus, "已生效"));
//        ruleNum += chargeParkLinkMapper.selectCount(new LambdaQueryWrapper<ChargeParkLinkDO>()
//                .eq(ChargeParkLinkDO::getDeleted, false).eq(ChargeParkLinkDO::getStatus, "已生效"));
//        data.setEffectiveRuleNum(ruleNum);
//
//        // 8. 统计追缴完成率
//        BigDecimal recoveryRate = debtExpandMapper.selectAvgRecoveryRate();
//        data.setRecoveryRate(Objects.requireNonNullElse(recoveryRate, BigDecimal.ZERO));
//
//        // 9. 统计押金订单数量
//        Integer depositOrderNum = depositPlanMapper.selectSumDepositOrderCount();
//        data.setDepositOrderNum(Objects.requireNonNullElse(depositOrderNum, 0));
//
//        // 10. 订单量与营收（预留扩展）
//        data.setOrderNum(0L);
//        data.setIncome(BigDecimal.ZERO);

//        return data;

        return null;
    }

    /**
     * 导出场站资源报表
     *
     * @param reqVO    查询条件
     * @param response 响应
     */
    @Override
    public void exportReport(StationReportPageReqVO reqVO, HttpServletResponse response) {
        // 报表导出逻辑，可使用ExcelUtils实现
        log.info("exportReport 导出完成");
    }

    /**
     * 获取场站报表图表数据
     *
     * @param reqVO 查询参数
     * @return 图表VO
     */
    @Override
    public StationReportChartRespVO getReportChart(StationReportChartReqVO reqVO) {
//        // 获取统计数据
//        StationReportDO data = buildRealTimeData(reqVO);
//
//        // 转换为卡片数据
//        StationReportChartRespVO vo = new StationReportChartRespVO();
//        StationReportChartRespVO.CardData card = BeanUtils.toBean(data, StationReportChartRespVO.CardData.class);
//        vo.setCardData(card);
//
//        // 地图、柱状图、折线图（预留扩展）
//        vo.setMapData(Collections.emptyList());
//        vo.setBarData(Collections.emptyList());
//        vo.setLineData(Collections.emptyList());
//
//        return vo;
        return null;
    }

    @Override
    public StationReportDO getReportPage2(StationOpReportCreateReqVO reqVO) {
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


        // TODO 插入
        stationReportMapper.insert(respVO);
        // 3. 封装最终返回数据
        return respVO;
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


        // TODO 插入
        stationReportMapper.insert(respVO);
        // 3. 封装最终返回数据
        return respVO.getId();
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
