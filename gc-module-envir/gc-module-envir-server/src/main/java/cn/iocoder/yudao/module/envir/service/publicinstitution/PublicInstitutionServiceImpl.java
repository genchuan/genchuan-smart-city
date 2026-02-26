package cn.iocoder.yudao.module.envir.service.publicinstitution;

import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.publicinstitution.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.publicinstitution.PublicInstitutionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

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
    public List<PublicInstitutionDetailDO> getPublicInstitutionListDetail() {
        return publicInstitutionMapper.selectListDetail();
    }

}