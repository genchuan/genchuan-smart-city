package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationCreateReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationUpdateReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation.ChargingStationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.alibaba.nacos.shaded.io.grpc.Status.NOT_FOUND;

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

    @Override
    public Long createChargingStation(ChargingStationCreateReqVO createReqVO) {
        // 1. 校验场站编号唯一
        validateStationCodeUnique(createReqVO.getStationCode());

        // 2. 构建DO对象
        ChargingStationDO station = new ChargingStationDO();
        station.setStationCode(createReqVO.getStationCode());
        station.setStationName(createReqVO.getStationName());
        station.setAddress(createReqVO.getAddress());
        station.setCoopMode(createReqVO.getCoopMode());
        station.setOpenTime(createReqVO.getOpenTime());
        station.setPriceService(createReqVO.getPriceService());
        station.setManager(createReqVO.getManager());
        station.setLon(createReqVO.getLon());
        station.setLat(createReqVO.getLat());
        station.setRemark(createReqVO.getRemark());
        station.setReserve1(createReqVO.getReserve1());
        station.setReserve2(createReqVO.getReserve2());

        // 默认启用状态（可根据业务调整）
        station.setStationStatus("enabled");

        // 3. 插入数据库
        chargingStationMapper.insert(station);

        // 4. 返回主键ID
        return station.getId();
    }

    /**
     * 校验场站编号唯一
     */
    private void validateStationCodeUnique(String stationCode) {
        ChargingStationDO station = chargingStationMapper.selectOne(
                new LambdaQueryWrapper<ChargingStationDO>()
                        .eq(ChargingStationDO::getStationCode, stationCode)
        );
        if (station != null) {
            throw new ServiceException(BAD_REQUEST.getCode(), "场站编号已存在");
        }
    }

    @Override
    public void updateChargingStation(ChargingStationUpdateReqVO updateReqVO) {
        // 1. 校验场站存在
        ChargingStationDO oldStation = validateStationExists(updateReqVO.getId());
        // 2. 校验场站编号唯一（排除自身）
        validateStationCodeUnique(updateReqVO.getStationCode(), updateReqVO.getId());

        // 3. 构建更新对象
        ChargingStationDO updateObj = new ChargingStationDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setStationCode(updateReqVO.getStationCode());
        updateObj.setStationName(updateReqVO.getStationName());
        updateObj.setAddress(updateReqVO.getAddress());
        updateObj.setCoopMode(updateReqVO.getCoopMode());
        updateObj.setOpenTime(updateReqVO.getOpenTime());
        updateObj.setPriceService(updateReqVO.getPriceService());
        updateObj.setManager(updateReqVO.getManager());
        updateObj.setLon(updateReqVO.getLon());
        updateObj.setLat(updateReqVO.getLat());
        updateObj.setRemark(updateReqVO.getRemark());
        updateObj.setReserve1(updateReqVO.getReserve1());
        updateObj.setReserve2(updateReqVO.getReserve2());

        // 4. 执行更新
        chargingStationMapper.updateById(updateObj);
    }

    /**
     * 校验场站编号唯一（编辑时排除自己）
     */
    private void validateStationCodeUnique(String stationCode, Long id) {
        ChargingStationDO station = chargingStationMapper.selectOne(
                new LambdaQueryWrapper<ChargingStationDO>()
                        .eq(ChargingStationDO::getStationCode, stationCode)
                        .ne(ChargingStationDO::getId, id) // 排除当前ID
        );
        if (station != null) {
            throw new ServiceException(BAD_REQUEST.getCode(), "场站编号已存在");
        }
    }

    /**
     * 校验场站是否存在
     */
    private ChargingStationDO validateStationExists(Long id) {
        ChargingStationDO station = chargingStationMapper.selectById(id);
        if (station == null) {
            throw new ServiceException(BAD_REQUEST.getCode(), "充电站不存在");
        }
        return station;
    }
}
