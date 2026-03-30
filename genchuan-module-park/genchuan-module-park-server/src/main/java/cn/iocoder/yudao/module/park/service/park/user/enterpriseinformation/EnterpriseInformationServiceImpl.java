package cn.iocoder.yudao.module.park.service.park.user.enterpriseinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.enterpriseinformation.vo.EnterpriseInformationSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.enterpriseinformation.EnterpriseInformationDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.enterpriseinformation.EnterpriseInformationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.ENTERPRISE_INFORMATION_NOT_EXISTS;

/**
 * 企业信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {

    @Resource
    private EnterpriseInformationMapper enterpriseInformationMapper;

    @Override
    public Long createEnterpriseInformation(EnterpriseInformationSaveReqVO createReqVO) {
        // 插入
        EnterpriseInformationDO enterpriseInformation = BeanUtils.toBean(createReqVO, EnterpriseInformationDO.class);
        enterpriseInformationMapper.insert(enterpriseInformation);
        // 返回
        return enterpriseInformation.getId();
    }

    @Override
    public void updateEnterpriseInformation(EnterpriseInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateEnterpriseInformationExists(updateReqVO.getId());
        // 更新
        EnterpriseInformationDO updateObj = BeanUtils.toBean(updateReqVO, EnterpriseInformationDO.class);
        enterpriseInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnterpriseInformation(Long id) {
        // 校验存在
        validateEnterpriseInformationExists(id);
        // 删除
        enterpriseInformationMapper.deleteById(id);
    }

    private void validateEnterpriseInformationExists(Long id) {
        if (enterpriseInformationMapper.selectById(id) == null) {
            throw exception(ENTERPRISE_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public EnterpriseInformationDO getEnterpriseInformation(Long id) {
        return enterpriseInformationMapper.selectById(id);
    }

    @Override
    public PageResult<EnterpriseInformationDO> getEnterpriseInformationPage(EnterpriseInformationPageReqVO pageReqVO) {
        return enterpriseInformationMapper.selectPage(pageReqVO);
    }

}
