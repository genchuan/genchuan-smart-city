package cn.iocoder.yudao.module.inspectop.service.inspectreport;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectreport.InspectReportMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

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
    public Long createInspectReport(InspectReportSaveReqVO createReqVO) {
        // 插入
        InspectReportDO inspectReport = BeanUtils.toBean(createReqVO, InspectReportDO.class);
        inspectReportMapper.insert(inspectReport);

        // 返回
        return inspectReport.getId();
    }

    @Override
    public void updateInspectReport(InspectReportSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectReportExists(updateReqVO.getId());
        // 更新
        InspectReportDO updateObj = BeanUtils.toBean(updateReqVO, InspectReportDO.class);
        inspectReportMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectReport(Long id) {
        // 校验存在
        validateInspectReportExists(id);
        // 删除
        inspectReportMapper.deleteById(id);
    }

    @Override
        public void deleteInspectReportListByIds(List<Long> ids) {
        // 删除
        inspectReportMapper.deleteByIds(ids);
        }


    private void validateInspectReportExists(Long id) {
        if (inspectReportMapper.selectById(id) == null) {
            throw exception(INSPECT_REPORT_NOT_EXISTS);
        }
    }

    @Override
    public InspectReportDO getInspectReport(Long id) {
        return inspectReportMapper.selectById(id);
    }

    @Override
    public PageResult<InspectReportRespVO> getInspectReportPage(InspectReportPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<InspectReportRespVO> mpPage
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<InspectReportRespVO> resultPage =
                inspectReportMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        // 5. 更新审核信息，状态改为3（已完成）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "3")                // 状态：已完成
                .set(InspectReportDO::getAuditUserId, auditUserId)   // 审核人ID
                .set(InspectReportDO::getAuditTime, auditTime)       // 审核时间
                .set(InspectReportDO::getRemark, auditRemark)   // 审核备注
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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

        // 5. 更新审核信息，状态改为1（待审核）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "1")                // 状态：待审核
                .set(InspectReportDO::getAuditUserId, auditUserId)   // 审核人ID
                .set(InspectReportDO::getAuditTime, auditTime)       // 审核时间
                .set(InspectReportDO::getRemark, auditRemark)   // 驳回理由
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processInspectReport(InspectReportProcessReqVO processReqVO) {
        // 1. 获取参数
        Long id = processReqVO.getId();

        // 2. 校验上报记录是否存在
        validateInspectReportExists(id);

        // 3. 获取当前登录用户ID（作为处置人ID）
        Long processUserId = SecurityFrameworkUtils.getLoginUserId();

        // 4. 获取当前时间（作为处置时间）
        LocalDateTime processTime = LocalDateTime.now();

        // 5. 更新处置信息，状态改为2（待处置）
        LambdaUpdateWrapper<InspectReportDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(InspectReportDO::getStatus, "2")                // 状态：待处置
                .set(InspectReportDO::getProcessUserId, processUserId) // 处置人ID
                .set(InspectReportDO::getProcessTime, processTime)   // 处置时间
                .eq(InspectReportDO::getId, id);

        inspectReportMapper.update(null, updateWrapper);
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