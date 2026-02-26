package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.evaluate.service.area.AreaService;
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

@Tag(name = "管理后台 - 区域编码")
@RestController
@RequestMapping("/evaluate/area")
@Validated
public class AreaController {

    @Resource
    private AreaService areaService;

    @PostMapping("/create")
    @Operation(summary = "创建区域编码")
    @PreAuthorize("@ss.hasPermission('evaluate:area:create')")
    public CommonResult<Long> createArea(@Valid @RequestBody AreaSaveReqVO createReqVO) {
        return success(areaService.createArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新区域编码")
    @PreAuthorize("@ss.hasPermission('evaluate:area:update')")
    public CommonResult<Boolean> updateArea(@Valid @RequestBody AreaSaveReqVO updateReqVO) {
        areaService.updateArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除区域编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:area:delete')")
    public CommonResult<Boolean> deleteArea(@RequestParam("id") Long id) {
        areaService.deleteArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得区域编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:area:query')")
    public CommonResult<AreaRespVO> getArea(@RequestParam("id") Long id) {
        AreaDO area = areaService.getArea(id);
        return success(BeanUtils.toBean(area, AreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得区域编码分页")
    @PreAuthorize("@ss.hasPermission('evaluate:area:query')")
    public CommonResult<PageResult<AreaRespVO>> getAreaPage(@Valid AreaPageReqVO pageReqVO) {
        PageResult<AreaDO> pageResult = areaService.getAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出区域编码 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaExcel(@Valid AreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaDO> list = areaService.getAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "区域编码.xls", "数据", AreaRespVO.class,
                        BeanUtils.toBean(list, AreaRespVO.class));
    }

}