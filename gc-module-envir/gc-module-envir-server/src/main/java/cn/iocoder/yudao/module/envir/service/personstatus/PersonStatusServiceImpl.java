package cn.iocoder.yudao.module.envir.service.personstatus;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.personstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.personstatus.PersonStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.personstatus.PersonStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 人员状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PersonStatusServiceImpl implements PersonStatusService {

    @Resource
    private PersonStatusMapper personStatusMapper;

    @Override
    public Long createPersonStatus(PersonStatusSaveReqVO createReqVO) {
        // 插入
        PersonStatusDO personStatus = BeanUtils.toBean(createReqVO, PersonStatusDO.class);
        personStatusMapper.insert(personStatus);
        // 返回
        return personStatus.getId();
    }

    @Override
    public void updatePersonStatus(PersonStatusSaveReqVO updateReqVO) {
        // 校验存在
        validatePersonStatusExists(updateReqVO.getId());
        // 更新
        PersonStatusDO updateObj = BeanUtils.toBean(updateReqVO, PersonStatusDO.class);
        personStatusMapper.updateById(updateObj);
    }

    @Override
    public void deletePersonStatus(Long id) {
        // 校验存在
        validatePersonStatusExists(id);
        // 删除
        personStatusMapper.deleteById(id);
    }

    private void validatePersonStatusExists(Long id) {
        if (personStatusMapper.selectById(id) == null) {
            throw exception(PERSON_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public PersonStatusDO getPersonStatus(Long id) {
        return personStatusMapper.selectById(id);
    }

    @Override
    public PageResult<PersonStatusDO> getPersonStatusPage(PersonStatusPageReqVO pageReqVO) {
        return personStatusMapper.selectPage(pageReqVO);
    }

}