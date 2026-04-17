package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.ChargeParkMapMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.CHARGE_PARK_MAP_NOT_EXISTS;

/**
 * 充停地图 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class ChargeParkMapServiceImpl implements ChargeParkMapService {

    @Resource
    private ChargeParkMapMapper chargeParkMapMapper;

    @Override
    public Long createChargeParkMap(ChargeParkMapSaveReqVO createReqVO) {
        ChargeParkMapDO chargeParkMap = BeanUtils.toBean(createReqVO, ChargeParkMapDO.class);
        if (chargeParkMap.getQueryTime() == null) {
            chargeParkMap.setQueryTime(LocalDateTime.now());
        }
        chargeParkMapMapper.insert(chargeParkMap);
        return chargeParkMap.getId();
    }

    @Override
    public void updateChargeParkMap(ChargeParkMapSaveReqVO updateReqVO) {
        validateChargeParkMapExists(updateReqVO.getId());
        ChargeParkMapDO updateObj = BeanUtils.toBean(updateReqVO, ChargeParkMapDO.class);
        chargeParkMapMapper.updateById(updateObj);
    }

    @Override
    public void deleteChargeParkMap(Long id) {
        validateChargeParkMapExists(id);
        chargeParkMapMapper.deleteById(id);
    }

    @Override
    public void deleteChargeParkMapListByIds(List<Long> ids) {
        chargeParkMapMapper.deleteByIds(ids);
    }

    private void validateChargeParkMapExists(Long id) {
        if (chargeParkMapMapper.selectById(id) == null) {
            throw exception(CHARGE_PARK_MAP_NOT_EXISTS);
        }
    }

    @Override
    public ChargeParkMapDO getChargeParkMap(Long id) {
        return chargeParkMapMapper.selectById(id);
    }

    @Override
    public PageResult<ChargeParkMapDO> getChargeParkMapPage(ChargeParkMapPageReqVO pageReqVO) {
        return chargeParkMapMapper.selectPage(pageReqVO);
    }

}
