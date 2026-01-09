package cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.FloodSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.FloodSafetyRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.FloodSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.floodsafety.FloodSafetyDO;
import cn.iocoder.yudao.module.smartcity.service.floodsafety.FloodSafetyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 内涝安全")
@RestController
@RequestMapping("/smartcity/flood-safety")
@Validated
public class FloodSafetyController {

    @Resource
    private FloodSafetyService floodSafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建内涝安全")
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:create')")
    public CommonResult<Long> createFloodSafety(@Valid @RequestBody FloodSafetySaveReqVO createReqVO) {
        return success(floodSafetyService.createFloodSafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新内涝安全")
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:update')")
    public CommonResult<Boolean> updateFloodSafety(@Valid @RequestBody FloodSafetySaveReqVO updateReqVO) {
        floodSafetyService.updateFloodSafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除内涝安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:delete')")
    public CommonResult<Boolean> deleteFloodSafety(@RequestParam("id") Long id) {
        floodSafetyService.deleteFloodSafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得内涝安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:query')")
    public CommonResult<FloodSafetyRespVO> getFloodSafety(@RequestParam("id") Long id) {
        FloodSafetyDO floodSafety = floodSafetyService.getFloodSafety(id);
        return success(BeanUtils.toBean(floodSafety, FloodSafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得内涝安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:query')")
    public CommonResult<PageResult<FloodSafetyRespVO>> getFloodSafetyPage(@Valid FloodSafetyPageReqVO pageReqVO) {
        PageResult<FloodSafetyDO> pageResult = floodSafetyService.getFloodSafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FloodSafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出内涝安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:flood-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFloodSafetyExcel(@Valid FloodSafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FloodSafetyDO> list = floodSafetyService.getFloodSafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "内涝安全.xls", "数据", FloodSafetyRespVO.class,
                        BeanUtils.toBean(list, FloodSafetyRespVO.class));
    }

}