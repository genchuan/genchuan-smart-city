package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 车位信息 RPC 实现（完整版）
 *
 * <p>路径与旧的 api/parking/ParkingSpaceInfoApiImpl 不冲突（/parking-space vs /parking-space-info），两套可并存
 */
@RestController
@Validated
public class ParkingSpaceInfoApiImpl implements ParkingSpaceInfoApi {

    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    @Override
    public CommonResult<ParkingSpaceInfoRespDTO> getSpace(Long id) {
        if (id == null) {
            return success(null);
        }
        ParkingSpaceInfoDO space = parkingSpaceInfoMapper.selectById(id);
        return success(space == null ? null : toDTO(space));
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
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
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
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    // ==================== 私有工具方法 ====================

    /**
     * ParkingSpaceInfoDO → ParkingSpaceInfoRespDTO
     *
     * <p>完整映射 parking_space_info 全部字段
     */
    private ParkingSpaceInfoRespDTO toDTO(ParkingSpaceInfoDO d) {
        ParkingSpaceInfoRespDTO dto = new ParkingSpaceInfoRespDTO();
        dto.setId(d.getId());
        dto.setSpaceNo(d.getSpaceNo());
        dto.setStationId(d.getStationId());
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
        return dto;
    }
}
