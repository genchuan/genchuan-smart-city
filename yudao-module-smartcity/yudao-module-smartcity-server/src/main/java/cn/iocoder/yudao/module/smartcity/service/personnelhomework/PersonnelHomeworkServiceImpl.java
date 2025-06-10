package cn.iocoder.yudao.module.smartcity.service.personnelhomework;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelhomework.PersonnelHomeworkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.personnelhomework.PersonnelHomeworkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 人员作业 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PersonnelHomeworkServiceImpl implements PersonnelHomeworkService {

    @Resource
    private PersonnelHomeworkMapper personnelHomeworkMapper;

    @Override
    public Long createPersonnelHomework(PersonnelHomeworkSaveReqVO createReqVO) {
        // 插入
        PersonnelHomeworkDO personnelHomework = BeanUtils.toBean(createReqVO, PersonnelHomeworkDO.class);
        personnelHomeworkMapper.insert(personnelHomework);
        // 返回
        return personnelHomework.getId();
    }

    @Override
    public void updatePersonnelHomework(PersonnelHomeworkSaveReqVO updateReqVO) {
        // 校验存在
        validatePersonnelHomeworkExists(updateReqVO.getId());
        // 更新
        PersonnelHomeworkDO updateObj = BeanUtils.toBean(updateReqVO, PersonnelHomeworkDO.class);
        personnelHomeworkMapper.updateById(updateObj);
    }

    @Override
    public void deletePersonnelHomework(Long id) {
        // 校验存在
        validatePersonnelHomeworkExists(id);
        // 删除
        personnelHomeworkMapper.deleteById(id);
    }

    private void validatePersonnelHomeworkExists(Long id) {
        if (personnelHomeworkMapper.selectById(id) == null) {
            throw exception(PERSONNEL_HOMEWORK_NOT_EXISTS);
        }
    }

    @Override
    public PersonnelHomeworkDO getPersonnelHomework(Long id) {
        return personnelHomeworkMapper.selectById(id);
    }

    @Override
    public PageResult<PersonnelHomeworkDO> getPersonnelHomeworkPage(PersonnelHomeworkPageReqVO pageReqVO) {
        return personnelHomeworkMapper.selectPage(pageReqVO);
    }

}