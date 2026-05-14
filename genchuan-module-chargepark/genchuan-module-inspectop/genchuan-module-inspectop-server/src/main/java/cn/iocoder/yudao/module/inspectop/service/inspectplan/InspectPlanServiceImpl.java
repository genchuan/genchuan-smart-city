package cn.iocoder.yudao.module.inspectop.service.inspectplan;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectplan.InspectPlanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectplan.InspectPlanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 巡检计划 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectPlanServiceImpl implements InspectPlanService {

    @Resource
    private InspectPlanMapper inspectPlanMapper;

    @Override
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = INSPECT_PLAN_CREATE_SUCCESS)
    public Long createInspectPlan(InspectPlanSaveReqVO createReqVO) {
        // 插入
        InspectPlanDO inspectPlan = BeanUtils.toBean(createReqVO, InspectPlanDO.class);
        inspectPlanMapper.insert(inspectPlan);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return inspectPlan.getId();
    }

    @Override
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = INSPECT_PLAN_UPDATE_SUCCESS)
    public void updateInspectPlan(InspectPlanSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        InspectPlanDO oldInspectPlan = validateInspectPlanExists(updateReqVO.getId());

        // 2. 更新
        InspectPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectPlanDO.class);
        inspectPlanMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        InspectPlanSaveReqVO oldVO = BeanUtils.toBean(oldInspectPlan, InspectPlanSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = INSPECT_PLAN_DELETE_SUCCESS)
    public void deleteInspectPlan(Long id) {
        // 校验存在
        validateInspectPlanExists(id);
        // 删除
        inspectPlanMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_DELETE_LIST_SUB_TYPE,
            success = INSPECT_PLAN_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteInspectPlanListByIds(List<Long> ids) {
        // 删除
        inspectPlanMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回InspectPlanDO对象，用于update方法的日志对比
    private InspectPlanDO validateInspectPlanExists(Long id) {
        InspectPlanDO inspectPlan = inspectPlanMapper.selectById(id);
        if (inspectPlan == null) {
            throw exception(INSPECT_PLAN_NOT_EXISTS);
        }
        return inspectPlan; // 返回查询到的对象
    }

    @Override
    public InspectPlanDO getInspectPlan(Long id) {
        return inspectPlanMapper.selectById(id);
    }

    @Override
    public PageResult<InspectPlanDO> getInspectPlanPage(InspectPlanPageReqVO pageReqVO) {
        return inspectPlanMapper.selectPage(pageReqVO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_IMPORT_SUB_TYPE,
            success = INSPECT_PLAN_IMPORT_SUCCESS, bizNo = "")
    public ImportRespVO importInspectPlan(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {

            List<InspectPlanImportReqVO> importList = ExcelUtils.read(file, InspectPlanImportReqVO.class);

            if (CollUtil.isEmpty(importList)) {
                throw exception(INSPECT_PLAN_IMPORT_DATA_EMPTY);
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 遍历处理每一行数据
            for (int i = 0; i < importList.size(); i++) {
                InspectPlanImportReqVO importReqVO = importList.get(i);
                int rowIndex = i + 2; // Excel行号（从1开始，标题行占1行）

                try {
                    // 校验必填字段
                    if (StrUtil.isBlank(importReqVO.getName())) {
                        throw exception(INSPECT_PLAN_NAME_NOT_NULL);
                    }
                    if (StrUtil.isBlank(importReqVO.getType())) {
                        throw exception(INSPECT_PLAN_TYPE_NOT_NULL);
                    }
                    if (StrUtil.isBlank(importReqVO.getScope())) {
                        throw exception(INSPECT_PLAN_SCOPE_NOT_NULL);
                    }
                    if (StrUtil.isBlank(importReqVO.getStatus())) {
                        throw exception(INSPECT_PLAN_STATUS_NOT_NULL);
                    }

                    // 根据名称查找是否已存在
                    InspectPlanDO existPlan = inspectPlanMapper.selectOne(
                            new LambdaQueryWrapperX<InspectPlanDO>()
                                    .eq(InspectPlanDO::getName, importReqVO.getName())
                    );

                    if (existPlan != null) {
                        if (updateSupport) {
                            // 更新已存在的记录
                            InspectPlanDO updateObj = BeanUtils.toBean(importReqVO, InspectPlanDO.class);
                            updateObj.setId(existPlan.getId());
                            inspectPlanMapper.updateById(updateObj);
                        } else {
                            throw exception(INSPECT_PLAN_EXISTS, importReqVO.getName());
                        }
                    } else {
                        // 新增记录
                        InspectPlanDO inspectPlan = BeanUtils.toBean(importReqVO, InspectPlanDO.class);
                        inspectPlanMapper.insert(inspectPlan);
                    }
                    successCount++;

                } catch (Exception e) {
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);

            // 设置日志上下文变量
            LogRecordContext.putVariable("successCount", successCount);
            LogRecordContext.putVariable("failureCount", failures.size());
            return resp;

        } catch (Exception e) {
            // 整体导入失败（如文件格式错误等）
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);

            // 设置日志上下文变量
            LogRecordContext.putVariable("successCount", 0);
            LogRecordContext.putVariable("failureCount", 1);
            return resp;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = INSPECT_PLAN_TYPE, subType = INSPECT_PLAN_UPDATE_STATUS_SUB_TYPE,
            bizNo = "{{#id}}", success = INSPECT_PLAN_UPDATE_STATUS_SUCCESS)
    public void updateInspectPlanStatus(Long id, String status) {
        // 校验计划是否存在
        validateInspectPlanExists(id);

        // 创建更新对象，只更新状态字段
        InspectPlanDO updateObj = new InspectPlanDO();
        updateObj.setId(id);
        updateObj.setStatus(status);

        // 根据状态设置相应的进度和时间
        String statusName = "";
        if ("0".equals(status)) {  // 已生效
            statusName = "已生效";
            updateObj.setProgress(50);
            updateObj.setEffectTime(LocalDateTime.now());
        } else if ("2".equals(status)) {  // 进行中
            statusName = "进行中";
            updateObj.setProgress(80);
        } else if ("3".equals(status)) {  // 已完成
            statusName = "已完成";
            updateObj.setProgress(100);
            updateObj.setFinishTime(LocalDateTime.now());
        } else {
            statusName = "未知状态";
        }

        // 执行更新
        inspectPlanMapper.updateById(updateObj);

        // 设置日志上下文变量
        LogRecordContext.putVariable("id", id);
        LogRecordContext.putVariable("status", status);
        LogRecordContext.putVariable("statusName", statusName);
    }


    @Override
    public InspectPlanChartRespVO getInspectPlanChart(InspectPlanChartReqVO reqVO) {
        InspectPlanChartRespVO respVO = new InspectPlanChartRespVO();

        // 获取趋势数据
        List<InspectPlanChartRespVO.TrendData> trendData = inspectPlanMapper.selectTrendData(reqVO.getTimeRange());
        respVO.setTrendData(trendData);

        // 获取类型分布数据
        List<InspectPlanChartRespVO.TypeData> typeData = inspectPlanMapper.selectTypeData(reqVO.getTimeRange());
        respVO.setTypeData(typeData);

        // 获取卡片统计数据
        InspectPlanChartRespVO.CardData cardData = inspectPlanMapper.selectCardData(reqVO.getTimeRange());
        respVO.setCardData(cardData);

        return respVO;
    }

}