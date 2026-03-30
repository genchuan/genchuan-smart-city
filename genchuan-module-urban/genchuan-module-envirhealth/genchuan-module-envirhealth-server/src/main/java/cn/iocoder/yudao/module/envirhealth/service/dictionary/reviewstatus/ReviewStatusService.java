package cn.iocoder.yudao.module.envirhealth.service.dictionary.reviewstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.reviewstatus.vo.ReviewStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.reviewstatus.vo.ReviewStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ReviewStatusDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 审核状态字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface ReviewStatusService {

    /**
     * 创建审核状态字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReviewStatus(@Valid ReviewStatusSaveReqVO createReqVO);

    /**
     * 更新审核状态字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateReviewStatus(@Valid ReviewStatusSaveReqVO updateReqVO);

    /**
     * 删除审核状态字典表
     *
     * @param id 编号
     */
    void deleteReviewStatus(Long id);

    /**
     * 获得审核状态字典表
     *
     * @param id 编号
     * @return 审核状态字典表
     */
    ReviewStatusDO getReviewStatus(Long id);

    /**
     * 获得审核状态字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 审核状态字典表分页
     */
    PageResult<ReviewStatusDO> getReviewStatusPage(ReviewStatusPageReqVO pageReqVO);

    /**
     * 获得审核状态下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getReviewStatusOptions();
}