package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.AreaInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
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
 * 片区信息 RPC 实现 V2（完整版）
 */
@RestController
@Validated
public class AreaInfoApiImplV2 implements AreaInfoApi {

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public CommonResult<AreaInfoRespDTO> getArea(Long id) {
        if (id == null) {
            return success(null);
        }
        AreaInfoDO area = areaInfoMapper.selectById(id);
        return success(area == null ? null : toDTO(area));
    }

    @Override
    public CommonResult<List<AreaInfoRespDTO>> listAreas() {
        List<AreaInfoDO> list = areaInfoMapper.selectList();
        if (list.isEmpty()) {
            return success(Collections.emptyList());
        }
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @Override
    public CommonResult<List<AreaInfoRespDTO>> listAreasByIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<Long> distinctIds = ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (distinctIds.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<AreaInfoDO> list = areaInfoMapper.selectBatchIds(distinctIds);
        if (list.isEmpty()) {
            return success(new ArrayList<>());
        }
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    // ==================== 私有工具方法 ====================

    /**
     * AreaInfoDO → AreaInfoRespDTO
     *
     * <p>完整映射 area_info + BaseDO 全部字段 + 坐标
     */
    private AreaInfoRespDTO toDTO(AreaInfoDO d) {
        AreaInfoRespDTO dto = new AreaInfoRespDTO();
        dto.setId(d.getId());
        dto.setAreaNo(d.getAreaNo());
        dto.setName(d.getName());
        dto.setParentId(d.getParentId());
        dto.setProvince(d.getProvince());
        dto.setCity(d.getCity());
        dto.setDistrict(d.getDistrict());
        dto.setAddress(d.getAddress());
        dto.setLeaderId(d.getLeaderId());
        dto.setLeaderName(d.getLeaderName());
        dto.setUserId(d.getUserId());
        dto.setPhone(d.getPhone());
        dto.setStationCount(d.getStationCount());
        dto.setStatus(d.getStatus());
        dto.setBindTime(d.getBindTime());
        dto.setBindUserId(d.getBindUserId());
        dto.setRemark(d.getRemark());
        dto.setReserve1(d.getReserve1());
        dto.setReserve2(d.getReserve2());
        // BaseDO 审计字段
        dto.setCreateTime(d.getCreateTime());
        dto.setCreator(d.getCreator());
        dto.setUpdater(d.getUpdater());
        dto.setUpdateTime(d.getUpdateTime());
        // 坐标
        dto.setLon(d.getLon());
        dto.setLat(d.getLat());
        return dto;
    }
}
