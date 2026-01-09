package cn.iocoder.yudao.module.industry.service.park.asset.lot;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.lot.ParkLotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.lot.ParkLotMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车场信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkLotServiceImpl implements ParkLotService {

    @Resource
    private ParkLotMapper parkLotMapper;

    @Override
    public Long createParkLot(ParkLotSaveReqVO createReqVO) {
        // 插入
        ParkLotDO parkLot = BeanUtils.toBean(createReqVO, ParkLotDO.class);
        parkLotMapper.insert(parkLot);
        // 返回
        return parkLot.getId();
    }

    @Override
    public void updateParkLot(ParkLotSaveReqVO updateReqVO) {
        // 校验存在
        validateParkLotExists(updateReqVO.getId());
        // 更新
        ParkLotDO updateObj = BeanUtils.toBean(updateReqVO, ParkLotDO.class);
        parkLotMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkLot(Long id) {
        // 校验存在
        validateParkLotExists(id);
        // 删除
        parkLotMapper.deleteById(id);
    }

    private void validateParkLotExists(Long id) {
        if (parkLotMapper.selectById(id) == null) {
            throw exception(PARK_LOT_NOT_EXISTS);
        }
    }

    @Override
    public ParkLotDO getParkLot(Long id) {
        return parkLotMapper.selectById(id);
    }

    @Override
    public PageResult<ParkLotDO> getParkLotPage(ParkLotPageReqVO pageReqVO) {
        return parkLotMapper.selectPage(pageReqVO);
    }

}