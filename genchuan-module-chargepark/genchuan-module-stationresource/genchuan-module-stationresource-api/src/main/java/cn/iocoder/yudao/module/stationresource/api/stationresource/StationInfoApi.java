package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.StationInfoRespDTO;
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
 * 场站信息 RPC 接口（完整版）
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 场站信息（完整版）")
public interface StationInfoApi {

    String PREFIX = ApiConstants.PREFIX + "/station";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "按 ID 获取单个场站（含片区坐标、空位数）")
    @Parameter(name = "id", description = "场站 ID", required = true)
    CommonResult<StationInfoRespDTO> getStation(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获取全部场站列表（含片区坐标、空位数）")
    CommonResult<List<StationInfoRespDTO>> listStations();

    @GetMapping(PREFIX + "/list-by-ids")
    @Operation(summary = "按 ID 批量获取场站")
    @Parameter(name = "ids", description = "场站 ID 集合", required = true)
    CommonResult<List<StationInfoRespDTO>> listStationsByIds(@RequestParam("ids") Collection<Long> ids);

    /**
     * 批量获取场站 → Map，便于调用方按 ID 快速查找
     */
    default Map<Long, StationInfoRespDTO> getStationMap(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<StationInfoRespDTO> list = listStationsByIds(ids).getCheckedData();
        return CollectionUtils.convertMap(list, StationInfoRespDTO::getId);
    }
}
