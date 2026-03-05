package cn.iocoder.yudao.module.evaluate.service.indexsystem;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 指标体系 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexSystemService {

    /**
     * 创建指标体系
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexSystem(@Valid IndexSystemSaveReqVO createReqVO);

    /**
     * 更新指标体系
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexSystem(@Valid IndexSystemSaveReqVO updateReqVO);

    /**
     * 删除指标体系
     *
     * @param id 编号
     */
    void deleteIndexSystem(Long id);

    /**
     * 获得指标体系
     *
     * @param id 编号
     * @return 指标体系
     */
    IndexSystemDO getIndexSystem(Long id);

    /**
     * 获得指标体系分页
     *
     * @param pageReqVO 分页查询
     * @return 指标体系分页
     */
    PageResult<IndexSystemDO> getIndexSystemPage(IndexSystemPageReqVO pageReqVO);

    PageResult<IndexSystemPageItemVO> getIndexSystemPageWithJoin(IndexSystemPageReqVO pageReqVO);

    @Transactional(rollbackFor = Exception.class)
    IndexSystemDetailVO getIndexSystemDetail(String systemId);

    WeightCheckRespVO checkWeight(WeightCheckReqVO reqVO);

    //----------------------xin-------------------
    PageResult<IndexSystemRespVO> getIndexSystemJoinPage(IndexSystemPageReqVO reqVO);

    IndexSystemRespVO getStatusCount(Integer statusId);
}