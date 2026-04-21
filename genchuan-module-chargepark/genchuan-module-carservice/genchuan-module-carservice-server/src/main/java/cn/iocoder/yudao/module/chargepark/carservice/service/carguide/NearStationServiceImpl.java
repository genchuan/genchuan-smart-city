package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.NEAR_STATION_NOT_EXISTS;

/**
 * 周边场站 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class NearStationServiceImpl implements NearStationService {

    @Resource
    private NearStationMapper nearStationMapper;

    @Override
    public Long createNearStation(NearStationSaveReqVO createReqVO) {
        NearStationDO nearStation = BeanUtils.toBean(createReqVO, NearStationDO.class);
        if (nearStation.getQueryTime() == null) {
            nearStation.setQueryTime(LocalDateTime.now());
        }
        nearStationMapper.insert(nearStation);
        return nearStation.getId();
    }

    @Override
    public void updateNearStation(NearStationSaveReqVO updateReqVO) {
        validateNearStationExists(updateReqVO.getId());
        NearStationDO updateObj = BeanUtils.toBean(updateReqVO, NearStationDO.class);
        nearStationMapper.updateById(updateObj);
    }

    @Override
    public void deleteNearStation(Long id) {
        validateNearStationExists(id);
        nearStationMapper.deleteById(id);
    }

    @Override
    public void deleteNearStationListByIds(List<Long> ids) {
        nearStationMapper.deleteByIds(ids);
    }

    private void validateNearStationExists(Long id) {
        if (nearStationMapper.selectById(id) == null) {
            throw exception(NEAR_STATION_NOT_EXISTS);
        }
    }

    @Override
    public NearStationDO getNearStation(Long id) {
        return nearStationMapper.selectById(id);
    }

    @Override
    public PageResult<NearStationDO> getNearStationPage(NearStationPageReqVO pageReqVO) {
        return nearStationMapper.selectPage(pageReqVO);
    }

}
