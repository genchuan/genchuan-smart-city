package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AddReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.areamgmt.areainfo.AreaInfoService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
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


@Tag(name = "管理后台 - 片区信息")
@RestController
@RequestMapping("/stationresource/area-info")
@Validated
public class AreaInfoController {

    @Resource
    private AreaInfoService areaInfoService;


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

    @PutMapping("/update")
    @Operation(summary = "更新片区信息")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:update')")
    public CommonResult<Boolean> updateAreaInfo(@Valid @RequestBody AreaInfoSaveReqVO updateReqVO) {
        areaInfoService.updateAreaInfo(updateReqVO);
        return success(true);
    }

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

    @GetMapping("/export-excel")
    @Operation(summary = "导出片区信息 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:area-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaInfoExcel(@Valid AreaInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaInfoDO> list = areaInfoService.getAreaInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "片区信息.xls", "数据", AreaInfoRespVO.class,
                        BeanUtils.toBean(list, AreaInfoRespVO.class));
    }

}
