package com.easy.store.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {

    /**
     * 创建base64二维码图片
     */
    public static String createQrCodeBase64Pic(String content) throws Exception {
        // 图像宽度
        int width = 139;
        // 图像高度
        int height = 139;
        // 图像类型
        String format = "png";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 生成矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        //新建流
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        //利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        ImageIO.write(image, format, arrayOutputStream);
        //从流中获取数据数组。
        byte[] bytes = arrayOutputStream.toByteArray();
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
    }
}
