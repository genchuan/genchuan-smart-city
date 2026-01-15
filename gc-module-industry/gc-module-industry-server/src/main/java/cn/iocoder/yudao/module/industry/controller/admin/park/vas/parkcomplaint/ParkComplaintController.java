package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkcomplaint.ParkComplaintDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkcomplaint.ParkComplaintService;
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


@Tag(name = "管理后台 - 投诉记录")
@RestController
@RequestMapping("/industry/park-complaint")
@Validated
public class ParkComplaintController {

    @Resource
    private ParkComplaintService parkComplaintService;

    @PostMapping("/create")
    @Operation(summary = "创建投诉记录")
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:create')")
    public CommonResult<Long> createParkComplaint(@Valid @RequestBody ParkComplaintSaveReqVO createReqVO) {
        return success(parkComplaintService.createParkComplaint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新投诉记录")
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:update')")
    public CommonResult<Boolean> updateParkComplaint(@Valid @RequestBody ParkComplaintSaveReqVO updateReqVO) {
        parkComplaintService.updateParkComplaint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除投诉记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:delete')")
    public CommonResult<Boolean> deleteParkComplaint(@RequestParam("id") Long id) {
        parkComplaintService.deleteParkComplaint(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得投诉记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:query')")
    public CommonResult<ParkComplaintRespVO> getParkComplaint(@RequestParam("id") Long id) {
        ParkComplaintDO parkComplaint = parkComplaintService.getParkComplaint(id);
        return success(BeanUtils.toBean(parkComplaint, ParkComplaintRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得投诉记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:query')")
    public CommonResult<PageResult<ParkComplaintRespVO>> getParkComplaintPage(@Valid ParkComplaintPageReqVO pageReqVO) {
        PageResult<ParkComplaintDO> pageResult = parkComplaintService.getParkComplaintPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkComplaintRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出投诉记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-complaint:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkComplaintExcel(@Valid ParkComplaintPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkComplaintDO> list = parkComplaintService.getParkComplaintPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "投诉记录.xls", "数据", ParkComplaintRespVO.class,
                        BeanUtils.toBean(list, ParkComplaintRespVO.class));
    }

}
