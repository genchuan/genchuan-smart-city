package cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitSaveReqVO;
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

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carexit.ParkCarExitDO;
import cn.iocoder.yudao.module.industry.service.park.through.carexit.ParkCarExitService;

@Tag(name = "管理后台 - 离场记录")
@RestController
@RequestMapping("/industry/park-car-exit")
@Validated
public class ParkCarExitController {

    @Resource
    private ParkCarExitService parkCarExitService;

    @PostMapping("/create")
    @Operation(summary = "创建离场记录")
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:create')")
    public CommonResult<Long> createParkCarExit(@Valid @RequestBody ParkCarExitSaveReqVO createReqVO) {
        return success(parkCarExitService.createParkCarExit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新离场记录")
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:update')")
    public CommonResult<Boolean> updateParkCarExit(@Valid @RequestBody ParkCarExitSaveReqVO updateReqVO) {
        parkCarExitService.updateParkCarExit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除离场记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:delete')")
    public CommonResult<Boolean> deleteParkCarExit(@RequestParam("id") Long id) {
        parkCarExitService.deleteParkCarExit(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得离场记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:query')")
    public CommonResult<ParkCarExitRespVO> getParkCarExit(@RequestParam("id") Long id) {
        ParkCarExitDO parkCarExit = parkCarExitService.getParkCarExit(id);
        return success(BeanUtils.toBean(parkCarExit, ParkCarExitRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得离场记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:query')")
    public CommonResult<PageResult<ParkCarExitRespVO>> getParkCarExitPage(@Valid ParkCarExitPageReqVO pageReqVO) {
        PageResult<ParkCarExitDO> pageResult = parkCarExitService.getParkCarExitPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkCarExitRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出离场记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-car-exit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkCarExitExcel(@Valid ParkCarExitPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkCarExitDO> list = parkCarExitService.getParkCarExitPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "离场记录.xls", "数据", ParkCarExitRespVO.class,
                        BeanUtils.toBean(list, ParkCarExitRespVO.class));
    }

}