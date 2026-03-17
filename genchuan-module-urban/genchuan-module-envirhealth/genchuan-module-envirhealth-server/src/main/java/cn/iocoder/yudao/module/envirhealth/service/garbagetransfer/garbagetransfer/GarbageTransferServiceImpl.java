package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagetransfer.GarbageTransferCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.GARBAGE_TRANSFER_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_NAME_EXISTS;

/**
 * 垃圾转运站 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageTransferServiceImpl implements GarbageTransferService {

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Resource
    private GarbageTransferCodeGenerator codeGenerator;

    @Override
    public Long createGarbageTransfer(GarbageTransferSaveReqVO createReqVO) {
        // 1. 校验名称是否已存在
        validateGarbageTransferNameDuplicate(null, createReqVO.getName());

        // 2. 插入
        GarbageTransferDO garbageTransfer = BeanUtils.toBean(createReqVO, GarbageTransferDO.class);
        garbageTransfer.setTransferId(codeGenerator.generateTransferId());


        // 设置 equipment_ids 默认值为空 JSON 数组
        if (garbageTransfer.getEquipmentIds() == null || garbageTransfer.getEquipmentIds().isEmpty()) {
            garbageTransfer.setEquipmentIds("[]"); // 空 JSON 数组
        }
        if (garbageTransfer.getEnvironmentData() == null || garbageTransfer.getEnvironmentData().isEmpty()) {
            garbageTransfer.setEnvironmentData("[]"); // 空 JSON 数组
        }

        garbageTransferMapper.insert(garbageTransfer);

        // 3. 返回
        return garbageTransfer.getId();
    }

    @Override
    public void updateGarbageTransfer(GarbageTransferSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageTransferExists(updateReqVO.getId());
        validateGarbageTransferNameDuplicate(updateReqVO.getId(), updateReqVO.getName());

        // 更新
        GarbageTransferDO updateObj = BeanUtils.toBean(updateReqVO, GarbageTransferDO.class);

        // 设置 equipment_ids 默认值为空 JSON 数组
        if (updateObj.getEquipmentIds() == null || updateObj.getEquipmentIds().isEmpty()) {
            updateObj.setEquipmentIds("[]"); // 空 JSON 数组
        }
        if (updateObj.getEnvironmentData() == null || updateObj.getEnvironmentData().isEmpty()) {
            updateObj.setEnvironmentData("[]"); // 空 JSON 数组
        }

        garbageTransferMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageTransfer(Long id) {
        // 校验存在
        validateGarbageTransferExists(id);
        // 删除
        garbageTransferMapper.deleteById(id);
    }

    private void validateGarbageTransferExists(Long id) {
        if (garbageTransferMapper.selectById(id) == null) {
            throw exception(GARBAGE_TRANSFER_NOT_EXISTS);
        }
    }

    /**
     * 校验转运站名称是否重复
     * @param id 当前ID（更新时使用）
     * @param name 转运站名称
     */
    private void validateGarbageTransferNameDuplicate(Long id, String name) {
        // 查询是否存在同名记录
        GarbageTransferDO existing = garbageTransferMapper.selectOne(
                GarbageTransferDO::getName, name
        );

        // 如果存在同名记录
        if (existing != null) {
            // 如果是更新操作，且同名记录就是当前记录，则允许通过
            if (id != null && existing.getId().equals(id)) {
                return;
            }
            // 否则抛出异常
            throw new ServiceException(TRANSFER_NAME_EXISTS);
        }
    }

    @Override
    public GarbageTransferDO getGarbageTransfer(Long id) {
        return garbageTransferMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageTransferDO> getGarbageTransferPage(GarbageTransferPageReqVO pageReqVO) {
        return garbageTransferMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageTransferDetailDO> getGarbageTransferDetailPage(GarbageTransferPageReqVO pageReqVO) {
        Long total = garbageTransferMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageTransferDetailDO> list = garbageTransferMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public GarbageTransferDashboardRespVO getDashboardStats() {
        GarbageTransferDashboardRespVO resp = new GarbageTransferDashboardRespVO();

        // 1. 卡片数据
        resp.setTotalStations(garbageTransferMapper.selectTotalStations());
        resp.setNormalOperationCount(garbageTransferMapper.selectNormalOperationCount());
        resp.setEquipmentNormalCount(garbageTransferMapper.selectEquipmentNormalCount());
        resp.setEnvironmentStandardCount(garbageTransferMapper.selectEnvironmentStandardCount());

        // 2. 圆环图数据 - 运营状态分布
        resp.setOperationStatusDistribution(garbageTransferMapper.selectOperationStatusPie());

        // 3. 圆环图数据 - 区域分布
        resp.setAreaDistribution(garbageTransferMapper.selectAreaPie());

        // 4. 柱状图数据 - 日转运量对比
        resp.setDailyTransferVolumeComparison(garbageTransferMapper.selectDailyTransferVolumeBar());

        // 5. 折线图数据 - 近7日环境指标变化趋势
//        resp.setEnvironmentTrend7Days(garbageTransferMapper.selectEnvironmentTrend7Days());

        return resp;
    }

}