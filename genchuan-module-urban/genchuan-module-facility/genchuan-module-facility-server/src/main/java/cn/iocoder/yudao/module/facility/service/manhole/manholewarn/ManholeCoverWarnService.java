package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnDetailReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnDetailRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageRespVO;

public interface ManholeCoverWarnService {
    /**
     * 窨井盖预警数据分页查询
     *
     * @param reqVO 分页查询参数
     * @return 窨井盖预警数据分页列表
     */
    PageResult<ManholeCoverWarnPageRespVO> getWarnPage(ManholeCoverWarnPageReqVO reqVO);
    /**
     * 窨井盖预警数据详情查询
     *
     * @param reqVO 详情查询参数
     * @return 窨井盖预警数据详情
     */
    ManholeCoverWarnDetailRespVO getWarnDetail(ManholeCoverWarnDetailReqVO reqVO);
}