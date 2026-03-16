package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutioninspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionInspectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionInspectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.InstitutionInspectionMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.publicinstitution.InstitutionInspectionCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.INSTITUTION_INSPECTION_NOT_EXISTS;

/**
 * 公共机构核查 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InstitutionInspectionServiceImpl implements InstitutionInspectionService {

    @Resource
    private InstitutionInspectionMapper institutionInspectionMapper;

    @Resource
    private InstitutionInspectionCodeGenerator codeGenerator;

    @Override
    public Long createInstitutionInspection(InstitutionInspectionSaveReqVO createReqVO) {
        // 插入
        InstitutionInspectionDO institutionInspection = BeanUtils.toBean(createReqVO, InstitutionInspectionDO.class);

        institutionInspection.setInspectionId(codeGenerator.generateInspectionId());

        institutionInspectionMapper.insert(institutionInspection);
        // 返回
        return institutionInspection.getId();
    }

    @Override
    public void updateInstitutionInspection(InstitutionInspectionSaveReqVO updateReqVO) {
        // 校验存在
        validateInstitutionInspectionExists(updateReqVO.getId());
        // 更新
        InstitutionInspectionDO updateObj = BeanUtils.toBean(updateReqVO, InstitutionInspectionDO.class);
        institutionInspectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstitutionInspection(Long id) {
        // 校验存在
        validateInstitutionInspectionExists(id);
        // 删除
        institutionInspectionMapper.deleteById(id);
    }

    private void validateInstitutionInspectionExists(Long id) {
        if (institutionInspectionMapper.selectById(id) == null) {
            throw exception(INSTITUTION_INSPECTION_NOT_EXISTS);
        }
    }

    @Override
    public InstitutionInspectionDO getInstitutionInspection(Long id) {
        return institutionInspectionMapper.selectById(id);
    }

    @Override
    public PageResult<InstitutionInspectionDO> getInstitutionInspectionPage(InstitutionInspectionPageReqVO pageReqVO) {
        return institutionInspectionMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<InstitutionInspectionDetailDO> getInstitutionInspectionDetailPage(InstitutionInspectionPageReqVO pageReqVO) {
        Long total = institutionInspectionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<InstitutionInspectionDetailDO> list = institutionInspectionMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}