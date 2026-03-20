package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutionproblem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.InstitutionProblemMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publicinstitution.InstitutionProblemCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.INSTITUTION_PROBLEM_NOT_EXISTS;

/**
 * 公共机构问题 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InstitutionProblemServiceImpl implements InstitutionProblemService {

    @Resource
    private InstitutionProblemMapper institutionProblemMapper;

    @Resource
    private InstitutionProblemCodeGenerator codeGenerator;

    @Override
    public Long createInstitutionProblem(InstitutionProblemSaveReqVO createReqVO) {
        // 插入
        InstitutionProblemDO institutionProblem = BeanUtils.toBean(createReqVO, InstitutionProblemDO.class);

        institutionProblem.setProblemId(codeGenerator.generateProblemId());

        institutionProblemMapper.insert(institutionProblem);
        // 返回
        return institutionProblem.getId();
    }

    @Override
    public void updateInstitutionProblem(InstitutionProblemSaveReqVO updateReqVO) {
        // 校验存在
        validateInstitutionProblemExists(updateReqVO.getId());
        // 更新
        InstitutionProblemDO updateObj = BeanUtils.toBean(updateReqVO, InstitutionProblemDO.class);
        institutionProblemMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstitutionProblem(Long id) {
        // 校验存在
        validateInstitutionProblemExists(id);
        // 删除
        institutionProblemMapper.deleteById(id);
    }

    private void validateInstitutionProblemExists(Long id) {
        if (institutionProblemMapper.selectById(id) == null) {
            throw exception(INSTITUTION_PROBLEM_NOT_EXISTS);
        }
    }

    @Override
    public InstitutionProblemDO getInstitutionProblem(Long id) {
        return institutionProblemMapper.selectById(id);
    }

    @Override
    public PageResult<InstitutionProblemDO> getInstitutionProblemPage(InstitutionProblemPageReqVO pageReqVO) {
        return institutionProblemMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<InstitutionProblemDetailDO> getInstitutionProblemDetailPage(InstitutionProblemPageReqVO pageReqVO) {
        Long total = institutionProblemMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<InstitutionProblemDetailDO> list = institutionProblemMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}