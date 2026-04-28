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
    public Long createInspectPlan(InspectPlanSaveReqVO createReqVO) {
        // 插入
        InspectPlanDO inspectPlan = BeanUtils.toBean(createReqVO, InspectPlanDO.class);
        inspectPlanMapper.insert(inspectPlan);

        // 返回
        return inspectPlan.getId();
    }

    @Override
    public void updateInspectPlan(InspectPlanSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectPlanExists(updateReqVO.getId());
        // 更新
        InspectPlanDO updateObj = BeanUtils.toBean(updateReqVO, InspectPlanDO.class);
        inspectPlanMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectPlan(Long id) {
        // 校验存在
        validateInspectPlanExists(id);
        // 删除
        inspectPlanMapper.deleteById(id);
    }

    @Override
        public void deleteInspectPlanListByIds(List<Long> ids) {
        // 删除
        inspectPlanMapper.deleteByIds(ids);
        }


    private void validateInspectPlanExists(Long id) {
        if (inspectPlanMapper.selectById(id) == null) {
            throw exception(INSPECT_PLAN_NOT_EXISTS);
        }
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
            return resp;

        } catch (Exception e) {
            // 整体导入失败（如文件格式错误等）
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInspectPlanStatus(Long id, String status) {
        // 校验计划是否存在
        validateInspectPlanExists(id);

        // 创建更新对象，只更新状态字段
        InspectPlanDO updateObj = new InspectPlanDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        if (status == "0"){
            updateObj.setProgress(50);
            updateObj.setEffectTime(LocalDateTime.now());
        }
        if (status == "2"){
            updateObj.setProgress(80);
        }
        if (status == "3"){
            updateObj.setProgress(100);
            updateObj.setFinishTime(LocalDateTime.now());
        }

        // 执行更新
        inspectPlanMapper.updateById(updateObj);
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