package cn.iocoder.yudao.module.smartcity.service.drainagesafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagesafety.DrainageSafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.drainagesafety.DrainageSafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 排水安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DrainageSafetyServiceImpl implements DrainageSafetyService {

    @Resource
    private DrainageSafetyMapper drainageSafetyMapper;

    @Override
    public Long createDrainageSafety(DrainageSafetySaveReqVO createReqVO) {
        // 插入
        DrainageSafetyDO drainageSafety = BeanUtils.toBean(createReqVO, DrainageSafetyDO.class);
        drainageSafetyMapper.insert(drainageSafety);
        // 返回
        return drainageSafety.getId();
    }

    @Override
    public void updateDrainageSafety(DrainageSafetySaveReqVO updateReqVO) {
        // 校验存在
        validateDrainageSafetyExists(updateReqVO.getId());
        // 更新
        DrainageSafetyDO updateObj = BeanUtils.toBean(updateReqVO, DrainageSafetyDO.class);
        drainageSafetyMapper.updateById(updateObj);
    }

    @Override
    public void deleteDrainageSafety(Long id) {
        // 校验存在
        validateDrainageSafetyExists(id);
        // 删除
        drainageSafetyMapper.deleteById(id);
    }

    private void validateDrainageSafetyExists(Long id) {
        if (drainageSafetyMapper.selectById(id) == null) {
            throw exception(DRAINAGE_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public DrainageSafetyDO getDrainageSafety(Long id) {
        return drainageSafetyMapper.selectById(id);
    }

    @Override
    public PageResult<DrainageSafetyDO> getDrainageSafetyPage(DrainageSafetyPageReqVO pageReqVO) {
        return drainageSafetyMapper.selectPage(pageReqVO);
    }

}