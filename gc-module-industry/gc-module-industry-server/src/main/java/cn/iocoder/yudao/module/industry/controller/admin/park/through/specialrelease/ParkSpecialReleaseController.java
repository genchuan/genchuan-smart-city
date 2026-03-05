package cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleasePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleaseRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleaseSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.specialrelease.ParkSpecialReleaseDO;
import cn.iocoder.yudao.module.industry.service.park.through.specialrelease.ParkSpecialReleaseService;

@Tag(name = "管理后台 - 特殊放行")
@RestController
@RequestMapping("/industry/park-special-release")
@Validated
public class ParkSpecialReleaseController {

    @Resource
    private ParkSpecialReleaseService parkSpecialReleaseService;

    @PostMapping("/create")
    @Operation(summary = "创建特殊放行")
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:create')")
    public CommonResult<Long> createParkSpecialRelease(@Valid @RequestBody ParkSpecialReleaseSaveReqVO createReqVO) {
        return success(parkSpecialReleaseService.createParkSpecialRelease(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新特殊放行")
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:update')")
    public CommonResult<Boolean> updateParkSpecialRelease(@Valid @RequestBody ParkSpecialReleaseSaveReqVO updateReqVO) {
        parkSpecialReleaseService.updateParkSpecialRelease(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除特殊放行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:delete')")
    public CommonResult<Boolean> deleteParkSpecialRelease(@RequestParam("id") Long id) {
        parkSpecialReleaseService.deleteParkSpecialRelease(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得特殊放行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:query')")
    public CommonResult<ParkSpecialReleaseRespVO> getParkSpecialRelease(@RequestParam("id") Long id) {
        ParkSpecialReleaseDO parkSpecialRelease = parkSpecialReleaseService.getParkSpecialRelease(id);
        return success(BeanUtils.toBean(parkSpecialRelease, ParkSpecialReleaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得特殊放行分页")
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:query')")
    public CommonResult<PageResult<ParkSpecialReleaseRespVO>> getParkSpecialReleasePage(@Valid ParkSpecialReleasePageReqVO pageReqVO) {
        PageResult<ParkSpecialReleaseDO> pageResult = parkSpecialReleaseService.getParkSpecialReleasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSpecialReleaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出特殊放行 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-special-release:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSpecialReleaseExcel(@Valid ParkSpecialReleasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSpecialReleaseDO> list = parkSpecialReleaseService.getParkSpecialReleasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "特殊放行.xls", "数据", ParkSpecialReleaseRespVO.class,
                        BeanUtils.toBean(list, ParkSpecialReleaseRespVO.class));
    }

}