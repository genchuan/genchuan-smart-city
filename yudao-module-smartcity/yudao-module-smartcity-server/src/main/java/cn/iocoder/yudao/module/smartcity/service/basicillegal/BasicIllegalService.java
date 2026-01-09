package cn.iocoder.yudao.module.smartcity.service.basicillegal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.BasicIllegalPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.BasicIllegalSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.basicillegal.BasicIllegalDO;
import jakarta.validation.Valid;

/**
 * 违建基本信息 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface BasicIllegalService {

    /**
     * 创建违建基本信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicIllegal(@Valid BasicIllegalSaveReqVO createReqVO);

    /**
     * 更新违建基本信息
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicIllegal(@Valid BasicIllegalSaveReqVO updateReqVO);

    /**
     * 删除违建基本信息
     *
     * @param id 编号
     */
    void deleteBasicIllegal(Long id);

    /**
     * 获得违建基本信息
     *
     * @param id 编号
     * @return 违建基本信息
     */
    BasicIllegalDO getBasicIllegal(Long id);

    /**
     * 获得违建基本信息分页
     *
     * @param pageReqVO 分页查询
     * @return 违建基本信息分页
     */
    PageResult<BasicIllegalDO> getBasicIllegalPage(BasicIllegalPageReqVO pageReqVO);

}