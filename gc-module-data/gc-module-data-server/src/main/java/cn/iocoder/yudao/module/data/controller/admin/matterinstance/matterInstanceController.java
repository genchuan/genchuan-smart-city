package cn.iocoder.yudao.module.data.controller.admin.matterinstance;

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

import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.matterInstanceDO;
import cn.iocoder.yudao.module.data.service.matterinstance.matterInstanceService;

@Tag(name = "管理后台 - 管理事项实例")
@RestController
@RequestMapping("/data/matter-instance")
@Validated
public class matterInstanceController {

    @Resource
    private matterInstanceService matterInstanceService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项实例")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:create')")
    public CommonResult<Long> creatematterInstance(@Valid @RequestBody matterInstanceSaveReqVO createReqVO) {
        return success(matterInstanceService.creatematterInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项实例")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:update')")
    public CommonResult<Boolean> updatematterInstance(@Valid @RequestBody matterInstanceSaveReqVO updateReqVO) {
        matterInstanceService.updatematterInstance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:matter-instance:delete')")
    public CommonResult<Boolean> deletematterInstance(@RequestParam("id") Long id) {
        matterInstanceService.deletematterInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:query')")
    public CommonResult<matterInstanceRespVO> getmatterInstance(@RequestParam("id") Long id) {
        matterInstanceDO matterInstance = matterInstanceService.getmatterInstance(id);
        return success(BeanUtils.toBean(matterInstance, matterInstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项实例分页")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:query')")
    public CommonResult<PageResult<matterInstanceRespVO>> getmatterInstancePage(@Valid matterInstancePageReqVO pageReqVO) {
        PageResult<matterInstanceDO> pageResult = matterInstanceService.getmatterInstancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, matterInstanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportmatterInstanceExcel(@Valid matterInstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<matterInstanceDO> list = matterInstanceService.getmatterInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项实例.xls", "数据", matterInstanceRespVO.class,
                        BeanUtils.toBean(list, matterInstanceRespVO.class));
    }

}