package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult;

import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult.vo.CheckResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult.vo.CheckResultRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult.vo.CheckResultSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.CheckResultDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.checkresult.CheckResultService;

@Tag(name = "字典表 - 核查结果")
@RestController
@RequestMapping("/envirhealth/check-result")
@Validated
public class CheckResultController {

    @Resource
    private CheckResultService checkResultService;

    @PostMapping("/create")
    @Operation(summary = "创建核查结果字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:create')")
    public CommonResult<Long> createCheckResult(@Valid @RequestBody CheckResultSaveReqVO createReqVO) {
        return success(checkResultService.createCheckResult(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新核查结果字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:update')")
    public CommonResult<Boolean> updateCheckResult(@Valid @RequestBody CheckResultSaveReqVO updateReqVO) {
        checkResultService.updateCheckResult(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除核查结果字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:delete')")
    public CommonResult<Boolean> deleteCheckResult(@RequestParam("id") Long id) {
        checkResultService.deleteCheckResult(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得核查结果字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:query')")
    public CommonResult<CheckResultRespVO> getCheckResult(@RequestParam("id") Long id) {
        CheckResultDO checkResult = checkResultService.getCheckResult(id);
        return success(BeanUtils.toBean(checkResult, CheckResultRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得核查结果字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:query')")
    public CommonResult<PageResult<CheckResultRespVO>> getCheckResultPage(@Valid CheckResultPageReqVO pageReqVO) {
        PageResult<CheckResultDO> pageResult = checkResultService.getCheckResultPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CheckResultRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出核查结果字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCheckResultExcel(@Valid CheckResultPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CheckResultDO> list = checkResultService.getCheckResultPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "核查结果字典表.xls", "数据", CheckResultRespVO.class,
                        BeanUtils.toBean(list, CheckResultRespVO.class));
    }

    /**
     * 获得核查结果字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得核查结果(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:check-result:query')")
    public CommonResult<List<OptionVO>> getCheckResultOptions() {
        return success(checkResultService.getCheckResultOptions());
    }

}
