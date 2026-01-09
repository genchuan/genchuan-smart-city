package cn.iocoder.yudao.module.smartcity.service.environmentalsanitation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsanitation.EnvironmentalSanitationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.environmentalsanitation.EnvironmentalSanitationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 市容环卫 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EnvironmentalSanitationServiceImpl implements EnvironmentalSanitationService {

    @Resource
    private EnvironmentalSanitationMapper environmentalSanitationMapper;

    @Override
    public Long createEnvironmentalSanitation(EnvironmentalSanitationSaveReqVO createReqVO) {
        // 插入
        EnvironmentalSanitationDO environmentalSanitation = BeanUtils.toBean(createReqVO, EnvironmentalSanitationDO.class);
        environmentalSanitationMapper.insert(environmentalSanitation);
        // 返回
        return environmentalSanitation.getId();
    }

    @Override
    public void updateEnvironmentalSanitation(EnvironmentalSanitationSaveReqVO updateReqVO) {
        // 校验存在
        validateEnvironmentalSanitationExists(updateReqVO.getId());
        // 更新
        EnvironmentalSanitationDO updateObj = BeanUtils.toBean(updateReqVO, EnvironmentalSanitationDO.class);
        environmentalSanitationMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnvironmentalSanitation(Long id) {
        // 校验存在
        validateEnvironmentalSanitationExists(id);
        // 删除
        environmentalSanitationMapper.deleteById(id);
    }

    private void validateEnvironmentalSanitationExists(Long id) {
        if (environmentalSanitationMapper.selectById(id) == null) {
            throw exception(ENVIRONMENTAL_SANITATION_NOT_EXISTS);
        }
    }

    @Override
    public EnvironmentalSanitationDO getEnvironmentalSanitation(Long id) {
        return environmentalSanitationMapper.selectById(id);
    }

    @Override
    public PageResult<EnvironmentalSanitationDO> getEnvironmentalSanitationPage(EnvironmentalSanitationPageReqVO pageReqVO) {
        return environmentalSanitationMapper.selectPage(pageReqVO);
    }

}