package cn.iocoder.yudao.module.waterdetection.service.testingagency;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingagency.TestingAgencyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.testingagency.TestingAgencyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 检测机构资质管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TestingAgencyServiceImpl implements TestingAgencyService {

    @Resource
    private TestingAgencyMapper testingAgencyMapper;

    @Override
    public Long createTestingAgency(TestingAgencySaveReqVO createReqVO) {
        // 插入
        TestingAgencyDO testingAgency = BeanUtils.toBean(createReqVO, TestingAgencyDO.class);
        testingAgencyMapper.insert(testingAgency);
        // 返回
        return testingAgency.getId();
    }

    @Override
    public void updateTestingAgency(TestingAgencySaveReqVO updateReqVO) {
        // 校验存在
        validateTestingAgencyExists(updateReqVO.getId());
        // 更新
        TestingAgencyDO updateObj = BeanUtils.toBean(updateReqVO, TestingAgencyDO.class);
        testingAgencyMapper.updateById(updateObj);
    }

    @Override
    public void deleteTestingAgency(Long id) {
        // 校验存在
        validateTestingAgencyExists(id);
        // 删除
        testingAgencyMapper.deleteById(id);
    }

    private void validateTestingAgencyExists(Long id) {
        if (testingAgencyMapper.selectById(id) == null) {
            throw exception(TESTING_AGENCY_NOT_EXISTS);
        }
    }

    @Override
    public TestingAgencyDO getTestingAgency(Long id) {
        return testingAgencyMapper.selectById(id);
    }

    @Override
    public PageResult<TestingAgencyDO> getTestingAgencyPage(TestingAgencyPageReqVO pageReqVO) {
        return testingAgencyMapper.selectPage(pageReqVO);
    }

}