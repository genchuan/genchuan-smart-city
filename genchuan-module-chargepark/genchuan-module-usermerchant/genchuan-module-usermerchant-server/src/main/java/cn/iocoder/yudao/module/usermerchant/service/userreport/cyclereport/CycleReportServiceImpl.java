package cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport;

import cn.hutool.core.collection.CollUtil;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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
    @LogRecord(type = TYPE_CYCLE_REPORT, subType = SUB_TYPE_CREATE_CYCLE_REPORT,
            bizNo = "{{#report.id}}",
            success = SUCCESS_CREATE_CYCLE_REPORT)
    public CycleReportCreateRespVO createCycleReport(CycleReportCreateReqVO createReqVO) {
        // 转换为 VO
        CycleReportDO report = new CycleReportDO();
        report.setReportCycle(createReqVO.getReportCycle());
        report.setStatStartTime(createReqVO.getStatStartTime());
        report.setStatEndTime(createReqVO.getStatEndTime());
        report.setReportName(createReqVO.getReportName());
        report.setRemark(createReqVO.getRemark());
        report.setTenantId(createReqVO.getTenantId());
        report.setReportStatus("已生成");
        report.setCreateTime(LocalDateTime.now());
        report.setCreator("SYSTEM");
        report.setUpdateTime(LocalDateTime.now());
        report.setUpdater("SYSTEM");

        // 实时统计图表数据
        LocalDateTime start = report.getStatStartTime();
        LocalDateTime end = report.getStatEndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String statTime = start.format(formatter) + "-" + end.format(formatter);
        report.setStatTime(statTime);

        // 统计卡片数据
        CycleReportChartRespVO.CardData cardData = computeCardData(start, end);

        // 设置卡片数据到 report
        report.setNewUserCount(cardData.getNewUserCount());
        report.setBindCarCount(cardData.getBindCarCount());
        report.setPlateAuthCount(cardData.getPlateAuthCount());
        report.setNewMerchantCount(cardData.getNewMerchantCount());
        report.setLinkMerchantCount(cardData.getLinkMerchantCount());
        report.setRechargeAmount(cardData.getRechargeAmount());
        report.setSendCouponCount(cardData.getSendCouponCount());
        report.setNewGroupCount(cardData.getNewGroupCount());
        report.setNewMemberCount(cardData.getNewMemberCount());
        report.setAvgCreditScore(cardData.getAvgCreditScore());


        // 插入
        cycleReportMapper.insert(report);
        CycleReportCreateRespVO createRespVO = new CycleReportCreateRespVO();
        createRespVO.setId(report.getId());
        createRespVO.setSuccess(Boolean.TRUE);
        // 记录操作日志上下文
        LogRecordContext.putVariable("report", report);
        // 返回
        return createRespVO;
    }

    private void validateCycleReportExists(Long id) {
        if (cycleReportMapper.selectById(id) == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CycleReportGetRespVO getCycleReport(Long id) {
        CycleReportDO report = cycleReportMapper.selectById(id);
        if (report == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
        // 2. 转换为 VO
        CycleReportGetRespVO respVO = BeanUtils.toBean(report, CycleReportGetRespVO.class);

        // 3. 实时统计图表数据
        LocalDateTime start = report.getStatStartTime();
        LocalDateTime end = report.getStatEndTime();
        String granularity = inferGranularity(report.getReportCycle());
        // 折线图
        Map<String, List<Map<String, Object>>> lineData = new HashMap<>();
        lineData.put("userGrowth", cycleReportMapper.selectUserGrowthTrend(start, end, granularity));
        lineData.put("plateAuth", cycleReportMapper.selectPlateAuthTrend(start, end, granularity));
        lineData.put("rechargeAmount", cycleReportMapper.selectRechargeAmountTrend(start, end, granularity));
        lineData.put("sendCoupon", cycleReportMapper.selectSendCouponTrend(start, end, granularity));

        // 柱状图
        Map<String, List<Map<String, Object>>> barData = new HashMap<>();
        barData.put("userType", cycleReportMapper.selectUserTypeDistribution(start, end));
        barData.put("carType", cycleReportMapper.selectCarTypeDistribution(start, end));
        barData.put("merchantType", cycleReportMapper.selectMerchantTypeDistribution(start, end));
        barData.put("groupType", cycleReportMapper.selectGroupTypeDistribution(start, end));

        // 饼图
        Map<String, List<Map<String, Object>>> pieData = new HashMap<>();
        pieData.put("creditLevel", cycleReportMapper.selectCreditLevelDistribution(start, end));
        pieData.put("memberLevel", cycleReportMapper.selectMemberLevelDistribution(start, end));

        respVO.setLineData(lineData);
        respVO.setBarData(barData);
        respVO.setPieData(pieData);

        return respVO;
    }

    @Override
    public PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO) {
        return cycleReportMapper.selectPage(pageReqVO);
    }

    @Override
    public CycleReportChartRespVO getChartData(CycleReportChartReqVO reqVO) {
        LocalDateTime start = reqVO.getStatStartTime();
        LocalDateTime end = reqVO.getStatEndTime();
        String granularity = inferGranularity(reqVO.getReportCycle());

        // 卡片数据
        CycleReportChartRespVO.CardData cardData = computeCardData(start, end);

        // 2. 折线图数据
        Map<String, List<Map<String, Object>>> lineData = new HashMap<>();
        lineData.put("userGrowth", cycleReportMapper.selectUserGrowthTrend(start, end, granularity));
        lineData.put("plateAuth", cycleReportMapper.selectPlateAuthTrend(start, end, granularity));
        lineData.put("rechargeAmount", cycleReportMapper.selectRechargeAmountTrend(start, end, granularity));
        lineData.put("sendCoupon", cycleReportMapper.selectSendCouponTrend(start, end, granularity));

        // 3. 柱状图数据
        Map<String, List<Map<String, Object>>> barData = new HashMap<>();
        barData.put("userType", cycleReportMapper.selectUserTypeDistribution(start, end));
        barData.put("carType", cycleReportMapper.selectCarTypeDistribution(start, end));
        barData.put("merchantType", cycleReportMapper.selectMerchantTypeDistribution(start, end));
        barData.put("groupType", cycleReportMapper.selectGroupTypeDistribution(start, end));

        // 4. 饼图数据
        Map<String, List<Map<String, Object>>> pieData = new HashMap<>();
        pieData.put("creditLevel", cycleReportMapper.selectCreditLevelDistribution(start, end));
        pieData.put("memberLevel", cycleReportMapper.selectMemberLevelDistribution(start, end));

        CycleReportChartRespVO respVO = new CycleReportChartRespVO();
        respVO.setCardData(cardData);
        respVO.setLineData(lineData);
        respVO.setBarData(barData);
        respVO.setPieData(pieData);
        return respVO;
    }

    private String inferGranularity(String reportCycle) {
        if (reportCycle.contains("日") || reportCycle.contains("周")) return "day";
        if (reportCycle.contains("月") || reportCycle.contains("季") || reportCycle.contains("半年")) return "month";
        if (reportCycle.contains("年")) return "year";
        return "day";
    }

    /**
     * 统计卡片数据（实时计算）
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 卡片数据
     */
    private CycleReportChartRespVO.CardData computeCardData(LocalDateTime start, LocalDateTime end) {
        CycleReportChartRespVO.CardData cardData = new CycleReportChartRespVO.CardData();

        // 新增用户数
        Long newUserCount = userInfoMapper.selectCount(new LambdaQueryWrapper<UserInfoDO>()
                .between(UserInfoDO::getCreateTime, start, end)
                .eq(UserInfoDO::getDeleted, 0));
        cardData.setNewUserCount(newUserCount.intValue());

        // 绑定车辆数
        Long bindCarCount = userCarMapper.selectCount(new LambdaQueryWrapper<UserCarDO>()
                .between(UserCarDO::getCreateTime, start, end)
                .eq(UserCarDO::getDeleted, 0));
        cardData.setBindCarCount(bindCarCount.intValue());

        // 车牌认证量
        Long plateAuthCount = plateAuthMapper.selectCount(new LambdaQueryWrapper<PlateAuthDO>()
                .between(PlateAuthDO::getCreateTime, start, end)
                .eq(PlateAuthDO::getDeleted, 0));
        cardData.setPlateAuthCount(plateAuthCount.intValue());

        // 新增商户数
        Long newMerchantCount = merchantInfoMapper.selectCount(new LambdaQueryWrapper<MerchantInfoDO>()
                .between(MerchantInfoDO::getCreateTime, start, end)
                .eq(MerchantInfoDO::getDeleted, 0));
        cardData.setNewMerchantCount(newMerchantCount.intValue());

        // 对接商户数
        Long linkMerchantCount = merchantLinkMapper.selectCount(new LambdaQueryWrapper<MerchantLinkDO>()
                .between(MerchantLinkDO::getCreateTime, start, end)
                .eq(MerchantLinkDO::getDeleted, 0));
        cardData.setLinkMerchantCount(linkMerchantCount.intValue());

        // 充值金额（只统计 status = '已支付'）
        QueryWrapper<MerchantRechargeDO> rechargeWrapper = new QueryWrapper<>();
        rechargeWrapper.select("COALESCE(SUM(amount), 0)")
                .between("create_time", start, end)
                .eq("status", "已支付")
                .eq("deleted", 0);
        BigDecimal rechargeAmount = (BigDecimal) merchantRechargeMapper.selectObjs(rechargeWrapper).get(0);
        cardData.setRechargeAmount(rechargeAmount != null ? rechargeAmount : BigDecimal.ZERO);

        // 发券量
        Long sendCouponCount = merchantSendCouponMapper.selectCount(new LambdaQueryWrapper<MerchantSendCouponDO>()
                .between(MerchantSendCouponDO::getCreateTime, start, end)
                .eq(MerchantSendCouponDO::getDeleted, 0));
        cardData.setSendCouponCount(sendCouponCount.intValue());

        // 新增集团数
        Long newGroupCount = groupInfoMapper.selectCount(new LambdaQueryWrapper<GroupInfoDO>()
                .between(GroupInfoDO::getCreateTime, start, end)
                .eq(GroupInfoDO::getDeleted, 0));
        cardData.setNewGroupCount(newGroupCount.intValue());

        // 会员新增数
        Long newMemberCount = memberUserMapper.selectCount(new LambdaQueryWrapper<MemberUserDO>()
                .between(MemberUserDO::getCreateTime, start, end)
                .eq(MemberUserDO::getDeleted, 0));
        cardData.setNewMemberCount(newMemberCount.intValue());

        // 平均信用分
        QueryWrapper<UserCreditDO> creditWrapper = new QueryWrapper<>();
        creditWrapper.select("COALESCE(AVG(credit_score), 0)")
                .between("create_time", start, end)
                .eq("deleted", 0);
        BigDecimal avgCredit = (BigDecimal) userCreditMapper.selectObjs(creditWrapper).get(0);
        cardData.setAvgCreditScore(avgCredit != null ? avgCredit.intValue() : 0);

        return cardData;
    }

    @Override
    public void incrementExportCountByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        LambdaUpdateWrapper<CycleReportDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(CycleReportDO::getId, ids)
                .setSql("export_count = export_count + 1");
        cycleReportMapper.update(null, wrapper);
    }

}