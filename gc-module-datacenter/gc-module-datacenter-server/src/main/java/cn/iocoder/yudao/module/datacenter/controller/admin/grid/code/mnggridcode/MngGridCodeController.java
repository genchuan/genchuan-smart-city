package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodeSaveReqVO;
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


import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.mnggridcode.MngGridCodeDO;
import cn.iocoder.yudao.module.datacenter.service.grid.code.mnggridcode.MngGridCodeService;

@Tag(name = "管理后台 - 管理网格编码")
@RestController
@RequestMapping("/datacenter/mng-grid-code")
@Validated
public class MngGridCodeController {

    @Resource
    private MngGridCodeService mngGridCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建管理网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:create')")
    public CommonResult<Long> createMngGridCode(@Valid @RequestBody MngGridCodeSaveReqVO createReqVO) {
        return success(mngGridCodeService.createMngGridCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:update')")
    public CommonResult<Boolean> updateMngGridCode(@Valid @RequestBody MngGridCodeSaveReqVO updateReqVO) {
        mngGridCodeService.updateMngGridCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理网格编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:delete')")
    public CommonResult<Boolean> deleteMngGridCode(@RequestParam("id") Long id) {
        mngGridCodeService.deleteMngGridCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理网格编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<MngGridCodeRespVO> getMngGridCode(@RequestParam("id") Long id) {
        MngGridCodeDO mngGridCode = mngGridCodeService.getMngGridCode(id);
        return success(BeanUtils.toBean(mngGridCode, MngGridCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理网格编码分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<PageResult<MngGridCodeRespVO>> getMngGridCodePage(@Valid MngGridCodePageReqVO pageReqVO) {
        PageResult<MngGridCodeDO> pageResult = mngGridCodeService.getMngGridCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngGridCodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理网格编码 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngGridCodeExcel(@Valid MngGridCodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngGridCodeDO> list = mngGridCodeService.getMngGridCodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理网格编码.xls", "数据", MngGridCodeRespVO.class,
                        BeanUtils.toBean(list, MngGridCodeRespVO.class));
    }

    @PostMapping("/generate-by-mng-grid")
    @Operation(summary = "根据管理网格ID生成编码")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:create')")
    public CommonResult<Long> generateCodeByMngGrid(
            @RequestParam("mngGridId") @NotEmpty(message = "管理网格ID不能为空") String mngGridId) {
        Long id = mngGridCodeService.generateCodeByMngGrid(mngGridId);
        return success(id);
    }

    @PostMapping("/validate-code-unique")
    @Operation(summary = "校验编码唯一性")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<Boolean> validateCodeUnique(
            @RequestParam("mgGridCode") @NotEmpty(message = "编码不能为空") String mgGridCode,
            @RequestParam(value = "excludeId", required = false) Long excludeId) {
        Boolean isUnique = mngGridCodeService.validateCodeUnique(mgGridCode, excludeId);
        return success(isUnique);
    }

    @PutMapping("/invalidate")
    @Operation(summary = "作废编码")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:update')")
    public CommonResult<Boolean> invalidateCode(
            @RequestParam("id") @NotNull(message = "编码ID不能为空") Long id,
            @RequestParam("reason") @NotEmpty(message = "作废原因不能为空") String reason) {
        mngGridCodeService.invalidateCode(id, reason);
        return success(true);
    }

    @GetMapping("/get-by-code")
    @Operation(summary = "根据编码查询")
    @Parameter(name = "mgGridCode", description = "管理网格编码", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<MngGridCodeRespVO> getByCode(@RequestParam("mgGridCode") String mgGridCode) {
        MngGridCodeDO codeDO = mngGridCodeService.getByCode(mgGridCode);
        return success(BeanUtils.toBean(codeDO, MngGridCodeRespVO.class));
    }

    @GetMapping("/get-by-mng-grid-id")
    @Operation(summary = "根据管理网格ID查询")
    @Parameter(name = "mngGridId", description = "管理网格ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<MngGridCodeRespVO> getByMngGridId(@RequestParam("mngGridId") String mngGridId) {
        MngGridCodeDO mngGridCode = mngGridCodeService.getByMngGridId(mngGridId);
        return success(BeanUtils.toBean(mngGridCode, MngGridCodeRespVO.class));
    }

    @GetMapping("/list-by-area")
    @Operation(summary = "根据行政区划查询编码列表")
    @Parameter(name = "areaFullCode", description = "行政区划完整代码", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-grid-code:query')")
    public CommonResult<List<MngGridCodeRespVO>> getListByArea(@RequestParam("areaFullCode") String areaFullCode) {
        List<MngGridCodeDO> list = mngGridCodeService.getListByArea(areaFullCode);
        return success(BeanUtils.toBean(list, MngGridCodeRespVO.class));
    }

}