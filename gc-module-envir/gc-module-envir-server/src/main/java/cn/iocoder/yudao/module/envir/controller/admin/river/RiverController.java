package cn.iocoder.yudao.module.envir.controller.admin.river;

import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDetailDO;
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

import cn.iocoder.yudao.module.envir.controller.admin.river.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envir.service.river.RiverService;

@Tag(name = "管理后台 - 河道")
@RestController
@RequestMapping("/envir/river")
@Validated
public class RiverController {

    @Resource
    private RiverService riverService;

    @PostMapping("/create")
    @Operation(summary = "创建河道")
    @PreAuthorize("@ss.hasPermission('envir:river:create')")
    public CommonResult<Long> createRiver(@Valid @RequestBody RiverSaveReqVO createReqVO) {
        return success(riverService.createRiver(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新河道")
    @PreAuthorize("@ss.hasPermission('envir:river:update')")
    public CommonResult<Boolean> updateRiver(@Valid @RequestBody RiverSaveReqVO updateReqVO) {
        riverService.updateRiver(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除河道")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:river:delete')")
    public CommonResult<Boolean> deleteRiver(@RequestParam("id") Long id) {
        riverService.deleteRiver(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得河道")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:river:query')")
    public CommonResult<RiverRespVO> getRiver(@RequestParam("id") Long id) {
        RiverDO river = riverService.getRiver(id);
        return success(BeanUtils.toBean(river, RiverRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得河道分页")
    @PreAuthorize("@ss.hasPermission('envir:river:query')")
    public CommonResult<PageResult<RiverRespVO>> getRiverPage(@Valid RiverPageReqVO pageReqVO) {
        PageResult<RiverDO> pageResult = riverService.getRiverPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiverRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出河道 Excel")
    @PreAuthorize("@ss.hasPermission('envir:river:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiverExcel(@Valid RiverPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiverDO> list = riverService.getRiverPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "河道.xls", "数据", RiverRespVO.class,
                        BeanUtils.toBean(list, RiverRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取河道列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:river:query')")
    public CommonResult<List<RiverDetailDO>> getRiverListDetail() {
        List<RiverDetailDO> list = riverService.getRiverListDetail();
        return success(list);
    }
}