package ir.mehdihosseini.basicframework.base.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public final class ConverterFileUtil {

    private ConverterFileUtil() {
    }

    /**
     * @Description <p>تبدیل و ریختن یک مقدار رشته ای درون یک فایل براساس نام و مقداری که قرار است درون فایل ریخته شود.</p>
     * @Pattern <p>The desired shedding inside a text file -> resource (download)</p>
     * @Parameters <p>filename -> name file | fileContent -> value in the file </p>
     */
    public static Resource convertStringToTextResource(String fileName, String fileContent) {
        if (fileName == null && fileContent == null)
            throw new NullPointerException("File path and name can't be null");

        String formatFilePrivateKey = ".txt";

        assert fileName != null;
        fileName = fileName.contentEquals(formatFilePrivateKey) ? fileName.replaceAll(formatFilePrivateKey, "") + "_" +
                System.currentTimeMillis() + formatFilePrivateKey : fileName + "_" + System.currentTimeMillis() + formatFilePrivateKey;

        Path downloadPath = Path.of(System.getProperty("user.home"), "Downloads", fileName);

        try {
            Files.writeString(downloadPath, fileContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return new FileSystemResource(downloadPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File convertMultipartFileToFile(MultipartFile file) {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close(); //IOUtils.closeQuietly(fos);
        } catch (IOException e) {
            convFile = null;
        }
        return convFile;
    }

    public static String readFileAndConvertToString(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            return String.valueOf(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMimeType(String fileName) {
        String mimeType = "application/octet-stream";
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "pdf" -> mimeType = "application/pdf";
            case "png" -> mimeType = "image/png";
            case "jpg", "jpeg" -> mimeType = "image/jpeg";
            case "txt" -> mimeType = "text/plain";
            case "zip" -> mimeType = "application/zip";
            case "docx" -> mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default -> {
            }
        }

        return mimeType;
    }

}
