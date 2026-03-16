package cn.iocoder.yudao.module.park.service.park.resource.parkinglotinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.parkinglotinfo.ParkingLotInfoDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.resource.parkinglotinfo.ParkingLotInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.ING_LOT_INFO_NOT_EXISTS;

/**
 * 停车场信息管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkingLotInfoServiceImpl implements ParkingLotInfoService {

    @Resource
    private ParkingLotInfoMapper ingLotInfoMapper;

    @Override
    public Long createingLotInfo(ParkingLotInfoSaveReqVO createReqVO) {
        // 插入
        ParkingLotInfoDO ingLotInfo = BeanUtils.toBean(createReqVO, ParkingLotInfoDO.class);
        ingLotInfoMapper.insert(ingLotInfo);
        // 返回
        return ingLotInfo.getId();
    }

    @Override
    public void updateingLotInfo(ParkingLotInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateingLotInfoExists(updateReqVO.getId());
        // 更新
        ParkingLotInfoDO updateObj = BeanUtils.toBean(updateReqVO, ParkingLotInfoDO.class);
        ingLotInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteingLotInfo(Long id) {
        // 校验存在
        validateingLotInfoExists(id);
        // 删除
        ingLotInfoMapper.deleteById(id);
    }

    private void validateingLotInfoExists(Long id) {
        if (ingLotInfoMapper.selectById(id) == null) {
            throw exception(ING_LOT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public ParkingLotInfoDO getingLotInfo(Long id) {
        return ingLotInfoMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingLotInfoDO> getingLotInfoPage(ParkingLotInfoPageReqVO pageReqVO) {
        return ingLotInfoMapper.selectPage(pageReqVO);
    }

}