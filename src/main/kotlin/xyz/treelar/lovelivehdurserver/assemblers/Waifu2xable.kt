package xyz.treelar.lovelivehdurserver.assemblers

import java.awt.image.BufferedImage
import java.nio.file.Path
import javax.imageio.ImageIO

interface Waifu2xable
{
    val inPath: Path
    val outPath: Path
    var img: BufferedImage
    val scale: Int

    fun writeImage()
    {
        ImageIO.write(img, "png", inPath.toFile().outputStream())
    }

    fun downloadImage()

    fun waifu2xIfy()
    {
        if (!inPath.toFile().exists())
        {
            downloadImage()
            writeImage()
        }

        if(!outPath.toFile().exists())
        {
            ProcessBuilder("/usr/bin/waifu2x-converter-cpp", "-i $inPath", "-o $outPath", "--scale-ratio $scale", "--noise-level 3")
                    .inheritIO()
                    .start()
                    .waitFor()
        }

        img = ImageIO.read(outPath.toFile().inputStream())
    }
}