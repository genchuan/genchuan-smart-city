package cn.iocoder.yudao.module.studentmgmt.service.parentreply;

import java.util.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.parentreply.ParentReplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 家长回复 Service 接口
 *
 * @author 芋道源码
 */
public interface ParentReplyService {

    /**
     * 创建家长回复
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParentReply(@Valid ParentReplySaveReqVO createReqVO);

    /**
     * 更新家长回复
     *
     * @param updateReqVO 更新信息
     */
    void updateParentReply(@Valid ParentReplySaveReqVO updateReqVO);

    /**
     * 删除家长回复
     *
     * @param id 编号
     */
    void deleteParentReply(Long id);

    /**
    * 批量删除家长回复
    *
    * @param ids 编号
    */
    void deleteParentReplyListByIds(List<Long> ids);

    /**
     * 获得家长回复
     *
     * @param id 编号
     * @return 家长回复
     */
    ParentReplyDO getParentReply(Long id);

    /**
     * 获得家长回复分页
     *
     * @param pageReqVO 分页查询
     * @return 家长回复分页
     */
    PageResult<ParentReplyDO> getParentReplyPage(ParentReplyPageReqVO pageReqVO);

    Boolean read(@Valid ParentReplyReadReqVO reqVO);

    Boolean submit(@Valid ParentReplySubmitReqVO reqVO);

    Boolean reply(@Valid ParentReplyReplyReqVO reqVO);

    ParentReplyChartRespVO chart(@Valid BaseChartReqVO reqVO);

    ParentReplyChartIndexRespVO index(@Valid BaseChartReqVO reqVO);

}