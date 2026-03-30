package cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagepermitapply.DrainagePermitApplyDO;
import cn.iocoder.yudao.module.smartcity.service.drainagepermitapply.DrainagePermitApplyService;

@Tag(name = "管理后台 - 排水许可证申请")
@RestController
@RequestMapping("/smartcity/drainage-permit-apply")
@Validated
public class DrainagePermitApplyController {

    @Resource
    private DrainagePermitApplyService drainagePermitApplyService;

    @PostMapping("/create")
    @Operation(summary = "创建排水许可证申请")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:create')")
    public CommonResult<Long> createDrainagePermitApply(@Valid @RequestBody DrainagePermitApplySaveReqVO createReqVO) {
        return success(drainagePermitApplyService.createDrainagePermitApply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排水许可证申请")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:update')")
    public CommonResult<Boolean> updateDrainagePermitApply(@Valid @RequestBody DrainagePermitApplySaveReqVO updateReqVO) {
        drainagePermitApplyService.updateDrainagePermitApply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排水许可证申请")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:delete')")
    public CommonResult<Boolean> deleteDrainagePermitApply(@RequestParam("id") Long id) {
        drainagePermitApplyService.deleteDrainagePermitApply(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排水许可证申请")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:query')")
    public CommonResult<DrainagePermitApplyRespVO> getDrainagePermitApply(@RequestParam("id") Long id) {
        DrainagePermitApplyDO drainagePermitApply = drainagePermitApplyService.getDrainagePermitApply(id);
        return success(BeanUtils.toBean(drainagePermitApply, DrainagePermitApplyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排水许可证申请分页")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:query')")
    public CommonResult<PageResult<DrainagePermitApplyRespVO>> getDrainagePermitApplyPage(@Valid DrainagePermitApplyPageReqVO pageReqVO) {
        PageResult<DrainagePermitApplyDO> pageResult = drainagePermitApplyService.getDrainagePermitApplyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DrainagePermitApplyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排水许可证申请 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-permit-apply:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDrainagePermitApplyExcel(@Valid DrainagePermitApplyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DrainagePermitApplyDO> list = drainagePermitApplyService.getDrainagePermitApplyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排水许可证申请.xls", "数据", DrainagePermitApplyRespVO.class,
                        BeanUtils.toBean(list, DrainagePermitApplyRespVO.class));
    }

}