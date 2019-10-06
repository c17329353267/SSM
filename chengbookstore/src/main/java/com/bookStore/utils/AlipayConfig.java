package com.bookStore.utils;

import java.io.FileWriter;
import java.io.IOException;
//udaumd5531@sandbox.com
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
public static String app_id = "2016092800616853";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCSg/7fLMI6NTxOqPB/x+AS6RIacUYMFsqBkMEszvLVoMTuskeJAJTFSnB+srueffp8iMypOacmQWgNK5uUTQ1ref0xxFEtFpQz+oZuZV6hukkAL1Vg8iUQCBnZor8FGL2ghYj0slizDFLg8suCvBlGuO++fQHvUxEMmfDXthRaIa+9WfQTaeALRUaWlgBJ/jq6UuhmO6SPdgMGj1Z0Ne1r8/gPCjD/qn8RzDU0dVd2IRF91YATu9Quzr7AkuW5oTk+gQ0pZ3NLFDfbgQwqKHKg1HujPic41Ak7X3Je5NpbjwJdq0rarCon4i6NNfp2vQyBeLRC9tWklBN1dd8Z8DW7AgMBAAECggEAPHZWJnkNCgJQCdHztXehQ4yFGP5nvrNlDETVQw1/7qKywiI2HRZtTmFivRY7ELPCO8QkWS1eig3xx4WYZYHKqPCliPUP3sBzlmOrOEGC5NDAMWBXJnOm0c/WAxcDJzJHhse0de5nZjQOu5/WVfQTpjjJTt29f6rS3VVlq08LZQnhhLj3tEwt/vsSbNbIGV/5MzyM2kBbvCMYhFbjr1ngW3+SELfaGTPir9mWj057x1+oohb27ZGy3wSfgwhqAND3FKAPfnPlQgmN56PS+1GcFZ7HN+gOxcCDV9ccwqTpiL1HftE1YNmWTvdHTMRz8LYZSz1aY/ziC0+SDysJW/xsEQKBgQDmL+c8/1SxiW98xtw70wg3tXThascXJ2RBgbSK6pM6SHabiaXX4ExAwzaGK6AM6hbhSkQmW4GZQPVM4b5n9AIswKRodOpJY2K/HqfNKL51TuZurUkMYJadVAMiO7epMcRKXip7MEsyH6yQTBnlP7o/INeA366LEtQn62Av/szRaQKBgQCi8hekPTsz32mU/l7lsoJRBh5/UlbIaTnMlIuClUq/R5LaSgYb+6d4m9dX7DCFVwI/GlOYlfcTiRd8zA9sp+Fd0ypdeu8J8xA25HSoW4MxYRX1plRN86s5CB35xk1YaU77Fw6AYVd7lMneFzKpTeYOC0CbUWsC3WJizN+yM3oFgwKBgQC/xdkY8IBXCPFhOyvwi3ilMQAXbSZBHAhiqvUHZzcRr51hJ+SLXcspiXm7fkzxdXVqbL0qV8I5M+0qCEjhwmCteDGA9SbbzXlPUPLr8a37fahtPOFG9pGEsaGKu8XfqB6o9prd1vZO5CSYc05/uAAq5+mi7Lg2FZG7UFXqppVtQQKBgFFtRV8vshwT8wEftHB4qiKvOiJKFVAFNXsEaEsTK802iS5Ie5EUSvY8enpXb0/S0S6Ti17e4h+DDJeyqaqUokxur+cS/c0tB9dGhv1y7baJW53sRojF0bSoV8Jv1ErwJSXYuchx3HPHQLGZShnM8b/m45w6LY267+WLl+XHT5zdAoGBANC2Cii8X1TnchqIx5YlJMKktsjSmDqKw+T7DjUGPbRq3smi9rgv4YJuEBinr0/FTGp3PvJBvN291fPp6aDvt0mj/a5PTeBMKw0se1jGUihYhSracalnv58Keelspb5hyiUUCBR2wDgjtGHuIQnwn9OOKRbWTxNP1FNVvjhR6wts";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiO6wXznjWzBPuCYoQ6brIwU+g7KJqy7uXp0EvvJR2d7pVmKo87Xt/s9R11odmlvZB3xQMHu4+UT8Zr4KOYqdVfiVDy100S5fwGLe955YlqfrzkBKrXBN5sl/yi49wpv0O5RtvQ7O8iEYjfZkKWd4I0HXHvEbS8Up4tZoOZFUAwCXiHMsa6l2TWWVGKPpHU4+JuxbZ8H3jG0NFhqi9vPADR4doHsjdCtfU4qKjnXTY51/+PXIdVK1RQfo2OFSCyAtqwBrfKId8VSSCIN9dvutBQcuv+M8Y2efMoFO3TV14BURtLZGCdjFu+krtU/aO4HvI5b4Kuz6fybm+4X0xpJPMwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://localhost:8080/client/order/paySuccess.do";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}