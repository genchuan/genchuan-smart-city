package cn.iocoder.yudao.module.vehiclecharging.service.sharingratio;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingratio.SharingRatioMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 分账比例 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SharingRatioServiceImpl implements SharingRatioService {

    @Resource
    private SharingRatioMapper sharingRatioMapper;

    @Override
    public Long createSharingRatio(SharingRatioCreateReqVO createReqVO) {
        // 插入
        SharingRatioDO sharingRatio = BeanUtils.toBean(createReqVO, SharingRatioDO.class);
        sharingRatioMapper.insert(sharingRatio);

        // 返回
        return sharingRatio.getId();
    }

    @Override
    public void updateSharingRatio(SharingRatioUpdateReqVO updateReqVO) {
        // 校验存在
        validateSharingRatioExists(updateReqVO.getId());
        // 更新
        SharingRatioDO updateObj = BeanUtils.toBean(updateReqVO, SharingRatioDO.class);
        sharingRatioMapper.updateById(updateObj);
    }

    @Override
    public void deleteSharingRatio(Long id) {
        // 校验存在
        validateSharingRatioExists(id);
        // 删除
        sharingRatioMapper.deleteById(id);
    }

    @Override
        public void deleteSharingRatioListByIds(List<Long> ids) {
        // 删除
        sharingRatioMapper.deleteByIds(ids);
        }


    private void validateSharingRatioExists(Long id) {
        if (sharingRatioMapper.selectById(id) == null) {
//            throw exception(SHARING_RATIO_NOT_EXISTS);
            throw new RuntimeException("500 记录不存在，id=" + id);
        }
    }

    @Override
    public SharingRatioDO getSharingRatio(Long id) {
        return sharingRatioMapper.selectById(id);
    }

    @Override
    public PageResult<SharingRatioDO> getSharingRatioPage(SharingRatioPageReqVO pageReqVO) {
        return sharingRatioMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateSharingStatus(Long id, int i) {
        // 校验存在
        validateSharingRatioExists(id);
        // 更新
        SharingRatioDO updateObj = new SharingRatioDO();
        updateObj.setId(id);
        if(i == 0) {
            updateObj.setSharingStatus("已生效");
        } else if(i == 1) {
            updateObj.setSharingStatus("已失效");
        } else {
            throw new RuntimeException("500 错误的状态");
        }
        int rows = sharingRatioMapper.updateById(updateObj);
        if (rows <= 0) {
            throw new RuntimeException("500 更新失败");
        }
    }

    @Override
    public Long copySharingRatio(Long sourceId) {
        // 1. 查询源数据（需未删除）
        SharingRatioDO source = sharingRatioMapper.selectById(sourceId);
        if (source == null || source.getDeleted()) {
            throw new RuntimeException("500 源分账方案不存在或已删除");
        }

        // 2. 复制新对象
        SharingRatioDO copy = new SharingRatioDO();
        BeanUtils.copyProperties(source, copy);

        // 3. 生成新的唯一方案编号
        copy.setSharingCode(generateSharingCode());

        // 4. 重置审计字段（createTime/updateTime由数据库自动填充，creator/updater由框架自动填充，此处不手动设置）
        // 注意：如果租户ID需要继承原租户，则保留source.getTenantId()，否则由框架自动填充当前租户

        // 5. 插入数据库
        int rows = sharingRatioMapper.insert(copy);
        if (rows <= 0) {
            throw new RuntimeException("500 复制分账方案失败");
        }

        return copy.getId();
    }

    /**
     * 生成唯一方案编号
     * 示例：SR-20250403-001
     */
    private String generateSharingCode() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 查询当日已存在的最大序号，简单起见可以使用UUID或雪花算法，这里提供序号生成示例
        String prefix = "SR-" + datePart + "-";
        LambdaQueryWrapper<SharingRatioDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SharingRatioDO::getSharingCode)
                .like(SharingRatioDO::getSharingCode, prefix)
                .orderByDesc(SharingRatioDO::getSharingCode)
                .last("LIMIT 1");
        SharingRatioDO last = sharingRatioMapper.selectOne(wrapper);
        int seq = 1;
        if (last != null && last.getSharingCode() != null) {
            String code = last.getSharingCode();
            String seqStr = code.substring(prefix.length());
            try {
                seq = Integer.parseInt(seqStr) + 1;
            } catch (NumberFormatException e) {
                seq = 1;
            }
        }
        return prefix + String.format("%03d", seq);
    }

}