package cn.iocoder.yudao.module.smartcity.service.dynamicinformationinput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationinput.DynamicInformationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.dynamicinformationinput.DynamicInformationInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 动态信息录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DynamicInformationInputServiceImpl implements DynamicInformationInputService {

    @Resource
    private DynamicInformationInputMapper dynamicInformationInputMapper;

    @Override
    public Long createDynamicInformationInput(DynamicInformationInputSaveReqVO createReqVO) {
        // 插入
        DynamicInformationInputDO dynamicInformationInput = BeanUtils.toBean(createReqVO, DynamicInformationInputDO.class);
        dynamicInformationInputMapper.insert(dynamicInformationInput);
        // 返回
        return dynamicInformationInput.getId();
    }

    @Override
    public void updateDynamicInformationInput(DynamicInformationInputSaveReqVO updateReqVO) {
        // 校验存在
        validateDynamicInformationInputExists(updateReqVO.getId());
        // 更新
        DynamicInformationInputDO updateObj = BeanUtils.toBean(updateReqVO, DynamicInformationInputDO.class);
        dynamicInformationInputMapper.updateById(updateObj);
    }

    @Override
    public void deleteDynamicInformationInput(Long id) {
        // 校验存在
        validateDynamicInformationInputExists(id);
        // 删除
        dynamicInformationInputMapper.deleteById(id);
    }

    private void validateDynamicInformationInputExists(Long id) {
        if (dynamicInformationInputMapper.selectById(id) == null) {
            throw exception(DYNAMIC_INFORMATION_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public DynamicInformationInputDO getDynamicInformationInput(Long id) {
        return dynamicInformationInputMapper.selectById(id);
    }

    @Override
    public PageResult<DynamicInformationInputDO> getDynamicInformationInputPage(DynamicInformationInputPageReqVO pageReqVO) {
        return dynamicInformationInputMapper.selectPage(pageReqVO);
    }

}