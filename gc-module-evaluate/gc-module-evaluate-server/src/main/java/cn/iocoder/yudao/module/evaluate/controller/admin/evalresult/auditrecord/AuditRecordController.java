package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.auditrecord.AuditRecordDO;
import cn.iocoder.yudao.module.evaluate.service.auditrecord.AuditRecordService;
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

@Tag(name = "管理后台 - 评价结果审核")
@RestController
@RequestMapping("/evaluate/audit-record")
@Validated
public class AuditRecordController {

    @Resource
    private AuditRecordService auditRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建评价结果审核")
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:create')")
    public CommonResult<Long> createAuditRecord(@Valid @RequestBody AuditRecordSaveReqVO createReqVO) {
        return success(auditRecordService.createAuditRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价结果审核")
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:update')")
    public CommonResult<Boolean> updateAuditRecord(@Valid @RequestBody AuditRecordSaveReqVO updateReqVO) {
        auditRecordService.updateAuditRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价结果审核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:delete')")
    public CommonResult<Boolean> deleteAuditRecord(@RequestParam("id") Long id) {
        auditRecordService.deleteAuditRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价结果审核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:query')")
    public CommonResult<AuditRecordRespVO> getAuditRecord(@RequestParam("id") Long id) {
        AuditRecordDO auditRecord = auditRecordService.getAuditRecord(id);
        return success(BeanUtils.toBean(auditRecord, AuditRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价结果审核分页")
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:query')")
    public CommonResult<PageResult<AuditRecordRespVO>> getAuditRecordPage(@Valid AuditRecordPageReqVO pageReqVO) {
        PageResult<AuditRecordDO> pageResult = auditRecordService.getAuditRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AuditRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价结果审核 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:audit-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAuditRecordExcel(@Valid AuditRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AuditRecordDO> list = auditRecordService.getAuditRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价结果审核.xls", "数据", AuditRecordRespVO.class,
                        BeanUtils.toBean(list, AuditRecordRespVO.class));
    }

}