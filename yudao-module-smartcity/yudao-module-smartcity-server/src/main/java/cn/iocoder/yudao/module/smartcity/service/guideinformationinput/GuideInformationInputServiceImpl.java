package cn.iocoder.yudao.module.smartcity.service.guideinformationinput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.guideinformationinput.GuideInformationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.guideinformationinput.GuideInformationInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 指南信息录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class GuideInformationInputServiceImpl implements GuideInformationInputService {

    @Resource
    private GuideInformationInputMapper guideInformationInputMapper;

    @Override
    public Long createGuideInformationInput(GuideInformationInputSaveReqVO createReqVO) {
        // 插入
        GuideInformationInputDO guideInformationInput = BeanUtils.toBean(createReqVO, GuideInformationInputDO.class);
        guideInformationInputMapper.insert(guideInformationInput);
        // 返回
        return guideInformationInput.getId();
    }

    @Override
    public void updateGuideInformationInput(GuideInformationInputSaveReqVO updateReqVO) {
        // 校验存在
        validateGuideInformationInputExists(updateReqVO.getId());
        // 更新
        GuideInformationInputDO updateObj = BeanUtils.toBean(updateReqVO, GuideInformationInputDO.class);
        guideInformationInputMapper.updateById(updateObj);
    }

    @Override
    public void deleteGuideInformationInput(Long id) {
        // 校验存在
        validateGuideInformationInputExists(id);
        // 删除
        guideInformationInputMapper.deleteById(id);
    }

    private void validateGuideInformationInputExists(Long id) {
        if (guideInformationInputMapper.selectById(id) == null) {
            throw exception(GUIDE_INFORMATION_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public GuideInformationInputDO getGuideInformationInput(Long id) {
        return guideInformationInputMapper.selectById(id);
    }

    @Override
    public PageResult<GuideInformationInputDO> getGuideInformationInputPage(GuideInformationInputPageReqVO pageReqVO) {
        return guideInformationInputMapper.selectPage(pageReqVO);
    }

}