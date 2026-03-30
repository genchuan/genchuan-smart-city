package cn.iocoder.yudao.module.waterdetection.service.testresult;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testresult.TestResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 检测结果录入 Service 接口
 *
 * @author zcq
 */
public interface TestResultService {

    /**
     * 创建检测结果录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTestResult(@Valid TestResultSaveReqVO createReqVO);

    /**
     * 更新检测结果录入
     *
     * @param updateReqVO 更新信息
     */
    void updateTestResult(@Valid TestResultSaveReqVO updateReqVO);

    /**
     * 删除检测结果录入
     *
     * @param id 编号
     */
    void deleteTestResult(Long id);

    /**
     * 获得检测结果录入
     *
     * @param id 编号
     * @return 检测结果录入
     */
    TestResultDO getTestResult(Long id);

    /**
     * 获得检测结果录入分页
     *
     * @param pageReqVO 分页查询
     * @return 检测结果录入分页
     */
    PageResult<TestResultDO> getTestResultPage(TestResultPageReqVO pageReqVO);

}