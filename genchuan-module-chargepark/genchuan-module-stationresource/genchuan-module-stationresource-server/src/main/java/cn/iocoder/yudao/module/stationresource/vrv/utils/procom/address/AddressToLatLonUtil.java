package cn.iocoder.yudao.module.stationresource.vrv.utils.procom.address;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class AddressToLatLonUtil {


    // 简称 → 全称
    private static final Map<String, String> PROVINCE_ALIAS = new HashMap<>();
    // 市坐标库（省全称+市 -> [lon, lat]）
    private static final Map<String, double[]> CITY_MAP = new HashMap<>();

    static {
        // ===================== 1. 省份简称映射 =====================
        PROVINCE_ALIAS.put("北京", "北京市");
        PROVINCE_ALIAS.put("天津", "天津市");
        PROVINCE_ALIAS.put("上海", "上海市");
        PROVINCE_ALIAS.put("重庆", "重庆市");

        PROVINCE_ALIAS.put("河北", "河北省");
        PROVINCE_ALIAS.put("山西", "山西省");
        PROVINCE_ALIAS.put("辽宁", "辽宁省");
        PROVINCE_ALIAS.put("吉林", "吉林省");
        PROVINCE_ALIAS.put("黑龙江", "黑龙江省");
        PROVINCE_ALIAS.put("江苏", "江苏省");
        PROVINCE_ALIAS.put("浙江", "浙江省");
        PROVINCE_ALIAS.put("安徽", "安徽省");
        PROVINCE_ALIAS.put("福建", "福建省");
        PROVINCE_ALIAS.put("江西", "江西省");
        PROVINCE_ALIAS.put("山东", "山东省");
        PROVINCE_ALIAS.put("河南", "河南省");
        PROVINCE_ALIAS.put("湖北", "湖北省");
        PROVINCE_ALIAS.put("湖南", "湖南省");
        PROVINCE_ALIAS.put("广东", "广东省");
        PROVINCE_ALIAS.put("海南", "海南省");
        PROVINCE_ALIAS.put("四川", "四川省");
        PROVINCE_ALIAS.put("贵州", "贵州省");
        PROVINCE_ALIAS.put("云南", "云南省");
        PROVINCE_ALIAS.put("陕西", "陕西省");
        PROVINCE_ALIAS.put("甘肃", "甘肃省");
        PROVINCE_ALIAS.put("青海", "青海省");
        PROVINCE_ALIAS.put("台湾", "台湾省");

        PROVINCE_ALIAS.put("广西", "广西壮族自治区");
        PROVINCE_ALIAS.put("内蒙古", "内蒙古自治区");
        PROVINCE_ALIAS.put("西藏", "西藏自治区");
        PROVINCE_ALIAS.put("宁夏", "宁夏回族自治区");
        PROVINCE_ALIAS.put("新疆", "新疆维吾尔自治区");

        PROVINCE_ALIAS.put("香港", "香港特别行政区");
        PROVINCE_ALIAS.put("澳门", "澳门特别行政区");

        // ===================== 2. 全国市级坐标 =====================
        // ===================== 华北地区 =====================
        CITY_MAP.put("北京市北京市", new double[]{116.403874, 39.914885});
        CITY_MAP.put("天津市天津市", new double[]{117.200000, 39.133333});
        CITY_MAP.put("河北省石家庄市", new double[]{114.483333, 38.033333});
        CITY_MAP.put("河北省唐山市", new double[]{118.013333, 39.633333});
        CITY_MAP.put("河北省秦皇岛市", new double[]{119.516667, 39.933333});
        CITY_MAP.put("河北省邯郸市", new double[]{114.466667, 36.616667});
        CITY_MAP.put("河北省邢台市", new double[]{114.500000, 37.050000});
        CITY_MAP.put("河北省保定市", new double[]{115.483333, 38.866667});
        CITY_MAP.put("河北省张家口市", new double[]{114.866667, 40.833333});
        CITY_MAP.put("河北省承德市", new double[]{117.933333, 40.966667});
        CITY_MAP.put("河北省沧州市", new double[]{116.833333, 38.333333});
        CITY_MAP.put("河北省廊坊市", new double[]{116.700000, 39.516667});
        CITY_MAP.put("河北省衡水市", new double[]{115.716667, 37.733333});

        CITY_MAP.put("山西省太原市", new double[]{112.533333, 37.866667});
        CITY_MAP.put("山西省大同市", new double[]{113.300000, 40.083333});
        CITY_MAP.put("山西省阳泉市", new double[]{113.566667, 37.866667});
        CITY_MAP.put("山西省长治市", new double[]{113.116667, 36.183333});
        CITY_MAP.put("山西省晋城市", new double[]{112.850000, 35.500000});
        CITY_MAP.put("山西省朔州市", new double[]{112.416667, 39.333333});
        CITY_MAP.put("山西省晋中市", new double[]{112.733333, 37.683333});
        CITY_MAP.put("山西省运城市", new double[]{110.983333, 35.033333});
        CITY_MAP.put("山西省忻州市", new double[]{112.716667, 38.416667});
        CITY_MAP.put("山西省临汾市", new double[]{111.500000, 36.083333});
        CITY_MAP.put("山西省吕梁市", new double[]{111.133333, 37.516667});

        CITY_MAP.put("内蒙古自治区呼和浩特市", new double[]{111.800000, 40.816667});
        CITY_MAP.put("内蒙古自治区包头市", new double[]{109.800000, 40.633333});
        CITY_MAP.put("内蒙古自治区乌海市", new double[]{106.816667, 39.683333});
        CITY_MAP.put("内蒙古自治区赤峰市", new double[]{118.966667, 42.250000});
        CITY_MAP.put("内蒙古自治区通辽市", new double[]{122.266667, 43.633333});
        CITY_MAP.put("内蒙古自治区鄂尔多斯市", new double[]{109.783333, 39.600000});
        CITY_MAP.put("内蒙古自治区呼伦贝尔市", new double[]{119.750000, 49.216667});
        CITY_MAP.put("内蒙古自治区巴彦淖尔市", new double[]{107.416667, 40.750000});
        CITY_MAP.put("内蒙古自治区乌兰察布市", new double[]{113.133333, 41.033333});
        CITY_MAP.put("内蒙古自治区兴安盟", new double[]{122.083333, 46.083333});
        CITY_MAP.put("内蒙古自治区锡林郭勒盟", new double[]{116.133333, 43.966667});
        CITY_MAP.put("内蒙古自治区阿拉善盟", new double[]{105.966667, 38.333333});

        // ===================== 东北地区 =====================
        CITY_MAP.put("辽宁省沈阳市", new double[]{123.433333, 41.800000});
        CITY_MAP.put("辽宁省大连市", new double[]{121.616667, 38.916667});
        CITY_MAP.put("辽宁省鞍山市", new double[]{122.983333, 41.116667});
        CITY_MAP.put("辽宁省抚顺市", new double[]{123.966667, 41.900000});
        CITY_MAP.put("辽宁省本溪市", new double[]{123.766667, 41.300000});
        CITY_MAP.put("辽宁省丹东市", new double[]{124.366667, 40.133333});
        CITY_MAP.put("辽宁省锦州市", new double[]{121.150000, 41.133333});
        CITY_MAP.put("辽宁省营口市", new double[]{122.233333, 40.666667});
        CITY_MAP.put("辽宁省阜新市", new double[]{121.633333, 42.033333});
        CITY_MAP.put("辽宁省辽阳市", new double[]{123.166667, 41.266667});
        CITY_MAP.put("辽宁省盘锦市", new double[]{122.066667, 41.116667});
        CITY_MAP.put("辽宁省铁岭市", new double[]{123.433333, 42.300000});
        CITY_MAP.put("辽宁省朝阳市", new double[]{120.433333, 41.566667});
        CITY_MAP.put("辽宁省葫芦岛市", new double[]{120.833333, 40.716667});

        CITY_MAP.put("吉林省长春市", new double[]{125.350000, 43.883333});
        CITY_MAP.put("吉林省吉林市", new double[]{126.566667, 43.833333});
        CITY_MAP.put("吉林省四平市", new double[]{124.366667, 43.166667});
        CITY_MAP.put("吉林省辽源市", new double[]{125.133333, 42.900000});
        CITY_MAP.put("吉林省通化市", new double[]{125.916667, 41.733333});
        CITY_MAP.put("吉林省白山市", new double[]{126.416667, 41.933333});
        CITY_MAP.put("吉林省松原市", new double[]{124.816667, 45.116667});
        CITY_MAP.put("吉林省白城市", new double[]{122.833333, 45.633333});
        CITY_MAP.put("吉林省延边朝鲜族自治州", new double[]{129.500000, 42.900000});

        CITY_MAP.put("黑龙江省哈尔滨市", new double[]{126.633333, 45.750000});
        CITY_MAP.put("黑龙江省齐齐哈尔市", new double[]{123.966667, 47.333333});
        CITY_MAP.put("黑龙江省鸡西市", new double[]{130.966667, 45.300000});
        CITY_MAP.put("黑龙江省鹤岗市", new double[]{130.266667, 47.333333});
        CITY_MAP.put("黑龙江省双鸭山市", new double[]{131.166667, 46.633333});
        CITY_MAP.put("黑龙江省大庆市", new double[]{125.033333, 46.583333});
        CITY_MAP.put("黑龙江省伊春市", new double[]{128.916667, 47.716667});
        CITY_MAP.put("黑龙江省佳木斯市", new double[]{130.366667, 46.816667});
        CITY_MAP.put("黑龙江省七台河市", new double[]{130.966667, 45.800000});
        CITY_MAP.put("黑龙江省牡丹江市", new double[]{129.616667, 44.600000});
        CITY_MAP.put("黑龙江省黑河市", new double[]{127.500000, 50.233333});
        CITY_MAP.put("黑龙江省绥化市", new double[]{126.966667, 46.633333});
        CITY_MAP.put("黑龙江省大兴安岭地区", new double[]{124.500000, 53.000000});

        // ===================== 华东地区 =====================
        CITY_MAP.put("上海市上海市", new double[]{121.473701, 31.230416});

        CITY_MAP.put("江苏省南京市", new double[]{118.783333, 32.066667});
        CITY_MAP.put("江苏省无锡市", new double[]{120.283333, 31.566667});
        CITY_MAP.put("江苏省徐州市", new double[]{117.166667, 34.250000});
        CITY_MAP.put("江苏省常州市", new double[]{119.966667, 31.800000});
        CITY_MAP.put("江苏省苏州市", new double[]{120.616667, 31.300000});
        CITY_MAP.put("江苏省南通市", new double[]{120.866667, 31.966667});
        CITY_MAP.put("江苏省连云港市", new double[]{119.166667, 34.583333});
        CITY_MAP.put("江苏省淮安市", new double[]{119.133333, 33.583333});
        CITY_MAP.put("江苏省盐城市", new double[]{120.133333, 33.366667});
        CITY_MAP.put("江苏省扬州市", new double[]{119.433333, 32.400000});
        CITY_MAP.put("江苏省镇江市", new double[]{119.433333, 32.200000});
        CITY_MAP.put("江苏省泰州市", new double[]{119.900000, 32.483333});
        CITY_MAP.put("江苏省宿迁市", new double[]{118.283333, 33.933333});

        CITY_MAP.put("浙江省杭州市", new double[]{120.150000, 30.283333});
        CITY_MAP.put("浙江省宁波市", new double[]{121.550000, 29.866667});
        CITY_MAP.put("浙江省温州市", new double[]{120.650000, 28.000000});
        CITY_MAP.put("浙江省嘉兴市", new double[]{120.750000, 30.750000});
        CITY_MAP.put("浙江省湖州市", new double[]{120.100000, 30.900000});
        CITY_MAP.put("浙江省绍兴市", new double[]{120.566667, 30.000000});
        CITY_MAP.put("浙江省金华市", new double[]{119.633333, 29.100000});
        CITY_MAP.put("浙江省衢州市", new double[]{118.866667, 28.933333});
        CITY_MAP.put("浙江省舟山市", new double[]{122.100000, 30.000000});
        CITY_MAP.put("浙江省台州市", new double[]{121.416667, 28.666667});
        CITY_MAP.put("浙江省丽水市", new double[]{119.916667, 28.466667});

        CITY_MAP.put("安徽省合肥市", new double[]{117.266667, 31.850000});
        CITY_MAP.put("安徽省芜湖市", new double[]{118.433333, 31.333333});
        CITY_MAP.put("安徽省蚌埠市", new double[]{117.366667, 32.916667});
        CITY_MAP.put("安徽省淮南市", new double[]{117.000000, 32.633333});
        CITY_MAP.put("安徽省马鞍山市", new double[]{118.500000, 31.700000});
        CITY_MAP.put("安徽省淮北市", new double[]{116.800000, 33.933333});
        CITY_MAP.put("安徽省铜陵市", new double[]{117.800000, 30.916667});
        CITY_MAP.put("安徽省安庆市", new double[]{117.033333, 30.500000});
        CITY_MAP.put("安徽省黄山市", new double[]{118.300000, 29.716667});
        CITY_MAP.put("安徽省滁州市", new double[]{118.300000, 32.300000});
        CITY_MAP.put("安徽省阜阳市", new double[]{115.800000, 32.900000});
        CITY_MAP.put("安徽省宿州市", new double[]{116.966667, 33.633333});
        CITY_MAP.put("安徽省六安市", new double[]{116.500000, 31.733333});
        CITY_MAP.put("安徽省亳州市", new double[]{115.766667, 33.866667});
        CITY_MAP.put("安徽省池州市", new double[]{117.466667, 30.650000});
        CITY_MAP.put("安徽省宣城市", new double[]{118.766667, 30.933333});

        CITY_MAP.put("福建省福州市", new double[]{119.300000, 26.083333});
        CITY_MAP.put("福建省厦门市", new double[]{118.100000, 24.466667});
        CITY_MAP.put("福建省莆田市", new double[]{119.000000, 25.433333});
        CITY_MAP.put("福建省三明市", new double[]{117.633333, 26.250000});
        CITY_MAP.put("福建省泉州市", new double[]{118.583333, 24.900000});
        CITY_MAP.put("福建省漳州市", new double[]{117.033333, 24.500000});
        CITY_MAP.put("福建省南平市", new double[]{118.150000, 26.633333});
        CITY_MAP.put("福建省龙岩市", new double[]{117.033333, 25.083333});
        CITY_MAP.put("福建省宁德市", new double[]{119.516667, 26.666667});

        CITY_MAP.put("江西省南昌市", new double[]{115.900000, 28.666667});
        CITY_MAP.put("江西省景德镇市", new double[]{117.200000, 29.300000});
        CITY_MAP.put("江西省萍乡市", new double[]{113.833333, 27.600000});
        CITY_MAP.put("江西省九江市", new double[]{115.966667, 29.700000});
        CITY_MAP.put("江西省新余市", new double[]{114.916667, 27.800000});
        CITY_MAP.put("江西省鹰潭市", new double[]{117.033333, 28.250000});
        CITY_MAP.put("江西省赣州市", new double[]{114.916667, 25.833333});
        CITY_MAP.put("江西省吉安市", new double[]{114.966667, 27.116667});
        CITY_MAP.put("江西省宜春市", new double[]{114.400000, 27.800000});
        CITY_MAP.put("江西省抚州市", new double[]{116.350000, 28.000000});
        CITY_MAP.put("江西省上饶市", new double[]{117.966667, 28.466667});

        CITY_MAP.put("山东省济南市", new double[]{117.000000, 36.633333});
        CITY_MAP.put("山东省青岛市", new double[]{120.333333, 36.066667});
        CITY_MAP.put("山东省淄博市", new double[]{118.033333, 36.800000});
        CITY_MAP.put("山东省枣庄市", new double[]{117.566667, 34.833333});
        CITY_MAP.put("山东省东营市", new double[]{118.483333, 37.450000});
        CITY_MAP.put("山东省烟台市", new double[]{121.400000, 37.516667});
        CITY_MAP.put("山东省潍坊市", new double[]{119.100000, 36.766667});
        CITY_MAP.put("山东省济宁市", new double[]{116.583333, 35.400000});
        CITY_MAP.put("山东省泰安市", new double[]{117.133333, 36.183333});
        CITY_MAP.put("山东省威海市", new double[]{122.133333, 37.500000});
        CITY_MAP.put("山东省日照市", new double[]{119.516667, 35.416667});
        CITY_MAP.put("山东省临沂市", new double[]{118.333333, 35.033333});
        CITY_MAP.put("山东省德州市", new double[]{116.300000, 37.433333});
        CITY_MAP.put("山东省聊城市", new double[]{115.966667, 36.433333});
        CITY_MAP.put("山东省滨州市", new double[]{118.033333, 37.366667});
        CITY_MAP.put("山东省菏泽市", new double[]{115.483333, 35.200000});

        // ===================== 华中地区 =====================
        CITY_MAP.put("河南省郑州市", new double[]{113.650000, 34.750000});
        CITY_MAP.put("河南省开封市", new double[]{114.333333, 34.800000});
        CITY_MAP.put("河南省洛阳市", new double[]{112.433333, 34.633333});
        CITY_MAP.put("河南省平顶山市", new double[]{113.300000, 33.733333});
        CITY_MAP.put("河南省安阳市", new double[]{114.350000, 36.100000});
        CITY_MAP.put("河南省鹤壁市", new double[]{114.283333, 35.900000});
        CITY_MAP.put("河南省新乡市", new double[]{113.866667, 35.300000});
        CITY_MAP.put("河南省焦作市", new double[]{113.233333, 35.233333});
        CITY_MAP.put("河南省濮阳市", new double[]{115.000000, 35.766667});
        CITY_MAP.put("河南省许昌市", new double[]{113.833333, 34.033333});
        CITY_MAP.put("河南省漯河市", new double[]{114.033333, 33.583333});
        CITY_MAP.put("河南省三门峡市", new double[]{111.183333, 34.766667});
        CITY_MAP.put("河南省南阳市", new double[]{112.533333, 33.000000});
        CITY_MAP.put("河南省商丘市", new double[]{115.633333, 34.433333});
        CITY_MAP.put("河南省信阳市", new double[]{114.083333, 32.133333});
        CITY_MAP.put("河南省周口市", new double[]{114.633333, 33.633333});
        CITY_MAP.put("河南省驻马店市", new double[]{114.033333, 32.966667});

        CITY_MAP.put("湖北省武汉市", new double[]{114.300000, 30.600000});
        CITY_MAP.put("湖北省黄石市", new double[]{115.033333, 30.200000});
        CITY_MAP.put("湖北省十堰市", new double[]{110.783333, 32.633333});
        CITY_MAP.put("湖北省宜昌市", new double[]{111.300000, 30.700000});
        CITY_MAP.put("湖北省襄阳市", new double[]{112.133333, 32.066667});
        CITY_MAP.put("湖北省鄂州市", new double[]{114.883333, 30.400000});
        CITY_MAP.put("湖北省荆门市", new double[]{112.200000, 31.033333});
        CITY_MAP.put("湖北省孝感市", new double[]{113.900000, 30.900000});
        CITY_MAP.put("湖北省荆州市", new double[]{112.233333, 30.333333});
        CITY_MAP.put("湖北省黄冈市", new double[]{114.866667, 30.433333});
        CITY_MAP.put("湖北省咸宁市", new double[]{114.300000, 29.833333});
        CITY_MAP.put("湖北省随州市", new double[]{113.366667, 31.700000});
        CITY_MAP.put("湖北省恩施土家族苗族自治州", new double[]{109.466667, 30.300000});

        CITY_MAP.put("湖南省长沙市", new double[]{112.933333, 28.216667});
        CITY_MAP.put("湖南省株洲市", new double[]{113.133333, 27.833333});
        CITY_MAP.put("湖南省湘潭市", new double[]{112.900000, 27.833333});
        CITY_MAP.put("湖南省衡阳市", new double[]{112.600000, 26.900000});
        CITY_MAP.put("湖南省邵阳市", new double[]{111.500000, 27.233333});
        CITY_MAP.put("湖南省岳阳市", new double[]{113.133333, 29.366667});
        CITY_MAP.put("湖南省常德市", new double[]{111.683333, 29.033333});
        CITY_MAP.put("湖南省张家界市", new double[]{110.483333, 29.100000});
        CITY_MAP.put("湖南省益阳市", new double[]{112.333333, 28.583333});
        CITY_MAP.put("湖南省郴州市", new double[]{113.033333, 25.800000});
        CITY_MAP.put("湖南省永州市", new double[]{111.633333, 26.433333});
        CITY_MAP.put("湖南省怀化市", new double[]{109.966667, 27.533333});
        CITY_MAP.put("湖南省娄底市", new double[]{112.000000, 27.733333});
        CITY_MAP.put("湖南省湘西土家族苗族自治州", new double[]{109.700000, 28.300000});

        // ===================== 华南地区 =====================
        CITY_MAP.put("广东省广州市", new double[]{113.250000, 23.133333});
        CITY_MAP.put("广东省韶关市", new double[]{113.600000, 24.800000});
        CITY_MAP.put("广东省深圳市", new double[]{114.070000, 22.620000});
        CITY_MAP.put("广东省珠海市", new double[]{113.566667, 22.266667});
        CITY_MAP.put("广东省汕头市", new double[]{116.683333, 23.366667});
        CITY_MAP.put("广东省佛山市", new double[]{113.100000, 23.033333});
        CITY_MAP.put("广东省江门市", new double[]{113.083333, 22.583333});
        CITY_MAP.put("广东省湛江市", new double[]{110.350000, 21.266667});
        CITY_MAP.put("广东省茂名市", new double[]{110.916667, 21.666667});
        CITY_MAP.put("广东省肇庆市", new double[]{112.433333, 23.033333});
        CITY_MAP.put("广东省惠州市", new double[]{114.400000, 23.083333});
        CITY_MAP.put("广东省梅州市", new double[]{116.100000, 24.300000});
        CITY_MAP.put("广东省汕尾市", new double[]{115.966667, 22.783333});
        CITY_MAP.put("广东省河源市", new double[]{114.683333, 23.733333});
        CITY_MAP.put("广东省阳江市", new double[]{111.966667, 21.866667});
        CITY_MAP.put("广东省清远市", new double[]{113.033333, 23.666667});
        CITY_MAP.put("广东省东莞市", new double[]{113.766667, 23.033333});
        CITY_MAP.put("广东省中山市", new double[]{113.383333, 22.516667});
        CITY_MAP.put("广东省潮州市", new double[]{116.633333, 23.666667});
        CITY_MAP.put("广东省揭阳市", new double[]{116.350000, 23.533333});
        CITY_MAP.put("广东省云浮市", new double[]{112.033333, 22.933333});

        CITY_MAP.put("广西壮族自治区南宁市", new double[]{108.333333, 22.816667});
        CITY_MAP.put("广西壮族自治区柳州市", new double[]{109.400000, 24.316667});
        CITY_MAP.put("广西壮族自治区桂林市", new double[]{110.300000, 25.266667});
        CITY_MAP.put("广西壮族自治区梧州市", new double[]{111.300000, 23.466667});
        CITY_MAP.put("广西壮族自治区北海市", new double[]{109.116667, 21.483333});
        CITY_MAP.put("广西壮族自治区防城港市", new double[]{108.333333, 21.766667});
        CITY_MAP.put("广西壮族自治区钦州市", new double[]{108.633333, 21.966667});
        CITY_MAP.put("广西壮族自治区贵港市", new double[]{109.633333, 23.100000});
        CITY_MAP.put("广西壮族自治区玉林市", new double[]{110.133333, 22.633333});
        CITY_MAP.put("广西壮族自治区百色市", new double[]{106.600000, 23.900000});
        CITY_MAP.put("广西壮族自治区贺州市", new double[]{111.533333, 24.400000});
        CITY_MAP.put("广西壮族自治区河池市", new double[]{108.066667, 24.700000});
        CITY_MAP.put("广西壮族自治区来宾市", new double[]{109.200000, 23.766667});
        CITY_MAP.put("广西壮族自治区崇左市", new double[]{107.350000, 22.400000});

        CITY_MAP.put("海南省海口市", new double[]{110.333333, 20.033333});
        CITY_MAP.put("海南省三亚市", new double[]{109.500000, 18.250000});
        CITY_MAP.put("海南省三沙市", new double[]{112.333333, 16.833333});
        CITY_MAP.put("海南省儋州市", new double[]{109.566667, 19.516667});

        // ===================== 西南地区 =====================
        CITY_MAP.put("重庆市重庆市", new double[]{106.550000, 29.566667});

        CITY_MAP.put("四川省成都市", new double[]{104.066667, 30.666667});
        CITY_MAP.put("四川省自贡市", new double[]{104.766667, 29.333333});
        CITY_MAP.put("四川省攀枝花市", new double[]{101.700000, 26.566667});
        CITY_MAP.put("四川省泸州市", new double[]{105.433333, 28.883333});
        CITY_MAP.put("四川省德阳市", new double[]{104.216667, 31.000000});
        CITY_MAP.put("四川省绵阳市", new double[]{104.733333, 31.483333});
        CITY_MAP.put("四川省广元市", new double[]{105.833333, 32.433333});
        CITY_MAP.put("四川省遂宁市", new double[]{105.566667, 30.516667});
        CITY_MAP.put("四川省内江市", new double[]{105.033333, 29.583333});
        CITY_MAP.put("四川省乐山市", new double[]{103.733333, 29.566667});
        CITY_MAP.put("四川省南充市", new double[]{106.083333, 30.800000});
        CITY_MAP.put("四川省眉山市", new double[]{103.833333, 30.066667});
        CITY_MAP.put("四川省宜宾市", new double[]{104.616667, 28.766667});
        CITY_MAP.put("四川省广安市", new double[]{106.633333, 30.466667});
        CITY_MAP.put("四川省达州市", new double[]{107.483333, 31.200000});
        CITY_MAP.put("四川省雅安市", new double[]{103.000000, 30.000000});
        CITY_MAP.put("四川省巴中市", new double[]{106.766667, 31.833333});
        CITY_MAP.put("四川省资阳市", new double[]{104.650000, 30.100000});
        CITY_MAP.put("四川省阿坝藏族羌族自治州", new double[]{102.500000, 32.900000});
        CITY_MAP.put("四川省甘孜藏族自治州", new double[]{100.000000, 31.000000});
        CITY_MAP.put("四川省凉山彝族自治州", new double[]{102.266667, 27.900000});

        CITY_MAP.put("贵州省贵阳市", new double[]{106.700000, 26.600000});
        CITY_MAP.put("贵州省六盘水市", new double[]{104.833333, 26.600000});
        CITY_MAP.put("贵州省遵义市", new double[]{106.900000, 27.700000});
        CITY_MAP.put("贵州省安顺市", new double[]{105.916667, 26.233333});
        CITY_MAP.put("贵州省毕节市", new double[]{105.300000, 27.300000});
        CITY_MAP.put("贵州省铜仁市", new double[]{109.183333, 27.733333});
        CITY_MAP.put("贵州省黔西南布依族苗族自治州", new double[]{104.900000, 25.083333});
        CITY_MAP.put("贵州省黔东南苗族侗族自治州", new double[]{107.966667, 26.633333});
        CITY_MAP.put("贵州省黔南布依族苗族自治州", new double[]{107.500000, 26.250000});

        CITY_MAP.put("云南省昆明市", new double[]{102.733333, 25.033333});
        CITY_MAP.put("云南省曲靖市", new double[]{103.783333, 25.483333});
        CITY_MAP.put("云南省玉溪市", new double[]{102.566667, 24.333333});
        CITY_MAP.put("云南省保山市", new double[]{99.166667, 25.100000});
        CITY_MAP.put("云南省昭通市", new double[]{103.700000, 27.333333});
        CITY_MAP.put("云南省丽江市", new double[]{100.233333, 26.866667});
        CITY_MAP.put("云南省普洱市", new double[]{100.966667, 22.833333});
        CITY_MAP.put("云南省临沧市", new double[]{100.083333, 23.883333});
        CITY_MAP.put("云南省楚雄彝族自治州", new double[]{101.533333, 25.033333});
        CITY_MAP.put("云南省红河哈尼族彝族自治州", new double[]{103.366667, 23.366667});
        CITY_MAP.put("云南省文山壮族苗族自治州", new double[]{104.233333, 23.400000});
        CITY_MAP.put("云南省西双版纳傣族自治州", new double[]{100.800000, 22.000000});
        CITY_MAP.put("云南省大理白族自治州", new double[]{100.233333, 25.583333});
        CITY_MAP.put("云南省德宏傣族景颇族自治州", new double[]{98.583333, 24.433333});
        CITY_MAP.put("云南省怒江傈僳族自治州", new double[]{98.866667, 26.200000});
        CITY_MAP.put("云南省迪庆藏族自治州", new double[]{99.700000, 27.833333});

        CITY_MAP.put("西藏自治区拉萨市", new double[]{91.166667, 29.666667});
        CITY_MAP.put("西藏自治区日喀则市", new double[]{88.866667, 29.283333});
        CITY_MAP.put("西藏自治区昌都市", new double[]{97.166667, 31.133333});
        CITY_MAP.put("西藏自治区林芝市", new double[]{94.333333, 29.500000});
        CITY_MAP.put("西藏自治区山南市", new double[]{91.766667, 29.233333});
        CITY_MAP.put("西藏自治区那曲市", new double[]{92.066667, 31.483333});
        CITY_MAP.put("西藏自治区阿里地区", new double[]{80.000000, 32.500000});

        // ===================== 西北地区 =====================
        CITY_MAP.put("陕西省西安市", new double[]{108.933333, 34.266667});
        CITY_MAP.put("陕西省铜川市", new double[]{109.066667, 35.100000});
        CITY_MAP.put("陕西省宝鸡市", new double[]{107.133333, 34.366667});
        CITY_MAP.put("陕西省咸阳市", new double[]{108.700000, 34.333333});
        CITY_MAP.put("陕西省渭南市", new double[]{109.483333, 34.516667});
        CITY_MAP.put("陕西省延安市", new double[]{109.466667, 36.600000});
        CITY_MAP.put("陕西省汉中市", new double[]{107.033333, 33.066667});
        CITY_MAP.put("陕西省榆林市", new double[]{109.733333, 38.300000});
        CITY_MAP.put("陕西省安康市", new double[]{109.033333, 32.700000});
        CITY_MAP.put("陕西省商洛市", new double[]{109.933333, 33.866667});

        CITY_MAP.put("甘肃省兰州市", new double[]{103.816667, 36.066667});
        CITY_MAP.put("甘肃省嘉峪关市", new double[]{98.200000, 39.800000});
        CITY_MAP.put("甘肃省金昌市", new double[]{102.166667, 38.516667});
        CITY_MAP.put("甘肃省白银市", new double[]{104.166667, 36.533333});
        CITY_MAP.put("甘肃省天水市", new double[]{105.716667, 34.583333});
        CITY_MAP.put("甘肃省武威市", new double[]{102.633333, 37.933333});
        CITY_MAP.put("甘肃省张掖市", new double[]{100.466667, 38.916667});
        CITY_MAP.put("甘肃省平凉市", new double[]{106.666667, 35.533333});
        CITY_MAP.put("甘肃省酒泉市", new double[]{98.500000, 39.733333});
        CITY_MAP.put("甘肃省庆阳市", new double[]{107.633333, 35.733333});
        CITY_MAP.put("甘肃省定西市", new double[]{104.600000, 35.600000});
        CITY_MAP.put("甘肃省陇南市", new double[]{104.916667, 33.400000});
        CITY_MAP.put("甘肃省临夏回族自治州", new double[]{103.200000, 35.600000});
        CITY_MAP.put("甘肃省甘南藏族自治州", new double[]{102.900000, 34.766667});

        CITY_MAP.put("青海省西宁市", new double[]{101.766667, 36.633333});
        CITY_MAP.put("青海省海东市", new double[]{102.100000, 36.500000});
        CITY_MAP.put("青海省海北藏族自治州", new double[]{100.866667, 36.966667});
        CITY_MAP.put("青海省黄南藏族自治州", new double[]{101.333333, 35.500000});
        CITY_MAP.put("青海省海南藏族自治州", new double[]{100.600000, 36.250000});
        CITY_MAP.put("青海省果洛藏族自治州", new double[]{100.133333, 34.466667});
        CITY_MAP.put("青海省玉树藏族自治州", new double[]{97.000000, 33.000000});
        CITY_MAP.put("青海省海西蒙古族藏族自治州", new double[]{97.366667, 37.366667});

        CITY_MAP.put("宁夏回族自治区银川市", new double[]{106.266667, 38.466667});
        CITY_MAP.put("宁夏回族自治区石嘴山市", new double[]{106.366667, 39.033333});
        CITY_MAP.put("宁夏回族自治区吴忠市", new double[]{106.200000, 37.333333});
        CITY_MAP.put("宁夏回族自治区固原市", new double[]{106.300000, 36.000000});
        CITY_MAP.put("宁夏回族自治区中卫市", new double[]{105.183333, 37.500000});

        CITY_MAP.put("新疆维吾尔自治区乌鲁木齐市", new double[]{87.600000, 43.800000});
        CITY_MAP.put("新疆维吾尔自治区克拉玛依市", new double[]{84.866667, 45.600000});
        CITY_MAP.put("新疆维吾尔自治区吐鲁番市", new double[]{89.166667, 42.900000});
        CITY_MAP.put("新疆维吾尔自治区哈密市", new double[]{93.466667, 42.800000});
        CITY_MAP.put("新疆维吾尔自治区昌吉回族自治州", new double[]{87.300000, 43.900000});
        CITY_MAP.put("新疆维吾尔自治区博尔塔拉蒙古自治州", new double[]{82.066667, 44.900000});
        CITY_MAP.put("新疆维吾尔自治区巴音郭楞蒙古自治州", new double[]{86.100000, 41.766667});
        CITY_MAP.put("新疆维吾尔自治区阿克苏地区", new double[]{80.250000, 41.166667});
        CITY_MAP.put("新疆维吾尔自治区克孜勒苏柯尔克孜自治州", new double[]{76.166667, 39.333333});
        CITY_MAP.put("新疆维吾尔自治区喀什地区", new double[]{75.966667, 39.466667});
        CITY_MAP.put("新疆维吾尔自治区和田地区", new double[]{79.933333, 37.100000});
        CITY_MAP.put("新疆维吾尔自治区伊犁哈萨克自治州", new double[]{83.250000, 43.000000});
        CITY_MAP.put("新疆维吾尔自治区塔城地区", new double[]{82.966667, 46.733333});
        CITY_MAP.put("新疆维吾尔自治区阿勒泰地区", new double[]{88.166667, 47.833333});
    }

    /**
     * 最终对外方法：支持简称 + 区县微小偏移 + 失败 fallback 福建
     */
    public static double[] getLatLon(String province, String city, String district) {
        // 1. 处理简称：广西 → 广西壮族自治区
        String provFull = PROVINCE_ALIAS.getOrDefault(province, province);

        // 2. 拼接 key
        String key = provFull + city;

        // 3. 获取城市基准坐标
        double[] base = CITY_MAP.get(key);

        // 4. 识别失败 → 用福建随机（泉州市附近）
        if (base == null) {
            return randomAround(118.583333, 24.900000, 0.05);
        }

        // 5. 有区县 → 微小偏移（不会出市）
        if (StrUtil.isNotBlank(district)) {
            return randomAround(base[0], base[1], 0.03); // ±0.03 度 ≈ 市内偏移
        }

        // 6. 无区县 → 基准坐标
        return base;
    }

    /**
     * 随机偏移：确保不会飘出市
     * offset: 0.03 ≈ 3km 左右，绝对安全
     */
    // ===================== 随机偏移（安全、不跨市） =====================
    private static double[] randomAround(double lon, double lat, double offset) {
        Random random = new Random();
        double lonDelta = (random.nextDouble() - 0.5) * 2 * offset;
        double latDelta = (random.nextDouble() - 0.5) * 2 * offset;
        return new double[]{lon + lonDelta, lat + latDelta};
    }
}
