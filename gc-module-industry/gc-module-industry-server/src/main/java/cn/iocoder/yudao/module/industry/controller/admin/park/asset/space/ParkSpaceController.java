package cn.iocoder.yudao.module.industry.controller.admin.park.asset.space;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpacePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpaceRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpaceSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.space.ParkSpaceDO;
import cn.iocoder.yudao.module.industry.service.park.asset.space.ParkSpaceService;

@Tag(name = "管理后台 - 车位信息")
@RestController
@RequestMapping("/industry/park-space")
@Validated
public class ParkSpaceController {

    @Resource
    private ParkSpaceService parkSpaceService;

    @PostMapping("/create")
    @Operation(summary = "创建车位信息")
    @PreAuthorize("@ss.hasPermission('industry:park-space:create')")
    public CommonResult<Long> createParkSpace(@Valid @RequestBody ParkSpaceSaveReqVO createReqVO) {
        return success(parkSpaceService.createParkSpace(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车位信息")
    @PreAuthorize("@ss.hasPermission('industry:park-space:update')")
    public CommonResult<Boolean> updateParkSpace(@Valid @RequestBody ParkSpaceSaveReqVO updateReqVO) {
        parkSpaceService.updateParkSpace(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车位信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-space:delete')")
    public CommonResult<Boolean> deleteParkSpace(@RequestParam("id") Long id) {
        parkSpaceService.deleteParkSpace(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车位信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-space:query')")
    public CommonResult<ParkSpaceRespVO> getParkSpace(@RequestParam("id") Long id) {
        ParkSpaceDO parkSpace = parkSpaceService.getParkSpace(id);
        return success(BeanUtils.toBean(parkSpace, ParkSpaceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位信息分页")
    @PreAuthorize("@ss.hasPermission('industry:park-space:query')")
    public CommonResult<PageResult<ParkSpaceRespVO>> getParkSpacePage(@Valid ParkSpacePageReqVO pageReqVO) {
        PageResult<ParkSpaceDO> pageResult = parkSpaceService.getParkSpacePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSpaceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车位信息 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-space:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSpaceExcel(@Valid ParkSpacePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSpaceDO> list = parkSpaceService.getParkSpacePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车位信息.xls", "数据", ParkSpaceRespVO.class,
                        BeanUtils.toBean(list, ParkSpaceRespVO.class));
    }

}