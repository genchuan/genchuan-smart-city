package cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.record.RecordDO;
import cn.iocoder.yudao.module.evaluate.service.record.RecordService;
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

@Tag(name = "数据中枢综合评价系统 - 考察记录")
@RestController
@RequestMapping("/evaluate/record")
@Validated
public class RecordController {

    @Resource(name = "inspectionRecordService")
    private RecordService recordService;

    @PostMapping("/create")
    @Operation(summary = "创建考察记录")
    @PreAuthorize("@ss.hasPermission('evaluate:record:create')")
    public CommonResult<Long> createRecord(@Valid @RequestBody RecordSaveReqVO createReqVO) {
        return success(recordService.createRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新考察记录")
    @PreAuthorize("@ss.hasPermission('evaluate:record:update')")
    public CommonResult<Boolean> updateRecord(@Valid @RequestBody RecordSaveReqVO updateReqVO) {
        recordService.updateRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除考察记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:record:delete')")
    public CommonResult<Boolean> deleteRecord(@RequestParam("id") Long id) {
        recordService.deleteRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得考察记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:record:query')")
    public CommonResult<RecordRespVO> getRecord(@RequestParam("id") Long id) {
        RecordDO record = recordService.getRecord(id);
        return success(BeanUtils.toBean(record, RecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得考察记录分页")
    @PreAuthorize("@ss.hasPermission('evaluate:record:query')")
    public CommonResult<PageResult<RecordRespVO>> getRecordPage(@Valid RecordPageReqVO pageReqVO) {
        PageResult<RecordDO> pageResult = recordService.getRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出考察记录 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRecordExcel(@Valid RecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RecordDO> list = recordService.getRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "考察记录.xls", "数据", RecordRespVO.class,
                        BeanUtils.toBean(list, RecordRespVO.class));
    }

}