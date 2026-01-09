package cn.iocoder.yudao.module.industry.service.park.asset.garage;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGaragePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGarageSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.garage.ParkGarageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.garage.ParkGarageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车库信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkGarageServiceImpl implements ParkGarageService {

    @Resource
    private ParkGarageMapper parkGarageMapper;

    @Override
    public Long createParkGarage(ParkGarageSaveReqVO createReqVO) {
        // 插入
        ParkGarageDO parkGarage = BeanUtils.toBean(createReqVO, ParkGarageDO.class);

        parkGarageMapper.insert(parkGarage);

        // 返回
        return parkGarage.getId();
    }

    @Override
    public void updateParkGarage(ParkGarageSaveReqVO updateReqVO) {
        // 校验存在
        validateParkGarageExists(updateReqVO.getId());
        // 更新
        ParkGarageDO updateObj = BeanUtils.toBean(updateReqVO, ParkGarageDO.class);
        parkGarageMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkGarage(Long id) {



        // 校验存在
        validateParkGarageExists(id);
        // 删除
        parkGarageMapper.deleteById(id);
    }

    private void validateParkGarageExists(Long id) {
        if (parkGarageMapper.selectById(id) == null) {
            throw exception(PARK_GARAGE_NOT_EXISTS);




        }
    }

    @Override
    public ParkGarageDO getParkGarage(Long id) {
        return parkGarageMapper.selectById(id);
    }

    @Override
    public PageResult<ParkGarageDO> getParkGaragePage(ParkGaragePageReqVO pageReqVO) {
        return parkGarageMapper.selectPage(pageReqVO);
    }

}