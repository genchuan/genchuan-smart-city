package cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTablePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTableRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTableSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.receivertable.ReceiverTableDO;
import cn.iocoder.yudao.module.park.service.park.user.receivertable.ReceiverTableService;
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


@Tag(name = "管理后台 - 接收方")
@RestController
@RequestMapping("/park/receiver-table")
@Validated
public class ReceiverTableController {

    @Resource
    private ReceiverTableService receiverTableService;

    @PostMapping("/create")
    @Operation(summary = "创建接收方")
    @PreAuthorize("@ss.hasPermission('park:receiver-table:create')")
    public CommonResult<Long> createReceiverTable(@Valid @RequestBody ReceiverTableSaveReqVO createReqVO) {
        return success(receiverTableService.createReceiverTable(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新接收方")
    @PreAuthorize("@ss.hasPermission('park:receiver-table:update')")
    public CommonResult<Boolean> updateReceiverTable(@Valid @RequestBody ReceiverTableSaveReqVO updateReqVO) {
        receiverTableService.updateReceiverTable(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除接收方")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:receiver-table:delete')")
    public CommonResult<Boolean> deleteReceiverTable(@RequestParam("id") Long id) {
        receiverTableService.deleteReceiverTable(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得接收方")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:receiver-table:query')")
    public CommonResult<ReceiverTableRespVO> getReceiverTable(@RequestParam("id") Long id) {
        ReceiverTableDO receiverTable = receiverTableService.getReceiverTable(id);
        return success(BeanUtils.toBean(receiverTable, ReceiverTableRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得接收方分页")
    @PreAuthorize("@ss.hasPermission('park:receiver-table:query')")
    public CommonResult<PageResult<ReceiverTableRespVO>> getReceiverTablePage(@Valid ReceiverTablePageReqVO pageReqVO) {
        PageResult<ReceiverTableDO> pageResult = receiverTableService.getReceiverTablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReceiverTableRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出接收方 Excel")
    @PreAuthorize("@ss.hasPermission('park:receiver-table:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReceiverTableExcel(@Valid ReceiverTablePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReceiverTableDO> list = receiverTableService.getReceiverTablePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "接收方.xls", "数据", ReceiverTableRespVO.class,
                        BeanUtils.toBean(list, ReceiverTableRespVO.class));
    }

}
