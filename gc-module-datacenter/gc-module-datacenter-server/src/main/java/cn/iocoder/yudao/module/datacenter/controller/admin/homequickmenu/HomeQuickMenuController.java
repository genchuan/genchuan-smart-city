package cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.homequickmenu.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.homequickmenu.HomeQuickMenuDO;
import cn.iocoder.yudao.module.datacenter.service.homequickmenu.HomeQuickMenuService;

@Tag(name = "管理后台 - 系统首页快捷菜单配置")
@RestController
@RequestMapping("/datacenter/home-quick-menu")
@Validated
public class HomeQuickMenuController {

    @Resource
    private HomeQuickMenuService homeQuickMenuService;

    @PostMapping("/create")
    @Operation(summary = "创建系统首页快捷菜单配置")
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:create')")
    public CommonResult<Long> createHomeQuickMenu(@Valid @RequestBody HomeQuickMenuSaveReqVO createReqVO) {
        return success(homeQuickMenuService.createHomeQuickMenu(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统首页快捷菜单配置")
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:update')")
    public CommonResult<Boolean> updateHomeQuickMenu(@Valid @RequestBody HomeQuickMenuSaveReqVO updateReqVO) {
        homeQuickMenuService.updateHomeQuickMenu(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除系统首页快捷菜单配置")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:delete')")
    public CommonResult<Boolean> deleteHomeQuickMenu(@RequestParam("id") Long id) {
        homeQuickMenuService.deleteHomeQuickMenu(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得系统首页快捷菜单配置")
    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:query')")
    public CommonResult<HomeQuickMenuRespVO> getHomeQuickMenu(@RequestParam("id") Long id) {
        HomeQuickMenuDO homeQuickMenu = homeQuickMenuService.getHomeQuickMenu(id);
        return success(BeanUtils.toBean(homeQuickMenu, HomeQuickMenuRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得系统首页快捷菜单配置分页")
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:query')")
    public CommonResult<PageResult<HomeQuickMenuRespVO>> getHomeQuickMenuPage(@Valid HomeQuickMenuPageReqVO pageReqVO) {
        PageResult<HomeQuickMenuDO> pageResult = homeQuickMenuService.getHomeQuickMenuPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HomeQuickMenuRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出系统首页快捷菜单配置 Excel")
//    @PreAuthorize("@ss.hasPermission('datacenter:home-quick-menu:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHomeQuickMenuExcel(@Valid HomeQuickMenuPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HomeQuickMenuDO> list = homeQuickMenuService.getHomeQuickMenuPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "系统首页快捷菜单配置.xls", "数据", HomeQuickMenuRespVO.class,
                        BeanUtils.toBean(list, HomeQuickMenuRespVO.class));
    }

}