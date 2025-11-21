package cn.iocoder.yudao.module.datacenter.service.operationguide.generaloperguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuideSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.generaloperguide.GeneralOperGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.generaloperguide.GeneralOperGuideMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 通用操作指南 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GeneralOperGuideServiceImpl implements GeneralOperGuideService {

    @Resource
    private GeneralOperGuideMapper generalOperGuideMapper;

    @Override
    public Long createGeneralOperGuide(GeneralOperGuideSaveReqVO createReqVO) {
        // 插入
        GeneralOperGuideDO generalOperGuide = BeanUtils.toBean(createReqVO, GeneralOperGuideDO.class);
        generalOperGuideMapper.insert(generalOperGuide);
        // 返回
        return generalOperGuide.getId();
    }

    @Override
    public void updateGeneralOperGuide(GeneralOperGuideSaveReqVO updateReqVO) {
        // 校验存在
        validateGeneralOperGuideExists(updateReqVO.getId());
        // 更新
        GeneralOperGuideDO updateObj = BeanUtils.toBean(updateReqVO, GeneralOperGuideDO.class);
        generalOperGuideMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeneralOperGuide(Long id) {
        // 校验存在
        validateGeneralOperGuideExists(id);
        // 删除
        generalOperGuideMapper.deleteById(id);
    }

    private void validateGeneralOperGuideExists(Long id) {
        if (generalOperGuideMapper.selectById(id) == null) {
            throw exception(GENERAL_OPER_GUIDE_NOT_EXISTS);
        }
    }

    @Override
    public GeneralOperGuideDO getGeneralOperGuide(Long id) {
        return generalOperGuideMapper.selectById(id);
    }

    @Override
    public PageResult<GeneralOperGuideDO> getGeneralOperGuidePage(GeneralOperGuidePageReqVO pageReqVO) {
        return generalOperGuideMapper.selectPage(pageReqVO);
    }

}