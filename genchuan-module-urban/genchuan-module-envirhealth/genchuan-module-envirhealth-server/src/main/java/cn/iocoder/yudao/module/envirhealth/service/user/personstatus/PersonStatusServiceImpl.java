package cn.iocoder.yudao.module.envirhealth.service.user.personstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus.PersonStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.personstatus.PersonStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.PersonStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.PersonStatusMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PERSON_STATUS_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getPersonStatusOptions() {

        List<PersonStatusDO> list;
        list = personStatusMapper.selectList(
                new LambdaQueryWrapperX<PersonStatusDO>()
                        .select(
                                PersonStatusDO::getId,
                                PersonStatusDO::getPersonStatusId,
                                PersonStatusDO::getName
                        )
                        .eq(PersonStatusDO::getDeleted, 0)
                        .orderByDesc(PersonStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, personStatusDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(personStatusDO.getName());
            vo.setValue(personStatusDO.getPersonStatusId());
            return vo;
        });
    }
}