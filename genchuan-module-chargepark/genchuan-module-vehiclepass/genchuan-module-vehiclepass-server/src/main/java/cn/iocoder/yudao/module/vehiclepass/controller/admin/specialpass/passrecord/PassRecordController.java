package cn.iocoder.yudao.module.pass.controller.admin.record;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord.PassRecordDO;
import cn.iocoder.yudao.module.vehiclepass.service.specialpass.passrecord.PassRecordService;
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


@Tag(name = "管理后台 - 放行记录")
@RestController
@RequestMapping("/pass/record")
@Validated
public class PassRecordController {

    @Resource
    private PassRecordService recordService;

    @PostMapping("/create")
    @Operation(summary = "创建放行记录")
    @PreAuthorize("@ss.hasPermission('pass:record:create')")
    public CommonResult<Long> createRecord(@Valid @RequestBody PassRecordSaveReqVO createReqVO) {
        return success(recordService.createRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新放行记录")
    @PreAuthorize("@ss.hasPermission('pass:record:update')")
    public CommonResult<Boolean> updateRecord(@Valid @RequestBody PassRecordSaveReqVO updateReqVO) {
        recordService.updateRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除放行记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('pass:record:delete')")
    public CommonResult<Boolean> deleteRecord(@RequestParam("id") Long id) {
        recordService.deleteRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除放行记录")
    @PreAuthorize("@ss.hasPermission('pass:record:delete')")
    public CommonResult<Boolean> deleteRecordList(@RequestParam("ids") List<Long> ids) {
        recordService.deleteRecordListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得放行记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('pass:record:query')")
    public CommonResult<PassRecordRespVO> getRecord(@RequestParam("id") Long id) {
        PassRecordDO record = recordService.getRecord(id);
        return success(BeanUtils.toBean(record, PassRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得放行记录分页")
    @PreAuthorize("@ss.hasPermission('pass:record:query')")
    public CommonResult<PageResult<PassRecordRespVO>> getRecordPage(@Valid PassRecordPageReqVO pageReqVO) {
        PageResult<PassRecordDO> pageResult = recordService.getRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PassRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出放行记录 Excel")
    @PreAuthorize("@ss.hasPermission('pass:record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRecordExcel(@Valid PassRecordPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PassRecordDO> list = recordService.getRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "放行记录.xls", "数据", PassRecordRespVO.class,
                BeanUtils.toBean(list, PassRecordRespVO.class));
    }

}