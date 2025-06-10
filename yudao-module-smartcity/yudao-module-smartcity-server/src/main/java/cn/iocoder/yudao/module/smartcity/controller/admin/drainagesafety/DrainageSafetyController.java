package cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.DrainageSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.DrainageSafetyRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.DrainageSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagesafety.DrainageSafetyDO;
import cn.iocoder.yudao.module.smartcity.service.drainagesafety.DrainageSafetyService;
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

@Tag(name = "管理后台 - 排水安全")
@RestController
@RequestMapping("/smartcity/drainage-safety")
@Validated
public class DrainageSafetyController {

    @Resource
    private DrainageSafetyService drainageSafetyService;

    @PostMapping("/create")
    @Operation(summary = "创建排水安全")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:create')")
    public CommonResult<Long> createDrainageSafety(@Valid @RequestBody DrainageSafetySaveReqVO createReqVO) {
        return success(drainageSafetyService.createDrainageSafety(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排水安全")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:update')")
    public CommonResult<Boolean> updateDrainageSafety(@Valid @RequestBody DrainageSafetySaveReqVO updateReqVO) {
        drainageSafetyService.updateDrainageSafety(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排水安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:delete')")
    public CommonResult<Boolean> deleteDrainageSafety(@RequestParam("id") Long id) {
        drainageSafetyService.deleteDrainageSafety(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排水安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:query')")
    public CommonResult<DrainageSafetyRespVO> getDrainageSafety(@RequestParam("id") Long id) {
        DrainageSafetyDO drainageSafety = drainageSafetyService.getDrainageSafety(id);
        return success(BeanUtils.toBean(drainageSafety, DrainageSafetyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排水安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:query')")
    public CommonResult<PageResult<DrainageSafetyRespVO>> getDrainageSafetyPage(@Valid DrainageSafetyPageReqVO pageReqVO) {
        PageResult<DrainageSafetyDO> pageResult = drainageSafetyService.getDrainageSafetyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DrainageSafetyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排水安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:drainage-safety:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDrainageSafetyExcel(@Valid DrainageSafetyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DrainageSafetyDO> list = drainageSafetyService.getDrainageSafetyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排水安全.xls", "数据", DrainageSafetyRespVO.class,
                        BeanUtils.toBean(list, DrainageSafetyRespVO.class));
    }

}