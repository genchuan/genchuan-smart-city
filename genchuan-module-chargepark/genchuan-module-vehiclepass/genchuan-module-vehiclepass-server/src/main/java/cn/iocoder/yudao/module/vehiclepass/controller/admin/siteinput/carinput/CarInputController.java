package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputAuditReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.carinput.CarInputDO;
import cn.iocoder.yudao.module.vehiclepass.service.siteinput.carinput.CarInputService;
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


@Tag(name = "管理后台 - 车辆录入")
@RestController
@RequestMapping("/car/input")
@Validated
public class CarInputController {

    @Resource
    private CarInputService inputService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆录入")
    @PreAuthorize("@ss.hasPermission('vehiclepass:car-input:create')")
    public CommonResult<Boolean> createInput(@Valid @RequestBody CarInputCreateReqVO createReqVO) {
        inputService.createInputByReq(createReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆录入")
    @PreAuthorize("@ss.hasPermission('car:input:update')")
    public CommonResult<Boolean> updateInput(@Valid @RequestBody CarInputSaveReqVO updateReqVO) {
        inputService.updateInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('car:input:delete')")
    public CommonResult<Boolean> deleteInput(@RequestParam("id") Long id) {
        inputService.deleteInput(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除车辆录入")
    @PreAuthorize("@ss.hasPermission('car:input:delete')")
    public CommonResult<Boolean> deleteInputList(@RequestParam("ids") List<Long> ids) {
        inputService.deleteInputListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('car:input:query')")
    public CommonResult<CarInputRespVO> getInput(@RequestParam("id") Long id) {
        CarInputDO input = inputService.getInput(id);
        return success(BeanUtils.toBean(input, CarInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆录入分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:car-input:query')")
    public CommonResult<PageResult<CarInputRespVO>> getInputPage(@Valid CarInputPageReqVO pageReqVO) {
        return success(inputService.getInputPageWithJoin(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆录入 Excel")
    @PreAuthorize("@ss.hasPermission('car:input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInputExcel(@Valid CarInputPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CarInputDO> list = inputService.getInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆录入.xls", "数据", CarInputRespVO.class,
                BeanUtils.toBean(list, CarInputRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核车辆录入")
    @PreAuthorize("@ss.hasPermission('vehiclepass:car-input:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody CarInputAuditReqVO reqVO) {
        inputService.audit(reqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认车辆录入")
    @PreAuthorize("@ss.hasPermission('vehiclepass:car-input:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody CarInputConfirmReqVO reqVO) {
        inputService.confirm(reqVO.getId());
        return success(true);
    }

    @PutMapping("/correct")
    @Operation(summary = "修正车辆录入")
    @PreAuthorize("@ss.hasPermission('vehiclepass:car-input:correct')")
    public CommonResult<Boolean> correct(@Valid @RequestBody CarInputCorrectReqVO reqVO) {
        inputService.correct(reqVO);
        return success(true);
    }

}