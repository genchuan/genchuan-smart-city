package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationRespVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation.ChargingStationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ChargingStationServiceImpl implements ChargingStationService{

    @Resource
    private ChargingStationMapper chargingStationMapper;

    @Override
    public PageResult<ChargingStationRespVO> getChargingStationPage(ChargingStationPageReqVO reqVO) {
        PageResult<ChargingStationDO> page = chargingStationMapper.selectPage(reqVO);
        return BeanUtils.toBean(page, ChargingStationRespVO.class);
    }
}
