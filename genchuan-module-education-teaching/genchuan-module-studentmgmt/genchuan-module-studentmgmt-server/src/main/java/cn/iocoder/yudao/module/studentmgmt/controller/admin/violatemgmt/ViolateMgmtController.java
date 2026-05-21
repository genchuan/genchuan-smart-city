package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.violatemgmt.ViolateMgmtService;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "学生管理后台 - 违纪管理")
@RestController
@RequestMapping("/studentmgmt/violate-mgmt")
@Validated
public class ViolateMgmtController {

    @Resource
    private ViolateMgmtService violateMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:create')")
//    @OperateLog(type = CREATE)
    public CommonResult<Long> createViolateMgmt(@Valid @RequestBody ViolateMgmtSaveReqVO createReqVO) {
        return success(violateMgmtService.createViolateMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:update')")
    public CommonResult<Boolean> updateViolateMgmt(@Valid @RequestBody ViolateMgmtSaveReqVO updateReqVO) {
        violateMgmtService.updateViolateMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违纪管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:delete')")
    public CommonResult<Boolean> deleteViolateMgmt(@RequestParam("id") Long id) {
        violateMgmtService.deleteViolateMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除违纪管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:delete')")
    public CommonResult<Boolean> deleteViolateMgmtList(@RequestParam("ids") List<Long> ids) {
        violateMgmtService.deleteViolateMgmtListByIds(ids);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:query')")
    public CommonResult<ViolateMgmtRespVO> getViolateMgmt(@RequestParam("id") Long id) {

        ViolateMgmtDO violateMgmt = violateMgmtService.getViolateMgmt(id);
        return success(BeanUtils.toBean(violateMgmt, ViolateMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违纪管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:query')")
    public CommonResult<PageResult<ViolateMgmtPageRespVO>> getViolateMgmtPage(@Valid ViolateMgmtPageReqVO pageReqVO) {
//        PageResult<ViolateMgmtPageRespVO> pageResult = violateMgmtService.getViolateMgmtPageVo(pageReqVO);
        return success(violateMgmtService.getViolateMgmtJoinPageVo(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出违纪管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportViolateMgmtExcel(@Valid ViolateMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ViolateMgmtDO> list = violateMgmtService.getViolateMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.VIOLATE_MGMT_VIOLATE_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.VIOLATE_MGMT_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> punishDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.VIOLATE_MGMT_PUNISH_TYPE.getType());
        list = list.stream().map(item -> {
            String violateType = item.getViolateType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(violateType)) {
                        violateType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setViolateType(violateType);
            String status = item.getStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStatus(status);
            String punishType = item.getPunishType();
            if (punishDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : punishDictDataList.getData()) {
                    if (dictData.getValue().equals(punishType)) {
                        punishType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setPunishType(punishType);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "违纪管理.xls", "数据", ViolateMgmtRespVO.class,
                        BeanUtils.toBean(list, ViolateMgmtRespVO.class));
    }


    @PutMapping("/audit")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "审批违纪管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:audit')")
    public CommonResult<Boolean> audit(@RequestParam("ids") List<Long> ids) {
        // 获取当前用户
        Long userId = getLoginUserId();
        boolean isSuccess = violateMgmtService.auditViolateMgmtListByIds(ids, userId);
        return success(isSuccess);
    }

    @PutMapping("/push")
    @Operation(summary = "推送违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:push')")
    public CommonResult<Boolean> push(@RequestParam("id") Long id) {
        // 获取当前用户
        Long userId = getLoginUserId();
        Boolean isSuccess = violateMgmtService.push(id, userId);
        return success(isSuccess);
    }


    @PutMapping("/warn")
    @Operation(summary = "预警违纪管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-mgmt:warn')")
    public CommonResult<Boolean> warn(@RequestParam("id") Long id) {
        // 获取当前用户
        Long userId = getLoginUserId();
        Boolean isSuccess = violateMgmtService.warn(id, userId);
        return success(isSuccess);
    }


    @GetMapping("/chart")
    @Operation(summary = "学生违纪预警看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<ViolateDashboardVO> chart(@Valid ViolateChartReqVO reqVO) {
        ViolateDashboardVO dashboardVO = violateMgmtService.chart(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/violateCount")
    @Operation(summary = "各班级违纪次数 / 类型分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<ViolateCountDashboardVO> violateCount(@Valid ViolateChartReqVO reqVO) {
        ViolateCountDashboardVO dashboardVO = violateMgmtService.violateCount(reqVO);
        return success(dashboardVO);
    }

    @GetMapping("/chart/warnIndex")
    @Operation(summary = "预警核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:violate-info:query')")
    public CommonResult<List<ViolateWarnIndexRespVO>> warnIndex(@Valid ViolateWarnIndexReqVO reqVO) {
        List<ViolateWarnIndexRespVO> list = violateMgmtService.warnIndex(reqVO);
        return success(list);
    }


}