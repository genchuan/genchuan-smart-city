package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectrec.EvalInspectRecDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.inspect.evalinspectrec.EvalInspectRecService;

@Tag(name = "管理后台 - 考察记录数据")
@RestController
@RequestMapping("/datacenter/eval-inspect-rec")
@Validated
public class EvalInspectRecController {

    @Resource
    private EvalInspectRecService evalInspectRecService;

    @PostMapping("/create")
    @Operation(summary = "创建考察记录数据")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:create')")
    public CommonResult<Long> createEvalInspectRec(@Valid @RequestBody EvalInspectRecSaveReqVO createReqVO) {
        return success(evalInspectRecService.createEvalInspectRec(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考察记录数据")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:update')")
    public CommonResult<Boolean> updateEvalInspectRec(@Valid @RequestBody EvalInspectRecSaveReqVO updateReqVO) {
        evalInspectRecService.updateEvalInspectRec(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考察记录数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:delete')")
    public CommonResult<Boolean> deleteEvalInspectRec(@RequestParam("id") Long id) {
        evalInspectRecService.deleteEvalInspectRec(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考察记录数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:query')")
    public CommonResult<EvalInspectRecRespVO> getEvalInspectRec(@RequestParam("id") Long id) {
        EvalInspectRecDO evalInspectRec = evalInspectRecService.getEvalInspectRec(id);
        return success(BeanUtils.toBean(evalInspectRec, EvalInspectRecRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考察记录数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:query')")
    public CommonResult<PageResult<EvalInspectRecRespVO>> getEvalInspectRecPage(@Valid EvalInspectRecPageReqVO pageReqVO) {
        PageResult<EvalInspectRecDO> pageResult = evalInspectRecService.getEvalInspectRecPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalInspectRecRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考察记录数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-inspect-rec:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalInspectRecExcel(@Valid EvalInspectRecPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalInspectRecDO> list = evalInspectRecService.getEvalInspectRecPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考察记录数据.xls", "数据", EvalInspectRecRespVO.class,
                        BeanUtils.toBean(list, EvalInspectRecRespVO.class));
    }

}