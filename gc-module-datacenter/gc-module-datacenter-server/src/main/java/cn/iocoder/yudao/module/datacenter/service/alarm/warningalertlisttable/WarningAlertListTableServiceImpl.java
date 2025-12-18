package cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceAttributeRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics.InspectionStatisticsDO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition.EventDispositionDO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.managedmattermajor.ManagedMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.enums.EventStatusEnum;
import cn.iocoder.yudao.module.datacenter.framework.util.ImageBase64Utils;
import cn.iocoder.yudao.module.datacenter.framework.util.UuidUtils;
import cn.iocoder.yudao.module.datacenter.service.appscenecategory.AppSceneCategoryService;
import cn.iocoder.yudao.module.datacenter.service.evaluate.inspectionstatistics.InspectionStatisticsService;
import cn.iocoder.yudao.module.datacenter.service.eventdisposition.EventDispositionService;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.managedmattermajor.ManagedMatterMajorService;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.DeviceService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartFile;

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
    private EventDispositionService eventDispositionService;
    @Resource
    private DeviceService deviceService;
    @Resource
    private InspectionStatisticsService inspectionStatisticsService;

    @Override
    public Long createWarningAlertListTable(WarningAlertListTableSaveReqVO createReqVO) {

        // 插入
        WarningAlertListTableDO warningAlertListTable = BeanUtils.toBean(createReqVO, WarningAlertListTableDO.class);

        //自动生成预警编码
//        warningAlertListTable.setAlertCode(UuidUtils.generateUUID());

        warningAlertListTableMapper.insert(warningAlertListTable);
        // 新增：更新环卫考核统计结果
        updateInspectionStatisticsAfterAlertCreation();

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

        // todo 通过事件关联处置表查找流程模型
        // 通过预警类型ID查询事件关联处置表，获取流程模型ID
        String warningTypeId = warningAlertListTable.getWarningTypeId();
        String divisionCode = warningAlertListTable.getRegionCode(); // 获取行政区划编码

        if (warningTypeId == null || warningTypeId.trim().isEmpty()) {
            throw new IllegalArgumentException("预警类型ID不能为空");
        }

        // 查询事件关联处置配置（根据事件类型ID和行政区划编码）
        EventDispositionDO eventDisposition = eventDispositionService.getEventDispositionByEventTypeIdAndDivisionCode(warningTypeId, divisionCode);
        if (eventDisposition == null) {
            throw new IllegalArgumentException("未找到对应的事件关联处置配置，预警类型ID：" + warningTypeId);
        }

        if (eventDisposition.getProcessModelId() == null || eventDisposition.getProcessModelId().trim().isEmpty()) {
            throw new IllegalArgumentException("事件关联处置配置中流程模型ID为空，事件类型ID：" + warningTypeId);
        }

        // 创建流程实例
        CommonResult<String> commonResult = processInstanceApi.createProcessInstance(1L,
                new BpmProcessInstanceCreateReqDTO()
                        .setProcessDefinitionKey(eventDisposition.getProcessModelId())
                        .setBusinessKey(String.valueOf(warningAlertListTable.getId())));

        if (!commonResult.isSuccess()) {
            throw new RuntimeException("创建流程实例失败: " + commonResult.getMsg());
        }

        String processInstanceId = commonResult.getData();

        // 更新预警记录
        warningAlertListTable.setProcessInstanceId(processInstanceId);
        warningAlertListTable.setStatus(EventStatusEnum.PADDED.getStatus());
        warningAlertListTableMapper.updateById(warningAlertListTable);

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

    @Override
    public Map<String, Object> uploadScenePhotosBase64(List<MultipartFile> file) {
        Map<String, Object> result = new HashMap<>();
        List<String> processedImages = new ArrayList<>();
        List<String> failReasons = new ArrayList<>();
        int successCount = 0;
        int failCount = 0;

        for (int i = 0; i < file.size(); i++) {
            MultipartFile files = file.get(i);
            try {
                // 1. 验证文件是否为空
                if (file.isEmpty()) {
                    failCount++;
                    failReasons.add("第" + (i + 1) + "张图片为空");
                    continue;
                }

                // 2. 验证文件类型
                String mimeType = files.getContentType();
                if (mimeType == null || !mimeType.startsWith("image/")) {
                    failCount++;
                    failReasons.add("第" + (i + 1) + "张图片类型不支持，当前类型: " + mimeType);
                    continue;
                }

                // 3. 验证文件大小（限制为2MB）
                if (files.getSize() > 2 * 1024 * 1024) {
                    failCount++;
                    failReasons.add("第" + (i + 1) + "张图片大小超过2MB限制");
                    continue;
                }

                // 4. 转换为Base64
                byte[] fileBytes = files.getBytes();
                String base64Data = java.util.Base64.getEncoder().encodeToString(fileBytes);
                String base64String = "data:" + mimeType + ";base64," + base64Data;

                // 5. 验证Base64格式
                if (!ImageBase64Utils.isValidBase64Image(base64String)) {
                    failCount++;
                    failReasons.add("第" + (i + 1) + "张图片Base64格式无效");
                    continue;
                }

                processedImages.add(base64String);
                successCount++;

            } catch (Exception e) {
                failCount++;
                failReasons.add("第" + (i + 1) + "张图片处理失败: " + e.getMessage());
                System.err.println("图片处理失败: " + e.getMessage());
            }
        }

        // 返回结果
        result.put("successCount", successCount);
        result.put("failCount", failCount);
        result.put("totalImages", file.size());
        result.put("processedImages", processedImages);

        if (!failReasons.isEmpty()) {
            result.put("failReasons", failReasons);
        }

        if (processedImages.isEmpty()) {
            throw new IllegalArgumentException("所有图片处理失败，请检查图片格式。失败原因: " + String.join("; ", failReasons));
        }

        return result;
    }


    @Override
    public List<String> getScenePhotos(Long alertId) {
        WarningAlertListTableDO alert = warningAlertListTableMapper.selectById(alertId);
        if (alert == null) {
            throw exception(WARNING_ALERT_LIST_TABLE_NOT_EXISTS);
        }

        if (alert.getScenePhotos() == null) {
            return new ArrayList<>();
        }

        return ImageBase64Utils.listStringToImages(alert.getScenePhotos());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteScenePhoto(Long alertId, Integer photoIndex) {
        WarningAlertListTableDO alert = warningAlertListTableMapper.selectById(alertId);
        if (alert == null) {
            throw exception(WARNING_ALERT_LIST_TABLE_NOT_EXISTS);
        }

        if (alert.getScenePhotos() == null) {
            return true;
        }

        List<String> photos = ImageBase64Utils.listStringToImages(alert.getScenePhotos());

        if (photoIndex < 0 || photoIndex >= photos.size()) {
            throw new IllegalArgumentException("图片索引超出范围");
        }

        photos.remove(photoIndex.intValue());

        // 更新数据库
        alert.setScenePhotos(ImageBase64Utils.imagesToListString(photos));
        warningAlertListTableMapper.updateById(alert);

        return true;
    }

    @Override
    public List<ResponsiblePersonStatisticsRespVO> getResponsiblePersonStatistics() {
        return warningAlertListTableMapper.selectResponsiblePersonStatistics();
    }

    @Override
    public List<ResponsiblePersonLevelStatisticsRespVO> getResponsiblePersonLevelStatistics(ResponsiblePersonLevelStatisticsReqVO reqVO) {
        // 验证责任人参数
        if (reqVO.getResponsiblePerson() == null || reqVO.getResponsiblePerson().trim().isEmpty()) {
            throw new IllegalArgumentException("责任人姓名不能为空");
        }

        List<ResponsiblePersonLevelStatisticsRespVO> result = warningAlertListTableMapper.selectResponsiblePersonLevelStatistics(
                reqVO.getResponsiblePerson(),
                reqVO.getStartTime(),
                reqVO.getEndTime(),
                reqVO.getWarningStatus()
        );

        // 确保所有预警等级都有数据（即使数量为0）
        return ensureAllLevelsPresent(result);
    }

    /**
     * 确保返回所有预警等级，没有数据的等级数量为0
     */
    private List<ResponsiblePersonLevelStatisticsRespVO> ensureAllLevelsPresent(List<ResponsiblePersonLevelStatisticsRespVO> statistics) {
        Map<String, ResponsiblePersonLevelStatisticsRespVO> levelMap = new HashMap<>();

        // 将查询结果放入Map，使用name作为key
        for (ResponsiblePersonLevelStatisticsRespVO stat : statistics) {
            levelMap.put(stat.getName(), stat);
        }

        // 定义所有可能的预警等级（中文名称）
        String[] allLevels = {"紧急", "重要", "一般"};

        List<ResponsiblePersonLevelStatisticsRespVO> result = new ArrayList<>();
        for (String levelName : allLevels) {
            if (levelMap.containsKey(levelName)) {
                result.add(levelMap.get(levelName));
            } else {
                // 创建默认的统计对象（数量为0）
                ResponsiblePersonLevelStatisticsRespVO defaultStat = new ResponsiblePersonLevelStatisticsRespVO();
                defaultStat.setName(levelName);
                defaultStat.setValue(0);
                result.add(defaultStat);
            }
        }

        return result;
    }


    /**
     * 将MultipartFile转换为Base64并处理
     */
    private List<String> convertMultipartFilesToBase64(List<MultipartFile> files, Boolean compress, Float compressQuality) {
        List<String> base64List = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String base64 = convertToBase64(file);

                    // 如果需要压缩
                    if (Boolean.TRUE.equals(compress)) {
                        base64 = ImageBase64Utils.compressBase64Image(base64, compressQuality);
                    }

                    base64List.add(base64);
                } catch (Exception e) {
                    System.err.println("文件转换失败: " + file.getOriginalFilename() + ", 错误: " + e.getMessage());
                    throw new IllegalArgumentException("文件 " + file.getOriginalFilename() + " 处理失败: " + e.getMessage());
                }
            }
        }

        return base64List;
    }

    /**
     * 处理已有的Base64图片
     */
    private List<String> processBase64Images(List<String> base64Images, Boolean compress, Float compressQuality) {
        List<String> processedList = new ArrayList<>();

        for (String base64Image : base64Images) {
            try {
                // 验证Base64格式
                if (!ImageBase64Utils.isValidBase64Image(base64Image)) {
                    throw new IllegalArgumentException("Base64图片格式无效");
                }

                String processedImage = base64Image;

                // 如果需要压缩
                if (Boolean.TRUE.equals(compress)) {
                    processedImage = ImageBase64Utils.compressBase64Image(base64Image, compressQuality);
                }

                processedList.add(processedImage);
            } catch (Exception e) {
                System.err.println("Base64图片处理失败: " + e.getMessage());
                throw new IllegalArgumentException("Base64图片处理失败: " + e.getMessage());
            }
        }

        return processedList;
    }

    /**
     * 单个文件转换为Base64
     */
    private String convertToBase64(MultipartFile file) throws IOException {
        String mimeType = file.getContentType();
        if (mimeType == null || !mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("文件类型必须是图片，当前类型: " + mimeType);
        }

        byte[] fileBytes = file.getBytes();
        String base64Data = java.util.Base64.getEncoder().encodeToString(fileBytes);

        // 根据MIME类型构建完整的Base64字符串
        String imageType = mimeType.substring(6); // 去掉"image/"
        return "data:image/" + imageType + ";base64," + base64Data;
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
                && isEmpty(vo.getTitle())
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
        vo.setTitle(trimOrNull(vo.getTitle()));
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
                    warningAlert.setWarningTypeId(attr.getValueAsString());
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

    /**
     * 创建告警后更新环卫考核统计结果
     */
    private void updateInspectionStatisticsAfterAlertCreation() {
        try {
            // 1. 获取环卫考核统计结果的分页数据
            InspectionStatisticsPageReqVO pageReqVO = new InspectionStatisticsPageReqVO();
            pageReqVO.setPageSize(100); // 限制查询数量
            PageResult<InspectionStatisticsDO> pageResult = inspectionStatisticsService.getInspectionStatisticsPage(pageReqVO);

            if (pageResult == null || pageResult.getList() == null || pageResult.getList().isEmpty()) {
                System.out.println("环卫考核统计结果为空，跳过更新");
                return;
            }

            // 2. 随机选择一条记录进行更新
            List<InspectionStatisticsDO> statisticsList = pageResult.getList();
            Random random = new Random();
            InspectionStatisticsDO randomRecord = statisticsList.get(random.nextInt(statisticsList.size()));

            // 3. 创建更新请求VO
            InspectionStatisticsSaveReqVO updateReqVO = new InspectionStatisticsSaveReqVO();
            updateReqVO.setId(randomRecord.getId());
            updateReqVO.setInspectionDate(randomRecord.getInspectionDate());
            updateReqVO.setAreaType(randomRecord.getAreaType());
            updateReqVO.setAreaName(randomRecord.getAreaName());
            updateReqVO.setTotalScore(randomRecord.getTotalScore());
            updateReqVO.setMaxScore(randomRecord.getMaxScore());
            updateReqVO.setWeight(randomRecord.getWeight());

            // 样本数加1
            Integer newSampleCount = (randomRecord.getSampleCount() != null ? randomRecord.getSampleCount() : 0) + 1;
            updateReqVO.setSampleCount(newSampleCount);

            // 重新计算最终得分（模拟计算逻辑）
            updateReqVO.setScoreWeighted(calculateNewScoreWeighted(randomRecord, newSampleCount));

            updateReqVO.setInspectionStatus(randomRecord.getInspectionStatus());

            // 4. 调用更新接口
            inspectionStatisticsService.updateInspectionStatistics(updateReqVO);

            System.out.println("成功更新环卫考核统计记录，ID: " + randomRecord.getId() +
                    ", 新样本数: " + newSampleCount);

        } catch (Exception e) {
            // 记录错误但不影响主流程
            System.err.println("更新环卫考核统计结果失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 模拟重新计算最终得分
     */
    private BigDecimal calculateNewScoreWeighted(InspectionStatisticsDO record, Integer newSampleCount) {
        if (record.getTotalScore() == null || record.getMaxScore() == null ||
                record.getWeight() == null || record.getMaxScore().compareTo(BigDecimal.ZERO) == 0) {
            // 如果缺少必要字段，返回原值或默认值
            return record.getScoreWeighted() != null ? record.getScoreWeighted() : BigDecimal.ZERO;
        }

        try {
            // 模拟计算逻辑：最终得分 = (总得分 / 满分) * 权重 * 100
            BigDecimal scoreRatio = record.getTotalScore().divide(record.getMaxScore(), 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal weightedScore = scoreRatio.multiply(record.getWeight()).multiply(new BigDecimal("100"));

            // 添加一些随机波动模拟真实场景（±5%）
            Random random = new Random();
            double fluctuation = (random.nextDouble() * 0.1) - 0.05; // -5% 到 +5%
            BigDecimal fluctuationFactor = BigDecimal.ONE.add(BigDecimal.valueOf(fluctuation));

            return weightedScore.multiply(fluctuationFactor).setScale(2, BigDecimal.ROUND_HALF_UP);

        } catch (Exception e) {
            System.err.println("计算最终得分失败: " + e.getMessage());
            return record.getScoreWeighted() != null ? record.getScoreWeighted() : BigDecimal.ZERO;
        }
    }


}