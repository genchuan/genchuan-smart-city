package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonidx;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonidx.BasicMonIdxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测指标 Service 接口
 *
 * @author zcq
 */
public interface BasicMonIdxService {

    /**
     * 创建基础监测指标
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonIdx(@Valid BasicMonIdxSaveReqVO createReqVO);

    /**
     * 更新基础监测指标
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonIdx(@Valid BasicMonIdxSaveReqVO updateReqVO);

    /**
     * 删除基础监测指标
     *
     * @param id 编号
     */
    void deleteBasicMonIdx(Long id);

    /**
     * 获得基础监测指标
     *
     * @param id 编号
     * @return 基础监测指标
     */
    BasicMonIdxDO getBasicMonIdx(Long id);

    /**
     * 获得基础监测指标分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测指标分页
     */
    PageResult<BasicMonIdxDO> getBasicMonIdxPage(BasicMonIdxPageReqVO pageReqVO);

}