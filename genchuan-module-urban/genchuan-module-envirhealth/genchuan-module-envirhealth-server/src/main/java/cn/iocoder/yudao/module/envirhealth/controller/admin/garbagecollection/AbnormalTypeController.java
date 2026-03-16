package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.abnormaltype.AbnormalTypeService;
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

@Tag(name = "字典表 - 垃圾异常类型")
@RestController
@RequestMapping("/envirhealth/abnormal-type")
@Validated
public class AbnormalTypeController {

    @Resource
    private AbnormalTypeService abnormalTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建垃圾异常类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:create')")
    public CommonResult<Long> createAbnormalType(@Valid @RequestBody AbnormalTypeSaveReqVO createReqVO) {
        return success(abnormalTypeService.createAbnormalType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新垃圾异常类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:update')")
    public CommonResult<Boolean> updateAbnormalType(@Valid @RequestBody AbnormalTypeSaveReqVO updateReqVO) {
        abnormalTypeService.updateAbnormalType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除垃圾异常类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:delete')")
    public CommonResult<Boolean> deleteAbnormalType(@RequestParam("id") Long id) {
        abnormalTypeService.deleteAbnormalType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得垃圾异常类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:query')")
    public CommonResult<AbnormalTypeRespVO> getAbnormalType(@RequestParam("id") Long id) {
        AbnormalTypeDO abnormalType = abnormalTypeService.getAbnormalType(id);
        return success(BeanUtils.toBean(abnormalType, AbnormalTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得垃圾异常类型字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:query')")
    public CommonResult<PageResult<AbnormalTypeRespVO>> getAbnormalTypePage(@Valid AbnormalTypePageReqVO pageReqVO) {
        PageResult<AbnormalTypeDO> pageResult = abnormalTypeService.getAbnormalTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AbnormalTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出垃圾异常类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:abnormal-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAbnormalTypeExcel(@Valid AbnormalTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AbnormalTypeDO> list = abnormalTypeService.getAbnormalTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "垃圾异常类型字典.xls", "数据", AbnormalTypeRespVO.class,
                        BeanUtils.toBean(list, AbnormalTypeRespVO.class));
    }

    /**
     * 获得收运频次字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得垃圾异常类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:abnormal-type:query')")
    public CommonResult<List<AbnormalTypeOptionVO>> getAbnormalTypeOptions() {
        return success(abnormalTypeService.getAbnormalTypeOptions());
    }
}
