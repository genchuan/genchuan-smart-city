package cn.iocoder.yudao.module.industry.service.park.order.parkordertemp;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkordertemp.ParkOrderTempDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkordertemp.ParkOrderTempMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 临停订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkOrderTempServiceImpl implements ParkOrderTempService {

    @Resource
    private ParkOrderTempMapper parkOrderTempMapper;

    @Override
    public Long createParkOrderTemp(ParkOrderTempSaveReqVO createReqVO) {
//        System.out.println(createReqVO.getParkingStartTime());
//        System.out.println(createReqVO.getParkingEndTime());
//        System.out.println(createReqVO.getPayTime());

        createReqVO.setId(null);
        // 插入
        ParkOrderTempDO parkOrderTemp = BeanUtils.toBean(createReqVO, ParkOrderTempDO.class);
        parkOrderTempMapper.insert(parkOrderTemp);
        // 返回
        return parkOrderTemp.getId();
    }

    @Override
    public void updateParkOrderTemp(ParkOrderTempSaveReqVO updateReqVO) {
        // 校验存在
        validateParkOrderTempExists(updateReqVO.getId());
        // 更新
        ParkOrderTempDO updateObj = BeanUtils.toBean(updateReqVO, ParkOrderTempDO.class);
        parkOrderTempMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkOrderTemp(Long id) {
        // 校验存在
        validateParkOrderTempExists(id);
        // 删除
        parkOrderTempMapper.deleteById(id);
    }

    private void validateParkOrderTempExists(Long id) {
        if (parkOrderTempMapper.selectById(id) == null) {
            throw exception(PARK_ORDER_TEMP_NOT_EXISTS);
        }
    }

    @Override
    public ParkOrderTempDO getParkOrderTemp(Long id) {
        return parkOrderTempMapper.selectById(id);
    }

    @Override
    public PageResult<ParkOrderTempDO> getParkOrderTempPage(ParkOrderTempPageReqVO pageReqVO) {
        return parkOrderTempMapper.selectPage(pageReqVO);
    }

}
