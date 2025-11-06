package cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor.ManagedMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.enums.EventStatusEnum;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.managedmattermajor.ManagedMatterMajorService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.warningalertlisttable.WarningAlertListTableMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.WARNING_ALERT_LIST_TABLE_NOT_EXISTS;

/**
 * 预警告警列表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class WarningAlertListTableServiceImpl implements WarningAlertListTableService {

    /**
     * 预警告警对应的流程定义
     */

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Resource
    private WarningAlertListTableMapper warningAlertListTableMapper;
    @Resource
    private ManagedMatterMajorService managedMatterMajorService;

    @Override
    public Long createWarningAlertListTable(WarningAlertListTableSaveReqVO createReqVO) {

        // 插入
        WarningAlertListTableDO warningAlertListTable = BeanUtils.toBean(createReqVO, WarningAlertListTableDO.class);
        warningAlertListTableMapper.insert(warningAlertListTable);

        // 返回
        return warningAlertListTable.getId();
    }

    @Override
    public void updateWarningAlertListTable(WarningAlertListTableSaveReqVO updateReqVO) {
        // 校验存在
        validateWarningAlertListTableExists(updateReqVO.getId());
        // 更新
        WarningAlertListTableDO updateObj = BeanUtils.toBean(updateReqVO, WarningAlertListTableDO.class);
        warningAlertListTableMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarningAlertListTable(Long id) {
        // 校验存在
        validateWarningAlertListTableExists(id);
        // 删除
        warningAlertListTableMapper.deleteById(id);
    }

    private void validateWarningAlertListTableExists(Long id) {
        if (warningAlertListTableMapper.selectById(id) == null) {
            throw exception(WARNING_ALERT_LIST_TABLE_NOT_EXISTS);
        }
    }

    @Override
    public WarningAlertListTableDO getWarningAlertListTable(Long id) {
        return warningAlertListTableMapper.selectById(id);
    }

    @Override
    public PageResult<WarningAlertListTableDO> getWarningAlertListTablePage(WarningAlertListTablePageReqVO pageReqVO) {
        return warningAlertListTableMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WarningAlertListTableStatisticsRespVO> getWarningLevelStatistics() {
        return warningAlertListTableMapper.selectWarningLevelStatistics();
    }

    @Override
    public List<WarningAlertListTableStatisticsRespVO> getWarningStatusStatistics() {
        return warningAlertListTableMapper.selectWarningStatusStatistics();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarningAlertListTableImportRespVO importWarningAlertList(List<WarningAlertListTableImportExcelVO> importList, boolean allowUpdate) {
        if (importList == null || importList.isEmpty()) {
            throw exception(WARNING_ALERT_LIST_TABLE_NOT_EXISTS);
        }
        
        WarningAlertListTableImportRespVO respVO = WarningAlertListTableImportRespVO.builder()
                .createAlertCodes(new ArrayList<>())
                .updateAlertCodes(new ArrayList<>())
                .failureAlertCodes(new LinkedHashMap<>())
                .build();
        

        java.util.concurrent.atomic.AtomicInteger processedRows = new java.util.concurrent.atomic.AtomicInteger(0);
        importList.forEach(vo -> {
            // 去除首尾空格，统一判空逻辑
            normalizeStringFields(vo);

            // 跳过整行为空的数据
            if (isRowEmpty(vo)) {
                return;
            }
            processedRows.incrementAndGet();
            try {
                // 校验必填字段
                if (vo.getAlertCode() == null || vo.getAlertCode().isEmpty()) {
                    respVO.getFailureAlertCodes().put("空告警编号", "告警编号不能为空");
                    return;
                }

                // 查询是否存在多条
                List<WarningAlertListTableDO> existList = warningAlertListTableMapper.selectListByAlertCode(vo.getAlertCode());
                WarningAlertListTableDO data = BeanUtils.toBean(vo, WarningAlertListTableDO.class);

                // 手动转换时间字段（使用多格式解析）
                if (vo.getTriggerTime() != null && !vo.getTriggerTime().isEmpty()) {
                    data.setTriggerTime(parseDateTime(vo.getTriggerTime()));
                }
                if (vo.getRequiredCompleteTime() != null && !vo.getRequiredCompleteTime().isEmpty()) {
                    data.setRequiredCompleteTime(parseDateTime(vo.getRequiredCompleteTime()));
                }
                if (vo.getReviewTime() != null && !vo.getReviewTime().isEmpty()) {
                    data.setReviewTime(parseDateTime(vo.getReviewTime()));
                }

                if (existList == null || existList.isEmpty()) {
                    warningAlertListTableMapper.insert(data);
                    respVO.getCreateAlertCodes().add(vo.getAlertCode());
                    return;
                }

                if (!allowUpdate) {
                    respVO.getFailureAlertCodes().put(vo.getAlertCode(), WARNING_ALERT_LIST_TABLE_NOT_EXISTS.getMsg());
                    return;
                }

                WarningAlertListTableDO first = existList.get(0);
                data.setId(first.getId());
                warningAlertListTableMapper.updateById(data);
                respVO.getUpdateAlertCodes().add(vo.getAlertCode());
            } catch (Exception e) {
                respVO.getFailureAlertCodes().put(vo.getAlertCode() != null ? vo.getAlertCode() : "未知",
                        "导入失败：" + e.getMessage());
            }
        });
        if (processedRows.get() == 0
                && respVO.getCreateAlertCodes().isEmpty()
                && respVO.getUpdateAlertCodes().isEmpty()
                && respVO.getFailureAlertCodes().isEmpty()) {
            respVO.getFailureAlertCodes().put("EMPTY_FILE", "文件没有有效数据行");
        }
        // 统计信息
        respVO.setCreateCount(respVO.getCreateAlertCodes().size());
        respVO.setUpdateCount(respVO.getUpdateAlertCodes().size());
        respVO.setFailureCount(respVO.getFailureAlertCodes().size());
        respVO.setSuccessCount(respVO.getCreateAlertCodes().size() + respVO.getUpdateAlertCodes().size());
        return respVO;
    }
/**
     * 业务流程
     * @param id 预警ID
     * @return 预警ID
     */
    @Override
    public Long createWarningAlertListTable(Long id) {
        WarningAlertListTableDO warningAlertListTable = warningAlertListTableMapper.selectById(id);

        // todo 通过事件小类查找流程模型

        ManagedMatterMajorDO managedMatterMajor =
                managedMatterMajorService.getManagedMatterMajor(Long.parseLong(warningAlertListTable.getWarningType()));

        // 创建流程实例
        CommonResult<String> commonResult = processInstanceApi.createProcessInstance(1L,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(managedMatterMajor.getFlowInstanceId())
                        .setBusinessKey(String.valueOf(warningAlertListTable.getId())));
        String processInstanceId = commonResult.getData();
        warningAlertListTableMapper.updateById(warningAlertListTable.setProcessInstanceId(processInstanceId).setStatus(EventStatusEnum.PADDED.getStatus()));
        return warningAlertListTable.getId();
    }

    private static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private static boolean isRowEmpty(WarningAlertListTableImportExcelVO vo) {
        return isEmpty(vo.getAlertCode())
                && isEmpty(vo.getRelatedObjectType())
                && isEmpty(vo.getRelatedObjectId())
                && isEmpty(vo.getRelatedObjectName())
                && isEmpty(vo.getWarningField())
                && isEmpty(vo.getWarningType())
                && isEmpty(vo.getWarningLevel())
                && isEmpty(vo.getWarningStatus())
                && isEmpty(vo.getTriggerReason())
                && isEmpty(vo.getRelatedEventCode())
                && isEmpty(vo.getDispatchDepartment())
                && isEmpty(vo.getResponsiblePerson())
                && isEmpty(vo.getResponsiblePersonPhone())
                && isEmpty(vo.getTriggerTime())
                && isEmpty(vo.getRequiredCompleteTime())
                && isEmpty(vo.getDisposalProgressDesc())
                && isEmpty(vo.getDisposalAttachmentPath())
                && isEmpty(vo.getReviewOpinion())
                && isEmpty(vo.getReviewer())
                && isEmpty(vo.getReviewTime())
                && isEmpty(vo.getExtendCategory1())
                && isEmpty(vo.getExtendCategory2())
                && isEmpty(vo.getExtendCategory3())
                && isEmpty(vo.getDeviceId());
    }

    private static void normalizeStringFields(WarningAlertListTableImportExcelVO vo) {
        vo.setAlertCode(trimOrNull(vo.getAlertCode()));
        vo.setRelatedObjectType(trimOrNull(vo.getRelatedObjectType()));
        vo.setRelatedObjectId(trimOrNull(vo.getRelatedObjectId()));
        vo.setRelatedObjectName(trimOrNull(vo.getRelatedObjectName()));
        vo.setWarningField(trimOrNull(vo.getWarningField()));
        vo.setWarningType(trimOrNull(vo.getWarningType()));
        vo.setWarningLevel(trimOrNull(vo.getWarningLevel()));
        vo.setWarningStatus(trimOrNull(vo.getWarningStatus()));
        vo.setTriggerReason(trimOrNull(vo.getTriggerReason()));
        vo.setRelatedEventCode(trimOrNull(vo.getRelatedEventCode()));
        vo.setDispatchDepartment(trimOrNull(vo.getDispatchDepartment()));
        vo.setResponsiblePerson(trimOrNull(vo.getResponsiblePerson()));
        vo.setResponsiblePersonPhone(trimOrNull(vo.getResponsiblePersonPhone()));
        vo.setTriggerTime(trimOrNull(vo.getTriggerTime()));
        vo.setRequiredCompleteTime(trimOrNull(vo.getRequiredCompleteTime()));
        vo.setDisposalProgressDesc(trimOrNull(vo.getDisposalProgressDesc()));
        vo.setDisposalAttachmentPath(trimOrNull(vo.getDisposalAttachmentPath()));
        vo.setReviewOpinion(trimOrNull(vo.getReviewOpinion()));
        vo.setReviewer(trimOrNull(vo.getReviewer()));
        vo.setReviewTime(trimOrNull(vo.getReviewTime()));
        vo.setExtendCategory1(trimOrNull(vo.getExtendCategory1()));
        vo.setExtendCategory2(trimOrNull(vo.getExtendCategory2()));
        vo.setExtendCategory3(trimOrNull(vo.getExtendCategory3()));
        vo.setDeviceId(trimOrNull(vo.getDeviceId()));
    }

    private static String trimOrNull(String s) {
        return s == null ? null : s.trim();
    }

    private LocalDateTime parseDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        List<DateTimeFormatter> formatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss")
        );
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateStr.trim(), formatter);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("日期格式错误: " + dateStr);
    }


}