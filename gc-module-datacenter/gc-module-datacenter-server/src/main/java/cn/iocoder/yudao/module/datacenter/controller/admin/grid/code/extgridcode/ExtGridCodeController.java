package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.extgridcode.ExtGridCodeDO;
import cn.iocoder.yudao.module.datacenter.service.grid.code.extgridcode.ExtGridCodeService;

@Tag(name = "管理后台 - 扩展网格编码")
@RestController
@RequestMapping("/datacenter/ext-grid-code")
@Validated
public class ExtGridCodeController {

    @Resource
    private ExtGridCodeService extGridCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建扩展网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:create')")
    public CommonResult<Long> createExtGridCode(@Valid @RequestBody ExtGridCodeSaveReqVO createReqVO) {
        return success(extGridCodeService.createExtGridCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新扩展网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:update')")
    public CommonResult<Boolean> updateExtGridCode(@Valid @RequestBody ExtGridCodeSaveReqVO updateReqVO) {
        extGridCodeService.updateExtGridCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除扩展网格编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:delete')")
    public CommonResult<Boolean> deleteExtGridCode(@RequestParam("id") Long id) {
        extGridCodeService.deleteExtGridCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得扩展网格编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:query')")
    public CommonResult<ExtGridCodeRespVO> getExtGridCode(@RequestParam("id") Long id) {
        ExtGridCodeDO extGridCode = extGridCodeService.getExtGridCode(id);
        return success(BeanUtils.toBean(extGridCode, ExtGridCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得扩展网格编码分页")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:query')")
    public CommonResult<PageResult<ExtGridCodeRespVO>> getExtGridCodePage(@Valid ExtGridCodePageReqVO pageReqVO) {
        PageResult<ExtGridCodeDO> pageResult = extGridCodeService.getExtGridCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ExtGridCodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出扩展网格编码 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:ext-grid-code:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExtGridCodeExcel(@Valid ExtGridCodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExtGridCodeDO> list = extGridCodeService.getExtGridCodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "扩展网格编码.xls", "数据", ExtGridCodeRespVO.class,
                        BeanUtils.toBean(list, ExtGridCodeRespVO.class));
    }

}