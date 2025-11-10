package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.explib;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.explib.ExpLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 经验库 Service 接口
 *
 * @author 亘川智城
 */
public interface ExpLibService {

    /**
     * 创建经验库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExpLib(@Valid ExpLibSaveReqVO createReqVO);

    /**
     * 更新经验库
     *
     * @param updateReqVO 更新信息
     */
    void updateExpLib(@Valid ExpLibSaveReqVO updateReqVO);

    /**
     * 删除经验库
     *
     * @param id 编号
     */
    void deleteExpLib(Long id);

    /**
     * 获得经验库
     *
     * @param id 编号
     * @return 经验库
     */
    ExpLibDO getExpLib(Long id);

    /**
     * 获得经验库分页
     *
     * @param pageReqVO 分页查询
     * @return 经验库分页
     */
    PageResult<ExpLibDO> getExpLibPage(ExpLibPageReqVO pageReqVO);

}