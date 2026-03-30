package cn.iocoder.yudao.module.waterdetection.service.testresult;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testresult.TestResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.testresult.TestResultMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 检测结果录入 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TestResultServiceImpl implements TestResultService {

    @Resource
    private TestResultMapper testResultMapper;

    @Override
    public Long createTestResult(TestResultSaveReqVO createReqVO) {
        // 插入
        TestResultDO testResult = BeanUtils.toBean(createReqVO, TestResultDO.class);
        testResultMapper.insert(testResult);
        // 返回
        return testResult.getId();
    }

    @Override
    public void updateTestResult(TestResultSaveReqVO updateReqVO) {
        // 校验存在
        validateTestResultExists(updateReqVO.getId());
        // 更新
        TestResultDO updateObj = BeanUtils.toBean(updateReqVO, TestResultDO.class);
        testResultMapper.updateById(updateObj);
    }

    @Override
    public void deleteTestResult(Long id) {
        // 校验存在
        validateTestResultExists(id);
        // 删除
        testResultMapper.deleteById(id);
    }

    private void validateTestResultExists(Long id) {
        if (testResultMapper.selectById(id) == null) {
            throw exception(TEST_RESULT_NOT_EXISTS);
        }
    }

    @Override
    public TestResultDO getTestResult(Long id) {
        return testResultMapper.selectById(id);
    }

    @Override
    public PageResult<TestResultDO> getTestResultPage(TestResultPageReqVO pageReqVO) {
        return testResultMapper.selectPage(pageReqVO);
    }

}