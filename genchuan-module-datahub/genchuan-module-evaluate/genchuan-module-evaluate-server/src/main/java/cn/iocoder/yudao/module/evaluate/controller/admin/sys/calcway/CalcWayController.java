package cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWayPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWayRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWaySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import cn.iocoder.yudao.module.evaluate.service.calcway.CalcWayService;
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

@Tag(name = "管理后台 - 计算方式字典")
@RestController
@RequestMapping("/evaluate/calc-way")
@Validated
public class CalcWayController {

    @Resource
    private CalcWayService calcWayService;

    @PostMapping("/create")
    @Operation(summary = "创建计算方式字典")
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:create')")
    public CommonResult<Long> createCalcWay(@Valid @RequestBody CalcWaySaveReqVO createReqVO) {
        return success(calcWayService.createCalcWay(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计算方式字典")
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:update')")
    public CommonResult<Boolean> updateCalcWay(@Valid @RequestBody CalcWaySaveReqVO updateReqVO) {
        calcWayService.updateCalcWay(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计算方式字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:delete')")
    public CommonResult<Boolean> deleteCalcWay(@RequestParam("id") Long id) {
        calcWayService.deleteCalcWay(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计算方式字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:query')")
    public CommonResult<CalcWayRespVO> getCalcWay(@RequestParam("id") Long id) {
        CalcWayDO calcWay = calcWayService.getCalcWay(id);
        return success(BeanUtils.toBean(calcWay, CalcWayRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计算方式字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:query')")
    public CommonResult<PageResult<CalcWayRespVO>> getCalcWayPage(@Valid CalcWayPageReqVO pageReqVO) {
        PageResult<CalcWayDO> pageResult = calcWayService.getCalcWayPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CalcWayRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计算方式字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:calc-way:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCalcWayExcel(@Valid CalcWayPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CalcWayDO> list = calcWayService.getCalcWayPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "计算方式字典.xls", "数据", CalcWayRespVO.class,
                        BeanUtils.toBean(list, CalcWayRespVO.class));
    }

}