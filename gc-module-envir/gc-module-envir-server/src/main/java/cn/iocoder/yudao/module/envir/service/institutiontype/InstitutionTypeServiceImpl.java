package cn.iocoder.yudao.module.envir.service.institutiontype;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.institutiontype.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.institutiontype.InstitutionTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.institutiontype.InstitutionTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 机构类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InstitutionTypeServiceImpl implements InstitutionTypeService {

    @Resource
    private InstitutionTypeMapper institutionTypeMapper;

    @Override
    public Long createInstitutionType(InstitutionTypeSaveReqVO createReqVO) {
        // 插入
        InstitutionTypeDO institutionType = BeanUtils.toBean(createReqVO, InstitutionTypeDO.class);
        institutionTypeMapper.insert(institutionType);
        // 返回
        return institutionType.getId();
    }

    @Override
    public void updateInstitutionType(InstitutionTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateInstitutionTypeExists(updateReqVO.getId());
        // 更新
        InstitutionTypeDO updateObj = BeanUtils.toBean(updateReqVO, InstitutionTypeDO.class);
        institutionTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstitutionType(Long id) {
        // 校验存在
        validateInstitutionTypeExists(id);
        // 删除
        institutionTypeMapper.deleteById(id);
    }

    private void validateInstitutionTypeExists(Long id) {
        if (institutionTypeMapper.selectById(id) == null) {
            throw exception(INSTITUTION_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public InstitutionTypeDO getInstitutionType(Long id) {
        return institutionTypeMapper.selectById(id);
    }

    @Override
    public PageResult<InstitutionTypeDO> getInstitutionTypePage(InstitutionTypePageReqVO pageReqVO) {
        return institutionTypeMapper.selectPage(pageReqVO);
    }

}