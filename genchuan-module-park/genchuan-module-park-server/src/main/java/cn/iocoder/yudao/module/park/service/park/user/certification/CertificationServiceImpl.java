package cn.iocoder.yudao.module.park.service.park.user.certification;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo.CertificationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.certification.CertificationDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.certification.CertificationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.CERTIFICATION_NOT_EXISTS;

/**
 * 认证记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CertificationServiceImpl implements CertificationService {

    @Resource
    private CertificationMapper certificationMapper;

    @Override
    public Long createCertification(CertificationSaveReqVO createReqVO) {
        // 插入
        CertificationDO certification = BeanUtils.toBean(createReqVO, CertificationDO.class);
        certificationMapper.insert(certification);
        // 返回
        return certification.getId();
    }

    @Override
    public void updateCertification(CertificationSaveReqVO updateReqVO) {
        // 校验存在
        validateCertificationExists(updateReqVO.getId());
        // 更新
        CertificationDO updateObj = BeanUtils.toBean(updateReqVO, CertificationDO.class);
        certificationMapper.updateById(updateObj);
    }

    @Override
    public void deleteCertification(Long id) {
        // 校验存在
        validateCertificationExists(id);
        // 删除
        certificationMapper.deleteById(id);
    }

    private void validateCertificationExists(Long id) {
        if (certificationMapper.selectById(id) == null) {
            throw exception(CERTIFICATION_NOT_EXISTS);
        }
    }

    @Override
    public CertificationDO getCertification(Long id) {
        return certificationMapper.selectById(id);
    }

    @Override
    public PageResult<CertificationDO> getCertificationPage(CertificationPageReqVO pageReqVO) {
        return certificationMapper.selectPage(pageReqVO);
    }

}
