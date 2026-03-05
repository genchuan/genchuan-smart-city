package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.util.options.vo.OptionVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ComplaintTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.complainttype.ComplaintTypeService;

@Tag(name = "字典表 - 投诉类型")
@RestController
@RequestMapping("/envirhealth/complaint-type")
@Validated
public class ComplaintTypeController {

    @Resource
    private ComplaintTypeService complaintTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建投诉类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:create')")
    public CommonResult<Long> createComplaintType(@Valid @RequestBody ComplaintTypeSaveReqVO createReqVO) {
        return success(complaintTypeService.createComplaintType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新投诉类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:update')")
    public CommonResult<Boolean> updateComplaintType(@Valid @RequestBody ComplaintTypeSaveReqVO updateReqVO) {
        complaintTypeService.updateComplaintType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除投诉类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:delete')")
    public CommonResult<Boolean> deleteComplaintType(@RequestParam("id") Long id) {
        complaintTypeService.deleteComplaintType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得投诉类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:query')")
    public CommonResult<ComplaintTypeRespVO> getComplaintType(@RequestParam("id") Long id) {
        ComplaintTypeDO complaintType = complaintTypeService.getComplaintType(id);
        return success(BeanUtils.toBean(complaintType, ComplaintTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得投诉类型字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:query')")
    public CommonResult<PageResult<ComplaintTypeRespVO>> getComplaintTypePage(@Valid ComplaintTypePageReqVO pageReqVO) {
        PageResult<ComplaintTypeDO> pageResult = complaintTypeService.getComplaintTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ComplaintTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出投诉类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:complaint-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportComplaintTypeExcel(@Valid ComplaintTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ComplaintTypeDO> list = complaintTypeService.getComplaintTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "投诉类型字典.xls", "数据", ComplaintTypeRespVO.class,
                        BeanUtils.toBean(list, ComplaintTypeRespVO.class));
    }

    /**
     * 获得投诉类型下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得投诉类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:complaint-type:query')")
    public CommonResult<List<OptionVO>> getComplaintTypeOptions() {
        return success(complaintTypeService.getComplaintTypeOptions());
    }
}
