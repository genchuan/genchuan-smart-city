package cn.iocoder.yudao.module.kitchen.api.aialertmessage;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessagePageReqDTO;
import cn.iocoder.yudao.module.kitchen.api.aialertmessage.dto.AiAlertMessageRespDTO;
import cn.iocoder.yudao.module.kitchen.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME) // TODO fallbackFactory = AiAlertMessageApiFallback.class
@Tag(name = "RPC 服务 - AI告警消息")
public interface AiAlertMessageApi {

    String PREFIX = ApiConstants.PREFIX + "/ai-alert-message";

    // ==================== 查询接口 ====================
    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获得AI告警消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    CommonResult<AiAlertMessageRespDTO> getAiAlertMessage(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/page")
    @Operation(summary = "获得AI告警消息分页")
    CommonResult<PageResult<AiAlertMessageRespDTO>> getAiAlertMessagePage(
            @SpringQueryMap AiAlertMessagePageReqDTO pageReqDTO);


}
