package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.PublicInstitutionDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 公共机构 Service 接口
 *
 * @author 芋道源码
 */
public interface PublicInstitutionService {

    /**
     * 创建公共机构
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicInstitution(@Valid PublicInstitutionSaveReqVO createReqVO);

    /**
     * 更新公共机构
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicInstitution(@Valid PublicInstitutionSaveReqVO updateReqVO);

    /**
     * 删除公共机构
     *
     * @param id 编号
     */
    void deletePublicInstitution(Long id);

    /**
     * 获得公共机构
     *
     * @param id 编号
     * @return 公共机构
     */
    PublicInstitutionDO getPublicInstitution(Long id);

    /**
     * 获得公共机构分页
     *
     * @param pageReqVO 分页查询
     * @return 公共机构分页
     */
    PageResult<PublicInstitutionDO> getPublicInstitutionPage(PublicInstitutionPageReqVO pageReqVO);

    PageResult<PublicInstitutionDetailDO> getPublicInstitutionDetailPage(PublicInstitutionPageReqVO pageReqVO);

}