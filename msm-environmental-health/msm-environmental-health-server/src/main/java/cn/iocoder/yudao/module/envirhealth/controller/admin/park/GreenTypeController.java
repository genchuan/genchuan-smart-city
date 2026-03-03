/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.park;

import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypeSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.GreenTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.park.greentype.GreenTypeService;

@Tag(name = "环境卫生管理 - 绿化品类字典表")
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

}*/
