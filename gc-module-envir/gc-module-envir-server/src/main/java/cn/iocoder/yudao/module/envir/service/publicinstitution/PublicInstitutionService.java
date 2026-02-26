package cn.iocoder.yudao.module.envir.service.publicinstitution;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.publicinstitution.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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

    /**
     * 获得公共机构列表
     */
    List<PublicInstitutionDetailDO> getPublicInstitutionListDetail();
}