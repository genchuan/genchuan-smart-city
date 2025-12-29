package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkberthlock.ParkBerthLockDO;
import cn.iocoder.yudao.module.industry.service.park.pay.parkberthlock.ParkBerthLockService;
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



@Tag(name = "暂时不用-停车管理-泊位锁定记录")
@RestController
@RequestMapping("/industry/park-berth-lock")
@Validated
public class ParkBerthLockController {

    @Resource
    private ParkBerthLockService parkBerthLockService;

    @PostMapping("/create")
    @Operation(summary = "创建泊位锁定记录")
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:create')")
    public CommonResult<Long> createParkBerthLock(@Valid @RequestBody ParkBerthLockSaveReqVO createReqVO) {
        return success(parkBerthLockService.createParkBerthLock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新泊位锁定记录")
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:update')")
    public CommonResult<Boolean> updateParkBerthLock(@Valid @RequestBody ParkBerthLockSaveReqVO updateReqVO) {
        parkBerthLockService.updateParkBerthLock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除泊位锁定记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:delete')")
    public CommonResult<Boolean> deleteParkBerthLock(@RequestParam("id") Long id) {
        parkBerthLockService.deleteParkBerthLock(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得泊位锁定记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:query')")
    public CommonResult<ParkBerthLockRespVO> getParkBerthLock(@RequestParam("id") Long id) {
        ParkBerthLockDO parkBerthLock = parkBerthLockService.getParkBerthLock(id);
        return success(BeanUtils.toBean(parkBerthLock, ParkBerthLockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得泊位锁定记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:query')")
    public CommonResult<PageResult<ParkBerthLockRespVO>> getParkBerthLockPage(@Valid ParkBerthLockPageReqVO pageReqVO) {
        PageResult<ParkBerthLockDO> pageResult = parkBerthLockService.getParkBerthLockPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkBerthLockRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出泊位锁定记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-berth-lock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkBerthLockExcel(@Valid ParkBerthLockPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkBerthLockDO> list = parkBerthLockService.getParkBerthLockPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "泊位锁定记录.xls", "数据", ParkBerthLockRespVO.class,
                        BeanUtils.toBean(list, ParkBerthLockRespVO.class));
    }

}
