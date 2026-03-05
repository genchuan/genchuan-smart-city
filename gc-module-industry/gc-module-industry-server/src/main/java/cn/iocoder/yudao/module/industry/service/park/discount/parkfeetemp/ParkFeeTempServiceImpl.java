package cn.iocoder.yudao.module.industry.service.park.discount.parkfeetemp;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeetemp.ParkFeeTempDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkfeetemp.ParkFeeTempMapper;
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
 * 临停收费规则 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkFeeTempServiceImpl implements ParkFeeTempService {

    @Resource
    private ParkFeeTempMapper parkFeeTempMapper;

    @Override
    public Long createParkFeeTemp(ParkFeeTempSaveReqVO createReqVO) {
        // 插入
        ParkFeeTempDO parkFeeTemp = BeanUtils.toBean(createReqVO, ParkFeeTempDO.class);
        parkFeeTempMapper.insert(parkFeeTemp);
        // 返回
        return parkFeeTemp.getId();
    }

    @Override
    public void updateParkFeeTemp(ParkFeeTempSaveReqVO updateReqVO) {
        // 校验存在
        validateParkFeeTempExists(updateReqVO.getId());
        // 更新
        ParkFeeTempDO updateObj = BeanUtils.toBean(updateReqVO, ParkFeeTempDO.class);
        parkFeeTempMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkFeeTemp(Long id) {
        // 校验存在
        validateParkFeeTempExists(id);
        // 删除
        parkFeeTempMapper.deleteById(id);
    }

    private void validateParkFeeTempExists(Long id) {
        if (parkFeeTempMapper.selectById(id) == null) {
            throw exception(PARK_FEE_TEMP_NOT_EXISTS);
        }
    }

    @Override
    public ParkFeeTempDO getParkFeeTemp(Long id) {
        return parkFeeTempMapper.selectById(id);
    }

    @Override
    public PageResult<ParkFeeTempDO> getParkFeeTempPage(ParkFeeTempPageReqVO pageReqVO) {
        return parkFeeTempMapper.selectPage(pageReqVO);
    }

}
