package cn.iocoder.yudao.module.envirhealth.service.market.checkresult;

import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.checkresult.CheckResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.checkresult.CheckResultSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.CheckResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 核查结果字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface CheckResultService {

    /**
     * 创建核查结果字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCheckResult(@Valid CheckResultSaveReqVO createReqVO);

    /**
     * 更新核查结果字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateCheckResult(@Valid CheckResultSaveReqVO updateReqVO);

    /**
     * 删除核查结果字典表
     *
     * @param id 编号
     */
    void deleteCheckResult(Long id);

    /**
     * 获得核查结果字典表
     *
     * @param id 编号
     * @return 核查结果字典表
     */
    CheckResultDO getCheckResult(Long id);

    /**
     * 获得核查结果字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 核查结果字典表分页
     */
    PageResult<CheckResultDO> getCheckResultPage(CheckResultPageReqVO pageReqVO);

}