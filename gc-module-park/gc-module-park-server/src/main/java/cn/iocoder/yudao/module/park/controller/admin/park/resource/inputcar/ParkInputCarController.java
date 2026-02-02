package cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import cn.iocoder.yudao.module.park.service.park.resource.inputcar.ParkInputCarService;

@Tag(name = "管理后台 - 泊位录入车辆")
@RestController
@RequestMapping("/park/input-car")
@Validated
public class ParkInputCarController {

    @Resource
    private ParkInputCarService inputCarService;

    @PostMapping("/create")
    @Operation(summary = "创建泊位录入车辆")
    @PreAuthorize("@ss.hasPermission('park:input-car:create')")
    public CommonResult<Long> createInputCar(@Valid @RequestBody ParkInputCarSaveReqVO createReqVO) {
        return success(inputCarService.createInputCar(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新泊位录入车辆")
    @PreAuthorize("@ss.hasPermission('park:input-car:update')")
    public CommonResult<Boolean> updateInputCar(@Valid @RequestBody ParkInputCarSaveReqVO updateReqVO) {
        inputCarService.updateInputCar(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除泊位录入车辆")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:input-car:delete')")
    public CommonResult<Boolean> deleteInputCar(@RequestParam("id") Long id) {
        inputCarService.deleteInputCar(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得泊位录入车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:input-car:query')")
    public CommonResult<ParkInputCarRespVO> getInputCar(@RequestParam("id") Long id) {
        ParkInputCarDO inputCar = inputCarService.getInputCar(id);
        return success(BeanUtils.toBean(inputCar, ParkInputCarRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得泊位录入车辆分页")
    @PreAuthorize("@ss.hasPermission('park:input-car:query')")
    public CommonResult<PageResult<ParkInputCarRespVO>> getInputCarPage(@Valid ParkInputCarPageReqVO pageReqVO) {
        PageResult<ParkInputCarDO> pageResult = inputCarService.getInputCarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkInputCarRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出泊位录入车辆 Excel")
    @PreAuthorize("@ss.hasPermission('park:input-car:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInputCarExcel(@Valid ParkInputCarPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkInputCarDO> list = inputCarService.getInputCarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "泊位录入车辆.xls", "数据", ParkInputCarRespVO.class,
                        BeanUtils.toBean(list, ParkInputCarRespVO.class));
    }

    @PostMapping("/entry")
    @Operation(summary = "车辆进场")
    public CommonResult<Long> createEntry(@Valid @RequestBody ParkInputCarEntryReqVO reqVO) {
        return CommonResult.success(inputCarService.createEntry(reqVO));
    }

    @PutMapping("/exit")
    @Operation(summary = "车辆离场")
    public CommonResult<Boolean> updateExit(@Valid @RequestBody ParkInputCarExitReqVO reqVO) {
        inputCarService.updateExit(reqVO);
        return CommonResult.success(true);
    }

}