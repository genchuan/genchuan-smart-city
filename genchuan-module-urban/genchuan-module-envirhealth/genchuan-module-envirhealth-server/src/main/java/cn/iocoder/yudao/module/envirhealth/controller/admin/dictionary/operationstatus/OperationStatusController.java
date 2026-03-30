package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo.OperationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo.OperationStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo.OperationStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.OperationStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.operationstatus.OperationStatusService;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

@Tag(name = "字典表 - 运营状态")
@RestController
@RequestMapping("/envirhealth/operation-status")
@Validated
public class OperationStatusController {

    @Resource
    private OperationStatusService operationStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建运营状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:create')")
    public CommonResult<Long> createOperationStatus(@Valid @RequestBody OperationStatusSaveReqVO createReqVO) {
        return success(operationStatusService.createOperationStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运营状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:update')")
    public CommonResult<Boolean> updateOperationStatus(@Valid @RequestBody OperationStatusSaveReqVO updateReqVO) {
        operationStatusService.updateOperationStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运营状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:delete')")
    public CommonResult<Boolean> deleteOperationStatus(@RequestParam("id") Long id) {
        operationStatusService.deleteOperationStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运营状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:query')")
    public CommonResult<OperationStatusRespVO> getOperationStatus(@RequestParam("id") Long id) {
        OperationStatusDO operationStatus = operationStatusService.getOperationStatus(id);
        return success(BeanUtils.toBean(operationStatus, OperationStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运营状态字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:query')")
    public CommonResult<PageResult<OperationStatusRespVO>> getOperationStatusPage(@Valid OperationStatusPageReqVO pageReqVO) {
        PageResult<OperationStatusDO> pageResult = operationStatusService.getOperationStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OperationStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运营状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:operation-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOperationStatusExcel(@Valid OperationStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OperationStatusDO> list = operationStatusService.getOperationStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运营状态字典.xls", "数据", OperationStatusRespVO.class,
                        BeanUtils.toBean(list, OperationStatusRespVO.class));
    }

    /**
     * 获得运营状态下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得运营状态(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:operation-status:query')")
    public CommonResult<List<OptionVO>> getOperationStatusOptions() {
        return success(operationStatusService.getOperationStatusOptions());
    }
}
