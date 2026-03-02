package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.pushrecord.PushRecordDO;
import cn.iocoder.yudao.module.evaluate.service.pushrecord.PushRecordService;
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

@Tag(name = "管理后台 - 结果推送记录")
@RestController
@RequestMapping("/evaluate/push-record")
@Validated
public class PushRecordController {

    @Resource
    private PushRecordService pushRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建结果推送记录")
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:create')")
    public CommonResult<Long> createPushRecord(@Valid @RequestBody PushRecordSaveReqVO createReqVO) {
        return success(pushRecordService.createPushRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果推送记录")
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:update')")
    public CommonResult<Boolean> updatePushRecord(@Valid @RequestBody PushRecordSaveReqVO updateReqVO) {
        pushRecordService.updatePushRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果推送记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:delete')")
    public CommonResult<Boolean> deletePushRecord(@RequestParam("id") Long id) {
        pushRecordService.deletePushRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果推送记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:query')")
    public CommonResult<PushRecordRespVO> getPushRecord(@RequestParam("id") Long id) {
        PushRecordDO pushRecord = pushRecordService.getPushRecord(id);
        return success(BeanUtils.toBean(pushRecord, PushRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果推送记录分页")
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:query')")
    public CommonResult<PageResult<PushRecordRespVO>> getPushRecordPage(@Valid PushRecordPageReqVO pageReqVO) {
        PageResult<PushRecordDO> pageResult = pushRecordService.getPushRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PushRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果推送记录 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:push-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPushRecordExcel(@Valid PushRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PushRecordDO> list = pushRecordService.getPushRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果推送记录.xls", "数据", PushRecordRespVO.class,
                        BeanUtils.toBean(list, PushRecordRespVO.class));
    }

}