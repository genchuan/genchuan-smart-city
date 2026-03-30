package cn.iocoder.yudao.module.kitchen.service.rectifynotice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifynotice.vo.RectifyNoticeUpdateReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifynotice.RectifyNoticeDO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * 整改通知书 Service 接口
 *
 * @author 亘川智城
 */
public interface RectifyNoticeService {

    /**
     * 创建整改通知书
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRectifyNotice(@Valid RectifyNoticeSaveReqVO createReqVO);

    /**
     * 更新整改通知书
     *
     * @param updateReqVO 更新信息
     */
    void updateRectifyNotice(@Valid RectifyNoticeUpdateReqVO updateReqVO);

    /**
     * 删除整改通知书
     *
     * @param id 编号
     */
    void deleteRectifyNotice(Long id);

    /**
     * 获得整改通知书
     *
     * @param id 编号
     * @return 整改通知书
     */
    RectifyNoticeDO getRectifyNotice(Long id);

    /**
     * 获得整改通知书分页
     *
     * @param pageReqVO 分页查询
     * @return 整改通知书分页
     */
    PageResult<RectifyNoticeDO> getRectifyNoticePage(RectifyNoticePageReqVO pageReqVO);

    ResponseEntity<byte[]> downloadRectifyNoticePdf(Long rectifyNoticeId) throws IOException;
}
