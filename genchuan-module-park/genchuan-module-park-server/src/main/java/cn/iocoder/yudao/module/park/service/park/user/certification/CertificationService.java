package cn.iocoder.yudao.module.park.service.park.user.certification;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.certification.CertificationDO;
import jakarta.validation.Valid;

/**
 * 认证记录 Service 接口
 *
 * @author 亘川智城
 */
public interface CertificationService {

    /**
     * 创建认证记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCertification(@Valid CertificationSaveReqVO createReqVO);

    /**
     * 更新认证记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCertification(@Valid CertificationSaveReqVO updateReqVO);

    /**
     * 删除认证记录
     *
     * @param id 编号
     */
    void deleteCertification(Long id);

    /**
     * 获得认证记录
     *
     * @param id 编号
     * @return 认证记录
     */
    CertificationDO getCertification(Long id);

    /**
     * 获得认证记录分页
     *
     * @param pageReqVO 分页查询
     * @return 认证记录分页
     */
    PageResult<CertificationDO> getCertificationPage(CertificationPageReqVO pageReqVO);

}
