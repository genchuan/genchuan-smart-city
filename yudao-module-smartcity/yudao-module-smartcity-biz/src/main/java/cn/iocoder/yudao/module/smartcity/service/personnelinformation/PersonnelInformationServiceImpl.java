package cn.iocoder.yudao.module.smartcity.service.personnelinformation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelinformation.PersonnelInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.personnelinformation.PersonnelInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 人员信息 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PersonnelInformationServiceImpl implements PersonnelInformationService {

    @Resource
    private PersonnelInformationMapper personnelInformationMapper;

    @Override
    public Long createPersonnelInformation(PersonnelInformationSaveReqVO createReqVO) {
        // 插入
        PersonnelInformationDO personnelInformation = BeanUtils.toBean(createReqVO, PersonnelInformationDO.class);
        personnelInformationMapper.insert(personnelInformation);
        // 返回
        return personnelInformation.getId();
    }

    @Override
    public void updatePersonnelInformation(PersonnelInformationSaveReqVO updateReqVO) {
        // 校验存在
        validatePersonnelInformationExists(updateReqVO.getId());
        // 更新
        PersonnelInformationDO updateObj = BeanUtils.toBean(updateReqVO, PersonnelInformationDO.class);
        personnelInformationMapper.updateById(updateObj);
    }

    @Override
    public void deletePersonnelInformation(Long id) {
        // 校验存在
        validatePersonnelInformationExists(id);
        // 删除
        personnelInformationMapper.deleteById(id);
    }

    private void validatePersonnelInformationExists(Long id) {
        if (personnelInformationMapper.selectById(id) == null) {
            throw exception(PERSONNEL_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public PersonnelInformationDO getPersonnelInformation(Long id) {
        return personnelInformationMapper.selectById(id);
    }

    @Override
    public PageResult<PersonnelInformationDO> getPersonnelInformationPage(PersonnelInformationPageReqVO pageReqVO) {
        return personnelInformationMapper.selectPage(pageReqVO);
    }

}