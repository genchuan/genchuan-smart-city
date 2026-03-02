package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.publicrecord.PublicRecordDO;
import cn.iocoder.yudao.module.evaluate.service.publicrecord.PublicRecordService;
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

@Tag(name = "管理后台 - 评价结果公示")
@RestController
@RequestMapping("/evaluate/public-record")
@Validated
public class PublicRecordController {

    @Resource
    private PublicRecordService publicRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建评价结果公示")
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:create')")
    public CommonResult<Long> createPublicRecord(@Valid @RequestBody PublicRecordSaveReqVO createReqVO) {
        return success(publicRecordService.createPublicRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价结果公示")
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:update')")
    public CommonResult<Boolean> updatePublicRecord(@Valid @RequestBody PublicRecordSaveReqVO updateReqVO) {
        publicRecordService.updatePublicRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价结果公示")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:delete')")
    public CommonResult<Boolean> deletePublicRecord(@RequestParam("id") Long id) {
        publicRecordService.deletePublicRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价结果公示")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:query')")
    public CommonResult<PublicRecordRespVO> getPublicRecord(@RequestParam("id") Long id) {
        PublicRecordDO publicRecord = publicRecordService.getPublicRecord(id);
        return success(BeanUtils.toBean(publicRecord, PublicRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价结果公示分页")
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:query')")
    public CommonResult<PageResult<PublicRecordRespVO>> getPublicRecordPage(@Valid PublicRecordPageReqVO pageReqVO) {
        PageResult<PublicRecordDO> pageResult = publicRecordService.getPublicRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价结果公示 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:public-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicRecordExcel(@Valid PublicRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicRecordDO> list = publicRecordService.getPublicRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价结果公示.xls", "数据", PublicRecordRespVO.class,
                        BeanUtils.toBean(list, PublicRecordRespVO.class));
    }

}