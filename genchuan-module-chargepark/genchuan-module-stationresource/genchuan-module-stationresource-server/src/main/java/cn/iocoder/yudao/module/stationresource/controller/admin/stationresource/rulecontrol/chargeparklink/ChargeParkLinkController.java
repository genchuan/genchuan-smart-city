package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink;

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


@Tag(name = "管理后台 - 充停联动")
@RestController
@RequestMapping("/stationresource/charge-park-link")
@Validated
public class ChargeParkLinkController {

    @Resource
    private ChargeParkLinkService chargeParkLinkService;

    @PostMapping("/create")
    @Operation(summary = "创建充停联动")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:create')")
    public CommonResult<Long> createChargeParkLink(@Valid @RequestBody ChargeParkLinkSaveReqVO createReqVO) {
        return success(chargeParkLinkService.createChargeParkLink(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新充停联动")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:update')")
    public CommonResult<Boolean> updateChargeParkLink(@Valid @RequestBody ChargeParkLinkSaveReqVO updateReqVO) {
        chargeParkLinkService.updateChargeParkLink(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除充停联动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:delete')")
    public CommonResult<Boolean> deleteChargeParkLink(@RequestParam("id") Long id) {
        chargeParkLinkService.deleteChargeParkLink(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除充停联动")
                @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:delete')")
    public CommonResult<Boolean> deleteChargeParkLinkList(@RequestParam("ids") List<Long> ids) {
        chargeParkLinkService.deleteChargeParkLinkListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得充停联动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:query')")
    public CommonResult<ChargeParkLinkRespVO> getChargeParkLink(@RequestParam("id") Long id) {
        ChargeParkLinkDO chargeParkLink = chargeParkLinkService.getChargeParkLink(id);
        return success(BeanUtils.toBean(chargeParkLink, ChargeParkLinkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得充停联动分页")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:query')")
    public CommonResult<PageResult<ChargeParkLinkRespVO>> getChargeParkLinkPage(@Valid ChargeParkLinkPageReqVO pageReqVO) {
        PageResult<ChargeParkLinkDO> pageResult = chargeParkLinkService.getChargeParkLinkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ChargeParkLinkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出充停联动 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:charge-park-link:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportChargeParkLinkExcel(@Valid ChargeParkLinkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ChargeParkLinkDO> list = chargeParkLinkService.getChargeParkLinkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "充停联动.xls", "数据", ChargeParkLinkRespVO.class,
                        BeanUtils.toBean(list, ChargeParkLinkRespVO.class));
    }

}
