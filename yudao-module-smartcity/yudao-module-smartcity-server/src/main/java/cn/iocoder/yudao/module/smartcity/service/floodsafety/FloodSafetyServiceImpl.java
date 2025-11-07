package cn.iocoder.yudao.module.smartcity.service.floodsafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.floodsafety.FloodSafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.floodsafety.FloodSafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 内涝安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class FloodSafetyServiceImpl implements FloodSafetyService {

    @Resource
    private FloodSafetyMapper floodSafetyMapper;

    @Override
    public Long createFloodSafety(FloodSafetySaveReqVO createReqVO) {
        // 插入
        FloodSafetyDO floodSafety = BeanUtils.toBean(createReqVO, FloodSafetyDO.class);
        floodSafetyMapper.insert(floodSafety);
        // 返回
        return floodSafety.getId();
    }

    @Override
    public void updateFloodSafety(FloodSafetySaveReqVO updateReqVO) {
        // 校验存在
        validateFloodSafetyExists(updateReqVO.getId());
        // 更新
        FloodSafetyDO updateObj = BeanUtils.toBean(updateReqVO, FloodSafetyDO.class);
        floodSafetyMapper.updateById(updateObj);
    }

    @Override
    public void deleteFloodSafety(Long id) {
        // 校验存在
        validateFloodSafetyExists(id);
        // 删除
        floodSafetyMapper.deleteById(id);
    }

    private void validateFloodSafetyExists(Long id) {
        if (floodSafetyMapper.selectById(id) == null) {
            throw exception(FLOOD_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public FloodSafetyDO getFloodSafety(Long id) {
        return floodSafetyMapper.selectById(id);
    }

    @Override
    public PageResult<FloodSafetyDO> getFloodSafetyPage(FloodSafetyPageReqVO pageReqVO) {
        return floodSafetyMapper.selectPage(pageReqVO);
    }

}