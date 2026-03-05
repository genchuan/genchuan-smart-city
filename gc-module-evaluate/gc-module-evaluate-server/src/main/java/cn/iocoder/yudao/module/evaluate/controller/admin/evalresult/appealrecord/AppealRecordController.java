package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealrecord.AppealRecordDO;
import cn.iocoder.yudao.module.evaluate.service.appealrecord.AppealRecordService;
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

@Tag(name = "管理后台 - 申诉复核")
@RestController
@RequestMapping("/evaluate/appeal-record")
@Validated
public class AppealRecordController {

    @Resource
    private AppealRecordService appealRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建申诉复核")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:create')")
    public CommonResult<Long> createAppealRecord(@Valid @RequestBody AppealRecordSaveReqVO createReqVO) {
        return success(appealRecordService.createAppealRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新申诉复核")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:update')")
    public CommonResult<Boolean> updateAppealRecord(@Valid @RequestBody AppealRecordSaveReqVO updateReqVO) {
        appealRecordService.updateAppealRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除申诉复核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:delete')")
    public CommonResult<Boolean> deleteAppealRecord(@RequestParam("id") Long id) {
        appealRecordService.deleteAppealRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得申诉复核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:query')")
    public CommonResult<AppealRecordRespVO> getAppealRecord(@RequestParam("id") Long id) {
        AppealRecordDO appealRecord = appealRecordService.getAppealRecord(id);
        return success(BeanUtils.toBean(appealRecord, AppealRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得申诉复核分页")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:query')")
    public CommonResult<PageResult<AppealRecordRespVO>> getAppealRecordPage(@Valid AppealRecordPageReqVO pageReqVO) {
        PageResult<AppealRecordDO> pageResult = appealRecordService.getAppealRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppealRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出申诉复核 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:appeal-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAppealRecordExcel(@Valid AppealRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AppealRecordDO> list = appealRecordService.getAppealRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "申诉复核.xls", "数据", AppealRecordRespVO.class,
                        BeanUtils.toBean(list, AppealRecordRespVO.class));
    }

}