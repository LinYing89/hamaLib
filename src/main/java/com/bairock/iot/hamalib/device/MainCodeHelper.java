package com.bairock.iot.hamalib.device;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * main code helper
 * @author LinQiang
 *
 */
public class MainCodeHelper {

	private static MainCodeHelper ins;
	/**
	 * 协调器
	 */
	public static final String XIE_TIAO_QI = "A1";
	/**
     * PLC
     */
    public static final String PLC = "Ax";
	/**
	 * 呱呱嘴
	 */
	public static final String GUAGUA_MOUTH = "R1";
	/**
	 * 门禁
	 */
	public static final String MEN_JIN = "w1";
	/**
	 * 液位计
	 */
	public static final String YE_WEI = "y1";
	/**
	 * 信号采集控制器，采集电压电流等信号
	 */
	public static final String COLLECTOR_SIGNAL_CONTAINER = "bx";
	/**
	 * 信号采集器，单个设备，一次只能采集一种信号，目前作为虚拟设备挂在信号采集控制器下使用
	 */
	public static final String COLLECTOR_SIGNAL = "b1";
	/**
	 * 多功能信号传感器，采集温度、湿度和甲醛等
	 */
	public static final String COLLECTOR_CLIMATE_CONTAINER = "x1";
	/**
	 * 烟雾、可燃气体探测器
	 */
	public static final String YAN_WU = "z1";
	/**
	 * 温度，目前作为虚拟设备挂在多功能信号传感器下使用
	 */
	public static final String WEN_DU = "e1";
	/**
	 * 湿度，目前作为虚拟设备挂在多功能信号传感器下使用
	 */
	public static final String SHI_DU = "e2";
	/**
	 * 甲醛，目前作为虚拟设备挂在多功能信号传感器下使用
	 */
	public static final String JIA_QUAN = "e3";
	/**
	 * 一路两态开关
	 */
	public static final String KG_1LU_2TAI = "B1";
	/**
	 * 两路两态开关
	 */
	public static final String KG_2LU_2TAI = "B2";
	/**
	 * 三路两态开关
	 */
	public static final String KG_3LU_2TAI = "B3";
	/**
	 * 多路两态开关
	 */
	public static final String KG_XLU_2TAI = "Bx";
	/**
	 * 三态开关
	 */
	public static final String KG_3TAI = "C1";
	/**
	 * 遥控器
	 */
	public static final String YAO_KONG = "D1";
	
	/**
     * 虚拟设备, 参数设备
     */
    public static final String VT_PARAM = "V1";
    /**
     * 虚拟设备, 计数器
     */
    public static final String VT_COUNTER = "V2";
	/**
	 * 插座
	 */
	public static final String CHA_ZUO = "E1";
	//bao jing she bei lei xing 1
	public static final String BAO_JING1 = "a1";
	//bao jing she bei lei xing 2
	public static final String BAO_JING2 = "a2";
	
	//以下多为子设备
	/**
	 * 无类型设备
	 */
	public static final String SMC_WU = "0";
	/**
	 * 窗帘
	 */
	public static final String SMC_REMOTER_CHUANG_LIAN = "3";
	/**
	 * 电视
	 */
	public static final String SMC_REMOTER_DIAN_SHI = "1";
	/**
	 * 空调
	 */
	public static final String SMC_REMOTER_KONG_TIAO = "2";
	/**
	 * 投影仪
	 */
	public static final String SMC_REMOTER_TOU_YING = "4";
	/**
	 * 投影幕布
	 */
	public static final String SMC_REMOTER_MU_BU = "5";
	/**
	 * 升降架
	 */
	public static final String SMC_REMOTER_SHENG_JIANG_JIA = "6";
	/**
	 * 自定义设备
	 */
	public static final String SMC_REMOTER_ZI_DING_YI = "7";
	/**
	 * 灯
	 */
	public static final String SMC_DENG = "10";
	/**
	 * 窗户
	 */
	public static final String SMC_CHUANG_HU = "11";
	/**
	 * 阀门
	 */
	public static final String SMC_FA_MEN = "12";
	/**
	 * 冰箱
	 */
	public static final String SMC_BING_XIANG = "14";
	/**
	 * 洗衣机
	 */
	public static final String SMC_XI_YI_JI = "15";
	/**
	 * 微波炉
	 */
	public static final String SMC_WEI_BO_LU = "16";
	/**
	 * 音箱
	 */
	public static final String SMC_YIN_XIANG = "17";
	/**
	 * 水龙头
	 */
	public static final String SMC_SHUI_LONG_TOU = "18";

	private Map<String, String> mainCodeMap;

	/**
	 *
	 */
	private MainCodeHelper(){
		mainCodeMap = new HashMap<>();
		mainCodeMap.put(XIE_TIAO_QI, "xie_tiao_qi");
		mainCodeMap.put(PLC, "PLC");
		mainCodeMap.put(GUAGUA_MOUTH, "gua_gua_zui");
		mainCodeMap.put(MEN_JIN, "men_jin");

		mainCodeMap.put(YE_WEI, "qi_ya_chuan_gan_ye_wei_ji");
		mainCodeMap.put(COLLECTOR_SIGNAL, "xin_hao_cai_ji_qi");
		mainCodeMap.put(COLLECTOR_SIGNAL_CONTAINER, "xin_hao_cai_ji_kong_zhi_qi");
		mainCodeMap.put(COLLECTOR_CLIMATE_CONTAINER, "duo_gong_neng_qi_hou_chuanganqi");

		mainCodeMap.put(YAN_WU, "yan_wu");
		mainCodeMap.put(WEN_DU, "wen_du");

		mainCodeMap.put(SHI_DU, "shi_du");
		mainCodeMap.put(JIA_QUAN, "jia_quan");

		mainCodeMap.put(KG_1LU_2TAI, "1_lu");
		mainCodeMap.put(KG_2LU_2TAI, "2_lu");
		mainCodeMap.put(KG_3LU_2TAI, "3_lu");
		mainCodeMap.put(KG_XLU_2TAI, "x_lu");

		mainCodeMap.put(KG_3TAI, "3_tai");
		mainCodeMap.put(YAO_KONG, "xue_xi_yao_kong");
		mainCodeMap.put(VT_PARAM, "can_shu_shi_bei");
		mainCodeMap.put(VT_COUNTER, "ji_shu_qi");

		mainCodeMap.put(CHA_ZUO, "cha_zuo");
		mainCodeMap.put(BAO_JING1, "bao_jing1");
		mainCodeMap.put(BAO_JING2, "bao_jing2");
		mainCodeMap.put(SMC_WU, "wu");

		mainCodeMap.put(SMC_REMOTER_CHUANG_LIAN, "chuang_lian");
		mainCodeMap.put(SMC_REMOTER_DIAN_SHI, "dian_shi");
		mainCodeMap.put(SMC_REMOTER_KONG_TIAO, "kong_tiao");
		mainCodeMap.put(SMC_REMOTER_TOU_YING, "tou_ying_yi");

		mainCodeMap.put(SMC_REMOTER_MU_BU, "mu_bu");
		mainCodeMap.put(SMC_REMOTER_SHENG_JIANG_JIA, "shen_jiang_jia");
		mainCodeMap.put(SMC_REMOTER_ZI_DING_YI, "zi_ding_yi");
		mainCodeMap.put(SMC_DENG, "deng");

		mainCodeMap.put(SMC_CHUANG_HU, "chuang_hu");
		mainCodeMap.put(SMC_FA_MEN, "fa_men");
		mainCodeMap.put(SMC_BING_XIANG, "bing_xiang");
		mainCodeMap.put(SMC_XI_YI_JI, "xi_yi_ji");

		mainCodeMap.put(SMC_WEI_BO_LU, "wei_bo_lu");
		mainCodeMap.put(SMC_YIN_XIANG, "yin_xiang");
		mainCodeMap.put(SMC_SHUI_LONG_TOU, "shui_long_tou");

	}

	public static MainCodeHelper getIns(){
		if(null == ins){
			ins = new MainCodeHelper();
		}
		return ins;
	}

	public void add(String key, String value){
		if(null != key && null != value) {
			mainCodeMap.put(key, value);
		}
	}

	public String remove(String key){
		return mainCodeMap.remove(key);
	}

	public String getMainCodeInfo(String mc) {
		if(null == mc){
			return "";
		}
		AtomicReference<String> info = new AtomicReference<>("");
		mainCodeMap.forEach((key, value) ->{
			if(key.equals(mc)){
				info.set(value);
			}
		});
		return info.get();
	}
}
