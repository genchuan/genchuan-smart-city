package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publiccomplain;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publiccomplain.vo.PublicComplainSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publiccomplain.PublicComplainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publiccomplain.PublicComplainMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 投诉举报 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicComplainServiceImpl implements PublicComplainService {

    @Resource
    private PublicComplainMapper publicComplainMapper;

    @Override
    public Long createPublicComplain(PublicComplainSaveReqVO createReqVO) {
        // 插入
        PublicComplainDO publicComplain = BeanUtils.toBean(createReqVO, PublicComplainDO.class);
        publicComplainMapper.insert(publicComplain);
        // 返回
        return publicComplain.getId();
    }

    @Override
    public void updatePublicComplain(PublicComplainSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicComplainExists(updateReqVO.getId());
        // 更新
        PublicComplainDO updateObj = BeanUtils.toBean(updateReqVO, PublicComplainDO.class);
        publicComplainMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicComplain(Long id) {
        // 校验存在
        validatePublicComplainExists(id);
        // 删除
        publicComplainMapper.deleteById(id);
    }

    private void validatePublicComplainExists(Long id) {
        if (publicComplainMapper.selectById(id) == null) {
            throw exception(PUBLIC_COMPLAIN_NOT_EXISTS);
        }
    }

    @Override
    public PublicComplainDO getPublicComplain(Long id) {
        return publicComplainMapper.selectById(id);
    }

    @Override
    public PageResult<PublicComplainDO> getPublicComplainPage(PublicComplainPageReqVO pageReqVO) {
        return publicComplainMapper.selectPage(pageReqVO);
    }

}