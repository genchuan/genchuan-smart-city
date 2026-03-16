package cn.iocoder.yudao.module.envirhealth.service.urbanvillage.reviewresult;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.ReviewResultDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 复核结果字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface ReviewResultService {

    /**
     * 创建复核结果字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReviewResult(@Valid ReviewResultSaveReqVO createReqVO);

    /**
     * 更新复核结果字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateReviewResult(@Valid ReviewResultSaveReqVO updateReqVO);

    /**
     * 删除复核结果字典表
     *
     * @param id 编号
     */
    void deleteReviewResult(Long id);

    /**
     * 获得复核结果字典表
     *
     * @param id 编号
     * @return 复核结果字典表
     */
    ReviewResultDO getReviewResult(Long id);

    /**
     * 获得复核结果字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 复核结果字典表分页
     */
    PageResult<ReviewResultDO> getReviewResultPage(ReviewResultPageReqVO pageReqVO);

    /**
     * 获得复核结果下拉框选项
     * @return 下拉框选项列表
     */
    List<ReviewResultOptionVO> getReviewResultOptions();
}