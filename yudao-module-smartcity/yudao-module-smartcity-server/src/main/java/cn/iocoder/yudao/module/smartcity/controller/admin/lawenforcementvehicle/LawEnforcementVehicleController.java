package cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.LawEnforcementVehiclePageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.LawEnforcementVehicleRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.LawEnforcementVehicleSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementvehicle.LawEnforcementVehicleDO;
import cn.iocoder.yudao.module.smartcity.service.lawenforcementvehicle.LawEnforcementVehicleService;
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

@Tag(name = "管理后台 - 执法车辆管理")
@RestController
@RequestMapping("/smartcity/law-enforcement-vehicle")
@Validated
public class LawEnforcementVehicleController {

    @Resource
    private LawEnforcementVehicleService lawEnforcementVehicleService;

    @PostMapping("/create")
    @Operation(summary = "创建执法车辆管理")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:create')")
    public CommonResult<Long> createLawEnforcementVehicle(@Valid @RequestBody LawEnforcementVehicleSaveReqVO createReqVO) {
        return success(lawEnforcementVehicleService.createLawEnforcementVehicle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新执法车辆管理")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:update')")
    public CommonResult<Boolean> updateLawEnforcementVehicle(@Valid @RequestBody LawEnforcementVehicleSaveReqVO updateReqVO) {
        lawEnforcementVehicleService.updateLawEnforcementVehicle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除执法车辆管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:delete')")
    public CommonResult<Boolean> deleteLawEnforcementVehicle(@RequestParam("id") Long id) {
        lawEnforcementVehicleService.deleteLawEnforcementVehicle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法车辆管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:query')")
    public CommonResult<LawEnforcementVehicleRespVO> getLawEnforcementVehicle(@RequestParam("id") Long id) {
        LawEnforcementVehicleDO lawEnforcementVehicle = lawEnforcementVehicleService.getLawEnforcementVehicle(id);
        return success(BeanUtils.toBean(lawEnforcementVehicle, LawEnforcementVehicleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得执法车辆管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:query')")
    public CommonResult<PageResult<LawEnforcementVehicleRespVO>> getLawEnforcementVehiclePage(@Valid LawEnforcementVehiclePageReqVO pageReqVO) {
        PageResult<LawEnforcementVehicleDO> pageResult = lawEnforcementVehicleService.getLawEnforcementVehiclePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LawEnforcementVehicleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出执法车辆管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:law-enforcement-vehicle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLawEnforcementVehicleExcel(@Valid LawEnforcementVehiclePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LawEnforcementVehicleDO> list = lawEnforcementVehicleService.getLawEnforcementVehiclePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "执法车辆管理.xls", "数据", LawEnforcementVehicleRespVO.class,
                        BeanUtils.toBean(list, LawEnforcementVehicleRespVO.class));
    }

}