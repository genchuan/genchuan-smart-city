package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxsystem.EvalIdxSystemDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxsystem.EvalIdxSystemService;

@Tag(name = "管理后台 - 指标体系管理")
@RestController
@RequestMapping("/datacenter/eval-idx-system")
@Validated
public class EvalIdxSystemController {

    @Resource
    private EvalIdxSystemService evalIdxSystemService;

    @PostMapping("/create")
    @Operation(summary = "创建指标体系管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:create')")
    public CommonResult<Long> createEvalIdxSystem(@Valid @RequestBody EvalIdxSystemSaveReqVO createReqVO) {
        return success(evalIdxSystemService.createEvalIdxSystem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标体系管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:update')")
    public CommonResult<Boolean> updateEvalIdxSystem(@Valid @RequestBody EvalIdxSystemSaveReqVO updateReqVO) {
        evalIdxSystemService.updateEvalIdxSystem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标体系管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:delete')")
    public CommonResult<Boolean> deleteEvalIdxSystem(@RequestParam("id") Long id) {
        evalIdxSystemService.deleteEvalIdxSystem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标体系管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:query')")
    public CommonResult<EvalIdxSystemRespVO> getEvalIdxSystem(@RequestParam("id") Long id) {
        EvalIdxSystemDO evalIdxSystem = evalIdxSystemService.getEvalIdxSystem(id);
        return success(BeanUtils.toBean(evalIdxSystem, EvalIdxSystemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标体系管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:query')")
    public CommonResult<PageResult<EvalIdxSystemRespVO>> getEvalIdxSystemPage(@Valid EvalIdxSystemPageReqVO pageReqVO) {
        PageResult<EvalIdxSystemDO> pageResult = evalIdxSystemService.getEvalIdxSystemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalIdxSystemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标体系管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-system:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalIdxSystemExcel(@Valid EvalIdxSystemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalIdxSystemDO> list = evalIdxSystemService.getEvalIdxSystemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标体系管理.xls", "数据", EvalIdxSystemRespVO.class,
                        BeanUtils.toBean(list, EvalIdxSystemRespVO.class));
    }

}