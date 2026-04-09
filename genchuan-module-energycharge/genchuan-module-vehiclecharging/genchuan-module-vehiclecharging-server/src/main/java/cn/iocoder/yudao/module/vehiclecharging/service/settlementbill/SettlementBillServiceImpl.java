package cn.iocoder.yudao.module.vehiclecharging.service.settlementbill;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillSaveReqVO;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.settlementbill.SettlementBillMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 结算单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SettlementBillServiceImpl implements SettlementBillService {

    @Resource
    private SettlementBillMapper settlementBillMapper;

    // ==================== 图表统计 ====================
    @Override
    public SettlementBillSummaryRespVO getSettlementBillChart(SettlementBillChartReqVO reqVO) {
        LambdaQueryWrapper<SettlementBillDO> qw = buildTimeQuery(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());

        List<SettlementBillDO> all = settlementBillMapper.selectList(qw);
        long total = all.size();
        long pending = all.stream().filter(b -> "待审核".equals(b.getBillStatus())).count();
        long completed = all.stream().filter(b -> "已完成".equals(b.getBillStatus())).count();
        BigDecimal totalAmount = all.stream().map(SettlementBillDO::getSettlementAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Long> dateGroup = all.stream()
                .collect(Collectors.groupingBy(b -> b.getCreateTime().toLocalDate().toString(), Collectors.counting()));

        List<SettlementBillSummaryRespVO.LineItem> line = dateGroup.entrySet().stream().map(e -> {
            SettlementBillSummaryRespVO.LineItem item = new SettlementBillSummaryRespVO.LineItem();
            item.setDate(e.getKey());
            item.setCount(e.getValue().intValue());
            return item;
        }).sorted(Comparator.comparing(SettlementBillSummaryRespVO.LineItem::getDate)).collect(Collectors.toList());

        Map<String, BigDecimal> coopAmount = all.stream()
                .collect(Collectors.groupingBy(SettlementBillDO::getCooperator,
                        Collectors.reducing(BigDecimal.ZERO, SettlementBillDO::getSettlementAmount, BigDecimal::add)));

        List<SettlementBillSummaryRespVO.BarItem> bar = coopAmount.entrySet().stream().map(e -> {
            SettlementBillSummaryRespVO.BarItem item = new SettlementBillSummaryRespVO.BarItem();
            item.setName(e.getKey());
            item.setAmount(e.getValue());
            return item;
        }).collect(Collectors.toList());

        SettlementBillSummaryRespVO resp = new SettlementBillSummaryRespVO();
        resp.setTotalBillCount((int) total);
        resp.setPendingAuditCount((int) pending);
        resp.setCompletedCount((int) completed);
        resp.setTotalSettlementAmount(totalAmount);
        resp.setLineData(line);
        resp.setBarData(bar);
        return resp;
    }

    /**
     * 查询结算账单的每日趋势
     * 作用：按天分组统计 每天的总账单数 + 已完成账单数
     */
    /**
     * 查询结算账单的每日趋势
     * 作用：按天分组统计 每天的总账单数 + 已完成账单数
     */
    @Override
    public SettlementBillDailyTrendRespVO getDailyTrend(SettlementBillDailyTrendReqVO reqVO) {
        LambdaQueryWrapper<SettlementBillDO> qw = buildTimeQuery(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());
        List<SettlementBillDO> all = settlementBillMapper.selectList(qw);

        Map<String, List<SettlementBillDO>> group = all.stream()
                .collect(Collectors.groupingBy(b -> b.getCreateTime().toLocalDate().toString()));

        List<SettlementBillDailyTrendRespVO.DailyItem> list = group.entrySet().stream().map(e -> {
                    SettlementBillDailyTrendRespVO.DailyItem item = new SettlementBillDailyTrendRespVO.DailyItem();
                    item.setDate(e.getKey());
                    item.setTotalCount(e.getValue().size());
                    item.setCompletedCount((int) e.getValue().stream().filter(b -> "已完成".equals(b.getBillStatus())).count());
                    return item;
                })
                // 👇 👇 👇 【强制按日期从早到晚排序，绝对稳】
                .sorted((d1, d2) -> d1.getDate().compareTo(d2.getDate()))
                .collect(Collectors.toList());

        SettlementBillDailyTrendRespVO resp = new SettlementBillDailyTrendRespVO();
        resp.setList(list);
        return resp;
    }

    @Override
    public SettlementBillCooperatorAmountRespVO getCooperatorAmount(SettlementBillCooperatorAmountReqVO reqVO) {
        LambdaQueryWrapper<SettlementBillDO> qw = buildTimeQuery(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());
        List<SettlementBillDO> all = settlementBillMapper.selectList(qw);

        Map<String, List<SettlementBillDO>> group = all.stream()
                .collect(Collectors.groupingBy(SettlementBillDO::getCooperator));
        List<SettlementBillCooperatorAmountRespVO.CooperatorItem> list = group.entrySet().stream().map(e -> {
            SettlementBillCooperatorAmountRespVO.CooperatorItem item = new SettlementBillCooperatorAmountRespVO.CooperatorItem();
            item.setName(e.getKey());
            item.setCount(e.getValue().size());
            item.setAmount(e.getValue().stream().map(SettlementBillDO::getSettlementAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            return item;
        }).collect(Collectors.toList());

        SettlementBillCooperatorAmountRespVO resp = new SettlementBillCooperatorAmountRespVO();
        resp.setList(list);
        return resp;
    }

    @Override
    public SettlementBillCountRespVO getBillCount(SettlementBillCountReqVO reqVO) {
        LambdaQueryWrapper<SettlementBillDO> qw = buildTimeQuery(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());
        List<SettlementBillDO> all = settlementBillMapper.selectList(qw);

        SettlementBillCountRespVO.StatusCountItem status = new SettlementBillCountRespVO.StatusCountItem();
        status.setPendingAudit((int) all.stream().filter(b -> "待审核".equals(b.getBillStatus())).count());
        status.setAuditPass((int) all.stream().filter(b -> "审核通过".equals(b.getBillStatus())).count());
        status.setSettling((int) all.stream().filter(b -> "结算中".equals(b.getBillStatus())).count());
        status.setCompleted((int) all.stream().filter(b -> "已完成".equals(b.getBillStatus())).count());
        status.setRejected((int) all.stream().filter(b -> "已驳回".equals(b.getBillStatus())).count());

        SettlementBillCountRespVO resp = new SettlementBillCountRespVO();
        resp.setStatusCount(status);
        return resp;
    }

    // ==================== 工具：仅构建时间查询条件 ====================
    private LambdaQueryWrapper<SettlementBillDO> buildTimeQuery(String start, String end) {
        LambdaQueryWrapper<SettlementBillDO> qw = new LambdaQueryWrapper<>();
        qw.eq(SettlementBillDO::getDeleted, false);

        if (start != null && !start.isEmpty()) {
            LocalDateTime startTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochSecond(Long.parseLong(start)), java.time.ZoneId.systemDefault());
            qw.ge(SettlementBillDO::getCreateTime, startTime);
        }
        if (end != null && !end.isEmpty()) {
            LocalDateTime endTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochSecond(Long.parseLong(end)), java.time.ZoneId.systemDefault());
            qw.le(SettlementBillDO::getCreateTime, endTime);
        }
        return qw;
    }
    @Override
    public Long createSettlementBill(SettlementBillSaveReqVO createReqVO) {
        // 插入
        SettlementBillDO settlementBill = BeanUtils.toBean(createReqVO, SettlementBillDO.class);
        settlementBillMapper.insert(settlementBill);

        // 返回
        return settlementBill.getId();
    }

    @Override
    public void updateSettlementBill(SettlementBillSaveReqVO updateReqVO) {
        // 校验存在
        validateSettlementBillExists(updateReqVO.getId());
        // 更新
        SettlementBillDO updateObj = BeanUtils.toBean(updateReqVO, SettlementBillDO.class);
        settlementBillMapper.updateById(updateObj);
    }

    @Override
    public void deleteSettlementBill(Long id) {
        // 校验存在
        validateSettlementBillExists(id);
        // 删除
        settlementBillMapper.deleteById(id);
    }

    @Override
        public void deleteSettlementBillListByIds(List<Long> ids) {
        // 删除
        settlementBillMapper.deleteByIds(ids);
        }


    private void validateSettlementBillExists(Long id) {
        if (settlementBillMapper.selectById(id) == null) {
            throw exception(SETTLEMENT_BILL_NOT_EXISTS);
        }
    }

    @Override
    public SettlementBillDO getSettlementBill(Long id) {
        return settlementBillMapper.selectById(id);
    }

    @Override
    public PageResult<SettlementBillDO> getSettlementBillPage(SettlementBillPageReqVO pageReqVO) {
        return settlementBillMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditSettlementBill(SettlementBillAuditReqVO reqVO) {
        // 1. 查询结算单
        SettlementBillDO bill = getSettlementBill(reqVO.getId());
        if (bill == null) {
            throw exception("结算单不存在");
        }

        // 2. 校验状态：只能审核【待审核】
        if (!"待审核".equals(bill.getBillStatus())) {
            throw exception("只有待审核状态的结算单才能审核");
        }

        // 3. 执行审核更新
        bill.setBillStatus("审核通过");
        bill.setAuditUser(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setAuditTime(LocalDateTime.now());
        bill.setAuditRemark(reqVO.getAuditRemark());
        bill.setUpdater(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setUpdateTime(LocalDateTime.now());

        // 4. 更新数据库
        settlementBillMapper.updateById(bill);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settleSettlementBill(SettlementBillSettleReqVO reqVO) {
        // 1. 查询结算单
        SettlementBillDO bill = getSettlementBill(reqVO.getId());
        if (bill == null) {
            throw exception("结算单不存在");
        }

        // 2. 校验状态：只能结算【审核通过】
        if (!"审核通过".equals(bill.getBillStatus())) {
            throw exception("只有审核通过状态的结算单才能结算");
        }

        // 3. 执行结算更新
        bill.setBillStatus("已完成");
        bill.setSettlementChannel(reqVO.getSettlementChannel());
        bill.setSettlementTime(LocalDateTime.now());
        bill.setUpdater(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setUpdateTime(LocalDateTime.now());

        // 4. 更新数据库
        settlementBillMapper.updateById(bill);
    }

    @Override
    public Integer createBatchSettlementBill(SettlementBillCreateBatchReqVO reqVO) {
        // 1. 生成结算单编号（时间戳+随机数）
        String billCode = "JD"  + (int)((Math.random() * 9000) + 1000);

        // 2. 构建结算单（可替换为真实分账计算逻辑）
        SettlementBillDO bill = new SettlementBillDO();
        bill.setBillCode(billCode);
        bill.setCooperator(reqVO.getCooperator());
        bill.setSettlementCycle(reqVO.getSettlementCycle());

        // ===================== 自动计算金额（示例逻辑，可替换） =====================
        bill.setSettlementAmount(new BigDecimal("1000.00"));   // 结算金额
        bill.setSharingAmount(new BigDecimal("200.00"));        // 分账金额
        bill.setBillStatus("待审核");                           // 默认待审核

        // ===================== 自动填充基础字段 =====================
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        bill.setCreator(String.valueOf(userId));
        bill.setUpdater(String.valueOf(userId));
        bill.setCreateTime(LocalDateTime.now());
        bill.setUpdateTime(LocalDateTime.now());

        // 3. 插入
        settlementBillMapper.insert(bill);

        // 4. 返回生成数量
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectSettlementBill(SettlementBillRejectReqVO reqVO) {
        // 1. 查询结算单
        SettlementBillDO bill = getSettlementBill(reqVO.getId());
        if (bill == null) {
            throw exception("结算单不存在");
        }

        // 2. 校验状态：只有【待审核】才能驳回
        if (!"待审核".equals(bill.getBillStatus())) {
            throw exception("只有待审核状态的结算单才能驳回");
        }

        // 3. 执行驳回更新
        bill.setBillStatus("已驳回");
        bill.setAuditRemark(reqVO.getRejectReason()); // 驳回原因写入审核备注
        bill.setAuditUser(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setAuditTime(LocalDateTime.now());
        bill.setUpdater(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setUpdateTime(LocalDateTime.now());

        // 4. 保存
        settlementBillMapper.updateById(bill);
    }

    // ==================== 5、重新审核 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reauditSettlementBill(SettlementBillReauditReqVO reqVO) {
        // 1. 查询结算单
        SettlementBillDO bill = getSettlementBill(reqVO.getId());
        if (bill == null) {
            throw exception("结算单不存在");
        }

        // 2. 校验：必须是【已驳回】才能重新审核
        if (!"已驳回".equals(bill.getBillStatus())) {
            throw exception("只有已驳回状态的结算单才能重新提交审核");
        }

        // 3. 重置为待审核
        bill.setBillStatus("待审核");
        bill.setUpdater(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setUpdateTime(LocalDateTime.now());

        settlementBillMapper.updateById(bill);
    }

    // ==================== 6、修改备注 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSettlementBillRemark(SettlementBillRemarkReqVO reqVO) {
        // 1. 查询结算单
        SettlementBillDO bill = getSettlementBill(reqVO.getId());
        if (bill == null) {
            throw exception("结算单不存在");
        }

        // 2. 更新备注
        bill.setRemark(reqVO.getRemark());
        bill.setUpdater(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        bill.setUpdateTime(LocalDateTime.now());

        settlementBillMapper.updateById(bill);
    }
}
