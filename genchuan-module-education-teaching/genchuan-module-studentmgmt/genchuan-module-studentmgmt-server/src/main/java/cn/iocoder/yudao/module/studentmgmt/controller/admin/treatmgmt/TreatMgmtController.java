package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt.TreatMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.treatmgmt.TreatMgmtService;
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

@Tag(name = "学生管理后台 - 就诊管理")
@RestController
@RequestMapping("/studentmgmt/treat-mgmt")
@Validated
public class TreatMgmtController {

    @Resource
    private TreatMgmtService treatMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建就诊管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:create')")
    public CommonResult<Long> createTreatMgmt(@Valid @RequestBody TreatMgmtSaveReqVO createReqVO) {
        return success(treatMgmtService.createTreatMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新就诊管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:update')")
    public CommonResult<Boolean> updateTreatMgmt(@Valid @RequestBody TreatMgmtSaveReqVO updateReqVO) {
        treatMgmtService.updateTreatMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除就诊管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:delete')")
    public CommonResult<Boolean> deleteTreatMgmt(@RequestParam("id") Long id) {
        treatMgmtService.deleteTreatMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除就诊管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:delete')")
    public CommonResult<Boolean> deleteTreatMgmtList(@RequestParam("ids") List<Long> ids) {
        treatMgmtService.deleteTreatMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得就诊管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:query')")
    public CommonResult<TreatMgmtRespVO> getTreatMgmt(@RequestParam("id") Long id) {
        TreatMgmtDO treatMgmt = treatMgmtService.getTreatMgmt(id);
        return success(BeanUtils.toBean(treatMgmt, TreatMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得就诊管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:query')")
    public CommonResult<PageResult<TreatMgmtRespVO>> getTreatMgmtPage(@Valid TreatMgmtPageReqVO pageReqVO) {
        PageResult<TreatMgmtDO> pageResult = treatMgmtService.getTreatMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TreatMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出就诊管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTreatMgmtExcel(@Valid TreatMgmtPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TreatMgmtDO> list = treatMgmtService.getTreatMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.TREAT_MGMT_TREAT_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.TREAT_MGMT_STATUS.getType());
        list = list.stream().map(item -> {
            String treatType = item.getTreatType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(treatType)) {
                        treatType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setTreatType(treatType);
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
        ExcelUtils.write(response, "就诊管理.xls", "数据", TreatMgmtRespVO.class, BeanUtils.toBean(list, TreatMgmtRespVO.class));
    }

    @PostMapping("/appoint")
    @Operation(summary = "就诊管理预约")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:appoint')")
    public CommonResult<Boolean> appoint(@Valid @RequestBody TreatMgmtAppointReqVO reqVO) {
        Boolean isSuccess = treatMgmtService.appoint(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/audit")
    @Operation(summary = "就诊管理审核")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody TreatMgmtAuditReqVO reqVO) {
        Boolean isSuccess = treatMgmtService.audit(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/register")
    @Operation(summary = "就诊管理登记")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:register')")
    public CommonResult<Boolean> register(@Valid @RequestBody TreatMgmtRegisterReqVO reqVO) {
        Boolean isSuccess = treatMgmtService.register(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/feedback")
    @Operation(summary = "就诊管理反馈")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:feedback')")
    public CommonResult<Boolean> feedback(@Valid @RequestBody TreatMgmtFeedbackReqVO reqVO) {
        Boolean isSuccess = treatMgmtService.feedback(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "学生就诊健康看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:chart')")
    public CommonResult<TreatMgmtChartRespVO> chart(@Valid TreatMgmtChartReqVO reqVO) {
        TreatMgmtChartRespVO vo = treatMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/treatDistribution")
    @Operation(summary = "就诊类型 / 年级分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:treat-mgmt:chart')")
    public CommonResult<TreatMgmtDistributionRespVO> treatDistribution(@Valid TreatMgmtChartReqVO reqVO) {
        TreatMgmtDistributionRespVO vo = treatMgmtService.treatDistribution(reqVO);
        return success(vo);
    }

}