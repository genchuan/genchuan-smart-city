package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.AddParkingSpaceInfoReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoService;
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
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 车位信息")
@RestController
@RequestMapping("/stationresource/parking-space-info")
@Validated
public class ParkingSpaceInfoController {

    @Resource
    private ParkingSpaceInfoService parkingSpaceInfoService;
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
        ParkingSpaceInfoDO parkingSpaceInfo = parkingSpaceInfoService.getParkingSpaceInfo(id);
        return success(BeanUtils.toBean(parkingSpaceInfo, ParkingSpaceInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位信息分页")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:query')")
    public CommonResult<PageResult<ParkingSpaceInfoRespVO>> getParkingSpaceInfoPage(@Valid ParkingSpaceInfoPageReqVO pageReqVO) {
        PageResult<ParkingSpaceInfoDO> pageResult = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkingSpaceInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车位信息 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:parking-space-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkingSpaceInfoExcel(@Valid ParkingSpaceInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkingSpaceInfoDO> list = parkingSpaceInfoService.getParkingSpaceInfoPage(pageReqVO).getList();
        // 导出 Excel
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
