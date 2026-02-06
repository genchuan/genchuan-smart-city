package cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManageRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage.RoadsideBerthManageDO;
import cn.iocoder.yudao.module.park.service.park.resource.roadsideberthmanage.RoadsideBerthManageService;
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

@Tag(name = "管理后台 - 路测泊位管理")
@RestController
@RequestMapping("/park/roadside-berth-manage")
@Validated
public class RoadsideBerthManageController {

    @Resource
    private RoadsideBerthManageService roadsideBerthManageService;

    @PostMapping("/create")
    @Operation(summary = "创建路测泊位管理")
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:create')")
    public CommonResult<Long> createRoadsideBerthManage(@Valid @RequestBody RoadsideBerthManageSaveReqVO createReqVO) {
        return success(roadsideBerthManageService.createRoadsideBerthManage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新路测泊位管理")
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:update')")
    public CommonResult<Boolean> updateRoadsideBerthManage(@Valid @RequestBody RoadsideBerthManageSaveReqVO updateReqVO) {
        roadsideBerthManageService.updateRoadsideBerthManage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路测泊位管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:delete')")
    public CommonResult<Boolean> deleteRoadsideBerthManage(@RequestParam("id") Long id) {
        roadsideBerthManageService.deleteRoadsideBerthManage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得路测泊位管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:query')")
    public CommonResult<RoadsideBerthManageRespVO> getRoadsideBerthManage(@RequestParam("id") Long id) {
        RoadsideBerthManageDO roadsideBerthManage = roadsideBerthManageService.getRoadsideBerthManage(id);
        return success(BeanUtils.toBean(roadsideBerthManage, RoadsideBerthManageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得路测泊位管理分页")
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:query')")
    public CommonResult<PageResult<RoadsideBerthManageRespVO>> getRoadsideBerthManagePage(@Valid RoadsideBerthManagePageReqVO pageReqVO) {
        PageResult<RoadsideBerthManageDO> pageResult = roadsideBerthManageService.getRoadsideBerthManagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadsideBerthManageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出路测泊位管理 Excel")
    @PreAuthorize("@ss.hasPermission('park:roadside-berth-manage:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRoadsideBerthManageExcel(@Valid RoadsideBerthManagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadsideBerthManageDO> list = roadsideBerthManageService.getRoadsideBerthManagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "路测泊位管理.xls", "数据", RoadsideBerthManageRespVO.class,
                        BeanUtils.toBean(list, RoadsideBerthManageRespVO.class));
    }

}
