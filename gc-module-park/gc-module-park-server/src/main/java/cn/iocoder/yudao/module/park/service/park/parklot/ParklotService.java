package cn.iocoder.yudao.module.park.service.park.parklot;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotCreateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotUpdateReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.parkLot.ParkLotDO;
import jakarta.validation.Valid;

public interface ParklotService {
    /**
     * 获得停车场分页接口
     *
     * @param pageReqVO 分页查询
     * @return 畅停卡分页
     */
    PageResult<ParkLotDO> getPageData(ParkLotPageReqVO pageReqVO);



    /**
     * 创建停车场接口
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createParkLot(@Valid ParkLotCreateReqVO parkLotCreateReqVO);
    String updateParkLot(@Valid ParkLotUpdateReqVO parkLotUpdateReqVO );

    int deleteById(@Valid Long id);
}


