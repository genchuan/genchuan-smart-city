package cn.iocoder.yudao.module.datacenter.service.exchangepoint.locallevelexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.locallevelexp.LocalLevelExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 本级经验分享 Service 接口
 *
 * @author 亘川智城
 */
public interface LocalLevelExpService {

    /**
     * 创建本级经验分享
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLocalLevelExp(@Valid LocalLevelExpSaveReqVO createReqVO);

    /**
     * 更新本级经验分享
     *
     * @param updateReqVO 更新信息
     */
    void updateLocalLevelExp(@Valid LocalLevelExpSaveReqVO updateReqVO);

    /**
     * 删除本级经验分享
     *
     * @param id 编号
     */
    void deleteLocalLevelExp(Long id);

    /**
     * 获得本级经验分享
     *
     * @param id 编号
     * @return 本级经验分享
     */
    LocalLevelExpDO getLocalLevelExp(Long id);

    /**
     * 获得本级经验分享分页
     *
     * @param pageReqVO 分页查询
     * @return 本级经验分享分页
     */
    PageResult<LocalLevelExpDO> getLocalLevelExpPage(LocalLevelExpPageReqVO pageReqVO);

}