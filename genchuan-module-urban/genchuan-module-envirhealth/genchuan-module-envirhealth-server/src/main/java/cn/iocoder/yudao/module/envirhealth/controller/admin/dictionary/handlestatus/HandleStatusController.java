package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.handlestatus.HandleStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.handlestatus.HandleStatusService;
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

@Tag(name = "字典表 - 处置状态")
@RestController
@RequestMapping("/envirhealth/handle-status")
@Validated
public class HandleStatusController {

    @Resource
    private HandleStatusService handleStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建处置状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:create')")
    public CommonResult<Long> createHandleStatus(@Valid @RequestBody HandleStatusSaveReqVO createReqVO) {
        return success(handleStatusService.createHandleStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处置状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:update')")
    public CommonResult<Boolean> updateHandleStatus(@Valid @RequestBody HandleStatusSaveReqVO updateReqVO) {
        handleStatusService.updateHandleStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处置状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:delete')")
    public CommonResult<Boolean> deleteHandleStatus(@RequestParam("id") Long id) {
        handleStatusService.deleteHandleStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处置状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:query')")
    public CommonResult<HandleStatusRespVO> getHandleStatus(@RequestParam("id") Long id) {
        HandleStatusDO handleStatus = handleStatusService.getHandleStatus(id);
        return success(BeanUtils.toBean(handleStatus, HandleStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处置状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:query')")
    public CommonResult<PageResult<HandleStatusRespVO>> getHandleStatusPage(@Valid HandleStatusPageReqVO pageReqVO) {
        PageResult<HandleStatusDO> pageResult = handleStatusService.getHandleStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HandleStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出处置状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:handle-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHandleStatusExcel(@Valid HandleStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HandleStatusDO> list = handleStatusService.getHandleStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "处置状态字典表.xls", "数据", HandleStatusRespVO.class,
                        BeanUtils.toBean(list, HandleStatusRespVO.class));
    }

    /**
     * 获得处置状态下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得处置状态类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:handle-status:query')")
    public CommonResult<List<HandleStatusOptionVO>> getHandleStatusOptions() {
        return success(handleStatusService.getHandleStatusOptions());
    }
}
