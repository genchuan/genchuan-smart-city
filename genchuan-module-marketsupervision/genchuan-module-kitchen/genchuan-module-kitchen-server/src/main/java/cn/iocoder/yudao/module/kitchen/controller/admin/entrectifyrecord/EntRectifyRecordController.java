package cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.service.entrectifyrecord.EntRectifyRecordService;
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


@Tag(name = "管理后台 - 企业整改记录")
@RestController
@RequestMapping("/kitchen/ent-rectify-record")
@Validated
public class EntRectifyRecordController {

    @Resource
    private EntRectifyRecordService entRectifyRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建企业整改记录")
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:create')")
    public CommonResult<Long> createEntRectifyRecord(@Valid @RequestBody EntRectifyRecordSaveReqVO createReqVO) {
        return success(entRectifyRecordService.createEntRectifyRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新企业整改记录")
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:update')")
    public CommonResult<Boolean> updateEntRectifyRecord(@Valid @RequestBody EntRectifyRecordSaveReqVO updateReqVO) {
        entRectifyRecordService.updateEntRectifyRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业整改记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:delete')")
    public CommonResult<Boolean> deleteEntRectifyRecord(@RequestParam("id") Long id) {
        entRectifyRecordService.deleteEntRectifyRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业整改记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:query')")
    public CommonResult<EntRectifyRecordRespVO> getEntRectifyRecord(@RequestParam("id") Long id) {
        EntRectifyRecordDO entRectifyRecord = entRectifyRecordService.getEntRectifyRecord(id);
        return success(BeanUtils.toBean(entRectifyRecord, EntRectifyRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业整改记录分页")
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:query')")
    public CommonResult<PageResult<EntRectifyRecordRespVO>> getEntRectifyRecordPage(@Valid EntRectifyRecordPageReqVO pageReqVO) {
        PageResult<EntRectifyRecordDO> pageResult = entRectifyRecordService.getEntRectifyRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EntRectifyRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业整改记录 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:ent-rectify-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEntRectifyRecordExcel(@Valid EntRectifyRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EntRectifyRecordDO> list = entRectifyRecordService.getEntRectifyRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业整改记录.xls", "数据", EntRectifyRecordRespVO.class,
                        BeanUtils.toBean(list, EntRectifyRecordRespVO.class));
    }

}
