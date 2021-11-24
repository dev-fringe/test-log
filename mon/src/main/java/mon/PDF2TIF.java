package mon;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class PDF2TIF {


    public static void main(String[] args) throws Exception {
    	pdf2Tif("C:\\pdf\\ss.pdf", "c:\\tiff");
    }

    public static void pdf2Tif(String path, String outputDir) throws Exception {
        File file = new File(path);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("TIFF");
        ImageWriter writer = null;
        while (writers.hasNext()) {
            writer = writers.next();
            if (writer instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter) {
                break;
            }
        }
        if (!(writer instanceof com.twelvemonkeys.imageio.plugins.tiff.TIFFImageWriter)) {
            throw new Exception("not found twelvemonkeys TIFFImageWriter");
        }
        ImageWriteParam param = writer.getDefaultWriteParam();
        //压缩模式
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionType("ZLib");
        param.setCompressionQuality(1f);

        try (PDDocument doc = PDDocument.load(file)) {
            int pageCount = doc.getNumberOfPages();

            // 创建pdf渲染器
            PDFRenderer renderer = new PDFRenderer(doc);


            for (int i = 0; i < pageCount; i++) {
                try (FileOutputStream out = new FileOutputStream(outputDir + "\\" + i + ".tif");
                     ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out)) {

                    writer.setOutput(imageOutputStream);

                    long pdfRenderStart = System.currentTimeMillis();
                    BufferedImage image = renderer.renderImageWithDPI(i, 100, ImageType.GRAY);
                    long pdfRenderEnd = System.currentTimeMillis();

                    // writer.write(null,new IIOImage(image,null,tiffImageMetadata),param);
                    long writeStart = System.currentTimeMillis();
                    writer.write(null, new IIOImage(image, null, null), param);
                    long writeEnd = System.currentTimeMillis();
                }
            }

        } finally {
            writer.dispose();
        }
    }

}