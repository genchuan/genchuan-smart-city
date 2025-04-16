package cn.iocoder.yudao.module.smartcity.service.environmentalsafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsafety.EnvironmentalSafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.environmentalsafety.EnvironmentalSafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 环卫设施安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EnvironmentalSafetyServiceImpl implements EnvironmentalSafetyService {

    @Resource
    private EnvironmentalSafetyMapper environmentalSafetyMapper;

    @Override
    public Long createEnvironmentalSafety(EnvironmentalSafetySaveReqVO createReqVO) {
        // 插入
        EnvironmentalSafetyDO environmentalSafety = BeanUtils.toBean(createReqVO, EnvironmentalSafetyDO.class);
        environmentalSafetyMapper.insert(environmentalSafety);
        // 返回
        return environmentalSafety.getId();
    }

    @Override
    public void updateEnvironmentalSafety(EnvironmentalSafetySaveReqVO updateReqVO) {
        // 校验存在
        validateEnvironmentalSafetyExists(updateReqVO.getId());
        // 更新
        EnvironmentalSafetyDO updateObj = BeanUtils.toBean(updateReqVO, EnvironmentalSafetyDO.class);
        environmentalSafetyMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnvironmentalSafety(Long id) {
        // 校验存在
        validateEnvironmentalSafetyExists(id);
        // 删除
        environmentalSafetyMapper.deleteById(id);
    }

    private void validateEnvironmentalSafetyExists(Long id) {
        if (environmentalSafetyMapper.selectById(id) == null) {
            throw exception(ENVIRONMENTAL_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public EnvironmentalSafetyDO getEnvironmentalSafety(Long id) {
        return environmentalSafetyMapper.selectById(id);
    }

    @Override
    public PageResult<EnvironmentalSafetyDO> getEnvironmentalSafetyPage(EnvironmentalSafetyPageReqVO pageReqVO) {
        return environmentalSafetyMapper.selectPage(pageReqVO);
    }

}