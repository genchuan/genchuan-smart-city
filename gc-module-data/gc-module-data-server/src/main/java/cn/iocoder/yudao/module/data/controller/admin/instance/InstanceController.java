package cn.iocoder.yudao.module.data.controller.admin.instance;

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

import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import cn.iocoder.yudao.module.data.service.instance.InstanceService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 管理部件实例")
@RestController
@RequestMapping("/data/instance")
@Validated
public class InstanceController {

    @Resource
    private InstanceService instanceService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件实例")
    @PreAuthorize("@ss.hasPermission('data:instance:create')")
    public CommonResult<Long> createInstance(@Valid @RequestBody InstanceSaveReqVO createReqVO) {
        return success(instanceService.createInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件实例")
    @PreAuthorize("@ss.hasPermission('data:instance:update')")
    public CommonResult<Boolean> updateInstance(@Valid @RequestBody InstanceSaveReqVO updateReqVO) {
        instanceService.updateInstance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:instance:delete')")
    public CommonResult<Boolean> deleteInstance(@RequestParam("id") Long id) {
        instanceService.deleteInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:instance:query')")
    public CommonResult<InstanceRespVO> getInstance(@RequestParam("id") Long id) {
        InstanceDO instance = instanceService.getInstance(id);
        return success(BeanUtils.toBean(instance, InstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件实例分页")
    @PreAuthorize("@ss.hasPermission('data:instance:query')")
    public CommonResult<PageResult<InstanceRespVO>> getInstancePage(@Valid InstancePageReqVO pageReqVO) {
        PageResult<InstanceDO> pageResult = instanceService.getInstancePage(pageReqVO);
        List<InstanceRespVO> voList = BeanUtils.toBean(pageResult.getList(), InstanceRespVO.class);
        return success(new PageResult<>(voList, pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstanceExcel(@Valid InstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstanceDO> list = instanceService.getInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件实例.xls", "数据", InstanceRespVO.class,
                        BeanUtils.toBean(list, InstanceRespVO.class));
    }

    @GetMapping("/list-by-category")
    @Operation(summary = "根据分类ID获得管理部件实例列表")
    @Parameter(name = "categoryId", description = "分类ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('data:instance:query')")
    public CommonResult<List<InstanceRespVO>> getInstanceListByCategory(@RequestParam("categoryId") String categoryId) {
        List<InstanceDO> list = instanceService.getInstanceListByCategoryId(categoryId);
        return success(BeanUtils.toBean(list, InstanceRespVO.class));
    }

    @PostMapping("/import-excel")
    @Operation(summary = "导入管理部件实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:instance:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<String> importInstanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 调用服务层进行导入
        String importResult = instanceService.importInstanceExcel(file);
        return success(importResult);
    }

}