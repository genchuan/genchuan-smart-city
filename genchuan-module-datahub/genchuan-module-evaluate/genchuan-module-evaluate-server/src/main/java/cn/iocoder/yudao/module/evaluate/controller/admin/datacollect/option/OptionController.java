package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.option.OptionDO;
import cn.iocoder.yudao.module.evaluate.service.option.OptionService;
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

@Tag(name = "管理后台 - 选项")
@RestController
@RequestMapping("/evaluate/option")
@Validated
public class OptionController {

    @Resource
    private OptionService optionService;

    @PostMapping("/create")
    @Operation(summary = "创建选项")
    @PreAuthorize("@ss.hasPermission('evaluate:option:create')")
    public CommonResult<Long> createOption(@Valid @RequestBody OptionSaveReqVO createReqVO) {
        return success(optionService.createOption(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新选项")
    @PreAuthorize("@ss.hasPermission('evaluate:option:update')")
    public CommonResult<Boolean> updateOption(@Valid @RequestBody OptionSaveReqVO updateReqVO) {
        optionService.updateOption(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除选项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:option:delete')")
    public CommonResult<Boolean> deleteOption(@RequestParam("id") Long id) {
        optionService.deleteOption(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除选项")
                @PreAuthorize("@ss.hasPermission('evaluate:option:delete')")
    public CommonResult<Boolean> deleteOptionList(@RequestParam("ids") List<Long> ids) {
        optionService.deleteOptionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得选项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:option:query')")
    public CommonResult<OptionRespVO> getOption(@RequestParam("id") Long id) {
        OptionDO option = optionService.getOption(id);
        return success(BeanUtils.toBean(option, OptionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得选项分页")
    @PreAuthorize("@ss.hasPermission('evaluate:option:query')")
    public CommonResult<PageResult<OptionRespVO>> getOptionPage(@Valid OptionPageReqVO pageReqVO) {
        PageResult<OptionDO> pageResult = optionService.getOptionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OptionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出选项 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:option:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOptionExcel(@Valid OptionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OptionDO> list = optionService.getOptionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "选项.xls", "数据", OptionRespVO.class,
                        BeanUtils.toBean(list, OptionRespVO.class));
    }

}