package cn.iocoder.yudao.module.waterdetection.service.testingcapability;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingcapability.TestingCapabilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.testingcapability.TestingCapabilityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 检测能力及设备管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TestingCapabilityServiceImpl implements TestingCapabilityService {

    @Resource
    private TestingCapabilityMapper testingCapabilityMapper;

    @Override
    public Long createTestingCapability(TestingCapabilitySaveReqVO createReqVO) {
        // 插入
        TestingCapabilityDO testingCapability = BeanUtils.toBean(createReqVO, TestingCapabilityDO.class);
        testingCapabilityMapper.insert(testingCapability);
        // 返回
        return testingCapability.getId();
    }

    @Override
    public void updateTestingCapability(TestingCapabilitySaveReqVO updateReqVO) {
        // 校验存在
        validateTestingCapabilityExists(updateReqVO.getId());
        // 更新
        TestingCapabilityDO updateObj = BeanUtils.toBean(updateReqVO, TestingCapabilityDO.class);
        testingCapabilityMapper.updateById(updateObj);
    }

    @Override
    public void deleteTestingCapability(Long id) {
        // 校验存在
        validateTestingCapabilityExists(id);
        // 删除
        testingCapabilityMapper.deleteById(id);
    }

    private void validateTestingCapabilityExists(Long id) {
        if (testingCapabilityMapper.selectById(id) == null) {
            throw exception(TESTING_CAPABILITY_NOT_EXISTS);
        }
    }

    @Override
    public TestingCapabilityDO getTestingCapability(Long id) {
        return testingCapabilityMapper.selectById(id);
    }

    @Override
    public PageResult<TestingCapabilityDO> getTestingCapabilityPage(TestingCapabilityPageReqVO pageReqVO) {
        return testingCapabilityMapper.selectPage(pageReqVO);
    }

}