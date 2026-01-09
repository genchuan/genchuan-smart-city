package cn.iocoder.yudao.module.smartcity.service.bridgesafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.bridgesafety.BridgeSafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.bridgesafety.BridgeSafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 桥梁安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class BridgeSafetyServiceImpl implements BridgeSafetyService {

    @Resource
    private BridgeSafetyMapper bridgeSafetyMapper;

    @Override
    public Long createBridgeSafety(BridgeSafetySaveReqVO createReqVO) {
        // 插入
        BridgeSafetyDO bridgeSafety = BeanUtils.toBean(createReqVO, BridgeSafetyDO.class);
        bridgeSafetyMapper.insert(bridgeSafety);
        // 返回
        return bridgeSafety.getId();
    }

    @Override
    public void updateBridgeSafety(BridgeSafetySaveReqVO updateReqVO) {
        // 校验存在
        validateBridgeSafetyExists(updateReqVO.getId());
        // 更新
        BridgeSafetyDO updateObj = BeanUtils.toBean(updateReqVO, BridgeSafetyDO.class);
        bridgeSafetyMapper.updateById(updateObj);
    }

    @Override
    public void deleteBridgeSafety(Long id) {
        // 校验存在
        validateBridgeSafetyExists(id);
        // 删除
        bridgeSafetyMapper.deleteById(id);
    }

    private void validateBridgeSafetyExists(Long id) {
        if (bridgeSafetyMapper.selectById(id) == null) {
            throw exception(BRIDGE_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public BridgeSafetyDO getBridgeSafety(Long id) {
        return bridgeSafetyMapper.selectById(id);
    }

    @Override
    public PageResult<BridgeSafetyDO> getBridgeSafetyPage(BridgeSafetyPageReqVO pageReqVO) {
        return bridgeSafetyMapper.selectPage(pageReqVO);
    }

}