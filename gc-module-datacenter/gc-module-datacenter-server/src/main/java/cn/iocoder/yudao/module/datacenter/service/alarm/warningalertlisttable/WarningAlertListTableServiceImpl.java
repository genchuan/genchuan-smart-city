package cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceAttributeRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor.ManagedMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.enums.EventStatusEnum;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.managedmattermajor.ManagedMatterMajorService;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.DeviceService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private DeviceService deviceService;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarningAlertListTableSyncRespVO syncAllAlarmsFromThingsBoard(Boolean overwrite) {
        try {
            // 从ThingsBoard获取所有告警
            PageResult<AlarmRespVO> allAlarms = deviceService.getAlarmPage(Integer.MAX_VALUE, 0);

            if (allAlarms == null || allAlarms.getList() == null || allAlarms.getList().isEmpty()) {
                return WarningAlertListTableSyncRespVO.builder()
                        .successAlarmIds(new ArrayList<>())
                        .failureAlarmIds(Map.of("ALL", "未获取到告警数据"))
                        .totalCount(0)
                        .successCount(0)
                        .failureCount(1)
                        .createCount(0)
                        .updateCount(0)
                        .build();
            }

            // 直接处理告警列表，而不是提取ID
            return processAlarmsDirectly(allAlarms.getList(), overwrite);

        } catch (Exception e) {
//            log.error("同步所有告警失败", e);
            return WarningAlertListTableSyncRespVO.builder()
                    .successAlarmIds(new ArrayList<>())
                    .failureAlarmIds(Map.of("ALL", "同步失败: " + e.getMessage()))
                    .totalCount(0)
                    .successCount(0)
                    .failureCount(1)
                    .createCount(0)
                    .updateCount(0)
                    .build();
        }
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
                && isEmpty(vo.getDeviceId())
                && isEmpty(vo.getRegionCode())
                && isEmpty(vo.getRegionName())
                && isEmpty(vo.getGridId())
                && isEmpty(vo.getGridName())
                && isEmpty(vo.getAddress())
                && isEmpty(vo.getLongitude())
                && isEmpty(vo.getLatitude());
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
        vo.setRegionCode(trimOrNull(vo.getRegionCode()));
        vo.setRegionName(trimOrNull(vo.getRegionName()));
        vo.setGridId(trimOrNull(vo.getGridId()));
        vo.setGridName(trimOrNull(vo.getGridName()));
        vo.setAddress(trimOrNull(vo.getAddress()));
        vo.setLongitude(trimOrNull(vo.getLongitude()));
        vo.setLatitude(trimOrNull(vo.getLatitude()));
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

    /**
     * 从ThingsBoard获取告警详情
     */
    private AlarmRespVO getAlarmDetailFromThingsBoard(String alarmId) {
        // 这里需要根据您的实际业务逻辑实现
        // 由于DeviceService中没有直接根据alarmId查询的方法，可能需要扩展
        // 暂时返回null，需要您根据实际情况实现
        return null;
    }

    /**
     * 映射告警严重程度到预警等级
     */
    private String mapAlarmSeverityToWarningLevel(Object severity) {
        if (severity == null) return "general";

        String severityStr = severity.toString();
        switch (severityStr) {
            case "CRITICAL":
            case "MAJOR":
                return "emergency";
            case "MINOR":
                return "important";
            case "WARNING":
            default:
                return "general";
        }
    }

    /**
     * 映射告警状态到预警状态
     */
    private String mapAlarmStatusToWarningStatus(Object status) {
        if (status == null) return "pending";

        String statusStr = status.toString();
        switch (statusStr) {
            case "ACTIVE":
                return "processing";
            case "CLEARED_UNACK":
            case "CLEARED_ACK":
                return "completed";
            case "ACK":
                return "acknowledged";
            default:
                return "pending";
        }
    }

    /**
     * 映射设备属性到预警告警记录
     */
    private void mapDeviceAttributesToWarningAlert(List<DeviceAttributeRespVO> deviceAttributes,
                                                   WarningAlertListTableDO warningAlert) {
        for (DeviceAttributeRespVO attr : deviceAttributes) {
            if (attr.getKey() == null) continue;

            switch (attr.getKey()) {
                case "deviceId":
                    warningAlert.setDeviceId(attr.getValueAsString());
                    break;
                case "region_code":
                    warningAlert.setRegionCode(attr.getValueAsString());
                    break;
                case "warning_type_id":
                    try {
                        warningAlert.setWarningTypeId(Long.parseLong(attr.getValueAsString()));
                    } catch (NumberFormatException e) {
                        // 忽略转换错误
                    }
                    break;
                case "longitude":
                    warningAlert.setLongitude(attr.getValueAsString());
                    break;
                case "latitude":
                    warningAlert.setLatitude(attr.getValueAsString());
                    break;
                case "adress":
                    warningAlert.setAddress(attr.getValueAsString());
                    break;
            }
        }
    }

    /**
     * 列表分片工具方法
     */
    private <T> List<List<T>> partitionList(List<T> list, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        for (int i = 0; i < list.size(); i += batchSize) {
            batches.add(list.subList(i, Math.min(i + batchSize, list.size())));
        }
        return batches;
    }

    /**
     * 直接处理告警列表
     */
    private WarningAlertListTableSyncRespVO processAlarmsDirectly(List<AlarmRespVO> alarms, Boolean overwrite) {
        WarningAlertListTableSyncRespVO respVO = WarningAlertListTableSyncRespVO.builder()
                .successAlarmIds(new ArrayList<>())
                .failureAlarmIds(new LinkedHashMap<>())
                .build();

        int totalCount = alarms.size();
        respVO.setTotalCount(totalCount);

        // 分批处理，避免内存溢出
        int batchSize = 100;
        List<List<AlarmRespVO>> batches = partitionAlarmList(alarms, batchSize);

        for (List<AlarmRespVO> batch : batches) {
            processAlarmBatchDirectly(batch, overwrite, respVO);
        }

        // 统计信息
        respVO.setCreateCount(respVO.getSuccessAlarmIds().size());
        respVO.setFailureCount(respVO.getFailureAlarmIds().size());
        respVO.setSuccessCount(respVO.getSuccessAlarmIds().size());

        return respVO;
    }

    /**
     * 直接处理告警批次
     */
    private void processAlarmBatchDirectly(List<AlarmRespVO> alarms, Boolean overwrite,
                                           WarningAlertListTableSyncRespVO respVO) {
        List<WarningAlertListTableDO> toInsert = new ArrayList<>();
        List<WarningAlertListTableDO> toUpdate = new ArrayList<>();

        for (AlarmRespVO alarm : alarms) {
            try {
                if (alarm == null || alarm.getId() == null) {
                    respVO.getFailureAlarmIds().put("NULL_ALARM", "告警数据为空");
                    continue;
                }

                String alarmId = alarm.getId().getId().toString();

                // 转换为预警告警记录
                WarningAlertListTableDO warningAlert = convertAlarmToWarningAlert(alarm);

                // 检查是否已存在
                WarningAlertListTableDO existing = warningAlertListTableMapper.selectByAlertCode(alarmId);

                if (existing == null) {
                    // 新增记录
                    toInsert.add(warningAlert);
                    respVO.getSuccessAlarmIds().add(alarmId);
                } else if (Boolean.TRUE.equals(overwrite)) {
                    // 更新记录
                    warningAlert.setId(existing.getId());
                    toUpdate.add(warningAlert);
                    respVO.getSuccessAlarmIds().add(alarmId);
                } else {
                    respVO.getFailureAlarmIds().put(alarmId, "告警已存在且不允许覆盖");
                }

            } catch (Exception e) {
                String alarmId = alarm != null && alarm.getId() != null ?
                        alarm.getId().getId().toString() : "UNKNOWN";
                respVO.getFailureAlarmIds().put(alarmId, "处理失败: " + e.getMessage());
            }
        }

        // 批量操作
        if (!toInsert.isEmpty()) {
            warningAlertListTableMapper.insertBatch(toInsert);
        }
        if (!toUpdate.isEmpty()) {
            warningAlertListTableMapper.updateBatch(toUpdate);
        }
    }

    /**
     * 告警列表分片工具方法
     */
    private List<List<AlarmRespVO>> partitionAlarmList(List<AlarmRespVO> list, int batchSize) {
        List<List<AlarmRespVO>> batches = new ArrayList<>();
        for (int i = 0; i < list.size(); i += batchSize) {
            batches.add(list.subList(i, Math.min(i + batchSize, list.size())));
        }
        return batches;
    }

    /**
     * 将告警信息转换为预警告警记录
     */
    private WarningAlertListTableDO convertAlarmToWarningAlert(AlarmRespVO alarm) {
        WarningAlertListTableDO warningAlert = new WarningAlertListTableDO();

        warningAlert.setId(null);

        // 基础信息映射
        String alarmId = alarm.getId() != null ? alarm.getId().getId().toString() : null;
        warningAlert.setAlertCode(alarmId);
        warningAlert.setRelatedObjectType("DEVICE");
        warningAlert.setRelatedObjectId(alarm.getOriginator() != null ?
                alarm.getOriginator().getId().toString() : null);
        warningAlert.setRelatedObjectName(alarm.getOriginatorName());

        // 告警类型和等级映射
        warningAlert.setWarningType(alarm.getType());
        warningAlert.setWarningLevel(mapAlarmSeverityToWarningLevel(alarm.getSeverity()));
        warningAlert.setWarningStatus(mapAlarmStatusToWarningStatus(alarm.getStatus()));

        // 时间信息转换（修复时间转换问题）
        if (alarm.getStartTs() != null) {
            warningAlert.setTriggerTime(convertTimestampToLocalDateTime(alarm.getStartTs()));
        }
        if (alarm.getEndTs() != null) {
            warningAlert.setRequiredCompleteTime(convertTimestampToLocalDateTime(alarm.getEndTs()));
        }

        // 设备属性映射
        if (alarm.getDeviceAttributes() != null && !alarm.getDeviceAttributes().isEmpty()) {
            mapDeviceAttributesToWarningAlert(alarm.getDeviceAttributes(), warningAlert);
        }

        // 触发原因
        warningAlert.setTriggerReason(alarm.getName() + " - " + alarm.getType());

        // 设置默认状态
        warningAlert.setStatus(0); // 待处理状态

        // 设置创建和更新时间
        warningAlert.setCreateTime(LocalDateTime.now());
        warningAlert.setUpdateTime(LocalDateTime.now());
        warningAlert.setUpdater("admin");
        warningAlert.setCreator("admin");
//        warningAlert.setDeleted(false);

        return warningAlert;
    }

    /**
     * 时间戳转换为LocalDateTime
     */
    private LocalDateTime convertTimestampToLocalDateTime(Long timestamp) {
        if (timestamp == null) return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }


}