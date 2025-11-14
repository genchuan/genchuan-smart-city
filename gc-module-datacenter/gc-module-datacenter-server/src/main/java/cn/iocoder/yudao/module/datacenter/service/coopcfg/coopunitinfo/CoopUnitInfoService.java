package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopunitinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopunitinfo.CoopUnitInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 联动单位信息 Service 接口
 *
 * @author 亘川智城
 */
public interface CoopUnitInfoService {

    /**
     * 创建联动单位信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoopUnitInfo(@Valid CoopUnitInfoSaveReqVO createReqVO);

    /**
     * 更新联动单位信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCoopUnitInfo(@Valid CoopUnitInfoSaveReqVO updateReqVO);

    /**
     * 删除联动单位信息
     *
     * @param id 编号
     */
    void deleteCoopUnitInfo(Long id);

    /**
     * 获得联动单位信息
     *
     * @param id 编号
     * @return 联动单位信息
     */
    CoopUnitInfoDO getCoopUnitInfo(Long id);

    /**
     * 获得联动单位信息分页
     *
     * @param pageReqVO 分页查询
     * @return 联动单位信息分页
     */
    PageResult<CoopUnitInfoDO> getCoopUnitInfoPage(CoopUnitInfoPageReqVO pageReqVO);

}