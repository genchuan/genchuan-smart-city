package cn.iocoder.yudao.module.smartcity.service.pointinfo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pointinfo.PointInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测点位 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PointInfoService {

    /**
     * 创建监测点位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPointInfo(@Valid PointInfoSaveReqVO createReqVO);

    /**
     * 更新监测点位
     *
     * @param updateReqVO 更新信息
     */
    void updatePointInfo(@Valid PointInfoSaveReqVO updateReqVO);

    /**
     * 删除监测点位
     *
     * @param id 编号
     */
    void deletePointInfo(Long id);

    /**
     * 获得监测点位
     *
     * @param id 编号
     * @return 监测点位
     */
    PointInfoDO getPointInfo(Long id);

    /**
     * 获得监测点位分页
     *
     * @param pageReqVO 分页查询
     * @return 监测点位分页
     */
    PageResult<PointInfoDO> getPointInfoPage(PointInfoPageReqVO pageReqVO);

}