package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatinfo.EmerMatInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应急物资信息 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerMatInfoService {

    /**
     * 创建应急物资信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerMatInfo(@Valid EmerMatInfoSaveReqVO createReqVO);

    /**
     * 更新应急物资信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerMatInfo(@Valid EmerMatInfoSaveReqVO updateReqVO);

    /**
     * 删除应急物资信息
     *
     * @param id 编号
     */
    void deleteEmerMatInfo(Long id);

    /**
     * 获得应急物资信息
     *
     * @param id 编号
     * @return 应急物资信息
     */
    EmerMatInfoDO getEmerMatInfo(Long id);

    /**
     * 获得应急物资信息分页
     *
     * @param pageReqVO 分页查询
     * @return 应急物资信息分页
     */
    PageResult<EmerMatInfoDO> getEmerMatInfoPage(EmerMatInfoPageReqVO pageReqVO);

}