package cn.iocoder.yudao.module.data.controller.admin.code;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.GeoCodePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.GeoCodeRespVO;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.GeoCodeSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.GeoCodeTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.code.GeoCodeDO;
import cn.iocoder.yudao.module.data.service.code.GeoCodeService;
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

@Tag(name = "管理后台 - 地理编码")
@RestController
@RequestMapping("/data/geo-code")
@Validated
public class GeoCodeController {

    @Resource
    private GeoCodeService geoCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建地理编码")
    @PreAuthorize("@ss.hasPermission('data:geo-code:create')")
    public CommonResult<Long> createGeoCode(@Valid @RequestBody GeoCodeSaveReqVO createReqVO) {
        return success(geoCodeService.createGeoCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地理编码")
    @PreAuthorize("@ss.hasPermission('data:geo-code:update')")
    public CommonResult<Boolean> updateGeoCode(@Valid @RequestBody GeoCodeSaveReqVO updateReqVO) {
        geoCodeService.updateGeoCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地理编码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:geo-code:delete')")
    public CommonResult<Boolean> deleteGeoCode(@RequestParam("id") Long id) {
        geoCodeService.deleteGeoCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地理编码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:geo-code:query')")
    public CommonResult<GeoCodeRespVO> getGeoCode(@RequestParam("id") Long id) {
        GeoCodeDO geoCode = geoCodeService.getGeoCode(id);
        return success(BeanUtils.toBean(geoCode, GeoCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地理编码分页")
    @PreAuthorize("@ss.hasPermission('data:geo-code:query')")
    public CommonResult<PageResult<GeoCodeRespVO>> getGeoCodePage(@Valid GeoCodePageReqVO pageReqVO) {
        PageResult<GeoCodeDO> pageResult = geoCodeService.getGeoCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GeoCodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出地理编码 Excel")
    @PreAuthorize("@ss.hasPermission('data:geo-code:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGeoCodeExcel(@Valid GeoCodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GeoCodeDO> list = geoCodeService.getGeoCodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "地理编码.xls", "数据", GeoCodeRespVO.class,
                        BeanUtils.toBean(list, GeoCodeRespVO.class));
    }

    @GetMapping("/tree")
    @Operation(summary = "获得地理编码树")
    @PreAuthorize("@ss.hasPermission('data:geo-code:query')")
    public CommonResult<List<GeoCodeTreeRespVO>> getGeoCodeTree() {
        List<GeoCodeTreeRespVO> tree = geoCodeService.getGeoCodeTree();
        return success(tree);
    }

}