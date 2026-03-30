package cn.iocoder.yudao.module.waterdetection.service.testprogress;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testprogress.TestProgressDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.testprogress.TestProgressMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 检测进度跟踪 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TestProgressServiceImpl implements TestProgressService {

    @Resource
    private TestProgressMapper testProgressMapper;

    @Override
    public Long createTestProgress(TestProgressSaveReqVO createReqVO) {
        // 插入
        TestProgressDO testProgress = BeanUtils.toBean(createReqVO, TestProgressDO.class);
        testProgressMapper.insert(testProgress);
        // 返回
        return testProgress.getId();
    }

    @Override
    public void updateTestProgress(TestProgressSaveReqVO updateReqVO) {
        // 校验存在
        validateTestProgressExists(updateReqVO.getId());
        // 更新
        TestProgressDO updateObj = BeanUtils.toBean(updateReqVO, TestProgressDO.class);
        testProgressMapper.updateById(updateObj);
    }

    @Override
    public void deleteTestProgress(Long id) {
        // 校验存在
        validateTestProgressExists(id);
        // 删除
        testProgressMapper.deleteById(id);
    }

    private void validateTestProgressExists(Long id) {
        if (testProgressMapper.selectById(id) == null) {
            throw exception(TEST_PROGRESS_NOT_EXISTS);
        }
    }

    @Override
    public TestProgressDO getTestProgress(Long id) {
        return testProgressMapper.selectById(id);
    }

    @Override
    public PageResult<TestProgressDO> getTestProgressPage(TestProgressPageReqVO pageReqVO) {
        return testProgressMapper.selectPage(pageReqVO);
    }

}