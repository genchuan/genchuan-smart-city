package cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntryRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntrySaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carentry.ParkCarEntryDO;
import cn.iocoder.yudao.module.industry.service.park.through.carentry.ParkCarEntryService;

@Tag(name = "管理后台 - 入场记录")
@RestController
@RequestMapping("/industry/park-car-entry")
@Validated
public class ParkCarEntryController {

    @Resource
    private ParkCarEntryService parkCarEntryService;

    @PostMapping("/create")
    @Operation(summary = "创建入场记录")
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:create')")
    public CommonResult<Long> createParkCarEntry(@Valid @RequestBody ParkCarEntrySaveReqVO createReqVO) {
        return success(parkCarEntryService.createParkCarEntry(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新入场记录")
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:update')")
    public CommonResult<Boolean> updateParkCarEntry(@Valid @RequestBody ParkCarEntrySaveReqVO updateReqVO) {
        parkCarEntryService.updateParkCarEntry(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除入场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:delete')")
    public CommonResult<Boolean> deleteParkCarEntry(@RequestParam("id") Long id) {
        parkCarEntryService.deleteParkCarEntry(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得入场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:query')")
    public CommonResult<ParkCarEntryRespVO> getParkCarEntry(@RequestParam("id") Long id) {
        ParkCarEntryDO parkCarEntry = parkCarEntryService.getParkCarEntry(id);
        return success(BeanUtils.toBean(parkCarEntry, ParkCarEntryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得入场记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:query')")
    public CommonResult<PageResult<ParkCarEntryRespVO>> getParkCarEntryPage(@Valid ParkCarEntryPageReqVO pageReqVO) {
        PageResult<ParkCarEntryDO> pageResult = parkCarEntryService.getParkCarEntryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkCarEntryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出入场记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-car-entry:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkCarEntryExcel(@Valid ParkCarEntryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkCarEntryDO> list = parkCarEntryService.getParkCarEntryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "入场记录.xls", "数据", ParkCarEntryRespVO.class,
                        BeanUtils.toBean(list, ParkCarEntryRespVO.class));
    }

}