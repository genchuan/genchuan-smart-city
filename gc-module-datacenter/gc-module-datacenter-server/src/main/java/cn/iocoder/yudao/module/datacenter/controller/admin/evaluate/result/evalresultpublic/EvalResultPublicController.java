package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpublic.EvalResultPublicDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpublic.EvalResultPublicService;

@Tag(name = "管理后台 - 结果公示")
@RestController
@RequestMapping("/datacenter/eval-result-public")
@Validated
public class EvalResultPublicController {

    @Resource
    private EvalResultPublicService evalResultPublicService;

    @PostMapping("/create")
    @Operation(summary = "创建结果公示")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:create')")
    public CommonResult<Long> createEvalResultPublic(@Valid @RequestBody EvalResultPublicSaveReqVO createReqVO) {
        return success(evalResultPublicService.createEvalResultPublic(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果公示")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:update')")
    public CommonResult<Boolean> updateEvalResultPublic(@Valid @RequestBody EvalResultPublicSaveReqVO updateReqVO) {
        evalResultPublicService.updateEvalResultPublic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果公示")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:delete')")
    public CommonResult<Boolean> deleteEvalResultPublic(@RequestParam("id") Long id) {
        evalResultPublicService.deleteEvalResultPublic(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果公示")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:query')")
    public CommonResult<EvalResultPublicRespVO> getEvalResultPublic(@RequestParam("id") Long id) {
        EvalResultPublicDO evalResultPublic = evalResultPublicService.getEvalResultPublic(id);
        return success(BeanUtils.toBean(evalResultPublic, EvalResultPublicRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果公示分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:query')")
    public CommonResult<PageResult<EvalResultPublicRespVO>> getEvalResultPublicPage(@Valid EvalResultPublicPageReqVO pageReqVO) {
        PageResult<EvalResultPublicDO> pageResult = evalResultPublicService.getEvalResultPublicPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultPublicRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果公示 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-public:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultPublicExcel(@Valid EvalResultPublicPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultPublicDO> list = evalResultPublicService.getEvalResultPublicPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果公示.xls", "数据", EvalResultPublicRespVO.class,
                        BeanUtils.toBean(list, EvalResultPublicRespVO.class));
    }

}