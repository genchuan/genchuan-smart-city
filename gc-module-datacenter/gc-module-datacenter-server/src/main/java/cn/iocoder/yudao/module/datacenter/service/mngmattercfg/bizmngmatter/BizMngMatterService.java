package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatter.BizMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项信息 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngMatterService {

    /**
     * 创建管理事项信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngMatter(@Valid BizMngMatterSaveReqVO createReqVO);

    /**
     * 更新管理事项信息
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngMatter(@Valid BizMngMatterSaveReqVO updateReqVO);

    /**
     * 删除管理事项信息
     *
     * @param id 编号
     */
    void deleteBizMngMatter(Long id);

    /**
     * 获得管理事项信息
     *
     * @param id 编号
     * @return 管理事项信息
     */
    BizMngMatterDO getBizMngMatter(Long id);

    /**
     * 获得管理事项信息分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项信息分页
     */
    PageResult<BizMngMatterDO> getBizMngMatterPage(BizMngMatterPageReqVO pageReqVO);

}