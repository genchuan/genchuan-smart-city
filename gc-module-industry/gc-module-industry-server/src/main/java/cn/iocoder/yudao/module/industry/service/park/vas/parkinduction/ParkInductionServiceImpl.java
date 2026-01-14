package cn.iocoder.yudao.module.industry.service.park.vas.parkinduction;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkinduction.ParkInductionDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkinduction.ParkInductionMapper;
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
 * 停车诱导配置 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkInductionServiceImpl implements ParkInductionService {

    @Resource
    private ParkInductionMapper parkInductionMapper;

    @Override
    public Long createParkInduction(ParkInductionSaveReqVO createReqVO) {
        // 插入
        ParkInductionDO parkInduction = BeanUtils.toBean(createReqVO, ParkInductionDO.class);
        parkInductionMapper.insert(parkInduction);
        // 返回
        return parkInduction.getId();
    }

    @Override
    public void updateParkInduction(ParkInductionSaveReqVO updateReqVO) {
        // 校验存在
        validateParkInductionExists(updateReqVO.getId());
        // 更新
        ParkInductionDO updateObj = BeanUtils.toBean(updateReqVO, ParkInductionDO.class);
        parkInductionMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkInduction(Long id) {
        // 校验存在
        validateParkInductionExists(id);
        // 删除
        parkInductionMapper.deleteById(id);
    }

    private void validateParkInductionExists(Long id) {
        if (parkInductionMapper.selectById(id) == null) {
            throw exception(PARK_INDUCTION_NOT_EXISTS);
        }
    }

    @Override
    public ParkInductionDO getParkInduction(Long id) {
        return parkInductionMapper.selectById(id);
    }

    @Override
    public PageResult<ParkInductionDO> getParkInductionPage(ParkInductionPageReqVO pageReqVO) {
        return parkInductionMapper.selectPage(pageReqVO);
    }

}
