package cn.iocoder.yudao.module.smartcity.controller.admin.landscaping;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.LandscapingPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.LandscapingRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.LandscapingSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.landscaping.LandscapingDO;
import cn.iocoder.yudao.module.smartcity.service.landscaping.LandscapingService;
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

@Tag(name = "管理后台 - 园林绿化")
@RestController
@RequestMapping("/smartcity/landscaping")
@Validated
public class LandscapingController {

    @Resource
    private LandscapingService landscapingService;

    @PostMapping("/create")
    @Operation(summary = "创建园林绿化")
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:create')")
    public CommonResult<Long> createLandscaping(@Valid @RequestBody LandscapingSaveReqVO createReqVO) {
        return success(landscapingService.createLandscaping(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新园林绿化")
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:update')")
    public CommonResult<Boolean> updateLandscaping(@Valid @RequestBody LandscapingSaveReqVO updateReqVO) {
        landscapingService.updateLandscaping(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除园林绿化")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:delete')")
    public CommonResult<Boolean> deleteLandscaping(@RequestParam("id") Long id) {
        landscapingService.deleteLandscaping(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得园林绿化")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:query')")
    public CommonResult<LandscapingRespVO> getLandscaping(@RequestParam("id") Long id) {
        LandscapingDO landscaping = landscapingService.getLandscaping(id);
        return success(BeanUtils.toBean(landscaping, LandscapingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得园林绿化分页")
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:query')")
    public CommonResult<PageResult<LandscapingRespVO>> getLandscapingPage(@Valid LandscapingPageReqVO pageReqVO) {
        PageResult<LandscapingDO> pageResult = landscapingService.getLandscapingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LandscapingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出园林绿化 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:landscaping:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLandscapingExcel(@Valid LandscapingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LandscapingDO> list = landscapingService.getLandscapingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "园林绿化.xls", "数据", LandscapingRespVO.class,
                        BeanUtils.toBean(list, LandscapingRespVO.class));
    }

}