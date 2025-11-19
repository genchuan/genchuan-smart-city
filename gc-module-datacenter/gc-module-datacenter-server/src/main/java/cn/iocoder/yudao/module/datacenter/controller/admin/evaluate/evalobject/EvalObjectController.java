package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalobject.EvalObjectDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.evalobject.EvalObjectService;

@Tag(name = "管理后台 - 评价对象管理")
@RestController
@RequestMapping("/datacenter/eval-object")
@Validated
public class EvalObjectController {

    @Resource
    private EvalObjectService evalObjectService;

    @PostMapping("/create")
    @Operation(summary = "创建评价对象管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:create')")
    public CommonResult<Long> createEvalObject(@Valid @RequestBody EvalObjectSaveReqVO createReqVO) {
        return success(evalObjectService.createEvalObject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价对象管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:update')")
    public CommonResult<Boolean> updateEvalObject(@Valid @RequestBody EvalObjectSaveReqVO updateReqVO) {
        evalObjectService.updateEvalObject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价对象管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:delete')")
    public CommonResult<Boolean> deleteEvalObject(@RequestParam("id") Long id) {
        evalObjectService.deleteEvalObject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价对象管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:query')")
    public CommonResult<EvalObjectRespVO> getEvalObject(@RequestParam("id") Long id) {
        EvalObjectDO evalObject = evalObjectService.getEvalObject(id);
        return success(BeanUtils.toBean(evalObject, EvalObjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价对象管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:query')")
    public CommonResult<PageResult<EvalObjectRespVO>> getEvalObjectPage(@Valid EvalObjectPageReqVO pageReqVO) {
        PageResult<EvalObjectDO> pageResult = evalObjectService.getEvalObjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalObjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价对象管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-object:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalObjectExcel(@Valid EvalObjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalObjectDO> list = evalObjectService.getEvalObjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价对象管理.xls", "数据", EvalObjectRespVO.class,
                        BeanUtils.toBean(list, EvalObjectRespVO.class));
    }

}