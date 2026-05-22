package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.repairmgmt.RepairMgmtService;
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

@Tag(name = "学生管理后台 - 报修管理")
@RestController
@RequestMapping("/studentmgmt/repair-mgmt")
@Validated
public class RepairMgmtController {

    @Resource
    private RepairMgmtService repairMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:create')")
    public CommonResult<Long> createRepairMgmt(@Valid @RequestBody RepairMgmtSaveReqVO createReqVO) {
        return success(repairMgmtService.createRepairMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:update')")
    public CommonResult<Boolean> updateRepairMgmt(@Valid @RequestBody RepairMgmtSaveReqVO updateReqVO) {
        repairMgmtService.updateRepairMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报修管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:delete')")
    public CommonResult<Boolean> deleteRepairMgmt(@RequestParam("id") Long id) {
        repairMgmtService.deleteRepairMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除报修管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:delete')")
    public CommonResult<Boolean> deleteRepairMgmtList(@RequestParam("ids") List<Long> ids) {
        repairMgmtService.deleteRepairMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报修管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtRespVO> getRepairMgmt(@RequestParam("id") Long id) {
        RepairMgmtDO repairMgmt = repairMgmtService.getRepairMgmt(id);
        return success(BeanUtils.toBean(repairMgmt, RepairMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报修管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<PageResult<RepairMgmtRespVO>> getRepairMgmtPage(@Valid RepairMgmtPageReqVO pageReqVO) {
        PageResult<RepairMgmtDO> pageResult = repairMgmtService.getRepairMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RepairMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报修管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRepairMgmtExcel(@Valid RepairMgmtPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RepairMgmtDO> list = repairMgmtService.getRepairMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.REPAIR_MGMT_REPAIR_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.REPAIR_MGMT_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> checkStatusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.REPAIR_MGMT_CHECK_STATUS.getType());

        list = list.stream().map(item -> {
            String applyType = item.getRepairType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(applyType)) {
                        applyType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setRepairType(applyType);
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
            String checkStatus = item.getCheckStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setCheckStatus(checkStatus);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "报修管理.xls", "数据", RepairMgmtRespVO.class,
                BeanUtils.toBean(list, RepairMgmtRespVO.class));
    }

    @PutMapping("/assign")
    @Operation(summary = "派单")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody RepairMgmtAssignReqVO reqVO) {
        Boolean isSuccess = repairMgmtService.assign(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/feedback")
    @Operation(summary = "反馈")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:feedback')")
    public CommonResult<Boolean> feedback(@Valid @RequestBody RepairMgmtFeedbackReqVO reqVO) {
        Boolean isSuccess = repairMgmtService.feedback(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/accept")
    @Operation(summary = "验收")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:feedback')")
    public CommonResult<Boolean> accept(@Valid @RequestBody RepairMgmtAcceptReqVO reqVO) {
        Boolean isSuccess = repairMgmtService.accept(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "宿舍报修处置看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtChartRespVO> chart(@Valid RepairMgmtChartReqVO reqVO) {
        RepairMgmtChartRespVO vo = repairMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/repairCount")
    @Operation(summary = "报修类型 / 维修完成率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:repair-mgmt:query')")
    public CommonResult<RepairMgmtCountRespVO> repairCount(@Valid RepairMgmtCountReqVO reqVO) {
        RepairMgmtCountRespVO vo = repairMgmtService.repairCount(reqVO);
        return success(vo);
    }


}