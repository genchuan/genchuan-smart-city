package cn.iocoder.yudao.module.datacenter.service.polcomplianceck;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.polcomplianceck.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.polcomplianceck.PolComplianceCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 政策合规校验 Service 接口
 *
 * @author 亘川智城
 */
public interface PolComplianceCkService {

    /**
     * 创建政策合规校验
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPolComplianceCk(@Valid PolComplianceCkSaveReqVO createReqVO);

    /**
     * 更新政策合规校验
     *
     * @param updateReqVO 更新信息
     */
    void updatePolComplianceCk(@Valid PolComplianceCkSaveReqVO updateReqVO);

    /**
     * 删除政策合规校验
     *
     * @param id 编号
     */
    void deletePolComplianceCk(Long id);

    /**
     * 获得政策合规校验
     *
     * @param id 编号
     * @return 政策合规校验
     */
    PolComplianceCkDO getPolComplianceCk(Long id);

    /**
     * 获得政策合规校验分页
     *
     * @param pageReqVO 分页查询
     * @return 政策合规校验分页
     */
    PageResult<PolComplianceCkDO> getPolComplianceCkPage(PolComplianceCkPageReqVO pageReqVO);

}