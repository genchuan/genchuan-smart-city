package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicnotice;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicnotice.PublicNoticeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 通知公告发布 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicNoticeService {

    /**
     * 创建通知公告发布
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicNotice(@Valid PublicNoticeSaveReqVO createReqVO);

    /**
     * 更新通知公告发布
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicNotice(@Valid PublicNoticeSaveReqVO updateReqVO);

    /**
     * 删除通知公告发布
     *
     * @param id 编号
     */
    void deletePublicNotice(Long id);

    /**
     * 获得通知公告发布
     *
     * @param id 编号
     * @return 通知公告发布
     */
    PublicNoticeDO getPublicNotice(Long id);

    /**
     * 获得通知公告发布分页
     *
     * @param pageReqVO 分页查询
     * @return 通知公告发布分页
     */
    PageResult<PublicNoticeDO> getPublicNoticePage(PublicNoticePageReqVO pageReqVO);

}