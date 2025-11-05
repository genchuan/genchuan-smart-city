package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompinfo.MonCompInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件信息 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompInfoService {

    /**
     * 创建监测部件信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompInfo(@Valid MonCompInfoSaveReqVO createReqVO);

    /**
     * 更新监测部件信息
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompInfo(@Valid MonCompInfoSaveReqVO updateReqVO);

    /**
     * 删除监测部件信息
     *
     * @param id 编号
     */
    void deleteMonCompInfo(Long id);

    /**
     * 获得监测部件信息
     *
     * @param id 编号
     * @return 监测部件信息
     */
    MonCompInfoDO getMonCompInfo(Long id);

    /**
     * 获得监测部件信息分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件信息分页
     */
    PageResult<MonCompInfoDO> getMonCompInfoPage(MonCompInfoPageReqVO pageReqVO);

}