package cn.iocoder.yudao.module.smartcity.service.experienceinformationinput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.experienceinformationinput.ExperienceInformationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.experienceinformationinput.ExperienceInformationInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 经验信息录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ExperienceInformationInputServiceImpl implements ExperienceInformationInputService {

    @Resource
    private ExperienceInformationInputMapper experienceInformationInputMapper;

    @Override
    public Long createExperienceInformationInput(ExperienceInformationInputSaveReqVO createReqVO) {
        // 插入
        ExperienceInformationInputDO experienceInformationInput = BeanUtils.toBean(createReqVO, ExperienceInformationInputDO.class);
        experienceInformationInputMapper.insert(experienceInformationInput);
        // 返回
        return experienceInformationInput.getId();
    }

    @Override
    public void updateExperienceInformationInput(ExperienceInformationInputSaveReqVO updateReqVO) {
        // 校验存在
        validateExperienceInformationInputExists(updateReqVO.getId());
        // 更新
        ExperienceInformationInputDO updateObj = BeanUtils.toBean(updateReqVO, ExperienceInformationInputDO.class);
        experienceInformationInputMapper.updateById(updateObj);
    }

    @Override
    public void deleteExperienceInformationInput(Long id) {
        // 校验存在
        validateExperienceInformationInputExists(id);
        // 删除
        experienceInformationInputMapper.deleteById(id);
    }

    private void validateExperienceInformationInputExists(Long id) {
        if (experienceInformationInputMapper.selectById(id) == null) {
            throw exception(EXPERIENCE_INFORMATION_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public ExperienceInformationInputDO getExperienceInformationInput(Long id) {
        return experienceInformationInputMapper.selectById(id);
    }

    @Override
    public PageResult<ExperienceInformationInputDO> getExperienceInformationInputPage(ExperienceInformationInputPageReqVO pageReqVO) {
        return experienceInformationInputMapper.selectPage(pageReqVO);
    }

}