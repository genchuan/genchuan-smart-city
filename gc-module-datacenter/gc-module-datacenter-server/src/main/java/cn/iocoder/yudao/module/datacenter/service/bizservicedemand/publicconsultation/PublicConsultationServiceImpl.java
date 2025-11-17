package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publicconsultation;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicconsultation.PublicConsultationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publicconsultation.PublicConsultationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 咨询建议 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicConsultationServiceImpl implements PublicConsultationService {

    @Resource
    private PublicConsultationMapper publicConsultationMapper;

    @Override
    public Long createPublicConsultation(PublicConsultationSaveReqVO createReqVO) {
        // 插入
        PublicConsultationDO publicConsultation = BeanUtils.toBean(createReqVO, PublicConsultationDO.class);
        publicConsultationMapper.insert(publicConsultation);
        // 返回
        return publicConsultation.getId();
    }

    @Override
    public void updatePublicConsultation(PublicConsultationSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicConsultationExists(updateReqVO.getId());
        // 更新
        PublicConsultationDO updateObj = BeanUtils.toBean(updateReqVO, PublicConsultationDO.class);
        publicConsultationMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicConsultation(Long id) {
        // 校验存在
        validatePublicConsultationExists(id);
        // 删除
        publicConsultationMapper.deleteById(id);
    }

    private void validatePublicConsultationExists(Long id) {
        if (publicConsultationMapper.selectById(id) == null) {
            throw exception(PUBLIC_CONSULTATION_NOT_EXISTS);
        }
    }

    @Override
    public PublicConsultationDO getPublicConsultation(Long id) {
        return publicConsultationMapper.selectById(id);
    }

    @Override
    public PageResult<PublicConsultationDO> getPublicConsultationPage(PublicConsultationPageReqVO pageReqVO) {
        return publicConsultationMapper.selectPage(pageReqVO);
    }

}