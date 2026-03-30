package cn.iocoder.yudao.module.waterdetection.service.testingpersonnel;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingpersonnel.TestingPersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.testingpersonnel.TestingPersonnelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 检测人员信息管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class TestingPersonnelServiceImpl implements TestingPersonnelService {

    @Resource
    private TestingPersonnelMapper testingPersonnelMapper;

    @Override
    public Long createTestingPersonnel(TestingPersonnelSaveReqVO createReqVO) {
        // 插入
        TestingPersonnelDO testingPersonnel = BeanUtils.toBean(createReqVO, TestingPersonnelDO.class);
        testingPersonnelMapper.insert(testingPersonnel);
        // 返回
        return testingPersonnel.getId();
    }

    @Override
    public void updateTestingPersonnel(TestingPersonnelSaveReqVO updateReqVO) {
        // 校验存在
        validateTestingPersonnelExists(updateReqVO.getId());
        // 更新
        TestingPersonnelDO updateObj = BeanUtils.toBean(updateReqVO, TestingPersonnelDO.class);
        testingPersonnelMapper.updateById(updateObj);
    }

    @Override
    public void deleteTestingPersonnel(Long id) {
        // 校验存在
        validateTestingPersonnelExists(id);
        // 删除
        testingPersonnelMapper.deleteById(id);
    }

    private void validateTestingPersonnelExists(Long id) {
        if (testingPersonnelMapper.selectById(id) == null) {
            throw exception(TESTING_PERSONNEL_NOT_EXISTS);
        }
    }

    @Override
    public TestingPersonnelDO getTestingPersonnel(Long id) {
        return testingPersonnelMapper.selectById(id);
    }

    @Override
    public PageResult<TestingPersonnelDO> getTestingPersonnelPage(TestingPersonnelPageReqVO pageReqVO) {
        return testingPersonnelMapper.selectPage(pageReqVO);
    }

}