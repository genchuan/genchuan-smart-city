package cn.iocoder.yudao.module.industry.service.park.order.parkarrears;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkarrears.ParkArrearsDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkarrears.ParkArrearsMapper;
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
 * 欠费记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkArrearsServiceImpl implements ParkArrearsService {

    @Resource
    private ParkArrearsMapper parkArrearsMapper;

    @Override
    public Long createParkArrears(ParkArrearsSaveReqVO createReqVO) {
        // 插入
        ParkArrearsDO parkArrears = BeanUtils.toBean(createReqVO, ParkArrearsDO.class);
        parkArrearsMapper.insert(parkArrears);
        // 返回
        return parkArrears.getId();
    }

    @Override
    public void updateParkArrears(ParkArrearsSaveReqVO updateReqVO) {
        // 校验存在
        validateParkArrearsExists(updateReqVO.getId());
        // 更新
        ParkArrearsDO updateObj = BeanUtils.toBean(updateReqVO, ParkArrearsDO.class);
        parkArrearsMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkArrears(Long id) {
        // 校验存在
        validateParkArrearsExists(id);
        // 删除
        parkArrearsMapper.deleteById(id);
    }

    private void validateParkArrearsExists(Long id) {
        if (parkArrearsMapper.selectById(id) == null) {
            throw exception(PARK_ARREARS_NOT_EXISTS);
        }
    }

    @Override
    public ParkArrearsDO getParkArrears(Long id) {
        return parkArrearsMapper.selectById(id);
    }

    @Override
    public PageResult<ParkArrearsDO> getParkArrearsPage(ParkArrearsPageReqVO pageReqVO) {
        return parkArrearsMapper.selectPage(pageReqVO);
    }

}
