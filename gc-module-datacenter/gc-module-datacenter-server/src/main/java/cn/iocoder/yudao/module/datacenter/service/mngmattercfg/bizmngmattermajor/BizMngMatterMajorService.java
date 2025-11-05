package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmattermajor;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmattermajor.BizMngMatterMajorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项大类 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngMatterMajorService {

    /**
     * 创建管理事项大类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngMatterMajor(@Valid BizMngMatterMajorSaveReqVO createReqVO);

    /**
     * 更新管理事项大类
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngMatterMajor(@Valid BizMngMatterMajorSaveReqVO updateReqVO);

    /**
     * 删除管理事项大类
     *
     * @param id 编号
     */
    void deleteBizMngMatterMajor(Long id);

    /**
     * 获得管理事项大类
     *
     * @param id 编号
     * @return 管理事项大类
     */
    BizMngMatterMajorDO getBizMngMatterMajor(Long id);

    /**
     * 获得管理事项大类分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项大类分页
     */
    PageResult<BizMngMatterMajorDO> getBizMngMatterMajorPage(BizMngMatterMajorPageReqVO pageReqVO);

}