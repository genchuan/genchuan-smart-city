package cn.iocoder.yudao.module.stationresource.service.stationresource.areamgmt.areainfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AddReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 片区信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AreaInfoServiceImpl implements AreaInfoService {

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public Long createAreaInfo(AreaInfoSaveReqVO createReqVO) {
        // 插入
        AreaInfoDO areaInfo = BeanUtils.toBean(createReqVO, AreaInfoDO.class);
        areaInfoMapper.insert(areaInfo);

        // 返回
        return areaInfo.getId();
    }

    @Override
    public void updateAreaInfo(AreaInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateAreaInfoExists(updateReqVO.getId());
        // 更新
        AreaInfoDO updateObj = BeanUtils.toBean(updateReqVO, AreaInfoDO.class);
        areaInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteAreaInfo(Long id) {
        // 校验存在
        validateAreaInfoExists(id);
        // 删除
        areaInfoMapper.deleteById(id);
    }

    @Override
        public void deleteAreaInfoListByIds(List<Long> ids) {
        // 删除
        areaInfoMapper.deleteByIds(ids);
        }


    private void validateAreaInfoExists(Long id) {
        if (areaInfoMapper.selectById(id) == null) {
            throw exception(AREA_INFO_NOT_EXISTS);
        }
    }

    @Override
    public AreaInfoDO getAreaInfo(Long id) {
        return areaInfoMapper.selectById(id);
    }

    @Override
    public PageResult<AreaInfoDO> getAreaInfoPage(AreaInfoPageReqVO pageReqVO) {
        return areaInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addAreaInfo(AddReq createReqVO) {
        // 插入
        AreaInfoDO areaInfo = BeanUtils.toBean(createReqVO, AreaInfoDO.class);
        areaInfo.setStatus("未生效");
        areaInfoMapper.insert(areaInfo);

        //片区编号唯一性
        LambdaQueryWrapper<AreaInfoDO> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(AreaInfoDO::getAreaNo,areaInfo.getAreaNo());
        if (areaInfoMapper.exists(wrapper)){
            throw exception("片区编号不能和数据库存在的一样");
        }
        // 返回
        return areaInfo.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportRespVO importAreaInfo(MultipartFile file) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailures(new ArrayList<>());

        try {
            // ===================== 【完整使用 VrvExcelUtils】 =====================
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    AddReq.class.getName()
            );

            // 获取实体列表
            List<AddReq> reqList = (List<AddReq>) resultMap.get("entityList");

            // 判空
            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 遍历导入
            for (int i = 0; i < reqList.size(); i++) {
                AddReq req = reqList.get(i);
                int rowIndex = i + 2; // Excel 真实行号

                try {
                    // ===================== 【复用你自己的 addAreaInfo 方法】 =====================
                    // 直接调用你写好的新增方法：自带校验、状态设置、唯一性判断
                    addAreaInfo(req);
                    successCount++;

                } catch (Exception e) {
                    // 记录失败
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 封装返回
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailures(failures);
            return resp;

        } catch (Exception e) {
            // 文件解析异常
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailures().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

}
