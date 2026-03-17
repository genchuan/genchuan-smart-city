package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilityPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilityRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo.FacilitySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.facility.FacilityDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.facility.FacilityService;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
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

@Tag(name = "字典表 - 设施")
@RestController
@RequestMapping("/envirhealth/facility")
@Validated
public class FacilityController {

    @Resource
    private FacilityService facilityService;

    @PostMapping("/create")
    @Operation(summary = "创建设施字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:create')")
    public CommonResult<Long> createFacility(@Valid @RequestBody FacilitySaveReqVO createReqVO) {
        return success(facilityService.createFacility(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设施字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:update')")
    public CommonResult<Boolean> updateFacility(@Valid @RequestBody FacilitySaveReqVO updateReqVO) {
        facilityService.updateFacility(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设施字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:delete')")
    public CommonResult<Boolean> deleteFacility(@RequestParam("id") Long id) {
        facilityService.deleteFacility(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设施字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:query')")
    public CommonResult<FacilityRespVO> getFacility(@RequestParam("id") Long id) {
        FacilityDO facility = facilityService.getFacility(id);
        return success(BeanUtils.toBean(facility, FacilityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设施字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:query')")
    public CommonResult<PageResult<FacilityRespVO>> getFacilityPage(@Valid FacilityPageReqVO pageReqVO) {
        PageResult<FacilityDO> pageResult = facilityService.getFacilityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FacilityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设施字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFacilityExcel(@Valid FacilityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FacilityDO> list = facilityService.getFacilityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设施字典.xls", "数据", FacilityRespVO.class,
                        BeanUtils.toBean(list, FacilityRespVO.class));
    }

    /**
     * 获得设施字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得设施(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:facility:query')")
    public CommonResult<List<OptionVO>> getFacilityOptions() {
        return success(facilityService.getFacilityOptions());
    }

}
