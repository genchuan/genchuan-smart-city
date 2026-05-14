package cn.iocoder.yudao.module.inspectop.service.inspectreport;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectreport.InspectReportMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 巡检上报 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectReportServiceImpl implements InspectReportService {

    @Resource
    private InspectReportMapper inspectReportMapper;

    @Override
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = INSPECT_REPORT_CREATE_SUCCESS)
    public Long createInspectReport(InspectReportSaveReqVO createReqVO) {
        // 插入
        InspectReportDO inspectReport = BeanUtils.toBean(createReqVO, InspectReportDO.class);
        inspectReportMapper.insert(inspectReport);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return inspectReport.getId();
    }

    @Override
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = INSPECT_REPORT_UPDATE_SUCCESS)
    public void updateInspectReport(InspectReportSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        InspectReportDO oldInspectReport = validateInspectReportExists(updateReqVO.getId());

        // 2. 更新
        InspectReportDO updateObj = BeanUtils.toBean(updateReqVO, InspectReportDO.class);
        inspectReportMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        InspectReportSaveReqVO oldVO = BeanUtils.toBean(oldInspectReport, InspectReportSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = INSPECT_REPORT_DELETE_SUCCESS)
    public void deleteInspectReport(Long id) {
        // 校验存在
        validateInspectReportExists(id);
        // 删除
        inspectReportMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_DELETE_LIST_SUB_TYPE,
            success = INSPECT_REPORT_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteInspectReportListByIds(List<Long> ids) {
        // 删除
        inspectReportMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回InspectReportDO对象，用于update方法的日志对比
    private InspectReportDO validateInspectReportExists(Long id) {
        InspectReportDO inspectReport = inspectReportMapper.selectById(id);
        if (inspectReport == null) {
            throw exception(INSPECT_REPORT_NOT_EXISTS);
        }
        return inspectReport; // 返回查询到的对象
    }

    @Override
    public InspectReportDO getInspectReport(Long id) {
        return inspectReportMapper.selectById(id);
    }

    @Override
    public PageResult<InspectReportRespVO> getInspectReportPage(InspectReportPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        Page<InspectReportRespVO> mpPage
                = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        Page<InspectReportRespVO> resultPage =
                inspectReportMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_BATCH_AUDIT_SUB_TYPE,
            success = INSPECT_REPORT_BATCH_AUDIT_SUCCESS, bizNo = "")
    public void batchAuditInspectReport(InspectReportBatchAuditReqVO batchAuditReqVO) {
        // 1. 获取参数
        List<Long> ids = batchAuditReqVO.getIds();
        String auditResult = batchAuditReqVO.getAuditResult();
        String auditRemark = batchAuditReqVO.getAuditRemark();

        if (CollUtil.isEmpty(ids)) {
            return; // 如果ID列表为空，直接返回
        }

        // 2. 校验所有上报记录是否存在
        List<InspectReportDO> reportList = inspectReportMapper.selectBatchIds(ids);
        if (reportList.size() != ids.size()) {
            // 如果查询到的记录数量与传入的ID数量不一致，说明有记录不存在
            throw exception(INSPECT_REPORT_NOT_EXISTS);
        }

        // 3. 获取当前时间（作为审核时间）
        LocalDateTime auditTime = LocalDateTime.now();

        // 4. 获取当前登录用户ID（作为审核人ID）
        Long auditUserId = SecurityFrameworkUtils.getLoginUserId();

        // 5. 批量更新审核信息
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, auditResult)        // 设置审核结果状态
                .set(InspectReportDO::getAuditUserId, auditUserId)   // 设置审核人ID
                .set(InspectReportDO::getAuditTime, auditTime)       // 设置审核时间
                .set(InspectReportDO::getRemark, auditRemark)   // 设置审核备注
                .in(InspectReportDO::getId, ids);

        inspectReportMapper.update(null, updateWrapper);

        // 6. 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
        LogRecordContext.putVariable("auditResult", auditResult);

        // 将审核结果映射为中文名称
        String auditResultName = "";
        if ("2".equals(auditResult)) {
            auditResultName = "待处置";
        } else if ("5".equals(auditResult)) {
            auditResultName = "已驳回";
        } else {
            auditResultName = auditResult;
        }
        LogRecordContext.putVariable("auditResultName", auditResultName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_APPROVE_SUB_TYPE,
            bizNo = "{{#approveReqVO.id}}", success = INSPECT_REPORT_APPROVE_SUCCESS)
    public void approveInspectReport(InspectReportApproveReqVO approveReqVO) {
        // 1. 获取参数
        Long id = approveReqVO.getId();
        String auditRemark = approveReqVO.getAuditRemark();

        // 2. 校验上报记录是否存在
        validateInspectReportExists(id);

        // 3. 获取当前登录用户ID（作为审核人ID）
        Long auditUserId = SecurityFrameworkUtils.getLoginUserId();

        // 4. 获取当前时间（作为审核时间）
        LocalDateTime auditTime = LocalDateTime.now();

        // 5. 更新审核信息，状态改为2（待处置）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "2")                // 状态：待处置
                .set(InspectReportDO::getAuditUserId, auditUserId)   // 审核人ID
                .set(InspectReportDO::getAuditTime, auditTime)       // 审核时间
                .set(InspectReportDO::getRemark, auditRemark)   // 审核备注
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);

        // 6. 设置日志上下文变量
        LogRecordContext.putVariable("approveReqVO", approveReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_REJECT_SUB_TYPE,
            bizNo = "{{#rejectReqVO.id}}", success = INSPECT_REPORT_REJECT_SUCCESS)
    public void rejectInspectReport(InspectReportRejectReqVO rejectReqVO) {
        // 1. 获取参数
        Long id = rejectReqVO.getId();
        String auditRemark = rejectReqVO.getAuditRemark();

        // 2. 校验上报记录是否存在
        validateInspectReportExists(id);

        // 3. 获取当前登录用户ID（作为审核人ID）
        Long auditUserId = SecurityFrameworkUtils.getLoginUserId();

        // 4. 获取当前时间（作为审核时间）
        LocalDateTime auditTime = LocalDateTime.now();

        // 5. 更新审核信息，状态改为5（已驳回）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "5")                // 状态：已驳回
                .set(InspectReportDO::getAuditUserId, auditUserId)   // 审核人ID
                .set(InspectReportDO::getAuditTime, auditTime)       // 审核时间
                .set(InspectReportDO::getRemark, auditRemark)   // 驳回理由
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);

        // 6. 设置日志上下文变量
        LogRecordContext.putVariable("rejectReqVO", rejectReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_REPORT_TYPE, subType = INSPECT_REPORT_PROCESS_SUB_TYPE,
            bizNo = "{{#processReqVO.id}}", success = INSPECT_REPORT_PROCESS_SUCCESS)
    public void processInspectReport(InspectReportProcessReqVO processReqVO) {
        // 1. 获取参数
        Long id = processReqVO.getId();

        // 2. 校验上报记录是否存在
        validateInspectReportExists(id);

        // 3. 获取当前登录用户ID（作为处置人ID）
//        Long processUserId = SecurityFrameworkUtils.getLoginUserId();

        // 4. 获取当前时间（作为处置时间）
        LocalDateTime processTime = LocalDateTime.now();

        // 5. 更新处置信息，状态改为2（处理中）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "4")                // 状态：处理中
                .set(InspectReportDO::getProcessUserId,processReqVO.getProcessUserId()) // 处置人ID
                .set(InspectReportDO::getProcessTime, processTime)   // 处置时间
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);

        // 6. 设置日志上下文变量
        LogRecordContext.putVariable("processReqVO", processReqVO);
    }

    @Override
    public InspectReportChartRespVO getInspectReportChartData(String[] timeRange) {
        InspectReportChartRespVO chartRespVO = new InspectReportChartRespVO();

        // 1. 获取趋势数据
        List<InspectReportChartRespVO.TrendData> trendData = inspectReportMapper.selectReportTrendData(timeRange);
        chartRespVO.setTrendData(trendData);

        // 2. 获取类型分布数据
        List<InspectReportChartRespVO.TypeData> typeData = inspectReportMapper.selectReportTypeDistribution(timeRange);
        chartRespVO.setTypeData(typeData);

        // 3. 获取卡片统计数据
        InspectReportChartRespVO.CardData cardData = inspectReportMapper.selectReportCardData(timeRange);
        chartRespVO.setCardData(cardData);

        return chartRespVO;
    }

}