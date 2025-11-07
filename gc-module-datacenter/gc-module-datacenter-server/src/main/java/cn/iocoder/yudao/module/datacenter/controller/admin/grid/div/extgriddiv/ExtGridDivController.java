package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.extgriddiv.ExtGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.grid.div.extgriddiv.ExtGridDivService;

@Tag(name = "管理后台 - 扩展网格划分")
@RestController
@RequestMapping("/datacenter/ext-grid-div")
@Validated
public class ExtGridDivController {

    @Resource
    private ExtGridDivService extGridDivService;

    @PostMapping("/create")
    @Operation(summary = "创建扩展网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:create')")
    public CommonResult<Long> createExtGridDiv(@Valid @RequestBody ExtGridDivSaveReqVO createReqVO) {
        return success(extGridDivService.createExtGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新扩展网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:update')")
    public CommonResult<Boolean> updateExtGridDiv(@Valid @RequestBody ExtGridDivSaveReqVO updateReqVO) {
        extGridDivService.updateExtGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除扩展网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:delete')")
    public CommonResult<Boolean> deleteExtGridDiv(@RequestParam("id") Long id) {
        extGridDivService.deleteExtGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得扩展网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:query')")
    public CommonResult<ExtGridDivRespVO> getExtGridDiv(@RequestParam("id") Long id) {
        ExtGridDivDO extGridDiv = extGridDivService.getExtGridDiv(id);
        return success(BeanUtils.toBean(extGridDiv, ExtGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得扩展网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:query')")
    public CommonResult<PageResult<ExtGridDivRespVO>> getExtGridDivPage(@Valid ExtGridDivPageReqVO pageReqVO) {
        PageResult<ExtGridDivDO> pageResult = extGridDivService.getExtGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ExtGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出扩展网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExtGridDivExcel(@Valid ExtGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtGridDivDO> list = extGridDivService.getExtGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "扩展网格划分.xls", "数据", ExtGridDivRespVO.class,
                        BeanUtils.toBean(list, ExtGridDivRespVO.class));
    }

}