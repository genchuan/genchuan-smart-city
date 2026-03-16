package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletfacilityrepair;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.api.file.FileFeignClient;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPendingRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletFacilityRepairDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletFacilityRepairMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.publictoilet.ToiletFacilityRepairCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.util.convert.UrlConvert;
import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TOILET_COMPLAINT_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TOILET_FACILITY_REPAIR_NOT_EXISTS;

/**
 * 公厕设施维修 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ToiletFacilityRepairServiceImpl implements ToiletFacilityRepairService {

    @Resource
    private ToiletFacilityRepairMapper toiletFacilityRepairMapper;

    @Resource
    private ToiletFacilityRepairCodeGenerator codeGenerator;

    @Resource
    private FileFeignClient fileFeignClient;

    @Resource
    private UrlConvert urlConvertUtil;

    @Override
    public Long createToiletFacilityRepair(ToiletFacilityRepairSaveReqVO createReqVO) {

        ToiletFacilityRepairDO toiletFacilityRepair = BeanUtils.toBean(createReqVO, ToiletFacilityRepairDO.class);
        toiletFacilityRepair.setRepairId(codeGenerator.generateRepairId());

        toiletFacilityRepairMapper.insert(toiletFacilityRepair);

        return toiletFacilityRepair.getId();
    }

    @Override
    public void updateToiletFacilityRepair(ToiletFacilityRepairSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletFacilityRepairExists(updateReqVO.getId());

        ToiletFacilityRepairDO updateObj = BeanUtils.toBean(updateReqVO, ToiletFacilityRepairDO.class);

        toiletFacilityRepairMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletFacilityRepair(Long id) {
        // 校验存在
        validateToiletFacilityRepairExists(id);
        // 删除
        toiletFacilityRepairMapper.deleteById(id);
    }

    @Override
    public void deleteToiletFacilityRepairBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 校验所有计划是否存在
        List<ToiletFacilityRepairDO> toiletFacilityRepairs = toiletFacilityRepairMapper.selectBatchIds(ids);
        if (toiletFacilityRepairs.size() != ids.size()) {
            throw exception(TOILET_COMPLAINT_NOT_EXISTS);
        }

        // 批量删除
        toiletFacilityRepairMapper.deleteBatchIds(ids);
    }

    private void validateToiletFacilityRepairExists(Long id) {
        if (toiletFacilityRepairMapper.selectById(id) == null) {
            throw exception(TOILET_FACILITY_REPAIR_NOT_EXISTS);
        }
    }

    @Override
    public ToiletFacilityRepairDO getToiletFacilityRepair(Long id) {
        return toiletFacilityRepairMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletFacilityRepairDO> getToiletFacilityRepairPage(ToiletFacilityRepairPageReqVO pageReqVO) {
        return toiletFacilityRepairMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletFacilityRepairDetailDO> getToiletFacilityRepairDetailPage(ToiletFacilityRepairPageReqVO pageReqVO) {
        Long total = toiletFacilityRepairMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletFacilityRepairDetailDO> list = toiletFacilityRepairMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<String> uploadPhotos(Long id, List<MultipartFile> files) {
        // 校验维修记录存在
        validateToiletFacilityRepairExists(id);

        List<String> photoUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            CommonResult<String> result = fileFeignClient.uploadFile(file);
            if (result.isError()) {
                throw new RuntimeException("图片上传失败：" + result.getMsg());
            }

            // 转换为公网地址
            String publicUrl = urlConvertUtil.convertToPublicUrl(result.getData());
            photoUrls.add(publicUrl);
        }

        // 获取当前维修记录
        ToiletFacilityRepairDO repair = toiletFacilityRepairMapper.selectById(id);

        // 解析现有的图片列表
        List<String> photoList = parsePhotoList(repair.getPhotoUrl());

        // 添加新图片
        photoList.addAll(photoUrls);

        // 更新数据库
        repair.setPhotoUrl(JSON.toJSONString(photoList));
        toiletFacilityRepairMapper.updateById(repair);

        return photoUrls;
    }

    @Override
    public List<String> getPhotos(Long id) {
        validateToiletFacilityRepairExists(id);
        ToiletFacilityRepairDO repair = toiletFacilityRepairMapper.selectById(id);
        return parsePhotoList(repair.getPhotoUrl());
    }

    @Override
    public void deletePhoto(Long id, String photoUrl) {
        validateToiletFacilityRepairExists(id);

        ToiletFacilityRepairDO repair = toiletFacilityRepairMapper.selectById(id);
        List<String> photoList = parsePhotoList(repair.getPhotoUrl());

        if (photoList.remove(photoUrl)) {
            repair.setPhotoUrl(JSON.toJSONString(photoList));
            toiletFacilityRepairMapper.updateById(repair);
        }
    }

    /**
     * 上传图片文件，返回URL列表（私有方法，供内部调用）
     */
    private List<String> uploadPhotoFiles(List<MultipartFile> files) {
        List<String> photoUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            CommonResult<String> result = fileFeignClient.uploadFile(file);
            if (result.isError()) {
                throw new RuntimeException("图片上传失败：" + result.getMsg());
            }
            String publicUrl = urlConvertUtil.convertToPublicUrl(result.getData());
            photoUrls.add(publicUrl);
        }

        return photoUrls;
    }

    /**
     * 解析图片列表
     */
    private List<String> parsePhotoList(String photoJson) {
        if (photoJson == null || photoJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseArray(photoJson, String.class);
        } catch (Exception e) {
            // 如果是单个图片地址，转为列表
            List<String> list = new ArrayList<>();
            list.add(photoJson);
            return list;
        }
    }

    @Override
    public ToiletFacilityRepairPendingRespVO getPendingData() {
        ToiletFacilityRepairPendingRespVO respVO = new ToiletFacilityRepairPendingRespVO();

        // 1. 卡片统计：待维修总数
        Long toRepairTotal = toiletFacilityRepairMapper.countPendingRepair();
        respVO.setToRepairTotal(toRepairTotal == null ? 0L : toRepairTotal);

        // 2. 卡片统计：已派单总数
        Long dispatchedTotal = toiletFacilityRepairMapper.selectDispatchedTotal();
        respVO.setDispatchedTotal(dispatchedTotal == null ? 0L : dispatchedTotal);

        // 3. 圆环图：设施类型占比
        List<PieItemVO> facilityTypeRatio = toiletFacilityRepairMapper.selectFacilityTypeRatio();
        respVO.setFacilityTypeRatio(facilityTypeRatio);

        // 4. 圆环图：维修状态占比
        List<PieItemVO> repairStatusRatio = toiletFacilityRepairMapper.selectRepairStatusRatio();
        respVO.setRepairStatusRatio(repairStatusRatio);

        // 5. 柱状图：不同区域设施损坏数量
        List<BarItemVO> damageCountByArea = toiletFacilityRepairMapper.selectDamageCountByArea();
        respVO.setDamageCountByArea(damageCountByArea);

        // 6. 柱状图：按类型维修数对比
        List<BarItemVO> repairCountByType = toiletFacilityRepairMapper.selectRepairCountByType();
        respVO.setRepairCountByType(repairCountByType);

        return respVO;
    }
}