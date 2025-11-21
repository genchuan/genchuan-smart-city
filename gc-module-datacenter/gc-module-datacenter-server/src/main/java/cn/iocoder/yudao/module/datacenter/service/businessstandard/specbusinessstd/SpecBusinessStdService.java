package cn.iocoder.yudao.module.datacenter.service.businessstandard.specbusinessstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.specbusinessstd.SpecBusinessStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 专项业务标准 Service 接口
 *
 * @author 亘川智城
 */
public interface SpecBusinessStdService {

    /**
     * 创建专项业务标准
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSpecBusinessStd(@Valid SpecBusinessStdSaveReqVO createReqVO);

    /**
     * 更新专项业务标准
     *
     * @param updateReqVO 更新信息
     */
    void updateSpecBusinessStd(@Valid SpecBusinessStdSaveReqVO updateReqVO);

    /**
     * 删除专项业务标准
     *
     * @param id 编号
     */
    void deleteSpecBusinessStd(Long id);

    /**
     * 获得专项业务标准
     *
     * @param id 编号
     * @return 专项业务标准
     */
    SpecBusinessStdDO getSpecBusinessStd(Long id);

    /**
     * 获得专项业务标准分页
     *
     * @param pageReqVO 分页查询
     * @return 专项业务标准分页
     */
    PageResult<SpecBusinessStdDO> getSpecBusinessStdPage(SpecBusinessStdPageReqVO pageReqVO);

}