package cn.iocoder.yudao.module.industry.service.park.guidance.parkguidance;

import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidancePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidanceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.guidance.parkguidance.ParkGuidanceDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.guidance.parkguidance.ParkGuidanceMapper;
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
 * 停车诱导服务 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkGuidanceServiceImpl implements ParkGuidanceService {

    @Resource
    private ParkGuidanceMapper parkGuidanceMapper;

    @Override
    public Long createParkGuidance(ParkGuidanceSaveReqVO createReqVO) {
        // 插入
        ParkGuidanceDO parkGuidance = BeanUtils.toBean(createReqVO, ParkGuidanceDO.class);
        parkGuidanceMapper.insert(parkGuidance);
        // 返回
        return parkGuidance.getId();
    }

    @Override
    public void updateParkGuidance(ParkGuidanceSaveReqVO updateReqVO) {
        // 校验存在
        validateParkGuidanceExists(updateReqVO.getId());
        // 更新
        ParkGuidanceDO updateObj = BeanUtils.toBean(updateReqVO, ParkGuidanceDO.class);
        parkGuidanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkGuidance(Long id) {
        // 校验存在
        validateParkGuidanceExists(id);
        // 删除
        parkGuidanceMapper.deleteById(id);
    }

    private void validateParkGuidanceExists(Long id) {
        if (parkGuidanceMapper.selectById(id) == null) {
            throw exception(PARK_GUIDANCE_NOT_EXISTS);
        }
    }

    @Override
    public ParkGuidanceDO getParkGuidance(Long id) {
        return parkGuidanceMapper.selectById(id);
    }

    @Override
    public PageResult<ParkGuidanceDO> getParkGuidancePage(ParkGuidancePageReqVO pageReqVO) {
        return parkGuidanceMapper.selectPage(pageReqVO);
    }

}
