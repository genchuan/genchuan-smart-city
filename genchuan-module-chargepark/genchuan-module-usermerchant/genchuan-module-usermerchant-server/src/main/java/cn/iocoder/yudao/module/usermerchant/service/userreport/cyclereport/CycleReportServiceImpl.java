package cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo.UserCreditChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.GroupInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.MemberUserChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo.MerchantInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo.MerchantRechargeChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.MerchantSendCouponChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.UserInfoChartRespVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit.UserCreditDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantinfo.MerchantInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.creditmgmt.usercredit.UserCreditMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.groupclient.groupinfo.GroupInfoMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser.MemberUserMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo.MerchantInfoMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantlink.MerchantLinkMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantrecharge.MerchantRechargeMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantsendcoupon.MerchantSendCouponMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.plateauth.PlateAuthMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.usercar.UserCarMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo.UserInfoMapper;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.userreport.cyclereport.CycleReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 周期报表存储 Service 实现类
 *
 * @author 亘川智城
 */
@Slf4j
@Service
@Validated
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserCarMapper userCarMapper;

    @Resource
    private PlateAuthMapper plateAuthMapper;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Resource
    private MerchantLinkMapper merchantLinkMapper;

    @Resource
    private MerchantRechargeMapper merchantRechargeMapper;

    @Resource
    private MerchantSendCouponMapper merchantSendCouponMapper;

    @Resource
    private GroupInfoMapper groupInfoMapper;

    @Resource
    private MemberUserMapper memberUserMapper;

    @Resource
    private UserCreditMapper userCreditMapper;

    @Override
    public Long createCycleReport(CycleReportSaveReqVO createReqVO) {
        // 插入
        CycleReportDO cycleReport = BeanUtils.toBean(createReqVO, CycleReportDO.class);
        cycleReportMapper.insert(cycleReport);

        // 返回
        return cycleReport.getId();
    }

    @Override
    public void updateCycleReport(CycleReportSaveReqVO updateReqVO) {
        // 校验存在
        validateCycleReportExists(updateReqVO.getId());
        // 更新
        CycleReportDO updateObj = BeanUtils.toBean(updateReqVO, CycleReportDO.class);
        cycleReportMapper.updateById(updateObj);
    }

    @Override
    public void deleteCycleReport(Long id) {
        // 校验存在
        validateCycleReportExists(id);
        // 删除
        cycleReportMapper.deleteById(id);
    }

    @Override
        public void deleteCycleReportListByIds(List<Long> ids) {
        // 删除
        cycleReportMapper.deleteByIds(ids);
        }


    private void validateCycleReportExists(Long id) {
        if (cycleReportMapper.selectById(id) == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public CycleReportDO getCycleReport(Long id) {
        return cycleReportMapper.selectById(id);
    }

    @Override
    public PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO) {
        return cycleReportMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CycleReportGenerateRespVO generateCycleReport(CycleReportGenerateReqVO generateReqVO) {
        // ========== 1. 自动计算时间（若非自定义报表） ==========
        String reportCycle = generateReqVO.getReportCycle();
        if (StrUtil.isNotBlank(reportCycle) && !"自定义报表".equals(reportCycle)) {
            Date[] dates = autoCalcReportTime(reportCycle);
            generateReqVO.setStatStartTime(LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault()));
            generateReqVO.setStatEndTime(LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault()));
        }

        LocalDateTime start = generateReqVO.getStatStartTime();
        LocalDateTime end = generateReqVO.getStatEndTime();
        Long tenantId = generateReqVO.getTenantId();

        // ========== 2. 统计卡片数据 ==========
        CycleReportDO report = new CycleReportDO();
        report.setReportCycle(reportCycle);
        report.setStatStartTime(start);
        report.setStatEndTime(end);
        report.setReportName(generateReqVO.getReportName());
        report.setRemark(generateReqVO.getRemark());
        report.setReportStatus("已生成");
        report.setExportCount(0);
        report.setCreator(SecurityFrameworkUtils.getLoginUserNickname()); // 获取当前登录用户昵称
        report.setCreateTime(LocalDateTime.now());

        // 2.1 新增用户数
        Long newUserCount = userInfoMapper.selectCount(new LambdaQueryWrapper<UserInfoDO>()
                .between(UserInfoDO::getCreateTime, start, end)
                .eq(UserInfoDO::getDeleted, 0));
        report.setNewUserCount(newUserCount.intValue());

        // 2.2 绑定车辆数
        Long bindCarCount = userCarMapper.selectCount(new LambdaQueryWrapper<UserCarDO>()
                .between(UserCarDO::getCreateTime, start, end)
                .eq(UserCarDO::getDeleted, 0));
        report.setBindCarCount(bindCarCount.intValue());

        // 2.3 车牌认证量
        Long plateAuthCount = plateAuthMapper.selectCount(new LambdaQueryWrapper<PlateAuthDO>()
                .between(PlateAuthDO::getCreateTime, start, end)
                .eq(PlateAuthDO::getDeleted, 0));
        report.setPlateAuthCount(plateAuthCount.intValue());

        // 2.4 新增商户数
        Long newMerchantCount = merchantInfoMapper.selectCount(new LambdaQueryWrapper<MerchantInfoDO>()
                .between(MerchantInfoDO::getCreateTime, start, end)
                .eq(MerchantInfoDO::getDeleted, 0));
        report.setNewMerchantCount(newMerchantCount.intValue());

        // 2.5 对接商户数
        Long linkMerchantCount = merchantLinkMapper.selectCount(new LambdaQueryWrapper<MerchantLinkDO>()
                .between(MerchantLinkDO::getCreateTime, start, end)
                .eq(MerchantLinkDO::getDeleted, 0));
        report.setLinkMerchantCount(linkMerchantCount.intValue());

        // 2.6 充值金额
        BigDecimal rechargeAmount = merchantRechargeMapper.selectTotalRechargeAmount(start, end);
        report.setRechargeAmount(rechargeAmount != null ? rechargeAmount : BigDecimal.ZERO);

        // 2.7 发券量
        Long sendCouponCount = merchantSendCouponMapper.selectCount(new LambdaQueryWrapper<MerchantSendCouponDO>()
                .between(MerchantSendCouponDO::getCreateTime, start, end)
                .eq(MerchantSendCouponDO::getDeleted, 0));
        report.setSendCouponCount(sendCouponCount.intValue());

        // 2.8 新增集团数
        Long newGroupCount = groupInfoMapper.selectCount(new LambdaQueryWrapper<GroupInfoDO>()
                .between(GroupInfoDO::getCreateTime, start, end)
                .eq(GroupInfoDO::getDeleted, 0));
        report.setNewGroupCount(newGroupCount.intValue());

        // 2.9 会员新增数
        Long newMemberCount = memberUserMapper.selectCount(new LambdaQueryWrapper<MemberUserDO>()
                .between(MemberUserDO::getCreateTime, start, end)
                .eq(MemberUserDO::getDeleted, 0));
        report.setNewMemberCount(newMemberCount.intValue());

        // 2.10 平均信用分
        BigDecimal avgCreditScore = BigDecimal.valueOf(userCreditMapper.selectAvgCreditScore(start, end));
        report.setAvgCreditScore(avgCreditScore != null ? avgCreditScore.intValue() : 0);

        // ========== 3. 图表数据（折线图/柱状图/饼图） ==========
        Map<String, Object> distribution = new HashMap<>();
        distribution.put("lineData", buildLineData(start, end, tenantId));
        distribution.put("barData", buildBarData(start, end, tenantId));
        distribution.put("pieData", buildPieData(start, end, tenantId));
        report.setDistributionData(JSONUtil.toJsonStr(distribution));

        // ========== 4. 保存报表 ==========
        cycleReportMapper.insert(report);

        // ========== 5. 返回响应 ==========
        CycleReportGenerateRespVO respVO = new CycleReportGenerateRespVO();
        respVO.setId(report.getId());
        respVO.setGenerateStatus("已生成");
        return respVO;
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

    private Map<String, List<Map<String, Object>>> buildLineData(LocalDateTime start, LocalDateTime end, Long tenantId) {
        Map<String, List<Map<String, Object>>> lineData = new HashMap<>();
        // 用户增长趋势
        List<UserInfoChartRespVO.UserGrowthTrendVO> userGrowthList = userInfoMapper.selectUserGrowthTrend(start, end, "day");
        lineData.put("userGrowth", toLineMap(userGrowthList, UserInfoChartRespVO.UserGrowthTrendVO::getDate, UserInfoChartRespVO.UserGrowthTrendVO::getCount));

        // 车牌认证趋势（假设返回 List<PlateAuthTrendVO>，字段 date, count）
        List<PlateAuthChartRespVO.AuthTrendVO> plateAuthList = plateAuthMapper.selectAuthTrend(start, end, "day");
        lineData.put("plateAuth", toLineMap(plateAuthList,
                PlateAuthChartRespVO.AuthTrendVO::getDate,
                PlateAuthChartRespVO.AuthTrendVO::getCount));

        // 充值金额趋势（字段 date, amount）
        List<MerchantRechargeChartRespVO.RechargeAmountTrendVO> rechargeList = merchantRechargeMapper.selectRechargeAmountTrend(start, end, "day");
        lineData.put("rechargeAmount", rechargeList.stream().map(vo -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", vo.getDate());
            map.put("amount", vo.getAmount());
            return map;
        }).collect(Collectors.toList()));

        // 发券量趋势（假设返回 List<SendCouponTrendVO>，字段 date, count）
        List<MerchantSendCouponChartRespVO.SendCountTrendVO> sendCouponList = merchantSendCouponMapper.selectSendCountTrend(start, end, "day");
        lineData.put("sendCoupon", toLineMap(sendCouponList,
                MerchantSendCouponChartRespVO.SendCountTrendVO::getDate,
                MerchantSendCouponChartRespVO.SendCountTrendVO::getCount));

        return lineData;

    }

    private Map<String, List<Map<String, Object>>> buildBarData(LocalDateTime start, LocalDateTime end, Long tenantId) {
        Map<String, List<Map<String, Object>>> barData = new HashMap<>();

//        // 用户类型分布
//        List<UserInfoChartRespVO.UserTypeDistributionVO> userTypeList = userInfoMapper.selectUserTypeDistribution(start, end);
//        barData.put("userType", toChartMap(userTypeList,
//                UserInfoChartRespVO.UserTypeDistributionVO::getType,
//                UserInfoChartRespVO.UserTypeDistributionVO::getCount));
//
//        // 车辆类型分布
//        List<UserCarChartRespVO.CarTypeDistributionVO> carTypeList = userCarMapper.selectCarTypeDistribution(start, end);
//        barData.put("carType", toChartMap(carTypeList,
//                UserCarChartRespVO.CarTypeDistributionVO::getType,
//                UserCarChartRespVO.CarTypeDistributionVO::getCount));
//
//        // 商户类型分布（假设类似结构）
//        List<MerchantInfoChartRespVO.MerchantTypeDistributionVO> merchantTypeList = merchantInfoMapper.selectMerchantTypeDistribution(start, end);
//        barData.put("merchantType", toChartMap(merchantTypeList,
//                MerchantInfoChartRespVO.MerchantTypeDistributionVO::getType,
//                MerchantInfoChartRespVO.MerchantTypeDistributionVO::getCount));
//
//        // 集团类型分布
//        List<GroupInfoChartRespVO.GroupTypeDistributionVO> groupTypeList = groupInfoMapper.selectGroupTypeDistribution(start, end);
//        barData.put("groupType", toChartMap(groupTypeList,
//                GroupInfoChartRespVO.GroupTypeDistributionVO::getType,
//                GroupInfoChartRespVO.GroupTypeDistributionVO::getCount));

        return barData;
    }

    private Map<String, List<Map<String, Object>>> buildPieData(LocalDateTime start, LocalDateTime end, Long tenantId) {
        Map<String, List<Map<String, Object>>> pieData = new HashMap<>();

//        // 信用等级分布
//        List<UserCreditChartRespVO.CreditLevelDistributionVO> creditList = userCreditMapper.selectCreditLevelDistribution(start, end);
//        pieData.put("creditLevel", toChartMap(creditList,
//                UserCreditChartRespVO.CreditLevelDistributionVO::getType,
//                UserCreditChartRespVO.CreditLevelDistributionVO::getCount));
//
//        // 会员等级分布
//        List<MemberUserChartRespVO.MemberLevelDistributionVO> memberList = memberUserMapper.selectMemberLevelDistribution(start, end);
//        pieData.put("memberLevel", toChartMap(memberList,
//                MemberUserChartRespVO.MemberLevelDistributionVO::getLevel,
//                MemberUserChartRespVO.MemberLevelDistributionVO::getCount));

        return pieData;
    }

    /**
     * 将强类型 VO 列表转换为 Map 列表
     * @param list VO 列表
     * @param keyExtractor 提取键的函数（例如 UserGrowthTrendVO::getDate）
     * @param valueExtractor 提取值的函数（例如 UserGrowthTrendVO::getCount）
     * @param <T> VO 类型
     * @return List<Map<String, Object>>
     */
    private <T> List<Map<String, Object>> toMapList(List<T> list,
                                                    Function<T, Object> keyExtractor,
                                                    Function<T, Object> valueExtractor) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", keyExtractor.apply(item));
            map.put("count", valueExtractor.apply(item));
            return map;
        }).collect(Collectors.toList());
    }

    /**
     * 转换折线图数据（通用字段：date, count）
     */
    private <T> List<Map<String, Object>> toLineMap(List<T> list,
                                                    Function<T, Object> dateGetter,
                                                    Function<T, Object> countGetter) {
        if (CollUtil.isEmpty(list)) return Collections.emptyList();
        return list.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", dateGetter.apply(item));
            map.put("count", countGetter.apply(item));
            return map;
        }).collect(Collectors.toList());
    }

    /**
     * 转换柱状图/饼图数据（通用字段：name, value）
     */
    private <T> List<Map<String, Object>> toChartMap(List<T> list,
                                                     Function<T, Object> nameGetter,
                                                     Function<T, Object> valueGetter) {
        if (CollUtil.isEmpty(list)) return Collections.emptyList();
        return list.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", nameGetter.apply(item));
            map.put("value", valueGetter.apply(item));
            return map;
        }).collect(Collectors.toList());
    }

}