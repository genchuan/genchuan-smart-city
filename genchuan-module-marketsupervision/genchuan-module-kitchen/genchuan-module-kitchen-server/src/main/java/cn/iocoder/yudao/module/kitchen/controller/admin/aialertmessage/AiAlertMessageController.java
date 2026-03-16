package cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.service.aialertmessage.AiAlertMessageService;
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


@Tag(name = "管理后台 - AI告警消息")
@RestController
@RequestMapping("/kitchen/ai-alert-message")
@Validated
public class AiAlertMessageController {

    @Resource
    private AiAlertMessageService aiAlertMessageService;

    @PostMapping("/create")
    @Operation(summary = "创建AI告警消息")
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:create')")
    public CommonResult<Long> createAiAlertMessage(@Valid @RequestBody AiAlertMessageSaveReqVO createReqVO) {
        return success(aiAlertMessageService.createAiAlertMessage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新AI告警消息")
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:update')")
    public CommonResult<Boolean> updateAiAlertMessage(@Valid @RequestBody AiAlertMessageSaveReqVO updateReqVO) {
        aiAlertMessageService.updateAiAlertMessage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除AI告警消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:delete')")
    public CommonResult<Boolean> deleteAiAlertMessage(@RequestParam("id") Long id) {
        aiAlertMessageService.deleteAiAlertMessage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得AI告警消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:query')")
    public CommonResult<AiAlertMessageRespVO> getAiAlertMessage(@RequestParam("id") Long id) {
        AiAlertMessageDO aiAlertMessage = aiAlertMessageService.getAiAlertMessage(id);
        return success(BeanUtils.toBean(aiAlertMessage, AiAlertMessageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得AI告警消息分页")
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:query')")
    public CommonResult<PageResult<AiAlertMessageRespVO>> getAiAlertMessagePage(@Valid AiAlertMessagePageReqVO pageReqVO) {
        PageResult<AiAlertMessageDO> pageResult = aiAlertMessageService.getAiAlertMessagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiAlertMessageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出AI告警消息 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:ai-alert-message:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAiAlertMessageExcel(@Valid AiAlertMessagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AiAlertMessageDO> list = aiAlertMessageService.getAiAlertMessagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "AI告警消息.xls", "数据", AiAlertMessageRespVO.class,
                        BeanUtils.toBean(list, AiAlertMessageRespVO.class));
    }

}
