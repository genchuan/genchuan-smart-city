package cn.iocoder.yudao.module.waterdetection.service.testingpersonnel;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingpersonnel.TestingPersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 检测人员信息管理 Service 接口
 *
 * @author zcq
 */
public interface TestingPersonnelService {

    /**
     * 创建检测人员信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTestingPersonnel(@Valid TestingPersonnelSaveReqVO createReqVO);

    /**
     * 更新检测人员信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTestingPersonnel(@Valid TestingPersonnelSaveReqVO updateReqVO);

    /**
     * 删除检测人员信息管理
     *
     * @param id 编号
     */
    void deleteTestingPersonnel(Long id);

    /**
     * 获得检测人员信息管理
     *
     * @param id 编号
     * @return 检测人员信息管理
     */
    TestingPersonnelDO getTestingPersonnel(Long id);

    /**
     * 获得检测人员信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 检测人员信息管理分页
     */
    PageResult<TestingPersonnelDO> getTestingPersonnelPage(TestingPersonnelPageReqVO pageReqVO);

}