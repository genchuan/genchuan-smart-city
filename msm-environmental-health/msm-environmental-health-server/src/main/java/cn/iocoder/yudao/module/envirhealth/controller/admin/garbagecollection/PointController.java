package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypeOptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.PointDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.point.PointService;

@Tag(name = "字典表 - 点位")
@RestController
@RequestMapping("/envirhealth/point")
@Validated
public class PointController {

    @Resource
    private PointService pointService;

    @PostMapping("/create")
    @Operation(summary = "创建点位")
    @PreAuthorize("@ss.hasPermission('envirhealth:point:create')")
    public CommonResult<Long> createPoint(@Valid @RequestBody PointSaveReqVO createReqVO) {
        return success(pointService.createPoint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新点位")
    @PreAuthorize("@ss.hasPermission('envirhealth:point:update')")
    public CommonResult<Boolean> updatePoint(@Valid @RequestBody PointSaveReqVO updateReqVO) {
        pointService.updatePoint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除点位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:point:delete')")
    public CommonResult<Boolean> deletePoint(@RequestParam("id") Long id) {
        pointService.deletePoint(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得点位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:point:query')")
    public CommonResult<PointRespVO> getPoint(@RequestParam("id") Long id) {
        PointDO point = pointService.getPoint(id);
        return success(BeanUtils.toBean(point, PointRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得点位分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:point:query')")
    public CommonResult<PageResult<PointRespVO>> getPointPage(@Valid PointPageReqVO pageReqVO) {
        PageResult<PointDO> pageResult = pointService.getPointPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PointRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出点位 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:point:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPointExcel(@Valid PointPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PointDO> list = pointService.getPointPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "点位.xls", "数据", PointRespVO.class,
                        BeanUtils.toBean(list, PointRespVO.class));
    }

    /**
     * 获得点位字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得点位字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:point:query')")
    public CommonResult<List<PointOptionVO>> getPointOptions() {
        return success(pointService.getPointOptions());
    }
}
