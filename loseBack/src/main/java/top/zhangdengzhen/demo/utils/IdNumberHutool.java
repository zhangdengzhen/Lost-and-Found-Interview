package top.zhangdengzhen.demo.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//身份证工具
@Component
public class IdNumberHutool {
    @Value("${hutool.aes.key}")
    private String key;

    public String encode(String content){
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        return encryptHex;
    }

    public String decode(String content){
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());

        //解密为字符串
        String decryptStr = aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
        return decryptStr;
    }
//    身份证，脱敏
    public String cardNum(String id_num){
        // 5***************1X
        return  DesensitizedUtil.idCardNum(id_num, 4, 3);
    }
//    验证身份证，是否有效
    public boolean isvalid(String ID_18){
        return  IdcardUtil.isValidCard(ID_18);
    }
}
