package cn.iocoder.yudao.module.datacenter.service.monevtinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtinfo.MonEvtInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测事件信息 Service 接口
 *
 * @author 亘川智城
 */
public interface MonEvtInfoService {

    /**
     * 创建监测事件信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonEvtInfo(@Valid MonEvtInfoSaveReqVO createReqVO);

    /**
     * 更新监测事件信息
     *
     * @param updateReqVO 更新信息
     */
    void updateMonEvtInfo(@Valid MonEvtInfoSaveReqVO updateReqVO);

    /**
     * 删除监测事件信息
     *
     * @param id 编号
     */
    void deleteMonEvtInfo(Long id);

    /**
     * 获得监测事件信息
     *
     * @param id 编号
     * @return 监测事件信息
     */
    MonEvtInfoDO getMonEvtInfo(Long id);

    /**
     * 获得监测事件信息分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件信息分页
     */
    PageResult<MonEvtInfoDO> getMonEvtInfoPage(MonEvtInfoPageReqVO pageReqVO);

}