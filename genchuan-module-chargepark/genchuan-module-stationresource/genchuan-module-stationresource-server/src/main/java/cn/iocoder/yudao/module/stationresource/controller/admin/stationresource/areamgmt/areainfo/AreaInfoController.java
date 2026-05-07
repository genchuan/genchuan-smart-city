package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo;

import cn.iocoder.yudao.module.inspectop.api.space.SpaceMonitorApi;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.AiAlertMessageApi;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessagePageReqDTO;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessageRespDTO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.lxscommon.vo.BatchStatusUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.areamgmt.areainfo.AreaInfoService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.pdf.pdf2.PdfUtils2;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.userfill.FillUserInfo;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 片区信息")
@RestController
@RequestMapping("/stationresource/area-info")
@Validated
@FillUserInfo(mode = FillUserInfo.FillMode.FIXED)
public class AreaInfoController {

    @Resource
    private AreaInfoService areaInfoService;

    @Resource
    private SpaceMonitorApi spaceMonitorApi;
    @Resource
    private AiAlertMessageApi aiAlertMessageApi;

    @GetMapping("/test-ai-alert-get")
    @Operation(summary = "测试：获取单个AI告警消息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:test')")
    public CommonResult<AiAlertMessageRespDTO> testAiAlertGet(@RequestParam("id") Long id) {
        // 1. 传入你要查询的 ID
        Long testId = 1L;

        // 2. RPC 调用（安全版，不会空指针）
        CommonResult<AiAlertMessageRespDTO> result = aiAlertMessageApi.getAiAlertMessage(id);
//        if (result == null || !result.isSuccess() || result.getData() == null) {
//            return success(new AiAlertMessageRespDTO());
//        }

        // 3. 返回数据
        return result;
    }
    // ===================== 【新增】AI告警消息调用示例（可直接用） =====================
    @GetMapping("/test-ai-alert-message")
    @Operation(summary = "(次级)获取AI告警消息列表")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:test-ai-alert-message')")
    public CommonResult<List<AiAlertMessageRespDTO>> testAiAlertMessage(AiAlertMessagePageReqDTO aiAlertMessagePageReqDTO) {
        // 1. 构建分页参数
        AiAlertMessagePageReqDTO pageReqDTO = new AiAlertMessagePageReqDTO();
        pageReqDTO.setPageSize(100); // 查100条

        // 2. RPC 调用
        PageResult<AiAlertMessageRespDTO> pageResult = aiAlertMessageApi.getAiAlertMessagePage(aiAlertMessagePageReqDTO).getData();

        // 3. 返回列表
        return success(pageResult.getList());
    }
    @GetMapping("/test-space-monitor")
    @Operation(summary = "(次级)获取车位坐标数据")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:test-space-monitor')")
    public CommonResult<List<SpaceMonitorRespDTO>> testSpaceMonitor(){
        CommonResult<List<SpaceMonitorRespDTO>> listCommonResult = spaceMonitorApi.listLatestSpaceMonitors();
        return listCommonResult;
    }

    @GetMapping("/export2")
    @Operation(summary = "(次级)导出 - 片区信息(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaInfo(
            @Valid AreaInfoPageReqVO pageReqVO,
            @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
            HttpServletResponse response) throws IOException {

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaInfoDO> list = areaInfoService.getAreaInfoPage(pageReqVO).getList();
        List<AreaInfoRespVO> respList = BeanUtils.toBean(list, AreaInfoRespVO.class);

        // ===================== PDF 导出 =====================
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils2.write(response, "片区信息.pdf", "片区信息台账",
                    PdfUtils2.headers(
                            "id", "ID",
                            "areaNo", "片区编号",
                            "name", "片区名称",
                            "district", "所属行政区划",
                            "leaderName", "负责人",
                            "phone", "联系电话",
                            "stationCount", "关联场站数",
                            "bindTime", "绑定时间",
                            "status", "状态",
                            "remark", "备注",
                            "createTime", "创建时间"
                    ),
                    respList);
        }
        // ===================== EXCEL 导出 =====================
        else {
            ExcelUtils.write(response, "片区信息.xls", "数据", AreaInfoRespVO.class, respList);
        }
    }
    @GetMapping("/chart")
    @Operation(summary = "片区数据可视化图表（地图+柱状图+卡片数据）")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:query')")
    public CommonResult<AreaInfoChartRespVO> getAreaInfoChart() {
        AreaInfoChartRespVO resp = areaInfoService.getAreaInfoChart();
        return success(resp);
    }
    @PutMapping("/update")
    @Operation(summary = "更新片区信息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:update')")
    public CommonResult<Boolean> updateAreaInfo(@Valid @RequestBody AreaInfoUpdateReqVO reqVO) {
        areaInfoService.updateArea(reqVO);
        return success(true);
    }
    @PutMapping("/enable")
    @Operation(summary = "批量生效片区信息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:update')")
    public CommonResult<Boolean> enableAreaInfo(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        areaInfoService.updateAreaInfoStatus(reqVO.getIds(), true);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用片区信息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:update')")
    public CommonResult<Boolean> disableAreaInfo(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        areaInfoService.updateAreaInfoStatus(reqVO.getIds(), false);
        return success(true);
    }

    @GetMapping("/import-template")
    @Operation(summary = "下载导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        // 传入你要生成模板的类（AddReq / 任意VO）
        VrvExcelUtils.downloadImportTemplate(response, AddReq.class);
    }


    @PostMapping("/import")
    @Operation(summary = "导入片区信息", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:import')")
//    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<ImportRespVO> importAreaInfo(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ImportRespVO result = areaInfoService.importAreaInfo(file,updateSupport);
        return success(result);
    }
    @PostMapping("/create")
    @Operation(summary = "创建片区信息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:create')")
    public CommonResult<Long> createAreaInfo(@Valid @RequestBody AddReq createReqVO) {
        Long id = areaInfoService.addAreaInfo(createReqVO);
        return success(id);
    }

//    @PutMapping("/update")
//    @Operation(summary = "更新片区信息")
//    @PreAuthorize("@ss.hasPermission('stationresource:area-info:update')")
//    public CommonResult<Boolean> updateAreaInfo(@Valid @RequestBody AreaInfoSaveReqVO updateReqVO) {
//        areaInfoService.updateAreaInfo(updateReqVO);
//        return success(true);
//    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除片区信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:delete')")
    public CommonResult<Boolean> deleteAreaInfo(@RequestParam("id") Long id) {
        areaInfoService.deleteAreaInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除片区信息")
                @PreAuthorize("@ss.hasPermission('stationresource:area-info:delete')")
    public CommonResult<Boolean> deleteAreaInfoList(@RequestParam("ids") List<Long> ids) {
        areaInfoService.deleteAreaInfoListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得片区信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:query')")
    public CommonResult<AreaInfoRespVO> getAreaInfo(@RequestParam("id") Long id) {
        AreaInfoDO areaInfo = areaInfoService.getAreaInfo(id);
        return success(BeanUtils.toBean(areaInfo, AreaInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得片区信息分页")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:query')")
    public CommonResult<PageResult<AreaInfoRespVO>> getAreaInfoPage(@Valid AreaInfoPageReqVO pageReqVO) {
        PageResult<AreaInfoDO> pageResult = areaInfoService.getAreaInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaInfoRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出片区信息 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaInfoExcel(@Valid AreaInfoPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "片区信息_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaInfoDO> list = areaInfoService.getAreaInfoPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString(); // 例如 "2026-03-10"
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "片区信息.xls", "数据", AreaInfoRespVO.class,
                BeanUtils.toBean(list, AreaInfoRespVO.class));
    }

}
