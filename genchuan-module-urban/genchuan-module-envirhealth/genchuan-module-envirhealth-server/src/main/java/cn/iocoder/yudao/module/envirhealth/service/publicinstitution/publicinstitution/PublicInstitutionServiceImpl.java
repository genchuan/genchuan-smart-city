package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.PublicInstitutionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.PublicInstitutionMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.publicinstitution.PublicInstitutionCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_INSTITUTION_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_INSTITUTION_NOT_EXISTS;

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

    @Resource
    private PublicInstitutionCodeGenerator codeGenerator;

    @Override
    public Long createPublicInstitution(PublicInstitutionSaveReqVO createReqVO) {
        //检验重复名称
        checkNameUnique(createReqVO.getName(), null);

        //CleanerIds空值处理
        if (createReqVO.getCleanerIds() == null || createReqVO.getCleanerIds().trim().isEmpty()) {
            createReqVO.setCleanerIds("[]");
        }

        // 插入
        PublicInstitutionDO publicInstitution = BeanUtils.toBean(createReqVO, PublicInstitutionDO.class);

        publicInstitution.setInstitutionId(codeGenerator.generateInstitutionId());

        publicInstitutionMapper.insert(publicInstitution);
        // 返回
        return publicInstitution.getId();
    }

    @Override
    public void updatePublicInstitution(PublicInstitutionSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicInstitutionExists(updateReqVO.getId());
        //CleanerIds空值处理
        if (updateReqVO.getCleanerIds() == null || updateReqVO.getCleanerIds().trim().isEmpty()) {
            updateReqVO.setCleanerIds("[]");
        }
        //检验重复名称
        checkNameUnique(updateReqVO.getName(), updateReqVO.getId());
        // 更新
        PublicInstitutionDO updateObj = BeanUtils.toBean(updateReqVO, PublicInstitutionDO.class);
        publicInstitutionMapper.updateById(updateObj);
    }

    private void checkNameUnique(String name, Long id) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        LambdaQueryWrapperX<PublicInstitutionDO> query = new LambdaQueryWrapperX<PublicInstitutionDO>()
                .eq(PublicInstitutionDO::getName, name)
                .eq(PublicInstitutionDO::getDeleted, false);

        if (id != null) {
            query.ne(PublicInstitutionDO::getId, id);
        }

        PublicInstitutionDO existing = publicInstitutionMapper.selectOne(query);
        if (existing != null) {
            throw exception(PUBLIC_INSTITUTION_NAME_DUPLICATE);
        }
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

    @Override
    public PublicInstitutionDashboardRespVO getPublicInstitutionDashboard() {
        PublicInstitutionDashboardRespVO resp = new PublicInstitutionDashboardRespVO();

        // 1. 卡片数据
        resp.setTotalInstitutions(publicInstitutionMapper.selectTotalInstitutions());
        resp.setCleaningStandardMetCount(publicInstitutionMapper.selectCleaningStandardMetCount());
        resp.setProblemClosedCount(publicInstitutionMapper.selectProblemClosedCount());
        resp.setInspectionPassCount(publicInstitutionMapper.selectInspectionPassCount());

        // 2. 圆环图数据 - 机构类型分布
        resp.setInstitutionTypeDistribution(publicInstitutionMapper.selectInstitutionTypePie());

        // 3. 圆环图数据 - 区域分布
        resp.setAreaDistribution(publicInstitutionMapper.selectAreaPie());

        // 4. 柱状图数据 - 不同类型机构保洁达标率对比
        resp.setCleaningRateByType(publicInstitutionMapper.selectCleaningRateByTypeBar());

        return resp;
    }
}