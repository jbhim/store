package com.easy.store.utils;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

@Component
public class VerifyCodeUtils {

    // 字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    private final String verifyCodes = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private Random random = new Random();

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     * @return
     */
    public String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, verifyCodes);
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     */
    public String generateVerifyCode(int verifySize, String sources) {
        String code = sources == null || sources.length() == 0 ? verifyCodes : sources;
        int codesLen = code.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(code.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 生成随机验证码文件,并返回验证码值
     */
    public String outputVerifyImage(int width, int height, File outputFile, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(width, height, outputFile, verifyCode);
        return verifyCode;
    }

    /**
     * 输出随机验证码图片流,并返回验证码值
     */
    public String outputVerifyImage(int width, int height, OutputStream outputStream, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(width, height, outputStream, verifyCode);
        return verifyCode;
    }

    /**
     * 生成指定验证码图像文件
     */
    public void outputImage(int width, int height, File outputFile, String code) throws IOException {
        if (outputFile != null) {
            File dir = outputFile.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                outputFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(outputFile);
                outputImage(width, height, fos, code);
                fos.close();
            } catch (IOException error) {
                throw error;
            }
        }
    }

    /**
     * 输出指定验证码图片流
     */
    public void outputImage(int width, int height, OutputStream outputStream, String code) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]
            {Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);
        // 设置边框色
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(0, 0, width, height);

        Color randColor = getRandColor(200, 250);
        // 设置背景色
        graphics2D.setColor(randColor);
        graphics2D.fillRect(0, 2, width, height - 4);

        //绘制干扰线
        Random random = new Random();
        // 设置线条的颜色
        graphics2D.setColor(getRandColor(160, 200));
        for (int i = 0; i < 15; i++) {
            int startX = random.nextInt(width - 1);
            int startY = random.nextInt(height - 1);
            int endX = random.nextInt(6) + 1;
            int endY = random.nextInt(12) + 1;
            graphics2D.drawLine(startX, startY, startX + endX + 40, startY + endY + 20);
        }

        // 添加噪点
        // 噪声率
        float yawpRate = 0.01f;
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int nextInt = random.nextInt(width);
            int nextInt1 = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(nextInt, nextInt1, rgb);
        }

        // 使图片扭曲
        shear(graphics2D, width, height, randColor);

        graphics2D.setColor(getRandColor(30, 120));
        int fontSize = height - 4;
        Font font = new Font("terminus-font", Font.ITALIC, fontSize);
        graphics2D.setFont(font);
        char[] chars = code.toCharArray();
        int verifySize = code.length();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (width / (verifySize + 0.0f)) * i + fontSize / 2f, height / 2f);
            graphics2D.setTransform(affine);
            graphics2D.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 3);
        }

        graphics2D.dispose();
        ImageIO.write(image, "jpg", outputStream);
    }

    private Color getRandColor(int parm1, int parm2) {
        int red = parm1 + random.nextInt(parm2 - parm1);
        int green = parm1 + random.nextInt(parm2 - parm1);
        int blue = parm1 + random.nextInt(parm2 - parm1);
        return new Color(red, green, blue);
    }

    private int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private void shear(Graphics graphics, int width, int hreight, Color color) {
        shearX(graphics, width, hreight, color);
        shearY(graphics, width, hreight, color);
    }

    private void shearX(Graphics graphics, int width, int height, Color color) {

        int period = random.nextInt(2);

        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < height; i++) {
            double result = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            graphics.copyArea(0, i, width, 1, (int) result, 0);
            graphics.setColor(color);
            graphics.drawLine((int) result, i, 0, i);
            graphics.drawLine((int) result + width, i, width, i);
        }

    }

    private void shearY(Graphics graphics, int width, int height, Color color) {
        int period = random.nextInt(40) + 10;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < width; i++) {
            double result = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            graphics.copyArea(i, 0, 1, height, 0, (int) result);
            if (borderGap) {
                graphics.setColor(color);
                graphics.drawLine(i, (int) result, i, 0);
                graphics.drawLine(i, (int) result + height, i, height);
            }
        }

    }
}
