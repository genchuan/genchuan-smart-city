package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.evalgridattr.EvalGridAttrDO;
import cn.iocoder.yudao.module.datacenter.service.grid.data.evalgridattr.EvalGridAttrService;

@Tag(name = "管理后台 - 评价网格属性")
@RestController
@RequestMapping("/datacenter/eval-grid-attr")
@Validated
public class EvalGridAttrController {

    @Resource
    private EvalGridAttrService evalGridAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建评价网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:create')")
    public CommonResult<Long> createEvalGridAttr(@Valid @RequestBody EvalGridAttrSaveReqVO createReqVO) {
        return success(evalGridAttrService.createEvalGridAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价网格属性")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:update')")
    public CommonResult<Boolean> updateEvalGridAttr(@Valid @RequestBody EvalGridAttrSaveReqVO updateReqVO) {
        evalGridAttrService.updateEvalGridAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价网格属性")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:delete')")
    public CommonResult<Boolean> deleteEvalGridAttr(@RequestParam("id") Long id) {
        evalGridAttrService.deleteEvalGridAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价网格属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:query')")
    public CommonResult<EvalGridAttrRespVO> getEvalGridAttr(@RequestParam("id") Long id) {
        EvalGridAttrDO evalGridAttr = evalGridAttrService.getEvalGridAttr(id);
        return success(BeanUtils.toBean(evalGridAttr, EvalGridAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价网格属性分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:query')")
    public CommonResult<PageResult<EvalGridAttrRespVO>> getEvalGridAttrPage(@Valid EvalGridAttrPageReqVO pageReqVO) {
        PageResult<EvalGridAttrDO> pageResult = evalGridAttrService.getEvalGridAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalGridAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价网格属性 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalGridAttrExcel(@Valid EvalGridAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalGridAttrDO> list = evalGridAttrService.getEvalGridAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价网格属性.xls", "数据", EvalGridAttrRespVO.class,
                        BeanUtils.toBean(list, EvalGridAttrRespVO.class));
    }

}