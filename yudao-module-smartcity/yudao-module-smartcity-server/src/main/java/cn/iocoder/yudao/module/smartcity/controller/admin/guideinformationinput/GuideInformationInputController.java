package cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.GuideInformationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.GuideInformationInputRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.GuideInformationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.guideinformationinput.GuideInformationInputDO;
import cn.iocoder.yudao.module.smartcity.service.guideinformationinput.GuideInformationInputService;
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

@Tag(name = "管理后台 - 指南信息录入")
@RestController
@RequestMapping("/smartcity/guide-information-input")
@Validated
public class GuideInformationInputController {

    @Resource
    private GuideInformationInputService guideInformationInputService;

    @PostMapping("/create")
    @Operation(summary = "创建指南信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:create')")
    public CommonResult<Long> createGuideInformationInput(@Valid @RequestBody GuideInformationInputSaveReqVO createReqVO) {
        return success(guideInformationInputService.createGuideInformationInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指南信息录入")
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:update')")
    public CommonResult<Boolean> updateGuideInformationInput(@Valid @RequestBody GuideInformationInputSaveReqVO updateReqVO) {
        guideInformationInputService.updateGuideInformationInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指南信息录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:delete')")
    public CommonResult<Boolean> deleteGuideInformationInput(@RequestParam("id") Long id) {
        guideInformationInputService.deleteGuideInformationInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指南信息录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:query')")
    public CommonResult<GuideInformationInputRespVO> getGuideInformationInput(@RequestParam("id") Long id) {
        GuideInformationInputDO guideInformationInput = guideInformationInputService.getGuideInformationInput(id);
        return success(BeanUtils.toBean(guideInformationInput, GuideInformationInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指南信息录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:query')")
    public CommonResult<PageResult<GuideInformationInputRespVO>> getGuideInformationInputPage(@Valid GuideInformationInputPageReqVO pageReqVO) {
        PageResult<GuideInformationInputDO> pageResult = guideInformationInputService.getGuideInformationInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GuideInformationInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指南信息录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:guide-information-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGuideInformationInputExcel(@Valid GuideInformationInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GuideInformationInputDO> list = guideInformationInputService.getGuideInformationInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指南信息录入.xls", "数据", GuideInformationInputRespVO.class,
                        BeanUtils.toBean(list, GuideInformationInputRespVO.class));
    }

}