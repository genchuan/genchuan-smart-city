package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.targetmgmt.TargetMgmtService;
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

@Tag(name = "学生管理后台 - 指标管理")
@RestController
@RequestMapping("/studentmgmt/target-mgmt")
@Validated
public class TargetMgmtController {

    @Resource
    private TargetMgmtService targetMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建指标管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:create')")
    public CommonResult<Long> createTargetMgmt(@Valid @RequestBody TargetMgmtSaveReqVO createReqVO) {
        return success(targetMgmtService.createTargetMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:update')")
    public CommonResult<Boolean> updateTargetMgmt(@Valid @RequestBody TargetMgmtSaveReqVO updateReqVO) {
        targetMgmtService.updateTargetMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:delete')")
    public CommonResult<Boolean> deleteTargetMgmt(@RequestParam("id") Long id) {
        targetMgmtService.deleteTargetMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除指标管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:delete')")
    public CommonResult<Boolean> deleteTargetMgmtList(@RequestParam("ids") List<Long> ids) {
        targetMgmtService.deleteTargetMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtRespVO> getTargetMgmt(@RequestParam("id") Long id) {
        TargetMgmtDO targetMgmt = targetMgmtService.getTargetMgmt(id);
        return success(BeanUtils.toBean(targetMgmt, TargetMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<PageResult<TargetMgmtRespVO>> getTargetMgmtPage(@Valid TargetMgmtPageReqVO pageReqVO) {
        PageResult<TargetMgmtDO> pageResult = targetMgmtService.getTargetMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TargetMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTargetMgmtExcel(@Valid TargetMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TargetMgmtDO> list = targetMgmtService.getTargetMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.TARGET_MGMT_SCORE_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.TARGET_MGMT_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> evaluatorTypeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.TARGET_MGMT_EVALUATOR_TYPE.getType());
        list = list.stream().map(item -> {
            String scoreType = item.getScoreType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(scoreType)) {
                        scoreType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setScoreType(scoreType);
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
            String evaluatorType = item.getEvaluatorType();
            if (evaluatorTypeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : evaluatorTypeDictDataList.getData()) {
                    if (dictData.getValue().equals(evaluatorType)) {
                        evaluatorType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setEvaluatorType(evaluatorType);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "指标管理.xls", "数据", TargetMgmtRespVO.class,
                        BeanUtils.toBean(list, TargetMgmtRespVO.class));
    }

    @PutMapping("/config")
    @Operation(summary = "配置")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:config')")
    public CommonResult<Boolean> config(@Valid @RequestBody TargetMgmtConfigReqVO reqVO) {
        boolean isSuccess = targetMgmtService.config(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/enable")
    @Operation(summary = "启用")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:enable')")
    public CommonResult<Boolean> enable(@Valid @RequestBody TargetMgmtEnableReqVO reqVO) {
        boolean isSuccess = targetMgmtService.enable(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/disable")
    @Operation(summary = "停用")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:disable')")
    public CommonResult<Boolean> disable(@Valid @RequestBody TargetMgmtEnableReqVO reqVO) {
        boolean isSuccess = targetMgmtService.disable(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "德育指标配置看板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtChartRespVO> chart(@Valid TargetMgmtChartReqVO reqVO) {
        TargetMgmtChartRespVO vo = targetMgmtService.chart(reqVO);
        return success(vo);
    }
    @GetMapping("/chart/targetIndex")
    @Operation(summary = "指标核心指标统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtChartIndexRespVO> targetIndex() {
        TargetMgmtChartIndexRespVO vo = targetMgmtService.targetIndex();
        return success(vo);
    }
}