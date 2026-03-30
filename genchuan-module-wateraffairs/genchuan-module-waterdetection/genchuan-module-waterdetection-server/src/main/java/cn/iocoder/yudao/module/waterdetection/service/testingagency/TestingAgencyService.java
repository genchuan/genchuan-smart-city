package cn.iocoder.yudao.module.waterdetection.service.testingagency;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.testingagency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingagency.TestingAgencyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 检测机构资质管理 Service 接口
 *
 * @author zcq
 */
public interface TestingAgencyService {

    /**
     * 创建检测机构资质管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTestingAgency(@Valid TestingAgencySaveReqVO createReqVO);

    /**
     * 更新检测机构资质管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTestingAgency(@Valid TestingAgencySaveReqVO updateReqVO);

    /**
     * 删除检测机构资质管理
     *
     * @param id 编号
     */
    void deleteTestingAgency(Long id);

    /**
     * 获得检测机构资质管理
     *
     * @param id 编号
     * @return 检测机构资质管理
     */
    TestingAgencyDO getTestingAgency(Long id);

    /**
     * 获得检测机构资质管理分页
     *
     * @param pageReqVO 分页查询
     * @return 检测机构资质管理分页
     */
    PageResult<TestingAgencyDO> getTestingAgencyPage(TestingAgencyPageReqVO pageReqVO);

}