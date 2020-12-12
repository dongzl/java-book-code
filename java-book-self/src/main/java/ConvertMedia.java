import org.apache.commons.codec.digest.DigestUtils;
import org.bytedeco.javacpp.Loader;
import org.mp4parser.IsoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongzonglei
 * @description
 * @date 2020/6/23 上午11:29
 */
public class ConvertMedia {
    
    private static final Logger log = LoggerFactory.getLogger(ConvertMedia.class);
    
    public static void main(String[] args) {
        String file = "/data/media/testvideo.mp4";
        String pcmdir = "/data/media";
        Path path = Paths.get(file);
        convertMP4toPCM(path, Paths.get(pcmdir));
    
//        Path root = Paths.get("D:\\dev2\\project\\thomas\\local\\videos\\第18季");
//        Path pcmDir = Paths.get("D:\\dev2\\project\\thomas\\local\\videos\\pcm");
//        int pcmFiles = batchConvertMP4toPCM(root, pcmDir);
//        log.info("转换出PCM文件数{}", pcmFiles);
    }
    
    public static long readDuration(Path mp4Path) {
        if (Files.notExists(mp4Path) || !Files.isReadable(mp4Path)) {
            log.warn("文件路径不存在或不可读 {}", mp4Path);
            return 0;
        }
        try {
            IsoFile isoFile = new IsoFile(mp4Path.toFile());
            long duration = isoFile.getMovieBox().getMovieHeaderBox().getDuration();
            long timescale = isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
            return duration / timescale;
        } catch (IOException e) {
            log.error("读取MP4文件时长出错", e);
            return 0;
        }
    }
    
    /**
     * 将单个PM4文件进行片头和片尾歌曲删除后，转换为PCM文件
     *
     * @param mp4Path
     * @param pcmDir
     * @return 转换完成后的pcm文件路径
     */
    public static Optional<String> convertMP4toPCM(Path mp4Path, Path pcmDir) {
        long seconds = readDuration(mp4Path);
        if (seconds == 0) {
            log.warn("文件总时长为0");
            return Optional.empty();
        }
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        String endTime = String.valueOf(seconds - 100 - 30);
        File src = mp4Path.toFile();
        //在当前源mp4文件目录下生成临时文件
        String mp4TempFile = src.getParent() + "\\" + System.currentTimeMillis() + ".mp4";
        //基于ffmpeg进行截取
        ProcessBuilder cutBuilder = new ProcessBuilder(ffmpeg, "-ss", "30", "-i", mp4Path.toAbsolutePath().toString(),
                "-to", endTime, "-c", "copy", mp4TempFile);
        try {
            cutBuilder.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            log.error("ffmpeg截取MP4文件出错", e);
            return Optional.empty();
        }
        // 基于ffmpeg进行pcm转换
        // 基于输入路径的md5值来命名，也可以基于系统时间戳来命名
        String pcmFile = pcmDir.resolve(DigestUtils.md5Hex(mp4Path.toString()) + ".pcm").toString();
        ProcessBuilder pcmBuilder = new ProcessBuilder(ffmpeg, "-y", "-i", mp4TempFile, "-vn", "-acodec", "pcm_s16le",
                "-f", "s16le", "-ac", "1", "-ar", "16000", pcmFile);
        try {
            //inheritIO是指将 子流程的IO与当前java流程的IO设置为相同
            pcmBuilder.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            log.error("ffmpeg将mp4转换为pcm时出错", e);
            return Optional.empty();
        }
        // 删除MP4临时文件
        try {
            Files.deleteIfExists(Paths.get(mp4TempFile));
        } catch (IOException e) {
            log.error("删除mp4临时文件出错", e);
        }
        //返回pcm文件路径
        return Optional.of(pcmFile);
    }
    
    /**
     * 批量将MP4文件转换为PCM文件
     *
     * @param rootDir
     * @param pcmDir
     * @return 成功转换的PCM文件数
     */
    public static int batchConvertMP4toPCM(Path rootDir, Path pcmDir) {
        if (Files.notExists(rootDir) || !Files.isDirectory(rootDir)) {
            log.warn("mp4文件目录{}不存在", rootDir);
            return 0;
        }
        
        if (Files.notExists(pcmDir)) {
            //级联创建目录
            try {
                Files.createDirectories(pcmDir);
            } catch (IOException e) {
                log.error("创建文件夹出错", e);
            }
        }
        AtomicInteger pcmCount = new AtomicInteger(0);
        //遍历rootdir，获取所有目录下子目录和文件
        try {
            Files.list(rootDir).forEach(path -> {
                if (Files.isDirectory(path)) {
                    //递归遍历下级目录
                    pcmCount.getAndAdd(batchConvertMP4toPCM(path, pcmDir));
                }
                if (Files.isRegularFile(path) && Files.isReadable(path) && path.getFileName()
                        .toString()
                        .endsWith("mp4")) {
                    Optional<String> pcmFile = convertMP4toPCM(path, pcmDir);
                    if (pcmFile.isPresent()) {
                        pcmCount.getAndIncrement();
                    }
                }
            });
        } catch (IOException e) {
            log.error("批量将MP4文件转换为PCM文件出错", e);
        }
        
        return pcmCount.get();
    }
}
