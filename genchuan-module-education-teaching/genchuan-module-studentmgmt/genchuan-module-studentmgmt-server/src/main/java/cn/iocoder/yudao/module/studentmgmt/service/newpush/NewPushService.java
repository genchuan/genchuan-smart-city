package cn.iocoder.yudao.module.studentmgmt.service.newpush;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.newpush.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.newpush.NewPushDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 迎新推送 Service 接口
 *
 * @author 芋道源码
 */
public interface NewPushService {

    /**
     * 创建迎新推送
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNewPush(@Valid NewPushSaveReqVO createReqVO);

    /**
     * 更新迎新推送
     *
     * @param updateReqVO 更新信息
     * @return
     */
    boolean updateNewPush(@Valid NewPushUpdateReqVO updateReqVO);

    /**
     * 删除迎新推送
     *
     * @param id 编号
     */
    void deleteNewPush(Long id);

    /**
    * 批量删除迎新推送
    *
    * @param ids 编号
    */
    void deleteNewPushListByIds(List<Long> ids);

    /**
     * 获得迎新推送
     *
     * @param id 编号
     * @return 迎新推送
     */
    NewPushDO getNewPush(Long id);

    /**
     * 获得迎新推送分页
     *
     * @param pageReqVO 分页查询
     * @return 迎新推送分页
     */
    PageResult<NewPushDO> getNewPushPage(NewPushPageReqVO pageReqVO);

    Boolean config(@Valid NewPushConfigReqVO reqVO);

    Boolean push(@Valid NewPushPushReqVO reqVO);

    NewPushChartRespVO chart(@Valid NewPushChartReqVO reqVO);

    NewPushIndexRespVO pushIndex(@Valid NewPushChartReqVO reqVO);
}