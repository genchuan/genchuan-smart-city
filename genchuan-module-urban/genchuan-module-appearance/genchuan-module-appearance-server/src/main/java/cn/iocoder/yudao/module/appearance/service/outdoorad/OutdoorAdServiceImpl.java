package cn.iocoder.yudao.module.appearance.service.outdoorad;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad.OutdoorAdMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 户外广告 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OutdoorAdServiceImpl implements OutdoorAdService {

    @Resource
    private OutdoorAdMapper outdoorAdMapper;

    @Override
    public PageResult<OutdoorAdDO> getOutdoorAdPage( OutdoorAdPageReqVO pageReqVO) {
        Page<OutdoorAdDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 直接调用自定义分页方法，传入 reqVO
        IPage<OutdoorAdDO> pageResult = outdoorAdMapper.selectPageVO(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public OutdoorAdDO getOutdoorAd( OutdoorAdGetReqVO getReqVO) {
        return outdoorAdMapper.selectOneWithRelations(getReqVO);
    }

    @Override
    public OutdoorAdDO addOutdoorAd( OutdoorAdAddReqVO addReqVO ) {
        // 插入
        OutdoorAdDO outdoorAd = BeanUtils.toBean(addReqVO, OutdoorAdDO.class);

        String outdoorAdId = IdUtil.fastUUID();
        outdoorAd.setOutdoorAdId(outdoorAdId);

        outdoorAd.setAreaId(outdoorAdMapper.getAreaIdByCode(outdoorAd.getAreaCode()));
        outdoorAd.setGridId(outdoorAdMapper.getGridIdByCode(outdoorAd.getGridCode()));

        String adCode = generateAdCode();
        outdoorAd.setAdCode(adCode);

        outdoorAd.setDataStatus(0L);
        outdoorAd.setApprovalStatus("待审批");

        String chainHash = generateChainHash();
        outdoorAd.setChainHash(chainHash);

        outdoorAdMapper.insert(outdoorAd);
        // 返回
        return outdoorAd;
    }

    @Override
    public Boolean editOutdoorAd( OutdoorAdEditReqVO editReqVO ) {
        // 更新
        OutdoorAdDO outdoorAd = BeanUtils.toBean(editReqVO, OutdoorAdDO.class);

        outdoorAd.setId(outdoorAdMapper.getIdByOutdoorAdId(outdoorAd.getOutdoorAdId()));

        outdoorAd.setAreaId(outdoorAdMapper.getAreaIdByCode(outdoorAd.getAreaCode()));
        outdoorAd.setGridId(outdoorAdMapper.getGridIdByCode(outdoorAd.getGridCode()));

        String chainHash = generateChainHash();
        outdoorAd.setChainHash(chainHash);

        int rows = outdoorAdMapper.updateById(outdoorAd);
        // 返回
        return rows > 0;
    }

    @Override
    public Boolean removeOutdoorAd( OutdoorAdRemoveReqVO removeReqVO ) {
        // 删除
        OutdoorAdDO outdoorAd = BeanUtils.toBean(removeReqVO, OutdoorAdDO.class);

        outdoorAd.setId(outdoorAdMapper.getIdByOutdoorAdId(outdoorAd.getOutdoorAdId()));

        int rows = outdoorAdMapper.deleteById(outdoorAd.getId());
        // 返回
        return rows > 0;
    }

    @Override
    public PageResult<OutdoorAdOrderDO> getOutdoorAdOrderPage( OutdoorAdOrderPageReqVO pageReqVO ) {
        Page<OutdoorAdOrderDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 直接调用自定义分页方法，传入 reqVO
        IPage<OutdoorAdOrderDO> pageResult = outdoorAdMapper.selectOrderPageVO(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public OutdoorAdOrderDO getOutdoorAdOrder( OutdoorAdOrderGetReqVO getReqVO ) {
        return outdoorAdMapper.selectOneOrder(getReqVO);
    }


    /**
     * 生成广告编码
     * 格式：AD-YYYYMM-xxxxxx (YYYYMM为年月，xxxxxx为6位随机数)
     */
    private String generateAdCode() {
        String yearMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        int randomNum = new Random().nextInt(999999); // 生成 0-999998 的随机数
        return String.format("AD-%s-%06d", yearMonth, randomNum);
    }

    /**
     * 生成区块链存证哈希（模拟）
     * 实际应调用区块链服务，这里使用 UUID 模拟唯一哈希值
     */
    private String generateChainHash() {
        return IdUtil.fastSimpleUUID(); // 返回32位无横线UUID
    }
}