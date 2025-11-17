package cn.iocoder.yudao.module.datacenter.service.operationguide.specbusinessguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuideSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.specbusinessguide.SpecBusinessGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.specbusinessguide.SpecBusinessGuideMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 专项业务指南 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SpecBusinessGuideServiceImpl implements SpecBusinessGuideService {

    @Resource
    private SpecBusinessGuideMapper specBusinessGuideMapper;

    @Override
    public Long createSpecBusinessGuide(SpecBusinessGuideSaveReqVO createReqVO) {
        // 插入
        SpecBusinessGuideDO specBusinessGuide = BeanUtils.toBean(createReqVO, SpecBusinessGuideDO.class);
        specBusinessGuideMapper.insert(specBusinessGuide);
        // 返回
        return specBusinessGuide.getId();
    }

    @Override
    public void updateSpecBusinessGuide(SpecBusinessGuideSaveReqVO updateReqVO) {
        // 校验存在
        validateSpecBusinessGuideExists(updateReqVO.getId());
        // 更新
        SpecBusinessGuideDO updateObj = BeanUtils.toBean(updateReqVO, SpecBusinessGuideDO.class);
        specBusinessGuideMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpecBusinessGuide(Long id) {
        // 校验存在
        validateSpecBusinessGuideExists(id);
        // 删除
        specBusinessGuideMapper.deleteById(id);
    }

    private void validateSpecBusinessGuideExists(Long id) {
        if (specBusinessGuideMapper.selectById(id) == null) {
            throw exception(SPEC_BUSINESS_GUIDE_NOT_EXISTS);
        }
    }

    @Override
    public SpecBusinessGuideDO getSpecBusinessGuide(Long id) {
        return specBusinessGuideMapper.selectById(id);
    }

    @Override
    public PageResult<SpecBusinessGuideDO> getSpecBusinessGuidePage(SpecBusinessGuidePageReqVO pageReqVO) {
        return specBusinessGuideMapper.selectPage(pageReqVO);
    }

}