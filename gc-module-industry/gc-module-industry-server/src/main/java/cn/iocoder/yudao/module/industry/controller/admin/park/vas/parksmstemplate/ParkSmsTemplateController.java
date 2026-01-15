package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplatePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplateRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplateSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parksmstemplate.ParkSmsTemplateDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parksmstemplate.ParkSmsTemplateService;
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


@Tag(name = "管理后台 - 短信模板")
@RestController
@RequestMapping("/industry/park-sms-template")
@Validated
public class ParkSmsTemplateController {

    @Resource
    private ParkSmsTemplateService parkSmsTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建短信模板")
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:create')")
    public CommonResult<Long> createParkSmsTemplate(@Valid @RequestBody ParkSmsTemplateSaveReqVO createReqVO) {
        return success(parkSmsTemplateService.createParkSmsTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新短信模板")
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:update')")
    public CommonResult<Boolean> updateParkSmsTemplate(@Valid @RequestBody ParkSmsTemplateSaveReqVO updateReqVO) {
        parkSmsTemplateService.updateParkSmsTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除短信模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:delete')")
    public CommonResult<Boolean> deleteParkSmsTemplate(@RequestParam("id") Long id) {
        parkSmsTemplateService.deleteParkSmsTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得短信模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:query')")
    public CommonResult<ParkSmsTemplateRespVO> getParkSmsTemplate(@RequestParam("id") Long id) {
        ParkSmsTemplateDO parkSmsTemplate = parkSmsTemplateService.getParkSmsTemplate(id);
        return success(BeanUtils.toBean(parkSmsTemplate, ParkSmsTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得短信模板分页")
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:query')")
    public CommonResult<PageResult<ParkSmsTemplateRespVO>> getParkSmsTemplatePage(@Valid ParkSmsTemplatePageReqVO pageReqVO) {
        PageResult<ParkSmsTemplateDO> pageResult = parkSmsTemplateService.getParkSmsTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSmsTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出短信模板 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-sms-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSmsTemplateExcel(@Valid ParkSmsTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSmsTemplateDO> list = parkSmsTemplateService.getParkSmsTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "短信模板.xls", "数据", ParkSmsTemplateRespVO.class,
                        BeanUtils.toBean(list, ParkSmsTemplateRespVO.class));
    }

}
