package cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.newpush.NewPushDO;
import cn.iocoder.yudao.module.studentmgmt.service.newpush.NewPushService;

@Tag(name = "管理后台 - 迎新推送")
@RestController
@RequestMapping("/studentmgmt/new-push")
@Validated
public class NewPushController {

    @Resource
    private NewPushService newPushService;

    @PostMapping("/create")
    @Operation(summary = "创建迎新推送")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:create')")
    public CommonResult<Long> createNewPush(@Valid @RequestBody NewPushSaveReqVO createReqVO) {
        return success(newPushService.createNewPush(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新迎新推送")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:update')")
    public CommonResult<Boolean> updateNewPush(@Valid @RequestBody NewPushUpdateReqVO updateReqVO) {

        return success(newPushService.updateNewPush(updateReqVO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除迎新推送")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:delete')")
    public CommonResult<Boolean> deleteNewPush(@RequestParam("id") Long id) {
        newPushService.deleteNewPush(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除迎新推送")
                @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:delete')")
    public CommonResult<Boolean> deleteNewPushList(@RequestParam("ids") List<Long> ids) {
        newPushService.deleteNewPushListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得迎新推送")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:query')")
    public CommonResult<NewPushRespVO> getNewPush(@RequestParam("id") Long id) {
        NewPushDO newPush = newPushService.getNewPush(id);
        return success(BeanUtils.toBean(newPush, NewPushRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得迎新推送分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:query')")
    public CommonResult<PageResult<NewPushRespVO>> getNewPushPage(@Valid NewPushPageReqVO pageReqVO) {
        PageResult<NewPushDO> pageResult = newPushService.getNewPushPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NewPushRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出迎新推送 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportNewPushExcel(@Valid NewPushPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<NewPushDO> list = newPushService.getNewPushPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "迎新推送.xls", "数据", NewPushRespVO.class,
                        BeanUtils.toBean(list, NewPushRespVO.class));
    }

    @PostMapping("/config")
    @Operation(summary = "配置")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:config')")
    public CommonResult<Boolean> config(@Valid @RequestBody NewPushConfigReqVO reqVO) {
        return success(newPushService.config(reqVO));
    }

    @PutMapping("/push")
    @Operation(summary = "推送")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:push')")
    public CommonResult<Boolean> push(@Valid @RequestBody NewPushPushReqVO reqVO) {
        return success(newPushService.push(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "迎新推送统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:chart')")
    public CommonResult<NewPushChartRespVO> chart(@Valid NewPushChartReqVO reqVO) {
        return success(newPushService.chart(reqVO));
    }

    @GetMapping("/pushIndex")
    @Operation(summary = "推送核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:new-push:query')")
    public CommonResult<NewPushIndexRespVO> pushIndex(@Valid NewPushChartReqVO reqVO) {
        return success(newPushService.pushIndex(reqVO));
    }

}