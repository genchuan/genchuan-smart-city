package cn.iocoder.yudao.module.stationresource.api.station;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
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

@FeignClient(name = ApiConstants.NAME) // TODO fallbackFactory = StationInfoApiFallback.class(等统一降级方案落地)
@Tag(name = "RPC 服务 - 场站信息")
public interface StationInfoApi {

    String PREFIX = ApiConstants.PREFIX + "/station-info";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获取单个场站(含片区坐标)")
    @Parameter(name = "id", description = "场站 ID", required = true)
    CommonResult<StationInfoRespDTO> getStation(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获取全部场站列表(含片区坐标)")
    CommonResult<List<StationInfoRespDTO>> listStations();

    @GetMapping(PREFIX + "/list-by-ids")
    @Operation(summary = "按 ID 批量获取场站")
    @Parameter(name = "ids", description = "场站 ID 集合", required = true)
    CommonResult<List<StationInfoRespDTO>> getStationList(@RequestParam("ids") Collection<Long> ids);

    /** 批量拿 station map,便于列表 RespVO 拼接场站名 */
    default Map<Long, StationInfoRespDTO> getStationMap(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<StationInfoRespDTO> list = getStationList(ids).getCheckedData();
        return CollectionUtils.convertMap(list, StationInfoRespDTO::getId);
    }

}
