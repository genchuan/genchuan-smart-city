package cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pointinfo.PointInfoDO;
import cn.iocoder.yudao.module.smartcity.service.pointinfo.PointInfoService;

@Tag(name = "管理后台 - 监测点位")
@RestController
@RequestMapping("/smartcity/point-info")
@Validated
public class PointInfoController {

    @Resource
    private PointInfoService pointInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建监测点位")
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:create')")
    public CommonResult<Long> createPointInfo(@Valid @RequestBody PointInfoSaveReqVO createReqVO) {
        return success(pointInfoService.createPointInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测点位")
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:update')")
    public CommonResult<Boolean> updatePointInfo(@Valid @RequestBody PointInfoSaveReqVO updateReqVO) {
        pointInfoService.updatePointInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测点位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:delete')")
    public CommonResult<Boolean> deletePointInfo(@RequestParam("id") Long id) {
        pointInfoService.deletePointInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测点位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:query')")
    public CommonResult<PointInfoRespVO> getPointInfo(@RequestParam("id") Long id) {
        PointInfoDO pointInfo = pointInfoService.getPointInfo(id);
        return success(BeanUtils.toBean(pointInfo, PointInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测点位分页")
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:query')")
    public CommonResult<PageResult<PointInfoRespVO>> getPointInfoPage(@Valid PointInfoPageReqVO pageReqVO) {
        PageResult<PointInfoDO> pageResult = pointInfoService.getPointInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PointInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测点位 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:point-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPointInfoExcel(@Valid PointInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PointInfoDO> list = pointInfoService.getPointInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测点位.xls", "数据", PointInfoRespVO.class,
                        BeanUtils.toBean(list, PointInfoRespVO.class));
    }

}