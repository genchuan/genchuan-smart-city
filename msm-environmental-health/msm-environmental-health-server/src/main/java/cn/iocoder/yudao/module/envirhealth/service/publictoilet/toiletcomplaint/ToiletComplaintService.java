package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcomplaint;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletComplaintDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 公厕投诉 Service 接口
 *
 * @author 芋道源码
 */
public interface ToiletComplaintService {

    /**
     * 创建公厕投诉
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToiletComplaint(@Valid ToiletComplaintSaveReqVO createReqVO);

    /**
     * 更新公厕投诉
     *
     * @param updateReqVO 更新信息
     */
    void updateToiletComplaint(@Valid ToiletComplaintSaveReqVO updateReqVO);

    /**
     * 删除公厕投诉
     *
     * @param id 编号
     */
    void deleteToiletComplaint(Long id);

    /**
     * 批量公厕投诉
     *
     * @param ids 编号列表
     */
    void deleteToiletComplaintBatch(List<Long> ids);

    /**
     * 获得公厕投诉
     *
     * @param id 编号
     * @return 公厕投诉
     */
    ToiletComplaintDO getToiletComplaint(Long id);

    /**
     * 获得公厕投诉分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕投诉分页
     */
    PageResult<ToiletComplaintDO> getToiletComplaintPage(ToiletComplaintPageReqVO pageReqVO);

    PageResult<ToiletComplaintDetailDO> getToiletComplaintDetailPage(ToiletComplaintPageReqVO pageReqVO);

}