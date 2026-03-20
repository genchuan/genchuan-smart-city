package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo.GreenTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.greentype.vo.GreenTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.GreenTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.greentype.GreenTypeService;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

@Tag(name = "字典表 - 绿化品类")
@RestController
@RequestMapping("/envirhealth/green-type")
@Validated
public class GreenTypeController {

    @Resource
    private GreenTypeService greenTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建绿化品类字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:create')")
    public CommonResult<Long> createGreenType(@Valid @RequestBody GreenTypeSaveReqVO createReqVO) {
        return success(greenTypeService.createGreenType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新绿化品类字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:update')")
    public CommonResult<Boolean> updateGreenType(@Valid @RequestBody GreenTypeSaveReqVO updateReqVO) {
        greenTypeService.updateGreenType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除绿化品类字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:delete')")
    public CommonResult<Boolean> deleteGreenType(@RequestParam("id") Long id) {
        greenTypeService.deleteGreenType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得绿化品类字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:query')")
    public CommonResult<GreenTypeRespVO> getGreenType(@RequestParam("id") Long id) {
        GreenTypeDO greenType = greenTypeService.getGreenType(id);
        return success(BeanUtils.toBean(greenType, GreenTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得绿化品类字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:query')")
    public CommonResult<PageResult<GreenTypeRespVO>> getGreenTypePage(@Valid GreenTypePageReqVO pageReqVO) {
        PageResult<GreenTypeDO> pageResult = greenTypeService.getGreenTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GreenTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出绿化品类字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:green-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGreenTypeExcel(@Valid GreenTypePageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GreenTypeDO> list = greenTypeService.getGreenTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "绿化品类字典表.xls", "数据", GreenTypeRespVO.class,
                BeanUtils.toBean(list, GreenTypeRespVO.class));
    }

    /**
     * 获得绿化品类字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得绿化品类(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:green-type:query')")
    public CommonResult<List<OptionVO>> getGreenTypeOptions() {
        return success(greenTypeService.getGreenTypeOptions());
    }

}
