package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutionproblem;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionProblemDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 公共机构问题 Service 接口
 *
 * @author 芋道源码
 */
public interface InstitutionProblemService {

    /**
     * 创建公共机构问题
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstitutionProblem(@Valid InstitutionProblemSaveReqVO createReqVO);

    /**
     * 更新公共机构问题
     *
     * @param updateReqVO 更新信息
     */
    void updateInstitutionProblem(@Valid InstitutionProblemSaveReqVO updateReqVO);

    /**
     * 删除公共机构问题
     *
     * @param id 编号
     */
    void deleteInstitutionProblem(Long id);

    /**
     * 获得公共机构问题
     *
     * @param id 编号
     * @return 公共机构问题
     */
    InstitutionProblemDO getInstitutionProblem(Long id);

    /**
     * 获得公共机构问题分页
     *
     * @param pageReqVO 分页查询
     * @return 公共机构问题分页
     */
    PageResult<InstitutionProblemDO> getInstitutionProblemPage(InstitutionProblemPageReqVO pageReqVO);

    PageResult<InstitutionProblemDetailDO> getInstitutionProblemDetailPage(InstitutionProblemPageReqVO pageReqVO);

}