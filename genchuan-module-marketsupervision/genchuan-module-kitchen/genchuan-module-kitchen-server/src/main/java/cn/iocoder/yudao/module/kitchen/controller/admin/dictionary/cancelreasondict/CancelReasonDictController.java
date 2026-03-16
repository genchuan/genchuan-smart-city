package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict.CancelReasonDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 撤销原因字典")
@RestController
@RequestMapping("/kitchen/cancel-reason-dict")
@Validated
public class CancelReasonDictController {

    @Resource
    private CancelReasonDictService cancelReasonDictService;

    @PostMapping("/create")
    @Operation(summary = "创建撤销原因字典")
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:create')")
    public CommonResult<Long> createCancelReasonDict(@Valid @RequestBody CancelReasonDictSaveReqVO createReqVO) {
        return success(cancelReasonDictService.createCancelReasonDict(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新撤销原因字典")
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:update')")
    public CommonResult<Boolean> updateCancelReasonDict(@Valid @RequestBody CancelReasonDictSaveReqVO updateReqVO) {
        cancelReasonDictService.updateCancelReasonDict(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除撤销原因字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:delete')")
    public CommonResult<Boolean> deleteCancelReasonDict(@RequestParam("id") Long id) {
        cancelReasonDictService.deleteCancelReasonDict(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得撤销原因字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:query')")
    public CommonResult<CancelReasonDictRespVO> getCancelReasonDict(@RequestParam("id") Long id) {
        CancelReasonDictDO cancelReasonDict = cancelReasonDictService.getCancelReasonDict(id);
        return success(BeanUtils.toBean(cancelReasonDict, CancelReasonDictRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得撤销原因字典分页")
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:query')")
    public CommonResult<PageResult<CancelReasonDictRespVO>> getCancelReasonDictPage(@Valid CancelReasonDictPageReqVO pageReqVO) {
        PageResult<CancelReasonDictDO> pageResult = cancelReasonDictService.getCancelReasonDictPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CancelReasonDictRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出撤销原因字典 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:cancel-reason-dict:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCancelReasonDictExcel(@Valid CancelReasonDictPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CancelReasonDictDO> list = cancelReasonDictService.getCancelReasonDictPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "撤销原因字典.xls", "数据", CancelReasonDictRespVO.class,
                        BeanUtils.toBean(list, CancelReasonDictRespVO.class));
    }

}
