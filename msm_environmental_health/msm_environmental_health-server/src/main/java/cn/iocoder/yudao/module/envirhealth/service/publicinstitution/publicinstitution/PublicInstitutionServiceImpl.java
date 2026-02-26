package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.PublicInstitutionDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.PublicInstitutionMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公共机构 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PublicInstitutionServiceImpl implements PublicInstitutionService {

    @Resource
    private PublicInstitutionMapper publicInstitutionMapper;

    @Override
    public Long createPublicInstitution(PublicInstitutionSaveReqVO createReqVO) {
        // 插入
        PublicInstitutionDO publicInstitution = BeanUtils.toBean(createReqVO, PublicInstitutionDO.class);
        publicInstitutionMapper.insert(publicInstitution);
        // 返回
        return publicInstitution.getId();
    }

    @Override
    public void updatePublicInstitution(PublicInstitutionSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicInstitutionExists(updateReqVO.getId());
        // 更新
        PublicInstitutionDO updateObj = BeanUtils.toBean(updateReqVO, PublicInstitutionDO.class);
        publicInstitutionMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicInstitution(Long id) {
        // 校验存在
        validatePublicInstitutionExists(id);
        // 删除
        publicInstitutionMapper.deleteById(id);
    }

    private void validatePublicInstitutionExists(Long id) {
        if (publicInstitutionMapper.selectById(id) == null) {
            throw exception(PUBLIC_INSTITUTION_NOT_EXISTS);
        }
    }

    @Override
    public PublicInstitutionDO getPublicInstitution(Long id) {
        return publicInstitutionMapper.selectById(id);
    }

    @Override
    public PageResult<PublicInstitutionDO> getPublicInstitutionPage(PublicInstitutionPageReqVO pageReqVO) {
        return publicInstitutionMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PublicInstitutionDetailDO> getPublicInstitutionDetailPage(PublicInstitutionPageReqVO pageReqVO) {
        Long total = publicInstitutionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<PublicInstitutionDetailDO> list = publicInstitutionMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}