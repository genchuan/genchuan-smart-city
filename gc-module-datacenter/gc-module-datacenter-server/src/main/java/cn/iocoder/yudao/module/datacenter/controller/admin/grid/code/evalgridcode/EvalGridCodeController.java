package cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.evalgridcode.EvalGridCodeDO;
import cn.iocoder.yudao.module.datacenter.service.grid.code.evalgridcode.EvalGridCodeService;

@Tag(name = "管理后台 - 评价网格编码")
@RestController
@RequestMapping("/datacenter/eval-grid-code")
@Validated
public class EvalGridCodeController {

    @Resource
    private EvalGridCodeService evalGridCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建评价网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:create')")
    public CommonResult<Long> createEvalGridCode(@Valid @RequestBody EvalGridCodeSaveReqVO createReqVO) {
        return success(evalGridCodeService.createEvalGridCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价网格编码")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:update')")
    public CommonResult<Boolean> updateEvalGridCode(@Valid @RequestBody EvalGridCodeSaveReqVO updateReqVO) {
        evalGridCodeService.updateEvalGridCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价网格编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:delete')")
    public CommonResult<Boolean> deleteEvalGridCode(@RequestParam("id") Long id) {
        evalGridCodeService.deleteEvalGridCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价网格编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:query')")
    public CommonResult<EvalGridCodeRespVO> getEvalGridCode(@RequestParam("id") Long id) {
        EvalGridCodeDO evalGridCode = evalGridCodeService.getEvalGridCode(id);
        return success(BeanUtils.toBean(evalGridCode, EvalGridCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价网格编码分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:query')")
    public CommonResult<PageResult<EvalGridCodeRespVO>> getEvalGridCodePage(@Valid EvalGridCodePageReqVO pageReqVO) {
        PageResult<EvalGridCodeDO> pageResult = evalGridCodeService.getEvalGridCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalGridCodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价网格编码 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-code:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalGridCodeExcel(@Valid EvalGridCodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalGridCodeDO> list = evalGridCodeService.getEvalGridCodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价网格编码.xls", "数据", EvalGridCodeRespVO.class,
                        BeanUtils.toBean(list, EvalGridCodeRespVO.class));
    }

}