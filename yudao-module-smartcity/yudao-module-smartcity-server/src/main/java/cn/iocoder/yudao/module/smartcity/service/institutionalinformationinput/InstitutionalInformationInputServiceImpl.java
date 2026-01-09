package cn.iocoder.yudao.module.smartcity.service.institutionalinformationinput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.institutionalinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.institutionalinformationinput.InstitutionalInformationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.institutionalinformationinput.InstitutionalInformationInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 机构信息录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InstitutionalInformationInputServiceImpl implements InstitutionalInformationInputService {

    @Resource
    private InstitutionalInformationInputMapper institutionalInformationInputMapper;

    @Override
    public Long createInstitutionalInformationInput(InstitutionalInformationInputSaveReqVO createReqVO) {
        // 插入
        InstitutionalInformationInputDO institutionalInformationInput = BeanUtils.toBean(createReqVO, InstitutionalInformationInputDO.class);
        institutionalInformationInputMapper.insert(institutionalInformationInput);
        // 返回
        return institutionalInformationInput.getId();
    }

    @Override
    public void updateInstitutionalInformationInput(InstitutionalInformationInputSaveReqVO updateReqVO) {
        // 校验存在
        validateInstitutionalInformationInputExists(updateReqVO.getId());
        // 更新
        InstitutionalInformationInputDO updateObj = BeanUtils.toBean(updateReqVO, InstitutionalInformationInputDO.class);
        institutionalInformationInputMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstitutionalInformationInput(Long id) {
        // 校验存在
        validateInstitutionalInformationInputExists(id);
        // 删除
        institutionalInformationInputMapper.deleteById(id);
    }

    private void validateInstitutionalInformationInputExists(Long id) {
        if (institutionalInformationInputMapper.selectById(id) == null) {
            throw exception(INSTITUTIONAL_INFORMATION_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public InstitutionalInformationInputDO getInstitutionalInformationInput(Long id) {
        return institutionalInformationInputMapper.selectById(id);
    }

    @Override
    public PageResult<InstitutionalInformationInputDO> getInstitutionalInformationInputPage(InstitutionalInformationInputPageReqVO pageReqVO) {
        return institutionalInformationInputMapper.selectPage(pageReqVO);
    }

}