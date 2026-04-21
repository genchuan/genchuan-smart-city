package cn.iocoder.yudao.module.inspectop.api.space;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.inspectop.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ApiConstants.NAME) // TODO fallbackFactory = SpaceMonitorApiFallback.class(等统一降级方案落地)
@Tag(name = "RPC 服务 - 车位监测")
public interface SpaceMonitorApi {

    String PREFIX = ApiConstants.PREFIX + "/space-monitor";

    @GetMapping(PREFIX + "/get-latest-by-space")
    @Operation(summary = "按 spaceId 获取最新的监测记录(含坐标)")
    @Parameter(name = "spaceId", description = "车位 ID", required = true)
    CommonResult<SpaceMonitorRespDTO> getLatestBySpaceId(@RequestParam("spaceId") Long spaceId);

    @GetMapping(PREFIX + "/list-latest")
    @Operation(summary = "每个 spaceId 取最新一条监测记录(去重,用于地图点位)")
    CommonResult<List<SpaceMonitorRespDTO>> listLatestSpaceMonitors();

}
