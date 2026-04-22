package cn.iocoder.yudao.module.stationresource.api.parking;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.parking.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口,给 Feign 调用
@Validated
public class ParkingSpaceInfoApiImpl implements ParkingSpaceInfoApi {

    @Resource private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    @Override
    public CommonResult<ParkingSpaceInfoRespDTO> getSpace(Long id) {
        if (id == null) {
            return success(null);
        }
        ParkingSpaceInfoDO space = parkingSpaceInfoMapper.selectById(id);
        return success(space == null ? null : toDTO(space));
    }

    @Override
    public CommonResult<List<ParkingSpaceInfoRespDTO>> getSpaceList(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<Long> distinctIds = ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (distinctIds.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<ParkingSpaceInfoDO> list = parkingSpaceInfoMapper.selectBatchIds(distinctIds);
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private ParkingSpaceInfoRespDTO toDTO(ParkingSpaceInfoDO d) {
        ParkingSpaceInfoRespDTO dto = new ParkingSpaceInfoRespDTO();
        dto.setId(d.getId());
        dto.setSpaceNo(d.getSpaceNo());
        dto.setStationId(d.getStationId());
        return dto;
    }

}
