package cn.iocoder.yudao.module.park.service.park.user.participatingunit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.participatingunit.ParticipatingUnitDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.participatingunit.ParticipatingUnitMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.PARTICIPATING_UNIT_NOT_EXISTS;

/**
 * 参与单位 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ParticipatingUnitServiceImpl implements ParticipatingUnitService {

    @Resource
    private ParticipatingUnitMapper participatingUnitMapper;

    @Override
    public Long createParticipatingUnit(ParticipatingUnitSaveReqVO createReqVO) {
        // 插入
        ParticipatingUnitDO participatingUnit = BeanUtils.toBean(createReqVO, ParticipatingUnitDO.class);
        participatingUnitMapper.insert(participatingUnit);
        // 返回
        return participatingUnit.getId();
    }

    @Override
    public void updateParticipatingUnit(ParticipatingUnitSaveReqVO updateReqVO) {
        // 校验存在
        validateParticipatingUnitExists(updateReqVO.getId());
        // 更新
        ParticipatingUnitDO updateObj = BeanUtils.toBean(updateReqVO, ParticipatingUnitDO.class);
        participatingUnitMapper.updateById(updateObj);
    }

    @Override
    public void deleteParticipatingUnit(Long id) {
        // 校验存在
        validateParticipatingUnitExists(id);
        // 删除
        participatingUnitMapper.deleteById(id);
    }

    private void validateParticipatingUnitExists(Long id) {
        if (participatingUnitMapper.selectById(id) == null) {
            throw exception(PARTICIPATING_UNIT_NOT_EXISTS);
        }
    }

    @Override
    public ParticipatingUnitDO getParticipatingUnit(Long id) {
        return participatingUnitMapper.selectById(id);
    }

    @Override
    public PageResult<ParticipatingUnitDO> getParticipatingUnitPage(ParticipatingUnitPageReqVO pageReqVO) {
        return participatingUnitMapper.selectPage(pageReqVO);
    }

}
