package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo.AlarmTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo.AlarmTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo.AlarmTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.AlarmTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.alarmtype.AlarmTypeService;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

@Tag(name = "字典表 - 预警类型")
@RestController
@RequestMapping("/envirhealth/alarm-type")
@Validated
public class AlarmTypeController {

    @Resource
    private AlarmTypeService alarmTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建预警类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:create')")
    public CommonResult<Long> createAlarmType(@Valid @RequestBody AlarmTypeSaveReqVO createReqVO) {
        return success(alarmTypeService.createAlarmType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:update')")
    public CommonResult<Boolean> updateAlarmType(@Valid @RequestBody AlarmTypeSaveReqVO updateReqVO) {
        alarmTypeService.updateAlarmType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:delete')")
    public CommonResult<Boolean> deleteAlarmType(@RequestParam("id") Long id) {
        alarmTypeService.deleteAlarmType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:query')")
    public CommonResult<AlarmTypeRespVO> getAlarmType(@RequestParam("id") Long id) {
        AlarmTypeDO alarmType = alarmTypeService.getAlarmType(id);
        return success(BeanUtils.toBean(alarmType, AlarmTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警类型字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:query')")
    public CommonResult<PageResult<AlarmTypeRespVO>> getAlarmTypePage(@Valid AlarmTypePageReqVO pageReqVO) {
        PageResult<AlarmTypeDO> pageResult = alarmTypeService.getAlarmTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:alarm-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmTypeExcel(@Valid AlarmTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmTypeDO> list = alarmTypeService.getAlarmTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警类型字典.xls", "数据", AlarmTypeRespVO.class,
                        BeanUtils.toBean(list, AlarmTypeRespVO.class));
    }

    /**
     * 获得预警类型下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得预警类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:facility:query')")
    public CommonResult<List<OptionVO>> getAlarmTypeOptions() {
        return success(alarmTypeService.getAlarmTypeOptions());
    }
}
