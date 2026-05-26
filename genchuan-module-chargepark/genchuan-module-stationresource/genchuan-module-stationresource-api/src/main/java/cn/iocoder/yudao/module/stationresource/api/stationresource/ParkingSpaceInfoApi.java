package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.ParkingSpaceInfoRespDTO;
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
 * 车位信息 RPC 接口（完整版）
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 车位信息（完整版）")
public interface ParkingSpaceInfoApi {

    String PREFIX = ApiConstants.PREFIX + "/parking-space";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "按 ID 获取单个车位")
    @Parameter(name = "id", description = "车位 ID", required = true)
    CommonResult<ParkingSpaceInfoRespDTO> getSpace(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list-by-ids")
    @Operation(summary = "按 ID 批量获取车位")
    @Parameter(name = "ids", description = "车位 ID 集合", required = true)
    CommonResult<List<ParkingSpaceInfoRespDTO>> listSpacesByIds(@RequestParam("ids") Collection<Long> ids);

    @GetMapping(PREFIX + "/list-by-station")
    @Operation(summary = "按场站 ID 获取该场站下所有车位")
    @Parameter(name = "stationId", description = "场站 ID", required = true)
    CommonResult<List<ParkingSpaceInfoRespDTO>> listSpacesByStationId(@RequestParam("stationId") Long stationId);

    /**
     * 批量获取车位 → Map，便于调用方按 ID 快速查找
     */
    default Map<Long, ParkingSpaceInfoRespDTO> getSpaceMap(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<ParkingSpaceInfoRespDTO> list = listSpacesByIds(ids).getCheckedData();
        return CollectionUtils.convertMap(list, ParkingSpaceInfoRespDTO::getId);
    }
}
