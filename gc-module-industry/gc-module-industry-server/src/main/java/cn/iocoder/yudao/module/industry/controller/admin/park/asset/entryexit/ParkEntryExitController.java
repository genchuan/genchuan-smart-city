package cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.entryexit.ParkEntryExitDO;
import cn.iocoder.yudao.module.industry.service.park.asset.entryexit.ParkEntryExitService;

@Tag(name = "管理后台 - 出入口信息")
@RestController
@RequestMapping("/industry/park-entry-exit")
@Validated
public class ParkEntryExitController {

    @Resource
    private ParkEntryExitService parkEntryExitService;

    @PostMapping("/create")
    @Operation(summary = "创建出入口信息")
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:create')")
    public CommonResult<Long> createParkEntryExit(@Valid @RequestBody ParkEntryExitSaveReqVO createReqVO) {
        return success(parkEntryExitService.createParkEntryExit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新出入口信息")
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:update')")
    public CommonResult<Boolean> updateParkEntryExit(@Valid @RequestBody ParkEntryExitSaveReqVO updateReqVO) {
        parkEntryExitService.updateParkEntryExit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除出入口信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:delete')")
    public CommonResult<Boolean> deleteParkEntryExit(@RequestParam("id") Long id) {
        parkEntryExitService.deleteParkEntryExit(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得出入口信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:query')")
    public CommonResult<ParkEntryExitRespVO> getParkEntryExit(@RequestParam("id") Long id) {
        ParkEntryExitDO parkEntryExit = parkEntryExitService.getParkEntryExit(id);
        return success(BeanUtils.toBean(parkEntryExit, ParkEntryExitRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得出入口信息分页")
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:query')")
    public CommonResult<PageResult<ParkEntryExitRespVO>> getParkEntryExitPage(@Valid ParkEntryExitPageReqVO pageReqVO) {
        PageResult<ParkEntryExitDO> pageResult = parkEntryExitService.getParkEntryExitPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkEntryExitRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出出入口信息 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-entry-exit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkEntryExitExcel(@Valid ParkEntryExitPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkEntryExitDO> list = parkEntryExitService.getParkEntryExitPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "出入口信息.xls", "数据", ParkEntryExitRespVO.class,
                        BeanUtils.toBean(list, ParkEntryExitRespVO.class));
    }

}