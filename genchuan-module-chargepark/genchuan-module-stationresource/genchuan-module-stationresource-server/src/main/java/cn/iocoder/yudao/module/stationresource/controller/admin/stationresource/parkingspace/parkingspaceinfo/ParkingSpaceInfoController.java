package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AddReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.AddParkingSpaceInfoReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.BindParkingSpaceReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.ImportResultVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.statistics.ParkingSpaceChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "管理后台 - 车位信息")
@RestController
@RequestMapping("/stationresource/parking-space-info")
@Validated
//@Hidden
public class ParkingSpaceInfoController {

    @Resource
    private ParkingSpaceInfoService parkingSpaceInfoService;


    @GetMapping("/chart")
    @Operation(summary = "车位数据可视化图表（地图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
    public CommonResult<ParkingSpaceChartRespVO> getParkingSpaceChart() {
        ParkingSpaceChartRespVO respVO = parkingSpaceInfoService.getParkingSpaceChart();
        return success(respVO);
    }
    @PutMapping("/update")
    @Operation(summary = "更新车位信息")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:update')")
    public CommonResult<Boolean> updateParkingSpaceInfo(@Valid @RequestBody ParkingSpaceInfoSaveReqVO updateReqVO) {
        parkingSpaceInfoService.updateParkingSpaceInfo(updateReqVO);
        return success(true);
    }
    @PutMapping("/bind")
    @Operation(summary = "车位绑定设备", description = "批量绑定设备，更新状态为【已绑定】")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:update')")
    public CommonResult<Boolean> bindParkingSpace(@Valid @RequestBody BindParkingSpaceReqVO reqVO) {
        parkingSpaceInfoService.bindParkingSpace(reqVO);
        return success(true);
    }
    @GetMapping("/get-import-template")
    @Operation(summary = "下载导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        // 传入你要生成模板的类（AddReq / 任意VO）
        VrvExcelUtils.downloadImportTemplate(response, AddParkingSpaceInfoReqVO.class);
    }
    @PostMapping("/import")
    @Operation(summary = "导入车位信息", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:import')")
    public CommonResult<ImportResultVO> importParkingSpaceInfo(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ImportResultVO result = parkingSpaceInfoService.importParkingSpaceInfo(file, updateSupport);
        return success(result);
    }
    @PostMapping("/create")
    @Operation(summary = "创建车位信息")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:create')")
    public CommonResult<Long> createParkingSpaceInfo(@RequestBody AddParkingSpaceInfoReqVO createReqVO) {
        Long id= parkingSpaceInfoService.addParkingSpaceInfo(createReqVO);
        return success(id);
    }
    @GetMapping("/get")
    @Operation(summary = "获得车位信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
    public CommonResult<ParkingSpaceInfoRespVO> getParkingSpaceInfo(@RequestParam("id") Long id) {
        ParkingSpaceInfoRespVO parkingSpaceInfo = parkingSpaceInfoService.getParkingSpaceInfo(id);
        return success(BeanUtils.toBean(parkingSpaceInfo, ParkingSpaceInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位信息分页")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
    public CommonResult<PageResult<ParkingSpaceInfoRespVO>> getParkingSpaceInfoPage(@Valid ParkingSpaceInfoPageReqVO pageReqVO) {
        PageResult<ParkingSpaceInfoRespVO> pageResult = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingSpaceInfoRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出车位信息 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingSpaceInfoExcel(@Valid ParkingSpaceInfoPageReqVO pageReqVO,
                                            HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "车位信息_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingSpaceInfoRespVO> list = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "车位信息.xls", "数据", ParkingSpaceInfoRespVO.class,
                BeanUtils.toBean(list, ParkingSpaceInfoRespVO.class));
    }
    //==================================================================================================
//    @PostMapping("/create")
//    @Operation(summary = "创建车位信息")
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:create')")
//    public CommonResult<Long> createParkingSpaceInfo(@Valid @RequestBody ParkingSpaceInfoSaveReqVO createReqVO) {
//        return success(parkingSpaceInfoService.createParkingSpaceInfo(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新车位信息")
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:update')")
//    public CommonResult<Boolean> updateParkingSpaceInfo(@Valid @RequestBody ParkingSpaceInfoSaveReqVO updateReqVO) {
//        parkingSpaceInfoService.updateParkingSpaceInfo(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除车位信息")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:delete')")
//    public CommonResult<Boolean> deleteParkingSpaceInfo(@RequestParam("id") Long id) {
//        parkingSpaceInfoService.deleteParkingSpaceInfo(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除车位信息")
//                @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:delete')")
//    public CommonResult<Boolean> deleteParkingSpaceInfoList(@RequestParam("ids") List<Long> ids) {
//        parkingSpaceInfoService.deleteParkingSpaceInfoListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得车位信息")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
//    public CommonResult<ParkingSpaceInfoRespVO> getParkingSpaceInfo(@RequestParam("id") Long id) {
//        ParkingSpaceInfoDO parkingSpaceInfo = parkingSpaceInfoService.getParkingSpaceInfo(id);
//        return success(BeanUtils.toBean(parkingSpaceInfo, ParkingSpaceInfoRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得车位信息分页")
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
//    public CommonResult<PageResult<ParkingSpaceInfoRespVO>> getParkingSpaceInfoPage(@Valid ParkingSpaceInfoPageReqVO pageReqVO) {
//        PageResult<ParkingSpaceInfoDO> pageResult = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, ParkingSpaceInfoRespVO.class));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出车位信息 Excel")
//    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportParkingSpaceInfoExcel(@Valid ParkingSpaceInfoPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<ParkingSpaceInfoDO> list = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "车位信息.xls", "数据", ParkingSpaceInfoRespVO.class,
//                        BeanUtils.toBean(list, ParkingSpaceInfoRespVO.class));
//    }

}
