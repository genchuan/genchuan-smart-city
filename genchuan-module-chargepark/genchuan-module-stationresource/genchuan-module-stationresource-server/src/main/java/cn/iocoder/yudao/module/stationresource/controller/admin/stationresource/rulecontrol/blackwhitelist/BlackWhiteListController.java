package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist;

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


@Tag(name = "管理后台 - 黑白名单")
@RestController
@RequestMapping("/stationresource/black-white-list")
@Validated
public class BlackWhiteListController {

    @Resource
    private BlackWhiteListService blackWhiteListService;

    @PostMapping("/create")
    @Operation(summary = "创建黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:create')")
    public CommonResult<Long> createBlackWhiteList(@Valid @RequestBody BlackWhiteListSaveReqVO createReqVO) {
        return success(blackWhiteListService.createBlackWhiteList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新黑白名单")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:update')")
    public CommonResult<Boolean> updateBlackWhiteList(@Valid @RequestBody BlackWhiteListSaveReqVO updateReqVO) {
        blackWhiteListService.updateBlackWhiteList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除黑白名单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:delete')")
    public CommonResult<Boolean> deleteBlackWhiteList(@RequestParam("id") Long id) {
        blackWhiteListService.deleteBlackWhiteList(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除黑白名单")
                @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:delete')")
    public CommonResult<Boolean> deleteBlackWhiteListList(@RequestParam("ids") List<Long> ids) {
        blackWhiteListService.deleteBlackWhiteListListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得黑白名单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:query')")
    public CommonResult<BlackWhiteListRespVO> getBlackWhiteList(@RequestParam("id") Long id) {
        BlackWhiteListDO blackWhiteList = blackWhiteListService.getBlackWhiteList(id);
        return success(BeanUtils.toBean(blackWhiteList, BlackWhiteListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得黑白名单分页")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:query')")
    public CommonResult<PageResult<BlackWhiteListRespVO>> getBlackWhiteListPage(@Valid BlackWhiteListPageReqVO pageReqVO) {
        PageResult<BlackWhiteListDO> pageResult = blackWhiteListService.getBlackWhiteListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BlackWhiteListRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出黑白名单 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:black-white-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBlackWhiteListExcel(@Valid BlackWhiteListPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BlackWhiteListDO> list = blackWhiteListService.getBlackWhiteListPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "黑白名单.xls", "数据", BlackWhiteListRespVO.class,
                        BeanUtils.toBean(list, BlackWhiteListRespVO.class));
    }

}
