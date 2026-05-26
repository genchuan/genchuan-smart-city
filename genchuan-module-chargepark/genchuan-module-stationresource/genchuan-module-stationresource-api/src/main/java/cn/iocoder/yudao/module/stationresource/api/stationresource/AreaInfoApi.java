package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.AreaInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 片区信息 RPC 接口（完整版）
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 片区信息（完整版）")
public interface AreaInfoApi {

    String PREFIX = ApiConstants.PREFIX + "/area";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "按 ID 获取单个片区")
    @Parameter(name = "id", description = "片区 ID", required = true)
    CommonResult<AreaInfoRespDTO> getArea(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获取全部片区列表")
    CommonResult<List<AreaInfoRespDTO>> listAreas();

    @GetMapping(PREFIX + "/list-by-ids")
    @Operation(summary = "按 ID 批量获取片区")
    @Parameter(name = "ids", description = "片区 ID 集合", required = true)
    CommonResult<List<AreaInfoRespDTO>> listAreasByIds(@RequestParam("ids") Collection<Long> ids);

    /**
     * 批量获取片区 → Map，便于调用方按 ID 快速查找
     */
    default Map<Long, AreaInfoRespDTO> getAreaMap(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<AreaInfoRespDTO> list = listAreasByIds(ids).getCheckedData();
        return CollectionUtils.convertMap(list, AreaInfoRespDTO::getId);
    }
}
