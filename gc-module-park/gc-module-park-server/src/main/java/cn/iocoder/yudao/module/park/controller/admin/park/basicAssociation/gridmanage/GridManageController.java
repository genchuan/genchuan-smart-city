package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManageRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.gridmanage.GridManageDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.gridmanage.GridManageService;
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

@Tag(name = "管理后台 - 网格管理")
@RestController
@RequestMapping("/park/grid-manage")
@Validated
public class GridManageController {

    @Resource
    private GridManageService gridManageService;

    @PostMapping("/create")
    @Operation(summary = "创建网格管理")
    @PreAuthorize("@ss.hasPermission('park:grid-manage:create')")
    public CommonResult<Long> createGridManage(@Valid @RequestBody GridManageSaveReqVO createReqVO) {
        return success(gridManageService.createGridManage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格管理")
    @PreAuthorize("@ss.hasPermission('park:grid-manage:update')")
    public CommonResult<Boolean> updateGridManage(@Valid @RequestBody GridManageSaveReqVO updateReqVO) {
        gridManageService.updateGridManage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:grid-manage:delete')")
    public CommonResult<Boolean> deleteGridManage(@RequestParam("id") Long id) {
        gridManageService.deleteGridManage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:grid-manage:query')")
    public CommonResult<GridManageRespVO> getGridManage(@RequestParam("id") Long id) {
        GridManageDO gridManage = gridManageService.getGridManage(id);
        return success(BeanUtils.toBean(gridManage, GridManageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格管理分页")
    @PreAuthorize("@ss.hasPermission('park:grid-manage:query')")
    public CommonResult<PageResult<GridManageRespVO>> getGridManagePage(@Valid GridManagePageReqVO pageReqVO) {
        PageResult<GridManageDO> pageResult = gridManageService.getGridManagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridManageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格管理 Excel")
    @PreAuthorize("@ss.hasPermission('park:grid-manage:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridManageExcel(@Valid GridManagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridManageDO> list = gridManageService.getGridManagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格管理.xls", "数据", GridManageRespVO.class,
                        BeanUtils.toBean(list, GridManageRespVO.class));
    }

}
