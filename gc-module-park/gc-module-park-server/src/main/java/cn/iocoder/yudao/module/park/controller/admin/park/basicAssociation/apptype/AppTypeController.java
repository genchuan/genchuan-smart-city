package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.apptype.AppTypeDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.apptype.AppTypeService;
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

@Tag(name = "管理后台 - 行业应用类别")
@RestController
@RequestMapping("/park/app-type")
@Validated
public class AppTypeController {

    @Resource
    private AppTypeService appTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建行业应用类别")
    @PreAuthorize("@ss.hasPermission('park:app-type:create')")
    public CommonResult<Long> createAppType(@Valid @RequestBody AppTypeSaveReqVO createReqVO) {
        return success(appTypeService.createAppType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行业应用类别")
    @PreAuthorize("@ss.hasPermission('park:app-type:update')")
    public CommonResult<Boolean> updateAppType(@Valid @RequestBody AppTypeSaveReqVO updateReqVO) {
        appTypeService.updateAppType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行业应用类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:app-type:delete')")
    public CommonResult<Boolean> deleteAppType(@RequestParam("id") Long id) {
        appTypeService.deleteAppType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行业应用类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:app-type:query')")
    public CommonResult<AppTypeRespVO> getAppType(@RequestParam("id") Long id) {
        AppTypeDO appType = appTypeService.getAppType(id);
        return success(BeanUtils.toBean(appType, AppTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行业应用类别分页")
    @PreAuthorize("@ss.hasPermission('park:app-type:query')")
    public CommonResult<PageResult<AppTypeRespVO>> getAppTypePage(@Valid AppTypePageReqVO pageReqVO) {
        PageResult<AppTypeDO> pageResult = appTypeService.getAppTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行业应用类别 Excel")
    @PreAuthorize("@ss.hasPermission('park:app-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAppTypeExcel(@Valid AppTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AppTypeDO> list = appTypeService.getAppTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行业应用类别.xls", "数据", AppTypeRespVO.class,
                        BeanUtils.toBean(list, AppTypeRespVO.class));
    }

}
