package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumableRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumableSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ConsumableDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.consumable.ConsumableService;
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

@Tag(name = "字典表 - 耗材字典")
@RestController
@RequestMapping("/envirhealth/consumable")
@Validated
public class ConsumableController {

    @Resource
    private ConsumableService consumableService;

    @PostMapping("/create")
    @Operation(summary = "创建耗材字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:create')")
    public CommonResult<Long> createConsumable(@Valid @RequestBody ConsumableSaveReqVO createReqVO) {
        return success(consumableService.createConsumable(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新耗材字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:update')")
    public CommonResult<Boolean> updateConsumable(@Valid @RequestBody ConsumableSaveReqVO updateReqVO) {
        consumableService.updateConsumable(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除耗材字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:delete')")
    public CommonResult<Boolean> deleteConsumable(@RequestParam("id") Long id) {
        consumableService.deleteConsumable(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得耗材字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:query')")
    public CommonResult<ConsumableRespVO> getConsumable(@RequestParam("id") Long id) {
        ConsumableDO consumable = consumableService.getConsumable(id);
        return success(BeanUtils.toBean(consumable, ConsumableRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得耗材字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:query')")
    public CommonResult<PageResult<ConsumableRespVO>> getConsumablePage(@Valid ConsumablePageReqVO pageReqVO) {
        PageResult<ConsumableDO> pageResult = consumableService.getConsumablePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ConsumableRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出耗材字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:consumable:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportConsumableExcel(@Valid ConsumablePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ConsumableDO> list = consumableService.getConsumablePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "耗材字典.xls", "数据", ConsumableRespVO.class,
                        BeanUtils.toBean(list, ConsumableRespVO.class));
    }

    /**
     * 获得投诉类型下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得耗材字典(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:consumable:query')")
    public CommonResult<List<OptionVO>> getConsumableOptions() {
        return success(consumableService.getConsumableOptions());
    }
}
