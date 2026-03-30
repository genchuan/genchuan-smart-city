package cn.iocoder.yudao.module.waterdetection.service.testingcapability;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingcapability.TestingCapabilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 检测能力及设备管理 Service 接口
 *
 * @author zcq
 */
public interface TestingCapabilityService {

    /**
     * 创建检测能力及设备管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTestingCapability(@Valid TestingCapabilitySaveReqVO createReqVO);

    /**
     * 更新检测能力及设备管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTestingCapability(@Valid TestingCapabilitySaveReqVO updateReqVO);

    /**
     * 删除检测能力及设备管理
     *
     * @param id 编号
     */
    void deleteTestingCapability(Long id);

    /**
     * 获得检测能力及设备管理
     *
     * @param id 编号
     * @return 检测能力及设备管理
     */
    TestingCapabilityDO getTestingCapability(Long id);

    /**
     * 获得检测能力及设备管理分页
     *
     * @param pageReqVO 分页查询
     * @return 检测能力及设备管理分页
     */
    PageResult<TestingCapabilityDO> getTestingCapabilityPage(TestingCapabilityPageReqVO pageReqVO);

}