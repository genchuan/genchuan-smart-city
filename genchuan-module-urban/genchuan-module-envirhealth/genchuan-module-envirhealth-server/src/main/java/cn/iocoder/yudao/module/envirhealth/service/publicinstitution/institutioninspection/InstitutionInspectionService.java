package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutioninspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionInspectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionInspectionDetailDO;
import jakarta.validation.Valid;

/**
 * 公共机构核查 Service 接口
 *
 * @author 芋道源码
 */
public interface InstitutionInspectionService {

    /**
     * 创建公共机构核查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstitutionInspection(@Valid InstitutionInspectionSaveReqVO createReqVO);

    /**
     * 更新公共机构核查
     *
     * @param updateReqVO 更新信息
     */
    void updateInstitutionInspection(@Valid InstitutionInspectionSaveReqVO updateReqVO);

    /**
     * 删除公共机构核查
     *
     * @param id 编号
     */
    void deleteInstitutionInspection(Long id);

    /**
     * 获得公共机构核查
     *
     * @param id 编号
     * @return 公共机构核查
     */
    InstitutionInspectionDO getInstitutionInspection(Long id);

    /**
     * 获得公共机构核查分页
     *
     * @param pageReqVO 分页查询
     * @return 公共机构核查分页
     */
    PageResult<InstitutionInspectionDO> getInstitutionInspectionPage(InstitutionInspectionPageReqVO pageReqVO);

    PageResult<InstitutionInspectionDetailDO> getInstitutionInspectionDetailPage(InstitutionInspectionPageReqVO pageReqVO);
}