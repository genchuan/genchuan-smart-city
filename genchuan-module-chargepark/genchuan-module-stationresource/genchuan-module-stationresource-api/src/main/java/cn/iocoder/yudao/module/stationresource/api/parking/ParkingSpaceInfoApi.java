package cn.iocoder.yudao.module.stationresource.api.parking;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.stationresource.api.parking.dto.ParkingSpaceInfoRespDTO;
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

@FeignClient(name = ApiConstants.NAME) // TODO fallbackFactory = ParkingSpaceInfoApiFallback.class
@Tag(name = "RPC 服务 - 车位信息")
public interface ParkingSpaceInfoApi {

    String PREFIX = ApiConstants.PREFIX + "/parking-space-info";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "按 ID 获取单个车位")
    @Parameter(name = "id", description = "车位 ID", required = true)
    CommonResult<ParkingSpaceInfoRespDTO> getSpace(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list-by-ids")
    @Operation(summary = "按 ID 批量获取车位")
    @Parameter(name = "ids", description = "车位 ID 集合", required = true)
    CommonResult<List<ParkingSpaceInfoRespDTO>> getSpaceList(@RequestParam("ids") Collection<Long> ids);

    /** 批量拿 space map,便于批量拼接 spaceNo */
    default Map<Long, ParkingSpaceInfoRespDTO> getSpaceMap(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<ParkingSpaceInfoRespDTO> list = getSpaceList(ids).getCheckedData();
        return CollectionUtils.convertMap(list, ParkingSpaceInfoRespDTO::getId);
    }

}
