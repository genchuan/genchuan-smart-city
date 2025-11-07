package cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.InstitutionalInformationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.InstitutionalInformationInputRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.InstitutionalInformationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.institutionalinformationinput.InstitutionalInformationInputDO;
import cn.iocoder.yudao.module.smartcity.service.institutionalinformationinput.InstitutionalInformationInputService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 机构信息录入")
@RestController
@RequestMapping("/smartcity/institutional-information-input")
@Validated
public class InstitutionalInformationInputController {

    @Resource
    private InstitutionalInformationInputService institutionalInformationInputService;

    @PostMapping("/create")
    @Operation(summary = "创建机构信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:create')")
    public CommonResult<Long> createInstitutionalInformationInput(@Valid @RequestBody InstitutionalInformationInputSaveReqVO createReqVO) {
        return success(institutionalInformationInputService.createInstitutionalInformationInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新机构信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:update')")
    public CommonResult<Boolean> updateInstitutionalInformationInput(@Valid @RequestBody InstitutionalInformationInputSaveReqVO updateReqVO) {
        institutionalInformationInputService.updateInstitutionalInformationInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除机构信息录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:delete')")
    public CommonResult<Boolean> deleteInstitutionalInformationInput(@RequestParam("id") Long id) {
        institutionalInformationInputService.deleteInstitutionalInformationInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得机构信息录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:query')")
    public CommonResult<InstitutionalInformationInputRespVO> getInstitutionalInformationInput(@RequestParam("id") Long id) {
        InstitutionalInformationInputDO institutionalInformationInput = institutionalInformationInputService.getInstitutionalInformationInput(id);
        return success(BeanUtils.toBean(institutionalInformationInput, InstitutionalInformationInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得机构信息录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:query')")
    public CommonResult<PageResult<InstitutionalInformationInputRespVO>> getInstitutionalInformationInputPage(@Valid InstitutionalInformationInputPageReqVO pageReqVO) {
        PageResult<InstitutionalInformationInputDO> pageResult = institutionalInformationInputService.getInstitutionalInformationInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InstitutionalInformationInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出机构信息录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:institutional-information-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstitutionalInformationInputExcel(@Valid InstitutionalInformationInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstitutionalInformationInputDO> list = institutionalInformationInputService.getInstitutionalInformationInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "机构信息录入.xls", "数据", InstitutionalInformationInputRespVO.class,
                        BeanUtils.toBean(list, InstitutionalInformationInputRespVO.class));
    }

}