package cn.iocoder.yudao.module.inspectop.api.space;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.module.inspectop.dal.mysql.spacemonitor.SpaceMonitorMapper;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController // 提供 RESTful API 接口,给 Feign 调用
@Validated
public class SpaceMonitorApiImpl implements SpaceMonitorApi {

    @Resource private SpaceMonitorMapper spaceMonitorMapper;

    @Override
    public CommonResult<SpaceMonitorRespDTO> getLatestBySpaceId(Long spaceId) {
        if (spaceId == null) {
            return success(null);
        }
        List<SpaceMonitorDO> list = spaceMonitorMapper.selectList(new LambdaQueryWrapperX<SpaceMonitorDO>()
                .eq(SpaceMonitorDO::getSpaceId, spaceId)
                .orderByDesc(SpaceMonitorDO::getMonitorTime)
                .last("LIMIT 1"));
        return success(list.isEmpty() ? null : toDTO(list.get(0)));
    }

    @Override
    public CommonResult<List<SpaceMonitorRespDTO>> listLatestSpaceMonitors() {
        // SQL 端按 space_id 去重 + 取最新监测时间,避免把全历史流水拉回来
        List<SpaceMonitorDO> list = spaceMonitorMapper.selectLatestPerSpace();
        if (list == null || list.isEmpty()) {
            return success(Collections.emptyList());
        }
        return success(list.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private SpaceMonitorRespDTO toDTO(SpaceMonitorDO d) {
        SpaceMonitorRespDTO dto = new SpaceMonitorRespDTO();
        dto.setId(d.getId());
        dto.setSpaceId(d.getSpaceId());
        dto.setStationId(d.getStationId());
        dto.setLongitude(d.getLongitude());
        dto.setLatitude(d.getLatitude());
        dto.setMonitorStatus(d.getMonitorStatus());
        return dto;
    }

}
