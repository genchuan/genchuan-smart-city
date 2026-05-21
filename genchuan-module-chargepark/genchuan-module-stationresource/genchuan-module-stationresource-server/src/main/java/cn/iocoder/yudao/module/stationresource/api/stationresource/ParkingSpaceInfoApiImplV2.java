package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo.StationInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 车位信息 RPC 实现 V2（完整版）
 *
 * <p>类名加 V2 后缀，避免与旧 api/parking/ParkingSpaceInfoApiImpl 的 Spring Bean 名冲突
 */
@RestController
@Validated
public class ParkingSpaceInfoApiImplV2 implements ParkingSpaceInfoApi {

    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Override
    public CommonResult<ParkingSpaceInfoRespDTO> getSpace(Long id) {
        if (id == null) {
            return success(null);
        }
        ParkingSpaceInfoDO space = parkingSpaceInfoMapper.selectById(id);
        if (space == null) {
            return success(null);
        }
        // 填充场站名称
        StationInfoDO station = stationInfoMapper.selectById(space.getStationId());
        String stationName = station != null ? station.getName() : null;
        return success(toDTO(space, stationName));
    }

    @Override
    public CommonResult<List<ParkingSpaceInfoRespDTO>> listSpacesByIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<Long> distinctIds = ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (distinctIds.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<ParkingSpaceInfoDO> list = parkingSpaceInfoMapper.selectBatchIds(distinctIds);
        if (list.isEmpty()) {
            return success(new ArrayList<>());
        }
        // 批量加载场站名称
        Map<Long, String> stationNameMap = loadStationNameMap(list);
        return success(list.stream()
                .map(d -> toDTO(d, stationNameMap.get(d.getStationId())))
                .collect(Collectors.toList()));
    }

    @Override
    public CommonResult<List<ParkingSpaceInfoRespDTO>> listSpacesByStationId(Long stationId) {
        if (stationId == null) {
            return success(Collections.emptyList());
        }
        List<ParkingSpaceInfoDO> list = parkingSpaceInfoMapper.selectList(
                new LambdaQueryWrapper<ParkingSpaceInfoDO>()
                        .eq(ParkingSpaceInfoDO::getStationId, stationId));
        if (list.isEmpty()) {
            return success(new ArrayList<>());
        }
        // 同场站下所有车位名称一致,批量加载
        Map<Long, String> stationNameMap = loadStationNameMap(list);
        return success(list.stream()
                .map(d -> toDTO(d, stationNameMap.get(d.getStationId())))
                .collect(Collectors.toList()));
    }

    // ==================== 私有工具方法 ====================

    /**
     * 批量加载场站名称
     */
    private Map<Long, String> loadStationNameMap(List<ParkingSpaceInfoDO> list) {
        List<Long> stationIds = list.stream()
                .map(ParkingSpaceInfoDO::getStationId)
                .filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());
        if (stationIds.isEmpty()) {
            return Collections.emptyMap();
        }
        return stationInfoMapper.selectBatchIds(stationIds).stream()
                .collect(Collectors.toMap(StationInfoDO::getId, StationInfoDO::getName, (a, b) -> a));
    }

    /**
     * ParkingSpaceInfoDO → ParkingSpaceInfoRespDTO
     *
     * <p>完整映射 parking_space_info + BaseDO 全部字段 + 场站名称
     */
    private ParkingSpaceInfoRespDTO toDTO(ParkingSpaceInfoDO d, String stationName) {
        ParkingSpaceInfoRespDTO dto = new ParkingSpaceInfoRespDTO();
        // parking_space_info 本体字段
        dto.setId(d.getId());
        dto.setSpaceNo(d.getSpaceNo());
        dto.setStationId(d.getStationId());
        dto.setStationName(stationName);
        dto.setGarage(d.getGarage());
        dto.setLocation(d.getLocation());
        dto.setType(d.getType());
        dto.setDeviceType(d.getDeviceType());
        dto.setQrcode(d.getQrcode());
        dto.setStatus(d.getStatus());
        dto.setRealStatus(d.getRealStatus());
        dto.setBindTime(d.getBindTime());
        dto.setBindUserId(d.getBindUserId());
        dto.setDeviceId(d.getDeviceId());
        dto.setStatusUpdateTime(d.getStatusUpdateTime());
        dto.setRemark(d.getRemark());
        dto.setReserve1(d.getReserve1());
        dto.setReserve2(d.getReserve2());
        // BaseDO 审计字段
        dto.setCreateTime(d.getCreateTime());
        dto.setCreator(d.getCreator());
        dto.setUpdater(d.getUpdater());
        dto.setUpdateTime(d.getUpdateTime());
        return dto;
    }
}
