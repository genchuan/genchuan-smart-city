package cn.iocoder.yudao.module.datacenter.service.policylegislation.nationalpol;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.NationalPolPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.NationalPolSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.nationalpol.NationalPolDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.nationalpol.NationalPolMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 国家政策信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class NationalPolServiceImpl implements NationalPolService {

    @Resource
    private NationalPolMapper nationalPolMapper;

    @Override
    public Long createNationalPol(NationalPolSaveReqVO createReqVO) {
        // 插入
        NationalPolDO nationalPol = BeanUtils.toBean(createReqVO, NationalPolDO.class);
        nationalPolMapper.insert(nationalPol);
        // 返回
        return nationalPol.getId();
    }

    @Override
    public void updateNationalPol(NationalPolSaveReqVO updateReqVO) {
        // 校验存在
        validateNationalPolExists(updateReqVO.getId());
        // 更新
        NationalPolDO updateObj = BeanUtils.toBean(updateReqVO, NationalPolDO.class);
        nationalPolMapper.updateById(updateObj);
    }

    @Override
    public void deleteNationalPol(Long id) {
        // 校验存在
        validateNationalPolExists(id);
        // 删除
        nationalPolMapper.deleteById(id);
    }

    private void validateNationalPolExists(Long id) {
        if (nationalPolMapper.selectById(id) == null) {
            throw exception(NATIONAL_POL_NOT_EXISTS);
        }
    }

    @Override
    public NationalPolDO getNationalPol(Long id) {
        return nationalPolMapper.selectById(id);
    }

    @Override
    public PageResult<NationalPolDO> getNationalPolPage(NationalPolPageReqVO pageReqVO) {
        return nationalPolMapper.selectPage(pageReqVO);
    }

}