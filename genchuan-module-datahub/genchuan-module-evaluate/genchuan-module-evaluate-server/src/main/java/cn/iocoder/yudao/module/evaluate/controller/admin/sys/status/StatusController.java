package cn.iocoder.yudao.module.evaluate.controller.admin.sys.status;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.service.status.StatusService;
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

@Tag(name = "管理后台 - 状态字典")
@RestController
@RequestMapping("/evaluate/status")
@Validated
public class StatusController {

    @Resource
    private StatusService statusService;

    @PostMapping("/create")
    @Operation(summary = "创建状态字典")
    @PreAuthorize("@ss.hasPermission('evaluate:status:create')")
    public CommonResult<Long> createStatus(@Valid @RequestBody StatusSaveReqVO createReqVO) {
        return success(statusService.createStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新状态字典")
    @PreAuthorize("@ss.hasPermission('evaluate:status:update')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody StatusSaveReqVO updateReqVO) {
        statusService.updateStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:status:delete')")
    public CommonResult<Boolean> deleteStatus(@RequestParam("id") Long id) {
        statusService.deleteStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:status:query')")
    public CommonResult<StatusRespVO> getStatus(@RequestParam("id") Long id) {
        StatusDO status = statusService.getStatus(id);
        return success(BeanUtils.toBean(status, StatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得状态字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:status:query')")
    public CommonResult<PageResult<StatusRespVO>> getStatusPage(@Valid StatusPageReqVO pageReqVO) {
        PageResult<StatusDO> pageResult = statusService.getStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStatusExcel(@Valid StatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StatusDO> list = statusService.getStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "状态字典.xls", "数据", StatusRespVO.class,
                        BeanUtils.toBean(list, StatusRespVO.class));
    }

}