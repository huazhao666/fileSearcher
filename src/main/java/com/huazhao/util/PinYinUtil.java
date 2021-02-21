package com.huazhao.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
    public static String getPinYin(String name){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer sb = new StringBuffer();
        for(char ch : name.toCharArray()){
            try {
                String[] pinArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                if (pinArray == null || pinArray.length == 0) {
                    sb.append(ch);
                    continue;
                }
                sb.append(pinArray[0]);
            }catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination){
                sb.append(ch);
            }
        }
        return sb.toString();

    }
    public static String getPinYinFirst(String name){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer sb = new StringBuffer();
        for(char ch : name.toCharArray()){
            try {
                String[] pinArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                if (pinArray == null || pinArray.length == 0) {
                    sb.append(ch);
                    continue;
                }
                sb.append(pinArray[0].charAt(0));
            }catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(getPinYinFirst("我爱你"));
    }
}
