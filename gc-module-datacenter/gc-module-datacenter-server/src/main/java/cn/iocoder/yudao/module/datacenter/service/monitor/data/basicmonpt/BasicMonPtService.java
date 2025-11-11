package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonpt.BasicMonPtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测点位 Service 接口
 *
 * @author zcq
 */
public interface BasicMonPtService {

    /**
     * 创建基础监测点位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonPt(@Valid BasicMonPtSaveReqVO createReqVO);

    /**
     * 更新基础监测点位
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonPt(@Valid BasicMonPtSaveReqVO updateReqVO);

    /**
     * 删除基础监测点位
     *
     * @param id 编号
     */
    void deleteBasicMonPt(Long id);

    /**
     * 获得基础监测点位
     *
     * @param id 编号
     * @return 基础监测点位
     */
    BasicMonPtDO getBasicMonPt(Long id);

    /**
     * 获得基础监测点位分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测点位分页
     */
    PageResult<BasicMonPtDO> getBasicMonPtPage(BasicMonPtPageReqVO pageReqVO);

}