package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalsubject.EvalSubjectDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.evalsubject.EvalSubjectService;

@Tag(name = "管理后台 - 评价主体管理")
@RestController
@RequestMapping("/datacenter/eval-subject")
@Validated
public class EvalSubjectController {

    @Resource
    private EvalSubjectService evalSubjectService;

    @PostMapping("/create")
    @Operation(summary = "创建评价主体管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:create')")
    public CommonResult<Long> createEvalSubject(@Valid @RequestBody EvalSubjectSaveReqVO createReqVO) {
        return success(evalSubjectService.createEvalSubject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价主体管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:update')")
    public CommonResult<Boolean> updateEvalSubject(@Valid @RequestBody EvalSubjectSaveReqVO updateReqVO) {
        evalSubjectService.updateEvalSubject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价主体管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:delete')")
    public CommonResult<Boolean> deleteEvalSubject(@RequestParam("id") Long id) {
        evalSubjectService.deleteEvalSubject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价主体管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:query')")
    public CommonResult<EvalSubjectRespVO> getEvalSubject(@RequestParam("id") Long id) {
        EvalSubjectDO evalSubject = evalSubjectService.getEvalSubject(id);
        return success(BeanUtils.toBean(evalSubject, EvalSubjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价主体管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:query')")
    public CommonResult<PageResult<EvalSubjectRespVO>> getEvalSubjectPage(@Valid EvalSubjectPageReqVO pageReqVO) {
        PageResult<EvalSubjectDO> pageResult = evalSubjectService.getEvalSubjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalSubjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价主体管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-subject:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalSubjectExcel(@Valid EvalSubjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalSubjectDO> list = evalSubjectService.getEvalSubjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价主体管理.xls", "数据", EvalSubjectRespVO.class,
                        BeanUtils.toBean(list, EvalSubjectRespVO.class));
    }

}