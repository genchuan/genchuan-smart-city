package cn.iocoder.yudao.module.facility.service.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageRespVO;

public interface ManholeCoverWarnService {

    PageResult<ManholeCoverWarnPageRespVO> getWarnPage(ManholeCoverWarnPageReqVO reqVO);

}