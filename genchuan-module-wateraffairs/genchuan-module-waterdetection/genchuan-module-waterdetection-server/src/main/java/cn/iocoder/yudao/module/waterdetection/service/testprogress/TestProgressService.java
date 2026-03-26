package cn.iocoder.yudao.module.waterdetection.service.testprogress;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testprogress.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testprogress.TestProgressDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 检测进度跟踪 Service 接口
 *
 * @author zcq
 */
public interface TestProgressService {

    /**
     * 创建检测进度跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTestProgress(@Valid TestProgressSaveReqVO createReqVO);

    /**
     * 更新检测进度跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateTestProgress(@Valid TestProgressSaveReqVO updateReqVO);

    /**
     * 删除检测进度跟踪
     *
     * @param id 编号
     */
    void deleteTestProgress(Long id);

    /**
     * 获得检测进度跟踪
     *
     * @param id 编号
     * @return 检测进度跟踪
     */
    TestProgressDO getTestProgress(Long id);

    /**
     * 获得检测进度跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 检测进度跟踪分页
     */
    PageResult<TestProgressDO> getTestProgressPage(TestProgressPageReqVO pageReqVO);

}