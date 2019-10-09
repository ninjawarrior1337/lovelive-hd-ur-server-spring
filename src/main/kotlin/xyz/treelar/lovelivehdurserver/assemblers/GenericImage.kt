package xyz.treelar.lovelivehdurserver.assemblers

import org.springframework.util.DigestUtils
import java.awt.image.BufferedImage
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths
import java.security.MessageDigest
import javax.imageio.ImageIO

class GenericImage(override var img: BufferedImage, override val scale: Int, originalFile: InputStream) : Waifu2xable
{
    override val inPath: Path
    override val outPath: Path
    private val hashDigest = DigestUtils.md5DigestAsHex(originalFile)

    init {
        inPath = Paths.get("./cardsIn/$hashDigest.png").toAbsolutePath().normalize()
        outPath = Paths.get("./cardsOut/$hashDigest.jpg").toAbsolutePath().normalize()
    }

    override fun downloadImage() {
        return
    }
}