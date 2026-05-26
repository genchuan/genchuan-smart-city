package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo;

import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupinfo.GroupInfoDO;
import cn.iocoder.yudao.module.usermerchant.service.groupclient.groupinfo.GroupInfoService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 集团信息")
@RestController
@RequestMapping("/usermerchant/group-info")
@Validated
public class GroupInfoController {

    @Resource
    private GroupInfoService groupInfoService;

    @GetMapping("/page")
    @Operation(summary = "获得集团信息分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:query')")
    public CommonResult<PageResult<GroupInfoPageRespVO>> getGroupInfoPage(@Valid GroupInfoPageReqVO pageReqVO) {
        PageResult<GroupInfoDO> pageResult = groupInfoService.getGroupInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GroupInfoPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建集团信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:create')")
    public CommonResult<Boolean> createGroupInfo(@Valid @RequestBody GroupInfoCreateReqVO createReqVO) {
        return success(groupInfoService.createGroupInfo(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入集团信息")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<GroupInfoImportExcelVO> list = ExcelUtils.read(file, GroupInfoImportExcelVO.class);
        return success(groupInfoService.importGroups(list, updateSupport));
    }

    @GetMapping("/template")
    @Operation(summary = "下载集团信息导入模板")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:import')")
    public void downloadImportTemplate(HttpServletResponse response) throws IOException {
        List<GroupInfoImportExcelVO> emptyList = Collections.emptyList();
        ExcelUtils.write(response, "集团信息导入模板.xlsx", "集团信息", GroupInfoImportExcelVO.class, emptyList);
    }

    @GetMapping("/export")
    @Operation(summary = "导出集团信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGroupInfoExcel(@Valid GroupInfoPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GroupInfoDO> list = groupInfoService.getGroupInfoPage(pageReqVO).getList();
        // 导出 Exce
        ExcelUtils.write(response, "集团信息.xls", "数据", GroupInfoExportRespVO.class,
                BeanUtils.toBean(list, GroupInfoExportRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:approve')")
    public CommonResult<Boolean> approve(@Valid @RequestBody GroupInfoAuditReqVO reqVO) {
        reqVO.setStatus("正常");
        groupInfoService.batchUpdateGroupInfo(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:reject')")
    public CommonResult<Boolean> reject(@Valid @RequestBody GroupInfoAuditReqVO reqVO) {
        reqVO.setStatus("已驳回");
        groupInfoService.batchUpdateGroupInfo(reqVO);
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用集团")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:disable')")
    public CommonResult<Boolean> disableGroupInfo(@Valid @RequestBody GroupInfoAuditReqVO reqVO) {
        groupInfoService.updateGroupStatus(reqVO.getIds(), "禁用");
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用集团")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:enable')")
    public CommonResult<Boolean> enableGroupInfo(@Valid @RequestBody GroupInfoAuditReqVO reqVO) {
        groupInfoService.updateGroupStatus(reqVO.getIds(), "正常");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得集团信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:query')")
    public CommonResult<GroupInfoPageRespVO> getGroupInfo(@RequestParam("id") Long id) {
        GroupInfoDO groupInfo = groupInfoService.getGroupInfo(id);
        return success(BeanUtils.toBean(groupInfo, GroupInfoPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新集团信息")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:update')")
    public CommonResult<Boolean> updateGroupInfo(@Valid @RequestBody GroupInfoUpdateReqVO updateReqVO) {
        groupInfoService.updateGroupInfo(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "集团信息统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:query')")
    public CommonResult<GroupInfoChartRespVO> getGroupInfoChart(@Valid GroupInfoChartReqVO chartReqVO) {
        return success(groupInfoService.getGroupInfoChart(chartReqVO));
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除集团信息")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:group-info:delete')")
//    public CommonResult<Boolean> deleteGroupInfo(@RequestParam("id") Long id) {
//        groupInfoService.deleteGroupInfo(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除集团信息")
//                @PreAuthorize("@ss.hasPermission('usermerchant:group-info:delete')")
//    public CommonResult<Boolean> deleteGroupInfoList(@RequestParam("ids") List<Long> ids) {
//        groupInfoService.deleteGroupInfoListByIds(ids);
//        return success(true);
//    }

}