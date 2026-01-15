package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceSharePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceShareRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshare.vo.ParkSpaceShareSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshare.ParkSpaceShareDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshare.ParkSpaceShareService;
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


@Tag(name = "管理后台 - 车位共享配置")
@RestController
@RequestMapping("/industry/park-space-share")
@Validated
public class ParkSpaceShareController {

    @Resource
    private ParkSpaceShareService parkSpaceShareService;

    @PostMapping("/create")
    @Operation(summary = "创建车位共享配置")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:create')")
    public CommonResult<Long> createParkSpaceShare(@Valid @RequestBody ParkSpaceShareSaveReqVO createReqVO) {
        return success(parkSpaceShareService.createParkSpaceShare(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车位共享配置")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:update')")
    public CommonResult<Boolean> updateParkSpaceShare(@Valid @RequestBody ParkSpaceShareSaveReqVO updateReqVO) {
        parkSpaceShareService.updateParkSpaceShare(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车位共享配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:delete')")
    public CommonResult<Boolean> deleteParkSpaceShare(@RequestParam("id") Long id) {
        parkSpaceShareService.deleteParkSpaceShare(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车位共享配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:query')")
    public CommonResult<ParkSpaceShareRespVO> getParkSpaceShare(@RequestParam("id") Long id) {
        ParkSpaceShareDO parkSpaceShare = parkSpaceShareService.getParkSpaceShare(id);
        return success(BeanUtils.toBean(parkSpaceShare, ParkSpaceShareRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位共享配置分页")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:query')")
    public CommonResult<PageResult<ParkSpaceShareRespVO>> getParkSpaceSharePage(@Valid ParkSpaceSharePageReqVO pageReqVO) {
        PageResult<ParkSpaceShareDO> pageResult = parkSpaceShareService.getParkSpaceSharePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSpaceShareRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车位共享配置 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSpaceShareExcel(@Valid ParkSpaceSharePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSpaceShareDO> list = parkSpaceShareService.getParkSpaceSharePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车位共享配置.xls", "数据", ParkSpaceShareRespVO.class,
                        BeanUtils.toBean(list, ParkSpaceShareRespVO.class));
    }

}
