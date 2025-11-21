package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publichotlinelink;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publichotlinelink.vo.PublicHotlineLinkSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publichotlinelink.PublicHotlineLinkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publichotlinelink.PublicHotlineLinkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 热线对接 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicHotlineLinkServiceImpl implements PublicHotlineLinkService {

    @Resource
    private PublicHotlineLinkMapper publicHotlineLinkMapper;

    @Override
    public Long createPublicHotlineLink(PublicHotlineLinkSaveReqVO createReqVO) {
        // 插入
        PublicHotlineLinkDO publicHotlineLink = BeanUtils.toBean(createReqVO, PublicHotlineLinkDO.class);
        publicHotlineLinkMapper.insert(publicHotlineLink);
        // 返回
        return publicHotlineLink.getId();
    }

    @Override
    public void updatePublicHotlineLink(PublicHotlineLinkSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicHotlineLinkExists(updateReqVO.getId());
        // 更新
        PublicHotlineLinkDO updateObj = BeanUtils.toBean(updateReqVO, PublicHotlineLinkDO.class);
        publicHotlineLinkMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicHotlineLink(Long id) {
        // 校验存在
        validatePublicHotlineLinkExists(id);
        // 删除
        publicHotlineLinkMapper.deleteById(id);
    }

    private void validatePublicHotlineLinkExists(Long id) {
        if (publicHotlineLinkMapper.selectById(id) == null) {
            throw exception(PUBLIC_HOTLINE_LINK_NOT_EXISTS);
        }
    }

    @Override
    public PublicHotlineLinkDO getPublicHotlineLink(Long id) {
        return publicHotlineLinkMapper.selectById(id);
    }

    @Override
    public PageResult<PublicHotlineLinkDO> getPublicHotlineLinkPage(PublicHotlineLinkPageReqVO pageReqVO) {
        return publicHotlineLinkMapper.selectPage(pageReqVO);
    }

}