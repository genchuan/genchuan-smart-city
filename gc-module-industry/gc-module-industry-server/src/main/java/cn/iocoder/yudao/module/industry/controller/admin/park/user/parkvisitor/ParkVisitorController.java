package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkvisitor.ParkVisitorDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkvisitor.ParkVisitorService;
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


@Tag(name = "漳州停车管理-用户商户域 - 访客")
@RestController
@RequestMapping("/industry/park-visitor")
@Validated
public class ParkVisitorController {

    @Resource
    private ParkVisitorService parkVisitorService;

    @PostMapping("/create")
    @Operation(summary = "创建访客")
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:create')")
    public CommonResult<Long> createParkVisitor(@Valid @RequestBody ParkVisitorSaveReqVO createReqVO) {
        return success(parkVisitorService.createParkVisitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新访客")
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:update')")
    public CommonResult<Boolean> updateParkVisitor(@Valid @RequestBody ParkVisitorSaveReqVO updateReqVO) {
        parkVisitorService.updateParkVisitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除访客")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:delete')")
    public CommonResult<Boolean> deleteParkVisitor(@RequestParam("id") Long id) {
        parkVisitorService.deleteParkVisitor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得访客")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:query')")
    public CommonResult<ParkVisitorRespVO> getParkVisitor(@RequestParam("id") Long id) {
        ParkVisitorDO parkVisitor = parkVisitorService.getParkVisitor(id);
        return success(BeanUtils.toBean(parkVisitor, ParkVisitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得访客分页")
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:query')")
    public CommonResult<PageResult<ParkVisitorRespVO>> getParkVisitorPage(@Valid ParkVisitorPageReqVO pageReqVO) {
        PageResult<ParkVisitorDO> pageResult = parkVisitorService.getParkVisitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkVisitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出访客 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-visitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkVisitorExcel(@Valid ParkVisitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkVisitorDO> list = parkVisitorService.getParkVisitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "访客.xls", "数据", ParkVisitorRespVO.class,
                        BeanUtils.toBean(list, ParkVisitorRespVO.class));
    }

}
