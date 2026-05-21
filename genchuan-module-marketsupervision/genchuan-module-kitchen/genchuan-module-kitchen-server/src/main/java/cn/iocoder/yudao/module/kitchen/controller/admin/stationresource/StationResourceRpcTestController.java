package cn.iocoder.yudao.module.kitchen.controller.admin.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.stationresource.AreaInfoApi;
import cn.iocoder.yudao.module.stationresource.api.stationresource.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.stationresource.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.AreaInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.StationInfoRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 场站资源 RPC 测试验证接口
 *
 * <p>用于明厨模块验证场站资源模块的 RPC 调用是否正常，不混入业务 Controller
 */
@Tag(name = "管理后台 - 场站资源RPC验证")
@RestController
@RequestMapping("/kitchen/station-resource-rpc")
@Validated
public class StationResourceRpcTestController {

    @Resource
    private StationInfoApi stationInfoApi;

    @Resource
    private ParkingSpaceInfoApi parkingSpaceInfoApi;

    @Resource
    private AreaInfoApi areaInfoApi;

    // ==================== StationInfoApi ====================

    @GetMapping("/station-get")
    @Operation(summary = "按ID获取单个场站")
    @Parameter(name = "id", description = "场站ID", required = true, example = "1")
    public CommonResult<StationInfoRespDTO> stationGet(@RequestParam("id") Long id) {
        return success(stationInfoApi.getStation(id).getCheckedData());
    }

    @GetMapping("/station-list-all")
    @Operation(summary = "获取全部场站列表")
    public CommonResult<List<StationInfoRespDTO>> stationListAll() {
        return success(stationInfoApi.listStations().getCheckedData());
    }

    @GetMapping("/station-list-by-ids")
    @Operation(summary = "按ID批量获取场站")
    @Parameter(name = "ids", description = "场站ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<List<StationInfoRespDTO>> stationListByIds(@RequestParam("ids") String ids) {
        List<Long> idList = parseIds(ids);
        return success(stationInfoApi.listStationsByIds(idList).getCheckedData());
    }

    @GetMapping("/station-map")
    @Operation(summary = "批量获取场站转Map")
    @Parameter(name = "ids", description = "场站ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<Map<Long, StationInfoRespDTO>> stationMap(@RequestParam("ids") String ids) {
        return success(stationInfoApi.getStationMap(parseIds(ids)));
    }

    // ==================== ParkingSpaceInfoApi ====================

    @GetMapping("/parking-get")
    @Operation(summary = "按ID获取单个车位")
    @Parameter(name = "id", description = "车位ID", required = true, example = "1")
    public CommonResult<ParkingSpaceInfoRespDTO> parkingGet(@RequestParam("id") Long id) {
        return success(parkingSpaceInfoApi.getSpace(id).getCheckedData());
    }

    @GetMapping("/parking-list-by-ids")
    @Operation(summary = "按ID批量获取车位")
    @Parameter(name = "ids", description = "车位ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<List<ParkingSpaceInfoRespDTO>> parkingListByIds(@RequestParam("ids") String ids) {
        return success(parkingSpaceInfoApi.listSpacesByIds(parseIds(ids)).getCheckedData());
    }

    @GetMapping("/parking-list-by-station")
    @Operation(summary = "按场站ID获取车位列表")
    @Parameter(name = "stationId", description = "场站ID", required = true, example = "1")
    public CommonResult<List<ParkingSpaceInfoRespDTO>> parkingListByStation(@RequestParam("stationId") Long stationId) {
        return success(parkingSpaceInfoApi.listSpacesByStationId(stationId).getCheckedData());
    }

    @GetMapping("/parking-map")
    @Operation(summary = "批量获取车位转Map")
    @Parameter(name = "ids", description = "车位ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<Map<Long, ParkingSpaceInfoRespDTO>> parkingMap(@RequestParam("ids") String ids) {
        return success(parkingSpaceInfoApi.getSpaceMap(parseIds(ids)));
    }

    // ==================== AreaInfoApi ====================

    @GetMapping("/area-get")
    @Operation(summary = "按ID获取单个片区")
    @Parameter(name = "id", description = "片区ID", required = true, example = "1")
    public CommonResult<AreaInfoRespDTO> areaGet(@RequestParam("id") Long id) {
        return success(areaInfoApi.getArea(id).getCheckedData());
    }

    @GetMapping("/area-list-all")
    @Operation(summary = "获取全部片区列表")
    public CommonResult<List<AreaInfoRespDTO>> areaListAll() {
        return success(areaInfoApi.listAreas().getCheckedData());
    }

    @GetMapping("/area-list-by-ids")
    @Operation(summary = "按ID批量获取片区")
    @Parameter(name = "ids", description = "片区ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<List<AreaInfoRespDTO>> areaListByIds(@RequestParam("ids") String ids) {
        return success(areaInfoApi.listAreasByIds(parseIds(ids)).getCheckedData());
    }

    @GetMapping("/area-map")
    @Operation(summary = "批量获取片区转Map")
    @Parameter(name = "ids", description = "片区ID列表,逗号分隔", required = true, example = "1,2,3")
    public CommonResult<Map<Long, AreaInfoRespDTO>> areaMap(@RequestParam("ids") String ids) {
        return success(areaInfoApi.getAreaMap(parseIds(ids)));
    }

    // ==================== 私有工具方法 ====================

    /**
     * 解析逗号分隔的 ID 字符串 → List<Long>
     */
    private List<Long> parseIds(String ids) {
        return Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
