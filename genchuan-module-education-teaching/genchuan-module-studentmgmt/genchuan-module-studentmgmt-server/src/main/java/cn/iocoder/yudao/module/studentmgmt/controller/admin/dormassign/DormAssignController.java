package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import cn.iocoder.yudao.module.studentmgmt.service.dormassign.DormAssignService;

@Tag(name = "学生管理后台 - 宿舍分配")
@RestController
@RequestMapping("/studentmgmt/dorm-assign")
@Validated
public class DormAssignController {

    @Resource
    private DormAssignService dormAssignService;
    @Resource
    private DictDataApi dictDataApi;
    @PostMapping("/create")
    @Operation(summary = "创建宿舍分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:create')")
    public CommonResult<Long> createDormAssign(@Valid @RequestBody DormAssignSaveReqVO createReqVO) {
        return success(dormAssignService.createDormAssign(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新宿舍分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:update')")
    public CommonResult<Boolean> updateDormAssign(@Valid @RequestBody DormAssignSaveReqVO updateReqVO) {
        dormAssignService.updateDormAssign(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除宿舍分配")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:delete')")
    public CommonResult<Boolean> deleteDormAssign(@RequestParam("id") Long id) {
        dormAssignService.deleteDormAssign(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除宿舍分配")
                @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:delete')")
    public CommonResult<Boolean> deleteDormAssignList(@RequestParam("ids") List<Long> ids) {
        dormAssignService.deleteDormAssignListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得宿舍分配")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<DormAssignRespVO> getDormAssign(@RequestParam("id") Long id) {
        DormAssignDO dormAssign = dormAssignService.getDormAssign(id);
        return success(BeanUtils.toBean(dormAssign, DormAssignRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得宿舍分配分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<PageResult<DormAssignRespVO>> getDormAssignPage(@Valid DormAssignPageReqVO pageReqVO) {
        PageResult<DormAssignDO> pageResult = dormAssignService.getDormAssignPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DormAssignRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出宿舍分配 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDormAssignExcel(@Valid DormAssignPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DormAssignDO> list = dormAssignService.getDormAssignPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.DORM_COMPARE_STATUS.getType());
        list = list.stream().map(item -> {
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
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "宿舍分配.xls", "数据", DormAssignRespVO.class,
                        BeanUtils.toBean(list, DormAssignRespVO.class));
    }
    @PutMapping("/assign")
    @Operation(summary = "分配")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody DormAssignAssignReqVO reqVO) {
        return success(dormAssignService.assign(reqVO));
    }
    @PutMapping("/adjust")
    @Operation(summary = "调整")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:adjust')")
    public CommonResult<Boolean> adjust(@Valid @RequestBody DormAssignAdjustReqVO reqVO) {
        return success(dormAssignService.adjust(reqVO));
    }
    @GetMapping("/chart")
    @Operation(summary = "新生宿舍分配看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<DormAssignChartRespVO> chart(@Valid DormAssignChartReqVO reqVO) {
        return success(dormAssignService.chart(reqVO));
    }
    @GetMapping("/chart/assignIndex")
    @Operation(summary = "分配核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-assign:query')")
    public CommonResult<DormAssignIndexRespVO> assignIndex(@Valid DormAssignChartReqVO reqVO) {
        return success(dormAssignService.assignIndex(reqVO));
    }

}