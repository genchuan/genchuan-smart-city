package cn.iocoder.yudao.module.envirhealth.service.dictionary.checkresult;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult.vo.CheckResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.checkresult.vo.CheckResultSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.CheckResultDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

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

    /**
     * 获得核查结果下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getCheckResultOptions();
}