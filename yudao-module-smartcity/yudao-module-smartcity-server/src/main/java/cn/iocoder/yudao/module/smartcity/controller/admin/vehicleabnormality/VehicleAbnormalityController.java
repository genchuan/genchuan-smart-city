package cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.VehicleAbnormalityPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.VehicleAbnormalityRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleabnormality.vo.VehicleAbnormalitySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleabnormality.VehicleAbnormalityDO;
import cn.iocoder.yudao.module.smartcity.service.vehicleabnormality.VehicleAbnormalityService;
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

@Tag(name = "管理后台 - 车辆异常")
@RestController
@RequestMapping("/smartcity/vehicle-abnormality")
@Validated
public class VehicleAbnormalityController {

    @Resource
    private VehicleAbnormalityService vehicleAbnormalityService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆异常")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:create')")
    public CommonResult<Long> createVehicleAbnormality(@Valid @RequestBody VehicleAbnormalitySaveReqVO createReqVO) {
        return success(vehicleAbnormalityService.createVehicleAbnormality(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆异常")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:update')")
    public CommonResult<Boolean> updateVehicleAbnormality(@Valid @RequestBody VehicleAbnormalitySaveReqVO updateReqVO) {
        vehicleAbnormalityService.updateVehicleAbnormality(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆异常")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:delete')")
    public CommonResult<Boolean> deleteVehicleAbnormality(@RequestParam("id") Long id) {
        vehicleAbnormalityService.deleteVehicleAbnormality(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆异常")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:query')")
    public CommonResult<VehicleAbnormalityRespVO> getVehicleAbnormality(@RequestParam("id") Long id) {
        VehicleAbnormalityDO vehicleAbnormality = vehicleAbnormalityService.getVehicleAbnormality(id);
        return success(BeanUtils.toBean(vehicleAbnormality, VehicleAbnormalityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆异常分页")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:query')")
    public CommonResult<PageResult<VehicleAbnormalityRespVO>> getVehicleAbnormalityPage(@Valid VehicleAbnormalityPageReqVO pageReqVO) {
        PageResult<VehicleAbnormalityDO> pageResult = vehicleAbnormalityService.getVehicleAbnormalityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleAbnormalityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆异常 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:vehicle-abnormality:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportVehicleAbnormalityExcel(@Valid VehicleAbnormalityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleAbnormalityDO> list = vehicleAbnormalityService.getVehicleAbnormalityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆异常.xls", "数据", VehicleAbnormalityRespVO.class,
                        BeanUtils.toBean(list, VehicleAbnormalityRespVO.class));
    }

}