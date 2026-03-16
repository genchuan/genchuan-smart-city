package cn.iocoder.yudao.module.kitchen.service.enterpriseinfo;

import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo.EnterpriseInfoSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.enterpriseinfo.EnterpriseInfoDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.enterpriseinfo.EnterpriseInfoMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 企业信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Override
    public Long createEnterpriseInfo(EnterpriseInfoSaveReqVO createReqVO) {
        // 插入
        EnterpriseInfoDO enterpriseInfo = BeanUtils.toBean(createReqVO, EnterpriseInfoDO.class);
        enterpriseInfoMapper.insert(enterpriseInfo);
        // 返回
        return enterpriseInfo.getId();
    }

    @Override
    public void updateEnterpriseInfo(EnterpriseInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateEnterpriseInfoExists(updateReqVO.getId());
        // 更新
        EnterpriseInfoDO updateObj = BeanUtils.toBean(updateReqVO, EnterpriseInfoDO.class);
        enterpriseInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnterpriseInfo(Long id) {
        // 校验存在
        validateEnterpriseInfoExists(id);
        // 删除
        enterpriseInfoMapper.deleteById(id);
    }

    private void validateEnterpriseInfoExists(Long id) {
        if (enterpriseInfoMapper.selectById(id) == null) {
            throw exception(ENTERPRISE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public EnterpriseInfoDO getEnterpriseInfo(Long id) {
        return enterpriseInfoMapper.selectById(id);
    }

    @Override
    public PageResult<EnterpriseInfoDO> getEnterpriseInfoPage(EnterpriseInfoPageReqVO pageReqVO) {
        return enterpriseInfoMapper.selectPage(pageReqVO);
    }

}
