package cn.iocoder.yudao.module.industry.service.park.vas.parksmstemplate;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplatePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplateSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parksmstemplate.ParkSmsTemplateDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parksmstemplate.ParkSmsTemplateMapper;
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
 * 短信模板 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkSmsTemplateServiceImpl implements ParkSmsTemplateService {

    @Resource
    private ParkSmsTemplateMapper parkSmsTemplateMapper;

    @Override
    public Long createParkSmsTemplate(ParkSmsTemplateSaveReqVO createReqVO) {
        // 插入
        ParkSmsTemplateDO parkSmsTemplate = BeanUtils.toBean(createReqVO, ParkSmsTemplateDO.class);
        parkSmsTemplateMapper.insert(parkSmsTemplate);
        // 返回
        return parkSmsTemplate.getId();
    }

    @Override
    public void updateParkSmsTemplate(ParkSmsTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSmsTemplateExists(updateReqVO.getId());
        // 更新
        ParkSmsTemplateDO updateObj = BeanUtils.toBean(updateReqVO, ParkSmsTemplateDO.class);
        parkSmsTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSmsTemplate(Long id) {
        // 校验存在
        validateParkSmsTemplateExists(id);
        // 删除
        parkSmsTemplateMapper.deleteById(id);
    }

    private void validateParkSmsTemplateExists(Long id) {
        if (parkSmsTemplateMapper.selectById(id) == null) {
            throw exception(PARK_SMS_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ParkSmsTemplateDO getParkSmsTemplate(Long id) {
        return parkSmsTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSmsTemplateDO> getParkSmsTemplatePage(ParkSmsTemplatePageReqVO pageReqVO) {
        return parkSmsTemplateMapper.selectPage(pageReqVO);
    }

}
