package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.warningalertlisttable.WarningAlertListTableMapper;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import com.alibaba.excel.EasyExcel;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable.WarningAlertListTableDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.warningalertlisttable.WarningAlertListTableService;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo.WarningAlertListTableStatisticsRespVO;

@Tag(name = "管理后台 - 预警告警列表")
@RestController
@RequestMapping("/datacenter/warning-alert-list-table")
@Validated
public class WarningAlertListTableController {

    @Resource
    private WarningAlertListTableService warningAlertListTableService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警列表")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:create')")
    public CommonResult<Long> createWarningAlertListTable(@Valid @RequestBody WarningAlertListTableSaveReqVO createReqVO) {
        return success(warningAlertListTableService.createWarningAlertListTable(createReqVO));
    }

//    @PostMapping("/create")
//    @Operation(summary = "创建预警告警列表")
//    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:create')")
//    public CommonResult<Long> createWarningAlertListTable(@Valid @RequestBody WarningAlertListTableSaveReqVO createReqVO) {
//        try {
//            // 直接调用服务方法，服务方法内部会处理图片数据
//            return success(warningAlertListTableService.createWarningAlertListTable(createReqVO));
//        } catch (Exception e) {
//            return CommonResult.error(500, "创建预警告警失败: " + e.getMessage());
//        }
//    }

    @PostMapping("/created")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:create')")
    @Operation(summary = "创建业务请求申请")
    public CommonResult<Long> pand(@RequestParam("id") Long id) {
        return success(warningAlertListTableService.createWarningAlertListTable(id));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警列表")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:update')")
    public CommonResult<Boolean> updateWarningAlertListTable(@Valid @RequestBody WarningAlertListTableSaveReqVO updateReqVO) {
        warningAlertListTableService.updateWarningAlertListTable(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警列表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:delete')")
    public CommonResult<Boolean> deleteWarningAlertListTable(@RequestParam("id") Long id) {
        warningAlertListTableService.deleteWarningAlertListTable(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警列表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<WarningAlertListTableRespVO> getWarningAlertListTable(@RequestParam("id") Long id) {
        WarningAlertListTableDO warningAlertListTable = warningAlertListTableService.getWarningAlertListTable(id);
        return success(BeanUtils.toBean(warningAlertListTable, WarningAlertListTableRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警列表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<PageResult<WarningAlertListTableRespVO>> getWarningAlertListTablePage(@Valid WarningAlertListTablePageReqVO pageReqVO) {
        PageResult<WarningAlertListTableDO> pageResult = warningAlertListTableService.getWarningAlertListTablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WarningAlertListTableRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警列表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarningAlertListTableExcel(@Valid WarningAlertListTablePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WarningAlertListTableDO> list = warningAlertListTableService.getWarningAlertListTablePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警列表.xls", "数据", WarningAlertListTableRespVO.class,
                        BeanUtils.toBean(list, WarningAlertListTableRespVO.class));
    }

    @GetMapping("/level-statistics")
    @Operation(summary = "获取预警等级分布统计")
//    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<List<WarningAlertListTableStatisticsRespVO>> getWarningLevelStatistics() {
        return success(warningAlertListTableService.getWarningLevelStatistics());
    }

    @GetMapping("/status-statistics")
    @Operation(summary = "获取预警状态分布统计")
//    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<List<WarningAlertListTableStatisticsRespVO>> getWarningStatusStatistics() {
        return success(warningAlertListTableService.getWarningStatusStatistics());
    }

    @GetMapping("/responsible-person-statistics")
    @Operation(summary = "获取责任人告警统计")
//    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<List<ResponsiblePersonStatisticsRespVO>> getResponsiblePersonStatistics() {
        List<ResponsiblePersonStatisticsRespVO> statistics = warningAlertListTableService.getResponsiblePersonStatistics();
        return success(statistics);
    }

    @PostMapping("/responsible-person-level-statistics")
    @Operation(summary = "获取责任人预警等级统计")
//    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<List<ResponsiblePersonLevelStatisticsRespVO>> getResponsiblePersonLevelStatistics(
            @Valid @RequestBody ResponsiblePersonLevelStatisticsReqVO reqVO) {
        List<ResponsiblePersonLevelStatisticsRespVO> statistics = warningAlertListTableService.getResponsiblePersonLevelStatistics(reqVO);
        return success(statistics);
    }

    @PostMapping("/import")
    @Operation(summary = "导入预警告警列表")
    @Parameters({
        @Parameter(name = "file", description = "Excel 文件", required = true),
        @Parameter(name = "allowUpdate", description = "是否允许更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<WarningAlertListTableImportRespVO> importWarningAlertListTable(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "allowUpdate", required = false, defaultValue = "false") Boolean allowUpdate) throws IOException {
        List<WarningAlertListTableImportExcelVO> list;
        String filename = file.getOriginalFilename() != null ? file.getOriginalFilename().toLowerCase() : "";
        String contentType = file.getContentType() != null ? file.getContentType().toLowerCase() : "";
        
        // 文件类型检测
        boolean isCsv = filename.endsWith(".csv") || contentType.contains("csv");
        boolean isExcel = filename.endsWith(".xls") || filename.endsWith(".xlsx") || 
                          filename.endsWith(".et") || // WPS表格格式
                          filename.endsWith(".xlsm") || // Excel宏文件
                          contentType.contains("excel") || 
                          contentType.contains("spreadsheet") ||
                          contentType.contains("application/vnd.ms-excel") ||
                          contentType.contains("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                          contentType.contains("application/et") || // WPS表格MIME类型
                          contentType.contains("application/wps");
        
        if (isCsv) {
            list = readCsvAsVoList(file);
        } else if (isExcel) {
            list = readExcelFile(file);
        } else {
            throw new IllegalArgumentException("不支持的文件类型，请上传Excel(xls/xlsx)或CSV文件");
        }
        
        // 记录导入信息
        logImportInfo(filename, contentType, isCsv, isExcel, list);
        
        // 执行导入
        WarningAlertListTableImportRespVO importResp = warningAlertListTableService.importWarningAlertList(list, allowUpdate);
        return success(importResp);
    }
    
    /**
     * 统一处理Excel文件读取
     */
    private List<WarningAlertListTableImportExcelVO> readExcelFile(MultipartFile file) throws IOException {
        List<WarningAlertListTableImportExcelVO> list = new ArrayList<>();
        String filename = file.getOriginalFilename() != null ? file.getOriginalFilename().toLowerCase() : "";
        String contentType = file.getContentType() != null ? file.getContentType().toLowerCase() : "";
        boolean isWpsFile = filename.endsWith(".et") || contentType.contains("application/et") || contentType.contains("application/wps");
        
        // 打印调试信息
        System.out.println("开始读取Excel文件...");
        System.out.println("文件类型检测 - 文件名: " + filename + ", 内容类型: " + contentType);
        System.out.println("是否为WPS文件: " + isWpsFile);
        debugExcelContent(file);
        
        try {
            // 主读取策略: 尝试多种方式读取Excel文件
            list = readExcelWithMultipleStrategies(file, isWpsFile);
        } catch (Exception e) {
            System.err.println("Excel读取异常: " + e.getMessage());
            e.printStackTrace();
            throw new IOException("Excel文件读取失败: " + e.getMessage());
        }
        
        // 验证读取结果
        if (list == null || list.isEmpty()) {
            System.out.println("警告：读取到的数据为空或null");
            throw new IOException("Excel文件中未找到有效数据，请检查文件格式和内容");
        }
        
        return list;
    }
    
    /**
     * 使用多种策略尝试读取Excel文件
     */
    private List<WarningAlertListTableImportExcelVO> readExcelWithMultipleStrategies(MultipartFile file, boolean isWpsFile) throws IOException {
        List<WarningAlertListTableImportExcelVO> list = null;
        
        // 策略1: 使用EasyExcel基于列名读取（推荐方式）
        System.out.println("尝试策略1：使用EasyExcel基于列名读取");
        list = readExcelWithEasyExcelByName(file);
        if (list != null && !list.isEmpty() && hasValidData(list)) {
            System.out.println("EasyExcel(列名映射)读取成功：" + list.size() + "条有效数据");
            return list;
        }
        
        // 策略2: 使用ExcelUtils读取
        System.out.println("尝试策略2：使用ExcelUtils读取");
        list = ExcelUtils.read(file, WarningAlertListTableImportExcelVO.class);
        if (list != null && !list.isEmpty() && hasValidData(list)) {
            System.out.println("ExcelUtils读取成功：" + list.size() + "条有效数据");
            return list;
        }
        
        // 策略3: 读取原始数据并手动映射
        System.out.println("尝试策略3：读取Excel表头和数据并手动映射");
        list = readExcelWithRawDataMapping(file);
        if (list != null && !list.isEmpty() && hasValidData(list)) {
            System.out.println("原始数据映射读取成功：" + list.size() + "条有效数据");
            return list;
        }
        
        // 策略4: 针对WPS文件的特殊处理
        if (isWpsFile) {
            System.out.println("尝试策略4：WPS文件特殊处理");
            list = readWpsFileAsVoList(file);
            if (list != null && !list.isEmpty() && hasValidData(list)) {
                System.out.println("WPS文件读取成功：" + list.size() + "条有效数据");
                return list;
            }
        }
        
        // 策略5: 强制读取（尝试从第0行开始读取所有数据）
        System.out.println("尝试策略5：强制读取所有数据");
        list = readExcelFileForce(file);
        if (list != null && !list.isEmpty() && hasValidData(list)) {
            System.out.println("强制读取成功：" + list.size() + "条有效数据");
            return list;
        }
        
        return list; // 可能为空，调用方需要验证
    }
    
    /**
     * 使用EasyExcel基于列名读取Excel文件
     */
    private List<WarningAlertListTableImportExcelVO> readExcelWithEasyExcelByName(MultipartFile file) throws IOException {
        try {
            return EasyExcel.read(file.getInputStream())
                    .head(WarningAlertListTableImportExcelVO.class)
                    .autoCloseStream(false)
                    .sheet(0) // 第一个Sheet
                    .headRowNumber(1) // 第一行作为表头
                    .doReadSync();
        } catch (Exception e) {
            System.err.println("EasyExcel列名读取失败: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 读取Excel原始数据并手动映射
     */
    private List<WarningAlertListTableImportExcelVO> readExcelWithRawDataMapping(MultipartFile file) throws IOException {
        try {
            // 读取所有数据
            List<List<String>> rawData = EasyExcel.read(file.getInputStream())
                    .autoCloseStream(false)
                    .sheet(0)
                    .headRowNumber(1) // 从第1行开始读取数据
                    .doReadSync();
            
            if (rawData != null && !rawData.isEmpty()) {
                System.out.println("原始数据行数：" + rawData.size());
                // 打印原始数据前几行用于调试
                for (int i = 0; i < Math.min(3, rawData.size()); i++) {
                    System.out.println("原始数据第" + (i+1) + "行：" + rawData.get(i));
                }
                return convertRawDataToVO(rawData);
            }
            return null;
        } catch (Exception e) {
            System.err.println("原始数据读取失败: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 检查数据是否包含有效内容
     */
    private boolean hasValidData(List<WarningAlertListTableImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        // 检查是否至少有一条记录包含非空字段
        return list.stream().anyMatch(vo -> 
            vo.getAlertCode() != null || vo.getRelatedObjectType() != null || 
            vo.getWarningLevel() != null || vo.getWarningStatus() != null);
    }
    
    /**
     * 记录导入信息
     */
    private void logImportInfo(String filename, String contentType, boolean isCsv, boolean isExcel, List<WarningAlertListTableImportExcelVO> list) {
        System.out.println("文件类型检测 - 文件名: " + filename + ", 内容类型: " + contentType);
        System.out.println("是否为CSV: " + isCsv + ", 是否为Excel: " + isExcel);
        System.out.println("导入数据条数：" + (list != null ? list.size() : "null"));
        
        if (list != null && !list.isEmpty()) {
            System.out.println("导入数据预览：");
            // 打印前5条数据作为预览
            int previewCount = Math.min(5, list.size());
            for (int i = 0; i < previewCount; i++) {
                WarningAlertListTableImportExcelVO vo = list.get(i);
                System.out.println("第" + (i+1) + "行数据 - alertCode: " + vo.getAlertCode() + 
                    ", relatedObjectType: " + vo.getRelatedObjectType() + 
                    ", warningLevel: " + vo.getWarningLevel());
            }
        }
    }
    
    private static List<WarningAlertListTableImportExcelVO> readCsvAsVoList(MultipartFile file) throws IOException {
        List<WarningAlertListTableImportExcelVO> results = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                return results;
            }
            // 处理 BOM
            headerLine = stripBom(headerLine);
            List<String> headers = splitCsvLine(headerLine);
            Map<String, Integer> nameToIndex = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                nameToIndex.put(normalizeHeader(headers.get(i).trim()), i);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                List<String> cols = splitCsvLine(line);
                WarningAlertListTableImportExcelVO vo = new WarningAlertListTableImportExcelVO();
                vo.setAlertCode(getByHeader(cols, nameToIndex, "告警编号"));
                vo.setRelatedObjectType(getByHeader(cols, nameToIndex, "关联对象类型"));
                vo.setRelatedObjectId(getByHeader(cols, nameToIndex, "关联对象ID"));
                vo.setRelatedObjectName(getByHeader(cols, nameToIndex, "关联对象名称"));
                vo.setWarningField(getByHeader(cols, nameToIndex, "预警领域"));
                vo.setWarningType(getByHeader(cols, nameToIndex, "预警类型"));
                vo.setWarningLevel(getByHeader(cols, nameToIndex, "预警等级"));
                vo.setWarningStatus(getByHeader(cols, nameToIndex, "预警状态"));
                vo.setTriggerReason(getByHeader(cols, nameToIndex, "触发原因"));
                vo.setRelatedEventCode(getByHeader(cols, nameToIndex, "关联事件编号"));
                vo.setDispatchDepartment(getByHeader(cols, nameToIndex, "派发部门"));
                vo.setResponsiblePerson(getByHeader(cols, nameToIndex, "责任人"));
                vo.setResponsiblePersonPhone(getByHeader(cols, nameToIndex, "责任人电话"));
                vo.setTriggerTime(getByHeader(cols, nameToIndex, "触发时间(yyyy-MM-dd HH:mm:ss)"));
                vo.setRequiredCompleteTime(getByHeader(cols, nameToIndex, "要求完成时间(yyyy-MM-dd HH:mm:ss)"));
                vo.setDisposalProgressDesc(getByHeader(cols, nameToIndex, "处置进展描述"));
                vo.setDisposalAttachmentPath(getByHeader(cols, nameToIndex, "处置附件路径"));
                vo.setReviewOpinion(getByHeader(cols, nameToIndex, "审核意见"));
                vo.setReviewer(getByHeader(cols, nameToIndex, "审核人"));
                vo.setReviewTime(getByHeader(cols, nameToIndex, "审核时间(yyyy-MM-dd HH:mm:ss)"));
                vo.setExtendCategory1(getByHeader(cols, nameToIndex, "扩展分类字段1"));
                vo.setExtendCategory2(getByHeader(cols, nameToIndex, "扩展分类字段2"));
                vo.setExtendCategory3(getByHeader(cols, nameToIndex, "扩展分类字段3"));
                vo.setDeviceId(getByHeader(cols, nameToIndex, "设备ID"));
                results.add(vo);
            }
        }
        return results;
    }

    private static String stripBom(String s) {
        if (s != null && s.startsWith("\uFEFF")) {
            return s.substring(1);
        }
        return s;
    }

    private static String getByHeader(List<String> cols, Map<String, Integer> nameToIndex, String header) {
        Integer idx = nameToIndex.get(normalizeHeader(header));
        if (idx == null || idx < 0 || idx >= cols.size()) return null;
        String v = cols.get(idx);
        return v != null ? v.trim() : null;
    }

    private static String normalizeHeader(String s) {
        if (s == null) return null;
        // 标准化全角括号与空白
        s = s.replace('（', '(').replace('）', ')');
        s = s.replaceAll("\\s+", "");
        return s;
    }

    // 简易 CSV 拆分，支持双引号包裹字段与转义的双引号
    private static List<String> splitCsvLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    // 转义双引号
                    cur.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                result.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        result.add(cur.toString());
        return result;
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入预警告警信息模板")
    public void getImportTemplate(HttpServletResponse response) throws IOException {
        List<WarningAlertListTableImportExcelVO> demoList = Arrays.asList(
            new WarningAlertListTableImportExcelVO() {{ // 紧急预警示例
                setAlertCode("ALERT245540003");
                setRelatedObjectType("设施设备1");
                setRelatedObjectId("abcdef123454");
                setRelatedObjectName("西直门垃圾转运站");
                setWarningField("环卫");
                setWarningType("状态提醒");
                setWarningLevel("general");
                setWarningStatus("rejected");
                setTriggerReason("垃圾箱满溢度超过80%阈值");
                setRelatedEventCode("EV2025100003");
                setDispatchDepartment("环卫清洁中心");
                setResponsiblePerson("钱七");
                setResponsiblePersonPhone("13800000004");
                setTriggerTime("2025-10-08 08:00:00");
                setRequiredCompleteTime("2025-10-08 10:00:00");
                setDisposalProgressDesc("已完成清理转运");
                setDisposalAttachmentPath("/upload/YJ2025100003/before.jpg;/upload/YJ2025100003/after.jpg");
                setReviewOpinion("已确认完成");
                setReviewer("孙八");
                setReviewTime("2025-10-08 10:30:00");
                setExtendCategory1("垃圾转运");
                setExtendCategory2("西城区");
                setExtendCategory3("北区");
                setDeviceId("abcdef123478");
                setRegionCode("110101");
                setRegionName("东城区");
                setGridId("GRID001");
                setGridName("王府井网格");
                setAddress("北京市东城区王府井大街100号");
                setLongitude("116.397128");
                setLatitude("39.916527");
            }},
            new WarningAlertListTableImportExcelVO() {{ // 重要预警示例
                setAlertCode("ALERT20256174");
                setRelatedObjectType("设施设备");
                setRelatedObjectId("abcdef123484");
                setRelatedObjectName("西直门垃圾转运站");
                setWarningField("环卫");
                setWarningType("状态提醒");
                setWarningLevel("general");
                setWarningStatus("rejected");
                setTriggerReason("垃圾箱满溢度超过80%阈值");
                setRelatedEventCode("EV2025100003");
                setDispatchDepartment("环卫清洁中心");
                setResponsiblePerson("钱七");
                setResponsiblePersonPhone("13800000004");
                setTriggerTime("2025-10-08 08:00:00");
                setRequiredCompleteTime("2025-10-08 10:00:00");
                setDisposalProgressDesc("已完成清理转运");
                setDisposalAttachmentPath("/upload/YJ2025100003/before.jpg;/upload/YJ2025100003/after.jpg");
                setReviewOpinion("已确认完成");
                setReviewer("孙八");
                setReviewTime("2025-10-08 10:30:00");
                setExtendCategory1("垃圾转运");
                setExtendCategory2("西城区");
                setExtendCategory3("北区");
                setDeviceId("abcdef12777");
                setRegionCode("110101");
                setRegionName("东城区");
                setGridId("GRID001");
                setGridName("王府井网格");
                setAddress("北京市东城区王府井大街100号");
                setLongitude("116.397128");
                setLatitude("39.916527");
                setTitle("垃圾箱满溢预警");
            }},
            new WarningAlertListTableImportExcelVO() {{ // 一般预警示例
                setAlertCode("ALERT202510099");
                setRelatedObjectType("设施设备");
                setRelatedObjectId("abcdef123999");
                setRelatedObjectName("西直门垃圾转运站");
                setWarningField("环卫");
                setWarningType("状态提醒");
                setWarningLevel("general");
                setWarningStatus("rejected");
                setTriggerReason("垃圾箱满溢度超过80%阈值");
                setRelatedEventCode("EV202510744");
                setDispatchDepartment("环卫清洁中心");
                setResponsiblePerson("钱七");
                setResponsiblePersonPhone("13800000004");
                setTriggerTime("2025-10-08 08:00:00");
                setRequiredCompleteTime("2025-10-08 10:00:00");
                setDisposalProgressDesc("已完成清理转运");
                setDisposalAttachmentPath("/upload/YJ2025100003/before.jpg;/upload/YJ2025100003/after.jpg");
                setReviewOpinion("已确认完成");
                setReviewer("孙八");
                setReviewTime("2025-10-08 10:30:00");
                setExtendCategory1("垃圾转运");
                setExtendCategory2("西城区");
                setExtendCategory3("北区");
                setDeviceId("abcdef12784");
                setRegionCode("110101");
                setRegionName("东城区");
                setGridId("GRID001");
                setGridName("王府井网格");
                setAddress("北京市东城区王府井大街100号");
                setLongitude("116.397128");
                setLatitude("39.916527");
                setTitle("垃圾箱满溢预警");
            }}
        );
        
        // 支持xls和xlsx两种格式下载
        ExcelUtils.write(response, "预警告警导入模板.xlsx", "预警告警列表", WarningAlertListTableImportExcelVO.class, demoList);
    }

    /**
     * 将原始数据转换为VO对象，兼容 List<List<String>> 和 List<LinkedHashMap>
     */
    private List<WarningAlertListTableImportExcelVO> convertRawDataToVO(List<?> rawData) {
        List<WarningAlertListTableImportExcelVO> result = new ArrayList<>();
        for (Object rowObj : rawData) {
            if (rowObj == null) continue;
            WarningAlertListTableImportExcelVO vo = new WarningAlertListTableImportExcelVO();
            if (rowObj instanceof List) {
                List<?> row = (List<?>) rowObj;
                if (row.size() > 0) vo.setAlertCode(getStringValue(row, 0));
                if (row.size() > 1) vo.setRelatedObjectType(getStringValue(row, 1));
                if (row.size() > 2) vo.setRelatedObjectId(getStringValue(row, 2));
                if (row.size() > 3) vo.setRelatedObjectName(getStringValue(row, 3));
                if (row.size() > 4) vo.setWarningField(getStringValue(row, 4));
                if (row.size() > 5) vo.setWarningType(getStringValue(row, 5));
                if (row.size() > 6) vo.setWarningLevel(getStringValue(row, 6));
                if (row.size() > 7) vo.setWarningStatus(getStringValue(row, 7));
                if (row.size() > 8) vo.setTriggerReason(getStringValue(row, 8));
                if (row.size() > 9) vo.setRelatedEventCode(getStringValue(row, 9));
                if (row.size() > 10) vo.setDispatchDepartment(getStringValue(row, 10));
                if (row.size() > 11) vo.setResponsiblePerson(getStringValue(row, 11));
                if (row.size() > 12) vo.setResponsiblePersonPhone(getStringValue(row, 12));
                if (row.size() > 13) vo.setTriggerTime(getStringValue(row, 13));
                if (row.size() > 14) vo.setRequiredCompleteTime(getStringValue(row, 14));
                if (row.size() > 15) vo.setDisposalProgressDesc(getStringValue(row, 15));
                if (row.size() > 16) vo.setDisposalAttachmentPath(getStringValue(row, 16));
                if (row.size() > 17) vo.setReviewOpinion(getStringValue(row, 17));
                if (row.size() > 18) vo.setReviewer(getStringValue(row, 18));
                if (row.size() > 19) vo.setReviewTime(getStringValue(row, 19));
                if (row.size() > 20) vo.setExtendCategory1(getStringValue(row, 20));
                if (row.size() > 21) vo.setExtendCategory2(getStringValue(row, 21));
                if (row.size() > 22) vo.setExtendCategory3(getStringValue(row, 22));
                if (row.size() > 23) vo.setDeviceId(getStringValue(row, 23));
            } else if (rowObj instanceof Map) {
                Map<?, ?> row = (Map<?, ?>) rowObj;
                vo.setAlertCode(getStringValue(row, 0, "0"));
                vo.setRelatedObjectType(getStringValue(row, 1, "1"));
                vo.setRelatedObjectId(getStringValue(row, 2, "2"));
                vo.setRelatedObjectName(getStringValue(row, 3, "3"));
                vo.setWarningField(getStringValue(row, 4, "4"));
                vo.setWarningType(getStringValue(row, 5, "5"));
                vo.setWarningLevel(getStringValue(row, 6, "6"));
                vo.setWarningStatus(getStringValue(row, 7, "7"));
                vo.setTriggerReason(getStringValue(row, 8, "8"));
                vo.setRelatedEventCode(getStringValue(row, 9, "9"));
                vo.setDispatchDepartment(getStringValue(row, 10, "10"));
                vo.setResponsiblePerson(getStringValue(row, 11, "11"));
                vo.setResponsiblePersonPhone(getStringValue(row, 12, "12"));
                vo.setTriggerTime(getStringValue(row, 13, "13"));
                vo.setRequiredCompleteTime(getStringValue(row, 14, "14"));
                vo.setDisposalProgressDesc(getStringValue(row, 15, "15"));
                vo.setDisposalAttachmentPath(getStringValue(row, 16, "16"));
                vo.setReviewOpinion(getStringValue(row, 17, "17"));
                vo.setReviewer(getStringValue(row, 18, "18"));
                vo.setReviewTime(getStringValue(row, 19, "19"));
                vo.setExtendCategory1(getStringValue(row, 20, "20"));
                vo.setExtendCategory2(getStringValue(row, 21, "21"));
                vo.setExtendCategory3(getStringValue(row, 22, "22"));
                vo.setDeviceId(getStringValue(row, 23, "23"));
            }
            result.add(vo);
        }
        return result;
    }

    // 支持List类型
    private String getStringValue(List<?> row, int index) {
        if (index >= row.size()) return null;
        Object value = row.get(index);
        return value != null ? value.toString().trim() : null;
    }

    // 支持Map类型，key为数字或字符串
    private String getStringValue(Map<?, ?> row, int intKey, String strKey) {
        Object value = row.get(intKey);
        if (value == null) value = row.get(strKey);
        return value != null ? value.toString().trim() : null;
    }

    /**
     * 读取WPS文件
     */
    private List<WarningAlertListTableImportExcelVO> readWpsFileAsVoList(MultipartFile file) throws IOException {
        System.out.println("开始读取WPS文件...");
        List<WarningAlertListTableImportExcelVO> result = new ArrayList<>();
        
        try {
            // 方式1：尝试使用EasyExcel读取WPS文件
            List<WarningAlertListTableImportExcelVO> list1 = EasyExcel.read(file.getInputStream())
                    .head(WarningAlertListTableImportExcelVO.class)
                    .autoCloseStream(false)
                    .sheet(0)
                    .headRowNumber(1)
                    .doReadSync();
            
            if (list1 != null && !list1.isEmpty()) {
                System.out.println("WPS文件EasyExcel读取成功：" + list1.size() + "条");
                return list1;
            }
            
            // 方式2：尝试读取原始数据
            List<List<String>> rawData = EasyExcel.read(file.getInputStream())
                    .autoCloseStream(false)
                    .sheet(0)
                    .headRowNumber(1)
                    .doReadSync();
            
            if (rawData != null && !rawData.isEmpty()) {
                System.out.println("WPS文件原始数据读取成功：" + rawData.size() + "条");
                result = convertRawDataToVO(rawData);
            }
            
        } catch (Exception e) {
            System.err.println("WPS文件读取失败: " + e.getMessage());
            // 如果WPS文件读取失败，尝试作为普通Excel处理
            try {
                result = ExcelUtils.read(file, WarningAlertListTableImportExcelVO.class);
            } catch (Exception e2) {
                System.err.println("WPS文件ExcelUtils也失败: " + e2.getMessage());
            }
        }
        
        return result;
    }
    
    /**
     * 强制读取Excel文件
     */
    private List<WarningAlertListTableImportExcelVO> readExcelFileForce(MultipartFile file) throws IOException {
        System.out.println("开始强制读取Excel文件...");
        List<WarningAlertListTableImportExcelVO> result = new ArrayList<>();
        
        try {
            // 尝试多种读取方式
            List<List<String>> rawData = EasyExcel.read(file.getInputStream())
                    .autoCloseStream(false)
                    .sheet(0)
                    .headRowNumber(0) // 从第0行开始，包含表头
                    .doReadSync();
            
            if (rawData != null && !rawData.isEmpty()) {
                System.out.println("强制读取原始数据成功：" + rawData.size() + "条");
                // 跳过表头，从第1行开始转换
                if (rawData.size() > 1) {
                    List<List<String>> dataRows = rawData.subList(1, rawData.size());
                    result = convertRawDataToVO(dataRows);
                }
            }
            
        } catch (Exception e) {
            System.err.println("强制读取Excel失败: " + e.getMessage());
        }
        
        return result;
    }

    /**
     * 调试方法：检查Excel文件内容
     */
    private void debugExcelContent(MultipartFile file) {
        try {
            System.out.println("=== Excel文件调试信息 ===");
            System.out.println("文件名: " + file.getOriginalFilename());
            System.out.println("文件大小: " + file.getSize() + " bytes");
            System.out.println("内容类型: " + file.getContentType());
            
            // 尝试读取前几行原始内容
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                System.out.println("文件前5行内容:");
                for (int i = 0; i < 5; i++) {
                    String line = reader.readLine();
                    if (line == null) break;
                    System.out.println("第" + (i+1) + "行: " + line);
                }
            }
            System.out.println("=== 调试信息结束 ===");
        } catch (Exception e) {
            System.err.println("调试Excel内容时出错: " + e.getMessage());
        }
    }

    @PostMapping("/sync-all-alarms")
    @Operation(summary = "一键同步所有ThingsBoard告警到预警告警列表")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:sync')")
    public CommonResult<WarningAlertListTableSyncRespVO> syncAllAlarmsFromThingsBoard(
            @RequestParam(value = "overwrite", defaultValue = "false") Boolean overwrite) {

        WarningAlertListTableSyncRespVO result = warningAlertListTableService.syncAllAlarmsFromThingsBoard(overwrite);
        return success(result);
    }

    @PostMapping(value = "/upload-scene-photos-base64", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "验证并处理现场照片(文件转Base64)")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:update')")
    public CommonResult<Map<String, Object>> uploadScenePhotosBase64(
            @RequestParam("file") List<MultipartFile> file) {

        try {
            if (file == null || file.isEmpty()) {
                return CommonResult.error(400, "上传文件不能为空");
            }

            Map<String, Object> result = warningAlertListTableService.uploadScenePhotosBase64(file);
            return success(result);
        } catch (Exception e) {
            return CommonResult.error(500, "图片处理失败: " + e.getMessage());
        }
    }

    @GetMapping("/get-scene-photos/{alertId}")
    @Operation(summary = "获取现场照片列表")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:query')")
    public CommonResult<List<String>> getScenePhotos(@PathVariable("alertId") Long alertId) {
        try {
            List<String> photos = warningAlertListTableService.getScenePhotos(alertId);
            return success(photos);
        } catch (Exception e) {
            return CommonResult.error(500, "获取图片失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-scene-photo-base64")
    @Operation(summary = "删除现场照片(Base64)")
    @PreAuthorize("@ss.hasPermission('datacenter:warning-alert-list-table:update')")
    public CommonResult<Boolean> deleteScenePhotoBase64(
            @RequestParam("alertId") Long alertId,
            @RequestParam("photoIndex") Integer photoIndex) {

        try {
            boolean result = warningAlertListTableService.deleteScenePhoto(alertId, photoIndex);
            return success(result);
        } catch (Exception e) {
            return CommonResult.error(500, "删除图片失败: " + e.getMessage());
        }
    }

}