package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StatusUpdateReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.StationInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationinfo.StationInfoService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
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


@Tag(name = "管理后台 - 场站信息")
@RestController
@RequestMapping("/stationresource/station-info")
@Validated
public class StationInfoController {

    @Resource
    private StationInfoService stationInfoService;

    @GetMapping("/chart")
    @Operation(summary = "场站信息统计（地图+柱状图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:query')")
    public CommonResult<StationInfoChartRespVO> getStationInfoChart() {
        StationInfoChartRespVO respVO=stationInfoService.getStationInfoChart();
        return success(respVO);
    }
    @GetMapping("/get")
    @Operation(summary = "获得场站信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:query')")
    public CommonResult<StationInfoRespVO> getStationInfo(@RequestParam("id") Long id) {
        StationInfoDO stationInfo = stationInfoService.getStationInfo(id);
        return success(BeanUtils.toBean(stationInfo, StationInfoRespVO.class));
    }
    @PutMapping("/update")
    @Operation(summary = "修改场站信息")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:update')")
    public CommonResult<Boolean> updateStationInfo(@Valid @RequestBody StationInfoUpdateReqVO reqVO) {
        stationInfoService.updateStation(reqVO);
        return success(true);
    }
    @PutMapping("/enable")
    @Operation(summary = "批量生效场站")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:update')")
    public CommonResult<Boolean> enableStationInfo(@Valid @RequestBody StatusUpdateReq req) {
        stationInfoService.updateStationStatus(req.getIds(), "已生效");
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用场站")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:update')")
    public CommonResult<Boolean> disableStationInfo(@Valid @RequestBody StatusUpdateReq req) {
        stationInfoService.updateStationStatus(req.getIds(), "已禁用");
        return success(true);
    }
    @GetMapping("/import-template")
    @Operation(summary = "下载场站信息导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:import')")
    public void importTemplate(HttpServletResponse response) throws Exception {
        // 导入模板使用 Import VO
        VrvExcelUtils.downloadImportTemplate(response, StationInfoCreateReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入场站信息", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:import')")
    public CommonResult<ImportRespVO> importStationInfo(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        ImportRespVO result = stationInfoService.importStationInfo(file, updateSupport);
        return success(result);
    }
    @PostMapping("/create")
    @Operation(summary = "创建场站信息")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:create')")
    public CommonResult<Long> createStationInfo(@Valid @RequestBody StationInfoCreateReqVO reqVO) {
        Long id =  stationInfoService.addStationInfo(reqVO);
        return success(id);
    }

    @GetMapping("/export")
    @Operation(summary = "导出场站信息 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStationInfoExcel(@Valid StationInfoPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StationInfoDO> list = stationInfoService.getStationInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "场站信息.xls", "数据", StationInfoRespVO.class,
                BeanUtils.toBean(list, StationInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场站信息分页")
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:query')")
    public CommonResult<PageResult<StationInfoRespVO>> getStationInfoPage(@Valid StationInfoPageReqVO pageReqVO) {
        PageResult<StationInfoDO> pageResult = stationInfoService.getStationInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StationInfoRespVO.class));
    }
    //=============================================
//    @PostMapping("/create")
//    @Operation(summary = "创建场站信息")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-info:create')")
//    public CommonResult<Long> createStationInfo(@Valid @RequestBody StationInfoSaveReqVO createReqVO) {
//        return success(stationInfoService.createStationInfo(createReqVO));
//    }

//    @PutMapping("/update")
//    @Operation(summary = "更新场站信息")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-info:update')")
//    public CommonResult<Boolean> updateStationInfo(@Valid @RequestBody StationInfoSaveReqVO updateReqVO) {
//        stationInfoService.updateStationInfo(updateReqVO);
//        return success(true);
//    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场站信息",hidden = true)
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:station-info:delete')")
    public CommonResult<Boolean> deleteStationInfo(@RequestParam("id") Long id) {
        stationInfoService.deleteStationInfo(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除场站信息",hidden = true)
                @PreAuthorize("@ss.hasPermission('stationresource:station-info:delete')")
    public CommonResult<Boolean> deleteStationInfoList(@RequestParam("ids") List<Long> ids) {
        stationInfoService.deleteStationInfoListByIds(ids);
        return success(true);
    }







}
