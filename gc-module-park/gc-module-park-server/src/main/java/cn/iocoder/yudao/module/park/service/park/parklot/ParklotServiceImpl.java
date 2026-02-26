package cn.iocoder.yudao.module.park.service.park.parklot;


import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotCreateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotUpdateReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.parkLot.ParkLotDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.parkLot.ParkLotMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 停车场service实现
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class ParklotServiceImpl implements  ParklotService{

    @Resource
    private ParkLotMapper parkLotMapper;

    @Override
    public PageResult<ParkLotDO> getPageData(ParkLotPageReqVO pageReqVO) {
        return parkLotMapper.selectPage(pageReqVO);
    }

    @Override
    public String createParkLot(ParkLotCreateReqVO createParkLotVO) {
        // 1.1 检查车场ID是否已存在
        if (parkLotMapper.checkLotIdExists(createParkLotVO.getLotId())) {
            throw exception(new ErrorCode(500,"车场ID已存在"));
        }
        // 1.2 检查可用车位是否大于总车位
        if (createParkLotVO.getAvailableSpace() > createParkLotVO.getTotalSpace()) {
            throw exception(new ErrorCode(500,"可用车位不能能大于总车位"));
        }
        ParkLotDO parkLotDO = BeanUtils.toBean(createParkLotVO, ParkLotDO.class);
        parkLotMapper.insert(parkLotDO);
        return parkLotDO.getLotId();
    }


    @Override
    public  String updateParkLot(ParkLotUpdateReqVO parkLotUpdateReqVOReqVO) {
        // 1.1 检查车场ID是否已存在
        if (parkLotMapper.checkLotIdExists(parkLotUpdateReqVOReqVO.getLotId())) {
            // 更新
            ParkLotDO parkLotDO = BeanUtils.toBean(parkLotUpdateReqVOReqVO, ParkLotDO.class);
            parkLotMapper.updateById(parkLotDO);
            return parkLotDO.getLotId();
        } else{
            throw exception(new ErrorCode(500,"车场ID不存在"));
        }

    }
    @Override
    public  int deleteById(Long id) {
       int resultBoolean = parkLotMapper.deleteById(id);
        return  resultBoolean;
    }

}
