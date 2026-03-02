package cn.iocoder.yudao.module.evaluate.service.platformreport;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.platformreport.PlatformReportDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 平台上报 Service 接口
 *
 * @author 亘川智城
 */
public interface PlatformReportService {

    /**
     * 创建平台上报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlatformReport(@Valid PlatformReportSaveReqVO createReqVO);

    /**
     * 更新平台上报
     *
     * @param updateReqVO 更新信息
     */
    void updatePlatformReport(@Valid PlatformReportSaveReqVO updateReqVO);

    /**
     * 删除平台上报
     *
     * @param id 编号
     */
    void deletePlatformReport(Long id);

    /**
     * 获得平台上报
     *
     * @param id 编号
     * @return 平台上报
     */
    PlatformReportDO getPlatformReport(Long id);

    /**
     * 获得平台上报分页
     *
     * @param pageReqVO 分页查询
     * @return 平台上报分页
     */
    PageResult<PlatformReportDO> getPlatformReportPage(PlatformReportPageReqVO pageReqVO);

}