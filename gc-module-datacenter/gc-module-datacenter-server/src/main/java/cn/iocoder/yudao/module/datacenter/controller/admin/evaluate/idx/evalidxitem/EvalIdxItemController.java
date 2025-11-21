package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxitem.EvalIdxItemDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxitem.EvalIdxItemService;

@Tag(name = "管理后台 - 指标项管理")
@RestController
@RequestMapping("/datacenter/eval-idx-item")
@Validated
public class EvalIdxItemController {

    @Resource
    private EvalIdxItemService evalIdxItemService;

    @PostMapping("/create")
    @Operation(summary = "创建指标项管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:create')")
    public CommonResult<Long> createEvalIdxItem(@Valid @RequestBody EvalIdxItemSaveReqVO createReqVO) {
        return success(evalIdxItemService.createEvalIdxItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标项管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:update')")
    public CommonResult<Boolean> updateEvalIdxItem(@Valid @RequestBody EvalIdxItemSaveReqVO updateReqVO) {
        evalIdxItemService.updateEvalIdxItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标项管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:delete')")
    public CommonResult<Boolean> deleteEvalIdxItem(@RequestParam("id") Long id) {
        evalIdxItemService.deleteEvalIdxItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标项管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:query')")
    public CommonResult<EvalIdxItemRespVO> getEvalIdxItem(@RequestParam("id") Long id) {
        EvalIdxItemDO evalIdxItem = evalIdxItemService.getEvalIdxItem(id);
        return success(BeanUtils.toBean(evalIdxItem, EvalIdxItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标项管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:query')")
    public CommonResult<PageResult<EvalIdxItemRespVO>> getEvalIdxItemPage(@Valid EvalIdxItemPageReqVO pageReqVO) {
        PageResult<EvalIdxItemDO> pageResult = evalIdxItemService.getEvalIdxItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalIdxItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标项管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalIdxItemExcel(@Valid EvalIdxItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalIdxItemDO> list = evalIdxItemService.getEvalIdxItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标项管理.xls", "数据", EvalIdxItemRespVO.class,
                        BeanUtils.toBean(list, EvalIdxItemRespVO.class));
    }

}