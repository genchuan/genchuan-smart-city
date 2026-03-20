package cn.iocoder.yudao.module.kitchen.service.punishnotice;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.PunishNoticeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.add.AddPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo.template.DraftPunishNoticeReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishnotice.PunishNoticeDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.http.ResponseEntity;

/**
 * 处罚通知书 Service 接口
 *
 * @author 亘川智城
 */
public interface PunishNoticeService {

    /**
     * 创建处罚通知书
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPunishNotice(@Valid PunishNoticeSaveReqVO createReqVO);

    /**
     * 更新处罚通知书
     *
     * @param updateReqVO 更新信息
     */
    void updatePunishNotice(@Valid PunishNoticeSaveReqVO updateReqVO);

    /**
     * 删除处罚通知书
     *
     * @param id 编号
     */
    void deletePunishNotice(Long id);

    /**
     * 获得处罚通知书
     *
     * @param id 编号
     * @return 处罚通知书
     */
    PunishNoticeDO getPunishNotice(Long id);

    /**
     * 获得处罚通知书分页
     *
     * @param pageReqVO 分页查询
     * @return 处罚通知书分页
     */
    PageResult<PunishNoticeDO> getPunishNoticePage(PunishNoticePageReqVO pageReqVO);

    Long addPunishNotice(AddPunishNoticeReq reqVO);

    String generatePunishNoticeDraft(DraftPunishNoticeReq reqVO);

    ResponseEntity<byte[]> downloadRectifyNoticePdf(Long punishNoticeId);
}
